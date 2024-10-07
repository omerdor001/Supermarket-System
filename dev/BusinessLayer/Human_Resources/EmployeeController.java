package BusinessLayer.Human_Resources;

import ServiceLayer.JsonService.JsonConverter;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import DataAccessLayer.DALFacade;

public class EmployeeController {
    private HashMap<String, Employee> employees;
    private final int MIN_PASS_LENGTH = 6;
    private final int MAX_PASS_LENGTH = 20;
    private final DALFacade dalController;

    public EmployeeController() {
        employees = new HashMap<>();
        dalController= DALFacade.getInstance();
        loadData();
    }

    public void loadData(){
        try{
            setEmployees(dalController.getAllEmployees());
            HashMap<String,BranchEmployee> branchEmployees=dalController.getAllBranchEmployees();
            for(String employeeId:branchEmployees.keySet()){
                employees.put(employeeId,branchEmployees.get(employeeId));
            }
            HashMap<String,Driver> drivers=dalController.getAllDrivers();
            for(String employeeId:drivers.keySet()){
                employees.put(employeeId,drivers.get(employeeId));
            }
        }
        catch (Exception e){
            throw new RuntimeException("DAL Error");
        }
    }

    private void setEmployees(HashMap<String, Employee> _employees){
        employees=_employees;
    }

    private static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public HashMap<String, Employee> getEmployees() {
        return employees;
    }

    public Set<String> getEmployeeIds(){
        return employees.keySet();
    }

    public String showEmployees() {
        StringBuilder allEmployees = new StringBuilder();
        for (Employee employee : employees.values()) {
            allEmployees.append("Id: ").append(employee.getEmployeeId()).append(" Name: ").append(employee.getFirstName()).append(" ").append(employee.getLastName()).append(" ").append(employee.getType()).append("\n");

        }
        return allEmployees.toString();
    }

    public BranchEmployee getBranchEmployee(String employeeId){
        if (!employees.containsKey(employeeId)) throw new NoSuchElementException("Employee doesn't exist");
        if(employees.get(employeeId).getType()== Employee.RoleType.BRANCHEMPLOYEE)
            return (BranchEmployee) employees.get(employeeId);
        else throw new IllegalArgumentException("Employee is not branch employee");
    }

    public String showAllConstraintToEmployee(String employeeId) throws Exception {
        String constraints = "";
        if (!employees.containsKey(employeeId)) throw new Exception("Employee doesn't exist");
        for (EmployeeConstraint employeeConstraint : employees.get(employeeId).getConstraints()) {
            constraints = employeeConstraint.getConstraintId() + "- Shift: " + employeeConstraint.getShiftId() + " Description: " + employeeConstraint.getDescription() + "\n";
        }
        return constraints;
    }

    public String getFirstName(String employeeId) throws Exception {
        if (!employees.containsKey(employeeId)) throw new Exception("Employee doesn't exist");
        return employees.get(employeeId).getFirstName();
    }

    public void setFirstName(String employeeId, String _firstName) throws Exception {
        if (!employees.containsKey(employeeId)) throw new Exception("Employee doesn't exist");
        if (_firstName == null || _firstName.length() == 0) throw new Exception("First name can't be empty");
        employees.get(employeeId).setFirstName(_firstName);
        dalController.updateEmployee(employees.get(employeeId),"FirstName");
    }

    public String getLastName(String employeeId) throws Exception {
        if (!employees.containsKey(employeeId)) throw new Exception("Employee doesn't exist");
        return employees.get(employeeId).getLastName();
    }

    public void setLastName(String employeeId, String _lastName) throws Exception {
        if (!employees.containsKey(employeeId)) throw new Exception("Employee doesn't exist");
        if (_lastName == null || _lastName.length() == 0) throw new Exception("Last name can't be empty");
        employees.get(employeeId).setLastName(_lastName);
        dalController.updateEmployee(employees.get(employeeId),"LastName");
    }

    public String returnRegisterDetails(String employeeId) throws Exception {
        if (!employees.containsKey(employeeId)) throw new Exception("Employee doesn't exist");
        return "Employee Id: " + employeeId + "\n" + "Password: " + employees.get(employeeId).getPassword();
    }

    public void editPassword(String employeeId, String password) throws Exception {
        if (!employees.containsKey(employeeId)) throw new Exception("Employee doesn't exist");
        checkPassword(password);
        employees.get(employeeId).setPassword(password);
        dalController.updateEmployee(employees.get(employeeId),"Password");
    }

    public String getAccountNumber(String employeeId) throws Exception {
        if (!employees.containsKey(employeeId)) throw new Exception("Employee doesn't exist");
        return Integer.toString(employees.get(employeeId).getAccountNumber());
    }

    public void setAccountNumber(String employeeId, int _accountNumber) throws Exception {
        if (!employees.containsKey(employeeId)) throw new Exception("Employee doesn't exist");
        employees.get(employeeId).setAccountNumber(_accountNumber);
        dalController.updateEmployee(employees.get(employeeId),"AccountNumber");
    }

    public String getBranchBankNumber(String employeeId) throws Exception {
        if (!employees.containsKey(employeeId)) throw new Exception("Employee doesn't exist");
        return Integer.toString(employees.get(employeeId).getBranchBankNumber());
    }

    public void setBranchBankNumber(String employeeId, int _branchBankNumber) throws Exception {
        if (!employees.containsKey(employeeId)) throw new Exception("Employee doesn't exist");
        employees.get(employeeId).setBranchBankNumber(_branchBankNumber);
        dalController.updateEmployee(employees.get(employeeId),"BranchBankNumber");
    }

    public String getSalary(String employeeId) throws Exception {
        if (!employees.containsKey(employeeId)) throw new Exception("Employee doesn't exist");
        return Integer.toString(employees.get(employeeId).getSalary());
    }

    public void setSalary(String employeeId, int _salary) throws Exception {
        if (!employees.containsKey(employeeId)) throw new Exception("Employee doesn't exist");
        employees.get(employeeId).setSalary(_salary);
        dalController.updateEmployee(employees.get(employeeId),"Salary");
    }

    public String getTermsOfEmployment(String employeeId) throws Exception {
        if (!employees.containsKey(employeeId)) throw new Exception("Employee doesn't exist");
        return employees.get(employeeId).getTermsOfEmployment();
    }

    public void setTermsOfEmployment(String employeeId, String _termsOfEmployment) throws Exception {
        if (!employees.containsKey(employeeId)) throw new Exception("Employee doesn't exist");
        employees.get(employeeId).setTermsOfEmployment(_termsOfEmployment);
        dalController.updateEmployee(employees.get(employeeId),"TermsOfEmployment");
    }

    public String getStatusOfEmployee(String employeeId) throws Exception {
        if (!employees.containsKey(employeeId)) throw new Exception("Employee doesn't exist");
        return employees.get(employeeId).getStatusOfEmployee();
    }

    public void setStatusOfEmployee(String employeeId, String _statusOfEmployee) throws Exception {
        if (!employees.containsKey(employeeId)) throw new Exception("Employee doesn't exist");
        if (_statusOfEmployee == null || _statusOfEmployee.length() == 0)
            throw new Exception("Status of employee can't be empty");
        employees.get(employeeId).setStatusOfEmployee(_statusOfEmployee);
        dalController.updateEmployee(employees.get(employeeId),"StatusOfEmployee");
    }

    public void setPhoneNumber(String employeeId, String _phoneNumber) throws Exception {
        if (!employees.containsKey(employeeId)) throw new Exception("Employee doesn't exist");
        if (_phoneNumber == null || _phoneNumber.length() == 0)
            throw new Exception("Phone number of employee can't be empty");
        employees.get(employeeId).setPhoneNumber(_phoneNumber);
        dalController.updateEmployee(employees.get(employeeId),"PhoneNumber");
    }

    public String getHireDate(String employeeId) throws Exception {
        if (!employees.containsKey(employeeId)) throw new Exception("Employee doesn't exist");
        return employees.get(employeeId).getHireDate().toString();
    }

    public boolean isHRManager(String employeeId) throws Exception {
        if (!employees.containsKey(employeeId)) throw new Exception("Employee doesn't exist");
        return employees.get(employeeId).isHRManager();
    }

    public void setHRManager(String employeeId) throws Exception {
        if (!employees.containsKey(employeeId)) throw new Exception("Employee doesn't exist");
        if (!employees.get(employeeId).isManagement() || !employees.get(employeeId).isCancellations())
            throw new Exception("Employee has not the trainings for being HR manager yet");
        employees.get(employeeId).setHRManager();
    }

    public String getCancellation(String employeeId) throws Exception {
        if (!employees.containsKey(employeeId)) throw new Exception("Employee doesn't exist");
        boolean cancellation = employees.get(employeeId).isCancellations();
        return Boolean.toString(cancellation);
    }

    public void setCancellation(String employeeId) throws Exception {
        if (!employees.containsKey(employeeId)) throw new Exception("Employee doesn't exist");
        if (!employees.get(employeeId).isCancellations()) employees.get(employeeId).setCancellations(true);
        else if (employees.get(employeeId).isCancellations()) employees.get(employeeId).setCancellations(false);
        dalController.updateBranchEmployee(getBranchEmployee(employeeId),"Cancellation");
    }

    public String getManagement(String employeeId) throws Exception {
        if (!employees.containsKey(employeeId)) throw new Exception("Employee doesn't exist");
        boolean management = employees.get(employeeId).isManagement();
        return Boolean.toString(management);
    }

    public void setManagement(String employeeId) throws Exception {
        if (!employees.containsKey(employeeId)) throw new Exception("Employee doesn't exist");
        if (!employees.get(employeeId).isManagement()) employees.get(employeeId).setManagement(true);
        else if(employees.get(employeeId).isManagement()) employees.get(employeeId).setManagement(false);
        dalController.updateBranchEmployee(getBranchEmployee(employeeId),"Management");
    }

    public String getRolesOfEmployee(String employeeId) throws Exception {
        StringBuilder roles = new StringBuilder();
        if (!employees.containsKey(employeeId)) throw new Exception("Employee doesn't exist");
        if (employees.get(employeeId).getType() == Employee.RoleType.DRIVER)
            roles = new StringBuilder("Driver");
        else {
            for (String role : employees.get(employeeId).getRoles()) {
                roles.append(role).append("\n");
            }
        }
        return roles.toString();
    }

    public void editConstraintDescription(String employeeId, int constraintId, String description) throws Exception {
        if (!employees.containsKey(employeeId)) throw new Exception("Employee doesn't exist");
        if (!employees.get(employeeId).isConstraintExist(constraintId))
            throw new Exception("Constraint does not exist at employee");
        for (EmployeeConstraint employeeConstraint : employees.get(employeeId).getConstraints()) {
            if (employeeConstraint.getConstraintId() == constraintId) employeeConstraint.setDescription(description);
            dalController.updateEmployeeConstraint(employees.get(employeeId).getConstraints().get(constraintId-1),"Description");
        }
    }

    public void registerBranchEmployee(String _employeeId, String _firstName, String _lastName, String _password, int _accountNumber, int _branchBankNumber, int _salary, String _termsOfEmployment, String _statusOfEmployee, LocalDateTime _hireDate, String _phoneNumber, boolean _management, boolean _cancellations) throws Exception {
        registerChecks(_employeeId, _firstName, _lastName, _password);
        char f = Character.toUpperCase(_firstName.charAt(0));
        String firstName = f + _firstName.substring(1);
        char l = Character.toUpperCase(_lastName.charAt(0));
        String lastName = l + _lastName.substring(1);
        BranchEmployee branchEmployee = new BranchEmployee(_employeeId, firstName, lastName, _password, _accountNumber, _branchBankNumber, _salary, _termsOfEmployment, _statusOfEmployee, _hireDate, _phoneNumber, _management, _cancellations);
        employees.put(_employeeId, branchEmployee);
        dalController.insertBranchEmployee(branchEmployee);
    }

    public void registerDriver(String _employeeId, String _firstName, String _lastName, String _password, int _accountNumber, int _branchBankNumber, int _salary, String _termsOfEmployment, String _statusOfEmployee, LocalDateTime _hireDate, String _phoneNumber, int _licenses) throws Exception {
        registerChecks(_employeeId, _firstName, _lastName, _password);
        char f = Character.toUpperCase(_firstName.charAt(0));
        String firstName = f + _firstName.substring(1);
        char l = Character.toUpperCase(_lastName.charAt(0));
        String lastName = l + _lastName.substring(1);
        if (_licenses <= 0)
            throw new IllegalArgumentException("illegal license");
        Driver driver = new Driver(_employeeId, firstName, lastName, _password, _accountNumber, _branchBankNumber, _salary, _termsOfEmployment, _statusOfEmployee, _hireDate, _phoneNumber, _licenses);
        employees.put(_employeeId, driver);
        dalController.insertDriver(driver);
    }

    public void registerEmployee(String _employeeId, String _firstName, String _lastName, String _password, int _accountNumber, int _branchBankNumber, int _salary, String _termsOfEmployment, String _statusOfEmployee, LocalDateTime _hireDate, String _phoneNumber, int type) throws Exception {
        registerChecks(_employeeId, _firstName, _lastName, _password);
        char f = Character.toUpperCase(_firstName.charAt(0));
        String firstName = f + _firstName.substring(1);
        char l = Character.toUpperCase(_lastName.charAt(0));
        String lastName = l + _lastName.substring(1);
        Employee employee = new Employee(_employeeId, firstName, lastName, _password, _accountNumber, _branchBankNumber, _salary, _termsOfEmployment, _statusOfEmployee, _hireDate, _phoneNumber, type);
        employees.put(_employeeId, employee);
        dalController.insertEmployee(employee);
    }

    public void insertRole(String employeeId, String role) throws Exception {
        String _role;
        if (!employees.containsKey(employeeId)) throw new Exception("Employee doesn't exist");
        if (role.equals("shift manager") || role.equals("shift Manager")) _role = "Shift Manager";
        else if (role.equals("store keeper") || role.equals("store Keeper")) _role = "Store Keeper";
        else if (role.equals("general employee") || role.equals("general Employee")) _role = "General Employee";
        else if (Character.isLowerCase(role.charAt(0)))
            _role = Character.toUpperCase(role.charAt(0)) + role.substring(1);
        else _role = role;
        if (isRoleExist(_role).equals("false")) throw new Exception("Role doesn't exist");
        if (employees.get(employeeId).getRoles().contains(_role)) throw new Exception("Role exists for employee");
        if (employees.get(employeeId).getType() == Employee.RoleType.DRIVER)
            throw new Exception("Driver has one rule only");
        employees.get(employeeId).addRole(_role);
        dalController.insertBranchEmployeeRole(getBranchEmployee(employeeId),role);
    }

    public void removeConstraint(String employeeId, int constraintId) throws Exception {
        if (!employees.containsKey(employeeId)) throw new Exception("Employee doesn't exist");
        if (!employees.get(employeeId).isConstraintExist(constraintId))
            throw new Exception("Constraint does not exist at employee");
        dalController.deleteConstraintToEmployee(employees.get(employeeId),employees.get(employeeId).getConstraints().get(constraintId-1));
        dalController.deleteEmployeeConstraint(employees.get(employeeId).getConstraints().get(constraintId-1));
        employees.get(employeeId).removeConstraint(constraintId-1);
    }

    public void removeEmployee(String employeeId){
        if (!employees.containsKey(employeeId)) throw new NoSuchElementException("Employee doesn't exist");
        employees.get(employeeId).getConstraints().clear();
        employees.remove(employeeId);
    }

    public void removeRole(String employeeId, String role) throws Exception {
        if (!employees.containsKey(employeeId)) throw new Exception("Employee doesn't exist");
        if (isRoleExist(role).equals("false")) throw new Exception("Role doesn't exist");
        employees.get(employeeId).removeRole(role);
        dalController.deleteBranchEmployeeRole(getBranchEmployee(employeeId),"Role");
    }

    public String isEmployeeExists(String employeeId) {
        boolean isExist = employees.containsKey(employeeId);
        return Boolean.toString(isExist);
    }

    public String isConstraintExist(String employeeId, int constraintId) throws Exception {
        if (!employees.containsKey(employeeId)) throw new Exception("Employee doesn't exist");
        boolean result = employees.get(employeeId).isConstraintExist(constraintId);
        return Boolean.toString(result);
    }

    public String isRoleExist(String role) {
        String _role;
        if (role.equals("shift manager") || role.equals("shift Manager")) _role = "Shift Manager";
        else if (role.equals("store keeper") || role.equals("store Keeper")) _role = "Store Keeper";
        else if (role.equals("general employee") || role.equals("general Employee")) _role = "General Employee";
        else if (Character.isLowerCase(role.charAt(0)))
            _role = Character.toUpperCase(role.charAt(0)) + role.substring(1);
        else _role = role;
        List<String> roles = new LinkedList<>();
        roles.add("Shift Manager");
        roles.add("Store Keeper");
        roles.add("Cashier");
        roles.add("General Employee");
        roles.add("Guard");
        roles.add("Cleaner");
        roles.add("Merchandiser");
        boolean isExist = roles.contains(_role);
        return Boolean.toString(isExist);
    }

    public void login(String employeeId, String password) throws Exception {
        if (!employees.containsKey(employeeId)) throw new Exception("Employee doesn't exist");
        employees.get(employeeId).login(password);
        dalController.updateEmployee(employees.get(employeeId),"IsLogged");
    }

    public void logout(String employeeId) throws Exception {
        if (!employees.containsKey(employeeId)) throw new Exception("Employee doesn't exist");
        employees.get(employeeId).logout();
        dalController.updateEmployee(employees.get(employeeId),"IsLogged");
    }

    private void checkPassword(String password) throws Exception {
        if (password == null) throw new Exception("Password is null");
        if (password.length() < MIN_PASS_LENGTH || password.length() > MAX_PASS_LENGTH)
            throw new Exception("Password must be between 6 till 20 characters");
        boolean isUpperCase = false;
        boolean isLowerCase = false;
        boolean isDigit = false;
        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);
            if (Character.isUpperCase(c)) isUpperCase = true;
            if (Character.isLowerCase(c)) isLowerCase = true;
            if (Character.isDigit(c)) isDigit = true;
        }
        if (!isDigit) throw new Exception("Password must be with at least one digit");
        if (!isUpperCase) throw new Exception("Password must be with at least one upper letter");
        if (!isLowerCase) throw new Exception("Password must be with at least one lower letter");
    }

    private void registerChecks(String _employeeId, String _firstName, String _lastName, String _password) throws Exception {
        if (_employeeId == null) throw new Exception("Employee Id is null");
        if(!isNumeric(_employeeId)) throw new Exception("Employee Id must be with numbers");
        if (_employeeId.length() != 9)
            throw new Exception("Employee Id must be with 9 numbers");
        if (_firstName == null) throw new Exception("First name is null");
        if (_firstName.length() == 0) throw new Exception("First name is empty");
        if (_lastName == null) throw new Exception("Last name is null");
        if (_lastName.length() == 0) throw new Exception("Last name is empty");
        checkPassword(_password);
        if (employees.containsKey(_employeeId)) {
            throw new Exception("Employee Id already exists");
        }
    }

    public Driver getDriver(String driverId) {
        if (!employees.containsKey(driverId)) {
            throw new NoSuchElementException("Driver doesn't exist");
        }
        if (employees.get(driverId).getType() != Employee.RoleType.DRIVER)
            throw new NoSuchElementException("this employee isn't driver");
        return (Driver) employees.get(driverId);
    }

    public HashMap<String, Driver> getDrivers() {
        HashMap<String, Driver> drivers = new HashMap<>();
        for (String driverId : employees.keySet()) {
            if (employees.get(driverId).getType() == Employee.RoleType.DRIVER) {
                drivers.put(driverId, (Driver) employees.get(driverId));
            }
        }
        return drivers;
    }

    public void upgradeDriverLicense(String driverId, int newLicense) {
        Driver driver = getDriver(driverId);
        driver.upgardeLicense(newLicense);
        dalController.updateDriver(driver,"License");
    }

    public void addDriverQualification(String driverId, String qualification) {
        Driver driver = getDriver(driverId);
        driver.addQualification(qualification);
        dalController.insertDriverQualification(driver,qualification);
    }

    public void removeDriverQualification(String driverId, String qualification) {
        Driver driver = getDriver(driverId);
        driver.removeQualification(qualification);
        dalController.deleteDriverQualification(driver,qualification);
    }

    public boolean hasDriverQualification(String driverId, String qualification) {
        Driver driver = getDriver(driverId);
        return driver.hasQualification(qualification);
    }

    public void addDriverDeliveryDate(String driverId, LocalDate deliveryDate) {
        Driver driver = getDriver(driverId);
        driver.addDeliveryDate(deliveryDate);
        dalController.insertDriverDeliveryDate(driver,deliveryDate);
    }

    public void removeDriverDeliveryDate(String driverId, LocalDate deliveryDate) {
        Driver driver = getDriver(driverId);
        driver.removeDeliveryDate(deliveryDate);
        dalController.deleteDriverDeliveryDate(driver,deliveryDate);
    }

    public String showDriver(String driverId) throws JsonProcessingException {
        Driver driver = getDriver(driverId);
        return JsonConverter.toJson(driver);
    }

    public String showAllDrivers() throws JsonProcessingException {
        return JsonConverter.toJson(getDrivers());
    }

    public String showEmployee(String employeeId) throws JsonProcessingException {
        if (!employees.containsKey(employeeId))
            throw new NoSuchElementException("Employee doesn't exist");
        if (employees.get(employeeId).getType() == Employee.RoleType.DRIVER)
            return showDriver(employeeId);
        else if (employees.get(employeeId).getType() == Employee.RoleType.BRANCHEMPLOYEE)
            return showBranchEmployee(employeeId);
        else {
            Employee employee = employees.get(employeeId);
            Employee employeeToJSON=new Employee(employee.getEmployeeId(),employee.getFirstName(),employee.getLastName(),employee.getPassword(),employee.getAccountNumber(),employee.getBranchBankNumber(),employee.getSalary(),
                    employee.getTermsOfEmployment(),employee.getStatusOfEmployee(),employee.getHireDate(),employee.getPhoneNumber(),employee.getType().ordinal());
            return JsonConverter.toJson(employeeToJSON);
        }
    }

    public String showBranchEmployee(String employeeId) throws JsonProcessingException {
        if (!employees.containsKey(employeeId)) {
            throw new NoSuchElementException("BranchEmployee doesn't exist");
        }
        if (employees.get(employeeId).getType() != Employee.RoleType.BRANCHEMPLOYEE)
            throw new NoSuchElementException("this employee isn't branch employee");
        BranchEmployee branchEmployee = (BranchEmployee) employees.get(employeeId);
        return JsonConverter.toJson(branchEmployee);
    }

    public String isBranchEmployeeV(String employeeId){
        return Boolean.toString(employees.get(employeeId).getType()== Employee.RoleType.BRANCHEMPLOYEE);
    }

    public String isDriverV(String employeeId){
        return Boolean.toString(employees.get(employeeId).getType()== Employee.RoleType.DRIVER);
    }
}