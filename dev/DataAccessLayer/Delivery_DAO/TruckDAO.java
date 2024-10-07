package DataAccessLayer.Delivery_DAO;

import BusinessLayer.Delivery.Truck;
import DataAccessLayer.AbstractDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class TruckDAO extends AbstractDAO {

    private static TruckDAO instance;
    private final TruckDeliveryDatesDAO truckDeliveryDatesDAO;
    private final HashMap<Identifier, Truck> identityMap;

    private TruckDAO() throws SQLException {
        truckDeliveryDatesDAO = TruckDeliveryDatesDAO.getInstance();
        identityMap = new HashMap<>();
    }

    public static TruckDAO getInstance() throws SQLException {
        if (instance == null)
            instance = new TruckDAO();
        return instance;
    }

    public static void deleteInstance() {
        if (instance != null)
            instance = null;
    }

    public Truck getTruck(int truckId) {
        for (Identifier curr : identityMap.keySet()) {
            if (curr.same(truckId))
                return identityMap.get(curr);
        }
        String query = "SELECT * FROM Trucks WHERE Id=" + truckId;
        try {
            connect();
            Truck truck = null;
            Statement statement = connection.createStatement();
            ResultSet set = statement.executeQuery(query);
            while (set.next()) {
                int trkId = set.getInt("Id");
                String trkType = set.getString("Type");
                String trkModel = set.getString("Model");
                float trkNetWeight = set.getInt("NetWeight");
                float trkMaxWeight = set.getInt("MaxWeight");
                int trkStatus = set.getInt("Status");
                LinkedList<LocalDate> deliveryDates = truckDeliveryDatesDAO.getDeliveryDates(truckId);
                truck = new Truck(trkId, trkModel, trkType, trkNetWeight, trkMaxWeight, deliveryDates, trkStatus);
                Identifier identifier = new Identifier(truckId);
                identityMap.put(identifier, truck);
            }
            return truck;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            disconnect();
        }
    }

    public void insert(int id, String type, String model, float netWeight, float maxWeight, int status) {
        String query = "INSERT INTO Trucks VALUES(" + id + ",'" + type + "','" + model + "'," + netWeight + "," + maxWeight + "," + status + ")";
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
        String query = "UPDATE Trucks SET " + column + "=" + val + " WHERE Id = " + id;
        if (textColumns.contains(column)) query = "UPDATE Trucks SET " + column + "='" + val + "' WHERE Id = " + id;
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

    public void delete(int truckId) {
        try {
            connect();
            String query = "DELETE from Trucks WHERE Id=" + truckId;
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            connection.commit();
            truckDeliveryDatesDAO.deleteDates(truckId);
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


    public HashMap<Integer, Truck> findAllTrucks() {
        HashMap<Integer, Truck> trucks = new HashMap<>();
        String query = "SELECT * FROM Trucks";
        try {
            truckDeliveryDatesDAO.loadData();
            connect();
            Truck truck;
            Statement statement = connection.createStatement();
            ResultSet set = statement.executeQuery(query);
            while (set.next()) {
                int trkId = set.getInt("Id");
                String trkType = set.getString("Type");
                String trkModel = set.getString("Model");
                float trkNetWeight = set.getInt("NetWeight");
                float trkMaxWeight = set.getInt("MaxWeight");
                int trkStatus = set.getInt("Status");
                LinkedList<LocalDate> deliveryDates = truckDeliveryDatesDAO.getDeliveryDatesV1(trkId);
                truck = new Truck(trkId, trkModel, trkType, trkNetWeight, trkMaxWeight, deliveryDates, trkStatus);
                Identifier identifier = new Identifier(trkId);
                identityMap.put(identifier, truck);
                trucks.put(trkId, truck);
            }
            disconnect();
            return trucks;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            disconnect();
        }
    }

    public void addToMap(int id, Truck t) {
        identityMap.put(new Identifier(id), t);
    }

    private static class Identifier {
        private final int truckId;

        public Identifier(int truckId) {
            this.truckId = truckId;
        }

        private boolean same(int truckId) {
            return this.truckId == truckId;
        }
    }
}