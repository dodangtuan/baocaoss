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
            if (ls.isEmpty()) {
                System.out.println("Khong ton tai nhan vien nao.");
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

    public Employees deleteEmployee(int employeeID) {
        Connection conn = null;
        Statement prst = null;
        try {

            // cập nhật danh sách nhân viên mới nhất từ CSDL
            conn = Connect.getInstance().getConnection();
            prst = conn.createStatement();

            // Cập nhật managerID quản lý của các nhân viên cần xóa thành NULL
            String sqlUpdateEmployees = "UPDATE employees SET managerID = NULL WHERE managerID = " + employeeID;
            prst = conn.createStatement();
            prst.executeUpdate(sqlUpdateEmployees);

            //Xóa đổi trạng thái nhân viên thành false
//          String sql = "DELETE FROM employees WHERE employeeID =" + employeeID;
            String sql = "UPDATE employees SET isDelete = 0 WHERE isDelete = 1 AND employeeID =" + employeeID;
            int rowsAffected = prst.executeUpdate(sql);
            if (rowsAffected == 0) {
                System.out.println("Khong ton tai nhan vien nao co id: " + employeeID);
            } else {
                System.out.println("Xoa thanh cong nhan vien co id: " + employeeID);
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

    //update thông tin
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


    public Employees addEmployeeToDepartment(int empID, int deptID) {
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

    //xóa nhân viên khỏi phòng ban
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

//    Thuế thu nhập cá nhân = Lương cơ bản * tỷ lệ thuế
//
//    Trong đó, tỷ lệ thuế được xác định hiện hành được áp dụng như sau:
//    Từ 0 đồng đến 5 triệu đồng: 5%
//    Từ 5 triệu đồng đến 10 triệu đồng: 10%
//    Từ 10 triệu đồng đến 18 triệu đồng: 15%
//    Từ 18 triệu đồng đến 32 triệu đồng: 20%
//    Từ 32 triệu đồng đến 52 triệu đồng: 25%
//    Từ 52 triệu đồng đến 80 triệu đồng: 30%
//    Trên 80 triệu đồng: 35%


    public double calculatePersonalIncomeTax(int employeeID) {
        double taxRate = 0.0;
//        double deduction = 11000000.0; // Giảm trừ cá nhân hàng tháng

        Employees emp = showEmployeeById(employeeID);
        if (emp == null) {
            System.out.println("Khong ton tai nhan vien co ma: " + employeeID);
            return 0.0;
        }

        double salary = emp.getSalary();

        if (salary <= 5000000) {
            taxRate = 0.05;
        } else if (salary <= 10000000) {
            taxRate = 0.1;
        } else if (salary <= 18000000) {
            taxRate = 0.15;
        } else if (salary <= 32000000) {
            taxRate = 0.2;
        } else if (salary <= 52000000) {
            taxRate = 0.25;
        } else if (salary <= 80000000) {
            taxRate = 0.3;
        } else {
            taxRate = 0.35;
        }

//        double personalIncomeTax = (salary * taxRate) - deduction;
        double personalIncomeTax = (salary * taxRate);
//        System.out.printf("Thuế thu nhập cá nhân của nhân viên có mã " + employeeID + " là: " + personalIncomeTax + " VNĐ.");
        System.out.printf("Thue thu nhap ca nhan cua nhan vien ma %s là: %.3f VNĐ.\n", employeeID, personalIncomeTax);
        return personalIncomeTax;
    }


    //lâấy ds nhân viên lương cao

    public List<Employees> sortEmployeesBySalaryDescending() {
        List<Employees> ls = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT * FROM Employees WHERE isDelete=1 ORDER BY salary DESC;";
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
            if (ls.isEmpty()) {
                System.out.println("Khong ton tai nhan vien nao.");
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

    //

    public List<Employees> getEmployeesByDepartmentId(int deptID) {
        List<Employees> employees = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = Connect.getInstance().getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT * FROM Employees WHERE isDelete = 1 AND deptID =" + deptID;
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
                Employees emp = new Employees(employeeID, fullName, age, gender, email, phone, salary, managerID, deptID);
                employees.add(emp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }



}
