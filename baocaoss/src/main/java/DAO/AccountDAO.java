package DAO;

import Database.Connect;
import Model.Account;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class AccountDAO {
    public static Scanner sc = new Scanner(System.in);
    public boolean loggedIn;

    public Account login(String name, String password) {
        Connection conn = null;
        Statement stmt = null;
        boolean loggedIn = false; // biến đánh dấu trạng thái đăng nhập
        try {
            conn = Connect.getInstance().getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT * FROM accounts WHERE username = '" + name + "' and password ='" + password + "' ";
            ResultSet rs = stmt.executeQuery(sql);
            Account s = null;
            if (rs.next()) {
                System.out.println("Dang nhap thanh cong !");
                // đánh dấu trạng thái đăng nhập là true
                loggedIn = true;
                // tạo đối tượng Account và trả về nó
                s = new Account(rs.getInt("AccountID"), rs.getString("username"), rs.getString("password"));
            } else {
                System.out.println("Tai khoan dang nhap khong ton tai hoac sai thong tin dang nhap !");
                System.out.println("Invalid username or password !");
            }
            return s;
        } catch (Exception s) {
            throw new RuntimeException(s);
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (!loggedIn) {
                // Hiển thị thông báo lỗi tài khoản hoặc mật khẩu và hỏi người dùng có muốn tiếp tục đăng nhập không
                System.out.println("Tai khoan hoac mat khau khong chinh xac.");
                Scanner sc = new Scanner(System.in);
                String response;
                do {
                    System.out.print("Ban co muon dang nhap lai khong ? (y/n) ");
                    response = sc.nextLine();
                } while (!response.equalsIgnoreCase("y") && !response.equalsIgnoreCase("n"));
                if (response.equalsIgnoreCase("y")) {
                    System.out.print("Username: ");
                    name = sc.nextLine();
                    System.out.print("Password: ");
                    password = sc.nextLine();
                    // gọi lại hàm login với thông tin đăng nhập mới
                    return login(name, password);
                } else {
                    // Hỏi người dùng có muốn thoát chương trình không
                    String exitResponse;
                    do {
                        System.out.print("Ban co muon thoat chuong trinh khong ? (y/n) ");
                        exitResponse = sc.nextLine();
                    } while (!exitResponse.equalsIgnoreCase("y") && !exitResponse.equalsIgnoreCase("n"));
                    if (exitResponse.equalsIgnoreCase("y")) {
                        System.exit(0);
                    }
                    return login(name, password);
                }
            }
        }
    }




}
