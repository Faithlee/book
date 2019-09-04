package jdbc.datasource;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import jdbc.base.Jdbc;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.sql.DataSource;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * C3P0相比之下，性能更优
 *
 * 此连接池不仅可以自动清理不再使用的Connection，还可自动清理Statement、ResultSet
 */
public class ComboPooledDataSourceBase extends Jdbc {

    private static final Logger logger = LogManager.getLogger(ComboPooledDataSourceBase.class);

    /**
     * 通过根路径下的c3p0-config.xml配置文件初始化
     */
    private static DataSource dataSource;

    static {
        dataSource = new ComboPooledDataSource("intergalactoApp");
    }

    public static void main(String[] args) throws Exception {
        ComboPooledDataSourceBase jdbc = new ComboPooledDataSourceBase();

        logger.info("查询开始");
        jdbc.executeQueryWithoutC3P0Config();
        logger.info("查询结束");

        logger.info("使用配置文件初始化开始");
        jdbc.executeQueryWithC3P0Config();
        logger.info("使用配置文件初始化结束");
    }

    /**
     * 不使用c3p0配置文件
     *
     * @throws Exception
     */
    public void executeQueryWithoutC3P0Config() throws Exception {
        String query = "select * from jdbc_test";

        Path path = Paths.get("src", "jdbc", "resources", "database.properties");
        initParam(path.toFile());
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setDriverClass(driver);
        dataSource.setJdbcUrl(url);
        dataSource.setUser(user);
        dataSource.setPassword(pass);

        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                System.out.println(resultSet.getString(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 通过c3p0-config.xml初始化连接池
     *
     * @throws Exception
     */
    public void executeQueryWithC3P0Config() throws Exception {
        Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement("select * from jdbc_test");
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            System.out.println(resultSet.getString(2));
        }

        resultSet.close();
        statement.close();
        connection.close();
    }
}
