package BusinessLayer.Human_Resources;

public class ProductCancellation {
    private final String productId;
    private final String employeeId;

    public ProductCancellation(String _productId, String _employeeId) {
        productId = _productId;
        employeeId = _employeeId;
    }

    public String getProductId() {
        return productId;
    }

    public String getEmployeeId() {
        return employeeId;
    }
}
