package DataAccessLayer.HR_DAO;

import BusinessLayer.Human_Resources.Driver;
import BusinessLayer.Human_Resources.EmployeeConstraint;
import DataAccessLayer.AbstractDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class DriverDAO extends AbstractDAO {
    private static DriverDAO instance;
    private final HashMap<DriverDAO.Identifier, Driver> identityMap;
    private final DriverQualificationsDAO driverQualificationsDAO;
    private final DriverDeliveryDatesDAO driverDeliveryDatesDAO;
    private final ConstraintToEmployeeDAO constraintToEmployeeDAO;

    private DriverDAO() throws SQLException {
        identityMap = new HashMap<>();
        driverQualificationsDAO = DriverQualificationsDAO.getInstance();
        driverDeliveryDatesDAO = DriverDeliveryDatesDAO.getInstance();
        constraintToEmployeeDAO = ConstraintToEmployeeDAO.getInstance();
    }

    public static DriverDAO getInstance() throws SQLException {
        if (instance == null)
            instance = new DriverDAO();
        return instance;
    }

    public static void deleteInstance() {
        if (instance != null)
            instance = null;
    }

    public void insert(String _employeeId, int _licenses) {
        String query = "INSERT INTO Drivers VALUES('" + _employeeId + "'," + _licenses + ")";
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

    public Driver getDriver(String _employeeId) {
        for (DriverDAO.Identifier curr : identityMap.keySet()) {
            if (curr.same(_employeeId))
                return identityMap.get(curr);
        }
        String query = "SELECT Drivers.EmployeeId,Employees.FirstName,Employees.LastName,Employees.Password,Employees.AccountNumber,Employees.BranchBankNumber,Employees.Salary,Employees.TermsOfEmployment,Employees.StatusOfEmployee,Employees.HireDate,Employees.PhoneNumber,Employees.IsLogged,Drivers.Licenses FROM Employees,Drivers WHERE EmployeeId='" + _employeeId + "'";
        try {
            connect();
            Driver driver = null;
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String employeeId = rs.getString("EmployeeId");
                String firstName = rs.getString("FirstName");
                String lastName = rs.getString("LastName");
                String password = rs.getString("Password");
                int accountNumber = rs.getInt("AccountNumber");
                int branchBankNumber = rs.getInt("BranchBankNumber");
                int salary = rs.getInt("Salary");
                String termsOfEmployment = rs.getString("TermsOfEmployment");
                String statusOfEmployment = rs.getString("StatusOfEmployment");
                LocalDateTime hireDate = LocalDateTime.parse(rs.getString("HireDate"));
                String phoneNumber = rs.getString("PhoneNumber");
                boolean isLogged = rs.getInt("IsLogged") == 1;
                int type = rs.getInt("EType");
                int licenses = rs.getInt("Licenses");
                LinkedList<LocalDate> dates = driverDeliveryDatesDAO.getDriverDeliveryDates(employeeId);
                LinkedList<String> qualifications = driverQualificationsDAO.getDriverQualifications(employeeId);
                List<EmployeeConstraint> constraints = constraintToEmployeeDAO.getConstraintsToEmployee(employeeId);
                driver = new Driver(employeeId, firstName, lastName, password, accountNumber, branchBankNumber, salary, termsOfEmployment, statusOfEmployment, hireDate, phoneNumber, licenses, isLogged, qualifications, dates, constraints, type);
                DriverDAO.Identifier identifier = new DriverDAO.Identifier(employeeId);
                identityMap.put(identifier, driver);
            }
            return driver;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            disconnect();
        }
    }

    public void update(String id, String column, Object val) {
        String query = "UPDATE Drivers SET " + column + "=" + val + " WHERE EmployeeId = '" + id + "'";
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

    public HashMap<String, Driver> findAllDrivers() {
        HashMap<String, Driver> drivers = new HashMap<>();
        String query = "SELECT Drivers.EmployeeId,Employees.FirstName,Employees.LastName,Employees.Password,Employees.AccountNumber,Employees.BranchBankNumber,Employees.Salary,Employees.TermsOfEmployment,Employees.StatusOfEmployee,Employees.HireDate,Employees.PhoneNumber,Employees.IsLogged,Employees.EType,Drivers.Licenses FROM Employees,Drivers\n" +
                "where Employees.employeeId=Drivers.EmployeeId";
        try {
            driverDeliveryDatesDAO.loadData();
            driverQualificationsDAO.loadData();
            constraintToEmployeeDAO.loadData();
            connect();
            Driver driver;
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String employeeId = rs.getString("EmployeeId");
                String firstName = rs.getString("FirstName");
                String lastName = rs.getString("LastName");
                String password = rs.getString("Password");
                int accountNumber = rs.getInt("AccountNumber");
                int branchBankNumber = rs.getInt("BranchBankNumber");
                int salary = rs.getInt("Salary");
                String termsOfEmployment = rs.getString("TermsOfEmployment");
                String statusOfEmployment = rs.getString("StatusOfEmployee");
                LocalDateTime hireDate = LocalDateTime.parse(rs.getString("HireDate"));
                String phoneNumber = rs.getString("PhoneNumber");
                boolean isLogged = rs.getInt("IsLogged") == 1;
                int type = rs.getInt("EType");
                int licenses = rs.getInt("Licenses");
                LinkedList<LocalDate> dates = driverDeliveryDatesDAO.getDriverDeliveryDatesIM(employeeId);
                LinkedList<String> qualifications = driverQualificationsDAO.getDriverQualificationsIM(employeeId);
                List<EmployeeConstraint> constraints = constraintToEmployeeDAO.getConstraintsToEmployeeIM(employeeId);
                driver = new Driver(employeeId, firstName, lastName, password, accountNumber, branchBankNumber, salary, termsOfEmployment, statusOfEmployment, hireDate, phoneNumber, licenses, isLogged, qualifications, dates, constraints, type);
                DriverDAO.Identifier identifier = new DriverDAO.Identifier(employeeId);
                identityMap.put(identifier, driver);
                drivers.put(employeeId, driver);
            }
            return drivers;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            disconnect();
        }
    }

    public void addToMap(String employeeId, Driver d) {
        identityMap.put(new DriverDAO.Identifier(employeeId), d);
    }


    private static class Identifier {
        private final String employeeId;

        public Identifier(String _employeeId) {
            employeeId = _employeeId;
        }

        public boolean same(String _employeeId) {
            return employeeId.equals(_employeeId);
        }
    }
}
