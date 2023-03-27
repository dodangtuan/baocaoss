package Service;

import java.text.ParseException;

public interface IDeparmentService {
    void getAllDepartments() ;
    void addDepartment() throws ParseException;
    void showDepartmentById() ;
    void updateDepartment() ;
    void deleteDepartment() ;

    //lấy thông tin quản lý trong phòng ban
    void DepartmentManagerInfo();

}
