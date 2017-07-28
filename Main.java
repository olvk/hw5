import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {

        JdbcUtils.executeU(JdbcUtils.CREATE_TABLE_USER, JdbcUtils.CREATE_TABLE_POSTS, JdbcUtils.INSERT_INTO_USER,
                JdbcUtils.INSERT_INTO_POSTS, JdbcUtils.SELECT_FROM_USER, JdbcUtils.SELECT_FROM_POSTS,
                JdbcUtils.SELECT_LOGINS_WITH_POSTS, JdbcUtils.SELECT_USERS_WITH_POSTS);
//        JdbcUtils.executeU(JdbcUtils.CREATE_TABLE_POSTS);
//        JdbcUtils.executeU(JdbcUtils.INSERT_INTO_USER);
//        JdbcUtils.executeU(JdbcUtils.INSERT_INTO_POSTS);
//        JdbcUtils.executeQ(JdbcUtils.SELECT_FROM_USER);
//        JdbcUtils.executeQ(JdbcUtils.SELECT_FROM_POSTS);
//        JdbcUtils.executeQ(JdbcUtils.SELECT_USERS_WITH_POSTS);





//        Connection mysql = JdbcUtils.getConnection();
//        mysql.close();






    }
}
