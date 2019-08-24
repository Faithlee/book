package jdbc.datasource;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import jdbc.base.Jdbc;

import java.io.FileInputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

/**
 * C3P0相比之下，性能更优
 *
 * 此连接池不仅可以自动清理不再使用的Connection，还可自动清理Statement、ResultSet
 */
public class ComboPooledDataSourceBase extends Jdbc {

    private static ComboPooledDataSource dataSource;

    static {
//        try {
//            Path path = Paths.get("book", "jdbc", "resources", "database.properties");
//            Properties properties = new Properties();
//            properties.load(new FileInputStream(path.toFile()));
//            String driver = properties.getProperty("driver");
//            String url = properties.getProperty("url");
//            String user = properties.getProperty("user");
//            String pass = properties.getProperty("pass");
//            System.out.println(driver);
//
//            // todo 创建数据库连接池
//            dataSource.setDriverClass(driver);
//            dataSource.setJdbcUrl(url);
//            dataSource.setUser(user);
//            dataSource.setPassword(pass);
//
//            // todo 初始化连接池
//            // 最大连接数
//            dataSource.setMaxPoolSize(40);
//            // 最小连接数
//            dataSource.setMinPoolSize(2);
//            // 初始连接数
//            dataSource.setInitialPoolSize(10);
//            // 缓存Statement的最大数
//            dataSource.setMaxStatements(180);
//        } catch (Exception e) {
//            //throw new SQLException(e.getMessage());
//            e.printStackTrace();
//        }
    }

    public static void main(String[] args) throws Exception {
        ComboPooledDataSourceBase jdbc = new ComboPooledDataSourceBase();

        String updateSql = "update jdbc_test set jdbc_name='赵六' where id=?";
        jdbc.executeUpdate(updateSql);
    }

    /**
     * 通过数据源获取数据库连接，更新数据
     *
     * @param updateSql
     * @throws Exception
     */
    public void executeUpdate(String updateSql) throws Exception {

        Path path = Paths.get("book", "jdbc", "resources", "database.properties");
        initParam(path.toFile());
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setDriverClass(driver);
        dataSource.setJdbcUrl(url);
        dataSource.setUser(user);
        dataSource.setPassword(pass);

        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(updateSql)
                ) {
            //statement.setString(1, "6");
            statement.setInt(1, 6);
            Integer count = statement.executeUpdate();
            System.out.println("受影响记录数: " + count);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
