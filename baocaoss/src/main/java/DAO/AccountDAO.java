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
//    public Account login(String name, String password) {
//        Connection conn = null;
//        Statement stmt = null;
//        boolean loggedIn = false; // biến đánh dấu trạng thái đăng nhập
//        try {
//            conn = Connect.getInstance().getConnection();
//            stmt = conn.createStatement();
//            String sql = "SELECT * FROM accounts WHERE username = '" + name + "' and password ='"+password+"' ";
//            ResultSet rs = stmt.executeQuery(sql);
//            Account s = null;
//            if (rs.next()) {
//                System.out.println("Đăng nhập thành công !");
////                System.out.println("Login successful !");
////                System.out.println("Chạy hàm menu trong này ");
//                loggedIn = true; // đánh dấu trạng thái đăng nhập là true
//            } else {
//                System.out.println("Tài khoản đăng nhập không tồn tại hoặc sai thông tin đăng nhập !");
//                System.out.println("Invalid username or password !");
//            }
//            return s;
//        } catch (Exception s) {
//            throw new RuntimeException(s);
//        } finally {
//            if(stmt != null) {
//                try {
//                    stmt.close();
//                } catch (SQLException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//            if(conn != null) {
//                try {
//                    conn.close();
//                } catch (SQLException e) {
//                    throw new RuntimeException(e);
//                }
//            }
////            if(!loggedIn){
////                // Hiển thị thông báo lỗi tài khoản hoặc mật khẩu
////                System.out.println("Invalid username or password");
////            }
//        }
//    }


//    public Account login(String username, String password) {
//        Connection conn = null;
//        Statement stmt = null;
//        boolean loggedIn = false; // biến đánh dấu trạng thái đăng nhập
//        Account account = null;
//
//        while(!loggedIn) {
//            try {
//                conn = Connect.getInstance().getConnection();
//                stmt = conn.createStatement();
//
//                String sql = "SELECT * FROM accounts WHERE username = '" + username + "' and password ='" + password + "'";
//                ResultSet rs = stmt.executeQuery(sql);
//
//                if (rs.next()) {
//                    System.out.println("Đăng nhập thành công !");
//                    loggedIn = true; // đánh dấu trạng thái đăng nhập là true
//                    int accountID = rs.getInt("AccountID");
//                    String name = rs.getString("username");
//                    account = new Account(accountID,username, password);
//                } else {
//                    System.out.println("Tài khoản đăng nhập không tồn tại hoặc sai thông tin đăng nhập !");
//                    System.out.println("Bạn có muốn đăng nhập lại không? (y/n)");
//                    String answer = sc.nextLine();
//                    if(answer.equals("n")) {
//                        break; // thoát khỏi vòng lặp nếu người dùng không muốn nhập lại
//                    }
//                }
//            } catch (Exception e) {
//                throw new RuntimeException(e);
//            } finally {
//                if(stmt != null) {
//                    try {
//                        stmt.close();
//                    } catch (SQLException e) {
//                        throw new RuntimeException(e);
//                    }
//                }
//                if(conn != null) {
//                    try {
//                        conn.close();
//                    } catch (SQLException e) {
//                        throw new RuntimeException(e);
//                    }
//                }
//            }
//        }
//        return account;
//    }

//    public Account login(String name, String password) {
//        Connection conn = null;
//        Statement stmt = null;
//        boolean loggedIn = false; // biến đánh dấu trạng thái đăng nhập
//        try {
//            conn = Connect.getInstance().getConnection();
//            stmt = conn.createStatement();
//            String sql = "SELECT * FROM accounts WHERE username = '" + name + "' and password ='" + password + "' ";
//            ResultSet rs = stmt.executeQuery(sql);
//            Account s = null;
//            if (rs.next()) {
//                System.out.println("Đăng nhập thành công !");
//                // đánh dấu trạng thái đăng nhập là true
//                loggedIn = true;
//                // tạo đối tượng Account và trả về nó
//                s = new Account(rs.getInt("id"), rs.getString("username"), rs.getString("password"));
//            } else {
//                System.out.println("Tài khoản đăng nhập không tồn tại hoặc sai thông tin đăng nhập !");
//                System.out.println("Invalid username or password !");
//            }
//            return s;
//        } catch (Exception s) {
//            throw new RuntimeException(s);
//        } finally {
//            if (stmt != null) {
//                try {
//                    stmt.close();
//                } catch (SQLException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//            if (conn != null) {
//                try {
//                    conn.close();
//                } catch (SQLException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//            if (!loggedIn) {
//                // Hiển thị thông báo lỗi tài khoản hoặc mật khẩu
//                System.out.println("Tài khoản hoặc mật khẩu không chính xác. Vui lòng nhập lại:");
//                Scanner sc = new Scanner(System.in);
//                System.out.print("Tên đăng nhập: ");
//                name = sc.nextLine();
//                System.out.print("Mật khẩu: ");
//                password = sc.nextLine();
//                // gọi lại hàm login với thông tin đăng nhập mới
//                return login(name, password);
//            }
//        }
//    }

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
                System.out.println("Đăng nhập thành công !");
                // đánh dấu trạng thái đăng nhập là true
                loggedIn = true;
                // tạo đối tượng Account và trả về nó
                s = new Account(rs.getInt("AccountID"), rs.getString("username"), rs.getString("password"));
            } else {
                System.out.println("Tài khoản đăng nhập không tồn tại hoặc sai thông tin đăng nhập !");
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
                System.out.println("Tài khoản hoặc mật khẩu không chính xác.");
                Scanner sc = new Scanner(System.in);
                String response;
                do {
                    System.out.print("Bạn có muốn đăng nhập lại không? (y/n) ");
                    response = sc.nextLine();
                } while (!response.equalsIgnoreCase("y") && !response.equalsIgnoreCase("n"));
                if (response.equalsIgnoreCase("y")) {
                    System.out.print("Tên đăng nhập: ");
                    name = sc.nextLine();
                    System.out.print("Mật khẩu: ");
                    password = sc.nextLine();
                    // gọi lại hàm login với thông tin đăng nhập mới
                    return login(name, password);
                } else {
                    // Hỏi người dùng có muốn thoát chương trình không
                    String exitResponse;
                    do {
                        System.out.print("Bạn có muốn thoát chương trình không? (y/n) ");
                        exitResponse = sc.nextLine();
                    } while (!exitResponse.equalsIgnoreCase("y") && !exitResponse.equalsIgnoreCase("n"));
                    if (exitResponse.equalsIgnoreCase("y")) {
                        System.exit(0);
                    }
                }
            }
        }
    }




}
