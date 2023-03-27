package Model;

public class Department {
    private int deptID;
    private String deptName;
    private String deptDesc;
    private int depManagerID;

    public Department(int deptID, String deptName, String deptDesc, int depManagerID) {
        this.deptID = deptID;
        this.deptName = deptName;
        this.deptDesc = deptDesc;
        this.depManagerID = depManagerID;
    }

    public Department(String deptName, String deptDesc, int depManagerID) {
        this.deptName = deptName;
        this.deptDesc = deptDesc;
        this.depManagerID = depManagerID;
    }

    public int getDeptID() {
        return deptID;
    }

    public void setDeptID(int deptID) {
        this.deptID = deptID;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getDeptDesc() {
        return deptDesc;
    }

    public void setDeptDesc(String deptDesc) {
        this.deptDesc = deptDesc;
    }

    public int getDepManagerID() {
        return depManagerID;
    }

    public void setDepManagerID(int depManagerID) {
        this.depManagerID = depManagerID;
    }

    @Override
    public String toString() {
        return "Department {" +
                "deptID=" + deptID +
                ", deptName='" + deptName + '\'' +
                ", deptDesc='" + deptDesc + '\'' +
                ", depManagerID=" + depManagerID +
                '}';
    }
}
