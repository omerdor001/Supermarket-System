package DataAccessLayer.HR_DAO;

import BusinessLayer.Human_Resources.EmployeeConstraint;
import DataAccessLayer.AbstractDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class ConstraintToEmployeeDAO extends AbstractDAO {
    private static ConstraintToEmployeeDAO instance;
    private final HashMap<ConstraintToEmployeeDAO.Identifier, Integer> identityMap;
    private final EmployeeConstraintDAO employeeConstraintDAO;

    private ConstraintToEmployeeDAO() throws SQLException {
        identityMap = new HashMap<>();
        employeeConstraintDAO = EmployeeConstraintDAO.getInstance();
    }

    public static ConstraintToEmployeeDAO getInstance() throws SQLException {
        if (instance == null)
            instance = new ConstraintToEmployeeDAO();
        return instance;
    }

    public static void deleteInstance() {
        if (instance != null)
            instance = null;
    }

    public void insert(String _employeeId, int _constraintId) {
        String query = "INSERT INTO ConstraintsToEmployee VALUES('" + _employeeId + "'," + _constraintId + ")";
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

    public List<EmployeeConstraint> getConstraintsToEmployeeIM(String _employeeId) {
        List<EmployeeConstraint> constraints = new LinkedList<>();
        for (ConstraintToEmployeeDAO.Identifier i : identityMap.keySet()) {
            if (i.employeeId.equals(_employeeId))
                constraints.add(employeeConstraintDAO.getEmployeeConstraintIM(i.constraintId));
        }
        return constraints;
    }

    public List<EmployeeConstraint> getConstraintsToEmployee(String _employeeId) {
        String query = "SELECT EmployeeId,ConstraintId FROM ConstraintsToEmployee WHERE EmployeeId=" + _employeeId;
        try {
            employeeConstraintDAO.loadData();
            connect();
            List<EmployeeConstraint> constraints = new LinkedList<>();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                int constraintId = rs.getInt("ConstraintId");
                EmployeeConstraint ec = employeeConstraintDAO.getEmployeeConstraint(constraintId);
                constraints.add(ec);
            }
            stmt.close();
            return constraints;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            disconnect();
        }
    }

    public void delete(String _employeeId, int _constraintId) {
        try {
            connect();
            connection.setAutoCommit(false);
            String query = "DELETE FROM ConstraintsToEmployee WHERE EmployeeId='" + _employeeId + "' AND ConstraintId=" + _constraintId;
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
        employeeConstraintDAO.loadData();
        String query = "SELECT * FROM ConstraintsToEmployee";
        try {
            connect();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                String employeeId = rs.getString("EmployeeId");
                int constraintId = rs.getInt("ConstraintId");
                identityMap.put(new ConstraintToEmployeeDAO.Identifier(employeeId, constraintId), constraintId);
            }
            disconnect();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            disconnect();
        }
    }

    public void addToMap(String employeeId, int constraintId) {
        identityMap.put(new ConstraintToEmployeeDAO.Identifier(employeeId, constraintId), constraintId);
    }

    public void removeFromMap(String employeeId, int constraintId) {
        for (ConstraintToEmployeeDAO.Identifier i : identityMap.keySet())
            if (i.same(employeeId, constraintId)) {
                identityMap.remove(i);
                break;
            }
    }

    private static class Identifier {
        private final String employeeId;
        private final int constraintId;

        public Identifier(String _employeeId, int _constraintId) {
            employeeId = _employeeId;
            constraintId = _constraintId;
        }

        public boolean same(String _employeeId, int _constraintId) {
            return employeeId.equals(_employeeId) && constraintId == _constraintId;
        }
    }
}
