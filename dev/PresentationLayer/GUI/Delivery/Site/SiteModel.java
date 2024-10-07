package PresentationLayer.GUI.Delivery.Site;

import ServiceLayer.JsonService.JsonConverter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.time.LocalTime;
import java.util.List;

public class SiteModel {

    public final SiteModel.Region region;
    public final SiteModel.Type type;
    public String address;
    public String phoneNumber;
    public String contactName;
    public CoordinateModel coordinateModel;

    public List<String> timeOfShifts;
    @JsonDeserialize(using = JsonConverter.LocalTimeDeserializer.class)
    @JsonSerialize(using = JsonConverter.LocalTimeSerializer.class)
    public LocalTime morningShiftStartHour;
    @JsonDeserialize(using = JsonConverter.LocalTimeDeserializer.class)
    @JsonSerialize(using = JsonConverter.LocalTimeSerializer.class)
    public LocalTime eveningShiftStartHour;
    @JsonDeserialize(using = JsonConverter.LocalTimeDeserializer.class)
    @JsonSerialize(using = JsonConverter.LocalTimeSerializer.class)
    public LocalTime morningShiftEndHour;
    @JsonDeserialize(using = JsonConverter.LocalTimeDeserializer.class)
    @JsonSerialize(using = JsonConverter.LocalTimeSerializer.class)
    public LocalTime eveningShiftEndHour;

    public SiteModel(@JsonProperty("address") String address, @JsonProperty("phoneNumber") String phoneNumber, @JsonProperty("contactName") String contactName, @JsonProperty("region") Region region, @JsonProperty("type") Type type, @JsonProperty("coordinate") CoordinateModel coordinateModel,
                     @JsonProperty("timesOfShift") List<String> timeOfShifts, @JsonProperty("morningShiftStartHour") LocalTime morningShiftStartHour, @JsonProperty("eveningShiftStartHour") LocalTime eveningShiftStartHour, @JsonProperty("morningShiftEndHour") LocalTime morningShiftEndHour, @JsonProperty("eveningShiftEndHour") LocalTime eveningShiftEndHour) {
        this.region = region;
        this.type = type;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.contactName = contactName;
        this.coordinateModel = coordinateModel;
        this.timeOfShifts = timeOfShifts;
        this.morningShiftStartHour = morningShiftStartHour;
        this.eveningShiftStartHour = eveningShiftStartHour;
        this.morningShiftEndHour = morningShiftEndHour;
        this.eveningShiftEndHour = eveningShiftEndHour;
    }

    public enum Region {NORTH, SOUTH, CENTER, GENERAL}

    public enum Type {STORE, SUPPLIER, CENTER}
}

