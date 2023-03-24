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

}
