package ServiceLayer;

import BusinessLayer.Delivery.DeliveryController;
import BusinessLayer.Delivery.DeliveryOrder;
import BusinessLayer.Delivery.ResourceController;
import BusinessLayer.Human_Resources.BranchController;
import BusinessLayer.Human_Resources.EmployeeController;
import BusinessLayer.Human_Resources.ShiftController;
import DataAccessLayer.DALFacade;
import ServiceLayer.JsonService.JsonConverter;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class SystemFacade {
    private static SystemFacade instance = null;
    private static DALFacade dalFacade = null;
    private DeliveryService deliveryService;
    private HRService hrService;

    private SystemFacade() {
        dalFacade = DALFacade.getInstance();
        EmployeeController employeeController = new EmployeeController();
        ResourceController resourceController = new ResourceController(employeeController);
        BranchController branchController = new BranchController(resourceController);
        ShiftController shiftController = new ShiftController(employeeController, branchController);
        DeliveryController deliveryController = new DeliveryController(resourceController, shiftController);
        shiftController.setDeliveryController(deliveryController);
        deliveryService = new DeliveryService(deliveryController, resourceController);
        hrService = new HRService(shiftController, employeeController, branchController);
    }

    public static SystemFacade getInstance() {
        if (instance == null) instance = new SystemFacade();
        return instance;
    }

    public static void deleteData() {
        if(dalFacade != null) {
            dalFacade.deleteData();
            dalFacade.deleteInstance();
            dalFacade = DALFacade.getInstance();
        }
    }

    public void DeleteInstance() {
        deleteData();
        instance = null;
        hrService = null;
        deliveryService = null;
        dalFacade = null;
    }

    public void resetData() throws JsonProcessingException {
        deleteData();
        dalFacade = DALFacade.getInstance();
        EmployeeController employeeController = new EmployeeController();
        ResourceController resourceController = new ResourceController(employeeController);
        BranchController branchController = new BranchController(resourceController);
        ShiftController shiftController = new ShiftController(employeeController, branchController);
        DeliveryController deliveryController = new DeliveryController(resourceController, shiftController);
        shiftController.setDeliveryController(deliveryController);
        deliveryService = new DeliveryService(deliveryController, resourceController);
        hrService = new HRService(shiftController, employeeController, branchController);
        registerEmployee("123456789", "Adi", "Cohen", "Ac123456", 30, 30, 50, "aaaaaaaaaaaaaaa", "aaaaaaaaaa", LocalDateTime.now(), "0547869234", 2);
        registerEmployee("207828120", "Omer", "Dor", "Om123456", 30, 30, 50, "aaaaaaaaaaaaaaa", "aaaaaaaaaa", LocalDateTime.of(2023,1,1,0,0), "0525277497", 2);
        LocalTime startMHour = LocalTime.of(8, 0);
        LocalTime endMHour = LocalTime.of(13, 0);
        LocalTime startEHour = LocalTime.of(13, 0);
        LocalTime endEHour = LocalTime.of(22, 0);
        addBranch("Beer Sheva", "0525381648", "Anna Zack", 1, startMHour, startEHour, endMHour, endEHour, 31.25181, 34.7913);
        addBranch("Tel Aviv", "0501234567", "Noa Kirel", 1, startMHour, startEHour, endMHour, endEHour, 32.109333, 34.855499);
        insertTimeOfShift("Beer Sheva", "TUESDAY", "E");
        insertTimeOfShift("Beer Sheva", "TUESDAY", "M");
        insertTimeOfShift("Beer Sheva", "FRIDAY", "E");
        insertTimeOfShift("Beer Sheva", "FRIDAY", "M");
        insertTimeOfShift("Beer Sheva", "SATURDAY", "E");
        insertTimeOfShift("Beer Sheva", "SATURDAY", "M");
        insertTimeOfShift("Beer Sheva", "SUNDAY", "E");
        insertTimeOfShift("Beer Sheva", "SUNDAY", "M");
        insertTimeOfShift("Beer Sheva", "MONDAY", "E");
        insertTimeOfShift("Beer Sheva", "MONDAY", "M");
        insertTimeOfShift("Beer Sheva", "THURSDAY", "E");
        insertTimeOfShift("Beer Sheva", "THURSDAY", "M");
        insertTimeOfShift("Beer Sheva", "WEDNESDAY", "E");
        insertTimeOfShift("Beer Sheva", "WEDNESDAY", "M");
        insertTimeOfShift("Tel Aviv", "TUESDAY", "E");
        insertTimeOfShift("Tel Aviv", "TUESDAY", "M");
        insertTimeOfShift("Tel Aviv", "FRIDAY", "E");
        insertTimeOfShift("Tel Aviv", "FRIDAY", "M");
        insertTimeOfShift("Tel Aviv", "SATURDAY", "E");
        insertTimeOfShift("Tel Aviv", "SATURDAY", "M");
        insertTimeOfShift("Tel Aviv", "SUNDAY", "E");
        insertTimeOfShift("Tel Aviv", "SUNDAY", "M");
        insertTimeOfShift("Tel Aviv", "MONDAY", "E");
        insertTimeOfShift("Tel Aviv", "MONDAY", "M");
        insertTimeOfShift("Tel Aviv", "THURSDAY", "E");
        insertTimeOfShift("Tel Aviv", "THURSDAY", "M");
        insertTimeOfShift("Tel Aviv", "WEDNESDAY", "E");
        insertTimeOfShift("Tel Aviv", "WEDNESDAY", "M");
        LocalDateTime date1 = LocalDateTime.of(2023, 5, 18, 8, 0);
        LocalDateTime date2 = LocalDateTime.of(2023, 7, 3, 8, 0);
        LocalDateTime date3 = LocalDateTime.of(2023, 5, 13, 8, 0);
        LocalDateTime date4 = LocalDateTime.of(2023, 5, 13, 15, 0);
        addBranchShift(date1, 5, 5, 5, 5, 5, 5, 5, "Beer Sheva", "M");
        addBranchShift(date2, 5, 5, 5, 5, 5, 5, 5, "Tel Aviv", "E");
        addBranchShift(date3, 5, 5, 5, 5, 5, 5, 5, "Beer Sheva", "M");//
        addBranchShift(date4, 5, 5, 5, 5, 5, 5, 5, "Beer Sheva", "E");//
        addBranchShift(date3, 5, 5, 5, 5, 5, 5, 5, "Tel Aviv", "M");//
        addBranchShift(date4, 5, 5, 5, 5, 5, 5, 5, "Tel Aviv", "E");//
        addDriverShift(date1, 4);
        addDriverShift(date2, 4);
        addDriverShift(date3, 4);
        registerBranchEmployee("555555555", "aaa", "aaa", "Ee5555555", 23, 23, 30, "aaa", "aaaaaaaa", LocalDateTime.of(2023,1,1,0,0), "0524479255", false, true);
        insertRole("555555555", "cashier");
        registerBranchEmployee("666666666", "bbb", "bbb", "Ff6666666", 24, 24, 30, "aaa", "aaaaaaaa", LocalDateTime.of(2023,1,1,0,0), "0544298340", false, false);
        registerBranchEmployee("777777777", "ccc", "ccc", "Gg7777777", 25, 25, 30, "aaa", "aaaaaaaa", LocalDateTime.of(2023,1,1,0,0), "0544298920", true, true);
        registerBranchEmployee("888888888", "ddd", "ddd", "Hh888888888", 26, 26, 30, "aaa", "aaaaaaaa", LocalDateTime.of(2023,1,1,0,0), "0546765234", false, false);
        registerBranchEmployee("999999999", "eee", "eee", "Ii999999999", 27, 27, 30, "aaa", "aaaaaaaa", LocalDateTime.of(2023,1,1,0,0), "0549879754", false, false);
        registerBranchEmployee("121121121", "fff", "fff", "Jj121121121", 28, 28, 30, "aaa", "aaaaaaaa", LocalDateTime.of(2023,1,1,0,0), "0543457970", false, false);
        insertRole("666666666", "store keeper");
        insertRole("888888888", "store keeper");
        insertRole("999999999", "store keeper");
        insertRole("121121121", "store keeper");
        addEmployeeToSchedule("555555555", 0);
        addEmployeeToSchedule("666666666", 2);
        addEmployeeToSchedule("777777777", 1);
        addEmployeeToSchedule("888888888", 3);
        addEmployeeToSchedule("999999999", 4);
        addEmployeeToSchedule("121121121", 5);
        scheduleEmployeeToRole("555555555", 0, "cashier");
        scheduleEmployeeToRole("666666666", 2, "store keeper");
        scheduleEmployeeToRole("888888888", 3, "store keeper");
        scheduleEmployeeToRole("999999999", 4, "store keeper");
        scheduleEmployeeToRole("121121121", 5, "store keeper");
        addConstraint("666666666", 1, "abc");
        addCancellation(1, "18-19", "555555555");
        //drivers
        registerDriver("111111111", "Moti", "Luchim", "Aa1111111", 50, 50, 30, "eeeeeeeeeeeee", "eeeeeeeeeee",LocalDateTime.of(2023,1,1,0,0), "0528377489", 5000);
        registerDriver("222222222", "Simha", "Gora", "Bb2222222", 60, 60, 30, "fffffffffff", "fffffffffff", LocalDateTime.of(2023,1,1,0,0), "0528637240", 8000);
        registerDriver("333333333", "Beri", "Tzacala", "Cc3333333", 70, 70, 30, "gggggggggggg", "ggggggggggg", LocalDateTime.of(2023,1,1,0,0), "0549870289", 5000);
        registerDriver("444444444", "Yoad", "Shtazaz", "Dd4444444", 80, 80, 30, "hhhhhhhhhhh", "hhhhhhhhhh", LocalDateTime.of(2023,1,1,0,0), "0538234790", 8000);
        addConstraint("222222222", 3, "abc");
        addDriverSchedule("111111111", 8);
        addDriverQualification("333333333", "Refrigeration");
        addDriverQualification("444444444", "Refrigeration");

        DeliveryOrder.Triplet<String, Integer, Double> item0 = new DeliveryOrder.Triplet<>("000-000", 15, 0.25);
        DeliveryOrder.Triplet<String, Integer, Double> item1 = new DeliveryOrder.Triplet<>("111-111", 30, 0.2);
        DeliveryOrder.Triplet<String, Integer, Double> item2 = new DeliveryOrder.Triplet<>("222-222", 5, 1.0);
        DeliveryOrder.Triplet<String, Integer, Double> item3 = new DeliveryOrder.Triplet<>("333-333", 20, 0.5);
        DeliveryOrder.Triplet<String, Integer, Double> item4 = new DeliveryOrder.Triplet<>("444-444", 20, 0.25);
        DeliveryOrder.Triplet<String, Integer, Double> item5 = new DeliveryOrder.Triplet<>("555-555", 10, 1.5);
        DeliveryOrder.Triplet<String, Integer, Double> item6 = new DeliveryOrder.Triplet<>("666-666", 15, 0.4);
        DeliveryOrder.Triplet<String, Integer, Double> item7 = new DeliveryOrder.Triplet<>("777-777", 10, 0.5);
        LinkedList<DeliveryOrder.Triplet<String, Integer, Double>> list1 = new LinkedList<>();
        LinkedList<DeliveryOrder.Triplet<String, Integer, Double>> list2 = new LinkedList<>();
        LinkedList<DeliveryOrder.Triplet<String, Integer, Double>> list3 = new LinkedList<>();
        LinkedList<DeliveryOrder.Triplet<String, Integer, Double>> list4 = new LinkedList<>();
        list1.add(item0);
        list1.add(item1);
        list2.add(item2);
        list2.add(item3);
        list3.add(item4);
        list3.add(item5);
        list4.add(item6);
        list4.add(item7);
        DeliveryOrder.Shipment ship1 = new DeliveryOrder.Shipment("13/04/2023", "Tel Aviv", "Beer Sheva", "Avi", "Moshe", "0501111111", "0502222222", list1);
        DeliveryOrder.Shipment ship2 = new DeliveryOrder.Shipment("14/04/2023", "Beer Sheva", "Tel Aviv", "Moshe", "Avi", "0502222222", "0501111111", list2);
        DeliveryOrder.Shipment ship3 = new DeliveryOrder.Shipment("15/04/2023", "Haifa", "Jerusalem", "Yossi", "Dan", "0503333333", "0504444444", list3);
        DeliveryOrder.Shipment ship4 = new DeliveryOrder.Shipment("16/04/2023", "Jerusalem", "Haifa", "Dan", "Yossi", "0504444444", "0503333333", list4);
        JsonConverter.toJson(ship1);
        JsonConverter.toJson(ship2);
        JsonConverter.toJson(ship3);
        JsonConverter.toJson(ship4);
        addSite("Haifa", "0503333333", "avi", 1, 1, 62, 58);
        addSite("Jerusalem", "0504444444", "Dan", 1, 1, 61, 58);
        addSite("Ashdod", "0525381648", "Anna Zack", 3, 2, 60, 57);
        parseOrder(JsonConverter.toJson(ship1));
        parseOrder(JsonConverter.toJson(ship2));
        parseOrder(JsonConverter.toJson(ship3));
        parseOrder(JsonConverter.toJson(ship4));
        addTruck(1234, "Regular", "Mazda", 1500, 4000);
        addTruck(4321, "Refrigeration", "Honda", 1500, 4000);
        addTruck(5678, "Regular", "Mitsubishi", 2000, 7000);
        addTruck(8765, "Refrigeration", "Mercedes", 2000, 7000);

        addDelivery(LocalDate.of(2023, 5, 13), LocalTime.of(11, 0), 1234, "111111111", "Tel Aviv");
        addStopToDelivery(0, "Beer Sheva");
        addItemToDelivery(0, 0, true, 0);
        approveDelivery(0);
    }

    //region PresentationLayer.GUI.Delivery module
    public DeliveryService getDeliveryService() {
        return deliveryService;
    }

    //region Truck functions
    public String addTruck(int id, String type, String model, float netWeight, float maxWeight) {
        return deliveryService.addTruck(id, type, model, netWeight, maxWeight);
    }

    public String removeTruck(int truckId) {
        return deliveryService.removeTruck(truckId);
    }

    public String updateTruckStatus(int truckId, int status) {
        return deliveryService.updateTruckStatus(truckId, status);
    }

    public String addTruckDeliveryDate(int truckId, LocalDate deliveryDate) {
        return deliveryService.addTruckDeliveryDate(truckId, deliveryDate);
    }

    public String removeTruckDeliveryDate(int truckId, LocalDate deliveryDate) {
        return deliveryService.removeTruckDeliveryDate(truckId, deliveryDate);
    }

    public String showTruck(int truckId) {
        return deliveryService.showTruck(truckId);
    }

//endregion

    public String showAllTrucks() {
        return deliveryService.showAllTrucks();
    }

    //region Site functions
    public String addSite(String address, String phoneNumber, String contactName, int region, int type, double latitude, double longitude) {
        return deliveryService.addSite(address, phoneNumber, contactName, region, type, latitude, longitude);
    }

    public String removeSite(String address) {
        return deliveryService.removeSite(address);
    }

    public String updatePhoneNumber(String address, String phoneNumber) {
        return deliveryService.updatePhoneNumber(address, phoneNumber);
    }

    public String updateContactName(String address, String contactName) {
        return deliveryService.updateContactName(address, contactName);
    }

    public String updateSiteCoordinate(String address, double latitude, double longitude) {
        return deliveryService.updateSiteCoordinate(address, latitude, longitude);
    }

    public String showSite(String address) {
        return deliveryService.showSite(address);
    }

    //endregion

    public String showAllSites() {
        return deliveryService.showAllSites();
    }

    //region PresentationLayer.GUI.Delivery functions
    public String parseOrder(String deliveryOrder) {
        return deliveryService.parseOrder(deliveryOrder);
    }

    public String addDelivery(LocalDate date, LocalTime time, int truckId, String driverId, String address) {
        return deliveryService.addDelivery(date, time, truckId, driverId, address);
    }

    public String cancelDelivery(int deliveryId) {
        return deliveryService.cancelDelivery(deliveryId);
    }

    public String updateDeliveryTruck(int deliveryId, int truckId) {
        return deliveryService.updateDeliveryTruck(deliveryId, truckId);
    }

    public String updateDeliveryDriver(int deliveryId, String driverId) {
        return deliveryService.updateDeliveryDriver(deliveryId, driverId);
    }

    public String addStopToDelivery(int deliveryId, String address) {
        return deliveryService.addStopToDelivery(deliveryId, address);
    }

    public String updateArriveTimeToStop(int deliveryId, String address, LocalTime time) {
        return deliveryService.updateArriveTimeToStop(deliveryId, address, time);
    }

    public String removeStopFromDelivery(int deliveryId, String address) {
        return deliveryService.removeStopFromDelivery(deliveryId, address);
    }

    public String addItemToDelivery(int deliveryId, int itemOrderId, boolean direct, int stage) {
        return deliveryService.addItemToDelivery(deliveryId, itemOrderId, direct, stage);
    }

    public String removeItemFromDelivery(int deliveryId, int itemOrderId) {
        return deliveryService.removeItemFromDelivery(deliveryId, itemOrderId);
    }

    public String approveDelivery(int deliveryId) {
        return deliveryService.approveDelivery(deliveryId);
    }

    public String visitStop(int deliveryId, String address, float weight) {
        return deliveryService.visitStop(deliveryId, address, weight);
    }

    public String switchTruck(int deliveryId, int truckId, float currentWeight) {
        return deliveryService.switchTruck(deliveryId, truckId, currentWeight);
    }

    public String switchTruckDriver(int deliveryId, int truckId, String driverId, float currentWeight) {
        return deliveryService.switchTruckDriver(deliveryId, truckId, driverId, currentWeight);
    }

    public String skipStopLoad(int deliveryId) {
        return deliveryService.skipStopLoad(deliveryId);
    }

    public String leaveItem(int deliveryId, int itemOrderId) {
        return deliveryService.leaveItem(deliveryId, itemOrderId);
    }

    public String showDelivery(int deliveryId) {
        return deliveryService.showDelivery(deliveryId);
    }

    public String showAllDeliveries(){
        return deliveryService.showAllDeliveries();
    }

    public String showDeliveryOrder(int deliveryOrderId) {
        return deliveryService.showDeliveryOrder(deliveryOrderId);
    }

    public String showAllDeliveryOrders() {
        return deliveryService.showAllDeliveryOrders();
    }

    public String showAllItemOrders(){
        return deliveryService.showAllItemOrders();
    }
    //endregion
    //endregion

    public String showDeliveryByShift(String employeeId, int shiftId) {
        String message = hrService.isShiftManagerAndStoreKeeper(employeeId, shiftId);
        if (!message.equals("true")) return message;
        else return deliveryService.showDeliveryByShift(shiftId);
    }

    //region Employee functions

    //region HR module
    public HRService getHrService() {
        return hrService;
    }

    public Set<String> getEmployeeIds() { return hrService.getEmployeeIds(); }

    public String showEmployees() {
        return hrService.showEmployees();
    }

    public String showAllConstraintToEmployee(String employeeId) {
        return hrService.showAllConstraintToEmployee(employeeId);
    }

    public String getFirstName(String employeeId) {
        return hrService.getFirstName(employeeId);
    }

    public String setFirstName(String employeeId, String _firstName) {
        return hrService.setFirstName(employeeId, _firstName);
    }

    public String getLastName(String employeeId) {
        return hrService.getLastName(employeeId);
    }

    public String setLastName(String employeeId, String _lastName) {
        return hrService.setLastName(employeeId, _lastName);
    }

    public String registerDetails(String _employeeId) {
        return hrService.registerDetails(_employeeId);
    }

    public String editPassword(String employeeId, String password) {
        return hrService.editPassword(employeeId, password);
    }

    public String getAccountNumber(String employeeId) {
        return hrService.getAccountNumber(employeeId);
    }

    public String setAccountNumber(String employeeId, int _accountNumber) {
        return hrService.setAccountNumber(employeeId, _accountNumber);
    }

    public String getBranchBankNumber(String employeeId) {
        return hrService.getBranchBankNumber(employeeId);
    }

    public String setBranchBankNumber(String employeeId, int _branchBankNumber) {
        return hrService.setBranchBankNumber(employeeId, _branchBankNumber);
    }

    public String getSalary(String employeeId) {
        return hrService.getSalary(employeeId);
    }

    public String setSalary(String employeeId, int _salary) {
        return hrService.setSalary(employeeId, _salary);
    }

    public String getTermsOfEmployment(String employeeId) {
        return hrService.getTermsOfEmployment(employeeId);
    }

    public String setTermsOfEmployment(String employeeId, String _termsOfEmployment) {
        return hrService.setTermsOfEmployment(employeeId, _termsOfEmployment);
    }

    public String getStatusOfEmployee(String employeeId) {
        return hrService.getStatusOfEmployee(employeeId);
    }

    public String setStatusOfEmployee(String employeeId, String _statusOfEmployee) {
        return hrService.setStatusOfEmployee(employeeId, _statusOfEmployee);
    }

    public String setPhoneNumber(String employeeId, String _phoneNumber) {
        return hrService.setPhoneNumber(employeeId, _phoneNumber);
    }

    public String getHireDate(String employeeId) {
        return hrService.getHireDate(employeeId);
    }

    public String isHRManager(String employeeId) {
        return hrService.isHRManager(employeeId);
    }

    public String setHRManager(String employeeId) {
        return hrService.setHRManager(employeeId);
    }

    public String getCancellation(String employeeId) {
        return hrService.getCancellation(employeeId);
    }

    public String setCancellation(String employeeId) {
        return hrService.setCancellation(employeeId);
    }

    public String getManagement(String employeeId) {
        return hrService.getManagement(employeeId);
    }

    public String setManagement(String employeeId) {
        return hrService.setManagement(employeeId);
    }

    public String getRolesOfEmployee(String employeeId) {
        return hrService.getRolesOfEmployee(employeeId);
    }

    public String editConstraintDescription(String employeeId, int constraintId, String description) {
        return hrService.editConstraintDescription(employeeId, constraintId, description);
    }

    public String registerBranchEmployee(String _employeeId, String _firstName, String _lastName, String _password, int _accountNumber, int _branchBankNumber, int _salary, String _termsOfEmployment, String _statusOfEmployee, LocalDateTime _hireDate, String _phoneNumber, boolean _management, boolean _cancellations) {
        return hrService.registerBranchEmployee(_employeeId, _firstName, _lastName, _password, _accountNumber, _branchBankNumber, _salary, _termsOfEmployment, _statusOfEmployee, _hireDate, _phoneNumber, _management, _cancellations);
    }

    public String registerDriver(String _employeeId, String _firstName, String _lastName, String _password, int _accountNumber, int _branchBankNumber, int _salary, String _termsOfEmployment, String _statusOfEmployee, LocalDateTime _hireDate, String _phoneNumber, int _licenses) {
        return hrService.registerDriver(_employeeId, _firstName, _lastName, _password, _accountNumber, _branchBankNumber, _salary, _termsOfEmployment, _statusOfEmployee, _hireDate, _phoneNumber, _licenses);
    }

    public String registerEmployee(String _employeeId, String _firstName, String _lastName, String _password, int _accountNumber, int _branchBankNumber, int _salary, String _termsOfEmployment, String _statusOfEmployee, LocalDateTime _hireDate, String _phoneNumber,int type){
        return hrService.registerEmployee(_employeeId, _firstName, _lastName, _password, _accountNumber, _branchBankNumber, _salary, _termsOfEmployment, _statusOfEmployee, _hireDate, _phoneNumber,type);
    }

    public String insertRole(String employeeId, String role) {
        return hrService.insertRole(employeeId, role);
    }

    public String removeEmployee(String employeeId) {
        return hrService.removeEmployee(employeeId);
    }

    public String removeConstraint(String employeeId, int constraintId) {
        return hrService.removeConstraint(employeeId, constraintId);
    }

    public String removeRole(String employeeId, String role) {
        return hrService.removeRole(employeeId, role);
    }

    public String isEmployeeExists(String employeeId) {
        return hrService.isEmployeeExists(employeeId);
    }

    public String isConstraintExist(String employeeId, int constraintId) {
        return hrService.isConstraintExist(employeeId, constraintId);
    }

    public String isRoleExist(String role) {
        return hrService.isRoleExist(role);
    }

    public String login(String employeeId, String password) {
        return hrService.login(employeeId, password);
    }

    public String logout(String employeeId) {
        return hrService.logout(employeeId);
    }

    public String upgradeDriverLicense(String driverId, int license) {
        return hrService.upgradeDriverLicense(driverId, license);
    }

    public String addDriverQualification(String driverId, String qualification) {
        return hrService.addDriverQualification(driverId, qualification);
    }

    public String removeDriverQualification(String driverId, String qualification) {
        return hrService.removeDriverQualification(driverId, qualification);
    }

    public String hasDriverQualification(String driverId, String qualification) {
        return hrService.hasDriverQualification(driverId, qualification);
    }

    public String addDriverDeliveryDate(String driverId, LocalDate deliveryDate) {
        return hrService.addDriverDeliveryDate(driverId, deliveryDate);
    }

    public String removeDriverDeliveryDate(String driverId, LocalDate deliveryDate) {
        return hrService.removeDriverDeliveryDate(driverId, deliveryDate);
    }

    public String showDriver(String driverId) {
        return hrService.showDriver(driverId);
    }
    //endregion

    public String showAllDrivers() {
        return hrService.showAllDrivers();
    }

    //region Branch functions
    public List<String> getBranchesList(){ return hrService.getBranchesList(); }

    public String showBranch(String address) {
        return hrService.showBranch(address);
    }

    public String showAllBranches() {
        return hrService.showAllBranches();
    }

    public String showNamesOfBranches() {
        return hrService.showNamesOfBranches();
    }

    public String showBranchMorningShiftHours(String branchName) {
        return hrService.showBranchMorningShiftHours(branchName);
    }

    public String showBranchEveningShiftHours(String branchName) {
        return hrService.showBranchEveningShiftHours(branchName);
    }

    public String setBranchName(String branchName, String _branchName) {
        return hrService.setBranchName(branchName, _branchName);
    }

    public String changeStartHourOfBranch(String branchName, String shiftType, LocalTime shiftStartHour) {
        return hrService.changeStartHourOfBranch(branchName, shiftType, shiftStartHour);
    }

    public String changeEndHourOfBranch(String branchName, String shiftType, LocalTime shiftEndHour) {
        return hrService.changeEndHourOfBranch(branchName, shiftType, shiftEndHour);
    }

    public String addBranch(String branchName, String phoneNumber, String contactName, int region, LocalTime morningShiftStartHour, LocalTime eveningShiftStartHour, LocalTime morningShiftEndHour, LocalTime eveningShiftEndHour, double latitude, double longitude) {
        return hrService.addBranch(branchName, phoneNumber, contactName, region, 0, morningShiftStartHour, eveningShiftStartHour, morningShiftEndHour, eveningShiftEndHour, latitude, longitude);
    }

    public String insertTimeOfShift(String branchName, String day, String type) {
        return hrService.insertTimeOfShift(branchName, day, type);
    }

    public String removeBranch(String branchName) {
        return hrService.removeBranch(branchName);
    }

    public String removeTimeOfShift(String branchName, String day, String type) {
        return hrService.removeTimeOfShift(branchName, day, type);
    }
    //endregion

    public String isBranchExists(String branchName) {
        return hrService.isBranchExists(branchName);
    }

    //region Shift functions
    public String showShift(int shiftId) {
        return hrService.showShift(shiftId);
    }

    public String getSchedules(int shiftId) {
        return hrService.getSchedules(shiftId);
    }

    public String getPayment(String employeeId, int bonus, int numberOfHours) {
        return hrService.getPayment(employeeId, bonus, numberOfHours);
    }

    public String getDriverCounts(int _shiftId) {
        return hrService.getDriverCounts(_shiftId);
    }

    public String showRoleCounts(int shiftId, String role) {
        return hrService.showRoleCounts(shiftId, role);
    }

    public String showProductCancellation(int shiftId) {
        return hrService.showProductCancellation(shiftId);
    }

    public String showFutureBranchShifts() {
        return hrService.showFutureBranchShifts();
    }

    public String showBranchShifts() {
        return hrService.showBranchShifts();
    }

    public String showFutureDriversShifts() {
        return hrService.showFutureDriversShifts();
    }

    public String showDriversShifts() {
        return hrService.showDriversShifts();
    }

    public String shiftsDriverScheduledTo(String employeeId) {
        return hrService.shiftsDriverScheduledTo(employeeId);
    }

    public String shiftsDriverNotScheduledTo(String employeeId) {
        return hrService.shiftsDriverNotScheduledTo(employeeId);
    }

    public String showFutureShifts() {
        return hrService.showFutureShifts();
    }

    public String showEmployeeHisFutureShifts(String employeeId) {
        return hrService.showEmployeeHisFutureShifts(employeeId);
    }

    public String showBranchEmployeeHisPastShifts(String employeeId) {
        return hrService.showBranchEmployeeHisPastShifts(employeeId);
    }

    public String showWhoWasChosen(int shiftId) {
        return hrService.showWhoWasChosen(shiftId);
    }

    public String showFutureShifts(String employeeId) {
        return hrService.showFutureShifts(employeeId);
    }

    public String setDate(int shiftId, LocalDateTime date) {
        return hrService.setDate(shiftId, date);
    }

    public String setBranch(int shiftId, String branchName) {
        return hrService.setBranch(shiftId, branchName);
    }

    public String addBranchShift(LocalDateTime date, int storeKeepersCount, int cashiersCount, int shiftManagersCount, int generalEmployeesCount, int guardsCount, int cleanersCount, int merchandisersCount, String branch, String type) {
        return hrService.addBranchShift(date, storeKeepersCount, cashiersCount, shiftManagersCount, generalEmployeesCount, guardsCount, cleanersCount, merchandisersCount, branch, type);
    }

    public String addCancellation(int shiftId, String productId, String employeeId) {
        return hrService.addCancellation(shiftId, productId, employeeId);
    }

    public String addConstraint(String employeeId, int shiftId, String description) {
        return hrService.addConstraint(employeeId, shiftId, description);
    }

    public String addEmployeeToSchedule(String employeeId, int shiftId) {
        return hrService.addEmployeeToSchedule(employeeId, shiftId);
    }

    public String addDriverShift(LocalDateTime date, int driversCount) {
        return hrService.addDriverShift(date, driversCount);
    }

    public String addDriverSchedule(String employeeId, int shiftId) {
        return hrService.addDriverSchedule(employeeId, shiftId);
    }

    public String changeDriverSchedule(String employeeId, int shiftIdOld, int shiftIdNew) {
        return hrService.changeDriverSchedule(employeeId, shiftIdOld, shiftIdNew);
    }

    public String removeShift(int shiftId) {
        return hrService.removeShift(shiftId);
    }

    public String removeSchedule(String employeeId, int shiftId) {
        return hrService.removeSchedule(employeeId, shiftId);
    }

    public String removeCancellation(int _shiftId, String _productId, String _employeeId) {
        return hrService.removeCancellation(_shiftId, _productId, _employeeId);
    }

    public String changeStartHourOfShift(int shiftId, LocalTime newHour) {
        return hrService.changeStartHourOfShift(shiftId, newHour);
    }

    public String changeScheduleByRole(String employeeId, int shiftId, String role) {
        return hrService.changeScheduleByRole(employeeId, shiftId, role);
    }

    public String editConstraintShift(String employeeId, int constraintId, int shiftId) {
        return hrService.editConstraintShift(employeeId, constraintId, shiftId);
    }

    public String isValidDate(int year, int month, int day) {
        return hrService.isValidDate(year, month, day);
    }

    public String isValidTime(int hour, int minute) {
        return hrService.isValidTime(hour, minute);
    }

    public String isFutureShifts(int shiftId) {
        return hrService.isFutureShifts(shiftId);
    }

    public String scheduleEmployeeToRole(String employeeId, int shiftId, String role) {
        return hrService.scheduleEmployeeToRole(employeeId, shiftId, role);
    }

    public String availableEmployeesNamesBE(int shiftId) {
        return hrService.availableEmployeesNamesBE(shiftId);
    }

    public String availableEmployeesNamesD(int shiftId) {
        return hrService.availableEmployeesNamesD(shiftId);
    }

    public String alertAboutShifts() {
        return hrService.alertAboutShifts();
    }

    public String isEmployeeAvailable(int shiftId, String employeeId) {
        return hrService.isEmployeeAvailable(shiftId, employeeId);
    }

    public String showEmployee(String employeeId) {
        return hrService.showEmployee(employeeId);
    }

    public String isBranchEmployeeV(String employeeId){ return hrService.isBranchEmployeeV(employeeId); }

    public String isDriverV(String employeeId){ return hrService.isDriverV(employeeId);}
    //endregion
    //endregion

    public String showAllBranchShifts() {
        return hrService.showAllBranchShifts();
    }

    public List<String> getDriverShifts(){ return hrService.getDriverShifts();}

    public List<String> getBranchShifts(){
        return hrService.getBranchShifts();
    }

    public List<String> showEmployeeHisFutureShiftsList(String employeeId){ return hrService.showEmployeeHisFutureShiftsList(employeeId); }

    public List<String> showEmployeeHisShiftsList(String employeeId){ return hrService.showEmployeeHisShiftsList(employeeId); }

    public List<String> showAllConstraintToEmployeeList(String employeeId) { return hrService.showAllConstraintToEmployeeList(employeeId); }

    public String showBranchShift(int shiftId){ return hrService.showBranchShift(shiftId); }

    public String showDriverShift(int shiftId){ return hrService.showDriverShift(shiftId); }

    public List<String> getShiftIds(){ return hrService.getShiftIds(); }

    public String isBranchShift(String shiftId){
        return hrService.isBranchShift(shiftId);
    }

    public String isDriverShift(String shiftId){
        return hrService.isDriverShift(shiftId);
    }

}


