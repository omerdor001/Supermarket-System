package DataAccessLayer.HR_DAO;

import BusinessLayer.Human_Resources.ProductCancellation;
import DataAccessLayer.AbstractDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class ProductCancellationToShiftDAO extends AbstractDAO {
    private static ProductCancellationToShiftDAO instance;
    private final HashMap<ProductCancellationToShiftDAO.Identifier, String> identityMap;

    private ProductCancellationToShiftDAO() {
        identityMap = new HashMap<>();
    }

    public static ProductCancellationToShiftDAO getInstance() throws SQLException {
        if (instance == null)
            instance = new ProductCancellationToShiftDAO();
        return instance;
    }

    public static void deleteInstance() {
        if (instance != null)
            instance = null;
    }

    public void insert(String _productId, String _employeeId, int _shiftId) {
        String query = "INSERT INTO ProductCancellationsToShift VALUES('" + _productId + "','" + _employeeId + "'," + _shiftId + ")";
        try {
            connect();
            connection.setAutoCommit(false);
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(query);
            connection.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            disconnect();
        }
    }

    public List<ProductCancellation> getPCToShift(int shiftId) {
        String query = "SELECT ShiftId,ProductId,EmployeeId FROM ProductCancellationsToShift WHERE ShiftId=" + shiftId;
        try {
            connect();
            List<ProductCancellation> cancellations = new LinkedList<>();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String employeeId = rs.getString("EmployeeId");
                String productId = rs.getString("ProductId");
                ProductCancellation pc = new ProductCancellation(productId, employeeId);
                cancellations.add(pc);
            }
            return cancellations;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            disconnect();
        }
    }

    public void delete(String _productId, String _employeeId, int _shiftId) {
        try {
            connect();
            connection.setAutoCommit(false);
            String query = "DELETE from ProductCancellationsToShift WHERE ProductId='" + _productId + "' AND EmployeeId='" + _employeeId + "' AND ShiftId=" + _shiftId;
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(query);
            connection.commit();
        } catch (Exception e) {
            try {
                connection.rollback();
                throw new RuntimeException(e);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        } finally {
            disconnect();
        }
    }

    public void addToMap(String productId, String employeeId, int shiftId) {
        identityMap.put(new ProductCancellationToShiftDAO.Identifier(productId, employeeId, shiftId), employeeId);
    }

    public void removeFromMap(String productId, String employeeId, int shiftId) {
        for (ProductCancellationToShiftDAO.Identifier i : identityMap.keySet())
            if (i.same(productId, employeeId, shiftId)) {
                identityMap.remove(i);
                break;
            }
    }

    public void loadData() {
        String query = "SELECT * FROM ProductCancellationsToShift";
        try {
            connect();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                String productId = rs.getString("ProductId");
                String employeeId = rs.getString("EmployeeId");
                int shiftId = rs.getInt("ShiftId");
                identityMap.put(new ProductCancellationToShiftDAO.Identifier(productId, employeeId, shiftId), employeeId);
            }
            disconnect();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            disconnect();
        }
    }

    public List<ProductCancellation> getPCToShiftIM(int shiftId) {
        LinkedList<ProductCancellation> productCancellations = new LinkedList<>();
        for (ProductCancellationToShiftDAO.Identifier i : identityMap.keySet()) {
            if (i.shiftId == shiftId)
                productCancellations.add(new ProductCancellation(i.productId, i.employeeId));
        }
        return productCancellations;
    }


    private static class Identifier {
        private final String productId;
        private final String employeeId;
        private final int shiftId;

        public Identifier(String _productId, String _employeeId, int _shiftId) {
            productId = _productId;
            employeeId = _employeeId;
            shiftId = _shiftId;
        }

        public boolean same(String _productId, String _employeeId, int _shiftId) {
            return productId.equals(_productId) && employeeId.equals(_employeeId) && _shiftId == shiftId;
        }
    }
}
