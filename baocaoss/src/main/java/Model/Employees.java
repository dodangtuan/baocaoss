package Model;

public class Employees {
    private int employeeID;
    private String fullName;
    private int age;
    private String gender;
    private String email;
    private String phone;
    private Double salary;
    private int managerID;
    private int deptID;


    public Employees(int employeeID, String fullName, int age, String gender, String email, String phone, Double salary, int managerID, int deptID) {
        this.employeeID = employeeID;
        this.fullName = fullName;
        this.age = age;
        this.gender = gender;
        this.email = email;
        this.phone = phone;
        this.salary = salary;
        this.managerID = managerID;
        this.deptID = deptID;
    }

    public Employees(String fullName, int age, String gender, String email, String phone, Double salary, int managerID, int deptID) {
        this.fullName = fullName;
        this.age = age;
        this.gender = gender;
        this.email = email;
        this.phone = phone;
        this.salary = salary;
        this.managerID = managerID;
        this.deptID = deptID;
    }

    public Employees(int employeeID, String fullName, int age, String gender, String email, String phone, Double salary) {
        this.employeeID = employeeID;
        this.fullName = fullName;
        this.age = age;
        this.gender = gender;
        this.email = email;
        this.phone = phone;
        this.salary = salary;
    }

    public Employees() {

    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public int getManagerID() {
        return managerID;
    }

    public void setManagerID(int managerID) {
        this.managerID = managerID;
    }

    public int getDeptID() {
        return deptID;
    }

    public void setDeptID(int deptID) {
        this.deptID = deptID;
    }

    @Override
    public String toString() {
        long roundedSalary = Math.round(salary);
        return "Employee {" +
                "employeeID=" + employeeID +
                ", fullName='" + fullName + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", salary=" + String.valueOf(roundedSalary) +
                ", managerID=" + managerID +
                ", deptID=" + deptID +
                '}';
    }
}
