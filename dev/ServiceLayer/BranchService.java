package ServiceLayer;

import BusinessLayer.Human_Resources.BranchController;

import java.time.LocalTime;
import java.util.List;

public class BranchService {
    private final BranchController branchController;

    public BranchService(BranchController _branchController) {
        branchController = _branchController;
    }

    public BranchController getBranchController() {
        return branchController;
    }

    public String showAllBranches() {
        try {
            return branchController.showAllBranches();
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public List<String> getBranchesList(){return branchController.getBranchesListJSON();}

    public String showBranch(String address) {
        try {
            return branchController.showBranch(address);
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String showNamesOfBranches() {
        try {
            return branchController.showNamesOfBranches();
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String showBranchMorningShiftHours(String branchName) {
        try {
            return branchController.showBranchMorningShiftHours(branchName);
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String showBranchEveningShiftHours(String branchName) {
        try {
            return branchController.showBranchEveningShiftHours(branchName);
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String setBranchName(String branchName, String _branchName) {
        try {
            branchController.setBranchName(branchName, _branchName);
            return "success";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String changeStartHourOfBranch(String branchName, String shiftType, LocalTime shiftStartHour) {
        try {
            branchController.changeStartHourOfBranch(branchName, shiftType, shiftStartHour);
            return "Change Start hour of branch successfully";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String changeEndHourOfBranch(String branchName, String shiftType, LocalTime shiftEndHour) {
        try {
            branchController.changeEndHourOfBranch(branchName, shiftType, shiftEndHour);
            return "Change End hour of branch successfully";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String addBranch(String branchName, String phoneNumber, String contactName, int region, int type, LocalTime morningShiftStartHour, LocalTime eveningShiftStartHour, LocalTime morningShiftEndHour, LocalTime eveningShiftEndHour, double latitude, double longitude) {
        try {
            branchController.addBranch(branchName, phoneNumber, contactName, region, type, morningShiftStartHour, eveningShiftStartHour, morningShiftEndHour, eveningShiftEndHour, latitude, longitude);
            return "Branch was inserted successfully";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String insertTimeOfShift(String branchName, String day, String type) {
        try {
            branchController.insertTimeOfShift(branchName, day, type);
            return "success";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String removeBranch(String branchName) {
        try {
            branchController.removeBranch(branchName);
            return "Delete branch successfully";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String removeTimeOfShift(String branchName, String day, String type) {
        try {
            branchController.removeTimeOfShift(branchName, day, type);
            return "success";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String isBranchExists(String branchName) {
        try {
            return branchController.isBranchExists(branchName).toString();
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
