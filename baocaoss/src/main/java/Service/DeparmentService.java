package Service;
import DAO.DepartmentDAO;
import DAO.EmployeeDAO;
import Model.Department;
import Model.Employees;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DeparmentService implements IDeparmentService{
    public DepartmentDAO departmentDAO;
    public static Scanner sc = new Scanner(System.in);

    public DeparmentService() {
        departmentDAO = new DepartmentDAO();
    }
    @Override
    public void getAllDepartments() {
        List<Department> ls = departmentDAO.getAllDepartments();
        for (Department item : ls) {
            System.out.println(item.toString());
        }

    }

    @Override
    public void addDepartment() throws ParseException {
        List<Department> departments = new ArrayList<>();
        System.out.print("deptName: ");
        String deptName = sc.nextLine();
        System.out.print("deptDesc: ");
        String deptDesc = sc.nextLine();
        System.out.print("depManagerID: ");
        Integer depManagerID = Integer.parseInt(sc.nextLine());

        Department dpm = new Department(deptName,deptDesc,depManagerID);
        departments.add(dpm);
        departmentDAO.addDepartment(departments);
    }

    @Override
    public void showDepartmentById() {
        System.out.print("Nhập mã phòng ban: ");
        Integer deptID = Integer.parseInt(sc.nextLine());

        Department dpm = departmentDAO.showDepartmentById(deptID);
        if (dpm == null) {
            System.out.println("Không tồn tại phòng ban có mã " + deptID);
            return;
        }

        System.out.println(dpm.toString());
    }

    @Override
    public void updateDepartment() {
            System.out.print("Nhap ma phòng ban can cap nhat : ");
            Integer deptID = Integer.parseInt(sc.nextLine());

            Department dpm = departmentDAO.showDepartmentById(deptID);

            if (dpm == null) {
                System.out.println("Không tồn tại phòng ban " + deptID);
                return;
            }

            // nếu nhân viên không tồn tại trong database
            System.out.println("Thông tin phòng ban " + deptID + ":");
            System.out.println(dpm.toString());

            boolean cond = true;
            do {
                System.out.println("1. deptName.");
                System.out.println("2. deptDesc.");
                System.out.println("3. depManagerID.");
                System.out.println("4. Exit.");
                int choice;
                System.out.print("Nhap lua chon: ");
                choice = Integer.parseInt(sc.nextLine());
                switch (choice) {
                    case 1:
                        System.out.print("deptName: ");
                        String  deptName= sc.nextLine();
                        dpm.setDeptName(deptName);
                        break;
                    case 2:
                        System.out.print("deptDesc: ");
                        String deptDesc= sc.nextLine();
                        dpm.setDeptDesc(deptDesc);
                        break;
                    case 3:
                        System.out.print("depManagerID: ");
                        Integer depManagerID = Integer.parseInt(sc.nextLine());
                        dpm.setDepManagerID(depManagerID);
                        break;
                    case 4:
                        System.out.println("Exit.");
                        cond = false;
                        break;
                    default:
                        System.out.println("Mời nhập lựa chọn");
                        break;
                }

            } while (cond == true);

            Department rs = departmentDAO.updateDepartment(deptID,dpm);
            System.out.println(rs.toString());
    }

    @Override
    public void deleteDepartment() {
        getAllDepartments();
        System.out.print("Nhập mã phòng ban cần xóa : ");
        Integer deptID = Integer.parseInt(sc.nextLine());
        Department dmp = departmentDAO.deleteDepartment(deptID);
    }

}
