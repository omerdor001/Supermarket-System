package BusinessLayer.Human_Resources;

import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;

import BusinessLayer.Delivery.Site;
import ServiceLayer.JsonService.JsonConverter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class Branch extends Site {
    private final List<String> timeOfShifts;

    @JsonDeserialize(using = JsonConverter.LocalTimeDeserializer.class)
    @JsonSerialize(using = JsonConverter.LocalTimeSerializer.class)
    private LocalTime morningShiftStartHour;

    @JsonDeserialize(using = JsonConverter.LocalTimeDeserializer.class)
    @JsonSerialize(using = JsonConverter.LocalTimeSerializer.class)
    private LocalTime eveningShiftStartHour;

    @JsonDeserialize(using = JsonConverter.LocalTimeDeserializer.class)
    @JsonSerialize(using = JsonConverter.LocalTimeSerializer.class)
    private LocalTime morningShiftEndHour;

    @JsonDeserialize(using = JsonConverter.LocalTimeDeserializer.class)
    @JsonSerialize(using = JsonConverter.LocalTimeSerializer.class)
    private LocalTime eveningShiftEndHour;

    public Branch(String address, String phoneNumber, String contactName, int region, int type, LocalTime _morningShiftStartHour, LocalTime _eveningShiftStartHour, LocalTime _morningShiftEndHour, LocalTime _eveningShiftEndHour, double latitude, double longitude) {
        super(address, phoneNumber, contactName, region, type, latitude, longitude);
        morningShiftStartHour = _morningShiftStartHour;
        eveningShiftStartHour = _eveningShiftStartHour;
        morningShiftEndHour = _morningShiftEndHour;
        eveningShiftEndHour = _eveningShiftEndHour;
        timeOfShifts = new LinkedList<>();
    }

    public Branch(String address, String phoneNumber, String contactName, int region, int type, LocalTime _morningShiftStartHour, LocalTime _eveningShiftStartHour, LocalTime _morningShiftEndHour, LocalTime _eveningShiftEndHour, double latitude, double longitude, List<String> _timeOfShifts) {
        super(address, phoneNumber, contactName, region, type, latitude, longitude);
        morningShiftStartHour = _morningShiftStartHour;
        eveningShiftStartHour = _eveningShiftStartHour;
        morningShiftEndHour = _morningShiftEndHour;
        eveningShiftEndHour = _eveningShiftEndHour;
        timeOfShifts=_timeOfShifts;
    }

    public LocalTime getEveningShiftEndHour() {
        return eveningShiftEndHour;
    }

    public void setEveningShiftEndHour(LocalTime _eveningShiftEndHour) {
        eveningShiftEndHour = _eveningShiftEndHour;
    }

    public LocalTime getEveningShiftStartHour() {
        return eveningShiftStartHour;
    }

    public void setEveningShiftStartHour(LocalTime _eveningShiftStartHour) {
        eveningShiftStartHour = _eveningShiftStartHour;
    }

    public List<String> getTimeOfShifts() {
        return timeOfShifts;
    }

    public LocalTime getMorningShiftStartHour() {
        return morningShiftStartHour;
    }

    public void setMorningShiftStartHour(LocalTime _morningShiftStartHour) {
        morningShiftStartHour = _morningShiftStartHour;
    }

    public LocalTime getMorningShiftEndHour() {
        return morningShiftEndHour;
    }

    public void setMorningShiftEndHour(LocalTime _morningShiftEndHour) {
        morningShiftEndHour = _morningShiftEndHour;
    }

    public void addShiftTime(String day, String type) throws Exception {
        if (timeOfShifts.size() == 14) throw new Exception("All shift times already existed");
        String day_ = day.toUpperCase();
        String type_ = type.toUpperCase();
        if (!day_.equals("SUNDAY") && !day_.equals("MONDAY") && !day_.equals("TUESDAY") && !day_.equals("WEDNESDAY") && !day_.equals("THURSDAY") && !day_.equals("FRIDAY") && !day_.equals("SATURDAY"))
            throw new Exception("String is not day");
        if (!type_.equals("M") && !type_.equals("E")) throw new Exception("String is not shift type");
        if (timeOfShifts.contains(day_ + " " + type_)) throw new Exception("Shift time already exist");
        timeOfShifts.add(day_ + " " + type_);
    }

    public void deleteShiftTime(String day, String type) throws Exception {
        if (timeOfShifts.size() == 0) throw new Exception("Empty list of shift times");
        if (!timeOfShifts.contains(day + " " + type))
            throw new Exception(day + " " + type + " does not in shift time's list");
        timeOfShifts.remove(day + " " + type);
    }
}
