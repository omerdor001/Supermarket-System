package PresentationLayer.GUI.HR.EmployeeInformationHR;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EmployeeConstraintModel {
    public int constraintId;
    public int shiftId;
    public String description;
    public EmployeeConstraintModel(@JsonProperty("constraintId") int constraintId,@JsonProperty("shiftId") int shiftId,@JsonProperty("description") String description){
        this.constraintId=constraintId;
        this.shiftId=shiftId;
        this.description=description;
    }
}
