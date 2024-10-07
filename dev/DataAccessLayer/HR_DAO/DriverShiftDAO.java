package DataAccessLayer.HR_DAO;

import BusinessLayer.Human_Resources.DriverShift;
import DataAccessLayer.AbstractDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class DriverShiftDAO extends AbstractDAO {
    private static DriverShiftDAO instance;
    private final HashMap<DriverShiftDAO.Identifier, DriverShift> identityMap;
    private final DriverScheduleDAO driverScheduleDAO;

    private DriverShiftDAO() throws SQLException {
        identityMap = new HashMap<>();
        driverScheduleDAO = DriverScheduleDAO.getInstance();
    }

    public static DriverShiftDAO getInstance() throws SQLException {
        if (instance == null)
            instance = new DriverShiftDAO();
        return instance;
    }

    public static void deleteInstance() {
        if (instance != null)
            instance = null;
    }

    public void insert(int _shiftId, int _driverCount) {
        String query = "INSERT INTO DriverShifts VALUES(" + _shiftId + "," + _driverCount + ")";
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

    public DriverShift getDriverShift(int _shiftId) {
        for (DriverShiftDAO.Identifier curr : identityMap.keySet()) {
            if (curr.same(_shiftId))
                return identityMap.get(curr);
        }
        String query = "SELECT DriverShifts.ShiftId,Shifts.Date,DriverShifts.DriversCount from Shifts,DriverShifts WHERE ShiftId=" + _shiftId;
        try {
            connect();
            DriverShift driverShift = null;
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                int shiftId = rs.getInt("ShiftId");
                LocalDateTime date = LocalDateTime.parse(rs.getString("Date"));
                int driversCount = rs.getInt("DriversCount");
                LinkedList<String> driverSchedules = driverScheduleDAO.getDriverSchedules(shiftId);
                driverShift = new DriverShift(shiftId, date, driversCount, driverSchedules);
                DriverShiftDAO.Identifier identifier = new DriverShiftDAO.Identifier(shiftId);
                identityMap.put(identifier, driverShift);
            }
            return driverShift;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            disconnect();
        }
    }

    public void update(String id, String column, Object val) {
        List<String> textColumns = List.of("Date");
        String query = "UPDATE DriverShifts SET " + column + "=" + val + " WHERE EmployeeId = '" + id + "'";
        if (textColumns.contains(column))
            query = "UPDATE DriverShifts SET " + column + "='" + val + "' WHERE EmployeeId = '" + id + "'";
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

    public void addToMap(int shiftId, DriverShift ds) {
        identityMap.put(new DriverShiftDAO.Identifier(shiftId), ds);
    }


    public HashMap<Integer, DriverShift> findAllDriverShifts() {
        HashMap<Integer, DriverShift> driverShifts = new HashMap<>();
        String query = "SELECT DriverShifts.ShiftId,Shifts.Date,DriverShifts.DriversCount from Shifts,DriverShifts WHERE  DriverShifts.ShiftId=Shifts.ShiftId";
        try {
            driverScheduleDAO.loadData();
            connect();
            DriverShift driverShift;
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                int shiftId = rs.getInt("ShiftId");
                LocalDateTime date = LocalDateTime.parse(rs.getString("Date"));
                int driversCount = rs.getInt("DriversCount");
                LinkedList<String> driverSchedules = driverScheduleDAO.getDriverSchedulesIM(shiftId);
                driverShift = new DriverShift(shiftId, date, driversCount, driverSchedules);
                DriverShiftDAO.Identifier identifier = new DriverShiftDAO.Identifier(shiftId);
                identityMap.put(identifier, driverShift);
                driverShifts.put(shiftId, driverShift);
            }
            return driverShifts;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            disconnect();
        }
    }

    private static class Identifier {
        private final int shiftId;

        public Identifier(int _shiftId) {
            shiftId = _shiftId;
        }

        public boolean same(int _shiftId) {
            return shiftId == _shiftId;
        }
    }
}
