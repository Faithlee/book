package jdbc.statement;

import java.io.FileInputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Properties;

public class JdbcStatement {

    private final Path path = Paths.get("book","jdbc", "resources", "database.properties");

    private String driver;

    private String url;

    private String user;

    private String pass;


    public void init(String paramFile) throws Exception {
        Properties properties = new Properties();
        properties.load(new FileInputStream(paramFile));
        driver = properties.getProperty("driver");
        url    = properties.getProperty("url");
        user   = properties.getProperty("user");
        pass   = properties.getProperty("pass");
    }

    public static void main(String[] args) throws Exception {
        JdbcStatement statement = new JdbcStatement();
        Path path = Paths.get("book","jdbc", "resources", "database.properties");

        statement.init(path.toFile().toString());
        statement.createTable();


        // insert
        StringBuilder insertSql = new StringBuilder();
        insertSql.append("insert into jdbc_test(jdbc_name, jdbc_text)");
        insertSql.append("select s.student_name, t.teacher_name from student_table s, teacher_table t where s.java_teacher=t.teacher_id;");
        JdbcStatement statement1 = new JdbcStatement();
        statement1.insert(insertSql.toString());
    }

    /**
     * 使用Statement处理语句执行DDL语句
     *
     *      todo executeUpdate()执行DDL语句，返回值为0
     *
     * @throws Exception
     */
    public void createTable() throws Exception {
        StringBuilder sb = new StringBuilder();
        String table = "jdbc_test";

        // 重新创建表
        sb.append("create table " + table + "(");
        sb.append("id int auto_increment primary key,");
        sb.append("jdbc_name varchar(255),");
        sb.append("jdbc_text varchar(100) ");
        sb.append(") engine=innodb;");
        System.out.println(sb);

        Class.forName(driver);
        try (
                Connection connection = DriverManager.getConnection(url, user, pass);
                Statement statement = connection.createStatement();
                ) {
            // 存在则删除
            statement.executeUpdate("drop table if exists " + table);
            // 执行创建表, 返回受影响数为0
            int result = statement.executeUpdate(sb.toString());
            System.out.println("执行DDL受影响数量为: " + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * statement执行DML语句
     *
     * @param insertSql
     * @throws Exception
     */
    public void insert(String insertSql) throws Exception {
        // 数据连接初始化
        Connection connection = connect(path.toFile().toString());
        if (null == connection) {
            throw new RuntimeException("连接数据库异常!");
        }

        // statement，执行execute
        Statement statement = connection.createStatement();

        // 使用executeUpdate执行DML语句，返回受影响数
        int insert = statement.executeUpdate(insertSql);
        System.out.println("插入返回受影响数: " + insert);

        statement.close();
        connection.close();
    }


    protected Connection connect(String database) throws Exception {
        Properties properties = new Properties();
        properties.load(new FileInputStream(database));
        driver = properties.getProperty("driver");
        url    = properties.getProperty("url");
        user   = properties.getProperty("user");
        pass   = properties.getProperty("pass");

        Class.forName(driver);
        Connection connection = DriverManager.getConnection(url, user, pass);

        return connection;
    }

}
