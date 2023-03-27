package Service;

import Model.Employees;

import java.text.ParseException;
import java.util.List;

public interface IEmployeeService {
 void getAllEmployees() ;
 void addEmployee() throws ParseException;
 void deleteEmployee();
 void updateEmployee();
 void showEmployeebyID();
 void searchEmployee();
 void addEmployeeToDepartment();
 void removeEmployeeFromDepartment();
 void calculatePersonalIncomeTax();
 //chức năng thêm
 void sortEmployeesBySalaryDescending(); // lấy danh sách có nhân viên từ cao xuống thấp
 void getEmployeesByDepartmentId(); // lấy ds nhân viên theo phng ban

}
