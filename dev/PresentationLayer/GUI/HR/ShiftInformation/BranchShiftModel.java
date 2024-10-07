package PresentationLayer.GUI.HR.ShiftInformation;

import BusinessLayer.Human_Resources.ProductCancellation;
import ServiceLayer.JsonService.JsonConverter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

public class BranchShiftModel {
    public int shiftId;
    @JsonDeserialize(using = JsonConverter.LocalDateTimeDeserializer.class)
    @JsonSerialize(using = JsonConverter.LocalDateTimeSerializer.class)
    public LocalDateTime date;
    public HashMap<String, Integer> roleCounts;
    public HashMap<String, Integer> scheduleToRoleCount;
    public List<ProductCancellationModel> cancellations;
    public HashMap<String, String> schedules;
    public String branch;
    public String typeBS;
    public String type;
    public List<String> scheduleDrivers;
    public int driversCount;
    public BranchShiftModel(@JsonProperty("shiftId") int shiftId,@JsonProperty("date") LocalDateTime date,@JsonProperty("roleCounts") HashMap<String, Integer> roleCounts,@JsonProperty("scheduleToRoleCount") HashMap<String, Integer> scheduleToRoleCount,
                            @JsonProperty("cancellations") List<ProductCancellationModel> cancellations,@JsonProperty("schedules") HashMap<String, String> schedules,@JsonProperty("branch") String branch,@JsonProperty("typeBS") String typeBS,
                            @JsonProperty("type") String type,@JsonProperty("scheduleDrivers") List<String> scheduleDrivers, @JsonProperty("driversCount") int driversCount){
        this.shiftId=shiftId;
        this.date=date;
        this.roleCounts=roleCounts;
        this.scheduleToRoleCount=scheduleToRoleCount;
        this.cancellations=cancellations;
        this.schedules=schedules;
        this.branch=branch;
        this.typeBS=typeBS;
        this.type=type;
        this.scheduleDrivers=scheduleDrivers;
        this.driversCount=driversCount;
    }

}
