package BusinessLayer.Human_Resources;

import BusinessLayer.Delivery.Coordinate;
import BusinessLayer.Delivery.ResourceController;
import BusinessLayer.Delivery.Site;
import ServiceLayer.JsonService.JsonConverter;
import DataAccessLayer.DALFacade;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

public class BranchController {
    private ResourceController resourceController;
    private final DALFacade dalController;

    public BranchController(ResourceController resourceController) {
        this.resourceController = resourceController;
        dalController= DALFacade.getInstance();
    }

    public HashMap<String, Branch> getBranches() {
        HashMap<String, Branch> branches = new HashMap<>();
        for (String address : resourceController.getSites().keySet()) {
            if (resourceController.getSites().get(address).getType().ordinal() == Site.Type.STORE.ordinal())
                branches.put(address, (Branch) resourceController.getSites().get(address));
        }
        return branches;
    }

    public List<String> getBranchesListJSON()  {
        List<String> branches=new LinkedList<>();
        for(String name:resourceController.getSites().keySet()){
            if(resourceController.getSites().get(name).getType()== Site.Type.STORE)
                branches.add(name);
        }
        return branches;
    }

    public String getNameOfBranch(String branchName) {
        StringBuilder branchName_ = new StringBuilder();
        String[] nameParts = branchName.split(" ");
        for (String part : nameParts) {
            char c = Character.toUpperCase(part.charAt(0));
            String part_ = c + part.substring(1);
            branchName_.append(part_).append(" ");
        }
        branchName_ = new StringBuilder(branchName_.substring(0, branchName_.length() - 1));
        return branchName_.toString();
    }

    public String showAllBranches() throws JsonProcessingException {
        return JsonConverter.toJson(getBranches());
    }

    public String showBranch(String address) throws JsonProcessingException {
        if (!getBranches().containsKey(address)) throw new NoSuchElementException("branch doesn't exist");
        return JsonConverter.toJson(getBranches().get(address));
    }

    public String showNamesOfBranches() {
        StringBuilder names = new StringBuilder();
        for (String branchName : getBranches().keySet()) {
            names.append(branchName).append("\n");
        }
        return names.toString();
    }

    public String showBranchMorningShiftHours(String branchName) throws Exception {
        if (!isBranchExists(branchName)) throw new Exception("Branch is not exist");
        String branchName_ = getNameOfBranch(branchName);
        Branch branch = getBranches().get(branchName_);
        return "Start hour: " + branch.getMorningShiftStartHour() + " - " + "End hour: " + branch.getMorningShiftEndHour();
    }

    public String showBranchEveningShiftHours(String branchName) throws Exception {
        if (!isBranchExists(branchName)) throw new Exception("Branch is not exist");
        String branchName_ = getNameOfBranch(branchName);
        Branch branch = getBranches().get(branchName_);
        return "Start hour: " + branch.getEveningShiftStartHour() + " - " + "End hour: " + branch.getEveningShiftEndHour();
    }

    public void changeStartHourOfBranch(String branchName, String shiftType, LocalTime shiftStartHour) throws Exception {
        if (!isBranchExists(branchName)) throw new Exception("Branch is not exist");
        String branchName_ = getNameOfBranch(branchName);
        Branch branch = getBranches().get(branchName_);
        if (shiftType.equals("M")) {
            branch.setMorningShiftStartHour(shiftStartHour);
            dalController.updateBranch(branch,"MorningShiftStartHour");
        }
        else if (shiftType.equals("E")) {
            branch.setEveningShiftStartHour(shiftStartHour);
            dalController.updateBranch(branch,"EveningShiftStartHour");
        } else throw new Exception("Type must be M or E");
    }

    public void changeEndHourOfBranch(String branchName, String shiftType, LocalTime shiftEndHour) throws Exception {
        String shiftType_ = shiftType.toUpperCase();
        if (!isBranchExists(branchName)) throw new Exception("Branch is not exist");
        String branchName_ = getNameOfBranch(branchName);
        Branch branch = getBranches().get(branchName_);
        if (shiftType_.equals("M")) {
            if (branch.getMorningShiftStartHour().compareTo(shiftEndHour) >= 0)
                throw new Exception("Start hour of shift must be before end hour of shift");
            branch.setMorningShiftEndHour(shiftEndHour);
            dalController.updateBranch(branch,"MorningShiftEndHour");
        } else if (shiftType_.equals("E")) {
            if (branch.getEveningShiftStartHour().compareTo(shiftEndHour) >= 0)
                throw new Exception("Start hour of shift must be before end hour of shift");
            branch.setEveningShiftEndHour(shiftEndHour);
            dalController.updateBranch(branch,"EveningShiftEndHour");
        } else throw new Exception("Type must be M or E");
    }

    public void addBranch(String branchName, String phoneNumber, String contactName, int region, int type, LocalTime morningShiftStartHour, LocalTime eveningShiftStartHour, LocalTime morningShiftEndHour, LocalTime eveningShiftEndHour, double latitude, double longitude) throws Exception {
        isValidTime(morningShiftStartHour.getHour(), morningShiftStartHour.getMinute());
        isValidTime(morningShiftEndHour.getHour(), morningShiftEndHour.getMinute());
        isValidTime(eveningShiftStartHour.getHour(), eveningShiftStartHour.getMinute());
        isValidTime(morningShiftEndHour.getHour(), morningShiftEndHour.getMinute());
        if (eveningShiftStartHour.compareTo(morningShiftEndHour) < 0 && !(eveningShiftStartHour.getHour() == 0 && morningShiftEndHour.getHour() == 23))
            throw new Exception("Evening shift hour must be after morning shift hour");
        if (getBranches().containsKey(branchName)) throw new Exception("Branch already exist");
        if (region < 0 || region > 2)
            throw new IllegalArgumentException("region is illegal");
        if (type != 0)
            throw new IllegalArgumentException("type isn't Store type");
        Coordinate check = new Coordinate(latitude, longitude);
        for (String site : getBranches().keySet()) {
            if (getBranches().get(site).getCoordinate().equals(check))
                throw new IllegalArgumentException("this coordinate is already exist");
        }
        Branch branch = new Branch(branchName, phoneNumber, contactName, region, type, morningShiftStartHour, eveningShiftStartHour, morningShiftEndHour, eveningShiftEndHour, latitude, longitude);
        resourceController.addBranch(branch);
        dalController.insertBranch(branch);
    }

    public void insertTimeOfShift(String branchName, String day, String type) throws Exception {
        if (!isBranchExists(branchName)) throw new Exception("Branch is not exist");
        String branchName_ = getNameOfBranch(branchName);
        Branch branch = getBranches().get(branchName_);
        branch.addShiftTime(day, type);
        dalController.insertTimesOfShifts(branchName_,day+" "+type);
    }

    public void removeBranch(String branchName) {
        resourceController.removeSite(branchName);
    }

    public void removeTimeOfShift(String branchName, String day, String type) throws Exception {
        String _day = day.toUpperCase();
        String _type = type.toUpperCase();
        if (!isBranchExists(branchName)) throw new Exception("Branch is not exist");
        String branchName_ = getNameOfBranch(branchName);
        Branch branch = getBranches().get(branchName_);
        branch.deleteShiftTime(_day, _type);
        dalController.deleteTimesOfShifts(branchName_,_day+" "+_type);
    }

    public Boolean isBranchExists(String branchName) {
        StringBuilder branchName_ = new StringBuilder();
        String[] nameParts = branchName.split(" ");
        for (String part : nameParts) {
            char c = Character.toUpperCase(part.charAt(0));
            String part_ = c + part.substring(1);
            branchName_.append(part_).append(" ");
        }
        branchName_ = new StringBuilder(branchName_.substring(0, branchName_.length() - 1));
        return getBranches().containsKey(branchName_.toString());
    }

    public void isValidTime(int hour, int minute) throws Exception {
        if (hour < 0 || hour > 23) {
            throw new Exception("Hour must be between 0 and 23");
        }
        if (minute < 0 || minute > 59) {
            throw new Exception("Minute must be between 0 and 59");
        }
    }

    public ResourceController getResourceController() {
        return resourceController;
    }

    public void setBranchName(String oldBranchName, String newBranchName) {
        resourceController.setSiteAddress(oldBranchName, newBranchName);
    }
}
