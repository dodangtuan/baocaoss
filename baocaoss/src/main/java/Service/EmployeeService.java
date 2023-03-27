package Service;
import DAO.DepartmentDAO;
import DAO.EmployeeDAO;
import Model.Department;
import Model.Employees;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static Validate.Validate.*;

public class EmployeeService implements IEmployeeService {
    public EmployeeDAO employeeDAO;
    public DepartmentDAO departmentDAO;
    public static Scanner sc = new Scanner(System.in);
    public EmployeeService() {
        employeeDAO = new EmployeeDAO();
        departmentDAO = new DepartmentDAO();
    };

    public void addEmployee() throws ParseException {
        List<Employees> employees = new ArrayList<>();
        System.out.print("FullName: ");
        String fullName = sc.nextLine();
        System.out.print("Age: ");
        Integer age = getValidAge(sc);
        System.out.print("Gender: ");
        String gender = getValidGender(sc);
        System.out.print("Email: ");
        String email = getValidEmail(sc);
        System.out.print("Phone: ");
        String phone = getValidPhoneNumber(sc);
        System.out.print("Salary: ");
        Double salary = getValidSalary(sc);
        System.out.print("Manager ID(0 == null): ");
        int managerID = getValidManagerID(sc);
        System.out.print("Department ID(0 == null): ");
        int deptID = getValidDeptID(sc);


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
        Integer employeeID = null;
        do {
            System.out.print("Nhap ma nhan vien (nhap 0 để thoat): ");
            employeeID = getValidEmployeeID(sc);
            if (employeeID == 0) {
                System.out.println("Da thoat khoi chuong trinh.");
                return;
            }
        } while (employeeID == null);
        Employees emp = employeeDAO.deleteEmployee(employeeID);
    }

    @Override
    public void updateEmployee() {
        System.out.print("Nhap ma nhan vien can cap nhat : ");
        Integer employeeID = getValidEmployeeID(sc);

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
//                    int age = Integer.parseInt(sc.nextLine());
//                    emp.setAge(age);
                    int age = getValidAge(sc);
                    emp.setAge(age);
                    ;
                    break;
                case 3:
                    System.out.print("Gender: ");
                    String gender = getValidGender(sc);
                    emp.setGender(gender);
                    break;
                case 4:
                    System.out.print("Email: ");
//                    String email = sc.nextLine();
                    String email = getValidEmail(sc);
                    emp.setEmail(email);
                    break;
                case 5:
                    System.out.print("Phone: ");
//                    String phone = sc.nextLine();
                    String phone = getValidPhoneNumber(sc);
                    emp.setEmail(phone);
                    break;
                case 6:
                    System.out.print("Salary: ");
                    Double salary = getValidSalary(sc);
                    emp.setSalary(salary);
                    break;
               case 7:
                    System.out.print("ManagerID( 0==null ): ");
                   int managerID = getValidManagerID(sc);
                   emp.setManagerID(managerID);
                    break;
               case 8:
                    System.out.print("DeptID( 0==null ): ");
                    int deptID = getValidDeptID(sc);
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
        System.out.print("Nhap ma nhan vien: ");
        Integer employeeID = getValidEmployeeID(sc);
        Employees emp = employeeDAO.showEmployeeById(employeeID);
        if (emp == null) {
            System.out.println("Khong ton tai nhan vien ma: " + employeeID);
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
        getAllEmployees();
        System.out.print("Nhap ma nhan vien: ");
        Integer employeeID = getValidEmployeeID(sc);


        Employees emp1 = employeeDAO.showEmployeeById(employeeID);
        if (emp1 == null) {
            System.out.println("Khong ton tai nhan vien ma: " + employeeID);
            return;
        }
        System.out.println(emp1.toString());

        System.out.print("Nhap ma phong ban: ");
        Integer deptID = getValidDeptID(sc);

        Department dept = departmentDAO.showDepartmentById(deptID);
        if (dept == null) {
            System.out.println("Khong ton tai phong ban co ma: " + deptID);
            return;
        }
        Employees emp2 = employeeDAO.addEmployeeToDepartment(employeeID,deptID);

        System.out.println("=====Them nhan vien vao phong ban so " +deptID+ " thanh cong=====");
        Employees emp3 = employeeDAO.showEmployeeById(employeeID);
        System.out.println(emp3.toString());
    }

    @Override
    public void removeEmployeeFromDepartment() {
            getAllEmployees();
            System.out.print("Nhap ma nhan vien: ");
            Integer employeeID = getValidEmployeeID(sc);
            Employees emp = employeeDAO.showEmployeeById(employeeID);
            if (emp == null) {
                System.out.println("Khong ton tai nhan vien ma: " + employeeID);
                return;
            }
            System.out.println(emp.toString());

            employeeDAO.removeEmployeeFromDepartment(employeeID);

            System.out.println("=====Xoa nhan vien khoi phong ban  "+" thanh cong=====");
            Employees emp2 = employeeDAO.showEmployeeById(employeeID);
            System.out.println(emp2.toString());
    }

    //Tính thuế thu nhập

    @Override
    public void calculatePersonalIncomeTax() {
        System.out.print("Nhap ma nhan vien can tinh thue: ");
        Integer employeeID = getValidEmployeeID(sc);
        Employees emp = employeeDAO.showEmployeeById(employeeID);
        if (emp == null) {
            System.out.println("Khong ton tai ma nhan vien: " + employeeID);
            return;
        }
        System.out.println(emp.toString());
        double emp1 = employeeDAO.calculatePersonalIncomeTax(employeeID);

    }

    @Override
    public void sortEmployeesBySalaryDescending() {
        List<Employees> ls = employeeDAO.sortEmployeesBySalaryDescending();
        for (Employees item : ls) {
            System.out.println(item.toString());
        }
    }

    @Override
    public void getEmployeesByDepartmentId() {
        System.out.print("Nhap ma phong ban: ");
        Integer deptID = getValidDeptID(sc);
        List<Employees> employees = employeeDAO.getEmployeesByDepartmentId(deptID);
        Department dept = departmentDAO.showDepartmentById(deptID);

        if (employees == null) {
            System.out.println("Khong ton tai nhan vien nao trong phong ban");
            return;
        }
        if(dept == null){
            System.out.println("Khong ton tai phong ban");
            return;
        }

        for (Employees emp : employees) {
            System.out.println(emp.toString());
        }
    }
}

