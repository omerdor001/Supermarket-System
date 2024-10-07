package DataAccessLayer.HR_DAO;

import BusinessLayer.Human_Resources.BranchEmployee;
import BusinessLayer.Human_Resources.EmployeeConstraint;
import DataAccessLayer.AbstractDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

public class BranchEmployeeDAO extends AbstractDAO {

    private static BranchEmployeeDAO instance;
    private final HashMap<BranchEmployeeDAO.Identifier, BranchEmployee> identityMap;
    private final BranchEmployeeRoleDAO branchEmployeeRoleDAO;
    private final ConstraintToEmployeeDAO constraintToEmployeeDAO;

    private BranchEmployeeDAO() throws SQLException {
        identityMap = new HashMap<>();
        branchEmployeeRoleDAO = BranchEmployeeRoleDAO.getInstance();
        constraintToEmployeeDAO = ConstraintToEmployeeDAO.getInstance();
    }

    public static BranchEmployeeDAO getInstance() throws SQLException {
        if (instance == null)
            instance = new BranchEmployeeDAO();
        return instance;
    }

    public static void deleteInstance() {
        if (instance != null)
            instance = null;
    }

    public void insert(String _employeeId, int _management, int _cancellations) {
        String query = "INSERT INTO BranchEmployees VALUES('" + _employeeId + "'," + _management + "," + _cancellations + ")";
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

    public BranchEmployee getBranchEmployee(String _employeeId) {
        for (BranchEmployeeDAO.Identifier curr : identityMap.keySet()) {
            if (curr.same(_employeeId))
                return identityMap.get(curr);
        }
        String query = "SELECT BranchEmployees.EmployeeId,Employees.FirstName,Employees.LastName,Employees.Password,Employees.AccountNumber,Employees.BranchBankNumber,Employees.Salary,Employees.TermsOfEmployment,Employees.StatusOfEmployment,Employees.HireDate,Employees.PhoneNumber,Employees.IsLogged,Employee.EType,BranchEmployees.Management,BranchEmployees.Cancellations FROM Employees,BranchEmployees WHERE EmployeeId='" + _employeeId + "'";
        try {
            connect();
            BranchEmployee branchEmployee = null;
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
                boolean management = rs.getInt("Management") == 1;
                boolean cancellations = rs.getInt("Cancellations") == 1;
                List<EmployeeConstraint> constraints = constraintToEmployeeDAO.getConstraintsToEmployeeIM(employeeId);
                List<String> roles = branchEmployeeRoleDAO.getBranchEmployeeRolesIM(employeeId);
                branchEmployee = new BranchEmployee(employeeId, firstName, lastName, password, accountNumber, branchBankNumber, salary, termsOfEmployment, statusOfEmployment, hireDate, phoneNumber, management, cancellations, isLogged, roles, constraints, type);
                BranchEmployeeDAO.Identifier identifier = new BranchEmployeeDAO.Identifier(employeeId);
                identityMap.put(identifier, branchEmployee);
            }
            return branchEmployee;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            disconnect();
        }
    }

    public void update(String id, String column, Object val) {
        String query = "UPDATE BranchEmployees SET " + column + "=" + val + " WHERE EmployeeId = '" + id + "'";
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

    public void addToMap(String employeeId, BranchEmployee be) {
        identityMap.put(new BranchEmployeeDAO.Identifier(employeeId), be);
    }

    public HashMap<String, BranchEmployee> findAllBranchEmployees() {
        HashMap<String, BranchEmployee> branchEmployees = new HashMap<>();
        String query = "SELECT BranchEmployees.EmployeeId,Employees.FirstName,Employees.LastName,Employees.Password,Employees.AccountNumber,Employees.BranchBankNumber,Employees.Salary,Employees.TermsOfEmployment,Employees.StatusOfEmployee,Employees.HireDate,Employees.PhoneNumber,Employees.IsLogged,Employees.EType,BranchEmployees.Management,BranchEmployees.Cancellation\n" +
                " FROM Employees,BranchEmployees\n" +
                "where Employees.EmployeeId=BranchEmployees.EmployeeId;";
        try {
            connect();
            Statement statement = connection.createStatement();
            ResultSet set = statement.executeQuery(query);
            while (set.next()) {
                String employeeId = set.getString("EmployeeId");
                String firstName = set.getString("FirstName");
                String lastName = set.getString("LastName");
                String password = set.getString("Password");
                int accountNumber = set.getInt("AccountNumber");
                int branchBankNumber = set.getInt("BranchBankNumber");
                int salary = set.getInt("Salary");
                String termsOfEmployment = set.getString("TermsOfEmployment");
                String statusOfEmployment = set.getString("StatusOfEmployee");
                LocalDateTime hireDate = LocalDateTime.parse(set.getString("HireDate"));
                String phoneNumber = set.getString("PhoneNumber");
                boolean isLogged = set.getInt("IsLogged") == 1;
                int type = set.getInt("EType");
                List<EmployeeConstraint> constraints = constraintToEmployeeDAO.getConstraintsToEmployeeIM(employeeId);
                boolean management = set.getInt("Management") == 1;
                boolean cancellations = set.getInt("Cancellation") == 1;
                List<String> roles = branchEmployeeRoleDAO.getBranchEmployeeRolesIM(employeeId);
                BranchEmployee branchEmployee = new BranchEmployee(employeeId, firstName, lastName, password, accountNumber, branchBankNumber, salary, termsOfEmployment, statusOfEmployment, hireDate, phoneNumber, management, cancellations, isLogged, roles, constraints, type);
                BranchEmployeeDAO.Identifier identifier = new BranchEmployeeDAO.Identifier(employeeId);
                identityMap.put(identifier, branchEmployee);
                branchEmployees.put(employeeId, branchEmployee);
            }
            return branchEmployees;
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

