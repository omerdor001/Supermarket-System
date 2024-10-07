package PresentationLayer.CLI;

import ServiceLayer.SystemFacade;

import java.util.Scanner;

public class EmployeeHRCLI {
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
        System.out.println("Login Successfully");
        message = systemFacade.isHRManager(employeeIdActive);
        if (message.equals("true")){
            System.out.println("Must be HR to enter");
            systemFacade.logout(employeeIdActive);
            System.out.println(message);
            System.out.println("Enter ID number");
            employeeIdActive = input.nextLine();
            System.out.println("Enter Password");
            passwordActive = input.nextLine();
            message = systemFacade.login(employeeIdActive, passwordActive);
        }
        else {
            if (message.equals("false")) showMenuOfREmployee(employeeIdActive, input, systemFacade);
            else System.out.println("There was an error at this page. Please enter this page again");
        }
    }

    public static void showMenuOfREmployee(String employeeIdActive, Scanner input, SystemFacade systemFacade) {
        Scanner input2 = new Scanner(System.in);
        Scanner input3 = new Scanner(System.in);
        Scanner input4 = new Scanner(System.in);
        String message;
        int option = 0;
        while (option != 11) {
            System.out.println("Choose an option:");
            System.out.println("1 - Change password");
            System.out.println("2 - Add constraint");
            System.out.println("3 - Edit constraint");
            System.out.println("4 - Remove constraint");
            System.out.println("5 - Add cancellation of product");
            System.out.println("6 - Show branch morning shift start hour and end hour");
            System.out.println("7 - Show branch evening shift start hour and end hour");
            System.out.println("8 - Show future shifts");
            System.out.println("9 - Remove cancellation of product");
            System.out.println("10 - Show deliveries by shift");
            System.out.println("11 - Logout");
            option = input.nextInt();
            if (option == 1) {
                System.out.println("Enter password");
                String password = input.next();
                message = systemFacade.editPassword(employeeIdActive, password);
                while (!message.equals("success")) {
                    System.out.println(message);
                    System.out.println("Enter password");
                    password = input.next();
                    message = systemFacade.editPassword(employeeIdActive, password);
                }
                System.out.println("Password changed successfully");
            }
            if (option == 2) {
                System.out.println("Choose shift:");
                System.out.println(systemFacade.showFutureShifts(employeeIdActive));
                int shiftId = input.nextInt();
                while (!systemFacade.isFutureShifts(shiftId).equals("true") || shiftId < 1) {
                    System.out.println("You chose wrong shift.");
                    System.out.println("Choose shift:");
                    System.out.println(systemFacade.showFutureShifts(employeeIdActive));
                    shiftId = input.nextInt();
                }
                System.out.println("Enter description of constraint: ");
                String description = input4.nextLine();
                System.out.println(systemFacade.addConstraint(employeeIdActive, shiftId, description));
            }
            if (option == 3) {
                System.out.println("Choose constraint:");
                System.out.println(systemFacade.showAllConstraintToEmployee(employeeIdActive));
                int constraintId = input.nextInt();
                while (!systemFacade.isConstraintExist(employeeIdActive, constraintId).equals("true")) {
                    System.out.println("You chose wrong constraint.");
                    System.out.println("Choose constraint:");
                    System.out.println(systemFacade.showAllConstraintToEmployee(employeeIdActive));
                    constraintId = input.nextInt();
                }
                System.out.println("Choose Option:");
                System.out.println("1- Edit shift");
                System.out.println("2- Edit description");
                int optionForChange = input.nextInt();
                while (optionForChange != 1 && optionForChange != 2) {
                    System.out.println("Choose Option:");
                    System.out.println("1- Edit shift");
                    System.out.println("2- Edit description");
                    optionForChange = input.nextInt();
                }
                if (optionForChange == 1) {
                    System.out.println("Choose shift:");
                    System.out.println(systemFacade.showFutureBranchShifts());
                    int shiftId = input2.nextInt();
                    while (systemFacade.isFutureShifts(shiftId).equals("false") || shiftId < 1) {
                        System.out.println("You chose wrong shift.");
                        System.out.println("Choose shift:");
                        System.out.println(systemFacade.showFutureBranchShifts());
                        shiftId = input2.nextInt();
                    }
                    System.out.println(systemFacade.editConstraintShift(employeeIdActive, constraintId, shiftId));
                }
                if (optionForChange == 2) {
                    System.out.println("Enter description:");
                    String description = input3.nextLine();
                    System.out.println(systemFacade.editConstraintDescription(employeeIdActive, constraintId, description));
                }
            }
            if (option == 4) {
                System.out.println("Choose constraint:");
                System.out.println(systemFacade.showAllConstraintToEmployee(employeeIdActive));
                int constraintId = input2.nextInt();
                while (systemFacade.isConstraintExist(employeeIdActive, constraintId).equals("false")) {
                    System.out.println("You chose wrong constraint.");
                    System.out.println("Choose constraint:");
                    System.out.println(systemFacade.showAllConstraintToEmployee(employeeIdActive));
                    constraintId = input2.nextInt();
                }
                System.out.println(systemFacade.removeConstraint(employeeIdActive, constraintId));
            }
            if (option == 5) {
                System.out.println("Choose shift:");
                System.out.println(systemFacade.showBranchEmployeeHisPastShifts(employeeIdActive));
                int shiftId = input2.nextInt();
                System.out.println("Enter product ID:");
                String productId = input2.next();
                System.out.println(systemFacade.addCancellation(shiftId, productId, employeeIdActive));
            }
            if (option == 6) {
                System.out.println("Choose branch:");
                System.out.println(systemFacade.showAllBranches());
                String branchName = input3.nextLine();
                while (!systemFacade.isBranchExists(branchName).equals("true")) {
                    System.out.println("Choose branch:");
                    System.out.println(systemFacade.showAllBranches());
                    branchName = input3.nextLine();
                }
                System.out.println(systemFacade.showBranchMorningShiftHours(branchName));
            }
            if (option == 7) {
                System.out.println("Choose branch:");
                System.out.println(systemFacade.showAllBranches());
                String branchName = input3.nextLine();
                while (!systemFacade.isBranchExists(branchName).equals("true")) {
                    System.out.println("Choose branch:");
                    System.out.println(systemFacade.showAllBranches());
                    branchName = input3.nextLine();
                }
                System.out.println(systemFacade.showBranchEveningShiftHours(branchName));
            }
            if (option == 8) {
                System.out.println(systemFacade.showEmployeeHisFutureShifts(employeeIdActive));
            }
            if (option == 9) {
                while (true) {
                    try {
                        System.out.println("Choose shift:");
                        System.out.println(systemFacade.showBranchEmployeeHisPastShifts(employeeIdActive));
                        int shiftId = input2.nextInt();
                        System.out.println("Enter product ID:");
                        String productId = input2.next();
                        System.out.println(systemFacade.removeCancellation(shiftId, productId, employeeIdActive));
                        continue;
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                        continue;
                    }
                }
            }
            if (option == 10) {
                while (true) {
                    try {
                        System.out.println("Choose shift:");
                        System.out.println(systemFacade.showAllBranchShifts());
                        int shiftId = input2.nextInt();
                        System.out.println(systemFacade.showDeliveryByShift(employeeIdActive, shiftId));
                        continue;
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                        continue;
                    }
                }
            }
        }
        if(systemFacade.isEmployeeExists(employeeIdActive).equals("false")){
            HRCLI.run();
        }
        else systemFacade.logout(employeeIdActive);
    }
}
