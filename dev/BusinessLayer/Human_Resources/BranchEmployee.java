package BusinessLayer.Human_Resources;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

public class BranchEmployee extends Employee {
    private final List<String> roles;
    private boolean management;
    private boolean cancellations;

    public BranchEmployee(String _employeeId, String _firstName, String _lastName, String _password, int _accountNumber, int _branchBankNumber, int _salary, String _termsOfEmployment, String _statusOfEmployee, LocalDateTime _hireDate, String _phoneNumber, boolean _management, boolean _cancellations) {
        super(_employeeId, _firstName, _lastName, _password, _accountNumber, _branchBankNumber, _salary, _termsOfEmployment, _statusOfEmployee, _hireDate, _phoneNumber, 0);
        management = _management;
        cancellations = _cancellations;
        roles = new LinkedList<>();
    }

    public BranchEmployee(String _employeeId, String _firstName, String _lastName, String _password, int _accountNumber, int _branchBankNumber, int _salary, String _termsOfEmployment, String _statusOfEmployee, LocalDateTime _hireDate, String _phoneNumber, boolean _management, boolean _cancellations, boolean _isLogged,List<EmployeeConstraint> constraints,int _type) {
        super(_employeeId, _firstName, _lastName, _password, _accountNumber, _branchBankNumber, _salary, _termsOfEmployment, _statusOfEmployee, _hireDate, _phoneNumber, _isLogged,constraints,_type);
        management = _management;
        cancellations = _cancellations;
        roles = new LinkedList<>();
    }

    public BranchEmployee(String _employeeId, String _firstName, String _lastName, String _password, int _accountNumber, int _branchBankNumber, int _salary, String _termsOfEmployment, String _statusOfEmployee, LocalDateTime _hireDate, String _phoneNumber, boolean _management, boolean _cancellations, boolean _isLogged,List<String> _roles,List<EmployeeConstraint> constraints,int _type) {
        super(_employeeId, _firstName, _lastName, _password, _accountNumber, _branchBankNumber, _salary, _termsOfEmployment, _statusOfEmployee, _hireDate, _phoneNumber, _isLogged,constraints,_type);
        management = _management;
        cancellations = _cancellations;
        roles=_roles;
    }

    public boolean isManagement() {
        return management;
    }

    public void setManagement(boolean _management) {
        management = _management;
    }

    public boolean isCancellations() {
        return cancellations;
    }

    public void setCancellations(boolean _cancellations) {
        cancellations = _cancellations;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void addRole(String _role) {
        String role_;
        if (_role.equals("shift manager") || _role.equals("shift Manager")) role_ = "Shift Manager";
        else if (_role.equals("store keeper") || _role.equals("store Keeper")) role_ = "Store Keeper";
        else if (_role.equals("general employee") || _role.equals("general Employee")) role_ = "General Employee";
        else if (Character.isLowerCase(_role.charAt(0)))
            role_ = Character.toUpperCase(_role.charAt(0)) + _role.substring(1);
        else role_ = _role;
        if (role_.equals("Shift Manager") && (!isManagement() || !isCancellations()))
            throw new IllegalArgumentException("Shift Manager must have training in management and cancellations");
        roles.add(role_);
    }

    public void removeRole(String _role) {
        roles.remove(_role);
    }

}
