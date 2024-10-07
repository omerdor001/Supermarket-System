package DataAccessLayer;

import java.sql.*;

public abstract class AbstractDAO {

    protected static final String path = "jdbc:sqlite::resource:DataAccessLayer/hr_deliveries_db.db";
    protected static Connection connection;

    public static Connection getConnection() {
        return connection;
    }

    public static void connect() {
        try {
            Class.forName("org.sqlite.JDBC");
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(path);
                connection.setAutoCommit(false);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void disconnect() {
        try {
            if (connection != null)
                connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void deleteData() {
        connect();
        String query = "DELETE FROM Deliveries;" + '\n';
        query = query + "DELETE FROM DeliveryOrders;" + '\n';
        query = query + "DELETE FROM ItemDeliveries;" + '\n';
        query = query + "DELETE FROM ItemOrders;" + '\n';
        query = query + "DELETE FROM Sites;" + '\n';
        query = query + "DELETE FROM Stops;" + '\n';
        query = query + "DELETE FROM StopLoadItems;" + '\n';
        query = query + "DELETE FROM StopUnloadItems;" + '\n';
        query = query + "DELETE FROM Trucks;" + '\n';
        query = query + "DELETE FROM TruckDeliveryDates;" + '\n';
        query = query + "DELETE FROM BranchEmployeeRoles;" + '\n';
        query = query + "DELETE FROM Employees;" + '\n';
        query = query + "DELETE FROM BranchEmployees;" + '\n';
        query = query + "DELETE FROM Branches;" + '\n';
        query = query + "DELETE FROM ConstraintsToEmployee;" + '\n';
        query = query + "DELETE FROM DriverDeliveryDates;" + '\n';
        query = query + "DELETE FROM DriverQualifications;" + '\n';
        query = query + "DELETE FROM DriverSchedules;" + '\n';
        query = query + "DELETE FROM Drivers;" + '\n';
        query = query + "DELETE FROM EmployeeConstraints;" + '\n';
        query = query + "DELETE FROM BranchShifts;" + '\n';
        query = query + "DELETE FROM RoleCounts;" + '\n';
        query = query + "DELETE FROM ProductCancellationsToShift;" + '\n';
        query = query + "DELETE FROM Schedules;" + '\n';
        query = query + "DELETE FROM TimesOfShifts;" + '\n';
        query = query + "DELETE FROM DriverShifts;" + '\n';
        query = query + "DELETE FROM Shifts;" + '\n';
        query = query + "UPDATE Counters SET NextId = 0;";

        try {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(query);
            connection.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
