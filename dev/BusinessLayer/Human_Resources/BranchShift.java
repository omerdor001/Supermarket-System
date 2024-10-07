package BusinessLayer.Human_Resources;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class BranchShift extends Shift {
    private final HashMap<String, Integer> roleCounts;
    private final HashMap<String, Integer> scheduleToRoleCount;
    private final List<ProductCancellation> cancellations;
    private HashMap<String, String> schedules;
    private String branch;
    private String typeBS;

    public BranchShift(int _shiftId, LocalDateTime _date, int _storeKeepersCount, int _cashiersCount, int _shiftManagersCount, int _generalEmployeesCount, int _guardsCount, int _cleanersCount, int _merchandisersCount, String _branch,String _typeBS) {
        super(_shiftId, _date);
        branch = _branch;
        roleCounts = new HashMap<>();
        scheduleToRoleCount = new HashMap<>();
        cancellations = new LinkedList<>();
        schedules = new HashMap<>();
        typeBS=_typeBS;
        createSchedulesMap(_shiftManagersCount, _storeKeepersCount, _cashiersCount, _generalEmployeesCount, _guardsCount, _cleanersCount, _merchandisersCount);
        createSignMap();
    }

    public BranchShift(int _shiftId, LocalDateTime _date,HashMap<String,Integer> _roleCounts,HashMap<String,Integer> _scheduleToRoleCount,List<ProductCancellation> _cancellations,HashMap<String, String> _schedules, String _branch,String _typeBS) {
        super(_shiftId, _date);
        branch = _branch;
        roleCounts=_roleCounts;
        scheduleToRoleCount=_scheduleToRoleCount;
        cancellations=_cancellations;
        schedules=_schedules;
        typeBS=_typeBS;
    }

    private void createSchedulesMap(int _shiftManagersCount, int _storeKeepersCount, int _cashiersCount, int _generalEmployeesCount, int _guardsCount, int _cleanersCount, int _merchandisersCount) {
        roleCounts.put("Shift Manager", _shiftManagersCount);
        roleCounts.put("Store Keeper", _storeKeepersCount);
        roleCounts.put("Cashier", _cashiersCount);
        roleCounts.put("General Employee", _generalEmployeesCount);
        roleCounts.put("Guard", _guardsCount);
        roleCounts.put("Cleaner", _cleanersCount);
        roleCounts.put("Merchandiser", _merchandisersCount);
    }

    private void createSignMap() {
        scheduleToRoleCount.put("Shift Manager", 0);
        scheduleToRoleCount.put("Store Keeper", 0);
        scheduleToRoleCount.put("Cashier", 0);
        scheduleToRoleCount.put("General Employee", 0);
        scheduleToRoleCount.put("Guard", 0);
        scheduleToRoleCount.put("Cleaner", 0);
        scheduleToRoleCount.put("Merchandiser", 0);
    }

    public HashMap<String, Integer> getRoleCounts() {
        return roleCounts;
    }

    public List<ProductCancellation> getCancellations() {
        return cancellations;
    }

    public HashMap<String, Integer> getScheduleToRoleCount() {
        return scheduleToRoleCount;
    }

    public void addCancellation(String _productId, String _employeeId) {
        ProductCancellation productCancellation = new ProductCancellation(_productId, _employeeId);
        cancellations.add(productCancellation);
    }

    public void removeCancellation(String _productId, String _employeeId) {
        ProductCancellation productCancellation = new ProductCancellation(_productId, _employeeId);
        cancellations.remove(productCancellation);
    }

    public void addSchedule(String employeeId, String role) throws Exception {
        if (role.equals("Shift Manager")) {
            addShiftManager(employeeId);
            scheduleToRoleCount.put("Shift Manager", scheduleToRoleCount.get("Shift Manager") + 1);
        }
        if (role.equals("Store Keeper")) {
            addStoreKeeper(employeeId);
            scheduleToRoleCount.put("Store Keeper", scheduleToRoleCount.get("Store Keeper") + 1);
        }
        if (role.equals("Cashier")) {
            addCashier(employeeId);
            scheduleToRoleCount.put("Cashier", scheduleToRoleCount.get("Cashier") + 1);
        }
        if (role.equals("General Employee")) {
            addGeneralEmployee(employeeId);
            scheduleToRoleCount.put("General Employee", scheduleToRoleCount.get("General Employee") + 1);
        }
        if (role.equals("Guard")) {
            addGuard(employeeId);
            scheduleToRoleCount.put("Guard", scheduleToRoleCount.get("Guard") + 1);
        }
        if (role.equals("Cleaner")) {
            addCleaner(employeeId);
            scheduleToRoleCount.put("Cleaner", scheduleToRoleCount.get("Cleaner") + 1);
        }
        if (role.equals("Merchandiser")) {
            addMerchandiser(employeeId);
            scheduleToRoleCount.put("Merchandiser", scheduleToRoleCount.get("Merchandiser") + 1);
        }
    }

    @Override
    public List<String> getScheduleDrivers() {
        return null;
    }

    @Override
    public int getDriversCount() {
        return 0;
    }

    private void addShiftManager(String employeeId) throws Exception {
        if (scheduleToRoleCount.get("Shift Manager") >= roleCounts.get("Shift Manager"))
            throw new Exception("Shift already full with shift managers");
        schedules.put(employeeId, "Shift Manager");

    }

    private void addStoreKeeper(String employeeId) throws Exception {
        if (scheduleToRoleCount.get("Store Keeper") >= roleCounts.get("Store Keeper"))
            throw new Exception("Shift already full with store Keepers");
        schedules.put(employeeId, "Store Keeper");
    }

    private void addCashier(String employeeId) throws Exception {
        if (scheduleToRoleCount.get("Cashier") >= roleCounts.get("Cashier"))
            throw new Exception("Shift already full with cashiers");
        schedules.put(employeeId, "Cashier");
    }

    private void addGeneralEmployee(String employeeId) throws Exception {
        if (scheduleToRoleCount.get("General Employee") >= roleCounts.get("General Employee"))
            throw new Exception("Shift already full with general employees");
        schedules.put(employeeId, "General Employee");
    }

    private void addGuard(String employeeId) throws Exception {
        if (scheduleToRoleCount.get("Guard") >= roleCounts.get("Guard"))
            throw new Exception("Shift already full with guards");
        schedules.put(employeeId, "Guard");
    }

    private void addCleaner(String employeeId) throws Exception {
        if (scheduleToRoleCount.get("Cleaner") >= roleCounts.get("Cleaner"))
            throw new Exception("Shift already full with cleaners");
        schedules.put(employeeId, "Cleaner");
    }

    private void addMerchandiser(String employeeId) throws Exception {
        if (scheduleToRoleCount.get("Merchandiser") >= roleCounts.get("Merchandiser"))
            throw new Exception("Shift already full with merchandisers");
        schedules.put(employeeId, "Merchandiser");
    }

    @Override
    public String getType() {
        return "BS";
    }

    @Override
    public String getTypeBS() {
        return typeBS;
    }

    public void addEmployeeToSchedule(String employeeId) {
        schedules.put(employeeId, "");
    }

    public void removeSchedule(String employeeId) {
        schedules.remove(employeeId);
    }

    public void changeSchedule(String employeeId, String role) throws Exception {
        removeSchedule(employeeId);
        addSchedule(employeeId, role);
    }

    public void changeHour(LocalTime hour) {
        date = LocalDateTime.of(date.getYear(), date.getMonth(), date.getDayOfMonth(), hour.getHour(), hour.getMinute());
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public HashMap<String, String> getSchedules() {
        return schedules;
    }

}