package Service;
import DAO.EmployeeDAO;
import Model.Employees;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EmployeeService implements IEmployeeService {
    public EmployeeDAO employeeDAO;
    public static Scanner sc = new Scanner(System.in);
    public EmployeeService() {
        employeeDAO = new EmployeeDAO();
    }

    public void addEmployee() throws ParseException {
        List<Employees> employees = new ArrayList<>();
        System.out.print("FullName: ");
        String fullName = sc.nextLine();
        System.out.print("Age: ");
        Integer age = Integer.parseInt(sc.nextLine());
        System.out.print("Gender: ");
        String gender = sc.nextLine();
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("Phone: ");
        String phone = sc.nextLine();
        System.out.print("Salary: ");
        Double salary = Double.parseDouble(sc.nextLine());
        System.out.print("Manager ID: ");
        int managerID = Integer.parseInt(sc.nextLine());
        System.out.print("Department ID: ");
        int deptID = Integer.parseInt(sc.nextLine());


        Employees e = new Employees(fullName, age, gender, email, phone,salary,managerID,deptID);
        employees.add(e);
        employeeDAO.addEmployee(employees);
    }

    @Override
    public void getAllEmployees() {
        List<Employees> ls = employeeDAO.getAllEmployees();
        for (Employees item : ls) {
            System.out.println(item.toString());
        }
    }

    @Override
    public void deleteEmployee() {
        getAllEmployees();
        System.out.print("Nhập mã nhân viên cần xóa : ");
        Integer employeeID = Integer.parseInt(sc.nextLine());
        Employees emp = employeeDAO.deleteEmployee(employeeID);
    }

    @Override
    public void updateEmployee() {
        System.out.print("Nhap ma nhan vien can cap nhat : ");
        Integer employeeID = Integer.parseInt(sc.nextLine());

        Employees emp = employeeDAO.showEmployeeById(employeeID);
        if (emp == null) {
            System.out.println("Khong ton tai nhan vien co ma " + employeeID);
            return;
        }

        // nếu nhân viên không tồn tại trong database
        System.out.println("Thong tin nhan vien co ma " + employeeID + ":");
        System.out.println(emp.toString());

        boolean cond = true;
        do {
            System.out.println("1. Name.");
            System.out.println("2. Age.");
            System.out.println("3. Gender.");
            System.out.println("4. Email.");
            System.out.println("5. Phone.");
            System.out.println("6. Salary.");
            System.out.println("7. ManagerID.");
            System.out.println("8. DeptID.");
            System.out.println("9. Exit.");
            int choice;
            System.out.print("Nhap lua chon: ");
            choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    System.out.print("Name: ");
                    String fullName = sc.nextLine();
                    emp.setFullName(fullName);
                    break;
                case 2:
                    System.out.print("Age: ");
                    int age = Integer.parseInt(sc.nextLine());
                    emp.setAge(age);
                    ;
                    break;
                case 3:
                    System.out.print("Gender: ");
                    String gender = sc.nextLine();
                    emp.setGender(gender);
                    break;
                case 4:
                    System.out.print("Email: ");
                    String email = sc.nextLine();
                    emp.setEmail(email);
                    break;
                case 5:
                    System.out.print("Phone: ");
                    String phone = sc.nextLine();
                    emp.setEmail(phone);
                    break;
                case 6:
                    System.out.print("Salary: ");
                    Double salary = sc.nextDouble();
                    emp.setSalary(salary);
                    break;
               case 7:
                    System.out.print("Manager: ");
                   int managerID = Integer.parseInt(sc.nextLine());
                   emp.setManagerID(managerID);
                    break;
               case 8:
                    System.out.print("DeptID: ");
                    int deptID = Integer.parseInt(sc.nextLine());
                    emp.setDeptID(deptID);
                    break;

               case 9:
                    System.out.println("Exit.");
                    cond = false;
                    break;
               default:
                    System.out.println("Moi nhap lua chon");
                    break;
            }

        } while (cond == true);

        Employees rs = employeeDAO.updateEmployee(employeeID,emp);
        System.out.println(rs.toString());

    }
    //Hiển thị nhân viên theo ID
    @Override
    public void showEmployeebyID() {
        System.out.print("Nhập mã nhân viên: ");
        Integer employeeID = Integer.parseInt(sc.nextLine());
        Employees emp = employeeDAO.showEmployeeById(employeeID);
        if (emp == null) {
            System.out.println("Không tồn tại mã nhân viên: " + employeeID);
            return;
        }

        System.out.println(emp.toString());
    }

    // Tìm nhân viên theo tên , sđt hoặc email


    @Override
    public void searchEmployee() {
            System.out.print("Nhap tu khoa can tim kiem: ");
            String keyword = sc.nextLine();

            List<Employees> employees = employeeDAO.searchEmployee(keyword);

            if (employees.isEmpty()) {
                System.out.println("Khong tim thay ket qua nao cho tu khoa '" + keyword + "'");
            } else {
                System.out.println("Ket qua tim kiem cho tu khoa '" + keyword + "'");
                for (Employees emp : employees) {
                    System.out.println(emp.toString());
                }
            }
        }

    @Override
    public void addEmployeeToDepartment() {
        System.out.print("Nhập mã nhân viên: ");
        Integer employeeID = Integer.parseInt(sc.nextLine());


        Employees emp1 = employeeDAO.showEmployeeById(employeeID);
        if (emp1 == null) {
            System.out.println("Không tồn tại mã nhân viên: " + employeeID);
            return;
        }
        System.out.println(emp1.toString());

        System.out.print("Nhập mã phòng ban: ");
        Integer deptID = Integer.parseInt(sc.nextLine());

        Employees emp2 = employeeDAO.addEmployeeToDepartment(employeeID,deptID);

        System.out.println("=====Thêm nhân viên vào phòng ban số " +deptID+ " thành công=====");
        Employees emp3 = employeeDAO.showEmployeeById(employeeID);
        System.out.println(emp3.toString());
    }

    @Override
    public void removeEmployeeFromDepartment() {
            System.out.print("Nhập mã nhân viên: ");
            Integer employeeID = Integer.parseInt(sc.nextLine());
            Employees emp = employeeDAO.showEmployeeById(employeeID);
            if (emp == null) {
                System.out.println("Không tồn tại mã nhân viên: " + employeeID);
                return;
            }
            System.out.println(emp.toString());

            employeeDAO.removeEmployeeFromDepartment(employeeID);

            System.out.println("=====Xoá nhân viên khỏi phòng ban số "+" thành công=====");
            Employees emp2 = employeeDAO.showEmployeeById(employeeID);
            System.out.println(emp2.toString());
    }
}

