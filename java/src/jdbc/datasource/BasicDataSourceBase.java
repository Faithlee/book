package jdbc.datasource;

import jdbc.base.Jdbc;
import org.apache.commons.dbcp2.BasicDataSource;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * DBCP数据源实现连接池
 *
 *  将数据库连接池设置为static修饰，启动时在初始化块中创建连接池
 *
 *  需要引入commons-dbcp.jar、commons-pool.jar、common-logging.jar包，注意版本问题
 *
 * Tomcat的连接池也是使用连接池
 */
public class BasicDataSourceBase extends Jdbc {

    /**
     * 初始连接数
     */
    private final static Integer initialSize = 5;

    /**
     * 最大活动连接数
     */
    private final static Integer maxActive = 20;

    /**
     * 最少空闲连接数
     */
    private final static Integer minIdle = 2;

    public static void main(String[] args) {
        BasicDataSourceBase jdbc = new BasicDataSourceBase();

        String query = "select * from jdbc_test";
        jdbc.executeQuery(query);
    }

    /**
     * sql查询
     *
     * @param query
     */
    public void executeQuery(String query) {
        Path path = Paths.get("src", "jdbc", "resources", "database.properties");

        try (
            Connection connection = createDataSource(path.toFile());
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery()
                ) {

            while (resultSet.next()) {
                System.out.println(resultSet.getString(1) + "\t" + resultSet.getString(2));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建连接池
     *
     * @param database
     * @return
     * @throws Exception
     */
    private Connection createDataSource(File database) throws Exception{
        initParam(database);

        BasicDataSource dataSource = new BasicDataSource();

        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(pass);

        // 初始化数据库连接池
        dataSource.setInitialSize(initialSize);
        dataSource.setMaxTotal(maxActive);
        dataSource.setMinIdle(minIdle);

        return dataSource.getConnection();
    }
}
