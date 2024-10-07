package DataAccessLayer.Delivery_DAO;

import BusinessLayer.Delivery.ItemDelivery;
import BusinessLayer.Delivery.ItemOrder;
import DataAccessLayer.AbstractDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;

public class ItemDeliveryDAO extends AbstractDAO {

    private static ItemDeliveryDAO instance;
    private final ItemOrderDAO itemOrder;
    private final HashMap<Identifier, ItemDelivery> identityMap;

    private ItemDeliveryDAO() throws SQLException {
        itemOrder = ItemOrderDAO.getInstance();
        identityMap = new HashMap<>();
    }

    public static ItemDeliveryDAO getInstance() throws SQLException {
        if (instance == null)
            instance = new ItemDeliveryDAO();
        return instance;
    }

    public static void deleteInstance() {
        if (instance != null)
            instance = null;
    }

    public ItemDelivery getItemDelivery(int itemDeliveryId) {
        for (Identifier i : identityMap.keySet()) {
            if (i.same(itemDeliveryId))
                return identityMap.get(i);
        }
        String query = "SELECT * FROM ItemDeliveries WHERE Id=" + itemDeliveryId;
        try {
            connect();
            ItemDelivery itemDelivery = null;
            Statement statement = connection.createStatement();
            ResultSet set = statement.executeQuery(query);
            while (set.next()) {
                int itmDeliveryId = set.getInt("Id");
                int itmDeliveryDeliveryId = set.getInt("DeliveryId");
                int itmDeliveryItemOrderId = set.getInt("ItemOrderId");
                String itmDeliverySource = set.getString("Source");
                String itmDeliveryDestination = set.getString("Destination");
                int itmDeliveryStatus = set.getInt("Status");
                ItemOrder itmOrder = itemOrder.getItemOrder(itmDeliveryItemOrderId);
                itemDelivery = new ItemDelivery(itmDeliveryId, itmDeliveryDeliveryId, itmOrder, itmDeliverySource, itmDeliveryDestination, itmDeliveryStatus);
                Identifier identifier = new Identifier(itemDeliveryId);
                identityMap.put(identifier, itemDelivery);
            }
            return itemDelivery;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            disconnect();
        }
    }

    public ItemDelivery getItemDeliveryV1(int itemDeliveryId) {
        for (Identifier i : identityMap.keySet()) {
            if (i.same(itemDeliveryId))
                return identityMap.get(i);
        }
        throw new NoSuchElementException("DAL - item delivery doesn't found");
    }

    public void insert(int id, int deliveryId, int itemOrderId, String source, String destination, int status) {
        String query = "INSERT INTO ItemDeliveries VALUES(" + id + "," + deliveryId + "," + itemOrderId + ",'" + source + "','" + destination + "'," + status + ")";
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
        String query = "UPDATE ItemDeliveries SET " + column + "=" + val + " WHERE Id = " + id;
        if (textColumns.contains(column))
            query = "UPDATE ItemDeliveries SET " + column + "='" + val + "' WHERE Id = " + id;
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

    public void delete(int itemDeliveryId) {
        try {
            connect();
            String query = "DELETE from ItemDeliveries WHERE Id=" + itemDeliveryId;
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
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


    public HashMap<Integer, ItemDelivery> findAllItemDeliveries() {
        HashMap<Integer, ItemDelivery> items = new HashMap<>();
        String query = "SELECT * FROM ItemDeliveries";
        try {
            connect();
            ItemDelivery itemDelivery;
            Statement statement = connection.createStatement();
            ResultSet set = statement.executeQuery(query);
            while (set.next()) {
                int itmDeliveryId = set.getInt("Id");
                int itmDeliveryDeliveryId = set.getInt("DeliveryId");
                int itmDeliveryItemOrderId = set.getInt("ItemOrderId");
                String itmDeliverySource = set.getString("Source");
                String itmDeliveryDestination = set.getString("Destination");
                int itmDeliveryStatus = set.getInt("Status");
                ItemOrder itmOrder = itemOrder.getItemOrderV1(itmDeliveryItemOrderId);
                itemDelivery = new ItemDelivery(itmDeliveryId, itmDeliveryDeliveryId, itmOrder, itmDeliverySource, itmDeliveryDestination, itmDeliveryStatus);
                Identifier identifier = new Identifier(itmDeliveryId);
                identityMap.put(identifier, itemDelivery);
                items.put(itmDeliveryId, itemDelivery);
            }
            disconnect();
            return items;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            disconnect();
        }
    }

    public void addToMap(int id, ItemDelivery d) {
        identityMap.put(new Identifier(id), d);
    }

    private static class Identifier {
        private final int itemDeliveryId;

        public Identifier(int itemDeliveryId) {
            this.itemDeliveryId = itemDeliveryId;
        }

        private boolean same(int itemDeliveryId) {
            return this.itemDeliveryId == itemDeliveryId;
        }
    }
}
