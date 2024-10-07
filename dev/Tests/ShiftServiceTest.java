package Tests;

import BusinessLayer.Delivery.ItemOrder;
import ServiceLayer.SystemFacade;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class ShiftServiceTest {
    SystemFacade systemFacade;

    @BeforeEach
    void setUp() {
        systemFacade = SystemFacade.getInstance();
        systemFacade.DeleteInstance();
        systemFacade = SystemFacade.getInstance();
        LocalDateTime date = LocalDateTime.of(2023, 1, 1, 1, 1);
        systemFacade.registerBranchEmployee("111111111", "aaa", "aaa", "Aa1111111", 23, 23, 30, "aaa", "aaaaa", date, "0534279975", false, false);
        systemFacade.insertRole("111111111", "cashier");
        LocalDateTime date1 = LocalDateTime.of(2023, 12, 1, 1, 1);
        systemFacade.registerBranchEmployee("2222222222", "bbb", "bbb", "Bb222222", 45, 12, 30, "bbb", "bbbbbb", date1, "0533819176", false, false);
        systemFacade.insertRole("2222222222", "cleaner");
        systemFacade.registerDriver("333333333", "ccc", "ccc", "Cc3333333", 64, 45, 30, "ccc", "cccccc", date1, "0522109876", 30000);
        LocalTime startMHour = LocalTime.of(8, 0);
        LocalTime endMHour = LocalTime.of(13, 0);
        LocalTime startEHour = LocalTime.of(13, 0);
        LocalTime endEHour = LocalTime.of(22, 0);
        systemFacade.addBranch("Beer Sheva", "053739204", "Aaa Aaa", 1, startMHour, startEHour, endMHour, endEHour, 31.25181, 34.7913);
        systemFacade.addBranch("Tel Aviv", "0526492090", "Bbb Bbb", 2, startMHour, startEHour, endMHour, endEHour, 32.109333, 34.855499);
        systemFacade.insertTimeOfShift("Tel Aviv", "TUESDAY", "E");
        systemFacade.insertTimeOfShift("Tel Aviv", "TUESDAY", "M");
        systemFacade.insertTimeOfShift("Beer Sheva", "TUESDAY", "E");
        systemFacade.insertTimeOfShift("Beer Sheva", "TUESDAY", "M");
        systemFacade.insertTimeOfShift("Beer Sheva", "FRIDAY", "E");
        systemFacade.insertTimeOfShift("Beer Sheva", "FRIDAY", "M");
        systemFacade.insertTimeOfShift("Beer Sheva", "SATURDAY", "E");
        systemFacade.insertTimeOfShift("Beer Sheva", "SATURDAY", "M");
        systemFacade.insertTimeOfShift("Beer Sheva", "SUNDAY", "E");
        systemFacade.insertTimeOfShift("Beer Sheva", "SUNDAY", "M");
        systemFacade.insertTimeOfShift("Beer Sheva", "MONDAY", "E");
        systemFacade.insertTimeOfShift("Beer Sheva", "MONDAY", "M");
        systemFacade.insertTimeOfShift("Beer Sheva", "THURSDAY", "E");
        systemFacade.insertTimeOfShift("Beer Sheva", "THURSDAY", "M");
        systemFacade.insertTimeOfShift("Beer Sheva", "WEDNESDAY", "E");
        systemFacade.insertTimeOfShift("Beer Sheva", "WEDNESDAY", "M");
    }

    @AfterEach
    void setDown() {
        systemFacade.DeleteInstance();
    }

    @Test
    void addMorningShiftSuccess() {
        LocalDateTime date = LocalDateTime.of(2023, 7, 4, 8, 0);
        String message = systemFacade.addBranchShift(date, 5, 5, 5, 5, 5, 5, 5, "Beer Sheva","M");
        assertEquals("success", message);
    }

    @Test
    void addMorningStoreKeepersCountOfRole() {
        LocalDateTime date = LocalDateTime.of(2023, 7, 4, 8, 0);
        String message = systemFacade.addBranchShift(date, -1, 5, 5, 5, 5, 5, 5, "Beer Sheva","M");
        assertEquals("Store keepers count must be zero or more", message);
    }

    @Test
    void addMorningShiftBranchNotExisted() {
        LocalDateTime date = LocalDateTime.of(2023, 7, 4, 8, 0);
        String message = systemFacade.addBranchShift(date, 5, 5, 5, 5, 5, 5, 5, "Eilat","M");
        assertEquals("Branch is not exist", message);
    }

    @Test
    void addMorningShiftBranchNotWorkingAtDay() {
        LocalDateTime date = LocalDateTime.of(2023, 7, 5, 8, 0);
        String message = systemFacade.addBranchShift(date, 5, 5, 5, 5, 5, 5, 5, "Tel Aviv","M");
        assertEquals("Branch is not working at this day", message);
    }

    @Test
    void addMorningShiftShiftManagersSize() {
        LocalDateTime date = LocalDateTime.of(2023, 7, 4, 8, 0);
        String message = systemFacade.addBranchShift(date, 5, 5, 0, 5, 0, 5, 5, "Beer Sheva","M");
        assertEquals("Shift managers count must be one or more", message);
    }

    @Test
    void addEveningShiftSuccess() {
        LocalDateTime date = LocalDateTime.of(2023, 7, 4, 8, 0);
        String message = systemFacade.addBranchShift(date, 5, 5, 5, 5, 5, 5, 5, "Beer Sheva","E");
        assertEquals("success", message);
    }

    @Test
    void addEveningShiftBranchNotExist() {
        LocalDateTime date = LocalDateTime.of(2023, 7, 4, 8, 0);
        String message = systemFacade.addBranchShift(date, 5, 5, 5, 5, 5, 5, 5, "Ariel","E");
        assertEquals("Branch is not exist", message);
    }

    @Test
    void addMorningShiftCashiersCountOfRole() {
        LocalDateTime date = LocalDateTime.of(2023, 7, 4, 8, 0);
        String message = systemFacade.addBranchShift(date, 5, -1, 5, 5, 5, 5, 5, "Beer Sheva","E");
        assertEquals("Cashiers count must be zero or more", message);
    }

    @Test
    void addMorningShiftTypeWrong() {
        LocalDateTime date = LocalDateTime.of(2023, 7, 4, 8, 0);
        String message = systemFacade.addBranchShift(date, 5, 5, 5, 5, 5, 5, 5, "Beer Sheva","A");
        assertEquals("Type is not illegal", message);
    }

    @Test
    void isValidDateSuccess() {
        String message = systemFacade.isValidDate(2023, 1, 1);
        assertEquals("success", message);
    }

    @Test
    void isValidDateFebruary30() {
        String message = systemFacade.isValidDate(2023, 2, 30);
        assertEquals("Day is not valid at February", message);
    }

    @Test
    void isValidDateNotValidMonth() {
        String message = systemFacade.isValidDate(2023, -1, 30);
        assertEquals("Month is not valid", message);
    }

    @Test
    void isValidDateNotValidYear() {
        String message = systemFacade.isValidDate(-20, 2, 13);
        assertEquals("Year is not valid", message);
    }

    @Test
    void isValidDateNotValidDay() {
        String message = systemFacade.isValidDate(2023, 1, 45);
        assertEquals("Day is not valid", message);
    }

    @Test
    void isValidDateValidMonth30() {
        String message = systemFacade.isValidDate(2023, 4, 31);
        assertEquals("Day Or Month is not valid and needs to be 30 and less", message);
    }

    @Test
    void isValidDateLeapYear() {
        String message = systemFacade.isValidDate(2024, 2, 30);
        assertEquals("Day is not valid during leap year at February", message);
    }

    @Test
    void isValidTimeSuccess() {
        String message = systemFacade.isValidTime(18, 0);
        assertEquals("success", message);
    }

    @Test
    void isValidTimeWrongMinutes() {
        String message = systemFacade.isValidTime(8, 70);
        assertEquals("Minute must be between 0 and 59", message);
    }

    @Test
    void isValidTimeHours() {
        String message = systemFacade.isValidTime(45, 45);
        assertEquals("Hour must be between 0 and 23", message);
    }

    @Test
    void changeStartHourOfShiftSuccess() {
        LocalDateTime date = LocalDateTime.of(2023, 7, 4, 8, 0);
        systemFacade.addBranchShift(date, 5, 5, 5, 5, 5, 5, 5, "Beer Sheva","M");
        LocalTime startMHour = LocalTime.of(8, 0);
        String message = systemFacade.changeStartHourOfShift(0, startMHour);
        assertEquals("Change hour of shift successfully", message);
    }

    @Test
    void changeStartHourOfShiftNotExistShift() {
        LocalTime startMHour = LocalTime.of(8, 0);
        String message = systemFacade.changeStartHourOfShift(1, startMHour);
        assertEquals("Shift doesn't exist", message);
    }

    @Test
    void addCancellationSuccess() {
        systemFacade.setCancellation("111111111");
        LocalDateTime date = LocalDateTime.of(2023, 7, 4, 8, 0);
        systemFacade.addBranchShift(date, 5, 5, 5, 5, 5, 5, 5, "Beer Sheva","M");
        String message = systemFacade.addCancellation(0, "12-14", "111111111");
        assertEquals("Cancellation of 12-14 was added successfully", message);
    }

    @Test
    void addCancellationNotExistEmployee() {
        LocalDateTime date = LocalDateTime.of(2023, 7, 4, 8, 0);
        systemFacade.addBranchShift(date, 5, 5, 5, 5, 5, 5, 5, "Beer Sheva","M");
        String message = systemFacade.addCancellation(1, "12-14", "111111777");
        assertEquals("Employee doesn't exist", message);
    }

    @Test
    void addCancellationNotExistShift() {
        String message = systemFacade.addCancellation(1, "12-14", "111111111");
        assertEquals("Shift doesn't exist", message);
    }

    @Test
    void addCancellationEmployeeTrainings() {
        LocalDateTime date = LocalDateTime.of(2023, 7, 4, 8, 0);
        systemFacade.addBranchShift(date, 5, 5, 5, 5, 5, 5, 5, "Beer Sheva","M");
        String message = systemFacade.addCancellation(0, "12-14", "111111111");
        assertEquals("Employee missing cancellation training", message);
    }

    @Test
    void scheduleEmployeeToRoleSuccess() {
        LocalDateTime date = LocalDateTime.of(2023, 7, 4, 8, 0);
        systemFacade.insertRole("111111111", "cashier");
        systemFacade.addBranchShift(date, 5, 5, 5, 5, 5, 5, 5, "Beer Sheva","M");
        systemFacade.addEmployeeToSchedule("111111111", 0);
        String message = systemFacade.scheduleEmployeeToRole("111111111", 0, "cashier");
        assertEquals("Schedule employee to role successfully", message);
    }

    @Test
    void scheduleEmployeeToRoleNotExistRole() {
        LocalDateTime date = LocalDateTime.of(2023, 7, 4, 8, 0);
        systemFacade.addBranchShift(date, 5, 5, 5, 5, 5, 5, 5, "Beer Sheva","M");
        systemFacade.addEmployeeToSchedule("111111111", 0);
        String message = systemFacade.scheduleEmployeeToRole("111111111", 0, "fehskfhskej");
        assertEquals("Role doesn't exist", message);
    }

    @Test
    void scheduleEmployeeToRoleTrainedToRole() {
        LocalDateTime date = LocalDateTime.of(2023, 7, 4, 8, 0);
        systemFacade.addBranchShift(date, 5, 5, 5, 5, 5, 5, 5, "Beer Sheva","M");
        systemFacade.addEmployeeToSchedule("111111111", 0);
        String message = systemFacade.scheduleEmployeeToRole("111111111", 0, "guard");
        assertEquals("The employee Aaa Aaa is not trained to do this role", message);
    }

    @Test
    void scheduleEmployeeToRoleChosenByHR() {
        LocalDateTime date = LocalDateTime.of(2023, 7, 4, 8, 0);
        systemFacade.addBranchShift(date, 5, 5, 5, 5, 5, 5, 5, "Beer Sheva","M");
        String message = systemFacade.scheduleEmployeeToRole("111111111", 0, "guard");
        assertEquals("Employee was not chosen by HR Manager to work at this shift", message);
    }

    @Test
    void scheduleEmployeeToRoleAlreadyWorkShift() {
        LocalDateTime date = LocalDateTime.of(2023, 7, 4, 8, 0);
        systemFacade.addBranchShift(date, 5, 5, 5, 5, 5, 5, 5, "Beer Sheva","M");
        systemFacade.addEmployeeToSchedule("111111111", 0);
        systemFacade.scheduleEmployeeToRole("111111111", 0, "cashier");
        String message = systemFacade.scheduleEmployeeToRole("111111111", 0, "cashier");
        assertEquals("Employee already exist in this shift", message);
    }

    @Test
    void scheduleEmployeeToRoleNotExistShift() {
        LocalDateTime date = LocalDateTime.of(2023, 4, 4, 8, 0);
        systemFacade.addBranchShift(date, 5, 5, 5, 5, 5, 5, 5, "Beer Sheva","M");
        String message = systemFacade.scheduleEmployeeToRole("111111111", 1, "guard");
        assertEquals("Shift doesn't exist", message);
    }

    @Test
    void scheduleEmployeeToRoleConstraints() {
        LocalDateTime date = LocalDateTime.of(2023, 7, 4, 8, 0);
        systemFacade.addBranchShift(date, 5, 5, 5, 5, 5, 5, 5, "Beer Sheva","M");
        systemFacade.addConstraint("111111111", 0, "gxhdsgckj");
        systemFacade.addEmployeeToSchedule("111111111", 0);
        String message = systemFacade.scheduleEmployeeToRole("111111111", 0, "cashier");
        assertEquals("Employee was not chosen by HR Manager to work at this shift", message);
    }

    @Test
    void scheduleEmployeeToRoleCashiersCount() {
        LocalDateTime date = LocalDateTime.of(2023, 7, 4, 8, 0);
        systemFacade.addBranchShift(date, 5, 0, 5, 5, 5, 5, 5, "Beer Sheva","M");
        systemFacade.addEmployeeToSchedule("111111111", 0);
        String message = systemFacade.scheduleEmployeeToRole("111111111", 0, "cashier");
        assertEquals("Shift already full with cashiers", message);
    }

    @Test
    void scheduleEmployeeToRoleNotExistEmployee() {
        LocalDateTime date = LocalDateTime.of(2023, 7, 4, 8, 0);
        systemFacade.addBranchShift(date, 5, 5, 5, 5, 5, 5, 5, "Beer Sheva","M");
        systemFacade.addEmployeeToSchedule("111111111", 1);
        String message = systemFacade.scheduleEmployeeToRole("112111111", 1, "cashier");
        assertEquals("Employee doesn't exist", message);
    }

    @Test
    void scheduleEmployeeToRoleHireDate() {
        LocalDateTime date = LocalDateTime.of(2023, 5, 10, 8, 0);
        systemFacade.addBranchShift(date, 5, 5, 5, 5, 5, 5, 5, "Beer Sheva","M");
        systemFacade.registerBranchEmployee("444444444", "bbb", "bbb", "Bb222222", 45, 12, 30, "bbb", "bbbbbb", LocalDateTime.of(2023, 6, 1, 1, 1), "0542198347", false, false);
        systemFacade.insertRole("444444444","cashier");
        systemFacade.addEmployeeToSchedule("444444444", 0);
        String message = systemFacade.scheduleEmployeeToRole("444444444", 0, "cashier");
        assertEquals("Employee was not chosen by HR Manager to work at this shift", message);
    }

    @Test
    void changeScheduleByRoleSuccess() {
        LocalDateTime date = LocalDateTime.of(2023, 7, 4, 8, 0);
        systemFacade.addBranchShift(date, 5, 5, 5, 5, 5, 5, 5, "Beer Sheva","M");
        systemFacade.addEmployeeToSchedule("111111111", 0);
        systemFacade.scheduleEmployeeToRole("111111111", 0, "cashier");
        systemFacade.insertRole("111111111", "guard");
        String message = systemFacade.changeScheduleByRole("111111111", 0, "guard");
        assertEquals("Change schedule by role successfully", message);
    }

    @Test
    void changeScheduleByRoleNotChosenByHR() {
        LocalDateTime date = LocalDateTime.of(2023, 7, 4, 8, 0);
        systemFacade.addBranchShift(date, 5, 5, 5, 5, 5, 5, 5, "Beer Sheva","M");
        systemFacade.scheduleEmployeeToRole("111111111", 0, "cashier");
        systemFacade.insertRole("111111111", "cashier");
        String message = systemFacade.changeScheduleByRole("111111111", 0, "guard");
        assertEquals("Employee was not chosen by HR Manager to work at this shift", message);
    }

    @Test
    void changeScheduleByRoleShiftNotExist() {
        LocalDateTime date = LocalDateTime.of(2023, 7, 4, 8, 0);
        systemFacade.addBranchShift(date, 5, 5, 5, 5, 5, 5, 5, "Beer Sheva","M");
        systemFacade.addEmployeeToSchedule("111111111", 1);
        systemFacade.scheduleEmployeeToRole("111111111", 1, "cashier");
        systemFacade.insertRole("111111111", "guard");
        String message = systemFacade.changeScheduleByRole("111111111", 2, "guard");
        assertEquals("Shift doesn't exist", message);
    }

    @Test
    void changeScheduleByRoleEmployeeNotExist() {
        LocalDateTime date = LocalDateTime.of(2023, 7, 4, 8, 0);
        systemFacade.addBranchShift(date, 5, 5, 5, 5, 5, 5, 5, "Beer Sheva","M");
        systemFacade.addEmployeeToSchedule("111111111", 1);
        systemFacade.scheduleEmployeeToRole("111111111", 1, "cashier");
        systemFacade.insertRole("111111111", "guard");
        String message = systemFacade.changeScheduleByRole("111122111", 1, "guard");
        assertEquals("Employee doesn't exist", message);
    }

    @Test
    void changeScheduleByRoleTrainedToRole() {
        LocalDateTime date = LocalDateTime.of(2023, 7, 4, 8, 0);
        systemFacade.addBranchShift(date, 5, 5, 5, 5, 5, 5, 5, "Beer Sheva","M");
        systemFacade.addEmployeeToSchedule("111111111", 0);
        systemFacade.scheduleEmployeeToRole("111111111", 0, "cashier");
        systemFacade.insertRole("111111111", "singer");
        String message = systemFacade.changeScheduleByRole("111111111", 0, "guard");
        assertEquals("Employee is not trained for Guard", message);
    }

    @Test
    void changeScheduleByRoleNotScheduled() {
        LocalDateTime date = LocalDateTime.of(2023, 7, 4, 8, 0);
        systemFacade.addBranchShift(date, 5, 5, 5, 5, 5, 5, 5, "Beer Sheva","M");
        systemFacade.addEmployeeToSchedule("111111111", 0);
        systemFacade.insertRole("111111111", "guard");
        String message = systemFacade.changeScheduleByRole("111111111", 0, "guard");
        assertEquals("Employee was not scheduled yet", message);
    }

    @Test
    void removeBranchEmployeeScheduleSuccess() {
        LocalDateTime date = LocalDateTime.of(2023, 7, 4, 8, 0);
        systemFacade.addBranchShift(date, 5, 5, 5, 5, 5, 5, 5, "Beer Sheva","M");
        systemFacade.addEmployeeToSchedule("111111111", 0);
        systemFacade.scheduleEmployeeToRole("111111111", 0, "cashier");
        String message = systemFacade.removeSchedule("111111111", 0);
        assertEquals("Remove schedule by role successfully", message);
    }

    @Test
    void removeBranchEmployeeScheduleEmployeeNotExisted() {
        LocalDateTime date = LocalDateTime.of(2023, 7, 4, 8, 0);
        systemFacade.addBranchShift(date, 5, 5, 5, 5, 5, 5, 5, "Beer Sheva","M");
        systemFacade.scheduleEmployeeToRole("111111111", 1, "cashier");
        String message = systemFacade.removeSchedule("111441111", 1);
        assertEquals("Employee doesn't exist", message);
    }

    @Test
    void removeBranchEmployeeScheduleShiftNotExist() {
        LocalDateTime date = LocalDateTime.of(2023, 7, 4, 8, 0);
        systemFacade.addBranchShift(date, 5, 5, 5, 5, 5, 5, 5, "Beer Sheva","M");
        systemFacade.scheduleEmployeeToRole("111111111", 1, "cashier");
        String message = systemFacade.removeSchedule("111111111", 2);
        assertEquals("Shift doesn't exist", message);
    }

    @Test
    void removeBranchEmployeeScheduleSuccessAtDeliveries() {
        LocalDateTime date = LocalDateTime.of(2023, 7, 4, 8, 0);
        systemFacade.addBranchShift(date, 5, 5, 5, 5, 5, 5, 5, "Beer Sheva","M");
        systemFacade.addEmployeeToSchedule("111111111", 1);
        systemFacade.scheduleEmployeeToRole("111111111", 1, "cashier");
        systemFacade.removeSchedule("111111111", 1);
        systemFacade.addTruck(6666, "reg", "mazda", 1500, 4000);
        systemFacade.registerDriver("777888777", "kfir", "nissim", "Kn123456", 45, 67, 30, "aaaaaaa", "aaaaaaa", LocalDateTime.of(2023, 1, 1, 1, 1), "0548929158", 15000);
        systemFacade.addDelivery(LocalDate.of(2023, 7, 4), LocalTime.of(11, 0), 6666, "777888777", "Beer Sheva");
        systemFacade.approveDelivery(0);
        assertTrue(systemFacade.getDeliveryService().getDeliveryController().getDelivery(0).getStatusOrdinal() == 0);
    }


    @Test
    void getPaymentNoShifts() {
        String message = systemFacade.getPayment("111111111", 0, 0);
        assertEquals("0", message);
    }

    @Test
    void getPaymentEmployeeNotExist() {
        LocalDateTime date = LocalDateTime.of(2023, 7, 4, 8, 0);
        systemFacade.addBranchShift(date, 5, 5, 5, 5, 5, 5, 5, "Beer Sheva","M");
        systemFacade.addEmployeeToSchedule("111111111", 1);
        systemFacade.scheduleEmployeeToRole("111111111", 1, "cashier");
        String message = systemFacade.getPayment("111144111", 0, 0);
        assertEquals("Employee doesn't exist", message);
    }

    @Test
    void getPaymentNoChosenByHR() {
        LocalDateTime date = LocalDateTime.of(2023, 7, 4, 8, 0);
        systemFacade.addBranchShift(date, 5, 5, 5, 5, 5, 5, 5, "Beer Sheva","M");
        systemFacade.scheduleEmployeeToRole("111111111", 1, "cashier");
        String message = systemFacade.getPayment("111111111", 0, 0);
        assertEquals("0", message);
    }

    @Test
    void isFutureShiftsTrue() {
        LocalDateTime date = LocalDateTime.of(2023, 7, 4, 8, 0);
        systemFacade.addBranchShift(date, 5, 5, 5, 5, 5, 5, 5, "Beer Sheva","M");
        String message = systemFacade.isFutureShifts(0);
        assertEquals("true", message);
    }

    @Test
    void isFutureShiftsShiftNotExist() {
        String message = systemFacade.isFutureShifts(1);
        assertEquals("Shift doesn't exist", message);
    }

    @Test
    void isFutureShiftsFalse() {
        LocalDateTime date = LocalDateTime.of(2023, 5, 10, 8, 0);
        systemFacade.addBranchShift(date, 5, 5, 5, 5, 5, 5, 5, "Beer Sheva","M");
        String message = systemFacade.isFutureShifts(0);
        assertEquals("false", message);
    }

    @Test
    void editConstraintShiftSuccess() {
        LocalDateTime date = LocalDateTime.of(2023, 7, 4, 8, 0);
        systemFacade.addBranchShift(date, 5, 5, 5, 5, 5, 5, 5, "Beer Sheva","M");
        systemFacade.addBranchShift(date, 5, 5, 5, 5, 5, 5, 5, "Beer Sheva","E");
        systemFacade.addConstraint("111111111", 0, "fffffff");
        String message = systemFacade.editConstraintShift("111111111", 1, 0);
        assertEquals("Constraint has been edited successfully", message);
    }

    @Test
    void editConstraintShiftNotExistShift() {
        LocalDateTime date = LocalDateTime.of(2023, 7, 4, 8, 0);
        systemFacade.addBranchShift(date, 5, 5, 5, 5, 5, 5, 5, "Beer Sheva","M");
        systemFacade.addConstraint("111111111", 0, "fffffff");
        String message = systemFacade.editConstraintShift("111111111", 1, 2);
        assertEquals("Shift doesn't exist", message);
    }

    @Test
    void editConstraintShiftNotExistedEmployee() {
        LocalDateTime date = LocalDateTime.of(2023, 7, 4, 8, 0);
        systemFacade.addBranchShift(date, 5, 5, 5, 5, 5, 5, 5, "Beer Sheva","M");
        systemFacade.addBranchShift(date, 5, 5, 5, 5, 5, 5, 5, "Beer Sheva","E");
        systemFacade.addConstraint("111111111", 1, "fffffff");
        String message = systemFacade.editConstraintShift("111222111", 1, 2);
        assertEquals("Employee doesn't exist", message);
    }

    @Test
    void editConstraintShiftNotExistedConstraint() {
        LocalDateTime date = LocalDateTime.of(2023, 7, 4, 8, 0);
        systemFacade.addBranchShift(date, 5, 5, 5, 5, 5, 5, 5, "Beer Sheva","M");
        systemFacade.addBranchShift(date, 5, 5, 5, 5, 5, 5, 5, "Beer Sheva","E");
        systemFacade.addConstraint("111111111", 1, "fffffff");
        String message = systemFacade.editConstraintShift("111111111", 3, 2);
        assertEquals("Constraint does not exist at employee", message);
    }

    @Test
    void addConstraintSuccess() {
        systemFacade.login("111111111", "Aa1111111");
        LocalDateTime date = LocalDateTime.of(2023, 7, 4, 8, 0);
        systemFacade.addBranchShift(date, 5, 5, 5, 5, 5, 5, 5, "Beer Sheva","M");
        String message = systemFacade.addConstraint("111111111", 0, "fffffff");
        assertEquals("Constraint added successfully", message);
    }

    @Test
    void addConstraintAddAnotherConstraint() {
        systemFacade.login("111111111", "Aa1111111");
        LocalDateTime date = LocalDateTime.of(2023, 7, 4, 8, 0);
        systemFacade.addBranchShift(date, 5, 5, 5, 5, 5, 5, 5, "Beer Sheva","M");
        systemFacade.addConstraint("111111111", 0, "fffffff");
        String message = systemFacade.addConstraint("111111111", 0, "fffffff");
        assertEquals("Constraint on this shift already existed", message);
    }

    @Test
    void addConstraintNotExisedEmployee() {
        LocalDateTime date = LocalDateTime.of(2023, 7, 4, 8, 0);
        systemFacade.addBranchShift(date, 5, 5, 5, 5, 5, 5, 5, "Beer Sheva","M");
        String message = systemFacade.addConstraint("11112211", 1, "fffffff");
        assertEquals("Employee doesn't exist", message);
    }

    @Test
    void addConstraintNotExistedShift() {
        String message = systemFacade.addConstraint("111111111", 1, "fffffff");
        assertEquals("Shift doesn't exist", message);
    }

    @Test
    void addEmployeeToSchedulesSuccess() {
        LocalDateTime date = LocalDateTime.of(2023, 7, 4, 8, 0);
        systemFacade.addBranchShift(date, 5, 5, 5, 5, 5, 5, 5, "Beer Sheva","M");
        String message = systemFacade.addEmployeeToSchedule("111111111", 0);
        assertEquals("Employee was added to shift successfully", message);
    }

    @Test
    void addEmployeeToSchedulesNotAvailable() {
        LocalDateTime date = LocalDateTime.of(2023, 7, 4, 8, 0);
        systemFacade.addBranchShift(date, 5, 5, 5, 5, 5, 5, 5, "Beer Sheva","M");
        systemFacade.addConstraint("111111111", 0, "aaaaaaa");
        String message = systemFacade.addEmployeeToSchedule("111111111", 0);
        assertEquals("The employee Aaa Aaa cannot work at this shift during his constraints", message);
    }

    @Test
    void addEmployeeToScheduleNotExistedEmployee() {
        LocalDateTime date = LocalDateTime.of(2023, 7, 4, 8, 0);
        systemFacade.addBranchShift(date, 5, 5, 5, 5, 5, 5, 5, "Beer Sheva","M");
        String message = systemFacade.addEmployeeToSchedule("111122111", 1);
        assertEquals("Employee doesn't exist", message);
    }

    @Test
    void addEmployeeToScheduleNotExistedShift() {
        LocalDateTime date = LocalDateTime.of(2023, 7, 4, 8, 0);
        systemFacade.addBranchShift(date, 5, 5, 5, 5, 5, 5, 5, "Beer Sheva","M");
        String message = systemFacade.addEmployeeToSchedule("111111111", 1);
        assertEquals("Shift doesn't exist", message);
    }

    @Test
    void addEmployeeToScheduleAlreadyChosenByHR() {
        LocalDateTime date = LocalDateTime.of(2023, 7, 4, 8, 0);
        systemFacade.addBranchShift(date, 5, 5, 5, 5, 5, 5, 5, "Beer Sheva","M");
        systemFacade.addEmployeeToSchedule("111111111", 0);
        systemFacade.scheduleEmployeeToRole("111111111", 0, "cashier");
        String message = systemFacade.addEmployeeToSchedule("111111111", 0);
        assertEquals("Employee was already chosen by HR Manager to work at this shift", message);
    }

    @Test
    void addEmployeeToScheduleHireDateIsLater() {
        LocalDateTime date = LocalDateTime.of(2023, 5, 10, 8, 0);
        systemFacade.addBranchShift(date, 5, 5, 5, 5, 5, 5, 5, "Beer Sheva","M");
        systemFacade.registerBranchEmployee("444444444", "bbb", "bbb", "Bb222222", 45, 12, 30, "bbb", "bbbbbb", LocalDateTime.of(2023, 5, 20, 1, 1), "0533191749", false, false);
        String message = systemFacade.addEmployeeToSchedule("444444444", 0);
        assertEquals("Employee cannot work at shifts yet", message);
    }

    @Test
    void addEmployeeToScheduleMoreThanOneShift() {
        LocalDateTime date = LocalDateTime.of(2023, 7, 4, 8, 0);
        systemFacade.addBranchShift(date, 5, 5, 5, 5, 5, 5, 5, "Beer Sheva","M");
        systemFacade.addBranchShift(date, 5, 5, 5, 5, 5, 5, 5, "Beer Sheva","E");
        systemFacade.addEmployeeToSchedule("111111111", 0);
        String message = systemFacade.addEmployeeToSchedule("111111111", 1);
        assertEquals("Employee cannot work more than one shift in day", message);
    }

    @Test
    void addEmployeeToScheduleToSameDayDifferentBranches() {
        LocalDateTime date = LocalDateTime.of(2023, 7, 4, 8, 0);
        systemFacade.addBranchShift(date, 5, 5, 5, 5, 5, 5, 5, "Beer Sheva","M");
        systemFacade.addBranchShift(date, 5, 5, 5, 5, 5, 5, 5, "Tel Aviv","M");
        systemFacade.addEmployeeToSchedule("111111111", 0);
        String message = systemFacade.addEmployeeToSchedule("111111111", 1);
        assertEquals("Employee already work at this time in another branch", message);
    }

    @Test
    void availableEmployeesNamesBESuccess() {
        LocalDateTime date = LocalDateTime.of(2023, 7, 4, 8, 0);
        systemFacade.addBranchShift(date, 5, 5, 5, 5, 5, 5, 5, "Beer Sheva","M");
        String message = systemFacade.availableEmployeesNamesBE(0);
        assertEquals("""
                ID: 111111111 First Name: Aaa Last Name: Aaa
                """, message);
    }

    @Test
    void availableEmployeesNamesNotExistedEmployeesBE() {
        LocalDateTime date = LocalDateTime.of(2023, 7, 4, 8, 0);
        systemFacade.addBranchShift(date, 5, 5, 5, 5, 5, 5, 5, "Beer Sheva","M");
        systemFacade.addConstraint("111111111", 0, "fffffff");
        systemFacade.addConstraint("333333333", 0, "fffffff");
        String message = systemFacade.availableEmployeesNamesBE(0);
        assertEquals("", message);
    }

    @Test
    void availableEmployeesNamesShiftNotExistedBE() {
        String message = systemFacade.availableEmployeesNamesBE(3);
        assertEquals("Shift doesn't exist", message);
    }

    @Test
    void AlertAboutShiftsNoShiftToBeScheduled() {
        LocalDateTime date = LocalDateTime.of(2023, 3, 7, 8, 0);
        systemFacade.addBranchShift(date, 5, 5, 5, 5, 5, 5, 5, "Beer Sheva","M");
        String message = systemFacade.alertAboutShifts();
        assertEquals("", message);
    }

    @Test
    void removeCancellationSuccess() {
        systemFacade.setCancellation("111111111");
        LocalDateTime date = LocalDateTime.of(2023, 7, 4, 8, 0);
        systemFacade.addBranchShift(date, 5, 5, 5, 5, 5, 5, 5, "Beer Sheva","M");
        systemFacade.addCancellation(0, "12-14", "111111111");
        String message = systemFacade.removeCancellation(0, "12-14", "111111111");
        assertEquals("Remove cancellation successfully", message);
    }

    @Test
    void removeCancellationNoProductCancellation() {
        systemFacade.setCancellation("111111111");
        LocalDateTime date = LocalDateTime.of(2023, 7, 4, 8, 0);
        systemFacade.addBranchShift(date, 5, 5, 5, 5, 5, 5, 5, "Beer Sheva","M");
        String message = systemFacade.removeCancellation(0, "12-14", "111111111");
        assertEquals("Product cancellation does not exist", message);
    }

    @Test
    void removeCancellationNoLegalToEmployee() {
        LocalDateTime date = LocalDateTime.of(2023, 7, 4, 8, 0);
        systemFacade.addBranchShift(date, 5, 5, 5, 5, 5, 5, 5, "Beer Sheva","M");
        systemFacade.addCancellation(0, "12-14", "111111111");
        String message = systemFacade.removeCancellation(0, "12-14", "111111111");
        assertEquals("Employee missing cancellation training", message);
    }

    @Test
    void removeCancellationNotExistedEmployee() {
        systemFacade.setCancellation("111111111");
        LocalDateTime date = LocalDateTime.of(2023, 7, 4, 8, 0);
        systemFacade.addBranchShift(date, 5, 5, 5, 5, 5, 5, 5, "Beer Sheva","M");
        systemFacade.addCancellation(1, "12-14", "111111111");
        String message = systemFacade.removeCancellation(1, "12-14", "111112111");
        assertEquals("Employee doesn't exist", message);
    }

    @Test
    void removeCancellationNotExistedShift() {
        systemFacade.setCancellation("111111111");
        LocalDateTime date = LocalDateTime.of(2023, 7, 4, 8, 0);
        systemFacade.addBranchShift(date, 5, 5, 5, 5, 5, 5, 5, "Beer Sheva","M");
        systemFacade.addCancellation(1, "12-14", "111111111");
        String message = systemFacade.removeCancellation(3, "12-14", "111111111");
        assertEquals("Shift doesn't exist", message);
    }

    @Test
    void addDriverShiftSuccess() {
        LocalDateTime date = LocalDateTime.of(2023, 7, 1, 1, 1);
        String message = systemFacade.addDriverShift(date, 2);
        assertEquals("Driver shift was added successfully", message);
    }

    @Test
    void addDriverShiftIllegalCount() {
        LocalDateTime date = LocalDateTime.of(2023, 7, 1, 1, 1);
        String message = systemFacade.addDriverShift(date, -1);
        assertEquals("Drivers count cannot be less than zero", message);
    }

    @Test
    void addDriverScheduleSuccess() {
        LocalDateTime date = LocalDateTime.of(2023, 7, 1, 1, 1);
        systemFacade.addDriverShift(date, 2);
        String message = systemFacade.addDriverSchedule("333333333", 0);
        assertEquals("Driver was scheduled successfully", message);
    }

    @Test
    void addDriverScheduleFullDriversCount() {
        LocalDateTime date = LocalDateTime.of(2023, 7, 1, 1, 1);
        systemFacade.addDriverShift(date, 0);
        systemFacade.registerDriver("777888777", "kfir", "nissim", "Kn123456", 45, 67, 30, "aaaaaaa", "aaaaaaa", LocalDateTime.of(2023, 1, 1, 1, 1), "0533912712", 15000);
        systemFacade.addDriverSchedule("333333333", 0);
        String message = systemFacade.addDriverSchedule("777888777", 0);
        assertEquals("The shift is already full with drivers", message);
    }

    @Test
    void addDriverScheduleNotExistedDriver() {
        LocalDateTime date = LocalDateTime.of(2023, 7, 1, 1, 1);
        systemFacade.addDriverShift(date, 2);
        String message = systemFacade.addDriverSchedule("333333344", 1);
        assertEquals("Employee doesn't exist", message);
    }

    @Test
    void addDriverScheduleNotExistedShift() {
        LocalDateTime date = LocalDateTime.of(2023, 7, 1, 1, 1);
        systemFacade.addDriverShift(date, 2);
        String message = systemFacade.addDriverSchedule("333333333", 6);
        assertEquals("Shift does not exists", message);
    }

    @Test
    void addDriverScheduleToNotDriver() {
        LocalDateTime date = LocalDateTime.of(2023, 7, 1, 1, 1);
        systemFacade.addDriverShift(date, 2);
        String message = systemFacade.addDriverSchedule("111111111", 1);
        assertEquals("Employee is not a driver", message);
    }

    @Test
    void addDriverScheduleConstraints() {
        LocalDateTime date = LocalDateTime.of(2023, 7, 1, 1, 1);
        systemFacade.addDriverShift(date, 2);
        systemFacade.addConstraint("333333333", 0, "tttttttttt");
        String message = systemFacade.addDriverSchedule("333333333", 0);
        assertEquals("The employee Ccc Ccc cannot work at this shift during his constraints", message);
    }

    @Test
    void addDriverScheduleTwiceSameShift() {
        LocalDateTime date = LocalDateTime.of(2023, 7, 1, 1, 1);
        systemFacade.addDriverShift(date, 2);
        systemFacade.addDriverSchedule("333333333", 0);
        String message = systemFacade.addDriverSchedule("333333333", 0);
        assertEquals("Driver was already chosen by HR Manager to work at this shift", message);
    }

    @Test
    void removeDriverScheduleSuccess() {
        String message;
        try {
            LocalDateTime date = LocalDateTime.of(2023, 7, 1, 1, 1);
            systemFacade.addDriverShift(date, 2);
            systemFacade.addDriverSchedule("333333333", 0);
            systemFacade.getHrService().shiftService.getShiftController().removeDriverSchedule("333333333", 0);
            message = "success";
        } catch (Exception e) {
            message = e.getMessage();
        }
        assertEquals("success", message);
    }

    @Test
    void removeDriverScheduleSuccessAtDeliveries() {
        try {
            LocalDateTime date = LocalDateTime.of(2023, 7, 4, 1, 1);
            systemFacade.addDriverShift(date, 2);
            systemFacade.addDriverSchedule("333333333", 1);
            systemFacade.addTruck(6666, "reg", "mazda", 1500, 4000);
            systemFacade.addDelivery(LocalDate.of(2023, 7, 4), LocalTime.of(11, 0), 6666, "333333333", "Beer Sheva");
            systemFacade.approveDelivery(0);
            systemFacade.addDriverDeliveryDate("333333333", LocalDate.of(2023, 7, 4));
            systemFacade.getHrService().shiftService.getShiftController().removeDriverSchedule("333333333", 1);
        } catch (Exception e) {
        }
        assertTrue(systemFacade.getDeliveryService().getDeliveryController().getDeliveries().get(0).getStatusOrdinal() == 0);
    }

    @Test
    void removeDriverScheduleNotExistedShift() {
        String message;
        try {
            LocalDateTime date = LocalDateTime.of(2023, 7, 1, 1, 1);
            systemFacade.addDriverShift(date, 2);
            systemFacade.addDriverSchedule("333333333", 1);
            systemFacade.getHrService().shiftService.getShiftController().removeDriverSchedule("333333333", 6);
            message = "success";
        } catch (Exception e) {
            message = e.getMessage();
        }
        assertEquals("Shift doesn't exist", message);
    }

    @Test
    void removeDriverScheduleNotExistedEmployee() {
        String message;
        try {
            LocalDateTime date = LocalDateTime.of(2023, 7, 1, 1, 1);
            systemFacade.addDriverShift(date, 2);
            systemFacade.addDriverSchedule("333333333", 1);
            systemFacade.getHrService().shiftService.getShiftController().removeDriverSchedule("333334333", 1);
            message = "success";
        } catch (Exception e) {
            message = e.getMessage();
        }
        assertEquals("Employee doesn't exist", message);
    }

    @Test
    void removeDriverScheduleNotDriver() {
        String message;
        try {
            LocalDateTime date = LocalDateTime.of(2023, 7, 1, 1, 1);
            systemFacade.addDriverShift(date, 2);
            systemFacade.addDriverSchedule("333333333", 1);
            systemFacade.getHrService().shiftService.getShiftController().removeDriverSchedule("111111111", 1);
            message = "success";
        } catch (Exception e) {
            message = e.getMessage();
        }
        assertEquals("Employee is not a driver", message);
    }

    @Test
    void removeDriverScheduleNotScheduled() {
        String message;
        try {
            LocalDateTime date = LocalDateTime.of(2023, 7, 1, 1, 1);
            systemFacade.addDriverShift(date, 2);
            systemFacade.getHrService().shiftService.getShiftController().removeDriverSchedule("333333333", 0);
            message = "success";
        } catch (Exception e) {
            message = e.getMessage();
        }
        assertEquals("Driver is not in this shift", message);
    }

    @Test
    void changeDriverScheduleSuccess() {
        LocalDateTime date = LocalDateTime.of(2023, 7, 1, 1, 1);
        systemFacade.addDriverShift(date, 2);
        LocalDateTime date1 = LocalDateTime.of(2023, 8, 1, 1, 1);
        systemFacade.addDriverShift(date1, 2);
        systemFacade.addDriverSchedule("333333333", 0);
        String message = systemFacade.changeDriverSchedule("333333333", 0, 1);
        assertEquals("Driver's schedule changed successfully", message);
    }

    @Test
    void changeDriverScheduleNotExistEmployee() {
        LocalDateTime date = LocalDateTime.of(2023, 7, 1, 1, 1);
        systemFacade.addDriverShift(date, 2);
        LocalDateTime date1 = LocalDateTime.of(2023, 8, 1, 1, 1);
        systemFacade.addDriverShift(date1, 2);
        systemFacade.addDriverSchedule("333333333", 1);
        String message = systemFacade.changeDriverSchedule("333333444", 1, 2);
        assertEquals("Employee doesn't exist", message);
    }

    @Test
    void changeDriverScheduleNotExistNewShift() {
        LocalDateTime date = LocalDateTime.of(2023, 7, 1, 1, 1);
        systemFacade.addDriverShift(date, 2);
        LocalDateTime date1 = LocalDateTime.of(2023, 8, 1, 1, 1);
        systemFacade.addDriverShift(date1, 2);
        systemFacade.addDriverSchedule("333333333", 1);
        String message = systemFacade.changeDriverSchedule("333333333", 1, 6);
        assertEquals("Shift doesn't exist", message);
    }

    @Test
    void changeDriverScheduleNotExistOldShift() {
        LocalDateTime date = LocalDateTime.of(2023, 7, 1, 1, 1);
        systemFacade.addDriverShift(date, 2);
        systemFacade.addDriverSchedule("333333333", 1);
        String message = systemFacade.changeDriverSchedule("333333333", 2, 1);
        assertEquals("Shift doesn't exist", message);
    }

    @Test
    void changeDriverScheduleNotADriver() {
        LocalDateTime date = LocalDateTime.of(2023, 7, 1, 1, 1);
        systemFacade.addDriverShift(date, 2);
        LocalDateTime date1 = LocalDateTime.of(2023, 8, 1, 1, 1);
        systemFacade.addDriverShift(date1, 2);
        systemFacade.addDriverSchedule("333333333", 0);
        String message = systemFacade.changeDriverSchedule("111111111", 0, 1);
        assertEquals("Employee is not a driver", message);
    }

    @Test
    void isDriverScheduledTrue() throws Exception {
        LocalDateTime date = LocalDateTime.of(2023, 7, 1, 1, 1);
        systemFacade.addDriverShift(date, 2);
        systemFacade.addDriverSchedule("333333333", 0);
        boolean answer = systemFacade.getHrService().shiftService.getShiftController().isDriverScheduled("333333333", date.toLocalDate());
        String message = Boolean.toString(answer);
        assertEquals("true", message);
    }

    @Test
    void isDriverScheduledFalseNewDate() throws Exception {
        LocalDateTime date = LocalDateTime.of(2023, 7, 1, 1, 1);
        systemFacade.addDriverShift(date, 2);
        systemFacade.addDriverSchedule("333333333", 0);
        LocalDateTime date1 = LocalDateTime.of(2023, 8, 1, 1, 1);
        boolean answer = systemFacade.getHrService().shiftService.getShiftController().isDriverScheduled("333333333", date1.toLocalDate());
        String message = Boolean.toString(answer);
        assertEquals("false", message);
    }

    @Test
    void isDriverScheduledFalseNotDriver() throws Exception {
        LocalDateTime date = LocalDateTime.of(2023, 7, 1, 1, 1);
        systemFacade.addDriverShift(date, 2);
        systemFacade.addDriverSchedule("333333333", 1);
        systemFacade.addBranchShift(date, 5, 5, 5, 5, 5, 5, 5, "Beer Sheva","M");
        systemFacade.addEmployeeToSchedule("111111111", 2);
        boolean answer = systemFacade.getHrService().shiftService.getShiftController().isDriverScheduled("111111111", date.toLocalDate());
        String message = Boolean.toString(answer);
        assertEquals("false", message);
    }

    @Test
    void isDriverScheduledNotScheduled() throws Exception {
        LocalDateTime date = LocalDateTime.of(2023, 7, 1, 1, 1);
        systemFacade.addDriverShift(date, 2);
        boolean answer = systemFacade.getHrService().shiftService.getShiftController().isDriverScheduled("333333333", date.toLocalDate());
        String message = Boolean.toString(answer);
        assertEquals("false", message);
    }

    @Test
    void isStoreKeeperExistSuccess() {
        String message;
        try {
            systemFacade.registerBranchEmployee("888888888", "bbb", "bbb", "Bb222222", 45, 12, 30, "bbb", "bbbbbb", LocalDateTime.of(2022, 1, 1, 1, 1), "0533719572", false, false);
            systemFacade.insertRole("888888888", "store keeper");
            LocalDateTime date = LocalDateTime.of(2023, 7, 4, 8, 0);
            systemFacade.addBranchShift(date, 5, 5, 5, 5, 5, 5, 5, "Beer Sheva","M");
            systemFacade.addEmployeeToSchedule("888888888", 0);
            systemFacade.scheduleEmployeeToRole("888888888", 0, "store keeper");
            systemFacade.registerBranchEmployee("999999999", "bbb", "bbb", "Bb222222", 45, 12, 30, "bbb", "bbbbbb", LocalDateTime.of(2022, 1, 1, 1, 1), "0544279391", false, false);
            systemFacade.insertRole("999999999", "store keeper");
            systemFacade.addBranchShift(date, 5, 5, 5, 5, 5, 5, 5, "Beer Sheva","E");
            systemFacade.addEmployeeToSchedule("999999999", 1);
            systemFacade.scheduleEmployeeToRole("999999999", 1, "store keeper");
            systemFacade.getHrService().shiftService.getShiftController().isStoreKeeperExist(date.toLocalDate(), "Beer Sheva", LocalTime.of(10, 0));
            message = "success";
        } catch (Exception e) {
            message = e.getMessage();
        }
        assertEquals("success", message);
    }

    @Test
    void isStoreKeeperExistBranchNotExist() {
        String message;
        try {
            systemFacade.registerBranchEmployee("888888888", "bbb", "bbb", "Bb222222", 45, 12, 30, "bbb", "bbbbbb", LocalDateTime.of(2022, 1, 1, 1, 1), "0533719572", false, false);
            systemFacade.insertRole("888888888", "store keeper");
            LocalDateTime date = LocalDateTime.of(2023, 7, 4, 8, 0);
            systemFacade.addBranchShift(date, 5, 5, 5, 5, 5, 5, 5, "Beer Sheva","M");
            systemFacade.addEmployeeToSchedule("888888888", 1);
            systemFacade.scheduleEmployeeToRole("888888888", 1, "store keeper");
            systemFacade.registerBranchEmployee("999999999", "bbb", "bbb", "Bb222222", 45, 12, 30, "bbb", "bbbbbb", LocalDateTime.of(2022, 1, 1, 1, 1), "0544279391", false, false);
            systemFacade.insertRole("888888888", "store keeper");
            systemFacade.addBranchShift(date, 5, 5, 5, 5, 5, 5, 5, "Beer Sheva","E");
            systemFacade.addEmployeeToSchedule("999999999", 2);
            systemFacade.scheduleEmployeeToRole("999999999", 2, "store keeper");
            systemFacade.getHrService().shiftService.getShiftController().isStoreKeeperExist(date.toLocalDate(), "Beer Shea", LocalTime.of(10, 0));
            message = "success";
        } catch (Exception e) {
            message = e.getMessage();
        }
        assertEquals("Branch is not exists", message);
    }

    @Test
    void isStoreKeeperExistTwoMorningShifts() {
        String message;
        try {
            systemFacade.registerBranchEmployee("888888888", "bbb", "bbb", "Bb222222", 45, 12, 30, "bbb", "bbbbbb", LocalDateTime.of(2022, 1, 1, 1, 1), "0533719572", false, false);
            systemFacade.insertRole("888888888", "store keeper");
            LocalDateTime date = LocalDateTime.of(2023, 7, 4, 8, 0);
            systemFacade.addBranchShift(date, 5, 5, 5, 5, 5, 5, 5, "Beer Sheva","M");
            systemFacade.addEmployeeToSchedule("888888888", 1);
            systemFacade.scheduleEmployeeToRole("888888888", 1, "store keeper");
            systemFacade.registerBranchEmployee("999999999", "bbb", "bbb", "Bb222222", 45, 12, 30, "bbb", "bbbbbb", LocalDateTime.of(2022, 1, 1, 1, 1), "0544279391", false, false);
            systemFacade.insertRole("999999999", "store keeper");
            systemFacade.addBranchShift(date, 5, 5, 5, 5, 5, 5, 5, "Beer Sheva","M");
            systemFacade.getHrService().shiftService.getShiftController().isStoreKeeperExist(date.toLocalDate(), "Beer Sheva", LocalTime.of(10, 0));
            message = "success";
        } catch (Exception e) {
            message = e.getMessage();
        }
        assertEquals("Needed two shifts: Morning and Evening", message);
    }

    @Test
    void isStoreKeeperExistStoreKeeperNotExist() {
        String message;
        try {
            systemFacade.registerBranchEmployee("888888888", "bbb", "bbb", "Bb222222", 45, 12, 30, "bbb", "bbbbbb", LocalDateTime.of(2022, 1, 1, 1, 1), "0533719572", false, false);
            systemFacade.insertRole("888888888", "store keeper");
            LocalDateTime date = LocalDateTime.of(2023, 7, 4, 8, 0);
            systemFacade.addBranchShift(date, 5, 5, 5, 5, 5, 5, 5, "Beer Sheva","M");
            systemFacade.addEmployeeToSchedule("888888888", 1);
            systemFacade.scheduleEmployeeToRole("888888888", 1, "store keeper");
            systemFacade.registerBranchEmployee("999999999", "bbb", "bbb", "Bb222222", 45, 12, 30, "bbb", "bbbbbb", LocalDateTime.of(2022, 1, 1, 1, 1), "0544279391", false, false);
            systemFacade.insertRole("999999999", "store keeper");
            systemFacade.addBranchShift(date, 5, 5, 5, 5, 5, 5, 5, "Beer Sheva","E");
            systemFacade.addEmployeeToSchedule("999999999", 2);
            systemFacade.getHrService().shiftService.getShiftController().isStoreKeeperExist(date.toLocalDate(), "Beer Sheva", LocalTime.of(10, 0));
            message = "success";
        } catch (Exception e) {
            message = e.getMessage();
        }
        assertEquals("One store keeper must be in this shift", message);
    }

    @Test
    void isStoreKeeperExistBranchNotWorkingAtTime() {
        String message;
        try {
            systemFacade.registerBranchEmployee("888888888", "bbb", "bbb", "Bb222222", 45, 12, 30, "bbb", "bbbbbb", LocalDateTime.of(2022, 1, 1, 1, 1), "0533719572", false, false);
            systemFacade.insertRole("888888888", "store keeper");
            LocalDateTime date = LocalDateTime.of(2023, 7, 4, 8, 0);
            systemFacade.addBranchShift(date, 5, 5, 5, 5, 5, 5, 5, "Beer Sheva","M");
            systemFacade.addEmployeeToSchedule("888888888", 0);
            systemFacade.scheduleEmployeeToRole("888888888", 0, "store keeper");
            systemFacade.registerBranchEmployee("999999999", "bbb", "bbb", "Bb222222", 45, 12, 30, "bbb", "bbbbbb", LocalDateTime.of(2022, 1, 1, 1, 1), "0544279391", false, false);
            systemFacade.insertRole("999999999", "store keeper");
            systemFacade.addBranchShift(date, 5, 5, 5, 5, 5, 5, 5, "Beer Sheva","E");
            systemFacade.addEmployeeToSchedule("999999999", 1);
            systemFacade.scheduleEmployeeToRole("999999999", 1, "store keeper");
            systemFacade.getHrService().shiftService.getShiftController().isStoreKeeperExist(date.toLocalDate(), "Beer Sheva", LocalTime.of(7, 0));
            message = "success";
        } catch (Exception e) {
            message = e.getMessage();
        }
        assertEquals("Branch is not working at time of delivery", message);
    }

    @Test
    void getDeliveriesByShiftAtDeliveries() {
        ItemOrder o1 = systemFacade.getDeliveryService().getDeliveryController().addItemOrderToSystem(111, 0, "Beer Sheva", "Tel Aviv", 20, false);
        ItemOrder o2 = systemFacade.getDeliveryService().getDeliveryController().addItemOrderToSystem(222, 0, "Beer Sheva", "Tel Aviv", 30, false);
        HashMap<Integer, ItemOrder> loadList = new HashMap<>();
        loadList.put(0, o1);
        loadList.put(1, o2);
        systemFacade.getDeliveryService().getDeliveryController().addDeliveryOrder(65, "Tel Aviv", "Beer Sheva", loadList);
        systemFacade.addTruck(6666, "reg", "mazda", 1500, 4000);
        systemFacade.registerDriver("777888777", "kfir", "nissim", "Kn123456", 45, 67, 30, "aaaaaaa", "aaaaaaa", LocalDateTime.of(2023, 1, 1, 1, 1), "0548929158", 15000);
        systemFacade.addDelivery(LocalDate.of(2023, 7, 11), LocalTime.of(11, 0), 6666, "777888777", "Beer Sheva");
        systemFacade.addItemToDelivery(0, 0, false, 0);
        LocalDateTime date = LocalDateTime.of(2023, 7, 11, 8, 0);
        systemFacade.addBranchShift(date, 5, 5, 5, 5, 5, 5, 5, "Beer Sheva","M");
        String json = systemFacade.getDeliveryService().showDeliveryByShift(0);
        json = json.replaceAll("\\r\\n?", "");
        json = json.replaceAll("\\r", "\n");
        String jsonToCheck = "[ {  \"date\" : \"2023-07-11\",  \"time\" : \"11:00:00\",  \"source\" : {    \"region\" : \"SOUTH\",    \"type\" : \"STORE\",    \"address\" : \"Beer Sheva\",    \"phoneNumber\" : \"053739204\",    \"contactName\" : \"Aaa Aaa\",    \"coordinate\" : {      \"latitude\" : 31.25181,      \"longitude\" : 34.7913    },    \"timeOfShifts\" : [ \"TUESDAY E\", \"TUESDAY M\", \"FRIDAY E\", \"FRIDAY M\", \"SATURDAY E\", \"SATURDAY M\", \"SUNDAY E\", \"SUNDAY M\", \"MONDAY E\", \"MONDAY M\", \"THURSDAY E\", \"THURSDAY M\", \"WEDNESDAY E\", \"WEDNESDAY M\" ],    \"morningShiftStartHour\" : \"08:00:00\",    \"eveningShiftStartHour\" : \"13:00:00\",    \"morningShiftEndHour\" : \"13:00:00\",    \"eveningShiftEndHour\" : \"22:00:00\"  },  \"destinations\" : [ {    \"id\" : 0,    \"deliveryId\" : 0,    \"destination\" : {      \"region\" : \"SOUTH\",      \"type\" : \"STORE\",      \"address\" : \"Beer Sheva\",      \"phoneNumber\" : \"053739204\",      \"contactName\" : \"Aaa Aaa\",      \"coordinate\" : {        \"latitude\" : 31.25181,        \"longitude\" : 34.7913      },      \"timeOfShifts\" : [ \"TUESDAY E\", \"TUESDAY M\", \"FRIDAY E\", \"FRIDAY M\", \"SATURDAY E\", \"SATURDAY M\", \"SUNDAY E\", \"SUNDAY M\", \"MONDAY E\", \"MONDAY M\", \"THURSDAY E\", \"THURSDAY M\", \"WEDNESDAY E\", \"WEDNESDAY M\" ],      \"morningShiftStartHour\" : \"08:00:00\",      \"eveningShiftStartHour\" : \"13:00:00\",      \"morningShiftEndHour\" : \"13:00:00\",      \"eveningShiftEndHour\" : \"22:00:00\"    },    \"loadList\" : [ ],    \"status\" : \"PENDING\",    \"arriveTime\" : \"2023-07-11T11:00:00\",    \"unloadList\" : [ ]  } ],  \"truckId\" : 6666,  \"driverId\" : \"777888777\",  \"startingWeight\" : -1.0,  \"status\" : \"PENDING\",  \"maxWeight\" : -1.0,  \"id\" : 0,  \"statusOrdinal\" : 0} ]";
        jsonToCheck = jsonToCheck.replaceAll("\\r\\n?", "");
        jsonToCheck = jsonToCheck.replaceAll("\\r", "\n");
        assertEquals(jsonToCheck, json);
    }


}