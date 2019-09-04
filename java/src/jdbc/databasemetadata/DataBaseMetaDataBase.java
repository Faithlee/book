package jdbc.databasemetadata;

import jdbc.base.Jdbc;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;

/**
 * 获取数据库信息
 *
 * 1. 使用DatabaseMetaBase分析:跨数据库特性好
 *
 * 2. 确定数据库系统时，通过系统字典表(information_schema)查看，读取到的信息更准确
 */
public class DataBaseMetaDataBase extends Jdbc {

    public static void main(String[] args) throws Exception {
        Path path = Paths.get("book", "jdbc", "resources", "database.properties");

        DataBaseMetaDataBase jdbc = new DataBaseMetaDataBase();
        jdbc.initParam(path.toFile());

        jdbc.getMetaData();
    }

    /**
     * 元数据获取
     *
     * @throws Exception
     */
    public void getMetaData() throws Exception {
        try (
                Connection connection = DriverManager.getConnection(url, user, pass)
                ) {
            DatabaseMetaData metaData = connection.getMetaData();

            // 支持的所有表类型
            ResultSet resultSet = metaData.getTableTypes();
            printDataMeta(resultSet);
            System.out.println();

            // 获取当前数据库的全部表
            resultSet = metaData.getTables(null, null, "%", new String[]{"TABLE"});
            printDataMeta(resultSet);
            System.out.println();

            // 获取指定表的主键
            resultSet = metaData.getPrimaryKeys(null, null, "jdbc_test");
            printDataMeta(resultSet);
            System.out.println();

            // 获取数据库全部储存过程
            resultSet = metaData.getProcedures(null, null, "%");
            printDataMeta(resultSet);
            System.out.println();

            // 获取外键约束
            resultSet = metaData.getCrossReference(
                    null,
                    null,
                    "teacher_table",
                    null,
                    null,
                    "student_table"
            );
            printDataMeta(resultSet);
            System.out.println();

            // 获取全部数据列
            resultSet = metaData.getColumns(null, null, "jdbc_test", "%");
            printDataMeta(resultSet);
        }
    }

    /**
     * 打印database元数据
     * @param resultSet
     * @throws SQLException
     */
    private void printDataMeta(ResultSet resultSet) throws SQLException{
        ResultSetMetaData metaData = resultSet.getMetaData();
        for (int i = 0; i < metaData.getColumnCount(); i++) {
            System.out.print(metaData.getColumnName(i+1) + "\t");
        }
        System.out.println();

        // 打印全部数据
        while (resultSet.next()) {
            for (int i = 0; i < metaData.getColumnCount(); i++) {
                System.out.print(resultSet.getString(i+1) + "\t");
            }
            System.out.println();
        }

        resultSet.close();
    }
}
