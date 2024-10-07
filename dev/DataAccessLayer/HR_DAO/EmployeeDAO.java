package DataAccessLayer.HR_DAO;

import BusinessLayer.Human_Resources.Employee;
import BusinessLayer.Human_Resources.EmployeeConstraint;
import DataAccessLayer.AbstractDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class EmployeeDAO extends AbstractDAO {

    private static EmployeeDAO instance;
    private final HashMap<EmployeeDAO.Identifier, Employee> identityMap;
    private final ConstraintToEmployeeDAO constraintToEmployeeDAO;

    private EmployeeDAO() throws SQLException {
        identityMap = new HashMap<>();
        constraintToEmployeeDAO = ConstraintToEmployeeDAO.getInstance();
    }

    public static EmployeeDAO getInstance() throws SQLException {
        if (instance == null)
            instance = new EmployeeDAO();
        return instance;
    }

    public static void deleteInstance() {
        if (instance != null)
            instance = null;
    }

    public void insert(String _employeeId, String _firstName, String _lastName, String _password, int _accountNumber, int _branchBankNumber, int _salary, String _termsOfEmployment, String _statusOfEmployee, LocalDateTime _hireDate, String _phoneNumber, int _isLogged, int type) {
        String query = "INSERT INTO Employees VALUES('" + _employeeId + "','" + _firstName + "','" + _lastName + "','" + _password + "'," + _accountNumber + "," + _branchBankNumber + "," + _salary + ",'" + _termsOfEmployment + "','" + _statusOfEmployee + "','" + _hireDate + "','" + _phoneNumber + "'," + _isLogged + "," + type + ")";
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

    public Employee getEmployee(String _employeeId) {
        for (EmployeeDAO.Identifier curr : identityMap.keySet()) {
            if (curr.same(_employeeId))
                return identityMap.get(curr);
        }
        String query = "SELECT EmployeeId,FirstName,LastName,Password,AccountNumber,BranchBankNumber,Salary,TermsOfEmployment,StatusOfEmployment,HireDate,PhoneNumber,IsLogged,EType FROM Employees WHERE EmployeeId=" + _employeeId;
        try {
            connect();
            Employee employee = null;
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
                List<EmployeeConstraint> constraints = constraintToEmployeeDAO.getConstraintsToEmployee(employeeId);
                employee = new Employee(employeeId, firstName, lastName, password, accountNumber, branchBankNumber, salary, termsOfEmployment, statusOfEmployment, hireDate, phoneNumber, isLogged, constraints, type);
                EmployeeDAO.Identifier identifier = new EmployeeDAO.Identifier(employeeId);
                identityMap.put(identifier, employee);
            }
            return employee;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            disconnect();
        }
    }

    public void update(String id, String column, Object val) {
        List<String> numberColumns = Arrays.asList("FirstName", "LastName", "Password", "AccountNumber", "BranchBankNumber", "Salary", "TermsOfEmployment", "StatusOfEmployment", "IsLogged");
        String query = "UPDATE Employees SET '" + column + "',='" + val + "' WHERE EmployeeId = '" + id + "'";
        if (numberColumns.contains(column))
            query = "UPDATE Employees SET '" + column + "'='" + val + "' WHERE EmployeeId = '" + id + "'";
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

    public void addToMap(String employeeId, Employee e) {
        identityMap.put(new EmployeeDAO.Identifier(employeeId), e);
    }


    public HashMap<String, Employee> findAllEmployees() {
        HashMap<String, Employee> employees = new HashMap<>();
        String query = "SELECT * From Employees";
        try {
            constraintToEmployeeDAO.loadData();
            connect();
            Employee employee;
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
                int type = rs.getInt("EType");
                boolean isLogged = rs.getInt("IsLogged") == 1;
                List<EmployeeConstraint> constraints = constraintToEmployeeDAO.getConstraintsToEmployeeIM(employeeId);
                employee = new Employee(employeeId, firstName, lastName, password, accountNumber, branchBankNumber, salary, termsOfEmployment, statusOfEmployment, hireDate, phoneNumber, isLogged, constraints, type);
                EmployeeDAO.Identifier identifier = new EmployeeDAO.Identifier(employeeId);
                identityMap.put(identifier, employee);
                employees.put(employeeId, employee);
            }
            return employees;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            disconnect();
        }
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
