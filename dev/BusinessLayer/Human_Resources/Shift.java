package BusinessLayer.Human_Resources;

import ServiceLayer.JsonService.JsonConverter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;


public abstract class Shift {
    protected int shiftId;
    @JsonDeserialize(using = JsonConverter.LocalDateTimeDeserializer.class)
    @JsonSerialize(using = JsonConverter.LocalDateTimeSerializer.class)
    protected LocalDateTime date;

    public Shift(int _shiftId, LocalDateTime _date) {
        shiftId = _shiftId;
        date = _date;
    }

    public int getShiftId() {
        return shiftId;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime _date) {
        date = _date;
    }

    public abstract String getType();

    public abstract String getBranch();

    public abstract void setBranch(String branch);

    public abstract HashMap<String, Integer> getRoleCounts();

    public abstract HashMap<String, String> getSchedules();

    public abstract HashMap<String, Integer> getScheduleToRoleCount();

    public abstract List<ProductCancellation> getCancellations();

    public abstract void addCancellation(String productId, String employeeId);

    public abstract void addEmployeeToSchedule(String employeeId);

    public abstract void removeSchedule(String employeeId);

    public abstract void removeCancellation(String _productId, String _employeeId);

    public abstract void changeHour(LocalTime newHour);

    public abstract void changeSchedule(String employeeId, String _role) throws Exception;

    public abstract void addSchedule(String employeeId, String _role) throws Exception;

    public abstract List<String> getScheduleDrivers();

    public abstract int getDriversCount();

    public abstract String getTypeBS();
}
