package jdbc.base;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * jdbc操作数据库基础
 */
public class Base {

    public static void main(String[] args) throws Exception {
        // 1. 使用反射加载数据库驱动
        Class.forName("com.mysql.jdbc.Driver");
        try (
                // 2. 使用DriverManager获取数据库连接，表示一个会话连接
                Connection connection = DriverManager.getConnection("jdbc:mysql://192.168.10.146:3306/test", "faithlee", "mysql@098");
                // 3. 使用Connection创建Statement对象
                Statement statement = connection.createStatement();
                // 4. 执行sql语句: execute()、executeQuery()、executeUpdate();
                ResultSet resultSet = statement.executeQuery("select s.*, teacher_name from student_table s, teacher_table t where t.teacher_id=s.java_teacher")
                ) {
            // 5. 记录指针指向行、特定例的值
            while (resultSet.next()) {
                System.out.println(resultSet.getInt(1) + "\t" + resultSet.getString(2) + "\t" + resultSet.getString(3) + "\t" + resultSet.getString(4));
                // 可读性好
                //System.out.println(resultSet.getString("teacher_name"));
                // 列索引性能好
                //System.out.println(resultSet.getString(4));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
