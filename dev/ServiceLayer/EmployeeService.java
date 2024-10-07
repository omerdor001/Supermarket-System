package ServiceLayer;

import BusinessLayer.Human_Resources.Employee;
import BusinessLayer.Human_Resources.EmployeeController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class EmployeeService {
    private final EmployeeController employeeController;

    public EmployeeService(EmployeeController employeeController) {
        this.employeeController = employeeController;
    }

    public EmployeeController getEmployeeController() {
        return employeeController;
    }

    public Set<String> getEmployeeIds(){
        return employeeController.getEmployeeIds();
    }

    public String showEmployees() {
        try {
            return employeeController.showEmployees();
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String showAllConstraintToEmployee(String employeeId) {
        try {
            return employeeController.showAllConstraintToEmployee(employeeId);
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String getFirstName(String employeeId) {
        try {
            return employeeController.getFirstName(employeeId);
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String setFirstName(String employeeId, String _firstName) {
        try {
            employeeController.setFirstName(employeeId, _firstName);
            return "success";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String getLastName(String employeeId) {
        try {
            return employeeController.getLastName(employeeId);
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String setLastName(String employeeId, String _lastName) {
        try {
            employeeController.setLastName(employeeId, _lastName);
            return "success";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String registerDetails(String _employeeId) {
        try {
            return employeeController.returnRegisterDetails(_employeeId);
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String editPassword(String employeeId, String password) {
        try {
            employeeController.editPassword(employeeId, password);
            return "success";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String getAccountNumber(String employeeId) {
        try {
            return employeeController.getAccountNumber(employeeId);
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String setAccountNumber(String employeeId, int _accountNumber) {
        try {
            employeeController.setAccountNumber(employeeId, _accountNumber);
            return "success";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String getBranchBankNumber(String employeeId) {
        try {
            return employeeController.getBranchBankNumber(employeeId);
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String setBranchBankNumber(String employeeId, int _branchBankNumber) {
        try {
            employeeController.setBranchBankNumber(employeeId, _branchBankNumber);
            return "success";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String getSalary(String employeeId) {
        try {
            return employeeController.getSalary(employeeId);
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String setSalary(String employeeId, int _salary) {
        try {
            employeeController.setSalary(employeeId, _salary);
            return "success";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String getTermsOfEmployment(String employeeId) {
        try {
            return employeeController.getTermsOfEmployment(employeeId);
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String setTermsOfEmployment(String employeeId, String _termsOfEmployment) {
        try {
            employeeController.setTermsOfEmployment(employeeId, _termsOfEmployment);
            return "success";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String getStatusOfEmployee(String employeeId) {
        try {
            return employeeController.getStatusOfEmployee(employeeId);
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String setStatusOfEmployee(String employeeId, String _statusOfEmployee) {
        try {
            employeeController.setStatusOfEmployee(employeeId, _statusOfEmployee);
            return "success";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String setPhoneNumber(String employeeId, String _phoneNumber) {
        try {
            employeeController.setPhoneNumber(employeeId, _phoneNumber);
            return "success";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String getHireDate(String employeeId) {
        try {
            return employeeController.getHireDate(employeeId);
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String isHRManager(String employeeId) {
        try {
            boolean answer = employeeController.isHRManager(employeeId);
            if (answer) return "true";
            else return "false";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String setHRManager(String employeeId) {
        try {
            employeeController.setHRManager(employeeId);
            return "Employee's HR status was edited successfully";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String getCancellation(String employeeId) {
        try {
            return employeeController.getCancellation(employeeId);
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String setCancellation(String employeeId) {
        try {
            employeeController.setCancellation(employeeId);
            return "Cancellation permission was changed successfully";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String getManagement(String employeeId) {
        try {
            return employeeController.getManagement(employeeId);
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String setManagement(String employeeId) {
        try {
            employeeController.setManagement(employeeId);
            return "success";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String getRolesOfEmployee(String employeeId) {
        try {
            return employeeController.getRolesOfEmployee(employeeId);
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String editConstraintDescription(String employeeId, int constraintId, String description) {
        try {
            employeeController.editConstraintDescription(employeeId, constraintId, description);
            return "Constraint has been edited successfully";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String registerBranchEmployee(String _employeeId, String _firstName, String _lastName, String _password, int _accountNumber, int _branchBankNumber, int _salary, String _termsOfEmployment, String _statusOfEmployee, LocalDateTime _hireDate, String _phoneNumber, boolean _management, boolean _cancellations) {
        try {
            employeeController.registerBranchEmployee(_employeeId, _firstName, _lastName, _password, _accountNumber, _branchBankNumber, _salary, _termsOfEmployment, _statusOfEmployee, _hireDate, _phoneNumber, _management, _cancellations);
            return "success";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String registerDriver(String _employeeId, String _firstName, String _lastName, String _password, int _accountNumber, int _branchBankNumber, int _salary, String _termsOfEmployment, String _statusOfEmployee, LocalDateTime _hireDate, String _phoneNumber, int _licenses) {
        try {
            employeeController.registerDriver(_employeeId, _firstName, _lastName, _password, _accountNumber, _branchBankNumber, _salary, _termsOfEmployment, _statusOfEmployee, _hireDate, _phoneNumber, _licenses);
            return "success";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String registerEmployee(String _employeeId, String _firstName, String _lastName, String _password, int _accountNumber, int _branchBankNumber, int _salary, String _termsOfEmployment, String _statusOfEmployee, LocalDateTime _hireDate, String _phoneNumber,int type) {
        try {
            employeeController.registerEmployee(_employeeId, _firstName, _lastName, _password, _accountNumber, _branchBankNumber, _salary, _termsOfEmployment, _statusOfEmployee, _hireDate, _phoneNumber,type);
            return "success";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String removeEmployee(String employeeId) {
        try {
            employeeController.removeEmployee(employeeId);
            return ("Employee removed successfully");
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String insertRole(String employeeId, String role) {
        try {
            employeeController.insertRole(employeeId, role);
            return "Role inserted successfully";

        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String removeConstraint(String employeeId, int constraintId) {
        try {
            employeeController.removeConstraint(employeeId, constraintId);
            return "Constraint was removed successfully";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String removeRole(String employeeId, String role) {
        try {
            employeeController.removeRole(employeeId, role);
            return "Role removed successfully";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String isEmployeeExists(String employeeId) {
        try {
            return employeeController.isEmployeeExists(employeeId);
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String isConstraintExist(String employeeId, int constraintId) {
        try {
            return employeeController.isConstraintExist(employeeId, constraintId);
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String isRoleExist(String role) {
        try {
            return employeeController.isRoleExist(role);
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String login(String employeeId, String password) {
        try {
            employeeController.login(employeeId, password);
            return "success";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String logout(String employeeId) {
        try {
            employeeController.logout(employeeId);
            return "Logout Successfully";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String upgradeDriverLicense(String driverId, int license) {
        try {
            employeeController.upgradeDriverLicense(driverId, license);
            return ("Driver license changed successfully");
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String addDriverQualification(String driverId, String qualification) {
        try {
            employeeController.addDriverQualification(driverId, qualification);
            return ("Driver qualification added successfully");
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String removeDriverQualification(String driverId, String qualification) {
        try {
            employeeController.removeDriverQualification(driverId, qualification);
            return ("Driver qualification removed successfully");
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String hasDriverQualification(String driverId, String qualification) {
        try {
            if (employeeController.hasDriverQualification(driverId, qualification))
                return "Driver has qualification";
            else
                return ("Driver does not have qualification");
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    //will become constraints later
    public String addDriverDeliveryDate(String driverId, LocalDate deliveryDate) {
        try {
            employeeController.addDriverDeliveryDate(driverId, deliveryDate);
            return ("Driver delivery date added successfully");
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String removeDriverDeliveryDate(String driverId, LocalDate deliveryDate) {
        try {
            employeeController.removeDriverDeliveryDate(driverId, deliveryDate);
            return ("Driver delivery date removed successfully");
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String showDriver(String driverId) {
        try {
            return employeeController.showDriver(driverId);
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String showAllDrivers() {
        try {
            return employeeController.showAllDrivers();
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String showEmployee(String employeeId) {
        try {
            return employeeController.showEmployee(employeeId);
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String isBranchEmployeeV(String employeeId){
        return employeeController.isBranchEmployeeV(employeeId);
    }

    public String isDriverV(String employeeId){
        return employeeController.isDriverV(employeeId);
    }
}
