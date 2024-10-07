package DataAccessLayer.Delivery_DAO;

import BusinessLayer.Delivery.Delivery;
import BusinessLayer.Delivery.Site;
import BusinessLayer.Delivery.Stop;
import DataAccessLayer.AbstractDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class DeliveryDAO extends AbstractDAO {

    private static DeliveryDAO instance;
    private final StopDAO stop;
    private final SiteDAO site;
    private final HashMap<Identifier, Delivery> identityMap;

    private DeliveryDAO() throws SQLException {
        stop = StopDAO.getInstance();
        site = SiteDAO.getInstance();
        identityMap = new HashMap<>();
    }

    public static DeliveryDAO getInstance() throws SQLException {
        if (instance == null)
            instance = new DeliveryDAO();
        return instance;
    }

    public static void deleteInstance() {
        if (instance != null)
            instance = null;
    }

    public void insert(int id, LocalDate date, LocalTime time, String source, int truckId, String driverId, float startingWeight, int status, float maxWeight) {
        String query = "INSERT INTO Deliveries VALUES(" + id + ",'" + date + "','" + time + "','" + source + "'," + truckId + ",'" + driverId + "'," + startingWeight + "," + status + "," + maxWeight + ")";
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
        List<String> textColumns = Arrays.asList("Date", "Time", "Source");
        String query = "UPDATE Deliveries SET " + column + "=" + val + " WHERE Id = " + id;
        if (textColumns.contains(column)) query = "UPDATE Deliveries SET " + column + "='" + val + "' WHERE Id = " + id;
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

    public void addToMap(int id, Delivery d) {
        identityMap.put(new Identifier(id), d);
    }


    public HashMap<Integer, Delivery> findAllDeliveries() {
        HashMap<Integer, Delivery> deliveries = new HashMap<>();
        String query = "SELECT * FROM Deliveries";
        try {
            stop.loadData();
            connect();
            Delivery delivery;
            Statement statement = connection.createStatement();
            ResultSet set = statement.executeQuery(query);
            while (set.next()) {
                int dlvId = set.getInt("Id");
                LocalDate dlvDate = LocalDate.parse(set.getString("Date"));
                LocalTime dlvTime = LocalTime.parse(set.getString("Time"));
                String dlvSource = set.getString("Source");
                int dlvTruckId = set.getInt("TruckId");
                String dlvDriverId = set.getString("DriverId");
                float dlvStartingWeight = set.getFloat("StartingWeight");
                int dlvOrderStatus = set.getInt("Status");
                float dlvMaxWeight = set.getFloat("MaxWeight");
                LinkedList<Stop> stops = stop.getStopsV1(dlvId);
                Site delvSource = site.getSiteV1(dlvSource);
                delivery = new Delivery(dlvId, dlvDate, dlvTime, delvSource, dlvTruckId, dlvDriverId, dlvStartingWeight, dlvOrderStatus, dlvMaxWeight, stops);
                Identifier identifier = new Identifier(dlvId);
                identityMap.put(identifier, delivery);
                deliveries.put(dlvId, delivery);
            }
            return deliveries;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            disconnect();
        }
    }

    private static class Identifier {
        private final int deliveryId;

        public Identifier(int deliveryId) {
            this.deliveryId = deliveryId;
        }

        private boolean same(int deliveryId) {
            return this.deliveryId == deliveryId;
        }
    }
}
