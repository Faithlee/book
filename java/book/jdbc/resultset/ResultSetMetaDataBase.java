package jdbc.resultset;

import java.io.FileInputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * 分析ResultSetMetaData
 *
 * 可以分析ResultSet里包含多少列，列名及类型
 */
public class ResultSetMetaDataBase {

    /**
     * 静态初始化块创建Connection对象
     */
    private static Connection connection;

    /**
     * 静态初始化块创建Connection对象
     */
    private static Statement statement;

    /**
     * 数据库连接文件
     */
    private static Path path = Paths.get("book", "jdbc", "resources", "database.properties");

    static {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream(path.toFile().toString()));
            String driver = properties.getProperty("driver");
            String url    = properties.getProperty("url");
            String user   = properties.getProperty("user");
            String pass   = properties.getProperty("pass");

            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, pass);
            statement  = connection.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // 当执行的sql查询以后无法知道包含哪些数据列及数据类型
        ResultSetMetaDataBase jdbc = new ResultSetMetaDataBase();

        String querySql = "select * from jdbc_test";
        jdbc.executeQuery(querySql);

        // 联表查询
        String joinQuerySql = "select s.student_name, t.teacher_name from student_table s, teacher_table t where s.java_teacher=t.teacher_id;";
        jdbc.executeQuery(joinQuerySql);
    }

    /**
     * 执行sql查询，分析ResultSetMetaData数据
     *
     * @param querySql
     */
    public void executeQuery(String querySql) {
        try (
                ResultSet resultSet = statement.executeQuery(querySql)
                ) {
            ResultSetMetaData metaData = resultSet.getMetaData();

            // 输出所有列名
            List<String> columnName = new ArrayList<>();
            for (int i = 0; i < metaData.getColumnCount(); i++) {
                //columnName.add(metaData.getColumnName(i+1));
                System.out.print(metaData.getColumnName(i+1) + "\t");
            }
            System.out.println();

            // 输出所有列值
            while (resultSet.next()) {
                for (int i = 0; i < metaData.getColumnCount(); i++) {
                    System.out.print(resultSet.getString(i+1) + "\t");
                }
                System.out.println();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
