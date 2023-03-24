package DAO;

import Database.Connect;
import Model.Employees;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static Database.Connect.getConnection;

public class EmployeeDAO {

    //Thêm nhân viên
    public void addEmployee(List<Employees> employees) {
        Connection conn = null;
        PreparedStatement prst = null;
        try {
            conn = Connect.getInstance().getConnection();
            String sql = "INSERT INTO employees(fullName,age,gender,email,phone,salary,managerID,deptID,isDelete) VALUES (?,?,?,?,?,?,?,?,true)";
            prst = conn.prepareStatement(sql);
            for (Employees e : employees) {
                prst.setString(1, e.getFullName());
                prst.setInt(2, e.getAge());
                prst.setString(3, e.getGender());
                prst.setString(4, e.getEmail());
                prst.setString(5, e.getPhone());
                prst.setDouble(6, e.getSalary());
                prst.setInt(7, e.getManagerID());
                prst.setInt(8, e.getDeptID());

                if (e.getManagerID() == 0) {
                    prst.setNull(7, Types.INTEGER);
                } else {
                    prst.setInt(7, e.getManagerID());
                }
                if (e.getDeptID() == 0) {
                    prst.setNull(8, Types.INTEGER);
                } else {
                    prst.setInt(9, e.getDeptID());
                }
                prst.executeUpdate();
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        } finally {
            if (prst != null) {
                try {
                    prst.close();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
    }

    // lấy toàn bộ danh sách nhân viên
    public List<Employees> getAllEmployees() {
        List<Employees> ls = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT * FROM employees WHERE isDelete = 1";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Employees employees = new Employees(
                        rs.getInt("employeeID"),
                        rs.getString("fullName"),
                        rs.getInt("age"),// lấy dữ liệu từng cot trong bảng
                        rs.getString("gender"),
                        rs.getString("email"),
                        rs.getString("phone"),
                        rs.getDouble("salary"),
                        rs.getInt("managerID"),
                        rs.getInt("deptID"));

                ls.add(employees);  // lưu theo hàng vào list ls
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return ls;
    }

    //Xóa nhân viên

//    public Employees deleteEmployee(int employeeID) {
//        Connection conn = null;
//        Statement prst = null;
//        try {
//            conn = Connect.getInstance().getConnection();
//            prst = conn.createStatement();
//            String sql = "DELETE FROM employees WHERE employeeID =" + employeeID;
//            prst.executeUpdate(sql);
//            System.out.println("Xóa thành công nhân viên có id :" + employeeID);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        } finally {
//            if(conn != null) {
//                try {
//                    conn.close();
//                } catch (SQLException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//        }
//        return null;
//    }

    public Employees deleteEmployee(int employeeID) {
        Connection conn = null;
        Statement prst = null;
        try {

            // cập nhật danh sách nhân viên mới nhất từ CSDL
            conn = Connect.getInstance().getConnection();
            prst = conn.createStatement();
//          String sql = "DELETE FROM employees WHERE employeeID =" + employeeID;
            String sql = "UPDATE employees SET isDelete = 0 WHERE isDelete = 1 AND employeeID =" + employeeID;
            int rowsAffected = prst.executeUpdate(sql);
            if (rowsAffected == 0) {
                System.out.println("Không tồn tại nhân viên với id: " + employeeID);
            } else {
                System.out.println("Xóa thành công nhân viên có id: " + employeeID);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return null;
    }

    public Employees updateEmployee(int employeeID, Employees emp) {
        Connection conn = null;
        PreparedStatement prst = null;
        try {
            conn = Connect.getInstance().getConnection();
            String sql = "UPDATE employees SET "
                    + "`fullName`= ?,"
                    + "`age`= ?,"
                    + "`gender`= ?,"
                    + "`email`= ?,"
                    + "`phone`= ?,"
                    + "`salary`= ?,"
                    + "`managerID`= ?,"
                    + "`deptID`= ?"
                    + " WHERE isDelete = 1 AND  employeeID = ?";
            prst = conn.prepareStatement(sql);

            prst.setString(1, emp.getFullName());
            prst.setInt(2, emp.getAge());
            prst.setString(3, emp.getGender());
            prst.setString(4, emp.getEmail());
            prst.setString(5, emp.getPhone());
            prst.setDouble(6, emp.getSalary());
            if (emp.getManagerID() == 0) {
                prst.setNull(7, Types.INTEGER);
            } else {
                prst.setInt(7, emp.getManagerID());
            }
            if (emp.getDeptID() == 0) {
                prst.setNull(8, Types.INTEGER);
            } else {
                prst.setInt(8, emp.getDeptID());
            }
            prst.setInt(9, employeeID);

            prst.executeUpdate();
//            System.out.println(prst.toString());
            prst.executeUpdate();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
        return emp;
    }

    //Hiển thị nhân viên theo ID
    public Employees showEmployeeById(int employeeID) {
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = Connect.getInstance().getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT * FROM employees WHERE isDelete = 1 AND employeeID = " + employeeID;
            ResultSet rs = stmt.executeQuery(sql);
            Employees emp = null;
            while (rs.next()) {
                String fullName = rs.getString("fullName");
                Integer age = rs.getInt("age");
                String gender = rs.getString("gender");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                Double salary = rs.getDouble("salary");
                Integer managerID = rs.getInt("managerID");
                Integer deptID = rs.getInt("deptID");
                emp = new Employees(employeeID, fullName, age, gender, email, phone, salary, managerID, deptID);
            }
            return emp;
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
        }
    }

    // tìm nhân viên
    public List<Employees> searchEmployee(String keyword) {
        List<Employees> employees = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = Connect.getInstance().getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT * FROM employees WHERE isDelete = 1 AND employeeID LIKE '%" + keyword + "%' OR fullName LIKE '%" + keyword + "%' OR phone LIKE '%" + keyword + "%' OR email LIKE '%" + keyword + "%'";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int employeeID = rs.getInt("employeeID");
                String fullName = rs.getString("fullName");
                int age = rs.getInt("age");
                String gender = rs.getString("gender");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                Double salary = rs.getDouble("salary");
                int managerID = rs.getInt("managerID");
                int deptID = rs.getInt("deptID");
                Employees emp = new Employees(employeeID, fullName, age, gender, email, phone, salary, managerID, deptID);
                employees.add(emp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }

    //xóa nhân viên khỏi phòng ban
//    public removeEmployeeFromDepartment(int employeeID, int deptID) {
//        Connection conn = null;
//        PreparedStatement pstmt = null;
//        try {
//            conn = Connect.getInstance().getConnection();
//            String sql = "SELECT * FROM Employees WHERE EmployeeID = ? AND DeptID = ?";
//            pstmt = conn.prepareStatement(sql);
//            pstmt.setInt(1, employeeID);
//            pstmt.setInt(2, deptID);
//            ResultSet rs = pstmt.executeQuery();
//            if (!rs.next()) {
//                // Nếu nhân viên không tồn tại trong phòng ban
//                System.out.println("Nhân viên không tồn tại trong phòng ban.");
//            } else {
//                // Nếu nhân viên tồn tại trong phòng ban, xóa nhân viên khỏi phòng ban
//                sql = "DELETE FROM Employees WHERE EmployeeID = ? AND DeptID = ?";
//                pstmt = conn.prepareStatement(sql);
//                pstmt.setInt(1, employeeID);
//                pstmt.setInt(2, deptID);
//                pstmt.executeUpdate();
//                System.out.println("Nhân viên đã được xóa khỏi phòng ban thành công.");
//            }
//        } catch (SQLException ex) {
//            throw new RuntimeException(ex);
//        } finally {
//            if (pstmt != null) {
//                try {
//                    pstmt.close();
//                } catch (SQLException ex) {
//                    throw new RuntimeException(ex);
//                }
//            }
//            if (conn != null) {
//                try {
//                    conn.close();
//                } catch (SQLException ex) {
//                    throw new RuntimeException(ex);
//                }
//            }
//        }
//    }

    public Employees addEmployeeToDepartment(int empID, int deptID ) {
        Connection conn = null;
        PreparedStatement prst = null;
        try {
            conn = Connect.getInstance().getConnection();
            String sql = "UPDATE employees SET deptID = ? WHERE isDelete = 1 AND employeeID = ?";
            prst = conn.prepareStatement(sql);
            prst.setInt(1, deptID);
            prst.setInt(2, empID);
            prst.executeUpdate();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
        return null;
    }


    public Employees removeEmployeeFromDepartment(int employeeID) {
        Connection conn = null;
        PreparedStatement prst = null;
        try {
            conn = Connect.getInstance().getConnection();
            String sql = "UPDATE employees SET deptID = NULL WHERE isDelete = 1 AND employeeID = ?";
            prst = conn.prepareStatement(sql);
            prst.setInt(1, employeeID);
            prst.executeUpdate();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
        return null;
    }




}
