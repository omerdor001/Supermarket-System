package DataAccessLayer.Delivery_DAO;

import BusinessLayer.Delivery.ItemDelivery;
import BusinessLayer.Delivery.Site;
import BusinessLayer.Delivery.Stop;
import DataAccessLayer.AbstractDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class StopDAO extends AbstractDAO {

    private static StopDAO instance;
    private final SiteDAO siteDAO;
    private final StopLoadItemsDAO loadItemsDAO;
    private final StopUnloadItemsDAO unloadItemsDAO;
    private final HashMap<Identifier, Stop> identityMap;

    private StopDAO() throws SQLException {
        siteDAO = SiteDAO.getInstance();
        loadItemsDAO = StopLoadItemsDAO.getInstance();
        unloadItemsDAO = StopUnloadItemsDAO.getInstance();
        identityMap = new HashMap<>();
    }

    public static StopDAO getInstance() throws SQLException {
        if (instance == null)
            instance = new StopDAO();
        return instance;
    }

    public static void deleteInstance() {
        if (instance != null)
            instance = null;
    }

    public Stop getStop(int stopId) {
        for (Identifier i : identityMap.keySet()) {
            if (i.same(stopId))
                return identityMap.get(i);
        }

        String query = "SELECT * FROM Stops WHERE Id=" + stopId;
        try {
            connect();
            Stop stop = null;
            Statement statement = connection.createStatement();
            ResultSet set = statement.executeQuery(query);
            while (set.next()) {
                int stpId = set.getInt("Id");
                int stpDeliveryId = set.getInt("DeliveryId");
                String stpAddress = set.getString("Destination");
                int stpStatus = set.getInt("Status");
                LocalDateTime stpArriveTime = set.getTimestamp("ArriveTime").toLocalDateTime();
                Site destination = siteDAO.getSite(stpAddress);
                LinkedList<ItemDelivery> loadItems = loadItemsDAO.getItems(stpId);
                LinkedList<ItemDelivery> unloadItems = unloadItemsDAO.getItems(stpId);
                stop = new Stop(stpId, stpDeliveryId, destination, stpStatus, stpArriveTime, loadItems, unloadItems);
                Identifier identifier = new Identifier(stpId);
                identityMap.put(identifier, stop);
            }
            return stop;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            disconnect();
        }
    }

    public LinkedList<Stop> getStops(int deliveryId) {
        String query = "SELECT * FROM Stops WHERE DeliveryId=" + deliveryId;
        try {
            connect();
            LinkedList<Stop> stops = new LinkedList<>();
            Statement statement = connection.createStatement();
            ResultSet set = statement.executeQuery(query);
            while (set.next()) {
                int stpId = set.getInt("Id");
                int stpDeliveryId = set.getInt("DeliveryId");
                String stpAddress = set.getString("Destination");
                int stpStatus = set.getInt("Status");
                LocalDateTime stpArriveTime = set.getTimestamp("ArriveTime").toLocalDateTime();
                Site destination = siteDAO.getSite(stpAddress);
                LinkedList<ItemDelivery> loadItems = loadItemsDAO.getItems(stpId);
                LinkedList<ItemDelivery> unloadItems = unloadItemsDAO.getItems(stpId);
                Stop stop = new Stop(stpId, stpDeliveryId, destination, stpStatus, stpArriveTime, loadItems, unloadItems);
                stops.add(stop);
                Identifier identifier = new Identifier(stpId);
                identityMap.put(identifier, stop);
            }
            return stops;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            disconnect();
        }
    }

    public LinkedList<Stop> getStopsV1(int deliveryId) {
        LinkedList<Stop> stops = new LinkedList<>();
        for (Identifier i : identityMap.keySet()) {
            if (identityMap.get(i).getDeliveryId() == deliveryId)
                stops.add(identityMap.get(i));
        }
        return stops;
    }

    public void insert(int id, int deliveryId, Site destination, int stopStatus, LocalDateTime arriveTime) {
        String query = "INSERT INTO Stops VALUES(" + id + "," + deliveryId + ",'" + destination.getAddress() + "'," + stopStatus + ",'" + arriveTime + "')";
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
        List<String> textColumns = Arrays.asList("Destination", "ArriveTime");
        String query = "UPDATE Stops SET " + column + "=" + val + " WHERE Id = " + id;
        if (textColumns.contains(column)) query = "UPDATE Stops SET " + column + "='" + val + "' WHERE Id = " + id;
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

//    public void delete(int stopId) {
//        try {
//            connect();
//            String query = "DELETE from Stops WHERE Id=" + stopId;
//            Statement statement = connection.createStatement();
//            statement.executeQuery(query);
//            loadItemsDAO.deleteItems(stopId);
//            unloadItemsDAO.deleteItems(stopId);
//            connection.commit();
//        } catch (Exception e) {
//            try {
//                connection.rollback();
//                throw new RuntimeException(e);
//            } catch (SQLException ex) {
//                throw new RuntimeException(ex);
//            }
//        } finally {
//            disconnect();
//        }
//    }

//    public void deleteStops(int deliveryId) {
//        try {
//            connect();
//            LinkedList<Stop> stops = getStops(deliveryId);
//            String query = "DELETE from Stops WHERE DeliveryId=" + deliveryId;
//            Statement statement = connection.createStatement();
//            statement.executeUpdate(query);
//            for (Stop s : stops) {
//                loadItemsDAO.deleteItems(s.getId());
//                unloadItemsDAO.deleteItems(s.getId());
//            }
//            connection.commit();
//        } catch (Exception e) {
//            try {
//                connection.rollback();
//                throw new RuntimeException(e);
//            } catch (SQLException ex) {
//                throw new RuntimeException(ex);
//            }
//        } finally {
//            disconnect();
//        }
//    }


    public void loadData() {
        String query = "SELECT * FROM Stops";
        try {
            loadItemsDAO.loadData();
            unloadItemsDAO.loadData();
            connect();
            Statement statement = connection.createStatement();
            ResultSet set = statement.executeQuery(query);
            while (set.next()) {
                int stpId = set.getInt("Id");
                int stpDeliveryId = set.getInt("DeliveryId");
                String stpAddress = set.getString("Destination");
                int stpStatus = set.getInt("Status");
                LocalDateTime stpArriveTime = LocalDateTime.parse(set.getString("ArriveTime"));
                Site destination = siteDAO.getSite(stpAddress);
                LinkedList<ItemDelivery> loadItems = loadItemsDAO.getItemsV1(stpId);
                LinkedList<ItemDelivery> unloadItems = unloadItemsDAO.getItemsV1(stpId);
                Stop stop = new Stop(stpId, stpDeliveryId, destination, stpStatus, stpArriveTime, loadItems, unloadItems);
                Identifier identifier = new Identifier(stpId);
                identityMap.put(identifier, stop);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            disconnect();
        }
    }

    public void addToMap(int id, Stop s) {
        identityMap.put(new Identifier(id), s);
    }

    private static class Identifier {
        private final int stopId;

        public Identifier(int stopId) {
            this.stopId = stopId;
        }

        private boolean same(int stopId) {
            return this.stopId == stopId;
        }
    }
}
