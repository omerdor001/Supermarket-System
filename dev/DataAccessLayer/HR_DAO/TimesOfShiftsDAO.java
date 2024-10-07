package DataAccessLayer.HR_DAO;

import DataAccessLayer.AbstractDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class TimesOfShiftsDAO extends AbstractDAO {
    private static TimesOfShiftsDAO instance;
    private final HashMap<TimesOfShiftsDAO.Identifier, String> identityMap;

    private TimesOfShiftsDAO() {
        identityMap = new HashMap<>();
    }

    public static TimesOfShiftsDAO getInstance() throws SQLException {
        if (instance == null)
            instance = new TimesOfShiftsDAO();
        return instance;
    }

    public static void deleteInstance() {
        if (instance != null)
            instance = null;
    }

    public void insert(String _address, String _timeOfShift) {
        String query = "INSERT INTO TimesOfShifts VALUES('" + _address + "','" + _timeOfShift + "')";
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

    public List<String> getTimesOfShiftsToBranch(String _address) {
        String query = "SELECT Address,TimeOfShift from TimesOfShifts WHERE Address=" + _address;
        try {
            connect();
            List<String> timesOfShifts = new LinkedList<>();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String timeOfShift = rs.getString("TimeOfShift");
                timesOfShifts.add(timeOfShift);
                TimesOfShiftsDAO.Identifier identifier = new TimesOfShiftsDAO.Identifier(_address, timeOfShift);
                identityMap.put(identifier, timeOfShift);
            }
            return timesOfShifts;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            disconnect();
        }
    }

    public void delete(String _address, String timeOfShift) {
        try {
            connect();
            connection.setAutoCommit(false);
            String query = "DELETE from TimesOfShifts WHERE Address='" + _address + "' AND TimeOfShift='" + timeOfShift + "'";
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
        String query = "SELECT * FROM TimesOfShifts";
        try {
            connect();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                String address = rs.getString("Address");
                String timeOfShift = rs.getString("TimeOfShift");
                identityMap.put(new TimesOfShiftsDAO.Identifier(address, timeOfShift), timeOfShift);
            }
            disconnect();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            disconnect();
        }
    }

    public LinkedList<String> getTimesOfShiftsIM(String address) {
        LinkedList<String> times = new LinkedList<>();
        for (TimesOfShiftsDAO.Identifier i : identityMap.keySet()) {
            if (i.address.equals(address))
                times.add(i.timeOfShift);
        }
        return times;
    }

    public void addToMap(String address, String timeOfShift) {
        identityMap.put(new TimesOfShiftsDAO.Identifier(address, timeOfShift), timeOfShift);
    }

    public void removeFromMap(String address, String timeOfShift) {
        for (TimesOfShiftsDAO.Identifier i : identityMap.keySet())
            if (i.same(address, timeOfShift)) {
                identityMap.remove(i);
                break;
            }
    }


    private static class Identifier {
        private final String address;
        private final String timeOfShift;

        public Identifier(String _address, String _timeOfShift) {
            address = _address;
            timeOfShift = _timeOfShift;
        }

        public boolean same(String _address, String _timeOfShift) {
            return address.equals(_address) && timeOfShift.equals(_timeOfShift);
        }
    }
}
