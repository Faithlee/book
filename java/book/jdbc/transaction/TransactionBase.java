package jdbc.transaction;

import jdbc.base.Jdbc;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * jdbc事务操作(ACID)
 *
 * 原子性
 * 一致性
 * 隔离性
 * 持久性
 *
 * 注意：需要保持源文件编码与数据库编码一致，否则会出现乱码
 */
public class TransactionBase extends Jdbc {

    public static void main(String[] args) throws Exception {
        Path path = Paths.get("book", "jdbc", "resources", "database.properties");

        TransactionBase jdbc = new TransactionBase();
        jdbc.initParam(path.toFile());

        String[] insertSql = new String[]{
                "insert into jdbc_test values(null, '赵六1', '小六子1')",
                "insert into jdbc_test values(null, '赵六2', '小六子2')",
        };
        jdbc.insert(insertSql);
    }

    /**
     * 测试事务
     */
    public void insert(String[] insertSql) throws Exception{
        try (
                Connection connection = DriverManager.getConnection(url, user, pass)
                ) {
            // todo 关闭自动提交，开启事务
            connection.setAutoCommit(false);

            try (
                    Statement statement = connection.createStatement()
                    ) {

//                for (String insert : insertSql) {
//                    statement.executeUpdate(insert);
//                }

                for (int i = 0; i < insertSql.length; i++) {
                    statement.executeUpdate(insertSql[i]);
                    if (i == insertSql.length - 1) {
                        throw new SQLException("测试事务!");
                    }
                }
            }

            // 提交事务
            connection.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
