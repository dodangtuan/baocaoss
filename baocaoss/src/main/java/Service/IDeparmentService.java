package Service;

import java.text.ParseException;

public interface IDeparmentService {
    void getAllDepartments() ;
    void addDepartment() throws ParseException;
    void showDepartmentById() ;
    void updateDepartment() ;
    void deleteDepartment() ;

//    void updateDeptIDNull();
}
