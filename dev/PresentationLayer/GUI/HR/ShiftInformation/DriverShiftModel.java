package PresentationLayer.GUI.HR.ShiftInformation;

import ServiceLayer.JsonService.JsonConverter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

public class DriverShiftModel {
    public int shiftId;
    @JsonDeserialize(using = JsonConverter.LocalDateTimeDeserializer.class)
    @JsonSerialize(using = JsonConverter.LocalDateTimeSerializer.class)
    public LocalDateTime date;
    public List<String> scheduleDrivers;
    public int driversCount;
    public String type;
    public HashMap<String, Integer> roleCounts;
    public String typeBS;
    public String branch;
    public List<ProductCancellationModel> cancellations;
    public HashMap<String, Integer> scheduleToRoleCount;
    public HashMap<String, String> schedules;
    public DriverShiftModel(@JsonProperty("shiftId") int shiftId,@JsonProperty("date") LocalDateTime date,@JsonProperty("scheduleDrivers") List<String> scheduleDrivers,
                            @JsonProperty("driversCount") int driversCount,@JsonProperty("type") String type,@JsonProperty("roleCounts") HashMap<String, Integer> roleCounts,
                            @JsonProperty("typeBS") String typeBS,@JsonProperty("branch") String branch,@JsonProperty("cancellations") List<ProductCancellationModel> cancellations,
                            @JsonProperty("scheduleToRoleCount") HashMap<String, Integer> scheduleToRoleCount,@JsonProperty("schedules") HashMap<String, String> schedules){
        this.shiftId=shiftId;
        this.date=date;
        this.scheduleDrivers=scheduleDrivers;
        this.driversCount=driversCount;
        this.type=type;
        this.roleCounts=roleCounts;
        this.typeBS=typeBS;
        this.branch=branch;
        this.cancellations=cancellations;
        this.scheduleToRoleCount=scheduleToRoleCount;
        this.schedules=schedules;
    }
}
