package DataAccessLayer.HR_DAO;

import DataAccessLayer.AbstractDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.LinkedList;

public class DriverScheduleDAO extends AbstractDAO {
    private static DriverScheduleDAO instance;
    private final HashMap<DriverScheduleDAO.Identifier, String> identityMap;

    private DriverScheduleDAO() {
        identityMap = new HashMap<>();
    }

    public static DriverScheduleDAO getInstance() throws SQLException {
        if (instance == null)
            instance = new DriverScheduleDAO();
        return instance;
    }

    public static void deleteInstance() {
        if (instance != null)
            instance = null;
    }

    public void insert(int _shiftId, String _employeeId) {
        String query = "INSERT INTO DriverSchedules VALUES(" + _shiftId + ",'" + _employeeId + "')";
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

    public LinkedList<String> getDriverSchedules(int _shiftId) {
        String query = "SELECT ShiftId, EmployeeId FROM DriverSchedules WHERE ShiftId=" + _shiftId;
        try {
            connect();
            LinkedList<String> schedules = new LinkedList<>();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String employeeId = rs.getString("EmployeeId");
                schedules.add(employeeId);
                DriverScheduleDAO.Identifier identifier = new DriverScheduleDAO.Identifier(_shiftId, employeeId);
                identityMap.put(identifier, employeeId);
            }
            return schedules;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            disconnect();
        }
    }

    public void delete(int _shiftId, String _employeeId) {
        try {
            connect();
            connection.setAutoCommit(false);
            String query = "DELETE from DriverSchedules WHERE ShiftId=" + _shiftId + " AND EmployeeId='" + _employeeId + "'";
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
        String query = "SELECT * FROM DriverSchedules";
        try {
            connect();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                int shiftId = rs.getInt("ShiftId");
                String employeeId = rs.getString("EmployeeId");
                identityMap.put(new DriverScheduleDAO.Identifier(shiftId, employeeId), employeeId);
            }
            disconnect();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            disconnect();
        }
    }

    public LinkedList<String> getDriverSchedulesIM(int _shiftId) {
        LinkedList<String> schedules = new LinkedList<>();
        for (DriverScheduleDAO.Identifier i : identityMap.keySet()) {
            if (i.shiftId == _shiftId)
                schedules.add(i.employeeId);
        }
        return schedules;
    }

    public void addToMap(int shiftId, String employeeId) {
        identityMap.put(new DriverScheduleDAO.Identifier(shiftId, employeeId), employeeId);
    }

    public void removeFromMap(int shiftId, String employeeId) {
        for (DriverScheduleDAO.Identifier i : identityMap.keySet())
            if (i.same(shiftId, employeeId)) {
                identityMap.remove(i);
                break;
            }
    }

    private static class Identifier {
        private final int shiftId;
        private final String employeeId;

        public Identifier(int _shiftId, String _employeeId) {
            shiftId = _shiftId;
            employeeId = _employeeId;
        }

        public boolean same(int _shiftId, String _employeeId) {
            return shiftId == _shiftId && employeeId.equals(_employeeId);
        }
    }
}
