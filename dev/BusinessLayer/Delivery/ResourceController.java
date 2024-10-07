package BusinessLayer.Delivery;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.NoSuchElementException;

import BusinessLayer.Human_Resources.Branch;
import BusinessLayer.Human_Resources.EmployeeController;
import BusinessLayer.Human_Resources.Driver;
import DataAccessLayer.DALFacade;
import ServiceLayer.JsonService.JsonConverter;
import com.fasterxml.jackson.core.JsonProcessingException;

public class ResourceController {
    private HashMap<Integer, Truck> trucks;
    private HashMap<String, Site> sites;
    private final EmployeeController employeeController;
    private final DALFacade dalController;

    public ResourceController(EmployeeController employeeController) {
        this.trucks = new HashMap<>();
        this.sites = new HashMap<>();
        this.employeeController = employeeController;
        this.dalController = DALFacade.getInstance();
        loadData();
    }

    private void setTrucks(HashMap<Integer, Truck> trucks){
        this.trucks=trucks;
    }

    private void setSites(HashMap<String, Site> sites){
        this.sites=sites;
    }

    public void loadData(){
        try{
            setTrucks(dalController.getAllTrucks());
            setSites(dalController.getAllSites());
            HashMap<String,Branch> branchHashMap =  dalController.getAllBranches();
            for (String address : branchHashMap.keySet())
                sites.put(address,branchHashMap.get(address));
        }
        catch (Exception e){
            throw new RuntimeException("DAL ERROR - ResourceController");
        }
    }

    public HashMap<Integer, Truck> getTrucks() {
        return trucks;
    }

    public HashMap<String, Site> getSites() {
        return sites;
    }

    public Site getSite(String address) {
        if (!sites.containsKey(address))
            throw new NoSuchElementException("the address doesn't exist");
        return sites.get(address);
    }

    public String getLogisticCenter() {
        for (String key : sites.keySet())
            if (sites.get(key).getType() == Site.Type.CENTER)
                return sites.get(key).getAddress();
        throw new NoSuchElementException("center doesn't exist");
    }

    public Truck getTruck(int truckId) {
        if (!trucks.containsKey(truckId))
            throw new NoSuchElementException("truck doesn't exist");
        return trucks.get(truckId);
    }

    public void addTruck(int truckId, String type, String model, float netWeight, float maxWeight) {
        if (trucks.containsKey(truckId))
            throw new IllegalArgumentException("truck's ID already exists");
        Truck newTruck = new Truck(truckId, type, model, netWeight, maxWeight);
        trucks.put(truckId, newTruck);
        dalController.insertTruck(newTruck);
    }

    public void removeTruck(int truckId) {
        if (!trucks.containsKey(truckId))
            throw new NoSuchElementException("truck doesn't exist");
        Truck remove = trucks.remove(truckId);
        dalController.deleteTruck(remove);
    }

    public void updateTruckStatus(int truckId, int status) {
        if (!trucks.containsKey(truckId))
            throw new NoSuchElementException("truck doesn't exist");
        Truck t = trucks.get(truckId);
        t.updateStatus(status);
        dalController.updateTruckStatus(t);
    }

    public void addTruckDeliveryDate(int truckId, LocalDate deliveryDate) {
        if (!trucks.containsKey(truckId))
            throw new NoSuchElementException("truck doesn't exist");
        Truck t = trucks.get(truckId);
        t.addDeliveryDate(deliveryDate);
        dalController.insertTruckDeliveryDate(t, deliveryDate);
    }

    public void removeTruckDeliveryDate(int truckId, LocalDate deliveryDate) {
        if (!trucks.containsKey(truckId))
            throw new NoSuchElementException("truck doesn't exist");
        Truck t = trucks.get(truckId);
        t.removeDeliveryDate(deliveryDate);
        dalController.deleteTruckDeliveryDate(t, deliveryDate);
    }

    public void addSite(String address, String phoneNumber, String contactName, int region, int type, double latitude, double longitude) {
        if (sites.containsKey(address))
            throw new IllegalArgumentException("the address exists");
        if (region < 0 || region > 3)
            throw new IllegalArgumentException("region is illegal");
        if (type < 0 || type > 2)
            throw new IllegalArgumentException("type is illegal");
        Coordinate check = new Coordinate(latitude, longitude);
        for (String site : sites.keySet()) {
            if (sites.get(site).getCoordinate().equals(check))
                throw new IllegalArgumentException("this coordinate already exists");
        }
        Site newSite = new Site(address, phoneNumber, contactName, region, type, latitude, longitude);
        sites.put(address, newSite);
        dalController.insertSite(newSite);
    }

    public void setSiteAddress(String oldAddress, String newAddress) {
        if (!sites.containsKey(oldAddress))
            throw new NoSuchElementException("Site does not exist");
        if (sites.containsKey(newAddress))
            throw new NoSuchElementException("new site address is already exist");
        Site site = sites.remove(oldAddress);
        site.setAddress(newAddress);
        sites.put(newAddress, site);
        dalController.updateSiteAddress(site, oldAddress);
    }

    public void updateSiteCoordinate(String address, double latitude, double longitude) {
        if (!sites.containsKey(address))
            throw new NoSuchElementException("Site does not exist");
        Coordinate check = new Coordinate(latitude, longitude);
        for (Site site : sites.values()) {
            if (site.getCoordinate().equals(check))
                throw new IllegalArgumentException("this coordinate is already exist");
            site.setCoordinate(latitude, longitude);
            dalController.updateSite(site, "Longitude");
            dalController.updateSite(site, "Latitude");
            break;
        }
    }

    public void removeSite(String address) {
        if (!sites.containsKey(address))
            throw new NoSuchElementException("Site does not exist");
        Site remove = sites.remove(address);
        dalController.deleteSite(remove);
    }

    public void updatePhoneNumber(String address, String phoneNumber) {
        if (!sites.containsKey(address))
            throw new NoSuchElementException("the address doesn't exist");
        Site s = sites.get(address);
        s.setPhoneNumber(phoneNumber);
        dalController.updateSite(s, "PhoneNumber");
    }

    public void updateContactName(String address, String contactName) {
        if (!sites.containsKey(address))
            throw new NoSuchElementException("the address doesn't exist");
        Site s = sites.get(address);
        s.setContactName(contactName);
        dalController.updateSite(s, "ContactName");
    }

    public void addBranch(Branch branch) {
        sites.put(branch.getAddress(), branch);
        dalController.insertSite(branch);
    }

    public Driver getDriver(String driverId) {
        return employeeController.getDriver(driverId);
    }

    public void approveDelivery(String driverId, int truckId, LocalDate date, boolean assertDeliveryType) {
        Driver driver = employeeController.getDriver(driverId);
        Truck truck = getTruck(truckId);
        if (driver != null) {
            if (truck != null) {
                truck.checkDate(date);
                driver.checkDate(date);
                checkCompatibility(driverId, truckId, assertDeliveryType);
            } else
                throw new NoSuchElementException("Truck does not exist");
        } else
            throw new NoSuchElementException("Driver does not exist");
    }

    public void checkCompatibility(String driverId, int truckId, boolean type) {
        Driver driver = employeeController.getDriver(driverId);
        Truck truck = getTruck(truckId);
        if (type) {
            if (!truck.getType().equals("Refrigeration"))
                throw new IllegalArgumentException("Truck does not match delivery type");
            if (!driver.hasQualification("Refrigeration"))
                throw new IllegalArgumentException("Driver does not have required qualification");
        }
        if (!type) {
            if (truck.getType().equals("Refrigeration"))
                throw new IllegalArgumentException("Truck does not match delivery type");
        }
        if (truck.getMaxWeight() > driver.getLicenses())
            throw new IllegalArgumentException("Driver does not have the required license for this truck");
    }

    public String showTruck(int truckId) throws JsonProcessingException {
        if (!trucks.containsKey(truckId))
            throw new NoSuchElementException("truck doesn't exist");
        return JsonConverter.toJson(trucks.get(truckId));
    }

    public String showAllTrucks() throws JsonProcessingException {
        if (trucks.isEmpty())
            throw new IllegalArgumentException("no trucks in the system");
        return JsonConverter.toJson(trucks);
    }

    public String showSite(String address) throws JsonProcessingException {
        if (!sites.containsKey(address))
            throw new NoSuchElementException("site doesn't exist");
        return JsonConverter.toJson(sites.get(address));
    }

    public String showAllSites() throws JsonProcessingException {
        if (sites.isEmpty())
            throw new IllegalArgumentException("no sites in the system");
        return JsonConverter.toJson(sites);
    }

    public EmployeeController getEmployeeController() {
        return employeeController;
    }
}