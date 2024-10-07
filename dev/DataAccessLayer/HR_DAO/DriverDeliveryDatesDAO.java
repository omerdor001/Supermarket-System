package DataAccessLayer.HR_DAO;

import DataAccessLayer.AbstractDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedList;

public class DriverDeliveryDatesDAO extends AbstractDAO {
    private final HashMap<DriverDeliveryDatesDAO.Identifier, LocalDate> identityMap;
    private static DriverDeliveryDatesDAO instance;

    private DriverDeliveryDatesDAO()  {
        identityMap = new HashMap<>();
    }

    public static DriverDeliveryDatesDAO getInstance() throws SQLException {
        if(instance==null)
            instance=new DriverDeliveryDatesDAO();
        return instance;
    }

    public static void deleteInstance(){
        if(instance != null)
            instance = null;
    }

    public void insert(String _employeeId,String _deliveryDate){
        String query = "INSERT INTO DriverDeliveryDates VALUES('" + _employeeId+"','"+_deliveryDate+ "')";
        try {
            connect();
            connection.setAutoCommit(false);
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(query);
            connection.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            disconnect();
        }
    }

    public LinkedList<LocalDate> getDriverDeliveryDates(String _employeeId) {
        String query = "SELECT EmployeeId,DeliveryDate from DriverDeliveryDates WHERE EmployeeId='" + _employeeId+"'" ;
        try {
            connect();
            LinkedList<LocalDate> deliveryDates=new LinkedList<>();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()){
                LocalDate deliveryDate=LocalDate.parse(rs.getString("DeliveryDate"));
                deliveryDates.add(deliveryDate);
                DriverDeliveryDatesDAO.Identifier identifier = new DriverDeliveryDatesDAO.Identifier(_employeeId,deliveryDate);
                identityMap.put(identifier, deliveryDate);
            }

            return deliveryDates;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            disconnect();
        }
    }

    public void delete(String _employeeId,String _deliveryDate) {
        try{
            connect();
            connection.setAutoCommit(false);
            String query = "DELETE from DriverDeliveryDates WHERE EmployeeId="+ _employeeId+" AND DeliveryDate="+_deliveryDate;
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(query);
            connection.commit();
        } catch (Exception e) {
            try{
                connection.rollback();
                throw new RuntimeException(e);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
        finally {
            disconnect();
        }
    }

    public void loadData(){
        String query = "SELECT * FROM DriverDeliveryDates";
        try {
            connect();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                String employeeId=rs.getString("EmployeeId");
                LocalDate deliveryDate=LocalDate.parse(rs.getString("DeliveryDate"));
                identityMap.put(new DriverDeliveryDatesDAO.Identifier(employeeId,deliveryDate), deliveryDate);
            }
            disconnect();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            disconnect();
        }
    }

    public LinkedList<LocalDate> getDriverDeliveryDatesIM(String _employeeId) {
        LinkedList<LocalDate> dates = new LinkedList<>();
        for (DriverDeliveryDatesDAO.Identifier i : identityMap.keySet()){
            if (i.employeeId.equals(_employeeId))
                dates.add(i.date);
        }
        return dates;
    }

    public void addToMap(String employeeId, LocalDate deliveryDate){
        identityMap.put(new DriverDeliveryDatesDAO.Identifier(employeeId,deliveryDate),deliveryDate);
    }

    public void removeFromMap(String employeeId, LocalDate deliveryDate){
        for(DriverDeliveryDatesDAO.Identifier i : identityMap.keySet())
            if(i.same(employeeId,deliveryDate)) {
                identityMap.remove(i);
                break;
            }
    }


    private static class Identifier {
        private final String employeeId;
        private final LocalDate date;
        public Identifier(String _employeeId,LocalDate _date) {
            employeeId = _employeeId;
            date=_date;
        }
        public boolean same(String _employeeId,LocalDate _date) {
            return employeeId.equals(_employeeId) && date.equals(_date);
        }
    }
}
