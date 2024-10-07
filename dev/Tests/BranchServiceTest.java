package Tests;

import BusinessLayer.Delivery.Site;
import ServiceLayer.SystemFacade;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class BranchServiceTest {
    SystemFacade systemFacade;

    @BeforeEach
    void setUp() {
        systemFacade = SystemFacade.getInstance();
        systemFacade.DeleteInstance();
        systemFacade = SystemFacade.getInstance();
        LocalTime startMHour = LocalTime.of(8, 0);
        LocalTime endMHour = LocalTime.of(13, 0);
        LocalTime startEHour = LocalTime.of(13, 0);
        LocalTime endEHour = LocalTime.of(22, 0);
        systemFacade.addBranch("Beer Sheva", "053739204", "Aaa Aaa", 1, startMHour, startEHour, endMHour, endEHour, 31.25181, 34.7913);
        systemFacade.insertTimeOfShift("Beer Sheva", "TUESDAY", "E");
    }

    @AfterEach
    void setDown() {
        systemFacade.DeleteInstance();
    }

    @Test
    void addBranchSuccess() {
        LocalTime startMHour = LocalTime.of(8, 0);
        LocalTime endMHour = LocalTime.of(13, 0);
        LocalTime startEHour = LocalTime.of(13, 0);
        LocalTime endEHour = LocalTime.of(22, 0);
        String message = systemFacade.addBranch("Tel Aviv", "0504742123", "Sss Sss", 1, startMHour, startEHour, endMHour, endEHour, 32.109333, 34.855499);
        assertEquals("Branch was inserted successfully", message);
    }

    @Test
    void addBranchEndBeforeStart() {
        LocalTime startMHour = LocalTime.of(8, 0);
        LocalTime endMHour = LocalTime.of(22, 0);
        LocalTime startEHour = LocalTime.of(13, 0);
        LocalTime endEHour = LocalTime.of(15, 0);
        String message = systemFacade.addBranch("Tel Aviv", "0504742123", "Sss Sss", 1, startMHour, startEHour, endMHour, endEHour, 32.109333, 34.855499);
        assertEquals("Evening shift hour must be after morning shift hour", message);
    }

    @Test
    void addBranchRegionFail() {
        LocalTime startMHour = LocalTime.of(8, 0);
        LocalTime endMHour = LocalTime.of(13, 0);
        LocalTime startEHour = LocalTime.of(13, 0);
        LocalTime endEHour = LocalTime.of(22, 0);
        String message = systemFacade.addBranch("Tel Aviv", "0504742123", "Sss Sss", 4, startMHour, startEHour, endMHour, endEHour, 32.109333, 34.855499);
        assertEquals("region is illegal", message);
    }

    @Test
    void addBranchAlreadyExisted() {
        LocalTime startMHour = LocalTime.of(8, 0);
        LocalTime endMHour = LocalTime.of(13, 0);
        LocalTime startEHour = LocalTime.of(13, 0);
        LocalTime endEHour = LocalTime.of(22, 0);
        String message = systemFacade.addBranch("Beer Sheva", "053739204", "Aaa Aaa", 1, startMHour, startEHour, endMHour, endEHour, 31.25181, 34.7913);
        assertEquals("Branch already exist", message);
    }

    @Test
    void addBranchSuccessAtDeliveries() {
        LocalTime startMHour = LocalTime.of(8, 0);
        LocalTime endMHour = LocalTime.of(13, 0);
        LocalTime startEHour = LocalTime.of(13, 0);
        LocalTime endEHour = LocalTime.of(22, 0);
        systemFacade.addBranch("Tel Aviv", "0504742123", "Sss Sss", 1, startMHour, startEHour, endMHour, endEHour, 32.109333, 34.855499);
        Site site = systemFacade.getDeliveryService().getResourceController().getSite("Tel Aviv");
        assertTrue(site.equals(systemFacade.getHrService().getBranchService().getBranchController().getBranches().get("Tel Aviv")));
    }

    @Test
    void removeBranchSuccess() {
        String message = systemFacade.removeBranch("Beer Sheva");
        assertEquals("Delete branch successfully", message);
    }

    @Test
    void removeBranchNotExisted() {
        String message = systemFacade.removeBranch("fhehxrghxr");
        assertEquals("Site does not exist", message);
    }

    @Test
    void insertTimeOfShiftSuccess() {
        String message = systemFacade.insertTimeOfShift("Beer Sheva", "TUESDAY", "M");
        assertEquals("success", message);
    }

    @Test
    void insertTimeOfShiftSuccessAtDeliveries() {
        systemFacade.insertTimeOfShift("Beer Sheva", "TUESDAY", "M");
        Site site = systemFacade.getDeliveryService().getResourceController().getSite("Beer Sheva");
        assertTrue(site.equals(systemFacade.getHrService().getBranchService().getBranchController().getBranches().get("Beer Sheva")));
    }

    @Test
    void insertTimeOfShiftWrongShiftType() {
        String message = systemFacade.insertTimeOfShift("Beer Sheva", "TUESDAY", "S");
        assertEquals("String is not shift type", message);
    }

    @Test
    void insertTimeOfShiftNotExistedBranch() {
        String message = systemFacade.insertTimeOfShift("treheszszj", "TUESDAY", "M");
        assertEquals("Branch is not exist", message);
    }

    @Test
    void insertTimeOfShiftNotExistedDay() {
        String message = systemFacade.insertTimeOfShift("Beer Sheva", "efgag", "M");
        assertEquals("String is not day", message);
    }

    @Test
    void insertTimeOfShiftShiftTimeExistedAlready() {
        String message = systemFacade.insertTimeOfShift("Beer Sheva", "TUESDAY", "E");
        assertEquals("Shift time already exist", message);
    }

    @Test
    void removeTimeOfShiftSuccess() {
        String message = systemFacade.removeTimeOfShift("Beer Sheva", "TUESDAY", "E");
        assertEquals("success", message);
    }

    @Test
    void removeTimeOfShiftNotExisted() {
        String message = systemFacade.removeTimeOfShift("Beer Sheva", "TUESDAY", "M");
        assertEquals("TUESDAY M does not in shift time's list", message);
    }

    @Test
    void removeTimeOfShiftEmptyListTimes() {
        LocalTime startMHour = LocalTime.of(8, 0);
        LocalTime endMHour = LocalTime.of(13, 0);
        LocalTime startEHour = LocalTime.of(13, 0);
        LocalTime endEHour = LocalTime.of(22, 0);
        systemFacade.addBranch("Tel Aviv", "0504742123", "Sss Sss", 1, startMHour, startEHour, endMHour, endEHour, 32.109333, 34.855499);
        String message = systemFacade.removeTimeOfShift("Tel Aviv", "TUESDAY", "E");
        assertEquals("Empty list of shift times", message);
    }

    @Test
    void removeTimeOfShiftBranchNotExisted() {
        String message = systemFacade.removeTimeOfShift("bdbeh", "Tuesday", "E");
        assertEquals("Branch is not exist", message);
    }

    @Test
    void changeStartHourOfBranchSuccess() {
        LocalTime newTime = LocalTime.of(7, 30);
        String message = systemFacade.changeStartHourOfBranch("Beer Sheva", "E", newTime);
        assertEquals("Change Start hour of branch successfully", message);
    }

    @Test
    void changeStartHourOfBranchSuccessAtDeliveries() {
        LocalTime newTime = LocalTime.of(7, 30);
        systemFacade.changeStartHourOfBranch("Beer Sheva", "E", newTime);
        Site site = systemFacade.getDeliveryService().getResourceController().getSite("Beer Sheva");
        assertTrue(site.equals(systemFacade.getHrService().getBranchService().getBranchController().getBranches().get("Beer Sheva")));
    }


    @Test
    void changeStartHourOfBranchBranchNotExisted() {
        LocalTime newTime = LocalTime.of(7, 30);
        String message = systemFacade.changeStartHourOfBranch("dksfjs", "E", newTime);
        assertEquals("Branch is not exist", message);
    }

    @Test
    void changeStartHourOfBranchWrongType() {
        LocalTime newTime = LocalTime.of(7, 30);
        String message = systemFacade.changeStartHourOfBranch("Beer Sheva", "D", newTime);
        assertEquals("Type must be M or E", message);
    }

    @Test
    void changeEndHourOfBranchSuccess() {
        LocalTime newTime = LocalTime.of(19, 30);
        String message = systemFacade.changeEndHourOfBranch("Beer Sheva", "E", newTime);
        assertEquals("Change End hour of branch successfully", message);
    }

    @Test
    void changeEndHourOfBranchBranchNotExisted() {
        LocalTime newTime = LocalTime.of(19, 30);
        String message = systemFacade.changeEndHourOfBranch("dksfjs", "E", newTime);
        assertEquals("Branch is not exist", message);
    }

    @Test
    void isBranchExistsTrue() {
        String message = systemFacade.isBranchExists("Beer Sheva");
        assertEquals("true", message);
    }

    @Test
    void isBranchExistsFalse() {
        String message = systemFacade.isBranchExists("grbgdthzs");
        assertEquals("false", message);
    }
}