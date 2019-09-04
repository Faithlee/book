package jdbc.rowset;

import com.sun.rowset.JdbcRowSetImpl;
import jdbc.base.Jdbc;

import javax.sql.RowSet;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * RowSet继承了ResultSet接口，不需要与数据库保持连接(除JdbcRowSet外)
 *
 * 1. RowSet默认是可滚动的、可更新、可序列化
 * 2. 可作为JavaBean使用，能方便地在网络传输
 * 3. 可以实现离线（ResultSet的结果必须立即处理，一旦Connection关闭则再读取时会引发异常）
 *
 * RowSetProvider类负责创建RowSetFactory接口，
 */
public class RowSetBase extends Jdbc {

    public static void main(String[] args) throws Exception {
        Path path = Paths.get("book", "jdbc", "resources", "database.properties");

        RowSetBase jdbc = new RowSetBase();
        jdbc.initParam(path.toFile());

        // RowSet基础操作及实现分页
//        String query = "select * from student_table";
//        jdbc.jdbcRowSet(query);

        // 使用RowSetProvider创建
//        String query1 = "select * from jdbc_test;";
//        jdbc.createRowSetFactory(query1);
//        System.exit(0);

        // 3. 使用离线RowSet
//        String query2 = "select * from student_table";
//        jdbc.foreachRowSet(query2, 0, 0);
//        System.exit(0);

        // 4. 离线分页
        String query3 = "select * from student_table";
        System.out.println("第一页:");
        jdbc.foreachRowSet(query3, 1, 10);
        System.out.println("第二页:");
        jdbc.foreachRowSet(query3, 2, 10);


    }

    /**
     * JdbcRow实例
     *
     * 实现结果集滚动、修改
     *
     * @param querySql
     */
    public void jdbcRowSet(String querySql) {
        try (
                Connection connection = DriverManager.getConnection(url, user, pass);
                JdbcRowSetImpl jdbcRowSet = new JdbcRowSetImpl(connection)
                ) {
            // 设置查询语句及查询
            jdbcRowSet.setCommand(querySql);
            jdbcRowSet.execute();

            // 向前滚动结果集
            jdbcRowSet.afterLast();
            while (jdbcRowSet.previous()) {
                System.out.println(jdbcRowSet.getString(1) + "\t" + jdbcRowSet.getString(2) + "\t" + jdbcRowSet.getString(3));

                // 修改指定记录
                if (jdbcRowSet.getInt("student_id") == 3) {
                    jdbcRowSet.updateString("student_name", "猪八戒");
                    jdbcRowSet.updateRow();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建RowSetFactory示例
     */
    public void createRowSetFactory(String query) throws Exception {
        RowSetFactory factory = RowSetProvider.newFactory();
        try (
                JdbcRowSet rowSet = factory.createJdbcRowSet()
                ) {
            rowSet.setUrl(url);
            rowSet.setUsername(user);
            rowSet.setPassword(pass);
            rowSet.setCommand(query);
            rowSet.execute();

            // 遍历查询结果
            while (rowSet.next()) {
                System.out.println(rowSet.getString(1) + "\t" + rowSet.getString(2) + "\t" + rowSet.getString(3));
            }
        } catch (Exception e) {

        }
    }

    /**
     * 遍历RowSet离线结果集并修改指定记录
     *
     * @param query
     * @throws Exception
     */
    public void foreachRowSet(String query, Integer pageIndex, Integer pageSize) throws Exception {
        CachedRowSet rowSet = createCachedRowSet(query, pageIndex, pageSize);
        //System.out.println(rowSet.size());

        rowSet.afterLast();
        while (rowSet.previous()) {
            System.out.println(rowSet.getString(1) + "\t" + rowSet.getString(2) + "\t" + rowSet.getString(3));

            // 更新指定记录
//            if (rowSet.getInt("id") == 3) {
//                rowSet.updateString("jdbc_name", "孙悟空");
//                rowSet.updateRow();
//            }
        }

        // todo 重新连接数据库，更新RowSet所做的修改同步到底层数据库
        // todo exception: SyncProviderException
//        Connection connection = DriverManager.getConnection(url, user, pass);
//        connection.setAutoCommit(false);
//        rowSet.acceptChanges(connection);
    }


    /**
     * CachedRowSet是所有离线的父接口
     *
     * 离线RowSet可以直接当作JavaBean使用，安全简单
     */
    private CachedRowSet createCachedRowSet(String query, Integer pageIndex, Integer pageSize) throws Exception {
        Connection connection = DriverManager.getConnection(url, user, pass);
        Statement  statement  = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);

        // todo 将所有的查询结果得到再装入RowSet，不推荐RowSet的分页
//        while (resultSet.next()) {
//            System.out.println(resultSet.getString(1));
//        }

        // 创建离线RowSet
        RowSetFactory factory = RowSetProvider.newFactory();
        CachedRowSet cachedRowSet = factory.createCachedRowSet();

        // todo 使用ResultSet封闭RowSet，并且是否分页(不推荐分页操作)
        if (pageIndex > 0) {
            cachedRowSet.setPageSize(pageSize);
            cachedRowSet.populate(resultSet, (pageIndex - 1) * pageSize + 1);
        } else {
            cachedRowSet.populate(resultSet);
        }

        resultSet.close();
        statement.close();
        connection.close();

        return cachedRowSet;
    }
}
