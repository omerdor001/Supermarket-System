package DataAccessLayer.Delivery_DAO;

import DataAccessLayer.AbstractDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedList;

public class TruckDeliveryDatesDAO extends AbstractDAO {

    private static TruckDeliveryDatesDAO instance;
    private final HashMap<Identifier, LocalDate> identityMap;

    private TruckDeliveryDatesDAO() {
        identityMap = new HashMap<>();
    }

    public static TruckDeliveryDatesDAO getInstance() throws SQLException {
        if (instance == null)
            instance = new TruckDeliveryDatesDAO();
        return instance;
    }

    public static void deleteInstance() {
        if (instance != null)
            instance = null;
    }

    public LinkedList<LocalDate> getDeliveryDates(int truckId) {
        LinkedList<LocalDate> dates = new LinkedList<>();
        String query = "SELECT Date FROM TruckDeliveryDates WHERE TruckId=" + truckId;
        try {
            connect();
            Statement statement = connection.createStatement();
            ResultSet set = statement.executeQuery(query);
            while (set.next()) {
                LocalDate date = set.getDate("date").toLocalDate();
                dates.add(date);
                identityMap.put(new Identifier(truckId, date), date);
            }
            return dates;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            disconnect();
        }
    }

    public LinkedList<LocalDate> getDeliveryDatesV1(int truckId) {
        LinkedList<LocalDate> dates = new LinkedList<>();
        for (Identifier i : identityMap.keySet()) {
            if (i.truckId == truckId)
                dates.add(i.date);
        }
        return dates;
    }

    public void insert(int id, LocalDate date) {
        String query = "INSERT INTO TruckDeliveryDates VALUES(" + id + ",'" + date + "')";
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

    public void delete(int truckId, LocalDate d) {
        try {
            connect();
            String query = "DELETE from TruckDeliveryDates WHERE TruckId=" + truckId + " AND Date=" + d;
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

    public void deleteDates(int truckId) {
        try {
            connect();
            String query = "DELETE from TruckDeliveryDates WHERE TruckId=" + truckId;
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
        String query = "SELECT * FROM TruckDeliveryDates";
        try {
            connect();
            Statement statement = connection.createStatement();
            ResultSet set = statement.executeQuery(query);
            while (set.next()) {
                int truckId = set.getInt("TruckId");
                LocalDate date = LocalDate.parse(set.getString("Date"));
                identityMap.put(new Identifier(truckId, date), date);
            }
            disconnect();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            disconnect();
        }
    }

    public void addToMap(int id, LocalDate d) {
        identityMap.put(new Identifier(id, d), d);
    }

    public void removeFromMap(int id, LocalDate d) {
        for (Identifier i : identityMap.keySet())
            if (i.same(id, d)) {
                identityMap.remove(i);
                break;
            }
    }

    private static class Identifier {
        private final int truckId;
        private final LocalDate date;

        public Identifier(int truckId, LocalDate date) {
            this.truckId = truckId;
            this.date = date;
        }

        public boolean same(int truckId, LocalDate date) {
            return (this.truckId == truckId && this.date == date);
        }
    }
}
