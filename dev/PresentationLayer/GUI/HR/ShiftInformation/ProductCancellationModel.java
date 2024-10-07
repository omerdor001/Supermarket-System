package PresentationLayer.GUI.HR.ShiftInformation;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductCancellationModel {
    public String productId;
    public String employeeId;
    public ProductCancellationModel(@JsonProperty("productId") String productId,@JsonProperty("employeeId") String employeeId){
        this.productId=productId;
        this.employeeId=employeeId;
    }
}
