import java.sql.*;

public class JdbcUtils {


//    static {
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException("Couldn't register driver. Error message: " + e.getMessage());
//        }
//
//    }
//
//    public static Connection getConnection() {
//        try {
//            return DriverManager.getConnection("jdbc:mysql://localhost:3306/posts?useSSL=false"
//                    + "&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");
//        } catch (SQLException e) {
//            throw new RuntimeException("Couldn't get connection" + e.getMessage());
//        }
//    }

//
    static {
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Couldn't register driver. Error message: " + e.getMessage());
        }

    }

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:h2:mem:posts;INIT=create schema if not exists posts", "sa", "");
        } catch (SQLException e) {
            throw new RuntimeException("Couldn't get connection" + e.getMessage());
        }
    }

    public static final String CREATE_TABLE_USER=
            "create table if not exists user (" +
            "userId int auto_increment primary key," +
            "login varchar(15) not null unique," +
             "name varchar(30)," +
            "lastName varchar(30)" +
            ")";

    public static void executeU(String create1, String create2, String insert1, String insert2,
                                String select1, String select2, String select3, String select4) {
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            //создаем USER
            System.out.println("Table 1 created. Changed rows = "
                    + statement.executeUpdate(create1));
            //создаем POSTS
            System.out.println("Table 2 created. Changed rows = "
                    + statement.executeUpdate(create2));
            //заполняем USER
            System.out.println("Data inserted into table 1. Changed rows = "
                    + statement.executeUpdate(insert1));
            //заполняем POSTS
            System.out.println("Data inserted into table 2. Changed rows = "
                    + statement.executeUpdate(insert2));


            //выводим всю USER
            ResultSet rs = statement.executeQuery(select1);
            System.out.println("Table USER: ");
            while(rs.next()) {

                int userId = rs.getInt("userId");
                String login = rs.getString("login");
                String names = rs.getString("name");
                String lastName = rs.getString("lastName");

                System.out.println("id = " + userId +
                        "; login = " + login + "; names = " + names + "; lastName = "
                        + lastName);
        }
//

//                выводим всю POSTS
                ResultSet rs2 = statement.executeQuery(select2);
                System.out.println("\n Table POSTS: ");
                while(rs2.next()) {

                    int postId = rs2.getInt("postId");
                    String title = rs2.getString("title");
                    String text = rs2.getString("text");
                    String postedAt = rs2.getString("postedAt");
                    String p_userId = rs2.getString("userId");

                    System.out.println("\n postId = " + postId +
                            "\n title = " + title + "\n text = " + text + "\n postedAt = " + postedAt
                              + "\n userId = " + p_userId);
                }

                //only logins for users with posts
            System.out.println("\n Showing only logins for distinct users with posts: ");
            ResultSet rs4 = statement.executeQuery(select3);

            while(rs4.next()) {

                String login = rs4.getString("login");
                System.out.print(login + "; "
                );
            }

                //SELECT_USERS_WITH_POSTS
            System.out.println("\n Showing login, text and time of the post only for users with posts: ");
            ResultSet rs3 = statement.executeQuery(select4);

            while(rs3.next()) {

                String login = rs3.getString("login");
                String text = rs3.getString("text");
                String postedAt = rs3.getString("postedAt");

                System.out.println("\n login = " + login +
                         "\n text = " + text + "\n postedAt = " + postedAt
                        );
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void executeQ(String sql) {
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            System.out.println("Table = " + statement.executeQuery(sql));
        } catch  (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static final String CREATE_TABLE_POSTS=
           "create schema posts2;" +
						"create table if not exists posts2.posts(" +
                                "postId int primary key auto_increment," +
                                "title varchar(100) not null," +
                                "text varchar(300) not null," +
                                "postedAt timestamp not null," +
                                "userId int not null," +
                                "foreign key (userId) references public.user(userId)" +
                                ")";



    public static final String INSERT_INTO_USER=
                    "insert into user values (1, 'login1', 'name1', 'lastName1');" +
                    "insert into user values (2, 'login2', 'name2', 'lastName2');" +
                    "insert into user values (3, 'login3', 'name3', 'lastName3');" +
                    "insert into user values (4, 'login4', 'name4', 'lastName4');" +
                    "insert into user values (5, 'login5', 'name5', 'lastName5');" +
                    "insert into user values (6, 'login6', 'name6', 'lastName6');" +
                    "insert into user values (7, 'login7', 'name7', 'lastName7');" +
                    "insert into user values (8, 'login8', 'name8', 'lastName8');";

    public static final String INSERT_INTO_POSTS=
            "insert into posts2.posts values (1, 'funnyGirl', 'dont rain on my parade', CURRENT_TIMESTAMP(), 3);" +
                    "insert into posts2.posts values (2, 'westSideStory', 'a boy like that will kill your brother', CURRENT_TIMESTAMP(), 8);" +
                    "insert into posts2.posts values (3, 'shakespeare', 'once more unto the breach', CURRENT_TIMESTAMP(), 1);" +
                    "insert into posts2.posts values (4, 'lesMiserables', 'another day, another destiny', CURRENT_TIMESTAMP(), 2);" +
                    "insert into posts2.posts values (5, 'hamilton', 'i am not throwing away my shot', CURRENT_TIMESTAMP(), 4);" +
                    "insert into posts2.posts values (6, 'funHome', 'where you tell me you see me', CURRENT_TIMESTAMP(), 6);" +
                    "insert into posts2.posts values (7, 'grease', 'there are worse things i could do', CURRENT_TIMESTAMP(), 6);" +
                    "insert into posts2.posts values (8, 'westSideStory', 'forget that boy and find another', CURRENT_TIMESTAMP(), 5);" +
                    "insert into posts2.posts values (9, 'company', 'someone to sit in your chair and ruin your sleep', CURRENT_TIMESTAMP(), 2)";

    public static final String SELECT_FROM_USER=
            "select * from public.user";

    public static final String SELECT_FROM_POSTS=
            "select * from posts2.posts";

    public static final String SELECT_LOGINS_WITH_POSTS=
            "select distinct login " +
            "from posts2.posts " +
            "inner join public.user " +
            "on public.user.userId = posts2.posts.userId";

    public static final String SELECT_USERS_WITH_POSTS=
            "select distinct login, text, postedAt " +
            "from posts2.posts " +
            "inner join public.user " +
            "on public.user.userId = posts2.posts.userId";



}
