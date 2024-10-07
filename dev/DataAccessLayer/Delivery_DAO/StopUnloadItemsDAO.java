package DataAccessLayer.Delivery_DAO;

import BusinessLayer.Delivery.ItemDelivery;
import DataAccessLayer.AbstractDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.LinkedList;

public class StopUnloadItemsDAO extends AbstractDAO {

    private static StopUnloadItemsDAO instance;
    private final ItemDeliveryDAO itemDeliveryDAO;
    private final HashMap<Identifier, Integer> identityMap;

    public StopUnloadItemsDAO() throws SQLException {
        itemDeliveryDAO = ItemDeliveryDAO.getInstance();
        identityMap = new HashMap<>();
    }

    public static StopUnloadItemsDAO getInstance() throws SQLException {
        if (instance == null)
            instance = new StopUnloadItemsDAO();
        return instance;
    }

    public static void deleteInstance() {
        if (instance != null)
            instance = null;
    }

    public LinkedList<ItemDelivery> getItems(int stopId) {
        LinkedList<ItemDelivery> items = new LinkedList<>();
        String query = "SELECT ItemDelivery FROM StopUnloadItems WHERE StopId=" + stopId;
        try {
            connect();
            Statement statement = connection.createStatement();
            ResultSet set = statement.executeQuery(query);
            while (set.next()) {
                int itemDeliveryId = set.getInt("ItemDelivery");
                ItemDelivery itemDelivery = itemDeliveryDAO.getItemDelivery(itemDeliveryId);
                identityMap.put(new Identifier(stopId, itemDeliveryId), itemDeliveryId);
                items.add(itemDelivery);
            }
            return items;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            disconnect();
        }
    }

    public LinkedList<ItemDelivery> getItemsV1(int stopId) {
        LinkedList<ItemDelivery> items = new LinkedList<>();
        for (Identifier i : identityMap.keySet()) {
            if (i.stopId == stopId)
                items.add(itemDeliveryDAO.getItemDeliveryV1(i.itemDeliveryId));
        }
        return items;
    }

    public void insert(int stopId, int itemDeliveryId) {
        String query = "INSERT INTO StopUnloadItems VALUES(" + stopId + "," + itemDeliveryId + ")";
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

    public void delete(int stopId, int itemDeliveryId) {
        try {
            connect();
            String query = "DELETE from StopUnloadItems WHERE StopId=" + stopId + " AND ItemDelivery=" + itemDeliveryId;
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

    public void deleteItemDelivery(int itemDeliveryId) {
        try {
            connect();
            String query = "DELETE from StopUnloadItems WHERE ItemDelivery=" + itemDeliveryId;
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


    public void loadData() {
        String query = "SELECT * FROM StopUnloadItems";
        try {
            connect();
            Statement statement = connection.createStatement();
            ResultSet set = statement.executeQuery(query);
            while (set.next()) {
                int stopId = set.getInt("StopId");
                int itemDelivery = set.getInt("ItemDelivery");
                identityMap.put(new Identifier(stopId, itemDelivery), itemDelivery);
            }
            disconnect();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            disconnect();
        }
    }

    public void addToMap(int stopId, int itemDeliveryId) {
        identityMap.put(new Identifier(stopId, itemDeliveryId), itemDeliveryId);
    }

    public void removeFromMap(int stopId, int itemDeliveryId) {
        for (Identifier i : identityMap.keySet())
            if (i.same(stopId, itemDeliveryId)) {
                identityMap.remove(i);
                break;
            }
    }

    private static class Identifier {
        private final int stopId;
        private final int itemDeliveryId;

        public Identifier(int stopId, int itemDeliveryId) {
            this.stopId = stopId;
            this.itemDeliveryId = itemDeliveryId;
        }

        public boolean same(int stopId, int itemDeliveryId) {
            return (this.itemDeliveryId == itemDeliveryId && this.stopId == stopId);
        }
    }
}
