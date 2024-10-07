package DataAccessLayer.Delivery_DAO;

import BusinessLayer.Delivery.Site;
import DataAccessLayer.AbstractDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;

public class SiteDAO extends AbstractDAO {

    private static SiteDAO instance;
    private final HashMap<Identifier, Site> identityMap;

    private SiteDAO() {
        identityMap = new HashMap<>();
    }

    public static SiteDAO getInstance() throws SQLException {
        if (instance == null)
            instance = new SiteDAO();
        return instance;
    }

    public static void deleteInstance() {
        if (instance != null)
            instance = null;
    }

    public Site getSite(String address) {
        for (Identifier i : identityMap.keySet()) {
            if (i.same(address))
                return identityMap.get(i);
        }
        String query = "SELECT * FROM Sites WHERE Address=" + address;
        try {
            connect();
            Site site = null;
            Statement statement = connection.createStatement();
            ResultSet set = statement.executeQuery(query);
            while (set.next()) {
                String siteAddress = set.getString("Address");
                String sitePhoneNumber = set.getString("PhoneNumber");
                String siteContactName = set.getString("ContactName");
                int siteRegion = set.getInt("Region");
                int siteType = set.getInt("Type");
                double latitude = set.getDouble("Latitude");
                double longitude = set.getDouble("Longitude");
                site = new Site(siteAddress, sitePhoneNumber, siteContactName, siteRegion, siteType, latitude, longitude);
                Identifier identifier = new Identifier(siteAddress);
                identityMap.put(identifier, site);
            }
            return site;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            disconnect();
        }
    }

    public Site getSiteV1(String address) {
        for (Identifier i : identityMap.keySet()) {
            if (i.same(address))
                return identityMap.get(i);
        }
        throw new NoSuchElementException("DAL - site doesn't found");
    }

    public void insert(String address, String phoneNumber, String contactName, int region, int type, double latitude, double longitude) {
        String query = "INSERT INTO Sites VALUES('" + address + "','" + phoneNumber + "','" + contactName + "'," + region + "," + type + "," + latitude + "," + longitude + ")";
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

    public void update(String address, String column, Object val) {
        //TODO update identity map?
        List<String> textColumns = Arrays.asList("Address", "PhoneNumber", "ContactName");
        String query = "UPDATE Sites SET " + column + "=" + val + " WHERE Address = '" + address + "'";
        if (textColumns.contains(column))
            query = "UPDATE Sites SET " + column + "='" + val + "' WHERE Address = '" + address + "'";
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

    public void delete(String address) {
        try {
            connect();
            String query = "DELETE from Sites WHERE Address='" + address + "'";
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
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


    public HashMap<String, Site> findAllSites() {
        HashMap<String, Site> sites = new HashMap<>();
        String query = "SELECT * FROM Sites";
        try {
            connect();
            Site site;
            Statement statement = connection.createStatement();
            ResultSet set = statement.executeQuery(query);
            while (set.next()) {
                String siteAddress = set.getString("Address");
                String sitePhoneNumber = set.getString("PhoneNumber");
                String siteContactName = set.getString("ContactName");
                int siteRegion = set.getInt("Region");
                int siteType = set.getInt("Type");
                double latitude = set.getDouble("Latitude");
                double longitude = set.getDouble("Longitude");
                site = new Site(siteAddress, sitePhoneNumber, siteContactName, siteRegion, siteType, latitude, longitude);
                Identifier identifier = new Identifier(siteAddress);
                identityMap.put(identifier, site);
                sites.put(siteAddress, site);
            }
            disconnect();
            return sites;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            disconnect();
        }
    }

    public void addToMap(String address, Site s) {
        identityMap.put(new Identifier(address), s);
    }

    public void removeFromMap(String address) {
        for (Identifier i : identityMap.keySet())
            if (i.same(address)) {
                identityMap.remove(i);
                break;
            }
    }

    private static class Identifier {
        private final String address;

        public Identifier(String address) {
            this.address = address;
        }

        private boolean same(String address) {
            return this.address.equals(address);
        }
    }
}
