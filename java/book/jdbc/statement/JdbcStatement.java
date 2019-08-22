package jdbc.statement;

import jdbc.base.Jdbc;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;

public class JdbcStatement extends Jdbc {

    public static void main(String[] args) throws Exception {
        Path path = Paths.get("book","jdbc", "resources", "database.properties");

        JdbcStatement statement = new JdbcStatement();
        statement.initParam(path.toFile());

        // create table
//        statement.initParam(path.toFile());
//        statement.createTable();

        // insert
//        StringBuilder insertSql = new StringBuilder();
//        insertSql.append("insert into jdbc_test(jdbc_name, jdbc_text)");
//        insertSql.append("select s.student_name, t.teacher_name from student_table s, teacher_table t where s.java_teacher=t.teacher_id;");
//        JdbcStatement insertStatement = new JdbcStatement();
//        insertStatement.insert(insertSql.toString());

        // 使用executeQuery()查询结果集
        String query = "select jdbc_name, jdbc_text from jdbc_test";
        statement.query(query);

        // 使用execute()执行任何类型的sql语句
        // 查询
        String querySql = "select jdbc_name, jdbc_text from jdbc_test";
        statement.executeSql(querySql);
        // 删除
//        String deleteSql = "delete from jdbc_test limit 1";
//        statement.executeSql(deleteSql);
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
        sb.append(") engine=innodb DEFAULT CHARSET=utf8;");
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
     * statement执行DML语句(insert/update/delete)
     *
     * @param insertSql
     * @throws Exception
     */
    public void insert(String insertSql) throws Exception {
        // 数据连接初始化
        Connection connection = DriverManager.getConnection(url, user, pass);
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

    /**
     * 执行executeQuery()查询结果集
     *
     * @param query
     * @throws Exception
     */
    public void query(String query) throws Exception {
        try (
               Connection connection = DriverManager.getConnection(url, user, pass);
               Statement statement = connection.createStatement();
               ResultSet resultSet = statement.executeQuery(query);
                ) {
            while (resultSet.next()) {
                System.out.println(resultSet.getString(1) + "\t" + resultSet.getString(2));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 使用execute()方法执行
     *
     * @param sql
     * @throws Exception
     */
    public void executeSql(String sql) throws Exception {
        try (
                Connection connection = DriverManager.getConnection(url, user, pass);
                Statement statement = connection.createStatement()
                ) {
            // 使用execute()执行sql返回bool值，如果不清楚执行的语句的类型只能使用此方法执行
            // 通常推荐使用executeUpdate()或 executeQuery()
            boolean hasResultRet = statement.execute(sql);
            // 如果执行DML语句则返回受影响数
            //Integer count = statement.getUpdateCount();
            // 如果执行查询则得到结果集
            //ResultSet resultSet = statement.getResultSet();
            if (hasResultRet) {
                try (
                        ResultSet resultSet = statement.getResultSet();
                        )  {
                    ResultSetMetaData metaData = resultSet.getMetaData();
                    int columnCount = metaData.getColumnCount();
                    while (resultSet.next()) {
                        for (int i = 0; i < columnCount; i++) {
                            // todo 注意列索引必须从1开始
                            System.out.print(resultSet.getString(i + 1) + "\t");
                        }
                        System.out.println();
                    }
                }
            } else {
                System.out.println("sql语句影响的记录有:" + statement.getUpdateCount());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
