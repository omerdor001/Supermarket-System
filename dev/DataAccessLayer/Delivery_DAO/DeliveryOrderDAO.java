package DataAccessLayer.Delivery_DAO;

import BusinessLayer.Delivery.DeliveryOrder;
import BusinessLayer.Delivery.ItemOrder;
import DataAccessLayer.AbstractDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class DeliveryOrderDAO extends AbstractDAO {

    private static DeliveryOrderDAO instance;
    private final ItemOrderDAO itemOrder;
    private final HashMap<Identifier, DeliveryOrder> identityMap;

    private DeliveryOrderDAO() throws SQLException {
        itemOrder = ItemOrderDAO.getInstance();
        identityMap = new HashMap<>();
    }

    public static DeliveryOrderDAO getInstance() throws SQLException {
        if (instance == null)
            instance = new DeliveryOrderDAO();
        return instance;
    }

    public static void deleteInstance() {
        if (instance != null)
            instance = null;
    }

    public void insert(int id, String destination, String source, double totalLoadWeight, int status) {
        String query = "INSERT INTO DeliveryOrders VALUES(" + id + ",'" + destination + "','" + source + "'," + totalLoadWeight + "," + status + ")";
        try {
            connect();
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            connection.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            disconnect();
        }
    }

    public void update(int id, String column, Object val) {
        //TODO update identity map?
        List<String> textColumns = Arrays.asList("Destination", "Source");
        String query = "UPDATE DeliveryOrders SET " + column + "=" + val + " WHERE Id = " + id;
        if (textColumns.contains(column))
            query = "UPDATE DeliveryOrders SET " + column + "='" + val + "' WHERE Id = " + id;
        try {
            connect();
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            connection.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            disconnect();
        }
    }

    public void delete(int deliveryOrderId) {
        try {
            connect();
            String query = "DELETE from DeliveryOrders WHERE Id=" + deliveryOrderId;
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            itemOrder.deleteOrder(deliveryOrderId);
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


    public HashMap<Integer, DeliveryOrder> findAllDeliveryOrders() {
        HashMap<Integer, DeliveryOrder> deliveryOrders = new HashMap<>();
        String query = "SELECT * FROM DeliveryOrders";
        try {
            connect();
            DeliveryOrder deliveryOrder;
            Statement statement = connection.createStatement();
            ResultSet set = statement.executeQuery(query);
            while (set.next()) {
                int dlvOrderId = set.getInt("Id");
                String dlvOrderDestination = set.getString("Destination");
                String dlvOrderSource = set.getString("Source");
                double dlvOrderTotalLoadWeight = set.getDouble("TotalLoadWeight");
                int dlvOrderStatus = set.getInt("Status");
                HashMap<Integer, ItemOrder> items = itemOrder.getItemOrdersV1(dlvOrderId);
                deliveryOrder = new DeliveryOrder(dlvOrderId, dlvOrderDestination, dlvOrderSource, dlvOrderTotalLoadWeight, items, dlvOrderStatus);
                Identifier identifier = new Identifier(dlvOrderId);
                identityMap.put(identifier, deliveryOrder);
                deliveryOrders.put(dlvOrderId, deliveryOrder);
            }
            disconnect();
            return deliveryOrders;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            disconnect();
        }
    }

    public void addToMap(int id, DeliveryOrder d) {
        identityMap.put(new Identifier(id), d);
    }

    private static class Identifier {
        private final int deliveryOrderId;

        public Identifier(int deliveryOrderId) {
            this.deliveryOrderId = deliveryOrderId;
        }

        private boolean same(int deliveryOrderId) {
            return this.deliveryOrderId == deliveryOrderId;
        }
    }
}
