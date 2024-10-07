import PresentationLayer.CLI.DeliveryCLI;
import PresentationLayer.CLI.HRCLI;
import ServiceLayer.SystemFacade;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.Scanner;

public class SuperLiMainCLI {
    public static void main(String[] args) throws JsonProcessingException {
        Scanner input = new Scanner(System.in);
        System.out.println("Welcome store manager");
        while (true) {
            System.out.println("Please pick a module: HR, Delivery, Reset data, Delete data or type exit");
            String userInput = input.nextLine();
            switch (userInput) {
                case "HR": {
                    try {
                        HRCLI.run();
                        break;
                    } catch (Exception e) {
                        System.out.println("Bad Input");
                        continue;
                    }
                }
                case "Delivery": {
                    DeliveryCLI.run();
                    break;
                }
                case "exit": {
                    input.close();
                    System.exit(0);
                }
                case "Reset data": {
                    SystemFacade.getInstance().resetData();
                    break;
                }
                case "Delete data": {
                    SystemFacade.deleteData();
                    break;
                }
                default:
                    System.out.println("Invalid command");
            }
        }
    }
}
