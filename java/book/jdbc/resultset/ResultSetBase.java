package jdbc.resultset;

import jdbc.base.Jdbc;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * 结果集操作
 *
 * 1. 可滚动的结果集，只能用于一张表，而且必须包含主键
 */
public class ResultSetBase extends Jdbc {


    public static void main(String[] args) throws Exception {
        Path path = Paths.get("book", "jdbc", "resources", "database.properties");

        ResultSetBase jdbc = new ResultSetBase();
        jdbc.initParam(path.toFile());

        // 滚动结果集
        jdbc.scrollResultSet("select * from student_table");
    }

    /**
     * 将查询的结果集遍历并且更新
     *
     * @param querySql
     */
    public void scrollResultSet(String querySql) {
        try (
                Connection connection = DriverManager.getConnection(url, user, pass);
                PreparedStatement statement = connection.prepareStatement(
                        querySql,
                        ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_UPDATABLE
                );
                ResultSet resultSet = statement.executeQuery();
        ) {
            resultSet.last();
            int rowCount = resultSet.getRow();
            for (int i = rowCount; i > 0; i--) {
                resultSet.absolute(i);
                System.out.println(resultSet.getString(1) + "\t" + resultSet.getString(2) + "\t" + resultSet.getString(3));

                // 修改记录指针所指记录
                resultSet.updateString(2, "学生名" + i);
                resultSet.updateRow();
            }
        } catch (Exception e) {

        }

    }

}
