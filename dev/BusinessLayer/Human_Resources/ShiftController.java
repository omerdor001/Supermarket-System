package BusinessLayer.Human_Resources;

import BusinessLayer.Delivery.DeliveryController;
import DataAccessLayer.DALFacade;
import ServiceLayer.JsonService.JsonConverter;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

public class ShiftController {
    private final BranchController branchController;
    private final EmployeeController employeeController;
    private DeliveryController deliveryController;
    private final DALFacade dalController;
    private int shiftId = 0;
    private HashMap<Integer, Shift> shifts;

    public ShiftController(EmployeeController _employeeController, BranchController _branchController) {
        shifts = new HashMap<>();
        employeeController = _employeeController;
        branchController = _branchController;
        deliveryController = null;
        dalController= DALFacade.getInstance();
        loadData();
    }

    public void loadData(){
        try{
            HashMap <String,Integer> counters = dalController.getAllCounters();
            setShiftId(counters.get("nextShiftId"));
            HashMap<Integer,BranchShift> branchShifts=dalController.getAllBranchShifts();
            for(Integer id:branchShifts.keySet()){
                shifts.put(id,branchShifts.get(id));
            }
            HashMap<Integer,DriverShift> driverShifts=dalController.getAllDriverShifts();
            for(Integer id:driverShifts.keySet()){
                shifts.put(id,driverShifts.get(id));
            }
        }
        catch (Exception e){
            throw new RuntimeException("DAL ERROR - ShiftController");
        }
    }

    private void setShifts(HashMap<Integer, Shift> _shifts){
        shifts=_shifts;
    }

    public String showShift(int shiftId) {
        if (!shifts.containsKey(shiftId)) throw new NoSuchElementException("Shift doesn't exist");
        if (shifts.get(shiftId).getType().equals("BS")) {
            return getDateTypeAndBranchOfShift(shiftId).toString();
        } else {
            return shifts.get(shiftId).getDate().toString() + " Driver Shift";
        }
    }

    private List<String> getDateTypeAndBranchOfShift(int shiftId) {
        if (!shifts.containsKey(shiftId)) throw new NoSuchElementException("Shift doesn't exist");
        BranchShift branchShift=getBranchShift(shiftId);
        List<String> shiftDetails = new LinkedList<>();
        shiftDetails.add(branchShift.getDate().toString());
        if (branchShift.getTypeBS().equals("M")) shiftDetails.add("Morning Shift");
        else shiftDetails.add("Evening Shift");
        shiftDetails.add(branchShift.getBranch());
        return shiftDetails;
    }

    public String getSchedules(int shiftId) throws Exception {
        if (!shifts.containsKey(shiftId)) throw new Exception("Shift doesn't exist");
        if (shifts.get(shiftId).getType().equals("DS")) {
            return getDriversSchedules(shiftId);
        } else return getBranchEmployeesSchedules(shiftId);
    }

    public String getBranchEmployeesSchedules(int shiftId) throws Exception {
        StringBuilder schedules = new StringBuilder();
        if (!shifts.containsKey(shiftId)) throw new Exception("Shift doesn't exist");
        Shift shift = shifts.get(shiftId);
        for (String role : shift.getRoleCounts().keySet()) {
            schedules.append(getScheduleOfRole(shiftId, role)).append("\n");
        }
        return schedules.toString();
    }

    public String getDriversSchedules(int shiftId) throws Exception {
        StringBuilder schedules = new StringBuilder();
        if (!shifts.containsKey(shiftId)) throw new Exception("Shift doesn't exist");
        Shift shift = shifts.get(shiftId);
        for (String id : shift.getScheduleDrivers()) {
            schedules.append(id).append("\n");
        }
        return schedules.toString();
    }

    private int getNumberOfShiftHours(int shiftId, int numberOfHours) throws Exception {
        if (!shifts.containsKey(shiftId)) throw new Exception("Shift doesn't exist");
        if (numberOfHours < 0) throw new Exception("Number of hours cannot be less then 0");
        if (numberOfHours > 0) return numberOfHours;
        Shift shift = getBranchShift(shiftId);
        String branchName = shift.getBranch();
        if (!branchController.getBranches().containsKey(branchName)) throw new Exception("Branch is not exist");
        Branch branch = branchController.getBranches().get(branchName);
        if (shift.getTypeBS().equals("M")) {
            int startHour = shift.getDate().getHour();
            int endHour = branch.getMorningShiftEndHour().getHour();
            return endHour - startHour;
        } else {
            int startHour = shift.getDate().getHour();
            int endHour = branch.getEveningShiftEndHour().getHour();
            return endHour - startHour;
        }
    }

    public String getPayment(String employeeId, int bonus, int numberOfHours) throws Exception {
        int payment = 0;
        int currentMonth = monthFromTenth();
        if (!employeeController.getEmployees().containsKey(employeeId)) throw new Exception("Employee doesn't exist");
        Employee employee = employeeController.getEmployees().get(employeeId);
        for (int id : shifts.keySet()) {
            Shift shift = shifts.get(id);
            if (!shift.getType().equals("DS")) {
                if (shift.getSchedules().containsKey(employeeId) && shift.getDate().toLocalDate().compareTo(LocalDate.of(LocalDate.now().getYear(), currentMonth, 10)) >= 0)
                    payment = payment + employee.getSalary() * getNumberOfShiftHours(id, numberOfHours);
            } else if (shift.getScheduleDrivers().contains(employeeId) && shift.getDate().compareTo(LocalDateTime.of(LocalDate.now().getYear(), currentMonth, 10, LocalDateTime.now().getHour(), LocalDateTime.now().getMinute())) > 0) {
                payment = payment + employee.getSalary();
            }
        }
        if (bonus < 0) throw new Exception("Bonus is negative");
        payment = payment + bonus;
        return Integer.toString(payment);
    }

    private String getScheduleOfRole(int shiftId, String role) throws Exception {
        if (!shifts.containsKey(shiftId)) throw new Exception("Shift doesn't exist");
        if (employeeController.isRoleExist(role).equals("false")) throw new Exception("Role doesn't exist");
        Shift shift = shifts.get(shiftId);
        for (String id : shift.getSchedules().keySet()) {
            if (shift.getSchedules().get(id).equals(role)) return role + ":" + id;
        }
        return "";
    }

    public String getDriverCounts(int _shiftId) throws Exception {
        if (!shifts.containsKey(_shiftId)) throw new Exception("Shift doesn't exist");
        if (!shifts.get(_shiftId).getType().equals("DS")) throw new Exception("Shift is not driver shift");
        return Integer.toString(shifts.get(_shiftId).getDriversCount());
    }

    public String showFutureDriversShifts() {
        StringBuilder allShifts = new StringBuilder();
        for (int id : shifts.keySet()) {
            Shift shift = shifts.get(id);
            if (shift.getType().equals("DS") && shifts.get(id).getDate().compareTo(LocalDateTime.now()) > 0)
                allShifts.append(id).append(" Date: ").append(shift.getDate()).append(" Type: ").append(shift.getType()).append("\n");
        }
        return allShifts.toString();
    }

    public String showDriversShifts() {
        StringBuilder allShifts = new StringBuilder();
        for (int id : shifts.keySet()) {
            Shift shift = shifts.get(id);
            if (shift.getType().equals("DS"))
                allShifts.append(id).append(" Date: ").append(shift.getDate()).append(" Type: ").append(shift.getType()).append("\n");
        }
        return allShifts.toString();
    }

    public List<String> getDriversShifts() {
        List<String> driverShifts=new LinkedList<>();
        for (int id : shifts.keySet()) {
            Shift shift = shifts.get(id);
            if (shift.getType().equals("DS") && shift.getDate().toLocalDate().compareTo(LocalDate.now())>0)
                driverShifts.add(id+" "+shift.getDate());
        }
        return driverShifts;
    }

    public String showFutureBranchShifts() {
        StringBuilder allShifts = new StringBuilder();
        for (int id : shifts.keySet()) {
            Shift shift = shifts.get(id);
            if (!shift.getType().equals("DS") && shifts.get(id).getDate().compareTo(LocalDateTime.now()) > 0)
                allShifts.append(id).append(" Date: ").append(shift.getDate()).append(" Type: ").append(shift.getType()).append(" Branch: ").append(shift.getBranch()).append("\n");
        }
        return allShifts.toString();
    }

    public String showBranchShifts() {
        StringBuilder allShifts = new StringBuilder();
        for (int id : shifts.keySet()) {
            Shift shift = shifts.get(id);
            if (!shift.getType().equals("DS"))
                allShifts.append(id).append(" Date: ").append(shift.getDate()).append(" Type: ").append(shift.getType()).append(" Branch: ").append(shift.getBranch()).append("\n");
        }
        return allShifts.toString();
    }

    public List<String> getBranchShiftsG() {
        List<String> branchShifts=new LinkedList<>();
        for (int id : shifts.keySet()) {
            Shift shift = shifts.get(id);
            if (shift.getType().equals("BS") && shift.getDate().toLocalDate().compareTo(LocalDate.now())>0)
                branchShifts.add(id+" "+shift.getDate());
        }
        return branchShifts;
    }

    public String showFutureShifts() {
        StringBuilder allShifts = new StringBuilder();
        for (int id : shifts.keySet()) {
            Shift shift = shifts.get(id);
            if (shifts.get(id).getDate().compareTo(LocalDateTime.now()) > 0)
                allShifts.append(id).append(" Date: ").append(shift.getDate()).append(" Type: ").append(shift.getType()).append("\n");
        }
        return allShifts.toString();
    }

    public String showFutureShifts(String employeeId) {
        if (!employeeController.getEmployees().containsKey(employeeId))
            throw new NoSuchElementException("Employee doesn't exist");
        if (employeeController.getEmployees().get(employeeId).getType() == Employee.RoleType.DRIVER)
            return showFutureDriversShifts();
        else if (employeeController.getEmployees().get(employeeId).getType() == Employee.RoleType.BRANCHEMPLOYEE)
            return showFutureBranchShifts();
        else throw new NoSuchElementException("Employee must be branch employee or driver");
    }

    public String showWhoWasChosen(int _shiftId) throws Exception {
        if (!shifts.containsKey(_shiftId)) throw new Exception("Shift doesn't exist");
        if (shifts.get(_shiftId).getType().equals("DS"))
            throw new Exception("In Driver shift there are no chosen employees, only scheduled ones ");
        StringBuilder chooses = new StringBuilder();
        for (Integer idS : shifts.keySet()) {
            if (idS == _shiftId) {
                for (String id : shifts.get(_shiftId).getSchedules().keySet()) {
                    chooses.append("ID: ").append(id).append(" First name: ").append(employeeController.getEmployees().get(id).getFirstName()).append(" Last Name: ").append(employeeController.getEmployees().get(id).getLastName()).append("\n");
                }
            }

        }
        return chooses.toString();
    }

    public String showHowMuchHRScheduled(int shiftId, String role) throws Exception {
        if (!shifts.containsKey(shiftId)) throw new Exception("Shift doesn't exist");
        if (employeeController.isRoleExist(role).equals("false")) throw new Exception("Role doesn't exist");
        Shift shift = shifts.get(shiftId);
        String _role;
        if (role.equals("shift manager") || role.equals("shift Manager")) _role = "Shift Manager";
        else if (role.equals("store keeper") || role.equals("store Keeper")) _role = "Store Keeper";
        else if (role.equals("general employee") || role.equals("general Employee")) _role = "General Employee";
        else if (Character.isLowerCase(role.charAt(0)))
            _role = Character.toUpperCase(role.charAt(0)) + role.substring(1);
        else _role = role;
        return shift.getScheduleToRoleCount().get(_role) + "/" + shift.getRoleCounts().get(_role);
    }

    public String showProductCancellation(int shiftId) throws Exception {
        if (!shifts.containsKey(shiftId)) throw new Exception("Shift doesn't exist");
        if (!shifts.get(shiftId).getType().equals("DS")) throw new Exception("Shift is in type of driver shift");
        Shift shift = shifts.get(shiftId);
        StringBuilder cancellations = new StringBuilder();
        for (ProductCancellation productCancellation : shift.getCancellations()) {
            cancellations.append(" ").append("\n").append("Product ID: ").append(productCancellation.getProductId()).append("\n").append("Employee ID: ").append(productCancellation.getEmployeeId()).append("\n");
        }
        return cancellations.toString();
    }

    public String showEmployeeHisShifts(String employeeId) throws Exception {
        if (!employeeController.getEmployees().containsKey(employeeId)) throw new Exception("Employee doesn't exist");
        if (employeeController.getEmployees().get(employeeId).getType() == Employee.RoleType.DRIVER)
            return shiftsDriverScheduledTo(employeeId);
        else if (employeeController.getEmployees().get(employeeId).getType() == Employee.RoleType.BRANCHEMPLOYEE)
            return showBranchEmployeeHisShifts(employeeId);
        else throw new NoSuchElementException("Employee must be branch employee or driver");
    }

    public String showBranchEmployeeHisPastShifts(String employeeId) {
        String shiftsForEmployee = "";
        for (Shift branchShift:getBranchShifts()) {
            if (branchShift.getDate().compareTo(LocalDateTime.now()) < 0) {
                for (String id : branchShift.getSchedules().keySet()) {
                    if (id.equals(employeeId)) {
                        String role = branchShift.getSchedules().get(employeeId);
                        if (branchShift.getTypeBS().equals("M"))
                            shiftsForEmployee = branchShift.getShiftId()+"Date: " + branchShift.getDate() + " Type: Morning Branch: " + branchShift.getBranch() + " Role: " + role + "\n";
                        else
                            shiftsForEmployee = branchShift.getShiftId()+"Date: " + branchShift.getDate() + " Type: Evening Branch: " + branchShift.getBranch() + " Role: " + role + "\n";
                    }
                }
            }
        }
        return shiftsForEmployee;
    }

    public String showBranchEmployeeHisShifts(String employeeId) throws Exception {
        String shiftsForEmployee = "";
        for (Shift shift : shifts.values()) {
            if (shift.getDate().compareTo(LocalDateTime.now()) >= 0) {
                for (String id : shift.getSchedules().keySet()) {
                    if (id.equals(employeeId)) {
                        String role = shift.getSchedules().get(employeeId);
                        if (shift.getTypeBS().equals("M"))
                            shiftsForEmployee = "Date: " + shift.getDate() + " Type: Morning Branch: " + shift.getBranch() + " Role: " + role + "\n";
                        else
                            shiftsForEmployee = "Date: " + shift.getDate() + " Type: Evening Branch: " + shift.getBranch() + " Role: " + role + "\n";
                    }
                }
            }
        }
        return shiftsForEmployee;
    }

    public List<String> showEmployeeHisFutureShiftsList(String employeeId) {
        List<String> shiftsForEmployee = new LinkedList<>();
        for (Shift shift : shifts.values()) {
            if (shift.getDate().compareTo(LocalDateTime.now()) >= 0) {
                if(shift.getType().equals("BS")){
                    for (String id : shift.getSchedules().keySet()) {
                        if (id.equals(employeeId)) {
                            if (shift.getTypeBS().equals("M"))
                                shiftsForEmployee.add(shift.getShiftId()+" "+"Date: " + shift.getDate() + " Type: Morning Branch: " + shift.getBranch());
                            else
                                shiftsForEmployee.add(shift.getShiftId()+" "+"Date: " + shift.getDate() + " Type: Evening Branch: " + shift.getBranch());
                        }
                    }
                }
                else {
                    for (String id : shift.getScheduleDrivers()) {
                        if (id.equals(employeeId)) {
                            shiftsForEmployee.add(shift.getShiftId()+" "+"Date: " + shift.getDate());
                        }
                    }
                }

            }
        }
        return shiftsForEmployee;
    }

    public List<String> showEmployeeHisShiftsList(String employeeId) {
        List<String> shiftsForEmployee = new LinkedList<>();
        for (Shift shift : shifts.values()) {
            if(shift.getType().equals("BS")){
                for (String id : shift.getSchedules().keySet()) {
                    if (id.equals(employeeId)) {
                        if (shift.getTypeBS().equals("M"))
                            shiftsForEmployee.add(shift.getShiftId()+" "+"Date: " + shift.getDate() + " Type: Morning Branch: " + shift.getBranch());
                        else
                            shiftsForEmployee.add(shift.getShiftId()+" "+"Date: " + shift.getDate() + " Type: Evening Branch: " + shift.getBranch());
                    }
                }
            }
            else {
                for (String id : shift.getScheduleDrivers()) {
                    if (id.equals(employeeId)) {
                        shiftsForEmployee.add(shift.getShiftId()+" "+"Date: " + shift.getDate());
                    }
                }
            }
        }
        return shiftsForEmployee;
    }

    public void setDate(int shiftId, LocalDateTime newDate) throws Exception {
        if (!shifts.containsKey(shiftId)) throw new Exception("Shift doesn't exist");
        isValidDate(newDate.getYear(), newDate.getMonth().getValue(), newDate.getDayOfMonth());
        if (!branchController.getBranches().get(shifts.get(shiftId).getBranch()).getTimeOfShifts().contains(newDate.getDayOfWeek() + " " + shifts.get(shiftId).getType()))
            throw new Exception("Branch is not working at this time");
        shifts.get(shiftId).setDate(newDate);
        dalController.updateShift(shifts.get(shiftId),"Date");
    }

    public void setBranch(int shiftId, String branchName) throws Exception {
        String branchName_ = branchController.getNameOfBranch(branchName);
        if (!shifts.containsKey(shiftId)) throw new Exception("Shift doesn't exist");
        if (shifts.get(shiftId).getType().equals("DS")) throw new Exception("Shift is in type of driver shift");
        if (!branchController.isBranchExists(branchName_)) throw new Exception("Branch doesn't exist");
        String timeOfShift = shifts.get(shiftId).getDate().getDayOfWeek() + " " + shifts.get(shiftId).getType();
        if (!branchController.getBranches().get(branchName_).getTimeOfShifts().contains(timeOfShift))
            throw new Exception("Branch is not working at shift time");
        shifts.get(shiftId).setBranch(branchName_);
        BranchShift branchShift=getBranchShift(shiftId);
        dalController.updateBranchShift(branchShift,"Branch");
    }

    public void addCancellation(int shiftId, String productId, String employeeId) throws Exception {
        if (!employeeController.getEmployees().containsKey(employeeId)) throw new Exception("Employee doesn't exist");
        if (!shifts.containsKey(shiftId)) throw new Exception("Shift doesn't exist");
        Employee employee = employeeController.getEmployees().get(employeeId);
        Shift shift = shifts.get(shiftId);
        if (!employee.isCancellations()) {
            throw new Exception("Employee missing cancellation training");
        }
        shift.addCancellation(productId, employeeId);
        dalController.insertProductCancellationToShift(shiftId,new ProductCancellation(productId,employeeId));
    }

    public void addEmployeeToSchedule(String employeeId, int shiftId) throws Exception {
        if (!employeeController.getEmployees().containsKey(employeeId)) throw new Exception("Employee doesn't exist");
        if (!shifts.containsKey(shiftId)) throw new Exception("Shift doesn't exist");
        if (shifts.get(shiftId).getSchedules().containsKey(employeeId))
            throw new Exception("Employee was already chosen by HR Manager to work at this shift");
        if (!availableEmployeesId(shiftId).contains(employeeId))
            throw new Exception("The employee " + employeeController.getEmployees().get(employeeId).getFirstName() + " " + employeeController.getEmployees().get(employeeId).getLastName() + " cannot work at this shift during his constraints");
        if (shifts.get(shiftId).getDate().toLocalDate().compareTo(employeeController.getEmployees().get(employeeId).getHireDate().toLocalDate()) < 0)
            throw new Exception("Employee cannot work at shifts yet");
        isAvailableDate(employeeId, shifts.get(shiftId).getDate(), shifts.get(shiftId).getTypeBS());
        shifts.get(shiftId).addEmployeeToSchedule(employeeId);
        dalController.insertSchedule(shiftId,employeeId,"");
    }

    public BranchShift getBranchShift(int _shiftId){
        if (!shifts.containsKey(_shiftId)) throw new NoSuchElementException("Shift doesn't exist");
        if(shifts.get(_shiftId).getType().equals("BS"))
            return (BranchShift) shifts.get(_shiftId);
        else throw new IllegalArgumentException("Shift is not branch shift");
    }

    private void isShiftExist(LocalDateTime date, String type, String branch) throws Exception {
        for (Shift shift : shifts.values()) {
            if (shift.getType().equals("BS") && shift.getDate().isEqual(date) && shift.getTypeBS().equals(type) && shift.getBranch().equals(branch))
                throw new Exception("Shift already exists");
        }
    }

    public void addBranchShift(LocalDateTime _date, int _storeKeepersCount, int _cashiersCount, int _shiftManagersCount, int _generalEmployeesCount, int _guardsCount, int _cleanersCount, int _merchandisersCount, String _branch,String type)throws Exception {
        LocalDateTime dateTime;
        isShiftExist(_date, type, _branch);
        isValidDate(_date.getYear(), _date.getMonth().getValue(), _date.getDayOfMonth());
        if (!branchController.isBranchExists(_branch)) throw new Exception("Branch is not exist");
        String branchName_ = branchController.getNameOfBranch(_branch);
        if(!type.equals("M") && !type.equals("E")) throw new Exception("Type is not illegal");
        if(type.equals("M")) dateTime = LocalDateTime.of(_date.getYear(), _date.getMonth(), _date.getDayOfMonth(), branchController.getBranches().get(branchName_).getMorningShiftStartHour().getHour(), branchController.getBranches().get(branchName_).getMorningShiftStartHour().getMinute());
        else dateTime = LocalDateTime.of(_date.getYear(), _date.getMonth(), _date.getDayOfMonth(), branchController.getBranches().get(branchName_).getEveningShiftStartHour().getHour(), branchController.getBranches().get(branchName_).getEveningShiftStartHour().getMinute());
        String timeOfShift = _date.getDayOfWeek() + " " + "M";
        if (!branchController.getBranches().get(branchName_).getTimeOfShifts().contains(timeOfShift))
            throw new Exception("Branch is not working at this day");
        if (_storeKeepersCount < 0) throw new Exception("Store keepers count must be zero or more");
        if (_cashiersCount < 0) throw new Exception("Cashiers count must be zero or more");
        if (_shiftManagersCount < 1) throw new Exception("Shift managers count must be one or more");
        if (_generalEmployeesCount < 0) throw new Exception("General employees count must be zero or more");
        if (_guardsCount < 0) throw new Exception("Guards count must be zero or more");
        if (_cleanersCount < 0) throw new Exception("Cleaners count must be zero or more");
        if (_merchandisersCount < 0) throw new Exception("Merchandisers count must be zero or more");
        BranchShift branchShift=new BranchShift(shiftId, dateTime, _storeKeepersCount, _cashiersCount, _shiftManagersCount, _generalEmployeesCount, _guardsCount, _cleanersCount, _merchandisersCount, branchController.getNameOfBranch(_branch),type);
        shifts.put(shiftId, branchShift);
        dalController.insertBranchShift(branchShift);
        shiftId = shiftId + 1;
        dalController.updateCounter("nextShiftId",shiftId);
    }

    public void addDriverShift(LocalDateTime _date, int _driversCount) throws Exception {
        isValidDate(_date.getYear(), _date.getMonth().getValue(), _date.getDayOfMonth());
        if (_driversCount < 0) throw new Exception("Drivers count cannot be less than zero");
        DriverShift driverShift = new DriverShift(shiftId, _date, _driversCount);
        shifts.put(shiftId, driverShift);
        dalController.insertDriverShift(driverShift);
        shiftId = shiftId + 1;
        dalController.updateCounter("nextShiftId",shiftId);
    }

    public void addDriverSchedule(String employeeId, int shiftId) throws Exception {
        if (!employeeController.getEmployees().containsKey(employeeId)) throw new Exception("Employee doesn't exist");
        if (employeeController.getEmployees().get(employeeId).getType() != Employee.RoleType.DRIVER)
            throw new Exception("Employee is not a driver");
        if (!shifts.containsKey(shiftId)) throw new Exception("Shift does not exists");
        DriverShift shift = (DriverShift) shifts.get(shiftId);
        Driver employee = employeeController.getDriver(employeeId);
        if (!availableEmployeesId(shiftId).contains(employeeId))
            throw new Exception("The employee " + employee.getFirstName() + " " + employee.getLastName() + " cannot work at this shift during his constraints");
        if (shift.getScheduleDrivers().contains(employeeId))
            throw new Exception("Driver was already chosen by HR Manager to work at this shift");
        if (shift.getDriversCount() == shift.getScheduleDrivers().size())
            throw new Exception("The shift is already full with drivers");
        shift.addDriverSchedule(employeeId);
        dalController.insertDriverSchedule(shift,employee);
    }

    public void removeDriverSchedule(String employeeId, int shiftId) throws Exception {
        if (!employeeController.getEmployees().containsKey(employeeId)) throw new Exception("Employee doesn't exist");
        if (employeeController.getEmployees().get(employeeId).getType() != Employee.RoleType.DRIVER)
            throw new Exception("Employee is not a driver");
        if (!shifts.containsKey(shiftId)) throw new Exception("Shift doesn't exist");
        DriverShift shift = (DriverShift) shifts.get(shiftId);
        if (!shift.getScheduleDrivers().contains(employeeId)) throw new Exception("Driver is not in this shift");
        shift.removeScheduleDriver(employeeId);
        dalController.deleteDriverSchedule(shift,employeeController.getDriver(employeeId));
    }

    public void removeSchedule(String employeeId, int shiftId) throws Exception {
        if (!employeeController.getEmployees().containsKey(employeeId)) throw new Exception("Employee doesn't exist");
        if (!shifts.containsKey(shiftId)) throw new Exception("Shift doesn't exist");
        if (shifts.get(shiftId).getType().equals("DS") && employeeController.getEmployees().get(employeeId).getType() == Employee.RoleType.DRIVER) {
            removeDriverSchedule(employeeId, shiftId);
            deliveryController.checkDriverDelivery(employeeId, shifts.get(shiftId).getDate().toLocalDate());
        } else if (!shifts.get(shiftId).getType().equals("DS") && employeeController.getEmployees().get(employeeId).getType() == Employee.RoleType.BRANCHEMPLOYEE)
            removeBranchEmployeeSchedule(employeeId, shiftId);
        else throw new Exception("Shift and employee are not at same type");

    }

    public String shiftsDriverScheduledTo(String employeeId) {
        String scheduledTo = "";
        if (!employeeController.getEmployees().containsKey(employeeId))
            throw new NoSuchElementException("Employee doesn't exist");
        if (employeeController.getEmployees().get(employeeId).getType() != Employee.RoleType.DRIVER)
            throw new IllegalArgumentException("Employee is not a driver");
        for (Integer id : shifts.keySet()) {
            if (shifts.get(id).getType().equals("DS") && shifts.get(id).getScheduleDrivers().contains(employeeId) && shifts.get(id).getDate().compareTo(LocalDateTime.now()) >= 0) {
                scheduledTo = id + " " + shifts.get(id).getDate().toString() + "\n";
            }
        }
        return scheduledTo;
    }

    public String shiftsDriverNotScheduledTo(String employeeId) {
        String notScheduledTo = "";
        if (!employeeController.getEmployees().containsKey(employeeId))
            throw new NoSuchElementException("Employee doesn't exist");
        if (employeeController.getEmployees().get(employeeId).getType() != Employee.RoleType.DRIVER)
            throw new IllegalArgumentException("Employee is not a driver");
        for (Integer id : shifts.keySet()) {
            if (shifts.get(id).getType().equals("DS") && !shifts.get(id).getScheduleDrivers().contains(employeeId)) {
                notScheduledTo = id + " " + shifts.get(id).getDate().toString() + "\n";
            }
        }
        return notScheduledTo;
    }

    public void changeDriverSchedule(String employeeId, int shiftIdOld, int shiftIdNew) throws Exception {
        if (!employeeController.getEmployees().containsKey(employeeId))
            throw new NoSuchElementException("Employee doesn't exist");
        if (!shifts.containsKey(shiftIdOld) || !shifts.containsKey(shiftIdNew))
            throw new Exception("Shift doesn't exist");
        if (employeeController.getEmployees().get(employeeId).getType() != Employee.RoleType.DRIVER)
            throw new IllegalArgumentException("Employee is not a driver");
        if (!shifts.get(shiftIdOld).getType().equals("DS") || !shifts.get(shiftIdNew).getType().equals("DS"))
            throw new Exception("Shift is not driver shift");
        if (!shifts.get(shiftIdOld).getScheduleDrivers().contains(employeeId))
            throw new Exception("Employee does not exist in shiftId to change from");
        if (shifts.get(shiftIdNew).getScheduleDrivers().contains(employeeId))
            throw new Exception("Employee is already exist in shiftId to change to");
        removeDriverSchedule(employeeId, shiftIdOld);
        addDriverSchedule(employeeId, shiftIdNew);
    }

    public boolean isDriverScheduled(String employeeId, LocalDate _date) {
        isValidDate(_date.getYear(), _date.getMonth().getValue(), _date.getDayOfMonth());
        if (!employeeController.getEmployees().containsKey(employeeId))
            throw new NoSuchElementException("Employee doesn't exist");
        for (Shift shift : shifts.values()) {
            if (shift.getType().equals("DS") && shift.getDate().toLocalDate().isEqual(_date) && shift.getScheduleDrivers().contains(employeeId)) {
                return true;
            }
        }
        return false;
    }

    public void removeBranchEmployeeSchedule(String employeeId, int shiftId) throws Exception {
        if (!employeeController.getEmployees().containsKey(employeeId)) throw new Exception("Employee doesn't exist");
        if (!shifts.containsKey(shiftId)) throw new Exception("Shift doesn't exist");
        Shift shift = shifts.get(shiftId);
        int schedulesB = shift.getSchedules().size();
        if (shift.getSchedules().get(employeeId).equals("Store Keeper")) {
            deliveryController.deliveriesToCancel(shift.getBranch(), shift.getDate().toLocalDate());
        }
        for (String id : shift.getSchedules().keySet()) {
            if (employeeController.getEmployees().get(employeeId).getEmployeeId().equals(id)){
                shift.removeSchedule(employeeId);
                dalController.deleteSchedule(shiftId,employeeId);
            }
        }
        if (schedulesB == shift.getSchedules().size()) throw new Exception("Employee doesn't exist in this shift");
    }

    public void removeShift(int _shiftId) throws Exception {
        if (!shifts.containsKey(_shiftId)) throw new Exception("Shift doesn't exist");
        Shift shift = shifts.get(_shiftId);
        shift.getRoleCounts().clear();
        shift.getScheduleToRoleCount().clear();
        shift.getSchedules().clear();
        shift.getCancellations().clear();
        shifts.remove(_shiftId);
    }

    public String removeCancellation(int _shiftId, String _productId, String _employeeId) throws Exception {
        if (!employeeController.getEmployees().containsKey(_employeeId)) throw new Exception("Employee doesn't exist");
        if (!shifts.containsKey(_shiftId)) throw new Exception("Shift doesn't exist");
        if (!employeeController.getEmployees().get(_employeeId).isCancellations()) {
            throw new Exception("Employee missing cancellation training");
        }
        ProductCancellation productCancellation = new ProductCancellation(_productId, _employeeId);
        for (ProductCancellation productCancellation1 : shifts.get(_shiftId).getCancellations()) {
            if (productCancellation1.getProductId().equals(productCancellation.getProductId()) && productCancellation1.getEmployeeId().equals(productCancellation.getEmployeeId())) {
                shifts.get(_shiftId).removeCancellation(_productId, _employeeId);
                dalController.deleteProductCancellation(shiftId,productCancellation);
                return "Remove cancellation successfully";
            }
        }
        throw new Exception("Product cancellation does not exist");
    }

    public void changeStartHourOfShift(int shiftId, LocalTime newHour) throws Exception {
        if (!shifts.containsKey(shiftId)) throw new Exception("Shift doesn't exist");
        if (shifts.get(shiftId).getType().equals("DS")) throw new Exception("Shift for drivers is per day");
        Shift shift = shifts.get(shiftId);
        shift.changeHour(newHour);
    }

    public void changeScheduleByRole(String employeeId, int shiftId, String role) throws Exception {
        String _role;
        if (!employeeController.getEmployees().containsKey(employeeId)) throw new Exception("Employee doesn't exist");
        if (!shifts.containsKey(shiftId)) throw new Exception("Shift doesn't exist");
        if (!shifts.get(shiftId).getSchedules().containsKey(employeeId))
            throw new Exception("Employee was not chosen by HR Manager to work at this shift");
        if (shifts.get(shiftId).getSchedules().containsKey(employeeId) && shifts.get(shiftId).getSchedules().get(employeeId).equals(""))
            throw new Exception("Employee was not scheduled yet");
        if (employeeController.isRoleExist(role).equals("false")) throw new Exception("Role doesn't exist");
        if (role.equals("shift manager") || role.equals("shift Manager")) _role = "Shift Manager";
        else if (role.equals("store keeper") || role.equals("store Keeper")) _role = "Store Keeper";
        else if (role.equals("general employee") || role.equals("general Employee")) _role = "General Employee";
        else if (Character.isLowerCase(role.charAt(0)))
            _role = Character.toUpperCase(role.charAt(0)) + role.substring(1);
        else _role = role;
        Employee employee = employeeController.getEmployees().get(employeeId);
        if (!employee.getRoles().contains(_role)) throw new Exception("Employee is not trained for " + _role);
        Shift shift = shifts.get(shiftId);
        shift.changeSchedule(employeeId, _role);
    }

    public void editConstraintShift(String employeeId, int constraintId, int shiftId) throws Exception {
        if (!employeeController.getEmployees().containsKey(employeeId)) throw new Exception("Employee doesn't exist");
        if (!employeeController.getEmployees().get(employeeId).isConstraintExist(constraintId))
            throw new Exception("Constraint does not exist at employee");
        if (!shifts.containsKey(shiftId)) throw new Exception("Shift doesn't exist");
        for (EmployeeConstraint employeeConstraint : employeeController.getEmployees().get(employeeId).getConstraints()) {
            if (employeeConstraint.getConstraintId() == constraintId) {
                employeeConstraint.setShiftId(shiftId);
                dalController.updateEmployeeConstraint(employeeConstraint,"ShiftId");
            }
        }
    }

    public Boolean isFutureShifts(int shiftId) throws Exception {
        if (!shifts.containsKey(shiftId)) throw new Exception("Shift doesn't exist");
        return (shifts.get(shiftId).getDate().compareTo(LocalDateTime.now()) > 0);
    }

    public void isValidTime(int hour, int minute) {
        if (hour < 0 || hour > 23) throw new IllegalArgumentException("Hour must be between 0 and 23");
        if (minute < 0 || minute > 59) throw new IllegalArgumentException("Minute must be between 0 and 59");
    }

    private boolean isLeap(int year) {
        return ((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0);
    }

    public void isValidDate(int year, int month, int day) {
        if (year > 9999 || year < 1000) throw new IllegalArgumentException("Year is not valid");
        if (month < 1 || month > 12) throw new IllegalArgumentException("Month is not valid");
        if (day < 1 || day > 31) throw new IllegalArgumentException("Day is not valid");
        if (month == 2) {
            if (isLeap(year) && day > 29)
                throw new IllegalArgumentException("Day is not valid during leap year at February");
            else if (day > 28) throw new IllegalArgumentException("Day is not valid at February");
        }
        if ((month == 4 || month == 6 || month == 9 || month == 11) && day > 30)
            throw new IllegalArgumentException("Day Or Month is not valid and needs to be 30 and less");
    }

    private List<Shift> getBranchShifts() {
        List<Shift> branchShifts = new LinkedList<>();
        for (Shift shift : shifts.values()) {
            if (!shift.getType().equals("DS"))
                branchShifts.add(shift);
        }
        return branchShifts;
    }

    private void isAvailableDate(String employeeId, LocalDateTime date, String type) throws Exception {
        if (!employeeController.getEmployees().containsKey(employeeId))
            throw new Exception("Employee does not existed");
        List<Shift> shiftInDate = new LinkedList<>();
        for (Shift shift : getBranchShifts()) {
            if (shift.getSchedules().containsKey(employeeId) && shift.getDate().toLocalDate().isEqual(date.toLocalDate()))
                shiftInDate.add(shift);
        }
        if (shiftInDate.size() == 1 && shiftInDate.get(0).getTypeBS().equals(type))
            throw new Exception("Employee already work at this time in another branch");
        else if (shiftInDate.size() == 1) throw new Exception("Employee cannot work more than one shift in day");
    }

    public void isTrainedToRole(String employeeId, int shiftId, String role) throws Exception {
        if (!shifts.containsKey(shiftId)) throw new Exception("Shift doesn't exist");
        if (!employeeController.getEmployees().containsKey(employeeId)) throw new Exception("employee doesn't exist");
        String role_;
        if (role.equals("shift manager") || role.equals("shift Manager")) role_ = "Shift Manager";
        else if (role.equals("store keeper") || role.equals("store Keeper")) role_ = "Store Keeper";
        else if (role.equals("general employee") || role.equals("general Employee")) role_ = "General Employee";
        else if (Character.isLowerCase(role.charAt(0)))
            role_ = Character.toUpperCase(role.charAt(0)) + role.substring(1);
        else role_ = role;
        if (employeeController.isRoleExist(role).equals("false")) throw new Exception("Role doesn't exist");
        if (!employeeController.getEmployees().get(employeeId).getRoles().contains(role_))
            throw new Exception("The employee " + employeeController.getEmployees().get(employeeId).getFirstName() + " " + employeeController.getEmployees().get(employeeId).getLastName() + " is not trained to do this role");
    }

    private String alertToHRAboutShift(int _shiftId) {
        String exception = "";
        Shift shift = shifts.get(_shiftId);
        if (shift.getDate().compareTo(LocalDateTime.now()) >= 0 && shift.getDate().getDayOfMonth() - LocalDateTime.now().getDayOfMonth() == 1 && shift.getDate().getHour() - LocalDateTime.now().getHour() + 24 <= 24)
            exception = "Shift number " + _shiftId + " needs to be scheduled";
        return exception;
    }

    public String alertAboutShifts() {
        StringBuilder exceptions = new StringBuilder();
        for (Integer shiftId : shifts.keySet()) {
            if (alertToHRAboutShift(shiftId).length() > 0) exceptions.append(alertToHRAboutShift(shiftId)).append("\n");
        }
        return exceptions.toString();
    }

    public void scheduleEmployeeToRole(String employeeId, int shiftId, String role) throws Exception {
        String _role;
        if (!employeeController.getEmployees().containsKey(employeeId)) throw new Exception("Employee doesn't exist");
        if (!shifts.containsKey(shiftId)) throw new Exception("Shift doesn't exist");
        if (role.equals("Driver")) throw new Exception("Driver can not be assign to this shift");
        if (employeeController.isRoleExist(role).equals("false")) throw new Exception("Role doesn't exist");
        if (role.equals("shift manager") || role.equals("shift Manager")) _role = "Shift Manager";
        else if (role.equals("store keeper") || role.equals("store Keeper")) _role = "Store Keeper";
        else if (role.equals("general employee") || role.equals("general Employee")) _role = "General Employee";
        else if (Character.isLowerCase(role.charAt(0)))
            _role = Character.toUpperCase(role.charAt(0)) + role.substring(1);
        else _role = role;
        BranchShift shift = (BranchShift) shifts.get(shiftId);
        if (!shift.getSchedules().containsKey(employeeId))
            throw new Exception("Employee was not chosen by HR Manager to work at this shift");
        if (shift.getSchedules().containsKey(employeeId) && !shift.getSchedules().get(employeeId).equals(""))
            throw new Exception("Employee already exist in this shift");
        isTrainedToRole(employeeId, shiftId, _role);
        int countRoleBeforeSchedule=shift.getScheduleToRoleCount().get(_role);
        shift.addSchedule(employeeId, _role);
        dalController.updateSchedule(shiftId,employeeId,role);
        dalController.updateRoleCount(shiftId,_role,countRoleBeforeSchedule+1,"CountAssigned");
    }

    public void insertConstraint(String employeeId, int shiftId, String description) throws Exception {
        if (!employeeController.getEmployees().containsKey(employeeId)) throw new Exception("Employee doesn't exist");
        if (!shifts.containsKey(shiftId)) throw new Exception("Shift doesn't exist");
        if (employeeController.getEmployees().get(employeeId).containsConstraintWithShiftId(shiftId))
            throw new Exception("Constraint on this shift already existed");
        employeeController.getEmployees().get(employeeId).addConstraint(new EmployeeConstraint(shiftId, description));
        dalController.insertEmployeeConstraint(new EmployeeConstraint(shiftId, description));
        dalController.insertConstraintToEmployee(employeeController.getEmployees().get(employeeId),new EmployeeConstraint(shiftId, description));
    }

    public List<String> availableEmployeesId(int shiftId) throws Exception {
        if (!shifts.containsKey(shiftId)) throw new Exception("Shift doesn't exist");
        List<String> availableEmployees = new LinkedList<>();
        for (String employeeId : employeeController.getEmployees().keySet()) {
            boolean existShiftId = false;
            Employee employee = employeeController.getEmployees().get(employeeId);
            for (int i = 0; i < employee.getConstraints().size() && !existShiftId; i++) {
                if (employee.getConstraints().get(i).getShiftId() == shiftId)
                    existShiftId = true;
            }
            if (!existShiftId) availableEmployees.add(employeeId);
        }
        return availableEmployees;
    }

    public String availableEmployeesNamesBE(int shiftId) throws Exception {
        if (!shifts.containsKey(shiftId)) throw new Exception("Shift doesn't exist");
        List<String> availableEmployees = new LinkedList<>();
        for (String employeeId : employeeController.getEmployees().keySet()) {
            int availableShifts = 0;
            Employee employee = employeeController.getEmployees().get(employeeId);
            if (employee.getType() == Employee.RoleType.BRANCHEMPLOYEE) {
                for (EmployeeConstraint constraint : employee.getConstraints()) {
                    if (constraint.getShiftId() != shiftId) availableShifts++;
                }
                if (availableShifts == employee.getConstraints().size())
                    availableEmployees.add("ID: " + employee.getEmployeeId() + " First Name: " + employee.getFirstName() + " Last Name: " + employee.getLastName());
            }
        }
        StringBuilder availableEmployeesS = new StringBuilder();
        for (String name : availableEmployees) {
            availableEmployeesS.append(name).append("\n");
        }
        return availableEmployeesS.toString();
    }

    public String availableEmployeesNamesD(int shiftId) throws Exception {
        if (!shifts.containsKey(shiftId)) throw new Exception("Shift doesn't exist");
        List<String> availableEmployees = new LinkedList<>();
        for (String employeeId : employeeController.getEmployees().keySet()) {
            int availableShifts = 0;
            Employee employee = employeeController.getEmployees().get(employeeId);
            if (employee.getType() == Employee.RoleType.DRIVER) {
                for (EmployeeConstraint constraint : employee.getConstraints()) {
                    if (constraint.getShiftId() != shiftId) availableShifts++;
                }
                if (availableShifts == employee.getConstraints().size())
                    availableEmployees.add("ID: " + employee.getEmployeeId() + " First Name: " + employee.getFirstName() + " Last Name: " + employee.getLastName());
            }
        }
        StringBuilder availableEmployeesS = new StringBuilder();
        for (String name : availableEmployees) {
            availableEmployeesS.append(name).append("\n");
        }
        return availableEmployeesS.toString();
    }

    public String isEmployeeAvailable(int shiftId, String employeeId) throws Exception {
        boolean result = availableEmployeesId(shiftId).contains(employeeId);
        return Boolean.toString(result);
    }

    public void scheduleBranchEmployee(String employeeId, int shiftId, String role) throws Exception {
        addEmployeeToSchedule(employeeId, shiftId);
        scheduleEmployeeToRole(employeeId, shiftId, role);
    }

    public void isStoreKeeperExist(LocalDate date, String address, LocalTime time) throws Exception {
        isValidDate(date.getYear(), date.getMonth().getValue(), date.getDayOfMonth());
        isValidTime(time.getHour(), time.getMinute());
        if (!branchController.isBranchExists(address)) throw new Exception("Branch is not exists");
        List<Integer> shiftsPerDateAndBranch = getShiftsByDateAndBranch(date, address);
        if (shiftsPerDateAndBranch.size() < 2 || (shifts.get(shiftsPerDateAndBranch.get(0)).getTypeBS().equals("M") && shifts.get(shiftsPerDateAndBranch.get(1)).getTypeBS().equals("M")) || (shifts.get(shiftsPerDateAndBranch.get(0)).getTypeBS().equals("E") && shifts.get(shiftsPerDateAndBranch.get(1)).getTypeBS().equals("E")))
            throw new Exception("Needed two shifts: Morning and Evening");
        int c = 0;
        for (Integer id : shiftsPerDateAndBranch) {
            Shift shift = shifts.get(id);
            LocalTime startTime = LocalTime.of(shift.getDate().getHour(), shift.getDate().getMinute());
            LocalTime endTime;
            if (shift.getTypeBS().equals("M")) {
                endTime = branchController.getBranches().get(address).getMorningShiftEndHour();
                if (time.compareTo(startTime) < 0 || time.compareTo(endTime) > 0) c = c + 1;
            }
            if (shift.getTypeBS().equals("E")) {
                endTime = branchController.getBranches().get(address).getEveningShiftEndHour();
                if (time.compareTo(startTime) < 0 || time.compareTo(endTime) > 0) c = c + 1;
            }
            if (shifts.get(id).getScheduleToRoleCount().get("Store Keeper") < 1)
                throw new Exception("One store keeper must be in this shift");
        }
        if (c != 1) throw new Exception("Branch is not working at time of delivery");
    }

    private List<Integer> getShiftsByDateAndBranch(LocalDate date, String address) {
        List<Integer> shiftsPerDateAndBranch = new LinkedList<>();
        for (Integer id : shifts.keySet()) {
            Shift shift = shifts.get(id);
            if (shift.getDate().toLocalDate().isEqual(date) && !shift.getType().equals("DS") && shift.getBranch().equals(address))
                shiftsPerDateAndBranch.add(id);
        }
        return shiftsPerDateAndBranch;
    }

    private int monthFromTenth() {
        int currentMonth;
        if (LocalDateTime.now().compareTo(LocalDateTime.of(LocalDate.now().getYear(), LocalDateTime.now().getMonth(), 10, LocalDateTime.now().getHour(), LocalDateTime.now().getMinute())) > 0)
            currentMonth = LocalDateTime.now().getMonth().getValue();
        else {
            if (LocalDateTime.now().getMonth().getValue() == 1) currentMonth = 12;
            else currentMonth = LocalDateTime.now().getMonth().getValue() - 1;
        }
        return currentMonth;
    }

    private LocalTime getOpeningHour(int shiftId) {
        if (!shifts.containsKey(shiftId)) throw new NoSuchElementException("Shift doesn't exist");
        return LocalTime.of(shifts.get(shiftId).getDate().getHour(), shifts.get(shiftId).getDate().getMinute());
    }

    private LocalTime getClosingHour(int shiftId) {
        if (!shifts.containsKey(shiftId)) throw new NoSuchElementException("Shift doesn't exist");
        if (shifts.get(shiftId).getTypeBS().equals("M"))
            return LocalTime.of(branchController.getBranches().get(shifts.get(shiftId).getBranch()).getMorningShiftEndHour().getHour(), branchController.getBranches().get(shifts.get(shiftId).getBranch()).getMorningShiftEndHour().getMinute());
        else
            return LocalTime.of(branchController.getBranches().get(shifts.get(shiftId).getBranch()).getEveningShiftEndHour().getHour(), branchController.getBranches().get(shifts.get(shiftId).getBranch()).getEveningShiftEndHour().getMinute());
    }

    public boolean isInShift(int shiftId, LocalTime deliveryTime) {
        isValidTime(deliveryTime.getHour(), deliveryTime.getMinute());
        return deliveryTime.compareTo(getOpeningHour(shiftId)) >= 0 && deliveryTime.compareTo(getClosingHour(shiftId)) <= 0;
    }

    public String getBranch(int shiftId) {
        if (!shifts.containsKey(shiftId)) throw new NoSuchElementException("Shift doesn't exist");
        return shifts.get(shiftId).getBranch();
    }

    public LocalDate getDate(int shiftId) {
        if (!shifts.containsKey(shiftId)) throw new NoSuchElementException("Shift doesn't exist");
        return shifts.get(shiftId).getDate().toLocalDate();
    }

    public DeliveryController getDeliveryController() {
        return deliveryController;
    }

    public void setDeliveryController(DeliveryController deliveryController) {
        this.deliveryController = deliveryController;
    }

    public void isShiftManagerAndStoreKeeper(String employeeId,int shiftId){
       if(employeeController.getEmployees().containsKey(employeeId)) throw new NoSuchElementException("Employee doesn't exist");
       if (!shifts.containsKey(shiftId)) throw new NoSuchElementException("Shift doesn't exist");
       if (!shifts.get(shiftId).getType().equals("BS")) throw new NoSuchElementException("Shift doesn't in type of Branch Shift");
       if(!shifts.get(shiftId).getSchedules().containsKey(employeeId)) throw new NoSuchElementException("Employee is not in shift");
       if(!shifts.get(shiftId).getSchedules().get(employeeId).equals("Shift Manager") && !shifts.get(shiftId).getSchedules().get(employeeId).equals("Store Keeper"))
           throw new IllegalArgumentException("Employee is not in right role");
    }

    public String showAllBranchShifts(){
        String allShifts="";
        for(Shift shift:shifts.values()){
            if(shift.getType().equals("BS"))
                allShifts=allShifts+shift.getShiftId()+" "+shift.getDate()+" "+shift.getTypeBS()+" "+shift.getBranch();
        }
        return allShifts;
    }

    private void setShiftId(int shiftId) {
        this.shiftId = shiftId;
    }

    public List<String> showAllConstraintToEmployeeList(String employeeId) throws Exception {
        List<String> constraints = new LinkedList<>();
        if (!employeeController.getEmployees().containsKey(employeeId)) throw new Exception("Employee doesn't exist");
        for (EmployeeConstraint employeeConstraint : employeeController.getEmployees().get(employeeId).getConstraints()) {
            if(shifts.get(employeeConstraint.getShiftId()).getDate().toLocalDate().compareTo(LocalDate.now())>0)
                constraints.add(employeeConstraint.getConstraintId() + " Shift: " + employeeConstraint.getShiftId() + " Description: " + employeeConstraint.getDescription());
        }
        return constraints;
    }

    public String showBranchShift(int shiftId) throws JsonProcessingException {
        BranchShift branchShift=getBranchShift(shiftId);
        return JsonConverter.toJson(branchShift);
    }

    public DriverShift getDriverShift(int shiftId){
        if (!shifts.containsKey(shiftId)) throw new NoSuchElementException("Shift doesn't exist");
        if(shifts.get(shiftId).getType().equals("DS"))
            return (DriverShift) shifts.get(shiftId);
        else throw new IllegalArgumentException("Shift is not branch shift");
    }

    public String showDriverShift(int shiftId) throws JsonProcessingException {
        DriverShift driverShift=getDriverShift(shiftId);
        return JsonConverter.toJson(driverShift);
    }

    public List<String> getShiftIds(){
        List<String> ids=new LinkedList<>();
        for(Integer id:shifts.keySet()){
            ids.add(Integer.toString(id));
        }
        return ids;
    }

    public String isBranchShift(String shiftId){
        return Boolean.toString(shifts.get(Integer.parseInt(shiftId)).getType().equals("BS"));
    }

    public String isDriverShift(String shiftId){
        return Boolean.toString(shifts.get(Integer.parseInt(shiftId)).getType().equals("DS"));
    }
}
