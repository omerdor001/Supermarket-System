package Tests;

import ServiceLayer.SystemFacade;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeServiceTest {
    SystemFacade systemFacade;

    @BeforeEach
    void setUp() {
        systemFacade = SystemFacade.getInstance();
        systemFacade.DeleteInstance();
        systemFacade = SystemFacade.getInstance();
        systemFacade.registerEmployee("123456789", "Adi", "Cohen", "Ac123456", 123, 123, 27, "sssssssss", "bbbbbbbbbb", LocalDateTime.now(), "0548293498",2);
        systemFacade.registerBranchEmployee("111111111", "aaa", "aaa", "Aa1111111", 23, 23, 30, "aaa", "aaaaaaa", LocalDateTime.now(), "0544792209", false, false);
        LocalTime startMHour = LocalTime.of(8, 0);
        LocalTime endMHour = LocalTime.of(13, 0);
        LocalTime startEHour = LocalTime.of(13, 0);
        LocalTime endEHour = LocalTime.of(22, 0);
        systemFacade.addBranch("Beer Sheva", "053739204", "Aaa Aaa", 1, startMHour, startEHour, endMHour, endEHour, 31.25181, 34.7913);
        systemFacade.insertTimeOfShift("Beer Sheva", "TUESDAY", "E");
        systemFacade.insertTimeOfShift("Beer Sheva", "TUESDAY", "M");
        LocalDateTime date = LocalDateTime.of(2023, 7, 4, 8, 0);
        systemFacade.addBranchShift(date, 5, 5, 5, 5, 5, 5, 5, "Beer Sheva","M");
    }

    @AfterEach
    void setDown() {
        systemFacade.DeleteInstance();
    }

    @Test
    void registerBranchEmployeeSuccess() {
        String message = systemFacade.registerBranchEmployee("222222222", "bbb", "bbb", "Bb222222", 45, 43, 35, "bbb", "aaaaaa", LocalDateTime.now(), "0544392729", true, false);
        assertEquals("success", message);
    }

    @Test
    void registerBranchEmployeeIdNull() {
        String message = systemFacade.registerBranchEmployee(null, "bbb", "bbb", "Bb222222", 45, 43, 35, "bbb", "aaaaaa", LocalDateTime.now(), "0522834875", false, true);
        assertEquals("Employee Id is null", message);
    }

    @Test
    void registerBranchEmployeeId9Digits() {
        String message = systemFacade.registerBranchEmployee("22222222", "bbb", "bbb", "bb222222", 45, 43, 35, "bbb", "aaaaaa", LocalDateTime.now(), "0533871241", false, true);
        assertEquals("Employee Id must be with 9 numbers", message);
    }

    @Test
    void registerBranchEmployeeFirstNameNull() {
        String message = systemFacade.registerBranchEmployee("222222222", null, "bbb", "Bb222222", 45, 43, 35, "bbb", "aaaaaa", LocalDateTime.now(), "0543289253", false, true);
        assertEquals("First name is null", message);
    }

    @Test
    void registerBranchEmployeeLastNameEmpty() {
        String message = systemFacade.registerBranchEmployee("222222222", "bbb", "", "Bb222222", 45, 43, 35, "bbb", "aaaaaa", LocalDateTime.now(), "0522384912", false, true);
        assertEquals("Last name is empty", message);
    }

    @Test
    void registerBranchEmployeeExisted() {
        String message = systemFacade.registerBranchEmployee("111111111", "aaa", "aaa", "Aa1111111", 23, 23, 30, "aaa", "aaaaaaa", LocalDateTime.now(), "0522351780", false, false);
        assertEquals("Employee Id already exists", message);
    }

    @Test
    void registerBranchEmployeePassword() {
        String message = systemFacade.registerBranchEmployee("222222222", "bbb", "bbb", "bbbbbbbb", 45, 43, 35, "bbb", "aaaaaa", LocalDateTime.now(), "0522385890", false, true);
        assertEquals("Password must be with at least one digit", message);
    }

    @Test
    void registerDriverSuccess() {
        String message = systemFacade.registerDriver("666666666", "rrr", "rrr", "aviNaaman501", 200, 10, 30, "abcabc", "aaaaaa", LocalDateTime.now(), "0544819876", 30000);
        assertEquals("success", message);
    }

    @Test
    void registerDriverWrongLicenses() {
        String message = systemFacade.registerDriver("666666666", "rrr", "rrr", "aviNaaman501", 200, 10, 30, "abcabc", "aaaaaa", LocalDateTime.now(), "0531573987", -30000);
        assertEquals("illegal license", message);
    }

    @Test
    void registerDriverExisted() {
        systemFacade.registerDriver("666666666", "rrr", "rrr", "aviNaaman501", 200, 10, 30, "abcabc", "aaaaaa", LocalDateTime.now(), "0522351987", 30000);
        String message = systemFacade.registerDriver("666666666", "rrr", "rrr", "aviNaaman501", 200, 10, 30, "abcabc", "aaaaaa", LocalDateTime.now(), "0528762436", 30000);
        assertEquals("Employee Id already exists", message);
    }

    @Test
    void LoginSuccess() {
        String message = systemFacade.login("111111111", "Aa1111111");
        assertEquals("success", message);
    }

    @Test
    void loginNotSuccess() {
        String message = systemFacade.login("111111111", "Aa1111112");
        assertEquals("Password does not match", message);
    }

    @Test
    void LoginNullPassword() {
        String message = systemFacade.login("111111111", null);
        assertEquals("Password is null", message);
    }

    @Test
    void LoginLoggedIn() {
        systemFacade.login("111111111", "Aa1111111");
        String message = systemFacade.login("111111111", "Aa1111111");
        assertEquals("User already logged in", message);
    }

    @Test
    void loginNotExisted() {
        String message = systemFacade.login("111111112", "Aa1111111");
        assertEquals("Employee doesn't exist", message);
    }

    @Test
    void logoutSuccess() {
        systemFacade.login("111111111", "Aa1111111");
        String message = systemFacade.logout("111111111");
        assertEquals("Logout Successfully", message);
    }

    @Test
    void logoutNotExisted() {
        String message = systemFacade.logout("111111112");
        assertEquals("Employee doesn't exist", message);
    }

    @Test
    void logoutNotLogged() {
        String message = systemFacade.logout("111111111");
        assertEquals("User is already logged out", message);
    }

    @Test
    void isEmployeeExistsTrue() {
        String message = systemFacade.isEmployeeExists("111111111");
        assertEquals("true", message);
    }

    @Test
    void isEmployeeExistsFalse() {
        String message = systemFacade.isEmployeeExists("111112211");
        assertEquals("false", message);
    }

    @Test
    void editPasswordSuccess() {
        systemFacade.login("111111111", "Aa1111111");
        String message = systemFacade.editPassword("111111111", "Om123456");
        assertEquals("success", message);
    }

    @Test
    void editPasswordDigit() {
        systemFacade.login("111111111", "Aa1111111");
        String message = systemFacade.editPassword("111111111", "aaaaaaaaaaaa");
        assertEquals("Password must be with at least one digit", message);
    }

    @Test
    void editPasswordUpper() {
        systemFacade.login("111111111", "Aa1111111");
        String message = systemFacade.editPassword("111111111", "57363737");
        assertEquals("Password must be with at least one upper letter", message);
    }

    @Test
    void editPasswordLower() {
        systemFacade.login("111111111", "Aa1111111");
        String message = systemFacade.editPassword("111111111", "57S63737");
        assertEquals("Password must be with at least one lower letter", message);
    }

    @Test
    void editPasswordShort() {
        systemFacade.login("111111111", "Aa1111111");
        String message = systemFacade.editPassword("111111111", "57");
        assertEquals("Password must be between 6 till 20 characters", message);
    }

    @Test
    void editPasswordNull() {
        systemFacade.login("111111111", "Aa1111111");
        String message = systemFacade.editPassword("111111111", null);
        assertEquals("Password is null", message);
    }

    @Test
    void editPasswordNotExists() {
        systemFacade.login("111111111", "Aa1111111");
        String message = systemFacade.editPassword("111116661", "Om123456");
        assertEquals("Employee doesn't exist", message);
    }

    @Test
    void editConstraintDescriptionSuccess() {
        systemFacade.addConstraint("111111111", 0, "fffffff");
        String message = systemFacade.editConstraintDescription("111111111", 1, "rrrrrrrrrrrrr");
        assertEquals("Constraint has been edited successfully", message);
    }

    @Test
    void editConstraintDescriptionNotExistConstraint() {
        systemFacade.addConstraint("111111111", 4, "fffffff");
        String message = systemFacade.editConstraintDescription("111111111", 1, "rrrrrrrrrrrrr");
        assertEquals("Constraint does not exist at employee", message);
    }

    @Test
    void editConstraintDescriptionNotExistEmployee() {
        systemFacade.addConstraint("111134561", 0, "fffffff");
        String message = systemFacade.editConstraintDescription("111134561", 1, "rrrrrrrrrrrrr");
        assertEquals("Employee doesn't exist", message);
    }

    @Test
    void isConstraintExistTrue() {
        systemFacade.addConstraint("111111111", 0, "fffffff");
        String message = systemFacade.isConstraintExist("111111111", 1);
        assertEquals("true", message);
    }

    @Test
    void isConstraintExistFalse() {
        systemFacade.addConstraint("111111111", 0, "fffffff");
        String message = systemFacade.isConstraintExist("111111111", 7);
        assertEquals("false", message);
    }

    @Test
    void isConstraintExistNotExistEmployee() {
        systemFacade.addConstraint("111111111", 0, "fffffff");
        String message = systemFacade.isConstraintExist("111115555", 1);
        assertEquals("Employee doesn't exist", message);
    }

    @Test
    void removeConstraintSuccess() {
        systemFacade.addConstraint("111111111", 0, "fffffff");
        String message = systemFacade.removeConstraint("111111111", 1);
        assertEquals("Constraint was removed successfully", message);
    }

    @Test
    void removeConstraintNotExistEmployee() {
        systemFacade.addConstraint("111111111", 0, "fffffff");
        String message = systemFacade.removeConstraint("111144111", 1);
        assertEquals("Employee doesn't exist", message);
    }

    @Test
    void removeConstraintNotExistConstraint() {
        systemFacade.addConstraint("111111111", 0, "fffffff");
        String message = systemFacade.removeConstraint("111111111", 10);
        assertEquals("Constraint does not exist at employee", message);
    }

    @Test
    void upgradeDriverLicenseSuccess() {
        systemFacade.registerDriver("555555555", "Avi", "Naaman", "aviNaaman501", 200, 10, 30, "abcabc", "aaaaaa", LocalDateTime.now(), "0547861578", 100);
        String ans = systemFacade.upgradeDriverLicense("555555555", 200);
        assertEquals("Driver license changed successfully", ans);
    }

    @Test
    void upgradeDriverLicenseIllegalLicense() {
        systemFacade.registerDriver("555555555", "Avi", "Naaman", "aviNaaman501", 200, 10, 30, "abcabc", "aaaaaa", LocalDateTime.now(), "0548791234", 150);
        String ans = systemFacade.upgradeDriverLicense("555555555", 100);
        assertEquals("illegal license", ans);
    }

    @Test
    void addDriverQualificationSuccess() {
        systemFacade.registerDriver("555555555", "Avi", "Naaman", "aviNaaman501", 200, 10, 30, "abcabc", "aaaaaa", LocalDateTime.now(), "0546791234", 10000);
        String ans = systemFacade.addDriverQualification("555555555", "refrigeration");
        assertEquals("Driver qualification added successfully", ans);
    }

    @Test
    void addDriverQualificationQualificationIsExist() {
        systemFacade.registerDriver("555555555", "Avi", "Naaman", "aviNaaman501", 200, 10, 30, "abcabc", "aaaaaa", LocalDateTime.now(), "0548641256", 10000);
        systemFacade.addDriverQualification("555555555", "refrigeration");
        String ans = systemFacade.addDriverQualification("555555555", "refrigeration");
        assertEquals("qualification is already exist", ans);
    }

    @Test
    void removeDriverQualificationSuccess() {
        systemFacade.registerDriver("555555555", "Avi", "Naaman", "aviNaaman501", 200, 10, 30, "abcabc", "aaaaaa", LocalDateTime.now(), "0523217563", 10000);
        systemFacade.addDriverQualification("555555555", "refrigeration");
        String ans = systemFacade.removeDriverQualification("555555555", "refrigeration");
        assertEquals("Driver qualification removed successfully", ans);
    }

    @Test
    void removeDriverQualificationQualificationDoesNotExist() {
        systemFacade.registerDriver("555555555", "Avi", "Naaman", "aviNaaman501", 200, 10, 30, "abcabc", "aaaaaa", LocalDateTime.now(), "05432798145", 10000);
        String ans = systemFacade.removeDriverQualification("555555555", "refrigeration");
        assertEquals("qualification doesn't exist", ans);
    }

    @Test
    void hasDriverQualification() {
        systemFacade.registerDriver("555555555", "Avi", "Naaman", "aviNaaman501", 200, 10, 30, "abcabc", "aaaaaa", LocalDateTime.now(), "0531289689", 10000);
        systemFacade.addDriverQualification("555555555", "refrigeration");
        String ans = systemFacade.hasDriverQualification("555555555", "refrigeration");
        assertEquals("Driver has qualification", ans);
        systemFacade.removeDriverQualification("555555555", "refrigeration");
        ans = systemFacade.hasDriverQualification("555555555", "refrigeration");
        assertEquals("Driver does not have qualification", ans);

    }

    @Test
    void addDriverDeliveryDateSuccess() {
        systemFacade.registerDriver("555555555", "Avi", "Naaman", "aviNaaman501", 200, 10, 30, "abcabc", "aaaaaa", LocalDateTime.now(), "0523728124", 10000);
        String ans = systemFacade.addDriverDeliveryDate("555555555", LocalDate.of(2023, 5, 13));
        assertEquals("Driver delivery date added successfully", ans);
    }

    @Test
    void addDriverDeliveryDateDeliveryDateIsExist() {
        systemFacade.registerDriver("555555555", "Avi", "Naaman", "aviNaaman501", 200, 10, 30, "abcabc", "aaaaaa", LocalDateTime.now(), "0546821570", 10000);
        systemFacade.addDriverDeliveryDate("555555555", LocalDate.of(2023, 5, 13));
        String ans = systemFacade.addDriverDeliveryDate("555555555", LocalDate.of(2023, 5, 13));
        assertEquals("deliveryDate is already exist", ans);
    }

    @Test
    void removeDriverDeliveryDateSuccess() {
        systemFacade.registerDriver("555555555", "Avi", "Naaman", "aviNaaman501", 200, 10, 30, "abcabc", "aaaaaa", LocalDateTime.now(), "0522378980", 10000);
        systemFacade.addDriverDeliveryDate("555555555", LocalDate.of(2023, 5, 13));
        String ans = systemFacade.removeDriverDeliveryDate("555555555", LocalDate.of(2023, 5, 13));
        assertEquals("Driver delivery date removed successfully", ans);
    }

    @Test
    void removeDriverDeliveryDateDeliveryDateDoesNotExist() {
        systemFacade.registerDriver("555555555", "Avi", "Naaman", "aviNaaman501", 200, 10, 30, "abcabc", "aaaaaa", LocalDateTime.now(), "0533198123", 10000);
        String ans = systemFacade.removeDriverDeliveryDate("555555555", LocalDate.of(2023, 5, 13));
        assertEquals("deliveryDate doesn't exist", ans);
    }

    @Test
    void insertRoleSuccess() {
        String message = systemFacade.insertRole("111111111", "guard");
        assertEquals("Role inserted successfully", message);
    }

    @Test
    void insertRoleNotExistedRole() {
        String message = systemFacade.insertRole("111111111", "fthrsr");
        assertEquals("Role doesn't exist", message);
    }

    @Test
    void insertRoleDriver() {
        systemFacade.registerDriver("666666666", "rrr", "rrr", "aviNaaman501", 200, 10, 30, "abcabc", "aaaaaa", LocalDateTime.now(), "0534293128", 30000);
        String message = systemFacade.insertRole("666666666", "guard");
        assertEquals("Driver has one rule only", message);
    }

    @Test
    void removeRoleSuccess() {
        String message = systemFacade.removeRole("111111111", "cashier");
        assertEquals("Role removed successfully", message);
    }

    @Test
    void removeRoleNotExistedRole() {
        String message = systemFacade.removeRole("111111111", "gjkhjhm");
        assertEquals("Role doesn't exist", message);
    }
}