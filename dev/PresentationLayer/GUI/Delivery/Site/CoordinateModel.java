package PresentationLayer.GUI.Delivery.Site;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CoordinateModel {

    public double latitude;
    public double longitude;

    public CoordinateModel(@JsonProperty("latitude") double latitude, @JsonProperty("longitude") double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
