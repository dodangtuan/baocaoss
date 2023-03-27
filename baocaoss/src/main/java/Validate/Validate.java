package Validate;

import java.util.Scanner;

public class Validate {
    public static Scanner sc = new Scanner(System.in);
    public static int getValidAge(Scanner sc) {
        int age = 0;
        boolean isValid = false;
        do {
//            System.out.print("Nhap tuoi (18-100): ");
            String input = sc.nextLine().trim();
            if (input.length() <= 10) {
                try {
                    age = Integer.parseInt(input);
                    if (age >= 18 && age <= 100) {
                        isValid = true;
                    } else {
                        System.out.println("Tuoi phai tu 18-100");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Khong dung dinh dang, tuoi phai dang so");
                }
            }
        } while (!isValid);
        return age;
    }

    //bat sdt

    public static String getValidPhoneNumber(Scanner sc) {
        String phoneNumber = "";
        boolean isValid = false;
        do {
//            System.out.print("Nhap sdt (10 ky tu): ");
            String input = sc.nextLine().trim();
            if (input.length() == 10) {
                try {
                    Long.parseLong(input);
                    phoneNumber = input;
                    isValid = true;
                } catch (NumberFormatException e) {
                    System.out.println("SDT phai dang so");
                }
            } else {
                System.out.println("SDT phai du 10 ky tu so");
            }
        } while (!isValid);
        return phoneNumber;
    }

    //bat email
    public static String getValidEmail(Scanner sc) {
        String email = "";
        boolean isValid = false;
        do {
//            System.out.print("Enter email: ");
            String input = sc.nextLine().trim();
            if (input.contains("@") && input.contains(".")) {
                int atIndex = input.indexOf("@");
                int dotIndex = input.lastIndexOf(".");
                if (dotIndex > atIndex) {
                    email = input;
                    isValid = true;
                }
            }
            if (!isValid) {
                System.out.println("Email phai dinh dang: 'name@domain.com'.");
            }
        } while (!isValid);
        return email;
    }


    //validate lương

    public static double getValidSalary(Scanner sc) {
        double salary = 0;
        boolean isValid = false;
        do {
//            System.out.print("Enter salary: ");
            String input = sc.nextLine().trim();
            try {
                salary = Double.parseDouble(input);
                if (salary > 0) {
                    isValid = true;
                } else {
                    System.out.println("Luong phai la so duong.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Luong phai dang so.");
            }
        } while (!isValid);
        return salary;
    }

    // validate gioi tinh
    public static String getValidGender(Scanner sc) {
        String gender = "";
        boolean isValid = false;
        do {
            String input = sc.nextLine().trim();
            if (input.matches("[a-zA-Z]+")) {
                gender = input;
                isValid = true;
            } else {
                System.out.println("Invalid input. Gender must only contain letters.");
            }
        } while (!isValid);
        return gender;
    }

    // validate managerID
    public static int getValidManagerID(Scanner sc) {
        int managerID = 0;
        boolean isValid = false;
        do {
//            System.out.print("Enter manager ID: ");
            String input = sc.nextLine().trim();
            if (input.matches("[0-9]+")) {
                managerID = Integer.parseInt(input);
                isValid = true;
            } else {
                System.out.println("Sai dinh dang, chi nhap so duong.");
            }
        } while (!isValid);
        return managerID;
    }

    //validate deptID
    public static int getValidDeptID(Scanner sc) {
        int deptID = 0;
        boolean isValid = false;
        do {
//            System.out.print("Enter department ID: ");
            String input = sc.nextLine().trim();
            if (input.matches("[0-9]+")) {
                deptID = Integer.parseInt(input);
                isValid = true;
            } else {
                System.out.println("Sai dinh dang, deptID phai dang so duong.");
            }
        } while (!isValid);
        return deptID;
    }


    //validate nhap ma nhan vien
    public static int getValidEmployeeID(Scanner sc) {
        int employeeID = 0;
        boolean isValid = false;
        do {
//            System.out.print("Nhap ma nhan vien: ");
            String input = sc.nextLine().trim();
            if (input.matches("[0-9][0-9]*")) {
                employeeID = Integer.parseInt(input);
                isValid = true;
            } else {
                System.out.println("Ma nhan vien phai dang so duong.");
            }
        } while (!isValid);
        return employeeID;
    }


    //input >0
    public static int getValidInput(Scanner scanner) {
        int input;
        do {
            while (!scanner.hasNextInt()) {
                System.out.println("Lỗi: Vui lòng nhập số nguyên.");
                scanner.next();
            }
            input = scanner.nextInt();
            if (input <= 0) {
                System.out.println("Lỗi: Vui lòng nhập số nguyên lớn hơn 0.");
            }
        } while (input <= 0);
        return input;
    }






}
