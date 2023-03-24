import Service.AccountService;
import Service.DeparmentService;
import Service.EmployeeService;

import java.text.ParseException;
import java.util.Scanner;

public class App {


    public static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) throws ParseException {
        boolean loggedIn = false; // biến đánh dấu trạng thái đăng nhập
        AccountService accSV = new AccountService();
        EmployeeService empSV = new EmployeeService();
        DeparmentService dpmSV = new DeparmentService();

        System.out.println("                                    ");
        System.out.println("***✿ ❀ ❁  QUAN LY NHAN SU ✿ ❀ ❁ ***:");

        boolean cond = true;
        do {

            System.out.println("Xin moi dang nhap !");

//            accSV.Login();

            System.out.println("1. Danh sách nhân viên");
            System.out.println("2. Thêm nhân viên ");
            System.out.println("3. Cập nhật nhân viên ");
            System.out.println("4. Xóa nhân viên ");
            System.out.println("5. Thông tin nhân viên theo ID ");
            System.out.println("6. Tìm nhân viên theo tên/sdt/email ");
            System.out.println("7. Thêm nhân viên/chuyển vào phòng ban ");
            System.out.println("8. Xóa nhân viên khỏi phòng ban ");
            System.out.println("9. Thêm phòng ban ");
            System.out.println("10. Danh sách phòng ban ");
            System.out.println("11. Xóa phòng ban ");
            System.out.println("12. Sửa phòng ban ");
            System.out.println("13. DS phòng ban theo ID ");
            System.out.println("14. Tinh thue cua nhan vien ");

            System.out.println("15. Thoat chuong trinh.");
            System.out.println("===========================");
            System.out.print("Mời nhập lựa chọn : ");

            int chon = Integer.parseInt(sc.nextLine());

            switch (chon) {
                case 1:
                    System.out.println("Danh sách nhân viên");
                    empSV.getAllEmployees();
                    break;
                case 2:
                    System.out.println("Thêm nhân viên");
                    empSV.addEmployee();
                    break;
                case 3:
                    System.out.println("Cập nhật nhân viên");
                    empSV.updateEmployee();
                    break;
                case 4:
                    System.out.println("Xóa nhân viên");
                    empSV.deleteEmployee();
                    break;
                case 5:
                    System.out.println("Thông tin nhân viên theo ID");
                    empSV.showEmployeebyID();
                    break;
                case 6:
                    System.out.println("Tìm nhân viên theo tên/sdt/email");
                    empSV.searchEmployee();
                    break;
                case 7:
                    System.out.println("Thêm nhân viên vào phòng ban");
                    empSV.addEmployeeToDepartment();
                    break;
                case 8:
                    System.out.println("Xóa nhân viên khỏi phòng ban");
                    empSV.removeEmployeeFromDepartment();
                    break;
                case 9:
                    System.out.println("Thêm phòng ban");
                    dpmSV.addDepartment();
                    break;
                case 10:
                    System.out.println("Danh sách phòng ban");
                    dpmSV.getAllDepartments();
                    break;
                case 11:
                    System.out.println("Xóa phòng ban");
                    dpmSV.deleteDepartment();
                    break;
                case 12:
                    System.out.println("Sửa phòng ban");
                    dpmSV.updateDepartment();
                    break;
                case 13:
                    System.out.println("ds phòng ban theo id");
                    dpmSV.showDepartmentById();
                    break;
                case 14:
                    System.out.println("Tính thuế của nhân viên");
                    empSV.calculatePersonalIncomeTax();
                    break;
                case 15:
                    System.out.println("Dung chuong trinh");
                    System.exit(0);
                    cond = false;
                    break;
            }
        } while (cond);
    }
}
