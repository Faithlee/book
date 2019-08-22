package jdbc.preparestatement;

import jdbc.base.Jdbc;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * sql预编译执行sql语句
 *
 * 1. 防止sql注入
 * 2. 参数无需拼接
 * 3. 执行效率更高比statement
 */
public class JdbcPrepareStatement extends Jdbc {

    public static void main(String[] args) throws Exception {
        Path path = Paths.get("book", "jdbc", "resources", "database.properties");

        JdbcPrepareStatement jdbc = new JdbcPrepareStatement();
        jdbc.initParam(path.toFile());

        // insert
        // jdbc.insertStatement();

        // select
        jdbc.queryStatement();
    }

    /**
     * executeUpdate()执行预编译插入
     *
     * @throws Exception
     */
    public void insertStatement() throws Exception {
        long start = System.currentTimeMillis();

        String insertSql = "insert into student_table values (null, ?, 1)";

        try (
            Connection connection = DriverManager.getConnection(url, user, pass);
            PreparedStatement statement = connection.prepareStatement(insertSql)
                ) {
            for (int i = 0; i < 100; i++) {
                statement.setString(1, "姓名" + 1);
                statement.executeUpdate();
            }
            System.out.println("费时: " + (System.currentTimeMillis() - start));
        } catch (Exception e) {

        }
    }

    /**
     * 预编译执行查询
     */
    public void queryStatement() {
        String sql = "select * from jdbc_test where id=?";

        try (
                Connection connection = DriverManager.getConnection(url, user, pass);
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setInt(1, 3);
            try (
                ResultSet resultSet = statement.executeQuery()
            ) {
                if (resultSet.next()) {
                   System.out.println(resultSet.getString(1) + "存在!");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
