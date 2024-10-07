package DataAccessLayer;

import BusinessLayer.Delivery.*;
import BusinessLayer.Human_Resources.*;
import DataAccessLayer.Delivery_DAO.*;
import DataAccessLayer.HR_DAO.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;

public class DALFacade {
    private static DALFacade instance;
    private final CountersDAO countersDAO;
    private final DeliveryDAO deliveryDAO;
    private final DeliveryOrderDAO deliveryOrderDAO;
    private final ItemDeliveryDAO itemDeliveryDAO;
    private final ItemOrderDAO itemOrderDAO;
    private final SiteDAO siteDAO;
    private final StopDAO stopDAO;
    private final StopLoadItemsDAO stopLoadItemsDAO;
    private final StopUnloadItemsDAO stopUnloadItemsDAO;
    private final TruckDAO truckDAO;
    private final TruckDeliveryDatesDAO truckDeliveryDatesDAO;
    private final BranchDAO branchDAO;
    private final EmployeeDAO employeeDAO;
    private final BranchEmployeeDAO branchEmployeeDAO;
    private final BranchEmployeeRoleDAO branchEmployeeRoleDAO;
    private final ConstraintToEmployeeDAO constraintToEmployeeDAO;
    private final DriverDAO driverDAO;
    private final DriverDeliveryDatesDAO driverDeliveryDatesDAO;
    private final DriverQualificationsDAO driverQualificationsDAO;
    private final DriverScheduleDAO driverScheduleDAO;
    private final DriverShiftDAO driverShiftDAO;
    private final EmployeeConstraintDAO employeeConstraintDAO;
    private final BranchShiftDAO branchShiftDAO;
    private final ShiftDAO shiftDAO;
    private final ProductCancellationToShiftDAO productCancellationToShiftDAO;
    private final RoleCountDAO roleCountDAO;
    private final ScheduleDAO scheduleDAO;
    private final TimesOfShiftsDAO timesOfShiftsDAO;

    private DALFacade() throws SQLException {
        this.countersDAO = CountersDAO.getInstance();
        this.deliveryDAO = DeliveryDAO.getInstance();
        this.deliveryOrderDAO = DeliveryOrderDAO.getInstance();
        this.itemDeliveryDAO = ItemDeliveryDAO.getInstance();
        this.itemOrderDAO = ItemOrderDAO.getInstance();
        this.siteDAO = SiteDAO.getInstance();
        this.stopDAO = StopDAO.getInstance();
        this.stopLoadItemsDAO = StopLoadItemsDAO.getInstance();
        this.stopUnloadItemsDAO = StopUnloadItemsDAO.getInstance();
        this.truckDAO = TruckDAO.getInstance();
        this.truckDeliveryDatesDAO = TruckDeliveryDatesDAO.getInstance();
        this.branchDAO = BranchDAO.getInstance();
        this.employeeDAO = EmployeeDAO.getInstance();
        this.branchEmployeeDAO = BranchEmployeeDAO.getInstance();
        this.branchEmployeeRoleDAO = BranchEmployeeRoleDAO.getInstance();
        this.constraintToEmployeeDAO = ConstraintToEmployeeDAO.getInstance();
        this.driverDAO = DriverDAO.getInstance();
        this.driverDeliveryDatesDAO = DriverDeliveryDatesDAO.getInstance();
        this.driverQualificationsDAO = DriverQualificationsDAO.getInstance();
        this.driverScheduleDAO = DriverScheduleDAO.getInstance();
        this.driverShiftDAO = DriverShiftDAO.getInstance();
        this.employeeConstraintDAO = EmployeeConstraintDAO.getInstance();
        this.branchShiftDAO = BranchShiftDAO.getInstance();
        this.shiftDAO = ShiftDAO.getInstance();
        this.productCancellationToShiftDAO = ProductCancellationToShiftDAO.getInstance();
        this.roleCountDAO = RoleCountDAO.getInstance();
        this.scheduleDAO = ScheduleDAO.getInstance();
        this.timesOfShiftsDAO = TimesOfShiftsDAO.getInstance();
    }

    public static DALFacade getInstance() {
        try {
            if (instance == null) instance = new DALFacade();
            return instance;
        } catch (SQLException e) {
            throw new RuntimeException("DAL error");
        }
    }

    public void deleteInstance() {
        if (instance != null) {
            instance = null;
            CountersDAO.deleteInstance();
            DeliveryDAO.deleteInstance();
            DeliveryOrderDAO.deleteInstance();
            ItemDeliveryDAO.deleteInstance();
            ItemOrderDAO.deleteInstance();
            SiteDAO.deleteInstance();
            StopDAO.deleteInstance();
            StopLoadItemsDAO.deleteInstance();
            StopUnloadItemsDAO.deleteInstance();
            TruckDAO.deleteInstance();
            TruckDeliveryDatesDAO.deleteInstance();
            BranchDAO.deleteInstance();
            EmployeeDAO.deleteInstance();
            BranchEmployeeDAO.deleteInstance();
            BranchEmployeeRoleDAO.deleteInstance();
            ConstraintToEmployeeDAO.deleteInstance();
            DriverDAO.deleteInstance();
            DriverDeliveryDatesDAO.deleteInstance();
            DriverQualificationsDAO.deleteInstance();
            DriverScheduleDAO.deleteInstance();
            DriverShiftDAO.deleteInstance();
            EmployeeConstraintDAO.deleteInstance();
            BranchShiftDAO.deleteInstance();
            ShiftDAO.deleteInstance();
            ProductCancellationToShiftDAO.deleteInstance();
            RoleCountDAO.deleteInstance();
            ScheduleDAO.deleteInstance();
            TimesOfShiftsDAO.deleteInstance();
        }
    }

    public void deleteData() {
        AbstractDAO.deleteData();
        deleteInstance();
    }

    public HashMap<String, Integer> getAllCounters() {
        return countersDAO.findAllCounters();
    }

    public HashMap<Integer, Truck> getAllTrucks() {
        return truckDAO.findAllTrucks();
    }

    public HashMap<String, Site> getAllSites() {
        return siteDAO.findAllSites();
    }

    public HashMap<Integer, ItemOrder> getAllItemOrders() {
        return itemOrderDAO.findAllItemOrders();
    }

    public HashMap<Integer, DeliveryOrder> getAllDeliveryOrders() {
        return deliveryOrderDAO.findAllDeliveryOrders();
    }

    public HashMap<Integer, ItemDelivery> getAllItemDeliveries() {
        return itemDeliveryDAO.findAllItemDeliveries();
    }

    public HashMap<Integer, Delivery> getAllDeliveries() {
        return deliveryDAO.findAllDeliveries();
    }

    public HashMap<String, Branch> getAllBranches() {
        return branchDAO.findAllBranches();
    }

    public HashMap<String, Employee> getAllEmployees() {
        return employeeDAO.findAllEmployees();
    }

    public HashMap<String, BranchEmployee> getAllBranchEmployees() {
        return branchEmployeeDAO.findAllBranchEmployees();
    }

    public HashMap<String, Driver> getAllDrivers() {
        return driverDAO.findAllDrivers();
    }

    public HashMap<Integer, BranchShift> getAllBranchShifts() {
        return branchShiftDAO.findAllBranchShifts();
    }

    public HashMap<Integer, DriverShift> getAllDriverShifts() {
        return driverShiftDAO.findAllDriverShifts();
    }

    public void updateCounter(String counterName, int id) {
        countersDAO.updateMap(counterName, id);
        countersDAO.update(counterName, id);
    }

    public void insertDelivery(Delivery d) {
        deliveryDAO.addToMap(d.getID(), d);
        deliveryDAO.insert(d.getID(), d.getDate(), d.getTime(), d.getSource().getAddress(), d.getTruckId(), d.getDriverId(), d.getStartingWeight(), d.getStatusOrdinal(), d.getMaxWeight());
    }

    public void updateDelivery(Delivery d, String property) {
        int id = d.getID();
        switch (property) {
            case "Date": {
                deliveryDAO.update(id, property, d.getDate());
                break;
            }
            case "Time": {
                deliveryDAO.update(id, property, d.getTime());
                break;
            }
            case "Source": {
                deliveryDAO.update(id, property, d.getSource().getAddress());
                break;
            }
            case "TruckId": {
                deliveryDAO.update(id, property, d.getTruckId());
                break;
            }
            case "DriverId": {
                deliveryDAO.update(id, property, d.getDriverId());
                break;
            }
            case "StartingWeight": {
                deliveryDAO.update(id, property, d.getStartingWeight());
                break;
            }
            case "Status": {
                deliveryDAO.update(id, property, d.getStatusOrdinal());
                break;
            }
            case "MaxWeight": {
                deliveryDAO.update(id, property, d.getMaxWeight());
            }
        }
    }

    public void insertStop(Stop s) {
        stopDAO.addToMap(s.getId(), s);
        stopDAO.insert(s.getId(), s.getDeliveryId(), s.getDestination(), s.getStatus().ordinal(), s.getArriveTime());
    }

    public void updateStop(Stop s, String property) {
        int id = s.getId();
        switch (property) {
            case "Status": {
                stopDAO.update(id, "Status", s.getStatus().ordinal());
                break;
            }
            case "ArriveTime": {
                stopDAO.update(id, "ArriveTime", s.getArriveTime());
                break;
            }
        }
    }

    public void insertDeliveryOrder(DeliveryOrder d) {
        deliveryOrderDAO.addToMap(d.getId(), d);
        deliveryOrderDAO.insert(d.getId(), d.getDestination(), d.getSource(), d.getTotalLoadWeight(), d.getStatusOrdinal());
    }

    public void updateDeliveryOrderStatus(DeliveryOrder d) {
        deliveryOrderDAO.update(d.getId(), "Status", d.getStatusOrdinal());
    }

    public void insertItemDelivery(ItemDelivery i) {
        itemDeliveryDAO.addToMap(i.getId(), i);
        itemDeliveryDAO.insert(i.getId(), i.getDeliveryId(), i.getItemOrder().getId(), i.getSource(), i.getDestination(), i.getStatusOrdinal());
    }

    public void updateItemDeliveryStatus(ItemDelivery i) {
        itemDeliveryDAO.update(i.getId(), "Status", i.getStatusOrdinal());
    }

    public void insertItemOrder(ItemOrder i) {
        itemOrderDAO.addToMap(i.getId(), i);
        itemOrderDAO.insert(i.getId(), i.getItemId(), i.getDeliveryOrderId(), i.getSource(), i.getDestination(), i.getQuantity(), i.isRefrigeration(), i.getStatusOrdinal(), i.getItemDelivery1(), i.getItemDelivery2());
    }

    public void updateItemOrder(ItemOrder i, String property) {
        int id = i.getId();
        switch (property) {
            case "Status": {
                itemOrderDAO.update(id, property, i.getStatusOrdinal());
                break;
            }
            case "ItemDelivery1Id": {
                itemOrderDAO.update(id, property, i.getItemDelivery1());
                break;
            }
            case "ItemDelivery2Id": {
                itemOrderDAO.update(id, property, i.getItemDelivery2());
                break;
            }
        }
    }

    public void insertSite(Site s) {
        siteDAO.addToMap(s.getAddress(), s);
        siteDAO.insert(s.getAddress(), s.getPhoneNumber(), s.getContactName(), s.getRegion().ordinal(), s.getType().ordinal(), s.getCoordinate().getLatitude(), s.getCoordinate().getLongitude());
    }

    public void deleteSite(Site s) { // will be removed later
        siteDAO.removeFromMap(s.getAddress());
        siteDAO.delete(s.getAddress());
    }

    public void updateSite(Site s, String property) {
        String address = s.getAddress();
        switch (property) {
            case "PhoneNumber": {
                siteDAO.update(address, property, s.getPhoneNumber());
                break;
            }
            case "ContactName": {
                siteDAO.update(address, property, s.getContactName());
                break;
            }
            case "Longitude": {
                siteDAO.update(address, property, s.getCoordinate().getLongitude());
                break;
            }
            case "Latitude": {
                siteDAO.update(address, property, s.getCoordinate().getLatitude());
                break;
            }
        }
    }

    public void updateSiteAddress(Site s, String address) {
        siteDAO.update(address, "Address", s.getAddress());
    }

    public void insertStopLoadItem(int stopId, int itemDeliveryId) {
        stopLoadItemsDAO.addToMap(stopId, itemDeliveryId);
        stopLoadItemsDAO.insert(stopId, itemDeliveryId);
    }

    public void deleteStopLoadItem(int stopId, int itemDeliveryId) {
        stopLoadItemsDAO.removeFromMap(stopId, itemDeliveryId);
        stopLoadItemsDAO.delete(stopId, itemDeliveryId);
    }

    public void insertStopUnloadItem(int stopId, int itemDeliveryId) {
        stopUnloadItemsDAO.addToMap(stopId, itemDeliveryId);
        stopUnloadItemsDAO.insert(stopId, itemDeliveryId);
    }

    public void deleteStopUnloadItem(int stopId, int itemDeliveryId) {
        stopUnloadItemsDAO.removeFromMap(stopId, itemDeliveryId);
        stopUnloadItemsDAO.delete(stopId, itemDeliveryId);
    }

    public void insertTruck(Truck t) {
        truckDAO.addToMap(t.getId(), t);
        truckDAO.insert(t.getId(), t.getType(), t.getModel(), t.getNetWeight(), t.getMaxWeight(), t.getStatusOrdinal());
    }

    public void deleteTruck(Truck t) {
        truckDAO.delete(t.getId());
    }

    public void updateTruckStatus(Truck t) {
        truckDAO.update(t.getId(), "Status", t.getStatusOrdinal());
    }

    public void insertTruckDeliveryDate(Truck t, LocalDate d) {
        truckDeliveryDatesDAO.addToMap(t.getId(), d);
        truckDeliveryDatesDAO.insert(t.getId(), d);
    }

    public void deleteTruckDeliveryDate(Truck t, LocalDate d) {
        truckDeliveryDatesDAO.removeFromMap(t.getId(), d);
        truckDeliveryDatesDAO.delete(t.getId(), d);
    }


    //HR

    public void insertBranch(Branch b) {
        branchDAO.addToMap(b.getAddress(), b);
        branchDAO.insert(b.getAddress(), b.getPhoneNumber(), b.getContactName(), b.getRegion().ordinal(), b.getMorningShiftStartHour(), b.getEveningShiftStartHour(), b.getMorningShiftEndHour(), b.getEveningShiftEndHour(), b.getCoordinate().getLatitude(), b.getCoordinate().getLongitude());
    }

    public void updateBranch(Branch b, String property) {
        String address = b.getAddress();
        switch (property) {
            case "PhoneNumber": {
                branchDAO.update(address, property, b.getPhoneNumber());
                break;
            }
            case "ContactName": {
                branchDAO.update(address, property, b.getContactName());
                break;
            }
        }
    }

    public void insertBranchEmployee(BranchEmployee br) {
        employeeDAO.addToMap(br.getEmployeeId(), br);
        branchEmployeeDAO.addToMap(br.getEmployeeId(), br);
        employeeDAO.insert(br.getEmployeeId(), br.getFirstName(), br.getLastName(), br.getPassword(), br.getAccountNumber(), br.getBranchBankNumber(), br.getSalary(), br.getTermsOfEmployment(), br.getStatusOfEmployee(), br.getHireDate(), br.getPhoneNumber(),0, br.isLogged() ? 1 : 0);
        branchEmployeeDAO.insert(br.getEmployeeId(), br.isManagement() ? 1 : 0, br.isCancellations() ? 1 : 0);
    }

    public void updateBranchEmployee(BranchEmployee br, String property) {
        String employeeId = br.getEmployeeId();
        switch (property) {
            case "Management": {
                branchEmployeeDAO.update(employeeId, property, br.isManagement() ? 1 : 0);
                break;
            }
            case "Cancellation": {
                siteDAO.update(employeeId, property, br.isCancellations() ? 1 : 0);
                break;
            }
        }
    }

    public void insertEmployee(Employee e) {
        employeeDAO.addToMap(e.getEmployeeId(), e);
        employeeDAO.insert(e.getEmployeeId(), e.getFirstName(), e.getLastName(), e.getPassword(), e.getAccountNumber(), e.getBranchBankNumber(), e.getSalary(), e.getTermsOfEmployment(), e.getStatusOfEmployee(), e.getHireDate(), e.getPhoneNumber(), e.getTypeOrdinal(), e.isLogged() ? 1 : 0);
    }

    public void updateEmployee(Employee br, String property) {
        String employeeId = br.getEmployeeId();
        switch (property) {
            case "FirstName": {
                employeeDAO.update(employeeId, property, br.getFirstName());
                break;
            }
            case "LastName": {
                employeeDAO.update(employeeId, property, br.getLastName());
                break;
            }
            case "Password": {
                employeeDAO.update(employeeId, property, br.getPassword());
                break;
            }
            case "AccountNumber": {
                employeeDAO.update(employeeId, property, br.getAccountNumber());
                break;
            }
            case "BranchBankNumber": {
                employeeDAO.update(employeeId, property, br.getBranchBankNumber());
                break;
            }
            case "Salary": {
                employeeDAO.update(employeeId, property, br.getSalary());
                break;
            }
            case "TermsOfEmployment": {
                employeeDAO.update(employeeId, property, br.getTermsOfEmployment());
                break;
            }
            case "StatusOfEmployee": {
                employeeDAO.update(employeeId, property, br.getStatusOfEmployee());
                break;
            }
            case "PhoneNumber": {
                employeeDAO.update(employeeId, property, br.getPhoneNumber());
                break;
            }
            case "IsLogged": {
                employeeDAO.update(employeeId, property, br.isLogged() ? 1 : 0);
                break;
            }
        }
    }

    public void updateSchedule(int shiftId, String employeeId, String role) {
        scheduleDAO.update(shiftId, employeeId, role);
    }

    public void insertDriver(Driver d) {
        employeeDAO.addToMap(d.getEmployeeId(), d);
        driverDAO.addToMap(d.getEmployeeId(), d);
        employeeDAO.insert(d.getEmployeeId(), d.getFirstName(), d.getLastName(), d.getPassword(), d.getAccountNumber(), d.getBranchBankNumber(), d.getSalary(), d.getTermsOfEmployment(), d.getStatusOfEmployee(), d.getHireDate(), d.getPhoneNumber(),1, d.isLogged() ? 1 : 0);
        driverDAO.insert(d.getEmployeeId(), d.getLicenses());
    }

    public void updateDriver(Driver d, String property) {
        String employeeId = d.getEmployeeId();
        switch (property) {
            case "Licenses": {
                branchEmployeeDAO.update(employeeId, property, d.getLicenses());
                break;
            }
        }
    }

    public void insertBranchEmployeeRole(BranchEmployee br, String role) {
        branchEmployeeRoleDAO.addToMap(br.getEmployeeId(), role);
        branchEmployeeRoleDAO.insert(br.getEmployeeId(), role);
    }

    public void deleteBranchEmployeeRole(BranchEmployee br, String role) {
        branchEmployeeRoleDAO.removeFromMap(br.getEmployeeId(), role);
        branchEmployeeRoleDAO.delete(br.getEmployeeId(), role);
    }

    public void insertConstraintToEmployee(Employee e, EmployeeConstraint ec) {
        constraintToEmployeeDAO.addToMap(e.getEmployeeId(), ec.getConstraintId());
        constraintToEmployeeDAO.insert(e.getEmployeeId(), ec.getConstraintId());
    }

    public void deleteConstraintToEmployee(Employee e, EmployeeConstraint ec) {
        constraintToEmployeeDAO.removeFromMap(e.getEmployeeId(), ec.getConstraintId());
        constraintToEmployeeDAO.delete(e.getEmployeeId(), ec.getConstraintId());
    }

    public void insertDriverDeliveryDate(Driver d, LocalDate ld) {
        driverDeliveryDatesDAO.addToMap(d.getEmployeeId(), ld);
        driverDeliveryDatesDAO.insert(d.getEmployeeId(), ld.toString());
    }

    public void deleteDriverDeliveryDate(Driver d, LocalDate ld) {
        driverDeliveryDatesDAO.removeFromMap(d.getEmployeeId(), ld);
        driverDeliveryDatesDAO.delete(d.getEmployeeId(), ld.toString());
    }

    public void insertDriverQualification(Driver d, String qualification) {
        driverQualificationsDAO.addToMap(d.getEmployeeId(), qualification);
        driverQualificationsDAO.insert(d.getEmployeeId(), qualification);
    }

    public void deleteDriverQualification(Driver d, String qualification) {
        driverQualificationsDAO.removeFromMap(d.getEmployeeId(), qualification);
        driverQualificationsDAO.delete(d.getEmployeeId(), qualification);
    }

    public void insertDriverSchedule(DriverShift ds, Driver d) {
        driverScheduleDAO.addToMap(ds.getShiftId(), d.getEmployeeId());
        driverScheduleDAO.insert(ds.getShiftId(), d.getEmployeeId());
    }

    public void deleteDriverSchedule(DriverShift ds, Driver d) {
        driverScheduleDAO.removeFromMap(ds.getShiftId(), d.getEmployeeId());
        driverScheduleDAO.delete(ds.getShiftId(), d.getEmployeeId());
    }

    public void insertDriverShift(DriverShift ds) {
        shiftDAO.addToMap(ds.getShiftId(), ds);
        shiftDAO.insert(ds.getShiftId(), ds.getDate().toString());
        driverShiftDAO.addToMap(ds.getShiftId(), ds);
        driverShiftDAO.insert(ds.getShiftId(), ds.getDriversCount());
    }

    public void updateDriverShift(DriverShift ds, String property) {
        String shiftId = Integer.toString(ds.getShiftId());
        switch (property) {
            case "DriversCount": {
                driverShiftDAO.update(shiftId, property, ds.getDriversCount());
                break;
            }
        }
    }

    public void insertEmployeeConstraint(EmployeeConstraint ec) {
        employeeConstraintDAO.addToMap(ec.getConstraintId(), ec);
        employeeConstraintDAO.insert(ec.getConstraintId(), ec.getShiftId(), ec.getDescription());
    }

    public void updateEmployeeConstraint(EmployeeConstraint ec, String property) {
        int constraintId = ec.getShiftId();
        switch (property) {
            case "ShiftId": {
                employeeConstraintDAO.update(constraintId, property, ec.getShiftId());
                break;
            }
            case "DriversCount": {
                employeeConstraintDAO.update(constraintId, property, ec.getDescription());
                break;
            }
        }
    }

    public void deleteEmployeeConstraint(EmployeeConstraint ec) {
        employeeConstraintDAO.removeFromMap(ec.getConstraintId());
        employeeConstraintDAO.delete(ec.getConstraintId());
    }

    public void insertBranchShift(BranchShift bs) {
        shiftDAO.addToMap(bs.getShiftId(), bs);
        shiftDAO.insert(bs.getShiftId(), bs.getDate().toString());
        branchShiftDAO.addToMap(bs.getShiftId(), bs);
        HashMap<String, Integer> roleCounts = bs.getRoleCounts();
        branchShiftDAO.insert(bs.getShiftId(), bs.getBranch(), bs.getTypeBS(), roleCounts.get("Shift Manager"), roleCounts.get("Store Keeper"), roleCounts.get("Cashier"), roleCounts.get("General Employee"), roleCounts.get("Guard"), roleCounts.get("Cleaner"), roleCounts.get("Merchandiser"));
    }

    public void updateShift(Shift s, String property) {
        int shiftId = s.getShiftId();
        switch (property) {
            case "Date": {
                shiftDAO.update(shiftId, property, s.getDate().toString());
                break;
            }
        }
    }

    public void updateBranchShift(BranchShift bs, String property) {
        int shiftId = bs.getShiftId();
        switch (property) {
            case "Date": {
                branchShiftDAO.update(shiftId, property, bs.getDate());
                break;
            }
            case "Branch": {
                branchShiftDAO.update(shiftId, property, bs.getBranch());
                break;
            }
        }
    }

    public void insertProductCancellationToShift(int shiftId, ProductCancellation pc) {
        productCancellationToShiftDAO.addToMap(pc.getProductId(), pc.getEmployeeId(), shiftId);
        productCancellationToShiftDAO.insert(pc.getProductId(), pc.getEmployeeId(), shiftId);
    }

    public void deleteProductCancellation(int shiftId, ProductCancellation pc) {
        productCancellationToShiftDAO.removeFromMap(pc.getProductId(), pc.getEmployeeId(), shiftId);
        productCancellationToShiftDAO.delete(pc.getProductId(), pc.getEmployeeId(), shiftId);
    }

    public void updateRoleCount(int shiftId, String role, int newCount, String property) {
        switch (property) {
            case "CountNeeded":
            case "CountAssigned": {
                roleCountDAO.update(shiftId, role, property, newCount);
                break;
            }
        }
    }

    public void insertSchedule(int shiftId, String employeeId, String role) {
        scheduleDAO.addToMap(shiftId, employeeId, role);
        scheduleDAO.insert(shiftId, employeeId, role);
    }

    public void deleteSchedule(int shiftId, String employeeId) {
        scheduleDAO.removeFromMap(shiftId, employeeId);
        scheduleDAO.delete(shiftId, employeeId);
    }


    public void insertTimesOfShifts(String address, String timeOfShift) {
        timesOfShiftsDAO.addToMap(address, timeOfShift);
        timesOfShiftsDAO.insert(address, timeOfShift);
    }

    public void deleteTimesOfShifts(String address, String timeOfShift) {
        timesOfShiftsDAO.removeFromMap(address, timeOfShift);
        timesOfShiftsDAO.delete(address, timeOfShift);
    }


    //endregion


}
