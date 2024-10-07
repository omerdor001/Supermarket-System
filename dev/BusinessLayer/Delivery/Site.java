package BusinessLayer.Delivery;

public class Site {
    private final Region region;
    private final Type type;
    private String address;
    private String phoneNumber;
    private String contactName;
    private Coordinate coordinate;

    public Site(String address, String phoneNumber, String contactName, Region region, Type type, double latitude, double longitude) {
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.contactName = contactName;
        this.region = region;
        this.type = type;
        this.coordinate = new Coordinate(latitude, longitude);
    }

    public Site(String address, String phoneNumber, String contactName, int region, int type, double latitude, double longitude) {
        this(address, phoneNumber, contactName, Region.values()[region], Type.values()[type], latitude, longitude);
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(double latitude, double longitude) {
        this.coordinate = new Coordinate(latitude, longitude);
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public Region getRegion() {
        return region;
    }

    public Type getType() {
        return type;
    }

    public enum Region {NORTH, SOUTH, CENTER, GENERAL}

    public enum Type {STORE, SUPPLIER, CENTER}
}
