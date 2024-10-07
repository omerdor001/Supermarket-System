package BusinessLayer.Human_Resources;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class DriverShift extends Shift {

    private final List<String> scheduleDrivers;
    private int driversCount;

    public DriverShift(int _shiftId, LocalDateTime _date, int _driversCount) {
        super(_shiftId, _date);
        scheduleDrivers = new LinkedList<>();
        driversCount = _driversCount;
    }

    public DriverShift(int _shiftId, LocalDateTime _date, int _driversCount, LinkedList<String> _scheduleDrivers) {
        super(_shiftId, _date);
        driversCount = _driversCount;
        scheduleDrivers=_scheduleDrivers;
    }

    public List<String> getScheduleDrivers() {
        return scheduleDrivers;
    }

    public void addDriverSchedule(String employeeId) {
        scheduleDrivers.add(employeeId);
    }

    public void removeScheduleDriver(String employeeId) {
        scheduleDrivers.remove(employeeId);
    }

    @Override
    public String getType() {
        return "DS";
    }

    @Override
    public String getBranch() {
        return null;
    }

    @Override
    public void setBranch(String branch) {

    }

    @Override
    public HashMap<String, Integer> getRoleCounts() {
        return null;
    }

    @Override
    public HashMap<String, String> getSchedules() {
        return null;
    }

    @Override
    public HashMap<String, Integer> getScheduleToRoleCount() {
        return null;
    }

    @Override
    public List<ProductCancellation> getCancellations() {
        return null;
    }

    @Override
    public void addCancellation(String productId, String employeeId) {

    }

    @Override
    public void addEmployeeToSchedule(String employeeId) {

    }

    @Override
    public void removeSchedule(String employeeId) {

    }

    @Override
    public void removeCancellation(String _productId, String _employeeId) {

    }

    @Override
    public void changeHour(LocalTime newHour) {

    }

    @Override
    public void changeSchedule(String employeeId, String _role) {
    }

    @Override
    public void addSchedule(String employeeId, String _role) {
    }

    public int getDriversCount() {
        return driversCount;
    }

    @Override
    public String getTypeBS() {return null;}

    public void setDriversCount(int _driversCount) {
        driversCount = driversCount;
    }
}