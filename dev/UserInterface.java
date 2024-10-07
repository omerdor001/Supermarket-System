import PresentationLayer.CLI.DeliveryCLI;
import PresentationLayer.CLI.EmployeeHRCLI;
import PresentationLayer.CLI.HRCLI;
import PresentationLayer.CLI.ManagerHRCLI;
import PresentationLayer.GUI.Delivery.DeliveryMainMenu.DeliveryMainController;
import PresentationLayer.GUI.HR.Login.EmployeeLoginController;
import PresentationLayer.GUI.HR.Login.HRLoginController;
import PresentationLayer.GUI.MainMenuController;
import ServiceLayer.SystemFacade;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.Scanner;

public class UserInterface {
    public static void main(String[] args) throws JsonProcessingException {
        SystemFacade systemFacade = SystemFacade.getInstance();
        if (args.length <= 2 && args.length > 0) {
            if (args[0].equals("CLI")) {
                if(args.length == 1)
                    storeManager(systemFacade);
                else {
                    switch (args[1]){
                        case "StoreManager": {
                            storeManager(systemFacade);
                            break;
                        }
                        case "DeliveryManager": {
                            DeliveryCLI.run();
                            break;
                        }
                        case "HRManager": {
                            ManagerHRCLI.run();
                            break;
                        }
                        case "Employee": {
                            EmployeeHRCLI.run();
                            break;
                        }
                    }
                }
            }
            else if(args[0].equals("GUI")) {
                if(args.length == 1)
                    new MainMenuController();
                else {
                    switch (args[1]){
                        case "StoreManager": {
                            new MainMenuController();
                            break;
                        }
                        case "DeliveryManager": {
                            new DeliveryMainController();
                            break;
                        }
                        case "HRManager": {
                            new HRLoginController();
                            break;
                        }
                        case "Employee": {
                            new EmployeeLoginController();
                            break;
                        }
                    }
                }
            }
            else
                throw new IllegalArgumentException("Invalid arguments");
        }
        else
            new MainMenuController();
    }

    public static void storeManager(SystemFacade systemFacade) throws JsonProcessingException {
        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.println("Please choose Delivery, HR, Reset data, Delete data or exit");
            if (input.hasNext()) {
                String action = input.nextLine();
                switch (action) {
                    case "exit": {
                        input.close();
                        System.exit(0);
                        break;
                    }
                    case "Delivery": {
                        DeliveryCLI.run();
                        break;
                    }
                    case "HR": {
                        try {
                            HRCLI.run();
                            break;
                        } catch (Exception e) {
                            System.out.println("Bad Input");
                            continue;
                        }
                    }
                    case "Reset data": {
                        systemFacade.resetData();
                        break;
                    }
                    case "Delete data": {
                        SystemFacade.deleteData();
                        break;
                    }
                    default:
                        System.out.println("Invalid action");
                }
            }
        }
    }
}
