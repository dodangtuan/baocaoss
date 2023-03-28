import DAO.AccountDAO;
import Model.Account;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class LogintestTest {
    @Test
    public void testLoginWithCorrectInformation() {
        String name = "username";
        String password = "password";
        AccountDAO accountDAO = new AccountDAO();
        Account result = accountDAO.login(name, password);
        assertNotNull(result);
        assertTrue(result.getUsername().equals(name));
        assertTrue(result.getPassword().equals(password));
        assertTrue(accountDAO.loggedIn);
    }

    @Test
    public void testLoginWithIncorrectInformation() {
        String name = "wrongusername";
        String password = "wrongpassword";
        AccountDAO accountDAO = new AccountDAO();
        Account result = accountDAO.login(name, password);
        assertNull(result);
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        String expectedOutput = "Tai khoan dang nhap khong ton tai hoac sai thong tin dang nhap !\r\nInvalid username or password !\r\n";
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void testLoginWithDatabaseError() {
        String name = "username";
        String password = "password";

    }
}