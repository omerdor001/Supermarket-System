package ServiceLayer;

import BusinessLayer.Human_Resources.BranchController;
import BusinessLayer.Human_Resources.EmployeeController;
import BusinessLayer.Human_Resources.ShiftController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

public class HRService {
    public EmployeeService employeeService;
    public ShiftService shiftService;
    public BranchService branchService;

    public HRService(ShiftController _shiftController, EmployeeController _employeeController, BranchController _branchController) {
        employeeService = new EmployeeService(_employeeController);
        shiftService = new ShiftService(_shiftController);
        branchService = new BranchService(_branchController);
    }

    public BranchService getBranchService() {
        return branchService;
    }

    public EmployeeService getEmployeeService() {
        return employeeService;
    }

    public ShiftService getShiftService() {
        return shiftService;
    }


    //region Employee service
    public Set<String> getEmployeeIds() { return employeeService.getEmployeeIds();}

    public String showEmployees() {
        return employeeService.showEmployees();
    }

    public String showAllConstraintToEmployee(String employeeId) {
        return employeeService.showAllConstraintToEmployee(employeeId);
    }

    public String getFirstName(String employeeId) {
        return employeeService.getFirstName(employeeId);
    }

    public String setFirstName(String employeeId, String _firstName) {
        return employeeService.setFirstName(employeeId, _firstName);
    }

    public String getLastName(String employeeId) {
        return employeeService.getLastName(employeeId);
    }

    public String setLastName(String employeeId, String _lastName) {
        return employeeService.setLastName(employeeId, _lastName);
    }

    public String registerDetails(String _employeeId) {
        return employeeService.registerDetails(_employeeId);
    }

    public String editPassword(String employeeId, String password) {
        return employeeService.editPassword(employeeId, password);
    }

    public String getAccountNumber(String employeeId) {
        return employeeService.getAccountNumber(employeeId);
    }

    public String setAccountNumber(String employeeId, int _accountNumber) {
        return employeeService.setAccountNumber(employeeId, _accountNumber);
    }

    public String getBranchBankNumber(String employeeId) {
        return employeeService.getBranchBankNumber(employeeId);
    }

    public String setBranchBankNumber(String employeeId, int _branchBankNumber) {
        return employeeService.setBranchBankNumber(employeeId, _branchBankNumber);
    }

    public String getSalary(String employeeId) {
        return employeeService.getSalary(employeeId);
    }

    public String setSalary(String employeeId, int _salary) {
        return employeeService.setSalary(employeeId, _salary);
    }

    public String getTermsOfEmployment(String employeeId) {
        return employeeService.getTermsOfEmployment(employeeId);
    }

    public String setTermsOfEmployment(String employeeId, String _termsOfEmployment) {
        return employeeService.setTermsOfEmployment(employeeId, _termsOfEmployment);
    }

    public String getStatusOfEmployee(String employeeId) {
        return employeeService.getStatusOfEmployee(employeeId);
    }

    public String setStatusOfEmployee(String employeeId, String _statusOfEmployee) {
        return employeeService.setStatusOfEmployee(employeeId, _statusOfEmployee);
    }

    public String setPhoneNumber(String employeeId, String _phoneNumber) {
        return employeeService.setPhoneNumber(employeeId, _phoneNumber);
    }

    public String getHireDate(String employeeId) {
        return employeeService.getHireDate(employeeId);
    }

    public String isHRManager(String employeeId) {
        return employeeService.isHRManager(employeeId);
    }

    public String setHRManager(String employeeId) {
        return employeeService.setHRManager(employeeId);
    }

    public String getCancellation(String employeeId) {
        return employeeService.getCancellation(employeeId);
    }

    public String setCancellation(String employeeId) {
        return employeeService.setCancellation(employeeId);
    }

    public String getManagement(String employeeId) {
        return employeeService.getManagement(employeeId);
    }

    public String setManagement(String employeeId) {
        return employeeService.setManagement(employeeId);
    }

    public String getRolesOfEmployee(String employeeId) {
        return employeeService.getRolesOfEmployee(employeeId);
    }

    public String editConstraintDescription(String employeeId, int constraintId, String description) {
        return employeeService.editConstraintDescription(employeeId, constraintId, description);
    }

    public String registerBranchEmployee(String _employeeId, String _firstName, String _lastName, String _password, int _accountNumber, int _branchBankNumber, int _salary, String _termsOfEmployment, String _statusOfEmployee, LocalDateTime _hireDate, String _phoneNumber, boolean _management, boolean _cancellations) {
        return employeeService.registerBranchEmployee(_employeeId, _firstName, _lastName, _password, _accountNumber, _branchBankNumber, _salary, _termsOfEmployment, _statusOfEmployee, _hireDate, _phoneNumber, _management, _cancellations);
    }

    public String registerDriver(String _employeeId, String _firstName, String _lastName, String _password, int _accountNumber, int _branchBankNumber, int _salary, String _termsOfEmployment, String _statusOfEmployee, LocalDateTime _hireDate, String _phoneNumber, int _licenses) {
        return employeeService.registerDriver(_employeeId, _firstName, _lastName, _password, _accountNumber, _branchBankNumber, _salary, _termsOfEmployment, _statusOfEmployee, _hireDate, _phoneNumber, _licenses);
    }

    public String registerEmployee(String _employeeId, String _firstName, String _lastName, String _password, int _accountNumber, int _branchBankNumber, int _salary, String _termsOfEmployment, String _statusOfEmployee, LocalDateTime _hireDate, String _phoneNumber,int type){
        return employeeService.registerEmployee(_employeeId, _firstName, _lastName, _password, _accountNumber, _branchBankNumber, _salary, _termsOfEmployment, _statusOfEmployee, _hireDate, _phoneNumber,type);
    }

    public String insertRole(String employeeId, String role) {
        return employeeService.insertRole(employeeId, role);
    }

    public String removeEmployee(String employeeId) {
        return employeeService.removeEmployee(employeeId);
    }

    public String removeConstraint(String employeeId, int constraintId) {
        return employeeService.removeConstraint(employeeId, constraintId);
    }

    public String removeRole(String employeeId, String role) {
        return employeeService.removeRole(employeeId, role);
    }

    public String isEmployeeExists(String employeeId) {
        return employeeService.isEmployeeExists(employeeId);
    }

    public String isConstraintExist(String employeeId, int constraintId) {
        return employeeService.isConstraintExist(employeeId, constraintId);
    }

    public String isRoleExist(String role) {
        return employeeService.isRoleExist(role);
    }

    public String login(String employeeId, String password) {
        return employeeService.login(employeeId, password);
    }

    public String logout(String employeeId) {
        return employeeService.logout(employeeId);
    }

    public String upgradeDriverLicense(String driverId, int license) {
        return employeeService.upgradeDriverLicense(driverId, license);
    }

    public String addDriverQualification(String driverId, String qualification) {
        return employeeService.addDriverQualification(driverId, qualification);
    }

    public String removeDriverQualification(String driverId, String qualification) {
        return employeeService.removeDriverQualification(driverId, qualification);
    }

    public String hasDriverQualification(String driverId, String qualification) {
        return employeeService.hasDriverQualification(driverId, qualification);
    }

    public String addDriverDeliveryDate(String driverId, LocalDate deliveryDate) {
        return employeeService.addDriverDeliveryDate(driverId, deliveryDate);
    }

    public String removeDriverDeliveryDate(String driverId, LocalDate deliveryDate) {
        return employeeService.removeDriverDeliveryDate(driverId, deliveryDate);
    }

    public String showDriver(String driverId) {
        return employeeService.showDriver(driverId);
    }

    public String showAllDrivers() {
        return employeeService.showAllDrivers();
    }

    public String showEmployee(String employeeId) {
        return employeeService.showEmployee(employeeId);
    }

    public String isBranchEmployeeV(String employeeId){ return employeeService.isBranchEmployeeV(employeeId); }

    public String isDriverV(String employeeId){ return employeeService.isDriverV(employeeId);}

    //endregion

    //region Branch service
    public List<String> getBranchesList(){ return branchService.getBranchesList(); }

    public String showBranch(String address) {
        return branchService.showBranch(address);
    }

    public String showAllBranches() {
        return branchService.showAllBranches();
    }

    public String showNamesOfBranches() {
        return branchService.showNamesOfBranches();
    }

    public String showBranchMorningShiftHours(String branchName) {
        return branchService.showBranchMorningShiftHours(branchName);
    }

    public String showBranchEveningShiftHours(String branchName) {
        return branchService.showBranchEveningShiftHours(branchName);
    }

    public String setBranchName(String branchName, String _branchName) {
        return branchService.setBranchName(branchName, _branchName);
    }

    public String changeStartHourOfBranch(String branchName, String shiftType, LocalTime shiftStartHour) {
        return branchService.changeStartHourOfBranch(branchName, shiftType, shiftStartHour);
    }

    public String changeEndHourOfBranch(String branchName, String shiftType, LocalTime shiftEndHour) {
        return branchService.changeEndHourOfBranch(branchName, shiftType, shiftEndHour);
    }

    public String addBranch(String branchName, String phoneNumber, String contactName, int region, int type, LocalTime morningShiftStartHour, LocalTime eveningShiftStartHour, LocalTime morningShiftEndHour, LocalTime eveningShiftEndHour, double latitude, double longitude) {
        return branchService.addBranch(branchName, phoneNumber, contactName, region, type, morningShiftStartHour, eveningShiftStartHour, morningShiftEndHour, eveningShiftEndHour, latitude, longitude);
    }

    public String insertTimeOfShift(String branchName, String day, String type) {
        return branchService.insertTimeOfShift(branchName, day, type);
    }

    public String removeBranch(String branchName) {
        return branchService.removeBranch(branchName);
    }

    public String removeTimeOfShift(String branchName, String day, String type) {
        return branchService.removeTimeOfShift(branchName, day, type);
    }

    public String isBranchExists(String branchName) {
        return branchService.isBranchExists(branchName);
    }
    //endregion

    //region Shift service
    public String showShift(int shiftId) {
        return shiftService.showShift(shiftId);
    }

    public String getSchedules(int shiftId) {
        return shiftService.getSchedules(shiftId);
    }

    public String getPayment(String employeeId, int bonus, int numberOfHours) {
        return shiftService.getPayment(employeeId, bonus, numberOfHours);
    }

    public String getDriverCounts(int _shiftId) {
        return shiftService.getDriverCounts(_shiftId);
    }

    public String showRoleCounts(int shiftId, String role) {
        return shiftService.showRoleCounts(shiftId, role);
    }

    public String showProductCancellation(int shiftId) {
        return shiftService.showProductCancellation(shiftId);
    }

    public String showFutureBranchShifts() {
        return shiftService.showFutureBranchShifts();
    }

    public String showBranchShifts() {
        return shiftService.showBranchShifts();
    }

    public String showFutureDriversShifts() {
        return shiftService.showFutureDriversShifts();
    }

    public String showDriversShifts() {
        return shiftService.showDriversShifts();
    }

    public String showFutureShifts() {
        return shiftService.showFutureShifts();
    }

    public String shiftsDriverScheduledTo(String employeeId) {
        return shiftService.shiftsDriverScheduledTo(employeeId);
    }

    public String shiftsDriverNotScheduledTo(String employeeId) {
        return shiftService.shiftsDriverNotScheduledTo(employeeId);
    }

    public String showEmployeeHisFutureShifts(String employeeId) {
        return shiftService.showEmployeeHisFutureShifts(employeeId);
    }

    public String showWhoWasChosen(int shiftId) {
        return shiftService.showWhoWasChosen(shiftId);
    }

    public String showFutureShifts(String employeeId) {
        return shiftService.showFutureShifts(employeeId);
    }

    public String showBranchEmployeeHisPastShifts(String employeeId) {
        return shiftService.showBranchEmployeeHisPastShifts(employeeId);
    }

    public String setDate(int shiftId, LocalDateTime date) {
        return shiftService.setDate(shiftId, date);
    }

    public String setBranch(int shiftId, String branchName) {
        return shiftService.setBranch(shiftId, branchName);
    }

    public String addBranchShift(LocalDateTime date, int storeKeepersCount, int cashiersCount, int shiftManagersCount, int generalEmployeesCount, int guardsCount, int cleanersCount, int merchandisersCount, String branch, String type) {
        return shiftService.addBranchShift(date, storeKeepersCount, cashiersCount, shiftManagersCount, generalEmployeesCount, guardsCount, cleanersCount, merchandisersCount, branch, type);
    }


    public String addCancellation(int shiftId, String productId, String employeeId) {
        return shiftService.addCancellation(shiftId, productId, employeeId);
    }

    public String addConstraint(String employeeId, int shiftId, String description) {
        return shiftService.addConstraint(employeeId, shiftId, description);
    }

    public String addEmployeeToSchedule(String employeeId, int shiftId) {
        return shiftService.addEmployeeToSchedule(employeeId, shiftId);
    }

    public String addDriverShift(LocalDateTime date, int driversCount) {
        return shiftService.addDriverShift(date, driversCount);
    }

    public String addDriverSchedule(String employeeId, int shiftId) {
        return shiftService.addDriverSchedule(employeeId, shiftId);
    }

    public String changeDriverSchedule(String employeeId, int shiftIdOld, int shiftIdNew) {
        return shiftService.changeDriverSchedule(employeeId, shiftIdOld, shiftIdNew);
    }

    public String removeShift(int shiftId) {
        return shiftService.removeShift(shiftId);
    }

    public String removeSchedule(String employeeId, int shiftId) {
        return shiftService.removeSchedule(employeeId, shiftId);
    }

    public String removeCancellation(int _shiftId, String _productId, String _employeeId) {
        return shiftService.removeCancellation(_shiftId, _productId, _employeeId);
    }

    public String changeStartHourOfShift(int shiftId, LocalTime newHour) {
        return shiftService.changeStartHourOfShift(shiftId, newHour);
    }

    public String changeScheduleByRole(String employeeId, int shiftId, String role) {
        return shiftService.changeScheduleByRole(employeeId, shiftId, role);
    }

    public String editConstraintShift(String employeeId, int constraintId, int shiftId) {
        return shiftService.editConstraintShift(employeeId, constraintId, shiftId);
    }

    public String isValidDate(int year, int month, int day) {
        return shiftService.isValidDate(year, month, day);
    }

    public String isValidTime(int hour, int minute) {
        return shiftService.isValidTime(hour, minute);
    }

    public String isFutureShifts(int shiftId) {
        return shiftService.isFutureShifts(shiftId);
    }

    public String scheduleEmployeeToRole(String employeeId, int shiftId, String role) {
        return shiftService.scheduleEmployeeToRole(employeeId, shiftId, role);
    }

    public String availableEmployeesNamesBE(int shiftId) {
        return shiftService.availableEmployeesNamesBE(shiftId);
    }

    public String availableEmployeesNamesD(int shiftId) {
        return shiftService.availableEmployeesNamesD(shiftId);
    }

    public String alertAboutShifts() {
        return shiftService.alertAboutShifts();
    }

    public String isEmployeeAvailable(int shiftId, String employeeId) {
        return shiftService.isEmployeeAvailable(shiftId, employeeId);
    }

    public String isShiftManagerAndStoreKeeper(String employeeId, int shiftId) {
        return shiftService.isShiftManagerAndStoreKeeper(employeeId, shiftId);
    }

    public String showAllBranchShifts() {
        return shiftService.showAllBranchShifts();
    }

    public List<String> getDriverShifts(){ return shiftService.getDriverShifts();}

    public List<String> getBranchShifts(){
        return shiftService.getBranchShifts();
    }

    public List<String> showEmployeeHisFutureShiftsList(String employeeId){ return shiftService.showEmployeeHisFutureShiftsList(employeeId); }

    public List<String> showEmployeeHisShiftsList(String employeeId){ return shiftService.showEmployeeHisShiftsList(employeeId); }

    public List<String> showAllConstraintToEmployeeList(String employeeId) { return shiftService.showAllConstraintToEmployeeList(employeeId); }

    public String showBranchShift(int shiftId){ return shiftService.showBranchShift(shiftId); }

    public String showDriverShift(int shiftId){ return shiftService.showDriverShift(shiftId); }

    public List<String> getShiftIds(){ return shiftService.getShiftIds(); }

    public String isBranchShift(String shiftId){
        return shiftService.isBranchShift(shiftId);
    }

    public String isDriverShift(String shiftId){
        return shiftService.isDriverShift(shiftId);
    }

    //endregion
}
