package PresentationLayer.CLI;

import ServiceLayer.SystemFacade;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Scanner;

public class ManagerHRCLI {
    public static void main(String[] args) {
        SystemFacade systemFacade = SystemFacade.getInstance();
        Scanner input = new Scanner(System.in);
        System.out.println("Enter ID number");
        String employeeIdActive = input.nextLine();
        System.out.println("Enter Password");
        String passwordActive = input.nextLine();
        String message = systemFacade.login(employeeIdActive, passwordActive);
        while (!message.contains("success")) {
            systemFacade.logout(employeeIdActive);
            System.out.println(message);
            System.out.println("Enter ID number");
            employeeIdActive = input.nextLine();
            System.out.println("Enter Password");
            passwordActive = input.nextLine();
            message = systemFacade.login(employeeIdActive, passwordActive);
        }
        message = systemFacade.isHRManager(employeeIdActive);
        if (message.equals("true")) showMenuOfHR(employeeIdActive, input, systemFacade);
        else {
            if (message.equals("false")){
                System.out.println("Must be Employee to enter");
                systemFacade.logout(employeeIdActive);
                System.out.println("Enter ID number");
                employeeIdActive = input.nextLine();
                System.out.println("Enter Password");
                passwordActive = input.nextLine();
                message = systemFacade.login(employeeIdActive, passwordActive);
            }
            else System.out.println("There was an error at this page. Please enter this page again");
        }
    }

    public static void run() {
        SystemFacade systemFacade = SystemFacade.getInstance();
        Scanner input = new Scanner(System.in);
        System.out.println("Enter ID number");
        String employeeIdActive = input.nextLine();
        System.out.println("Enter Password");
        String passwordActive = input.nextLine();
        String message = systemFacade.login(employeeIdActive, passwordActive);
        while (!message.contains("success")) {
            systemFacade.logout(employeeIdActive);
            System.out.println(message);
            System.out.println("Enter ID number");
            employeeIdActive = input.nextLine();
            System.out.println("Enter Password");
            passwordActive = input.nextLine();
            message = systemFacade.login(employeeIdActive, passwordActive);
        }
        message = systemFacade.isHRManager(employeeIdActive);
        if (message.equals("true")) showMenuOfHR(employeeIdActive, input, systemFacade);
        else {
            if (message.equals("false")){
                System.out.println("Must be Employee to enter");
                System.out.println("Enter ID number");
                employeeIdActive = input.nextLine();
                System.out.println("Enter Password");
                passwordActive = input.nextLine();
                message = systemFacade.login(employeeIdActive, passwordActive);
                while (!message.contains("success")) {
                    systemFacade.logout(employeeIdActive);
                    System.out.println(message);
                    System.out.println("Enter ID number");
                    employeeIdActive = input.nextLine();
                    System.out.println("Enter Password");
                    passwordActive = input.nextLine();
                    message = systemFacade.login(employeeIdActive, passwordActive);
                }
            }
            else System.out.println("There was an error at this page. Please enter this page again");
        }
    }

    public static void showMenuOfHR(String employeeIdActive, Scanner input, SystemFacade systemFacade) {
        Scanner input2 = new Scanner(System.in);
        Scanner input3 = new Scanner(System.in);
        Scanner input4 = new Scanner(System.in);
        Scanner input5 = new Scanner(System.in);
        Scanner input6 = new Scanner(System.in);
        Scanner input7 = new Scanner(System.in);
        Scanner input8 = new Scanner(System.in);
        Scanner input9 = new Scanner(System.in);
        Scanner input10 = new Scanner(System.in);
        Scanner input11 = new Scanner(System.in);
        String message;
        int option = 0;
        while (option != 33) {
            System.out.println("Choose an option:");
            System.out.println("1 - Register employee");
            System.out.println("2 - Return details for entering the site to employee");
            System.out.println("3 - Remove employee");
            System.out.println("4 - Add shift");
            System.out.println("5 - Remove shift");
            System.out.println("6 - Add branch");
            System.out.println("7 - Remove branch");
            System.out.println("8 - Add time of shift in branch");
            System.out.println("9 - Remove time of shift in branch");
            System.out.println("10 - Show all branches");
            System.out.println("11 - Show branch morning shift start hour and end hour");
            System.out.println("12 - Show branch evening shift start hour and end hour");
            System.out.println("13 - Change start hour of branch");
            System.out.println("14 - Change end hour of branch");
            System.out.println("15 - Change start hour of shift (for unusual cases)");
            System.out.println("16 - Choose branch employees for shift");
            System.out.println("17 - Show branch employees chosen for shift");
            System.out.println("18 - Schedule branch employee to role");
            System.out.println("19 - Schedule driver to shift");
            System.out.println("20 - Change schedule to employee");
            System.out.println("21 - Remove schedule");
            System.out.println("22 - Show details on shift");
            System.out.println("23 - Get schedules of shift");
            System.out.println("24 - Get payment of employee for this month");
            System.out.println("25 - Show number of branch employees needed by role on shift");
            System.out.println("26 - Show number of driver employees needed on shift");
            System.out.println("27 - Show product cancellations for shift");
            System.out.println("28 - Show details on employee");
            System.out.println("29 - Edit details on employee");
            System.out.println("30 - Edit branch");
            System.out.println("31 - Edit for shift");
            System.out.println("32 - Edit password");
            System.out.println("33 - Logout");
            option = input.nextInt();
            if (option == 1) {
                System.out.println("Choose type of employee:");
                System.out.println("1 - Branch employee");
                System.out.println("2 - Driver");
                int optionForType = input.nextInt();
                while (optionForType != 1 && optionForType != 2) {
                    System.out.println("Choose type of employee:");
                    System.out.println("1 - Branch employee");
                    System.out.println("2 - Driver");
                    optionForType = input.nextInt();
                }
                if (optionForType == 1) {
                    registerBranchEmployee(systemFacade);
                    System.out.println("Registered branch employee successfully");
                } else {
                    registerDriver(systemFacade);
                    System.out.println("Registered driver successfully");
                }
            }
            if (option == 2) {
                System.out.println("Choose employee:");
                System.out.println(systemFacade.showEmployees());
                String employeeId = input2.nextLine();
                while (!systemFacade.isEmployeeExists(employeeId).equals("true") || !isNumeric(employeeId)) {
                    System.out.println("Error at choosing employee");
                    System.out.println("Choose employee:");
                    System.out.println(systemFacade.showEmployees());
                    employeeId = input2.nextLine();
                }
                System.out.println(systemFacade.registerDetails(employeeId));
            }
            if (option == 3) {
                System.out.println("Choose employee:");
                System.out.println(systemFacade.showEmployees());
                String employeeId = input2.nextLine();
                while (!systemFacade.isEmployeeExists(employeeId).equals("true") || !isNumeric(employeeId)) {
                    System.out.println("Error at choosing employee");
                    System.out.println("Choose employee:");
                    System.out.println(systemFacade.showEmployees());
                    employeeId = input2.nextLine();
                }
                System.out.println(systemFacade.removeEmployee(employeeId));
            }
            if (option == 4) {
                System.out.println("Choose type of shift:");
                System.out.println("1 - Branch Shift");
                System.out.println("2 - Driver Shift");
                int optionForType = input.nextInt();
                while (optionForType != 1 && optionForType != 2) {
                    System.out.println("Choose type of shift:");
                    System.out.println("1 - Branch Shift");
                    System.out.println("2 - Driver Shift");
                    optionForType = input2.nextInt();
                }
                if (optionForType == 1) {
                    addBranchShift(systemFacade);

                } else {
                    System.out.println("Enter year of shift");
                    int year = input.nextInt();
                    System.out.println("Enter month of shift");
                    int month = input.nextInt();
                    System.out.println("Enter day of shift");
                    int day = input.nextInt();
                    message = systemFacade.isValidDate(year, month, day);
                    while (!message.contains("success")) {
                        System.out.println(message);
                        System.out.println("Enter year of shift");
                        year = input.nextInt();
                        System.out.println("Enter month of shift");
                        month = input.nextInt();
                        System.out.println("Enter day of shift");
                        day = input.nextInt();
                        message = systemFacade.isValidDate(year, month, day);
                    }
                    LocalDateTime localDateTime = LocalDateTime.of(year, month, day, 0, 0);
                    System.out.println("Enter count of drivers");
                    int countDrivers = input.nextInt();
                    System.out.println(systemFacade.addDriverShift(localDateTime, countDrivers));
                }
            }
            if (option == 5) {
                System.out.println("Choose shift:");
                System.out.println(systemFacade.showFutureShifts());
                int shiftId = input.nextInt();
                while (systemFacade.isFutureShifts(shiftId).equals("false") || shiftId < 1) {
                    System.out.println("Choose shift:");
                    System.out.println(systemFacade.showFutureShifts());
                    shiftId = input.nextInt();
                }
                System.out.println(systemFacade.removeShift(shiftId));
            }
            if (option == 6) {
                addBranch(systemFacade);
            }
            if (option == 7) {
                System.out.println("Choose branch:");
                System.out.println(systemFacade.showAllBranches());
                String branchName = input3.next();
                message = systemFacade.isBranchExists(branchName);
                while (!message.equals("true")) {
                    System.out.println("You chose wrong branch");
                    System.out.println("Choose branch:");
                    System.out.println(systemFacade.showAllBranches());
                    branchName = input3.next();
                    message = systemFacade.isBranchExists(branchName);
                }
                System.out.println(systemFacade.removeBranch(branchName));
            }
            if (option == 8) {
                System.out.println("Choose branch:");
                System.out.println(systemFacade.showAllBranches());
                String branchName = input3.nextLine();
                while (!systemFacade.isBranchExists(branchName).equals("true")) {
                    System.out.println("You chose wrong branch");
                    System.out.println("Choose branch:");
                    System.out.println(systemFacade.showAllBranches());
                    branchName = input3.nextLine();
                }
                System.out.println("Enter day of the week (must be with capital letters):");
                String day = input3.nextLine();
                System.out.println("Enter type of shift: M for morning and E for evening:");
                String type = input3.nextLine();
                message = systemFacade.insertTimeOfShift(branchName, day, type);
                while (!message.contains("success")) {
                    System.out.println(message);
                    System.out.println("Enter day of the week (must be with capital letters):");
                    day = input3.nextLine();
                    System.out.println("Enter type of shift: M for morning and E for evening:");
                    type = input3.nextLine();
                    message = systemFacade.insertTimeOfShift(branchName, day, type);
                }
                System.out.println("Time of shift inserted successfully");
            }
            if (option == 9) {
                System.out.println("Choose branch:");
                System.out.println(systemFacade.showAllBranches());
                String branchName = input3.nextLine();
                while (!systemFacade.isBranchExists(branchName).equals("true")) {
                    System.out.println("Choose branch:");
                    System.out.println(systemFacade.showAllBranches());
                    branchName = input3.nextLine();
                }
                System.out.println("Enter day of the week (must be with capital letters):");
                String day = input3.next();
                System.out.println("Enter type of shift: M for morning and E for evening:");
                String type = input3.next();
                message = systemFacade.removeTimeOfShift(branchName, day, type);
                while (!message.contains("success")) {
                    System.out.println(message);
                    System.out.println("Enter day of the week (must be with capital letters):");
                    day = input3.next();
                    System.out.println("Enter type of shift: M for morning and E for evening:");
                    type = input3.next();
                    message = systemFacade.removeTimeOfShift(branchName, day, type);
                }
                System.out.println("Time of shift removed successfully");
            }
            if (option == 10) {
                System.out.println(systemFacade.showNamesOfBranches());
            }
            if (option == 11) {
                System.out.println("Choose branch:");
                System.out.println(systemFacade.showAllBranches());
                String branchName = input4.nextLine();
                while (!systemFacade.isBranchExists(branchName).equals("true")) {
                    System.out.println("Choose branch:");
                    System.out.println(systemFacade.showAllBranches());
                    branchName = input4.nextLine();
                }
                System.out.println(systemFacade.showBranchMorningShiftHours(branchName));
            }
            if (option == 12) {
                System.out.println("Choose branch:");
                System.out.println(systemFacade.showAllBranches());
                String branchName = input4.nextLine();
                while (!systemFacade.isBranchExists(branchName).equals("true")) {
                    System.out.println("Choose branch:");
                    System.out.println(systemFacade.showAllBranches());
                    branchName = input4.nextLine();
                }
                System.out.println(systemFacade.showBranchEveningShiftHours(branchName));
            }
            if (option == 13) {
                System.out.println("Choose branch:");
                System.out.println(systemFacade.showAllBranches());
                String branchName = input10.nextLine();
                while (!systemFacade.isBranchExists(branchName).equals("true")) {
                    System.out.println("You chose wrong shift");
                    System.out.println("Choose branch:");
                    System.out.println(systemFacade.showAllBranches());
                    branchName = input10.nextLine();
                }
                System.out.println("Enter type of shift: M for morning and E for evening:");
                String type_ = input4.next();
                String type = type_.toUpperCase();
                while (!type.equals("M") && !type.equals("E")) {
                    System.out.println("Wrong shift type");
                    System.out.println("Enter type of shift: M for morning and E for evening:");
                    type_ = input4.next();
                    type = type_.toUpperCase();
                }
                System.out.println("Enter new start hour");
                int startHour = input4.nextInt();
                System.out.println("Enter new start minute");
                int startMinute = input4.nextInt();
                message = systemFacade.isValidTime(startHour, startMinute);
                while (!message.contains("success")) {
                    System.out.println(message);
                    System.out.println("Enter morning Shift start hour:");
                    startHour = input4.nextInt();
                    System.out.println("Enter morning Shift start minute:");
                    startMinute = input4.nextInt();
                    message = systemFacade.isValidTime(startHour, startMinute);
                }
                LocalTime newTime = LocalTime.of(startHour, startMinute);
                System.out.println(systemFacade.changeStartHourOfBranch(branchName, type, newTime));
            }
            if (option == 14) {
                System.out.println("Choose branch:");
                System.out.println(systemFacade.showAllBranches());
                String branchName = input4.nextLine();
                while (!systemFacade.isBranchExists(branchName).equals("true")) {
                    System.out.println("Choose branch:");
                    System.out.println(systemFacade.showAllBranches());
                    branchName = input4.nextLine();
                }
                System.out.println("Enter type of shift: M for morning and E for evening:");
                String type_ = input4.nextLine();
                String type = type_.toUpperCase();
                while (!type.equals("M") && !type.equals("E")) {
                    System.out.println("Enter type of shift: M for morning and E for evening:");
                    type_ = input4.nextLine();
                    type = type_.toUpperCase();
                }
                System.out.println("Enter new start hour");
                int endHour = input4.nextInt();
                System.out.println("Enter new start minute");
                int endMinute = input4.nextInt();
                message = systemFacade.isValidTime(endHour, endMinute);
                while (!message.contains("success")) {
                    System.out.println(message);
                    System.out.println("Enter morning Shift start hour:");
                    endHour = input4.nextInt();
                    System.out.println("Enter morning Shift start minute:");
                    endMinute = input4.nextInt();
                    message = systemFacade.isValidTime(endHour, endMinute);
                }
                LocalTime newTime = LocalTime.of(endHour, endMinute);
                System.out.println(systemFacade.changeEndHourOfBranch(branchName, type, newTime));
            }
            if (option == 15) {
                System.out.println("Choose shift:");
                System.out.println(systemFacade.showFutureBranchShifts());
                int shiftId = input5.nextInt();
                while (!systemFacade.isFutureShifts(shiftId).equals("true") || shiftId < 1) {
                    System.out.println("You chose wrong shift");
                    System.out.println("Choose shift:");
                    System.out.println(systemFacade.showFutureBranchShifts());
                    shiftId = input5.nextInt();
                }
                System.out.println("Enter new hour:");
                int newHour = input5.nextInt();
                System.out.println("Enter new minute:");
                int newMin = input5.nextInt();
                message = systemFacade.isValidTime(newHour, newMin);
                while (!message.contains("success")) {
                    System.out.println(message);
                    System.out.println("Enter new hour:");
                    newHour = input5.nextInt();
                    System.out.println("Enter new minute:");
                    newMin = input5.nextInt();
                    message = systemFacade.isValidTime(newHour, newMin);
                }
                LocalTime newTime = LocalTime.of(newHour, newMin);
                System.out.println(systemFacade.changeStartHourOfShift(shiftId, newTime));
            }
            if (option == 16) {
                System.out.println(systemFacade.alertAboutShifts());
                System.out.println("Choose shift:");
                System.out.println(systemFacade.showBranchShifts());
                int shiftId = input8.nextInt();
                message = systemFacade.isFutureShifts(shiftId);
                while (!message.equals("true") || shiftId < 1) {
                    System.out.println(message);
                    System.out.println("Error at choosing shift");
                    System.out.println("Choose shift:");
                    System.out.println(systemFacade.showBranchShifts());
                    shiftId = input8.nextInt();
                    message = systemFacade.isFutureShifts(shiftId);
                }
                System.out.println("Choose employee:");
                System.out.println(systemFacade.availableEmployeesNamesBE(shiftId));
                String employeeId = input8.next();
                message = systemFacade.isEmployeeAvailable(shiftId, employeeId);
                while (!message.equals("true")) {
                    System.out.println("Error at choosing employee");
                    System.out.println("Choose employee:");
                    System.out.println(systemFacade.availableEmployeesNamesBE(shiftId));
                    employeeId = input8.next();
                    message = systemFacade.isEmployeeAvailable(shiftId, employeeId);
                }
                System.out.println(systemFacade.addEmployeeToSchedule(employeeId, shiftId));
            }
            if (option == 17) {
                System.out.println("Choose shift:");
                System.out.println(systemFacade.showBranchShifts());
                int shiftId = input7.nextInt();
                while (systemFacade.isFutureShifts(shiftId).equals("false") || shiftId < 1) {
                    System.out.println("Error at choosing shift");
                    System.out.println("Choose shift:");
                    System.out.println(systemFacade.showBranchShifts());
                    shiftId = input7.nextInt();
                }
                System.out.println(systemFacade.showWhoWasChosen(shiftId));
            }
            if (option == 18) {
                System.out.println(systemFacade.alertAboutShifts());
                System.out.println("Choose shift:");
                System.out.println(systemFacade.showBranchShifts());
                int shiftId = input7.nextInt();
                while (systemFacade.isFutureShifts(shiftId).equals("false") || shiftId < 1) {
                    System.out.println("Error at choosing shift");
                    System.out.println("Choose shift:");
                    System.out.println(systemFacade.showBranchShifts());
                    shiftId = input7.nextInt();
                }
                System.out.println("Choose employee:");
                System.out.println(systemFacade.showWhoWasChosen(shiftId));
                String employeeId = input7.next();
                while (systemFacade.isEmployeeExists(employeeId).equals("false")) {
                    System.out.println("Error at choosing employee");
                    System.out.println("Choose employee:");
                    System.out.println(systemFacade.showWhoWasChosen(shiftId));
                    employeeId = input7.next();
                }
                System.out.println("Enter role:");
                String role = input10.nextLine();
                while (systemFacade.isRoleExist(role).equals("false")) {
                    System.out.println("Error at entering role");
                    System.out.println("Enter role:");
                    role = input10.nextLine();
                }
                System.out.println(systemFacade.scheduleEmployeeToRole(employeeId, shiftId, role));
            }
            if (option == 19) {
                System.out.println("Choose shift:");
                System.out.println(systemFacade.showFutureDriversShifts());
                int shiftId = input7.nextInt();
                while (systemFacade.isFutureShifts(shiftId).equals("false") || shiftId < 1) {
                    System.out.println("Error at choosing shift");
                    System.out.println("Choose shift:");
                    System.out.println(systemFacade.showFutureDriversShifts());
                    shiftId = input7.nextInt();
                }
                System.out.println("Choose employee:");
                System.out.println(systemFacade.availableEmployeesNamesD(shiftId));
                String employeeId = input7.next();
                while (systemFacade.isEmployeeExists(employeeId).equals("false")) {
                    System.out.println("Error at choosing employee");
                    System.out.println("Choose employee:");
                    System.out.println(systemFacade.availableEmployeesNamesD(shiftId));
                    employeeId = input7.next();
                }
                System.out.println(systemFacade.addDriverSchedule(employeeId, shiftId));
            }
            if (option == 20) {
                System.out.println("Choose what to change: \n1-Change branch shift\n2-Change driver shift");
                int optionToChange = input8.nextInt();
                if (optionToChange == 1) {
                    System.out.println("Choose shift:");
                    System.out.println(systemFacade.showBranchShifts());
                    int shiftId = input7.nextInt();
                    while (systemFacade.isFutureShifts(shiftId).equals("false") || shiftId < 1) {
                        System.out.println("Error at choosing shift");
                        System.out.println("Choose shift:");
                        System.out.println(systemFacade.showBranchShifts());
                        shiftId = input7.nextInt();
                    }
                    System.out.println("Choose employee:");
                    System.out.println(systemFacade.showWhoWasChosen(shiftId));
                    String employeeId = input7.next();
                    while (systemFacade.isEmployeeExists(employeeId).equals("false")) {
                        System.out.println("Error at choosing employee");
                        System.out.println("Choose employee:");
                        System.out.println(systemFacade.showWhoWasChosen(shiftId));
                        employeeId = input7.next();
                    }
                    System.out.println("Enter role:");
                    String role = input10.nextLine();
                    while (systemFacade.isRoleExist(role).equals("false")) {
                        System.out.println("Error at entering role");
                        System.out.println("Enter role:");
                        role = input10.nextLine();
                    }
                    System.out.println(systemFacade.changeScheduleByRole(employeeId, shiftId, role));
                } else if (optionToChange == 2) {
                    System.out.println("Choose employee:");
                    System.out.println(systemFacade.showAllDrivers());
                    String employeeId = input7.next();
                    while (systemFacade.isEmployeeExists(employeeId).equals("false")) {
                        System.out.println("Error at choosing employee");
                        System.out.println("Choose employee:");
                        System.out.println(systemFacade.showAllDrivers());
                        employeeId = input7.next();
                    }
                    System.out.println("Choose shift:");
                    System.out.println(systemFacade.shiftsDriverScheduledTo(employeeId));
                    int shiftIdOld = input7.nextInt();
                    while (systemFacade.isFutureShifts(shiftIdOld).equals("false") || shiftIdOld < 1) {
                        System.out.println("Error at choosing shift");
                        System.out.println("Choose shift:");
                        System.out.println(systemFacade.shiftsDriverScheduledTo(employeeId));
                        shiftIdOld = input7.nextInt();
                    }
                    System.out.println("Choose shift:");
                    System.out.println(systemFacade.shiftsDriverNotScheduledTo(employeeId));
                    int shiftIdNew = input7.nextInt();
                    while (systemFacade.isFutureShifts(shiftIdNew).equals("false") || shiftIdNew < 1) {
                        System.out.println("Error at choosing shift");
                        System.out.println("Choose shift:");
                        System.out.println(systemFacade.shiftsDriverNotScheduledTo(employeeId));
                        shiftIdNew = input7.nextInt();
                    }
                    System.out.println(systemFacade.changeDriverSchedule(employeeId, shiftIdOld, shiftIdNew));
                }
            }
            if (option == 21) {
                System.out.println("Choose employee:");
                System.out.println(systemFacade.showEmployees());
                String employeeId = input6.next();
                while (!systemFacade.isEmployeeExists(employeeId).equals("true")) {
                    System.out.println("Error at choosing employee");
                    System.out.println("Choose employee:");
                    System.out.println(systemFacade.showEmployees());
                    employeeId = input6.next();
                }
                System.out.println("Choose shift:");
                System.out.println(systemFacade.showFutureShifts());
                int shiftId = input5.nextInt();
                while (!systemFacade.isFutureShifts(shiftId).equals("true") || shiftId < 1) {
                    System.out.println("Error at choosing shift");
                    System.out.println("Choose shift:");
                    System.out.println(systemFacade.showFutureShifts());
                    shiftId = input5.nextInt();
                }
                System.out.println(systemFacade.removeSchedule(employeeId, shiftId));
            }
            if (option == 22) {
                System.out.println("Choose shift:");
                System.out.println(systemFacade.showFutureShifts());
                int shiftId = input5.nextInt();
                while (systemFacade.isFutureShifts(shiftId).equals("false") || shiftId < 1) {
                    System.out.println("Error at choosing shift");
                    System.out.println("Choose shift:");
                    System.out.println(systemFacade.showFutureShifts());
                    shiftId = input5.nextInt();
                }
                System.out.println(systemFacade.showShift(shiftId));
            }
            if (option == 23) {
                System.out.println("Choose shift:");
                System.out.println(systemFacade.showBranchShifts());
                System.out.println(systemFacade.showDriversShifts());
                int shiftId = input6.nextInt();
                while (systemFacade.isFutureShifts(shiftId).equals("false") || shiftId < 1) {
                    System.out.println("Error at choosing shift");
                    System.out.println("Choose shift:");
                    System.out.println(systemFacade.showBranchShifts());
                    System.out.println(systemFacade.showDriversShifts());
                    shiftId = input6.nextInt();
                }
                System.out.println(systemFacade.getSchedules(shiftId));
            }
            if (option == 24) {
                System.out.println("Choose employee:");
                System.out.println(systemFacade.showEmployees());
                String employeeId = input6.next();
                while (systemFacade.isEmployeeExists(employeeId).equals("false") || !isNumeric(employeeId)) {
                    System.out.println("Error at choosing employee");
                    System.out.println("Choose employee:");
                    employeeId = input6.next();
                }
                System.out.println("Enter bonus if exist (if not,enter 0)");
                int bonus = input6.nextInt();
                while (bonus < 0) {
                    System.out.println("bonus cannot be less than zero");
                    System.out.println("Enter bonus if exist (if not,enter 0)");
                    bonus = input6.nextInt();
                }
                System.out.println("Enter number of hours of shift if there is a change with regular hours (if not,enter 0)");
                int numberOfHours = input6.nextInt();
                while (numberOfHours < 0) {
                    System.out.println("number of hours cannot be less than zero");
                    System.out.println("Enter number of hours of shift if there is a change with regular hours (if not,enter 0)");
                    numberOfHours = input6.nextInt();
                }
                System.out.println(systemFacade.getPayment(employeeId, bonus, numberOfHours));
            }
            if (option == 25) {
                System.out.println("Choose shift:");
                System.out.println(systemFacade.showBranchShifts());
                int shiftId = input6.nextInt();
                while (systemFacade.isFutureShifts(shiftId).equals("false") || shiftId < 1) {
                    System.out.println("Error at choosing shift");
                    System.out.println("Choose shift:");
                    System.out.println(systemFacade.showBranchShifts());
                    shiftId = input6.nextInt();
                }
                System.out.println("Enter role:");
                String role = input10.nextLine();
                while (systemFacade.isRoleExist(role).equals("false")) {
                    System.out.println("Error at entering role");
                    System.out.println("Enter role:");
                    role = input10.nextLine();
                }
                System.out.println(systemFacade.showRoleCounts(shiftId, role));
            }
            if (option == 26) {
                System.out.println("Choose shift:");
                System.out.println(systemFacade.showDriversShifts());
                int shiftId = input6.nextInt();
                while (systemFacade.isFutureShifts(shiftId).equals("false") || shiftId < 1) {
                    System.out.println("Error at choosing shift");
                    System.out.println("Choose shift:");
                    System.out.println(systemFacade.showDriversShifts());
                    shiftId = input6.nextInt();
                }
                System.out.println(systemFacade.getDriverCounts(shiftId));
            }
            if (option == 27) {
                System.out.println("Choose shift:");
                System.out.println(systemFacade.showBranchShifts());
                int shiftId = input6.nextInt();
                while (systemFacade.isFutureShifts(shiftId).equals("false") || shiftId < 1) {
                    System.out.println("Error at choosing shift");
                    System.out.println("Choose shift:");
                    System.out.println(systemFacade.showBranchShifts());
                    shiftId = input6.nextInt();
                }
                System.out.println(systemFacade.showProductCancellation(shiftId));
            }
            if (option == 28) {
                System.out.println("Choose employee:");
                System.out.println(systemFacade.showEmployees());
                String employeeId = input6.next();
                while (systemFacade.isEmployeeExists(employeeId).equals("false") || !isNumeric(employeeId)) {
                    System.out.println("Error at choosing employee");
                    System.out.println("Choose employee:");
                    System.out.println(systemFacade.showEmployees());
                    employeeId = input6.next();
                }
                System.out.println(systemFacade.showEmployee(employeeId));
            }
            if (option == 29) {
                System.out.println("Choose employee:");
                System.out.println(systemFacade.showEmployees());
                String employeeId = input11.next();
                while (systemFacade.isEmployeeExists(employeeId).equals("false") || !isNumeric(employeeId)) {
                    System.out.println("Error at choosing employee");
                    System.out.println("Choose employee:");
                    System.out.println(systemFacade.showEmployees());
                    employeeId = input11.next();
                }
                System.out.println("Choose detail:");
                System.out.println("1 - First Name");
                System.out.println("2 - Last Name");
                System.out.println("3 - Bank account details");
                System.out.println("4 - Salary");
                System.out.println("5 - Terms of employment");
                System.out.println("6 - Status of employee");
                System.out.println("7 - HR status to employee");
                System.out.println("8 - Permissions - For branch employees only");
                System.out.println("9 - Roles - For branch employees only");
                System.out.println("10 - Licences - For drivers only");
                System.out.println("11 - Qualifications - For drivers only");
                System.out.println("12 - PresentationLayer.GUI.Delivery dates - For drivers only");
                int optionToEdit = input7.nextInt();
                if (optionToEdit == 1) {
                    System.out.println("Enter first name to edit to:");
                    String _firstName = input10.nextLine();
                    message = systemFacade.setFirstName(employeeId, _firstName);
                    while (!message.equals("success")) {
                        System.out.println(message);
                        System.out.println("Enter first name to edit to:");
                        _firstName = input10.nextLine();
                        message = systemFacade.setFirstName(employeeId, _firstName);
                    }
                    System.out.println("Employee's first name was edited successfully");
                }
                if (optionToEdit == 2) {
                    System.out.println("Enter last name to edit to:");
                    String _lastName = input10.nextLine();
                    message = systemFacade.setLastName(employeeId, _lastName);
                    while (!message.equals("success")) {
                        System.out.println(message);
                        System.out.println("Enter last name to edit to:");
                        _lastName = input10.nextLine();
                        message = systemFacade.setLastName(employeeId, _lastName);
                    }
                    System.out.println("Employee's last name was edited successfully");
                }
                if (optionToEdit == 3) {
                    System.out.println("Choose what to edit:");
                    System.out.println("1- Bank Account Number");
                    System.out.println("2- Branch Bank Number");
                    int optionToEditBank = input7.nextInt();
                    if (optionToEditBank == 1) {
                        System.out.println("Enter bank account number to edit to:");
                        int _bankAccountNumber = input10.nextInt();
                        message = systemFacade.setAccountNumber(employeeId, _bankAccountNumber);
                        while (!message.equals("success")) {
                            System.out.println(message);
                            System.out.println("Enter bank account number to edit to:");
                            _bankAccountNumber = input10.nextInt();
                            message = systemFacade.setAccountNumber(employeeId, _bankAccountNumber);
                        }
                        System.out.println("Employee's bank account number  was edited successfully");
                    }
                    if (optionToEditBank == 2) {
                        System.out.println("Enter branch bank account number to edit to:");
                        int _branchBankAccountNumber = input10.nextInt();
                        message = systemFacade.setBranchBankNumber(employeeId, _branchBankAccountNumber);
                        while (!message.equals("success")) {
                            System.out.println(message);
                            System.out.println("Enter branch bank account number to edit to:");
                            _branchBankAccountNumber = input10.nextInt();
                            message = systemFacade.setBranchBankNumber(employeeId, _branchBankAccountNumber);
                        }
                        System.out.println("Employee's bank branch account number was edited successfully");
                    }
                    while (optionToEditBank != 1 && optionToEditBank != 2) {
                        System.out.println("You chose wrong number");
                        System.out.println("Choose what to edit:");
                        System.out.println("1- Bank Account Number");
                        System.out.println("2- Branch Bank Number");
                        optionToEditBank = input7.nextInt();
                    }
                }
                if (optionToEdit == 4) {
                    System.out.println("Enter salary to edit to:");
                    int _salary = input10.nextInt();
                    message = systemFacade.setSalary(employeeId, _salary);
                    while (!message.equals("success")) {
                        System.out.println(message);
                        System.out.println("Enter salary to edit to:");
                        _salary = input10.nextInt();
                        message = systemFacade.setSalary(employeeId, _salary);
                    }
                    System.out.println("Employee's salary was edited successfully");
                }
                if (optionToEdit == 5) {
                    System.out.println("Enter terms of employment to edit to:");
                    String _termsOfEmployment = input10.nextLine();
                    message = systemFacade.setTermsOfEmployment(employeeId, _termsOfEmployment);
                    while (!message.equals("success")) {
                        System.out.println(message);
                        System.out.println("Enter terms of employment to edit to:");
                        _termsOfEmployment = input10.nextLine();
                        message = systemFacade.setTermsOfEmployment(employeeId, _termsOfEmployment);
                    }
                    System.out.println("Employee's terms of employment was edited successfully");
                }
                if (optionToEdit == 6) {
                    System.out.println("Enter status to edit to:");
                    String _statusOfEmployee = input4.nextLine();
                    message = systemFacade.setStatusOfEmployee(employeeId, _statusOfEmployee);
                    while (!message.equals("success")) {
                        System.out.println(message);
                        System.out.println("Enter status to edit to:");
                        _statusOfEmployee = input4.nextLine();
                        message = systemFacade.setStatusOfEmployee(employeeId, _statusOfEmployee);
                    }
                    System.out.println("Employee's status was edited successfully");
                }
                if (optionToEdit == 7) {
                    System.out.println(systemFacade.setHRManager(employeeId));
                }
                if (optionToEdit == 8) {
                    System.out.println("Choose what to give permission at:");
                    System.out.println("1- Cancellation");
                    System.out.println("2- Managements");
                    int optionToEditPermission = input10.nextInt();
                    if (optionToEditPermission == 1) {
                        System.out.println(systemFacade.setCancellation(employeeId));
                    }
                    if (optionToEditPermission == 2) {
                        System.out.println(systemFacade.setManagement(employeeId));
                    }
                    while (optionToEditPermission != 1 && optionToEditPermission != 2) {
                        System.out.println("You chose wrong number");
                        System.out.println("Choose what to give permission at:");
                        System.out.println("1- Cancellation");
                        System.out.println("2- Managements");
                        optionToEditPermission = input10.nextInt();
                    }
                }
                if (optionToEdit == 9) {
                    System.out.println("Choose what to do:");
                    System.out.println("1- Add role");
                    System.out.println("2- Remove role");
                    int optionToEditPermission = input10.nextInt();
                    if (optionToEditPermission == 1) {
                        System.out.println("Enter role:");
                        String role = input9.nextLine();
                        while (systemFacade.isRoleExist(role).equals("false")) {
                            System.out.println("Error at entering role");
                            System.out.println("Enter role:");
                            role = input9.nextLine();
                        }
                        System.out.println(systemFacade.insertRole(employeeId, role));
                    }
                    if (optionToEditPermission == 2) {
                        System.out.println("Enter role:");
                        String role = input10.nextLine();
                        while (systemFacade.isRoleExist(role).equals("false")) {
                            System.out.println("Error at entering role");
                            System.out.println("Enter role:");
                            role = input10.nextLine();
                        }
                        System.out.println(systemFacade.removeRole(employeeId, role));
                    }
                    while (optionToEditPermission != 1 && optionToEditPermission != 2) {
                        System.out.println("You chose wrong number");
                        System.out.println("Choose what to do:");
                        System.out.println("1- Add role");
                        System.out.println("2- Remove role");
                        optionToEditPermission = input10.nextInt();
                    }
                }
                if (optionToEdit == 10) {
                    while (true) {
                        System.out.println("Enter licences:");
                        int licences = input10.nextInt();
                        try {
                            System.out.println((systemFacade.upgradeDriverLicense(employeeId, licences)));
                        } catch (NumberFormatException e) {
                            System.out.println(e.getMessage());
                            continue;
                        }
                        break;
                    }
                }
                if (optionToEdit == 11) {
                    System.out.println("Choose what to do:");
                    System.out.println("1- Add qualification");
                    System.out.println("2- Remove qualification");
                    int optionToEditQualification = input10.nextInt();
                    if (optionToEditQualification == 1) {
                        while (true) {
                            System.out.println("Enter qualification");
                            String qualification = input10.nextLine();
                            try {
                                System.out.println((systemFacade.addDriverQualification(employeeId, qualification)));
                            } catch (NumberFormatException e) {
                                System.out.println(e.getMessage());
                                continue;
                            }
                            break;
                        }
                    }
                    if (optionToEditQualification == 2) {
                        while (true) {
                            System.out.println("Enter qualification");
                            String qualification = input10.nextLine();
                            try {
                                System.out.println((systemFacade.removeDriverQualification(employeeId, qualification)));
                            } catch (NumberFormatException e) {
                                System.out.println(e.getMessage());
                                continue;
                            }
                            break;
                        }
                    }
                    while (optionToEditQualification != 1 && optionToEditQualification != 2) {
                        System.out.println("You chose wrong number");
                        System.out.println("Choose what to do:");
                        System.out.println("1- Add qualification");
                        System.out.println("2- Remove qualification");
                        optionToEditQualification = input10.nextInt();
                    }
                }
                if (optionToEdit == 12) {
                    System.out.println("Choose what to do:");
                    System.out.println("1- Add delivery date");
                    System.out.println("2- Remove delivery date");
                    int optionToEditQualification = input10.nextInt();
                    if (optionToEditQualification == 1) {
                        while (true) {
                            System.out.println("Enter year of shift");
                            int year = input10.nextInt();
                            System.out.println("Enter month of shift");
                            int month = input10.nextInt();
                            System.out.println("Enter day of shift");
                            int day = input10.nextInt();
                            systemFacade.isValidDate(year, month, day);
                            LocalDate localDate = LocalDate.of(year, month, day);
                            try {
                                System.out.println((systemFacade.addDriverDeliveryDate(employeeId, localDate)));
                            } catch (NumberFormatException e) {
                                System.out.println(e.getMessage());
                                continue;
                            }
                            break;
                        }
                    }
                    if (optionToEditQualification == 2) {
                        while (true) {
                            System.out.println("Enter year of shift");
                            int year = input10.nextInt();
                            System.out.println("Enter month of shift");
                            int month = input10.nextInt();
                            System.out.println("Enter day of shift");
                            int day = input10.nextInt();
                            systemFacade.isValidDate(year, month, day);
                            LocalDate localDate = LocalDate.of(year, month, day);
                            try {
                                System.out.println((systemFacade.removeDriverDeliveryDate(employeeId, localDate)));
                            } catch (NumberFormatException e) {
                                System.out.println(e.getMessage());
                                continue;
                            }
                            break;
                        }
                    }
                    while (optionToEditQualification != 1 && optionToEditQualification != 2) {
                        System.out.println("You chose wrong number");
                        System.out.println("Choose what to do:");
                        System.out.println("1- Add qualification");
                        System.out.println("2- Remove qualification");
                        optionToEditQualification = input10.nextInt();
                    }
                }
            }
            if (option == 30) {
                System.out.println("Choose branch:");
                System.out.println(systemFacade.showAllBranches());
                String address = input10.nextLine();
                message = systemFacade.isBranchExists(address);
                while (!message.equals("true")) {
                    System.out.println(message);
                    System.out.println("Choose branch:");
                    System.out.println(systemFacade.showAllBranches());
                    address = input10.nextLine();
                }
                System.out.println("Choose what to edit:");
                System.out.println("1- Phone number");
                System.out.println("2- Contact number");
                int optionToEdit = input10.nextInt();
                if (optionToEdit == 1) {
                    while (true) {
                        System.out.println("Enter phone number to edit to:");
                        String phoneNumber = input10.nextLine();
                        try {
                            System.out.println((systemFacade.updatePhoneNumber(address, phoneNumber)));
                        } catch (NumberFormatException e) {
                            System.out.println(e.getMessage());
                            continue;
                        }
                        break;
                    }
                } else if (optionToEdit == 2) {
                    while (true) {
                        System.out.println("Enter contact number to edit to:");
                        String contactNumber = input10.nextLine();
                        try {
                            System.out.println((systemFacade.updateContactName(address, contactNumber)));
                        } catch (NumberFormatException e) {
                            System.out.println(e.getMessage());
                            continue;
                        }
                        break;
                    }
                }
                while (optionToEdit != 1 && optionToEdit != 2) {
                    System.out.println("You chose wrong number");
                    System.out.println("Choose what to edit:");
                    System.out.println("1- Phone number");
                    System.out.println("2- Contact number");
                    optionToEdit = input10.nextInt();
                }
            }
            if (option == 31) {
                System.out.println("Choose shift:");
                System.out.println(systemFacade.showBranchShifts());
                System.out.println(systemFacade.showDriversShifts());
                int shiftId = input6.nextInt();
                while (systemFacade.isFutureShifts(shiftId).equals("false") || shiftId < 1) {
                    System.out.println("Error at choosing shift");
                    System.out.println("Choose shift:");
                    System.out.println(systemFacade.showBranchShifts());
                    System.out.println(systemFacade.showDriversShifts());
                    shiftId = input6.nextInt();
                }
                System.out.println("Choose what to edit:");
                System.out.println("1- date of shift");
                System.out.println("2- branch");
                int optionToEditShift = input10.nextInt();
                if (optionToEditShift == 1) {
                    System.out.println("Enter year of shift");
                    int year = input10.nextInt();
                    System.out.println("Enter month of shift");
                    int month = input10.nextInt();
                    System.out.println("Enter day of shift");
                    int day = input10.nextInt();
                    message = systemFacade.isValidDate(year, month, day);
                    while (!message.contains("success")) {
                        System.out.println(message);
                        System.out.println("Enter year of shift");
                        year = input10.nextInt();
                        System.out.println("Enter month of shift");
                        month = input10.nextInt();
                        System.out.println("Enter day of shift");
                        day = input10.nextInt();
                        message = systemFacade.isValidDate(year, month, day);
                    }
                    LocalDateTime date = LocalDateTime.of(year, month, day, 1, 1);
                    System.out.println(systemFacade.setDate(shiftId, date));
                }
                if (optionToEditShift == 2) {
                    System.out.println("Choose branch:");
                    System.out.println(systemFacade.showAllBranches());
                    String branchName = input9.nextLine();
                    message = systemFacade.isBranchExists(branchName);
                    while (!message.equals("true")) {
                        System.out.println("You chose the wrong branch");
                        System.out.println("Choose branch:");
                        System.out.println(systemFacade.showAllBranches());
                        branchName = input9.nextLine();
                        message = systemFacade.isBranchExists(branchName);
                    }
                    System.out.println(systemFacade.setBranch(shiftId, branchName));
                }
                while (optionToEditShift != 1 && optionToEditShift != 2) {
                    System.out.println("You chose the wrong number");
                    System.out.println("Choose what to edit:");
                    System.out.println("1- date of shift");
                    System.out.println("2- branch");
                    optionToEditShift = input10.nextInt();
                }
            }
            if (option == 32) {
                System.out.println("Enter password");
                String password = input6.nextLine();
                message = systemFacade.editPassword(employeeIdActive, password);
                while (!message.equals("success")) {
                    System.out.println(message);
                    System.out.println("Enter password");
                    password = input6.nextLine();
                    message = systemFacade.editPassword(employeeIdActive, password);
                }
                System.out.println("Password changed successfully");
            }
        }
        if(employeeIdActive.isEmpty()){
            HRCLI.run();
        }
        else systemFacade.logout(employeeIdActive);
    }

    public static void registerDriver(SystemFacade systemFacade) {
        Scanner input1 = new Scanner(System.in);
        Scanner input2 = new Scanner(System.in);
        Scanner input3 = new Scanner(System.in);
        System.out.println("Enter ID of employee");
        String employeeId = input1.nextLine();
        System.out.println("Enter first name of employee");
        String firstName = input1.nextLine();
        System.out.println("Enter last name of employee");
        String lastName = input1.nextLine();
        System.out.println("Enter password for employee");
        String password = input1.nextLine();
        System.out.println("Enter bank account number of employee");
        int accountNumber = input1.nextInt();
        System.out.println("Enter branch-bank number of employee");
        int branchBankNumber = input1.nextInt();
        System.out.println("Enter salary of employee");
        int salary = input1.nextInt();
        System.out.println("Enter term of employment of employee");
        String termOfEmployment = input2.nextLine();
        System.out.println("Enter status employee");
        String statusOfEmployee = input3.next();
        System.out.println("Enter year of employment");
        int year = input1.nextInt();
        System.out.println("Enter month of employment");
        int month = input1.nextInt();
        System.out.println("Enter day of employment");
        int day = input1.nextInt();
        String message = systemFacade.isValidDate(year, month, day);
        while (!message.contains("success")) {
            System.out.println(message);
            System.out.println("Enter year of employment");
            year = input1.nextInt();
            System.out.println("Enter month of employment");
            month = input1.nextInt();
            System.out.println("Enter day of employment");
            day = input1.nextInt();
            message = systemFacade.isValidDate(year, month, day);
        }
        LocalDateTime date = LocalDateTime.of(year, month, day, 1, 1);
        System.out.println("Enter phone number");
        String phoneNumber = input1.next();
        System.out.println("Enter licenses:");
        int licenses = input2.nextInt();
        message = systemFacade.registerDriver(employeeId, firstName, lastName, password, accountNumber, branchBankNumber, salary, termOfEmployment, statusOfEmployee, date, phoneNumber, licenses);
        while (!message.equals("success")) {
            System.out.println(message);
            if (message.contains("Employee Id")) {
                System.out.println("Enter ID of employee");
                employeeId = input3.next();
            }
            if (message.contains("First name")) {
                System.out.println("Enter first name of employee");
                firstName = input3.next();
            }
            if (message.contains("Last name")) {
                System.out.println("Enter first name of employee");
                firstName = input3.next();
            }
            if (message.contains("Password")) {
                System.out.println("Enter password of employee");
                password = input3.next();
            }
            message = systemFacade.registerDriver(employeeId, firstName, lastName, password, accountNumber, branchBankNumber, salary, termOfEmployment, statusOfEmployee, date, phoneNumber, licenses);
        }
    }

    public static void registerBranchEmployee(SystemFacade systemFacade) {
        Scanner input1 = new Scanner(System.in);
        Scanner input2 = new Scanner(System.in);
        Scanner input3 = new Scanner(System.in);
        Scanner input4 = new Scanner(System.in);
        System.out.println("Enter ID of employee");
        String employeeId = input1.nextLine();
        System.out.println("Enter first name of employee");
        String firstName = input1.nextLine();
        System.out.println("Enter last name of employee");
        String lastName = input1.nextLine();
        System.out.println("Enter password for employee");
        String password = input1.nextLine();
        System.out.println("Enter bank account number of employee");
        int accountNumber = input1.nextInt();
        System.out.println("Enter branch-bank number of employee");
        int branchBankNumber = input1.nextInt();
        System.out.println("Enter salary of employee");
        int salary = input1.nextInt();
        System.out.println("Enter term of employment of employee");
        String termOfEmployment = input2.nextLine();
        System.out.println("Enter status employee");
        String statusOfEmployee = input3.next();
        System.out.println("Enter year of employment");
        int year = input1.nextInt();
        System.out.println("Enter month of employment");
        int month = input1.nextInt();
        System.out.println("Enter day of employment");
        int day = input1.nextInt();
        String message = systemFacade.isValidDate(year, month, day);
        while (!message.contains("success")) {
            System.out.println(message);
            System.out.println("Enter year of employment");
            year = input1.nextInt();
            System.out.println("Enter month of employment");
            month = input1.nextInt();
            System.out.println("Enter day of employment");
            day = input1.nextInt();
            message = systemFacade.isValidDate(year, month, day);
        }
        LocalDateTime date = LocalDateTime.of(year, month, day, 1, 1);
        System.out.println("Enter phone number");
        String phoneNumber = input1.next();
        System.out.println("Enter:");
        System.out.println("1-if employee will be with management permission");
        System.out.println("2-if employee will not be with cancellation permission");
        int answerM = input2.nextInt();
        boolean management;
        if (answerM == 1) management = true;
        else if (answerM == 2) management = false;
        else {
            System.out.println("Enter:");
            System.out.println("1-if employee will be with management permission");
            System.out.println("2-if employee will not be with cancellation permission");
            answerM = input2.nextInt();
            management = answerM == 1;
        }
        System.out.println("Enter:");
        System.out.println("1-if employee will be with cancellation permission");
        System.out.println("2-if employee will not be with cancellation permission");
        int answerC = input2.nextInt();
        boolean cancellation;
        if (answerC == 1) cancellation = true;
        else if (answerC == 2) cancellation = false;
        else {
            System.out.println("Enter:");
            System.out.println("1-if employee will be with cancellation permission");
            System.out.println("2-if employee will not be with cancellation permission");
            answerC = input2.nextInt();
            cancellation = answerC == 1;
        }
        System.out.println("Enter start role of employee");
        String startRole = input4.nextLine();
        while (!systemFacade.isRoleExist(startRole).equals("true")) {
            System.out.println("Error at entering role");
            System.out.println("Enter start role of employee:");
            startRole = input4.nextLine();
        }
        message = systemFacade.registerBranchEmployee(employeeId, firstName, lastName, password, accountNumber, branchBankNumber, salary, termOfEmployment, statusOfEmployee, date, phoneNumber, management, cancellation);
        while (!message.equals("success")) {
            System.out.println(message);
            if (message.contains("Employee Id")) {
                System.out.println("Enter ID of employee");
                employeeId = input3.next();
            }
            if (message.contains("First name")) {
                System.out.println("Enter first name of employee");
                firstName = input3.next();
            }
            if (message.contains("Last name")) {
                System.out.println("Enter last name of employee");
                lastName = input3.next();
            }
            if (message.contains("Password")) {
                System.out.println("Enter password of employee");
                password = input3.next();
            }
            if (message.contains("Shift Manager")) {
                System.out.println("Enter start role of employee:");
                startRole = input4.nextLine();
            }
            message = systemFacade.registerBranchEmployee(employeeId, firstName, lastName, password, accountNumber, branchBankNumber, salary, termOfEmployment, statusOfEmployee, date, phoneNumber, management, cancellation);
        }
    }

    public static void addBranchShift(SystemFacade systemFacade) {
        Scanner input1 = new Scanner(System.in);
        Scanner input2 = new Scanner(System.in);
        Scanner input3 = new Scanner(System.in);
        System.out.println("Enter year of shift");
        int year = input1.nextInt();
        System.out.println("Enter month of shift");
        int month = input1.nextInt();
        System.out.println("Enter day of shift");
        int day = input1.nextInt();
        String message = systemFacade.isValidDate(year, month, day);
        while (!message.contains("success")) {
            System.out.println(message);
            System.out.println("Enter year of shift");
            year = input1.nextInt();
            System.out.println("Enter month of shift");
            month = input1.nextInt();
            System.out.println("Enter day of shift");
            day = input1.nextInt();
            message = systemFacade.isValidDate(year, month, day);
        }
        System.out.println("Enter number of Store Keepers in this shift");
        int storeKeepersC = input1.nextInt();
        System.out.println("Enter number of Cashiers in this shift");
        int cashiersC = input1.nextInt();
        System.out.println("Enter number of Shift managers in this shift");
        int shiftManagersC = input2.nextInt();
        System.out.println("Enter number of General employees in this shift");
        int generalEmployeesC = input2.nextInt();
        System.out.println("Enter number of Guards in this shift");
        int guardsC = input2.nextInt();
        System.out.println("Enter number of Cleaners in this shift");
        int cleanersC = input2.nextInt();
        System.out.println("Enter number of Merchandisers in this shift");
        int merchandisersC = input2.nextInt();
        System.out.println("Choose branch:");
        System.out.println(systemFacade.showAllBranches());
        String branch = input3.nextLine();
        message = systemFacade.isBranchExists(branch);
        while (!message.equals("true")) {
            System.out.println(message);
            System.out.println("Choose branch:");
            System.out.println(systemFacade.showAllBranches());
            branch = input3.nextLine();
            message = systemFacade.isBranchExists(branch);
        }
        System.out.println("Enter type:");
        String type = input3.nextLine();
        LocalDateTime dateTime = LocalDateTime.of(year, month, day, 1, 1);
        message = systemFacade.addBranchShift(dateTime, storeKeepersC, cashiersC, shiftManagersC, generalEmployeesC, guardsC, cleanersC, merchandisersC, branch, type);
        if (!message.equals("success")) {
            System.out.println(message);
            if (message.contains("Store keepers")) {
                System.out.println("Enter number of Store Keepers in this shift");
                storeKeepersC = input2.nextInt();
                message = systemFacade.addBranchShift(dateTime, storeKeepersC, cashiersC, shiftManagersC, generalEmployeesC, guardsC, cleanersC, merchandisersC, branch, type);
            }
            if (message.contains("Cashiers")) {
                System.out.println("Enter number of Cashiers in this shift");
                cashiersC = input2.nextInt();
                message = systemFacade.addBranchShift(dateTime, storeKeepersC, cashiersC, shiftManagersC, generalEmployeesC, guardsC, cleanersC, merchandisersC, branch, type);
            }
            if (message.contains("Shift managers")) {
                System.out.println("Enter number of Shift managers in this shift");
                shiftManagersC = input2.nextInt();
                message = systemFacade.addBranchShift(dateTime, storeKeepersC, cashiersC, shiftManagersC, generalEmployeesC, guardsC, cleanersC, merchandisersC, branch, type);
            }
            if (message.contains("General employees")) {
                System.out.println("Enter number of General employees in this shift");
                generalEmployeesC = input3.nextInt();
                message = systemFacade.addBranchShift(dateTime, storeKeepersC, cashiersC, shiftManagersC, generalEmployeesC, guardsC, cleanersC, merchandisersC, branch, type);
            }
            if (message.contains("Guards")) {
                System.out.println("Enter number of Guards in this shift");
                guardsC = input3.nextInt();
                message = systemFacade.addBranchShift(dateTime, storeKeepersC, cashiersC, shiftManagersC, generalEmployeesC, guardsC, cleanersC, merchandisersC, branch, type);
            }
            if (message.contains("Cleaners")) {
                System.out.println("Enter number of Cleaners in this shift");
                cleanersC = input3.nextInt();
                message = systemFacade.addBranchShift(dateTime, storeKeepersC, cashiersC, shiftManagersC, generalEmployeesC, guardsC, cleanersC, merchandisersC, branch, type);
            }
            if (message.contains("Merchandisers")) {
                System.out.println("Enter number of Merchandisers in this shift");
                merchandisersC = input3.nextInt();
                message = systemFacade.addBranchShift(dateTime, storeKeepersC, cashiersC, shiftManagersC, generalEmployeesC, guardsC, cleanersC, merchandisersC, branch, type);
            }
            if (message.contains("Type")) {
                System.out.println("Enter type:");
                type = input3.nextLine();
                message = systemFacade.addBranchShift(dateTime, storeKeepersC, cashiersC, shiftManagersC, generalEmployeesC, guardsC, cleanersC, merchandisersC, branch, type);
            }
        }
        System.out.println("Created morning shift successfully");
    }

    public static void addBranch(SystemFacade systemFacade) {
        String message;
        Scanner input1 = new Scanner(System.in);
        Scanner input2 = new Scanner(System.in);
        Scanner input3 = new Scanner(System.in);
        System.out.println("Enter address:");
        String address = input1.nextLine();
        System.out.println("Enter phone number:");
        String phoneNumber = input1.nextLine();
        System.out.println("Enter contact number:");
        String contactNumber = input1.nextLine();
        System.out.println("Choose one from regions:");
        System.out.println("1-North\n2-South\n3-Center");
        int region = input1.nextInt();
        while (region < 1 || region > 3) {
            System.out.println("You chose wrong region");
            System.out.println("Choose one from regions:");
            System.out.println("1-North\n2-South\n3-Center");
            region = input1.nextInt();
        }
        System.out.println("Enter morning Shift start hour:");
        int morningStartHour = input1.nextInt();
        System.out.println("Enter morning Shift start minute:");
        int morningStartMin = input1.nextInt();
        message = systemFacade.isValidTime(morningStartHour, morningStartMin);
        while (!message.contains("success")) {
            System.out.println(message);
            System.out.println("Enter morning Shift start hour:");
            morningStartHour = input1.nextInt();
            System.out.println("Enter morning Shift start minute:");
            morningStartMin = input1.nextInt();
            message = systemFacade.isValidTime(morningStartHour, morningStartMin);
        }
        System.out.println("Enter morning shift end hour:");
        int morningEndHour = input1.nextInt();
        System.out.println("Enter morning shift end minute:");
        int morningEndMin = input1.nextInt();
        message = systemFacade.isValidTime(morningEndHour, morningEndMin);
        while (!message.contains("success")) {
            System.out.println(message);
            System.out.println("Enter morning shift end hour:");
            morningEndHour = input2.nextInt();
            System.out.println("Enter morning shift end minute:");
            morningEndMin = input2.nextInt();
            message = systemFacade.isValidTime(morningEndHour, morningEndMin);
        }
        System.out.println("Enter evening shift start hour:");
        int eveningStartHour = input2.nextInt();
        System.out.println("Enter evening shift start minute:");
        int eveningStartMin = input2.nextInt();
        message = systemFacade.isValidTime(eveningStartHour, eveningStartMin);
        while (!message.contains("success")) {
            System.out.println(message);
            System.out.println("Enter evening shift start hour:");
            eveningStartHour = input2.nextInt();
            System.out.println("Enter evening shift start minute:");
            eveningStartMin = input2.nextInt();
            message = systemFacade.isValidTime(eveningStartHour, eveningStartMin);
        }
        System.out.println("Enter evening shift end hour:");
        int eveningEndHour = input3.nextInt();
        System.out.println("Enter evening shift end minute:");
        int eveningEndMin = input3.nextInt();
        message = systemFacade.isValidTime(eveningEndHour, eveningEndMin);
        while (!message.contains("success")) {
            System.out.println(message);
            System.out.println("Enter evening shift end hour:");
            eveningEndHour = input3.nextInt();
            System.out.println("Enter evening shift end minute:");
            eveningEndMin = input3.nextInt();
            message = systemFacade.isValidTime(eveningEndHour, eveningEndMin);
        }
        LocalTime morningShiftStartHour = LocalTime.of(morningStartHour, morningStartMin);
        LocalTime morningShiftEndHour = LocalTime.of(morningEndHour, morningEndMin);
        LocalTime eveningShiftStartHour = LocalTime.of(eveningStartHour, eveningStartMin);
        LocalTime eveningShiftEndHour = LocalTime.of(eveningEndHour, eveningEndMin);
        System.out.println("Enter latitude");
        double latitude = input1.nextDouble();
        System.out.println("Enter longitude");
        double longitude = input1.nextDouble();
        System.out.println(systemFacade.addBranch(address, phoneNumber, contactNumber, region, morningShiftStartHour, eveningShiftStartHour, morningShiftEndHour, eveningShiftEndHour, latitude, longitude));
    }

    private static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}
