import Service.AccountService;
import Service.DeparmentService;
import Service.EmployeeService;

import java.text.ParseException;
import java.util.Scanner;

import static Validate.Validate.getValidInput;

public class App {


    public static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) throws ParseException {
        boolean loggedIn = false; // biến đánh dấu trạng thái đăng nhập
        AccountService accSV = new AccountService();
        EmployeeService empSV = new EmployeeService();
        DeparmentService dpmSV = new DeparmentService();

        System.out.println("Xin moi dang nhap !");

        accSV.Login();

        System.out.println("                                    ");
        System.out.println("***✿ ❀ ❁  QUAN LY NHAN SU ✿ ❀ ❁ ***:");

        boolean cond = true;
        do {
            System.out.println("                       ");
            System.out.println("1. Nhan vien");
            System.out.println("2. Phong ban");

            System.out.println("3. Thoat chuong trinh.");
            System.out.println("===========================");
            System.out.print("Moi nhap lua chon : ");

            int chon = getValidInput(sc);

            switch (chon) {
                case 1:
                    System.out.println("Nhan vien");
                    boolean cond1 = true;
                    do {
                        System.out.println("                       ");
                        System.out.println("1. Danh sach nhan vien");
                        System.out.println("2. Them nhan vien ");
                        System.out.println("3. Cap nhat nhan vien ");
                        System.out.println("4. Xoa nhan vien ");
                        System.out.println("5. Thong tin nhan vien theo ID ");
                        System.out.println("6. Tim nhan vien theo ten/sdt/email ");
                        System.out.println("7. Them nhan vien/chuyen vao phong ban ");
                        System.out.println("8. Xoa nhan vien khoi phong ban ");
                        System.out.println("9. Tinh thue cua nhan vien ");

                        System.out.println("10. DS nhan vien co luong cao->thap");
                        System.out.println("11. DS nhan vien theo phong ban");

                        System.out.println("12. Thoat chuong trinh.");
                        System.out.println("===========================");
                        System.out.print("Moi nhap lua chon : ");

                        int chon1 = getValidInput(sc);

                        switch (chon1) {
                            case 1:
                                System.out.println("Danh sach nhan vien");
                                empSV.getAllEmployees();
                                break;
                            case 2:
                                System.out.println("Them nhan vien");
                                empSV.addEmployee();
                                break;
                            case 3:
                                System.out.println("Cap nhat nhan vien");
                                empSV.updateEmployee();
                                break;
                            case 4:
                                System.out.println("Xoa nhan vien");
                                empSV.deleteEmployee();
                                break;
                            case 5:
                                System.out.println("Thong tin nhan vien theo ID");
                                empSV.showEmployeebyID();
                                break;
                            case 6:
                                System.out.println("Tim nhan vien theo ten/sdt/email");
                                empSV.searchEmployee();
                                break;
                            case 7:
                                System.out.println("Them nhan vien vao phong ban");
                                empSV.addEmployeeToDepartment();
                                break;
                            case 8:
                                System.out.println("Xoa nhan vien khoi phong ban");
                                empSV.removeEmployeeFromDepartment();
                                break;
                            case 9:
                                System.out.println("Tinh thue cua nhan vien");
                                empSV.calculatePersonalIncomeTax();
                                break;
                            case 10:
                                System.out.println("DS nhan vien luong cao->thap");
                                empSV.sortEmployeesBySalaryDescending();
                                break;
                            case 11:
                                System.out.println("DS nhan vien theo phong ban");
                                empSV.getEmployeesByDepartmentId();
                                break;
                            case 12:
                                System.out.println("Thoat");
                                cond1 = false;
                                break;
                        }
                    } while (cond1);
                    break;
                case 2:
                    System.out.println("Phong ban");
                    boolean cond2 = true;
                    do {
                        System.out.println("                       ");
                        System.out.println("1. Danh sach phong ban ");
                        System.out.println("2. Them phong ban  ");
                        System.out.println("3. Cap nhat phong ban ");
                        System.out.println("4. Xoa phong ban ");
                        System.out.println("5. DS phong ban theo ID ");
                        System.out.println("6. DS nhan vien theo phong ban");

                        System.out.println("7. Thong tin quan ly phong ban.");
                        System.out.println("8. Thoat chuong trinh.");
                        System.out.println("===========================");
                        System.out.print("Moi nhap lua chon : ");

                        int chon2 = getValidInput(sc);

                        switch (chon2) {
                            case 1:
                                System.out.println("Danh sach phong ban");
                                dpmSV.getAllDepartments();
                                break;
                            case 2:
                                System.out.println("Them phong ban ");
                                dpmSV.addDepartment();
                                break;
                            case 3:
                                System.out.println("Cap nhat phong ban");
                                dpmSV.updateDepartment();
                                break;
                            case 4:
                                System.out.println("Xoa phong ban");
                                dpmSV.deleteDepartment();
                                break;
                            case 5:
                                System.out.println("Phong ban theo ID");
                                dpmSV.showDepartmentById();
                                break;
                            case 6:
                                System.out.println("DS nhan vien theo phong ban");
                                empSV.getEmployeesByDepartmentId();
                                break;
                            case 7:
                                System.out.println("Thong tin quan ly theo phong ban");
                                dpmSV.DepartmentManagerInfo();
                                break;
                            case 8:
                                System.out.println("Thoat");
                                cond2 = false;
                                break;
                        }
                    } while (cond2);
                    break;
                case 3:
                    System.out.println("Dung chuong trinh");
                    System.exit(0);
                    cond = false;
                    break;
            }
        } while (cond);

    }
}
