package Database;

import Config.Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {
    private static Connect connect = null;
    private static String url = Config.DB_URL;
    private static String username = Config.USER_NAME;
    private static String password = Config.PASSWORD;
    //Phạm vi truy cập Private để ngăn chặn khởi tạo object.
    private Connect() {

    }
    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, username, password);
//            System.out.println("Kết nối sever thành công");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return conn;
    }

    public static Connect getInstance() {
        return connect == null ? new Connect() : connect;
    }
}
