package BusinessLayer.Human_Resources;

import BusinessLayer.Delivery.Delivery;
import ServiceLayer.JsonService.JsonConverter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

public class Employee {
    private final List<EmployeeConstraint> constraints;
    private String employeeId;
    private String firstName;
    private String lastName;
    private String password;
    private int accountNumber;
    private int branchBankNumber;
    private int salary;
    private String termsOfEmployment;
    private String statusOfEmployee;
    private String phoneNumber;
    private RoleType type;
    @JsonDeserialize(using = JsonConverter.LocalDateTimeDeserializer.class)
    @JsonSerialize(using = JsonConverter.LocalDateTimeSerializer.class)
    private final LocalDateTime hireDate;
    private boolean isLogged;

    public Employee(String _employeeId, String _firstName, String _lastName, String _password, int _accountNumber, int _branchBankNumber, int _salary, String _termsOfEmployment, String _statusOfEmployee, LocalDateTime _hireDate, String _phoneNumber, int _type) {
        employeeId = _employeeId;
        firstName = _firstName;
        lastName = _lastName;
        password = _password;
        accountNumber = _accountNumber;
        branchBankNumber = _branchBankNumber;
        salary = _salary;
        termsOfEmployment = _termsOfEmployment;
        statusOfEmployee = _statusOfEmployee;
        hireDate = _hireDate;
        phoneNumber = _phoneNumber;
        isLogged = false;
        constraints = new LinkedList<>();
        type= Employee.RoleType.values()[_type];
    }

    public Employee(String _employeeId, String _firstName, String _lastName, String _password, int _accountNumber, int _branchBankNumber, int _salary, String _termsOfEmployment, String _statusOfEmployee, LocalDateTime _hireDate, String _phoneNumber, boolean _isLogged,List<EmployeeConstraint> _constraints,int _type) {
        employeeId = _employeeId;
        firstName = _firstName;
        lastName = _lastName;
        password = _password;
        accountNumber = _accountNumber;
        branchBankNumber = _branchBankNumber;
        salary = _salary;
        termsOfEmployment = _termsOfEmployment;
        statusOfEmployee = _statusOfEmployee;
        hireDate = _hireDate;
        phoneNumber = _phoneNumber;
        isLogged = _isLogged;
        constraints = _constraints;
        type= Employee.RoleType.values()[_type];;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String _employeeId) {
        employeeId = _employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String _firstName) {
        firstName = _firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String _lastName) {
        lastName = _lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String _password) { //Will be change
        password = _password;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int _accountNumber) { //Will be change
        accountNumber = _accountNumber;
    }

    public int getBranchBankNumber() {
        return branchBankNumber;
    }

    public void setBranchBankNumber(int _branchBankNumber) { //Will be change
        branchBankNumber = _branchBankNumber;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int _salary) {
        salary = _salary;
    }

    public String getTermsOfEmployment() {
        return termsOfEmployment;
    }

    public void setTermsOfEmployment(String _termsOfEmployment) {
        termsOfEmployment = _termsOfEmployment;
    }

    public String getStatusOfEmployee() {
        return statusOfEmployee;
    }

    public void setStatusOfEmployee(String _statusOfEmployee) {
        statusOfEmployee = _statusOfEmployee;
    }

    public LocalDateTime getHireDate() {
        return hireDate;
    }

    public boolean isHRManager() {
        return type.ordinal()==2;
    }

    public void setHRManager() {
        type = RoleType.HRMANAGER;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String _phoneNumber) {
        phoneNumber = _phoneNumber;
    }

    public List<EmployeeConstraint> getConstraints() {
        return constraints;
    }

    public boolean isLogged() {return isLogged;}

    public void addConstraint(EmployeeConstraint _constraint) {
        constraints.add(_constraint);
    }

    public void removeConstraint(int constraintId) {
        constraints.removeIf(employeeConstraint -> employeeConstraint.getConstraintId() == constraintId);
    }

    public void deleteAllConstraints() {
        constraints.clear();
    }

    public boolean isConstraintExist(int constraintId) {
        for (EmployeeConstraint employeeConstraint : constraints) {
            if (employeeConstraint.getConstraintId() == constraintId) return true;
        }
        return false;
    }

    public boolean containsConstraintWithShiftId(int shiftId) {
        for (EmployeeConstraint employeeConstraint : constraints) {
            if (employeeConstraint.getShiftId() == shiftId) return true;
        }
        return false;
    }

    public void login(String password) throws Exception {
        if (isLogged) throw new Exception("User already logged in");
        if (password == null) throw new Exception("Password is null");
        if (password.equals(this.password)) isLogged = true;
        else throw new Exception("Password does not match");
    }

    public void logout() throws Exception {
        if (isLogged) isLogged = false;
        else throw new Exception("User is already logged out");
    }

    public boolean isManagement() {
        return false;
    }

    public void setManagement(boolean _management) {
    }

    public boolean isCancellations() {
        return false;
    }

    public void setCancellations(boolean _cancellations) {
    }

    public List<String> getRoles() {
        List<String> driver = new LinkedList<>();
        driver.add("Driver");
        return driver;
    }

    public void removeRole(String role) {
    }

    public void addRole(String role) {
    }

    public RoleType getType() {
        return type;
    }

    public int getTypeOrdinal() {
        return type.ordinal();
    }

    enum RoleType {BRANCHEMPLOYEE, DRIVER, HRMANAGER, DELIVERYMANAGER, SUPPLIERMANAGER,STORAGEMANAGER}


}
