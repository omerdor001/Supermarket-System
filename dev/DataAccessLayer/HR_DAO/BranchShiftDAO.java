package DataAccessLayer.HR_DAO;

import BusinessLayer.Human_Resources.BranchShift;
import BusinessLayer.Human_Resources.ProductCancellation;
import DataAccessLayer.AbstractDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

public class BranchShiftDAO extends AbstractDAO {
    private static BranchShiftDAO instance;
    private final HashMap<BranchShiftDAO.Identifier, BranchShift> identityMap;
    private final RoleCountDAO roleCountDAO;
    private final ScheduleDAO scheduleDAO;
    private final ProductCancellationToShiftDAO productCancellationToShiftDAO;

    private BranchShiftDAO() throws SQLException {
        identityMap = new HashMap<>();
        roleCountDAO = RoleCountDAO.getInstance();
        scheduleDAO = ScheduleDAO.getInstance();
        productCancellationToShiftDAO = ProductCancellationToShiftDAO.getInstance();
    }

    public static BranchShiftDAO getInstance() throws SQLException {
        if (instance == null)
            instance = new BranchShiftDAO();
        return instance;
    }

    public static void deleteInstance() {
        if (instance != null)
            instance = null;
    }

    public void insert(int _shiftId, String _branch, String _type, int _shiftManagersCountN, int _storeKeepersCountN, int _cashiersCountN, int _generalEmployeesCountN, int _guardsCountN, int _cleanersCountN, int _merchandisersCountN) {
        String query = "INSERT INTO BranchShifts VALUES(" + _shiftId + ",'" + _branch + "','" + _type + "')";
        try {
            connect();
            connection.setAutoCommit(false);
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(query);
            connection.commit();
            insertRoleCount(_shiftId, _shiftManagersCountN, _storeKeepersCountN, _cashiersCountN, _generalEmployeesCountN, _guardsCountN, _cleanersCountN, _merchandisersCountN);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            disconnect();
        }
    }

    private void insertRoleCount(int shiftId, int _shiftManagersCountN, int _storeKeepersCountN, int _cashiersCountN, int _generalEmployeesCountN, int _guardsCountN, int _cleanersCountN, int _merchandisersCountN) {
        roleCountDAO.insert(shiftId, "Shift Manager", _shiftManagersCountN, 0);
        roleCountDAO.insert(shiftId, "Store Keeper", _storeKeepersCountN, 0);
        roleCountDAO.insert(shiftId, "Cashier", _cashiersCountN, 0);
        roleCountDAO.insert(shiftId, "General Employee", _generalEmployeesCountN, 0);
        roleCountDAO.insert(shiftId, "Guard", _guardsCountN, 0);
        roleCountDAO.insert(shiftId, "Cleaner", _cleanersCountN, 0);
        roleCountDAO.insert(shiftId, "Merchandiser", _merchandisersCountN, 0);
    }

    public BranchShift getBranchShift(int _shiftId) {
        for (BranchShiftDAO.Identifier curr : identityMap.keySet()) {
            if (curr.same(_shiftId))
                return identityMap.get(curr);
        }
        String query = "SELECT Shifts.ShiftId,Shifts.Date,BranchShifts.Branch,BranchShifts.Type FROM Shifts,BranchShifts WHERE ShiftId=" + _shiftId;
        try {
            connect();
            BranchShift branchShift = null;
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                int shiftId = rs.getInt("ShiftId");
                LocalDateTime date = LocalDateTime.parse(rs.getString("Date"));
                String branch = rs.getString("Branch");
                String type = rs.getString("Type");
                HashMap<String, Integer> roleCounts = roleCountDAO.getRoleCountNeeded(shiftId);
                HashMap<String, Integer> scheduleToRoleCounts = roleCountDAO.getRoleCountAssigned(shiftId);
                List<ProductCancellation> cancellations = productCancellationToShiftDAO.getPCToShift(shiftId);
                HashMap<String, String> schedules = scheduleDAO.getSchedules(shiftId);
                branchShift = new BranchShift(shiftId, date, roleCounts, scheduleToRoleCounts, cancellations, schedules, branch, type);
                BranchShiftDAO.Identifier identifier = new BranchShiftDAO.Identifier(shiftId);
                identityMap.put(identifier, branchShift);
            }
            return branchShift;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            disconnect();
        }
    }

    public void update(int id, String column, Object val) {
        String query = "UPDATE BranchShifts SET " + column + "='" + val + "' WHERE ShiftId = " + id;
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

    public void addToMap(int shiftId, BranchShift bs) {
        identityMap.put(new BranchShiftDAO.Identifier(shiftId), bs);
    }


    public HashMap<Integer, BranchShift> findAllBranchShifts() {
        HashMap<Integer, BranchShift> branchShifts = new HashMap<>();
        String query = "SELECT Shifts.ShiftId,Shifts.Date,BranchShifts.Branch,BranchShifts.Type FROM Shifts,BranchShifts";
        try {
            roleCountDAO.loadData();
            productCancellationToShiftDAO.loadData();
            scheduleDAO.loadData();
            connect();
            BranchShift branchShift;
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                int shiftId = rs.getInt("ShiftId");
                LocalDateTime date = LocalDateTime.parse(rs.getString("Date"));
                String branch = rs.getString("Branch");
                String type = rs.getString("Type");
                HashMap<String, Integer> roleCounts = roleCountDAO.getRoleCountNeededIM(shiftId);
                HashMap<String, Integer> scheduleToRoleCounts = roleCountDAO.getRoleCountAssignedIM(shiftId);
                List<ProductCancellation> cancellations = productCancellationToShiftDAO.getPCToShiftIM(shiftId);
                HashMap<String, String> schedules = scheduleDAO.getSchedulesIM(shiftId);
                branchShift = new BranchShift(shiftId, date, roleCounts, scheduleToRoleCounts, cancellations, schedules, branch, type);
                BranchShiftDAO.Identifier identifier = new BranchShiftDAO.Identifier(shiftId);
                identityMap.put(identifier, branchShift);
                branchShifts.put(shiftId, branchShift);
            }
            return branchShifts;
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

