package DAO;
import Database.Connect;
import Model.Department;
import Model.Employees;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static Database.Connect.getConnection;

public class DepartmentDAO {
    //Thêm phòng ban
    public void addDepartment(List<Department> departments) {
        Connection conn = null;
        PreparedStatement prst = null;
        try {
            conn = Connect.getInstance().getConnection();
            String sql = "INSERT INTO departments(deptName,deptDesc,depManagerID ,isDelete) VALUES (?,?,?,true)";
            prst = conn.prepareStatement(sql);
            for(Department dpm : departments) {
                prst.setString(1,dpm.getDeptName());
                prst.setString(2,dpm.getDeptDesc());
                prst.setInt(3,dpm.getDepManagerID());

                if (dpm.getDepManagerID() == 0){
                    prst.setNull(3, Types.INTEGER);
                } else {
                    prst.setInt(3,dpm.getDepManagerID());
                }
                prst.executeUpdate();
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        } finally {
            if(prst != null) {
                try {
                    prst.close();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
            if(conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
    }


    //lấy danh sách phòng ban
    public List<Department> getAllDepartments() {
        List<Department> ls = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT * FROM Departments WHERE isDelete = 1";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()){
                Department department = new Department(
                        rs.getInt("deptID"),
                        rs.getString("deptName"),
                        rs.getString("deptDesc"),
                        rs.getInt("depManagerID")
                );
                ls.add(department);  // lưu theo hàng vào list ls
            }
            if (ls.isEmpty()) {
                System.out.println("Khong ton tai phong ban.");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if(conn != null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return ls;
    }


    // lấy thông tin phòng ban theo mã id
    public Department showDepartmentById(int deptID){
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = Connect.getInstance().getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT * FROM Departments  WHERE isDelete = 1 AND deptID = " + deptID;
            ResultSet rs = stmt.executeQuery(sql);
            Department dpm = null;
            while(rs.next()) {
                String deptName = rs.getString("deptName");
                String deptDesc = rs.getString("deptDesc");
                Integer depManagerID = rs.getInt("depManagerID");

                dpm = new Department(deptID,deptName,deptDesc,depManagerID);
            }
            return dpm;
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
        }

    }



    public Department deleteDepartment(int deptID) {
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = Connect.getInstance().getConnection();
            conn.setAutoCommit(false); // bắt đầu một transaction

            // Cập nhật deptID của các nhân viên trong phòng ban cần xóa thành NULL
            String sqlUpdateEmployees = "UPDATE employees SET deptID = NULL WHERE deptID = " + deptID;
            stmt = conn.createStatement();
            stmt.executeUpdate(sqlUpdateEmployees);

            // Xóa phòng ban đó
//            String sqlDeleteDepartment = "DELETE FROM Departments WHERE deptID = " + deptID;
            String sqlDeleteDepartment = "UPDATE Departments SET isDelete = 0 WHERE isDelete = 1 AND deptID = " + deptID;
            stmt.executeUpdate(sqlDeleteDepartment);

            conn.commit(); // commit transaction
            System.out.println("Xoa phong ban có id " + deptID + " thành công");
        } catch (SQLException e) {
            try {
                if (conn != null) {
                    conn.rollback(); // nếu có lỗi thì rollback transaction
                }
            } catch (SQLException ex) {
                throw new RuntimeException("Không thể rollback transaction: " + ex.getMessage());
            }
            throw new RuntimeException("Xoa phong ban that bai: " + e.getMessage());
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    throw new RuntimeException("Khong the dong statement: " + e.getMessage());
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    throw new RuntimeException("Khong the dong ket noi: " + e.getMessage());
                }
            }
        }
        return null;
    }


    //update vào phòng ban

    public Department updateDepartment(int deptID, Department dpm) {
        Connection conn = null;
        PreparedStatement prst = null;
        try {
            conn = Connect.getInstance().getConnection();
            String sql = "UPDATE Departments SET "
                    + "`deptName`= ?,"
                    + "`deptDesc`= ?,"
                    + "`depManagerID`= ?"
                    + " WHERE deptID= ?";
            prst = conn.prepareStatement(sql);

            prst.setString(1, dpm.getDeptName());
            prst.setString(2, dpm.getDeptDesc());
            prst.setInt(3, dpm.getDepManagerID());

            if (dpm.getDepManagerID() == 0){
                prst.setNull(3, Types.INTEGER);
            } else {
                prst.setInt(3, dpm.getDepManagerID());
            }

            prst.setInt(4, deptID);

            prst.executeUpdate();
//            System.out.println(prst.toString());
            prst.executeUpdate();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        } finally {
            if(conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
        return dpm;
    }

    //lấy thông tin quản lý
    public Department DepartmentManagerInfo (int deptID) {
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = Connect.getInstance().getConnection();
            stmt = conn.createStatement();
            String sql =  "SELECT e.fullName as managerName, e.email as managerEmail, e.phone as managerPhone " +
                    "FROM Departments d " +
                    "JOIN Employees e ON d.depManagerID = e.employeeID " +
                    "WHERE d.deptID =" + deptID;
            ResultSet rs = stmt.executeQuery(sql);
            Department dpm = null;

//            while(rs.next()) {
//                String deptName = rs.getString("deptName");
//                String deptDesc = rs.getString("deptDesc");
//                Integer depManagerID = rs.getInt("depManagerID");
//
//                dpm = new Department(deptID,deptName,deptDesc,depManagerID);
//            }
//            return dpm;
            if(rs.next()) {
                System.out.println("Quan ly phong " + deptID +":");
                System.out.println("FullName : " + rs.getString("managerName"));
                System.out.println("Email : " + rs.getString("managerEmail"));
                System.out.println("Phone : " + rs.getString("managerPhone"));
                System.out.println("                                                ");

            } else {
                System.out.println("Không tìm thấy bộ phận hoặc không có người quản lý.");
            }
            return dpm;
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
        }


    }


}
