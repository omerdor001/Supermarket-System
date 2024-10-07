package DataAccessLayer.HR_DAO;

import BusinessLayer.Human_Resources.Shift;
import DataAccessLayer.AbstractDAO;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public class ShiftDAO extends AbstractDAO {
    private static ShiftDAO instance;
    private final HashMap<ShiftDAO.Identifier, Shift> identityMap;

    private ShiftDAO(){
        identityMap = new HashMap<>();
    }

    public static ShiftDAO getInstance() throws SQLException {
        if (instance == null)
            instance = new ShiftDAO();
        return instance;
    }

    public static void deleteInstance() {
        if (instance != null)
            instance = null;
    }

    public void insert(int _shiftId, String _date) {
        String query = "INSERT INTO Shifts VALUES(" + _shiftId + ",'" + _date + "')";
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

    public void update(int id, String column, Object val) {
        String query = "UPDATE Shifts SET " + column + "=" + val + " WHERE ShiftId = " + id;
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

    public void addToMap(int shiftId, Shift s) {
        identityMap.put(new ShiftDAO.Identifier(shiftId), s);
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
