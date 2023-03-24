package DAO;

import Database.Connect;
import Model.Account;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AccountDAO {
    public Account login(String name, String password) {
        Connection conn = null;
        Statement stmt = null;
        boolean loggedIn = false; // biến đánh dấu trạng thái đăng nhập
        try {
            conn = Connect.getInstance().getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT * FROM accounts WHERE username = '" + name + "' and password ='"+password+"' ";
            ResultSet rs = stmt.executeQuery(sql);
            Account s = null;
            if (rs.next()) {
                System.out.println("Đăng nhập thành công !");
//                System.out.println("Login successful !");
//                System.out.println("Chạy hàm menu trong này ");
                loggedIn = true; // đánh dấu trạng thái đăng nhập là true
            } else {
                System.out.println("Tài khoản đăng nhập không tồn tại hoặc sai thông tin đăng nhập !");
                System.out.println("Invalid username or password !");
            }
            return s;
        } catch (Exception s) {
            throw new RuntimeException(s);
        } finally {
            if(stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if(conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
//            if(!loggedIn){
//                // Hiển thị thông báo lỗi tài khoản hoặc mật khẩu
//                System.out.println("Invalid username or password");
//            }
        }
    }
}
