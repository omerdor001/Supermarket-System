package ServiceLayer;

import BusinessLayer.Human_Resources.ShiftController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;

public class ShiftService {
    private final ShiftController shiftController;

    public ShiftService(ShiftController _shiftController) {
        shiftController = _shiftController;
    }

    public ShiftController getShiftController() {
        return shiftController;
    }

    public String getSchedules(int shiftId) {
        try {
            return shiftController.getSchedules(shiftId);
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String getPayment(String employeeId, int bonus, int numberOfHours) {
        try {
            return shiftController.getPayment(employeeId, bonus, numberOfHours);
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String getDriverCounts(int _shiftId) {
        try {
            return shiftController.getDriverCounts(_shiftId);
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String showRoleCounts(int shiftId, String role) {
        try {
            return "You scheduled " + shiftController.showHowMuchHRScheduled(shiftId, role);
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String showProductCancellation(int shiftId) {
        try {
            return shiftController.showProductCancellation(shiftId);
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String showFutureBranchShifts() {
        try {
            return shiftController.showFutureBranchShifts();
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String showBranchShifts() {
        try {
            return shiftController.showBranchShifts();
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String showFutureDriversShifts() {
        try {
            return shiftController.showFutureDriversShifts();
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public List<String> getDriverShifts(){
        return shiftController.getDriversShifts();
    }

    public List<String> getBranchShifts(){
        return shiftController.getBranchShiftsG();
    }

    public String showDriversShifts() {
        try {
            return shiftController.showFutureDriversShifts();
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String showFutureShifts() {
        try {
            return shiftController.showFutureShifts();
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String showFutureShifts(String employeeId) {
        try {
            return shiftController.showFutureShifts(employeeId);
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String showBranchEmployeeHisPastShifts(String employeeId) {
        try {
            return shiftController.showBranchEmployeeHisPastShifts(employeeId);
        } catch (Exception e) {
            return e.getMessage();
        }
    }


    public String shiftsDriverScheduledTo(String employeeId) {
        try {
            return shiftController.shiftsDriverScheduledTo(employeeId);
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String shiftsDriverNotScheduledTo(String employeeId) {
        try {
            return shiftController.shiftsDriverNotScheduledTo(employeeId);
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String showEmployeeHisFutureShifts(String employeeId) {
        try {
            return shiftController.showEmployeeHisShifts(employeeId);
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String showWhoWasChosen(int shiftId) {
        try {
            return shiftController.showWhoWasChosen(shiftId);
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String setDate(int shiftId, LocalDateTime date) {
        try {
            shiftController.setDate(shiftId, date);
            return "Date was changed successfully";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String setBranch(int shiftId, String branchName) {
        try {
            shiftController.setBranch(shiftId, branchName);
            return "Branch was changed successfully";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String addBranchShift(LocalDateTime date, int storeKeepersCount, int cashiersCount, int shiftManagersCount, int generalEmployeesCount, int guardsCount, int cleanersCount, int merchandisersCount, String branch, String type) {
        try {
            shiftController.addBranchShift(date, storeKeepersCount, cashiersCount, shiftManagersCount, generalEmployeesCount, guardsCount, cleanersCount, merchandisersCount, branch, type);
            return "success";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String addCancellation(int shiftId, String productId, String employeeId) {
        try {
            shiftController.addCancellation(shiftId, productId, employeeId);
            return "Cancellation of " + productId + " was added successfully";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String addConstraint(String employeeId, int shiftId, String description) {
        try {
            shiftController.insertConstraint(employeeId, shiftId, description);
            return "Constraint added successfully";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String addEmployeeToSchedule(String employeeId, int shiftId) {
        try {
            shiftController.addEmployeeToSchedule(employeeId, shiftId);
            return "Employee was added to shift successfully";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String addDriverShift(LocalDateTime date, int driversCount) {
        try {
            shiftController.addDriverShift(date, driversCount);
            return "Driver shift was added successfully";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String addDriverSchedule(String employeeId, int shiftId) {
        try {
            shiftController.addDriverSchedule(employeeId, shiftId);
            return "Driver was scheduled successfully";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String changeDriverSchedule(String employeeId, int shiftIdOld, int shiftIdNew) {
        try {
            shiftController.changeDriverSchedule(employeeId, shiftIdOld, shiftIdNew);
            return "Driver's schedule changed successfully";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String removeShift(int shiftId) {
        try {
            shiftController.removeShift(shiftId);
            return "Shift removed successfully";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String removeSchedule(String employeeId, int shiftId) {
        try {
            shiftController.removeSchedule(employeeId, shiftId);
            return "Remove schedule by role successfully";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String removeCancellation(int _shiftId, String _productId, String _employeeId) {
        try {
            return shiftController.removeCancellation(_shiftId, _productId, _employeeId);
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String changeStartHourOfShift(int shiftId, LocalTime newHour) {
        try {
            shiftController.changeStartHourOfShift(shiftId, newHour);
            return "Change hour of shift successfully";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String changeScheduleByRole(String employeeId, int shiftId, String role) {
        try {
            shiftController.changeScheduleByRole(employeeId, shiftId, role);
            return "Change schedule by role successfully";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String editConstraintShift(String employeeId, int constraintId, int shiftId) {
        try {
            shiftController.editConstraintShift(employeeId, constraintId, shiftId);
            return "Constraint has been edited successfully";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String isValidDate(int year, int month, int day) {
        try {
            shiftController.isValidDate(year, month, day);
            return "success";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String isValidTime(int hour, int minute) {
        try {
            shiftController.isValidTime(hour, minute);
            return "success";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String isFutureShifts(int shiftId) {
        try {
            return shiftController.isFutureShifts(shiftId).toString();
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String scheduleEmployeeToRole(String employeeId, int shiftId, String role) {
        try {
            shiftController.scheduleEmployeeToRole(employeeId, shiftId, role);
            return "Schedule employee to role successfully";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String availableEmployeesNamesBE(int shiftId) {
        try {
            return shiftController.availableEmployeesNamesBE(shiftId);
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String availableEmployeesNamesD(int shiftId) {
        try {
            return shiftController.availableEmployeesNamesD(shiftId);
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String alertAboutShifts() {
        try {
            return shiftController.alertAboutShifts();
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String isEmployeeAvailable(int shiftId, String employeeId) {
        try {
            return shiftController.isEmployeeAvailable(shiftId, employeeId);
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String showShift(int shiftId) {
        try {
            return shiftController.showShift(shiftId);
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String showBranchShift(int shiftId){
        try {
            return shiftController.showBranchShift(shiftId);
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String showDriverShift(int shiftId){
        try {
            return shiftController.showDriverShift(shiftId);
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String isShiftManagerAndStoreKeeper(String employeeId, int shiftId) {
        try {
            shiftController.isShiftManagerAndStoreKeeper(employeeId, shiftId);
            return "true";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String showAllBranchShifts() {
        try {
            return shiftController.showAllBranchShifts();
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public List<String> showEmployeeHisFutureShiftsList(String employeeId){ return shiftController.showEmployeeHisFutureShiftsList(employeeId); }

    public List<String> showEmployeeHisShiftsList(String employeeId){ return shiftController.showEmployeeHisShiftsList(employeeId); }

    public List<String> showAllConstraintToEmployeeList(String employeeId){
        List<String> res=new LinkedList<>();
        try{
            return shiftController.showAllConstraintToEmployeeList(employeeId);
        }
        catch (Exception e){
            res.add(e.getMessage());
            return res;
        }
    }

    public List<String> getShiftIds(){ return shiftController.getShiftIds(); }

    public String isBranchShift(String shiftId){
        return shiftController.isBranchShift(shiftId);
    }

    public String isDriverShift(String shiftId){
        return shiftController.isDriverShift(shiftId);
    }

}
