package Service;

import DAO.AccountDAO;
import Model.Account;

import java.util.Scanner;

public class AccountService {
    public AccountDAO accountDAO;
    public static Scanner sc = new Scanner(System.in);

    public AccountService() { accountDAO = new AccountDAO();}


    public void Login() {
        System.out.print("Nhap username: ");
        String username = sc.nextLine();

        System.out.print("Nhap password: ");
        String password = sc.nextLine();

        Account acc = accountDAO.login(username,password);
//
//        if (acc == null) {
//            System.out.println("Tai khoan khong ton tai " + username);
//            return;
//        }
    }
}
