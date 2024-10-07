package DataAccessLayer.HR_DAO;

import DataAccessLayer.AbstractDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.LinkedList;


public class DriverQualificationsDAO extends AbstractDAO {
    private static DriverQualificationsDAO instance;
    private final HashMap<DriverQualificationsDAO.Identifier, String> identityMap;

    private DriverQualificationsDAO() {
        identityMap = new HashMap<>();
    }

    public static DriverQualificationsDAO getInstance() throws SQLException {
        if (instance == null)
            instance = new DriverQualificationsDAO();
        return instance;
    }

    public static void deleteInstance() {
        if (instance != null)
            instance = null;
    }

    public void insert(String _employeeId, String _qualification) {
        String query = "INSERT INTO DriverQualifications VALUES('" + _employeeId + "','" + _qualification + "')";
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

    public LinkedList<String> getDriverQualifications(String _employeeId) {
        String query = "SELECT EmployeeId,Qualification from DriverQualifications WHERE EmployeeId='" + _employeeId + "'";
        try {
            connect();
            LinkedList<String> qualifications = new LinkedList<>();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String qualification = rs.getString("Qualification");
                qualifications.add(qualification);
                DriverQualificationsDAO.Identifier identifier = new DriverQualificationsDAO.Identifier(_employeeId, qualification);
                identityMap.put(identifier, qualification);
            }
            return qualifications;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            disconnect();
        }
    }

    public void deleteForEmployee(String _employeeId) {
        try {
            connection.setAutoCommit(false);
            String query = "DELETE from DriverQualifications WHERE EmployeeId='" + _employeeId + "'";
            Statement stmt = connection.createStatement();
            stmt.executeQuery(query);
            connection.commit();
        } catch (Exception e) {
            try {
                connect();
                connection.rollback();
                throw new RuntimeException(e);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        } finally {
            disconnect();
        }
    }

    public void delete(String _employeeId, String _qualification) {
        try {
            connect();
            connection.setAutoCommit(false);
            String query = "DELETE from DriverQualifications WHERE EmployeeId='" + _employeeId + "' AND Qualification='" + _qualification + "'";
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
        String query = "SELECT * FROM DriverQualifications";
        try {
            connect();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                String employeeId = rs.getString("EmployeeId");
                String qualification = rs.getString("Qualification");
                identityMap.put(new DriverQualificationsDAO.Identifier(employeeId, qualification), qualification);
            }
            disconnect();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            disconnect();
        }
    }

    public LinkedList<String> getDriverQualificationsIM(String _employeeId) {
        LinkedList<String> qualifications = new LinkedList<>();
        for (DriverQualificationsDAO.Identifier i : identityMap.keySet()) {
            if (i.employeeId.equals(_employeeId))
                qualifications.add(i.qualification);
        }
        return qualifications;
    }

    public void addToMap(String employeeId, String qualification) {
        identityMap.put(new DriverQualificationsDAO.Identifier(employeeId, qualification), qualification);
    }

    public void removeFromMap(String employeeId, String qualification) {
        for (DriverQualificationsDAO.Identifier i : identityMap.keySet())
            if (i.same(employeeId, qualification)) {
                identityMap.remove(i);
                break;
            }
    }

    private static class Identifier {
        private final String employeeId;
        private final String qualification;

        public Identifier(String _employeeId, String _qualification) {
            employeeId = _employeeId;
            qualification = _qualification;
        }

        public boolean same(String _employeeId, String _qualification) {
            return employeeId.equals(_employeeId) && qualification.equals(_qualification);
        }
    }


}
