package PresentationLayer.CLI;

import ServiceLayer.SystemFacade;

import java.util.Scanner;

public class HRCLI {
    public static void run() {
        SystemFacade systemFacade = SystemFacade.getInstance();
        Scanner input = new Scanner(System.in);
        Scanner input2 = new Scanner(System.in);
        System.out.println("Choose an option");
        System.out.println("1 - HR Manager");
        System.out.println("2 - Employee");
        System.out.println("3 - Exit");
        int choice = input.nextInt();
        while(choice!=1 && choice!=2 && choice!=3){
            System.out.println("Wrong input");
            System.out.println("Choose an option");
            System.out.println("1 - HR Manager");
            System.out.println("2 - Employee");
            System.out.println("3 - Exit");
            choice = input.nextInt();
        }
        if (choice==1){
            ManagerHRCLI.showMenuOfHR("",new Scanner(System.in),systemFacade);
        }
        else if (choice==2){
            System.out.println("Enter ID number");
            String employeeIdActive = input2.nextLine();
            while(employeeIdActive.length()!=9 && isNumeric(employeeIdActive)){
                System.out.println("Wrong input");
                System.out.println("Enter ID number");
                employeeIdActive = input2.nextLine();
            }
            EmployeeHRCLI.showMenuOfREmployee(employeeIdActive,new Scanner(System.in),systemFacade);
        }
        else return;
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
