package DataAccessLayer.HR_DAO;

import BusinessLayer.Human_Resources.Branch;
import DataAccessLayer.AbstractDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class BranchDAO extends AbstractDAO {
    private static BranchDAO instance;
    private final HashMap<BranchDAO.Identifier, Branch> identityMap;
    private final TimesOfShiftsDAO timesOfShiftsDAO;

    private BranchDAO() throws SQLException {
        identityMap = new HashMap<>();
        timesOfShiftsDAO = TimesOfShiftsDAO.getInstance();
    }

    public static BranchDAO getInstance() throws SQLException {
        if (instance == null)
            instance = new BranchDAO();
        return instance;
    }

    public static void deleteInstance() {
        if (instance != null)
            instance = null;
    }

    public void insert(String _address, String _phoneNumber, String _contactNumber, int _region, LocalTime _morningShiftStartHour, LocalTime _eveningShiftStartHour, LocalTime _morningShiftEndHour, LocalTime _eveningShiftEndHour, Double _latitude, Double _longitude) {
        String query = "INSERT INTO Branches VALUES('" + _address + "','" + _phoneNumber + "','" + _contactNumber + "'," + _region + ",'" + _morningShiftStartHour + "','" + _eveningShiftStartHour + "','" + _morningShiftEndHour + "','" + _eveningShiftEndHour + "'," + _latitude + "," + _longitude + ",0)";
        try {
            connect();
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(query);
            connection.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            disconnect();
        }
    }

    public Branch getBranch(String _address) {
        for (BranchDAO.Identifier curr : identityMap.keySet()) {
            if (curr.same(_address))
                return identityMap.get(curr);
        }
        String query = "SELECT Address,PhoneNumber,ContactName,Region,MorningShiftStartHour, EveningShiftStartHour,MorningShiftEndHour, EveningShiftEndHour,Latitude,Longitude,Type From Branches WHERE Address='" + _address + "'";
        try {
            connect();
            Branch branch = null;
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String address = rs.getString("Address");
                String phoneNumber = rs.getString("PhoneNumber");
                String contactName = rs.getString("ContactName");
                int region = rs.getInt("Region");
                LocalTime morningShiftStartHour = LocalTime.parse(rs.getString("MorningShiftStartHour"));
                LocalTime eveningShiftStartHour = LocalTime.parse(rs.getString("EveningShiftStartHour"));
                LocalTime morningShiftEndHour = LocalTime.parse(rs.getString("MorningShiftEndHour"));
                LocalTime eveningShiftEndHour = LocalTime.parse(rs.getString("EveningShiftEndHour"));
                double latitude = rs.getDouble("Latitude");
                double longitude = rs.getDouble("Longitude");
                int type = rs.getInt("Type");
                List<String> timesOfShifts = timesOfShiftsDAO.getTimesOfShiftsToBranch(address);
                branch = new Branch(address, phoneNumber, contactName, region, type, morningShiftStartHour, eveningShiftStartHour, morningShiftEndHour, eveningShiftEndHour, latitude, longitude, timesOfShifts);
                BranchDAO.Identifier identifier = new BranchDAO.Identifier(address);
                identityMap.put(identifier, branch);
            }
            return branch;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            disconnect();
        }
    }

    public void update(String id, String column, Object val) {
        List<String> textColumns = Arrays.asList("PhoneNumber", "ContactName");
        String query = "UPDATE BranchDAO SET " + column + "=" + val + " WHERE Address = '" + id + "'";
        if (textColumns.contains(column))
            query = "UPDATE BranchDAO SET " + column + "='" + val + "' WHERE Address = '" + id + "'";
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

    public void addToMap(String address, Branch b) {
        identityMap.put(new Identifier(address), b);
    }


    public HashMap<String, Branch> findAllBranches() {
        HashMap<String, Branch> branches = new HashMap<>();
        String query = "SELECT * From Branches";
        try {
            timesOfShiftsDAO.loadData();
            connect();
            Branch branch;
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String address = rs.getString("Address");
                String phoneNumber = rs.getString("PhoneNumber");
                String contactName = rs.getString("ContactNumber");
                int region = rs.getInt("Region");
                LocalTime morningShiftStartHour = LocalTime.parse(rs.getString("MorningShiftStartHour"));
                LocalTime eveningShiftStartHour = LocalTime.parse(rs.getString("EveningShiftStartHour"));
                LocalTime morningShiftEndHour = LocalTime.parse(rs.getString("MorningShiftEndHour"));
                LocalTime eveningShiftEndHour = LocalTime.parse(rs.getString("EveningShiftEndHour"));
                double latitude = rs.getDouble("Latitude");
                double longitude = rs.getDouble("Longitude");
                int type = rs.getInt("Type");
                LinkedList<String> timesOfShifts = timesOfShiftsDAO.getTimesOfShiftsIM(address);
                branch = new Branch(address, phoneNumber, contactName, region, type, morningShiftStartHour, eveningShiftStartHour, morningShiftEndHour, eveningShiftEndHour, latitude, longitude, timesOfShifts);
                BranchDAO.Identifier identifier = new BranchDAO.Identifier(address);
                identityMap.put(identifier, branch);
                branches.put(address, branch);
            }
            return branches;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            disconnect();
        }

    }

    private static class Identifier {
        private final String address;

        public Identifier(String _address) {
            address = _address;
        }

        public boolean same(String _address) {
            return address.equals(_address);
        }
    }
}
