package DataAccessLayer.Delivery_DAO;

import BusinessLayer.Delivery.ItemOrder;
import DataAccessLayer.AbstractDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;

public class ItemOrderDAO extends AbstractDAO {

    private static ItemOrderDAO instance;
    private final HashMap<Identifier, ItemOrder> identityMap;

    private ItemOrderDAO() {
        identityMap = new HashMap<>();
    }

    public static ItemOrderDAO getInstance() throws SQLException {
        if (instance == null)
            instance = new ItemOrderDAO();
        return instance;
    }

    public static void deleteInstance() {
        if (instance != null)
            instance = null;
    }

    public ItemOrder getItemOrder(int itemOrderId) {
        for (Identifier i : identityMap.keySet()) {
            if (i.same(itemOrderId))
                return identityMap.get(i);
        }
        String query = "SELECT * FROM ItemOrders WHERE Id=" + itemOrderId;
        try {
            connect();
            ItemOrder itemOrder = null;
            Statement statement = connection.createStatement();
            ResultSet set = statement.executeQuery(query);
            while (set.next()) {
                int itmOrderId = set.getInt("Id");
                int itmOrderItemId = set.getInt("ItemId");
                int itmDeliveryOrderId = set.getInt("DeliveryOrderId");
                String itmOrderSource = set.getString("Source");
                String itmOrderDestination = set.getString("Destination");
                int itmOrderQuantity = set.getInt("Quantity");
                int itmOrderRefrigeration = set.getInt("Refrigeration");
                int itmOrderStatus = set.getInt("Status");
                int itmOrderItemDelivery1Id = set.getInt("ItemDelivery1Id");
                int itmOrderItemDelivery2Id = set.getInt("ItemDelivery2Id");
                itemOrder = new ItemOrder(itmOrderId, itmOrderItemId, itmDeliveryOrderId, itmOrderSource, itmOrderDestination, itmOrderQuantity, itmOrderRefrigeration == 1, itmOrderStatus, itmOrderItemDelivery1Id, itmOrderItemDelivery2Id);
                Identifier identifier = new Identifier(itemOrderId);
                identityMap.put(identifier, itemOrder);
            }
            return itemOrder;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            disconnect();
        }
    }

    public ItemOrder getItemOrderV1(int itemOrderId) {
        for (Identifier i : identityMap.keySet()) {
            if (i.itemOrderId == itemOrderId)
                return identityMap.get(i);
        }
        throw new NoSuchElementException("DAL - item order doesn't found");
    }

    public HashMap<Integer, ItemOrder> getItemOrders(int deliveryOrderId) {
        String query = "SELECT * FROM ItemOrders WHERE DeliveryOrderId=" + deliveryOrderId;
        HashMap<Integer, ItemOrder> items = new HashMap<>();
        try {
            connect();
            ItemOrder itemOrder;
            Statement statement = connection.createStatement();
            ResultSet set = statement.executeQuery(query);
            while (set.next()) {
                int itmOrderId = set.getInt("Id");
                int itmOrderItemId = set.getInt("ItemId");
                int itmDeliveryOrderId = set.getInt("DeliveryOrderId");
                String itmOrderSource = set.getString("Source");
                String itmOrderDestination = set.getString("Destination");
                int itmOrderQuantity = set.getInt("Quantity");
                int itmOrderRefrigeration = set.getInt("Refrigeration");
                int itmOrderStatus = set.getInt("Status");
                int itmOrderItemDelivery1Id = set.getInt("ItemDelivery1Id");
                int itmOrderItemDelivery2Id = set.getInt("ItemDelivery2Id");
                itemOrder = new ItemOrder(itmOrderId, itmOrderItemId, itmDeliveryOrderId, itmOrderSource, itmOrderDestination, itmOrderQuantity, itmOrderRefrigeration == 1, itmOrderStatus, itmOrderItemDelivery1Id, itmOrderItemDelivery2Id);
                items.put(itemOrder.getId(), itemOrder);
            }
            return items;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            disconnect();
        }
    }

    public HashMap<Integer, ItemOrder> getItemOrdersV1(int deliveryOrderId) {
        HashMap<Integer, ItemOrder> items = new HashMap<>();
        for (Identifier i : identityMap.keySet()) {
            if (identityMap.get(i).getDeliveryOrderId() == deliveryOrderId)
                items.put(i.itemOrderId, identityMap.get(i));
        }
        return items;
    }

    public void insert(int id, int deliveryOrderId, int itemId, String source, String destination, int quantity, boolean refrigeration, int status, int itemDelivery1, int itemDelivery2) {
        String query = "INSERT INTO ItemOrders VALUES(" + id + "," + deliveryOrderId + "," + itemId + ",'" + source + "','" + destination + "'," + quantity + "," + Boolean.compare(refrigeration, false) + "," + status + "," + itemDelivery1 + "," + itemDelivery2 + ")";
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
        String query = "UPDATE ItemOrders SET " + column + "=" + val + " WHERE Id = " + id;
        if (textColumns.contains(column)) query = "UPDATE ItemOrders SET " + column + "='" + val + "' WHERE Id = " + id;
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

    public void delete(int itemOrderId) {
        try {
            connect();
            String query = "DELETE from ItemOrders WHERE Id=" + itemOrderId;
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

    public void deleteOrder(int deliveryOrderId) {
        try {
            connect();
            String query = "DELETE from ItemOrders WHERE DeliveryOrderId=" + deliveryOrderId;
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


    public HashMap<Integer, ItemOrder> findAllItemOrders() {
        HashMap<Integer, ItemOrder> items = new HashMap<>();
        String query = "SELECT * FROM ItemOrders";
        try {
            connect();
            ItemOrder itemOrder;
            Statement statement = connection.createStatement();
            ResultSet set = statement.executeQuery(query);
            while (set.next()) {
                int itmOrderId = set.getInt("Id");
                int itmOrderItemId = set.getInt("ItemId");
                int itmDeliveryOrderId = set.getInt("DeliveryOrderId");
                String itmOrderSource = set.getString("Source");
                String itmOrderDestination = set.getString("Destination");
                int itmOrderQuantity = set.getInt("Quantity");
                int itmOrderRefrigeration = set.getInt("Refrigeration");
                int itmOrderStatus = set.getInt("Status");
                int itmOrderItemDelivery1Id = set.getInt("ItemDelivery1Id");
                int itmOrderItemDelivery2Id = set.getInt("ItemDelivery2Id");
                itemOrder = new ItemOrder(itmOrderId, itmOrderItemId, itmDeliveryOrderId, itmOrderSource, itmOrderDestination, itmOrderQuantity, itmOrderRefrigeration == 1, itmOrderStatus, itmOrderItemDelivery1Id, itmOrderItemDelivery2Id);
                identityMap.put(new Identifier(itmOrderId), itemOrder);
                items.put(itmOrderId, itemOrder);
            }
            disconnect();
            return items;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            disconnect();
        }
    }

    public void addToMap(int id, ItemOrder i) {
        identityMap.put(new Identifier(id), i);
    }

    private static class Identifier {
        private final int itemOrderId;

        public Identifier(int itemOrderId) {
            this.itemOrderId = itemOrderId;
        }

        private boolean same(int itemOrderId) {
            return this.itemOrderId == itemOrderId;
        }
    }
}
