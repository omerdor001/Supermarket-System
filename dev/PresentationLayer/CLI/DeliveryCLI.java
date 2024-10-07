package PresentationLayer.CLI;

import BusinessLayer.Delivery.DeliveryOrder;
import ServiceLayer.JsonService.JsonConverter;
import ServiceLayer.SystemFacade;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.Scanner;

public class DeliveryCLI {
    public static void LoadData(SystemFacade systemFacade) throws JsonProcessingException {
        SystemFacade.deleteData();
        DeliveryOrder.Triplet<String, Integer, Double> item0 = new DeliveryOrder.Triplet<>("000-000", 15, 0.25);
        DeliveryOrder.Triplet<String, Integer, Double> item1 = new DeliveryOrder.Triplet<>("111-111", 30, 0.2);
        DeliveryOrder.Triplet<String, Integer, Double> item2 = new DeliveryOrder.Triplet<>("222-222", 5, 1.0);
        DeliveryOrder.Triplet<String, Integer, Double> item3 = new DeliveryOrder.Triplet<>("333-333", 20, 0.5);
        DeliveryOrder.Triplet<String, Integer, Double> item4 = new DeliveryOrder.Triplet<>("444-444", 20, 0.25);
        DeliveryOrder.Triplet<String, Integer, Double> item5 = new DeliveryOrder.Triplet<>("555-555", 10, 1.5);
        DeliveryOrder.Triplet<String, Integer, Double> item6 = new DeliveryOrder.Triplet<>("666-666", 15, 0.4);
        DeliveryOrder.Triplet<String, Integer, Double> item7 = new DeliveryOrder.Triplet<>("777-777", 10, 0.5);
        LinkedList<DeliveryOrder.Triplet<String, Integer, Double>> list1 = new LinkedList<>();
        LinkedList<DeliveryOrder.Triplet<String, Integer, Double>> list2 = new LinkedList<>();
        LinkedList<DeliveryOrder.Triplet<String, Integer, Double>> list3 = new LinkedList<>();
        LinkedList<DeliveryOrder.Triplet<String, Integer, Double>> list4 = new LinkedList<>();
        list1.add(item0);
        list1.add(item1);
        list2.add(item2);
        list2.add(item3);
        list3.add(item4);
        list3.add(item5);
        list4.add(item6);
        list4.add(item7);
        DeliveryOrder.Shipment ship1 = new DeliveryOrder.Shipment("13/04/2023", "Tel Aviv", "Beer Sheva", "Avi", "Moshe", "0501111111", "0502222222", list1);
        DeliveryOrder.Shipment ship2 = new DeliveryOrder.Shipment("14/04/2023", "Beer Sheva", "Tel Aviv", "Moshe", "Avi", "0502222222", "0501111111", list2);
        DeliveryOrder.Shipment ship3 = new DeliveryOrder.Shipment("15/04/2023", "Haifa", "Jerusalem", "Yossi", "Dan", "0503333333", "0504444444", list3);
        DeliveryOrder.Shipment ship4 = new DeliveryOrder.Shipment("16/04/2023", "Jerusalem", "Haifa", "Dan", "Yossi", "0504444444", "0503333333", list4);
        System.out.println(JsonConverter.toJson(ship1));
        System.out.println(JsonConverter.toJson(ship2));
        System.out.println(JsonConverter.toJson(ship3));
        System.out.println(JsonConverter.toJson(ship4));
        System.out.println();
        System.out.println();
        System.out.println(systemFacade.addSite("Tel Aviv", "0501111111", "Avi", 0, 0, 64, 60));
        System.out.println(systemFacade.addSite("Beer Sheva", "0502222222", "Moshe", 0, 1, 63, 59));
        System.out.println(systemFacade.addSite("Haifa", "0503333333", "avi", 1, 0, 62, 58));
        System.out.println(systemFacade.addSite("Jerusalem", "0504444444", "Dan", 1, 1, 61, 58));
        System.out.println(systemFacade.addSite("Ashdod", "0525381648", "Anna Zack", 3, 2, 60, 57));
        System.out.println(systemFacade.parseOrder(JsonConverter.toJson(ship1)));
        System.out.println(systemFacade.parseOrder(JsonConverter.toJson(ship2)));
        System.out.println(systemFacade.parseOrder(JsonConverter.toJson(ship3)));
        System.out.println(systemFacade.parseOrder(JsonConverter.toJson(ship4)));

        System.out.println(systemFacade.addDriverQualification("333333333", "Refrigeration"));
        System.out.println(systemFacade.addDriverQualification("444444444", "Refrigeration"));
        System.out.println(systemFacade.addTruck(1234, "Regular", "Mazda", 1500, 4000));
        System.out.println(systemFacade.addTruck(4321, "Refrigeration", "Honda", 1500, 4000));
        System.out.println(systemFacade.addTruck(5678, "Regular", "Mitsubishi", 2000, 7000));
        System.out.println(systemFacade.addTruck(8765, "Refrigeration", "Mercedes", 2000, 7000));
        System.out.println();
        System.out.println("Data loaded successfully");
        System.out.println();
        System.out.println();
    }

    public static void main(String[] args) throws JsonProcessingException {
        SystemFacade systemFacade = SystemFacade.getInstance();
        LoadData(systemFacade);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome delivery manager");
        while (true) {
            System.out.println("Please type in one of the following actions: resources, deliveries, exit");
            String userInput = scanner.nextLine();
            switch (userInput) {
                case "resources": {
                    resourceFunctions(scanner, systemFacade);
                    break;
                }
                case "deliveries": {
                    deliveryFunctions(scanner, systemFacade);
                    break;
                }
                case "exit": {
                    scanner.close();
                    System.exit(0);
                }
                default:
                    System.out.println("Invalid command");
            }
        }
    }

    public static void run() {
        SystemFacade systemFacade = SystemFacade.getInstance();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome delivery manager");
        while (true) {
            System.out.println("Please type in one of the following actions: resources, deliveries, exit");
            String userInput = scanner.nextLine();
            switch (userInput) {
                case "resources": {
                    resourceFunctions(scanner, systemFacade);
                    break;
                }
                case "deliveries": {
                    deliveryFunctions(scanner, systemFacade);
                    break;
                }
                case "exit": {
                    return;
                }
                default:
                    System.out.println("Invalid command");
            }
        }
    }

    //region Resource management
    private static void resourceFunctions(Scanner scanner, SystemFacade systemFacade) {
        while (true) {
            System.out.println("You are in the resource management menu please type in one of the following actions: drivers, trucks, sites or back");
            String userInput = scanner.nextLine();
            switch (userInput) {
                case "drivers": {
                    driverFunctions(scanner, systemFacade);
                    break;
                }
                case "trucks": {
                    truckFunctions(scanner, systemFacade);
                    break;
                }
                case "sites": {
                    siteFunctions(scanner, systemFacade);
                    break;
                }
                case "back":
                    return;
                default:
                    System.out.println("Invalid command");
            }
        }
    }

    //region Driver functions
    private static void driverFunctions(Scanner scanner, SystemFacade systemFacade) {
        while (true) {
            System.out.println("You are in the driver management menu please type in one of the following actions: show, show all or back");
            String userInput = scanner.nextLine();
            switch (userInput) {
                case "show": {
                    showDriver(scanner, systemFacade);
                    break;
                }
                case "show all": {
                    System.out.println(systemFacade.showAllDrivers());
                    break;
                }
                case "back":
                    return;
                default:
                    System.out.println("Not a valid command");
            }
        }
    }

    private static void showDriver(Scanner scanner, SystemFacade systemFacade) {
        while (true) {
            System.out.println("You are in the driver show menu please type in driver id or type back");
            String userInput = scanner.nextLine();
            if (!userInput.equals("back")) {
                try {
                    System.out.println(systemFacade.showDriver(userInput));
                    continue;
                } catch (Exception e) {
                    System.out.println("Wrong format");
                    continue;
                }
            }
            return;
        }
    }
    //endregion

    //region Truck user functions
    private static void truckFunctions(Scanner scanner, SystemFacade systemFacade) {
        while (true) {
            System.out.println("You are in the truck management menu please type in one of the following actions: add, remove, edit, show or back");
            String userInput = scanner.nextLine();
            switch (userInput) {
                case "add": {
                    addTruck(scanner, systemFacade);
                    break;
                }
                case "remove": {
                    removeTruck(scanner, systemFacade);
                    break;
                }
                case "edit": {
                    editTruck(scanner, systemFacade);
                    break;
                }
                case "show": {
                    showTruck(scanner, systemFacade);
                    break;
                }
                case "back":
                    return;
                default:
                    System.out.println("Not a valid command");
            }
        }
    }

    private static void addTruck(Scanner scanner, SystemFacade systemFacade) {
        while (true) {
            System.out.println("You are in the truck adding menu please type tuck id,truck type, model, net weight and max weight: [id,type,model,net weight,max weight] where id,net weight and max weight are numbers, net and max weight are in kilograms or type back");
            String userInput = scanner.nextLine();
            if (!userInput.equals("back")) {
                String[] split = userInput.split(",");
                if (split.length != 5) {
                    System.out.println("Wrong format");
                    continue;
                }
                try {
                    System.out.println(systemFacade.addTruck(Integer.parseInt(split[0]), split[1], split[2], Float.parseFloat(split[3]), Float.parseFloat(split[4])));
                    continue;
                } catch (Exception e) {
                    System.out.println("Wrong format");
                    continue;
                }
            }
            return;
        }
    }

    private static void removeTruck(Scanner scanner, SystemFacade systemFacade) {
        while (true) {
            System.out.println("You are in the truck remove menu please type truck id number or type back");
            String userInput = scanner.nextLine();
            if (!userInput.equals("back")) {
                try {
                    System.out.println(systemFacade.removeTruck(Integer.parseInt(userInput)));
                    continue;
                } catch (Exception e) {
                    System.out.println("Wrong format");
                    continue;
                }
            }
            return;
        }
    }

    private static void editTruck(Scanner scanner, SystemFacade systemFacade) {
        while (true) {
            System.out.println("You are in the truck edit menu please type in one of the following actions: status, add delivery date, remove delivery date or back");
            String userInput = scanner.nextLine();
            switch (userInput) {
                case "status": {
                    editTruckStatus(scanner, systemFacade);
                    break;
                }
                case "add delivery date": {
                    addTruckDeliveryDate(scanner, systemFacade);
                    break;
                }
                case "remove delivery date": {
                    removeTruckDeliveryDate(scanner, systemFacade);
                    break;
                }
                case "back":
                    return;
                default:
                    System.out.println("Not a valid command");
            }
        }
    }

    private static void editTruckStatus(Scanner scanner, SystemFacade systemFacade) {
        while (true) {
            System.out.println("You are in the truck status edit menu please type truck id and status in the format: [id,status] where id is a number and status is 0 or 1 or type back");
            String userInput = scanner.nextLine();
            if (!userInput.equals("back")) {
                String[] split = userInput.split(",");
                if (split.length != 2) {
                    System.out.println("Wrong format");
                    continue;
                }
                try {
                    System.out.println(systemFacade.updateTruckStatus(Integer.parseInt(split[0]), Integer.parseInt(split[1])));
                    continue;
                } catch (Exception e) {
                    System.out.println("Wrong format");
                    continue;
                }
            }
            return;
        }
    }

    private static void addTruckDeliveryDate(Scanner scanner, SystemFacade systemFacade) {
        while (true) {
            System.out.println("You are in the truck delivery date add menu please type truck id and delivery date in the format: [id,YYYY/MM/DD] where id is a number or type back");
            String userInput = scanner.nextLine();
            if (!userInput.equals("back")) {
                String[] split = userInput.split(",");
                if (split.length != 2) {
                    System.out.println("Wrong input format");
                    continue;
                }
                try {
                    String[] date = split[1].split("/");
                    if (date.length != 3) {
                        System.out.println("Wrong date format");
                        continue;
                    }
                    System.out.println(systemFacade.addTruckDeliveryDate(Integer.parseInt(split[0]), LocalDate.of(Integer.parseInt(date[0]), Integer.parseInt(date[1]), Integer.parseInt(date[2]))));
                    continue;
                } catch (Exception e) {
                    System.out.println("Wrong input format");
                    continue;
                }
            }
            return;
        }
    }

    private static void removeTruckDeliveryDate(Scanner scanner, SystemFacade systemFacade) {
        while (true) {
            System.out.println("You are in the truck delivery date remove menu please type truck id and delivery date in the format: [id,YYYY/MM/DD] where id is a number or type back");
            String userInput = scanner.nextLine();
            if (!userInput.equals("back")) {
                String[] split = userInput.split(",");
                if (split.length != 2) {
                    System.out.println("Wrong input format");
                    continue;
                }
                try {
                    String[] date = split[1].split("/");
                    if (date.length != 3) {
                        System.out.println("Wrong date format");
                        continue;
                    }
                    System.out.println(systemFacade.removeTruckDeliveryDate(Integer.parseInt(split[0]), LocalDate.of(Integer.parseInt(date[0]), Integer.parseInt(date[1]), Integer.parseInt(date[2]))));
                    continue;
                } catch (Exception e) {
                    System.out.println("Wrong input format");
                    continue;
                }
            }
            return;
        }
    }

    private static void showTruck(Scanner scanner, SystemFacade systemFacade) {
        while (true) {
            System.out.println("You are in the truck showing menu please type all, truck id or type back");
            String userInput = scanner.nextLine();
            if (!userInput.equals("back")) {
                if (userInput.equals("all")) {
                    System.out.println(systemFacade.showAllTrucks());
                    continue;
                }
                try {
                    System.out.println(systemFacade.showTruck(Integer.parseInt(userInput)));
                    continue;
                } catch (Exception e) {
                    System.out.println("Wrong format");
                    continue;
                }
            }
            return;
        }
    }
    //endregion

    //region Site user functions
    private static void siteFunctions(Scanner scanner, SystemFacade systemFacade) {
        while (true) {
            System.out.println("You are in the site management menu please type in one of the following actions: add, remove, edit, show or back");
            String userInput = scanner.nextLine();
            switch (userInput) {
                case "add": {
                    addSite(scanner, systemFacade);
                    break;
                }
                case "remove": {
                    removeSite(scanner, systemFacade);
                    break;
                }
                case "edit": {
                    editSite(scanner, systemFacade);
                    break;
                }
                case "show": {
                    showSites(scanner, systemFacade);
                    break;
                }
                case "back":
                    return;
                default:
                    System.out.println("Not a valid command");
            }
        }
    }

    private static void addSite(Scanner scanner, SystemFacade systemFacade) {
        while (true) {
            System.out.println("You are in the site adding menu please type new site address, phone number, contact name, region, type, latitude and longitude in the format: [address,phone number,contact name,region,type,latitude,longitude] where address,contact name and phone number are text, region is 0(North) 1(Center) 2(South) 3(General), type is 0(Store) 1(Supplier) 2(Center), latitude and longitude are real numbers or type back");
            String userInput = scanner.nextLine();
            if (!userInput.equals("back")) {
                String[] split = userInput.split(",");
                if (split.length != 7) {
                    System.out.println("Wrong format");
                    continue;
                }
                try {
                    System.out.println(systemFacade.addSite(split[0], split[1], split[2], Integer.parseInt(split[3]), Integer.parseInt(split[4]), Double.parseDouble(split[5]), Double.parseDouble(split[6])));
                    continue;
                } catch (Exception e) {
                    System.out.println("Wrong format");
                    continue;
                }
            }
            return;
        }
    }

    private static void removeSite(Scanner scanner, SystemFacade systemFacade) {
        while (true) {
            System.out.println("You are in the site remove menu please type site address or type back");
            String userInput = scanner.nextLine();
            if (!userInput.equals("back")) {
                System.out.println(systemFacade.removeSite(userInput));
                continue;
            }
            return;
        }
    }

    private static void editSite(Scanner scanner, SystemFacade systemFacade) {
        while (true) {
            System.out.println("You are in the site edit menu please select: contact name, phone number or back");
            String userInput = scanner.nextLine();
            switch (userInput) {
                case "contact name": {
                    editSiteContactName(scanner, systemFacade);
                    break;
                }
                case "phone number": {
                    editSitePhoneNumber(scanner, systemFacade);
                    break;
                }
                case "back":
                    return;
                default:
                    System.out.println("Invalid action");
            }
        }
    }

    private static void editSiteContactName(Scanner scanner, SystemFacade systemFacade) {
        while (true) {
            System.out.println("You are in the site contact name edit menu please type site address and new contact name in the format: [site address,contact name] or type back");
            String userInput = scanner.nextLine();
            if (!userInput.equals("back")) {
                String[] split = userInput.split(",");
                if (split.length != 2) {
                    System.out.println("Wrong format");
                    continue;
                }
                try {
                    System.out.println(systemFacade.updateContactName(split[0], split[1]));
                    continue;
                } catch (Exception e) {
                    System.out.println("Wrong format");
                    continue;
                }
            }
            return;
        }
    }

    private static void editSitePhoneNumber(Scanner scanner, SystemFacade systemFacade) {
        while (true) {
            System.out.println("You are in the site phone number edit menu please type site address and new phone number in the format: [site address,phone number] or type back");
            String userInput = scanner.nextLine();
            if (!userInput.equals("back")) {
                String[] split = userInput.split(",");
                if (split.length != 2) {
                    System.out.println("Wrong format");
                    continue;
                }
                try {
                    System.out.println(systemFacade.updatePhoneNumber(split[0], split[1]));
                    continue;
                } catch (Exception e) {
                    System.out.println("Wrong format");
                    continue;
                }
            }
            return;
        }
    }

    private static void showSites(Scanner scanner, SystemFacade systemFacade) {
        while (true) {
            System.out.println("You are in the site show menu please select: show, show all or back");
            String userInput = scanner.nextLine();
            switch (userInput) {
                case "show": {
                    showSite(scanner, systemFacade);
                    break;
                }
                case "show all": {
                    System.out.println(systemFacade.showAllSites());
                    break;
                }
                case "back":
                    return;
                default:
                    System.out.println("Invalid action");
            }
        }
    }

    private static void showSite(Scanner scanner, SystemFacade systemFacade) {
        while (true) {
            System.out.println("You are in the single site show menu please type site address or type back");
            String userInput = scanner.nextLine();
            if (!userInput.equals("back")) {
                System.out.println(systemFacade.showSite(userInput));
                continue;
            }
            return;
        }
    }
    //endregion

    //endregion

    //region Delivery management
    private static void deliveryFunctions(Scanner scanner, SystemFacade systemFacade) {
        while (true) {
            System.out.println("You are in the delivery management menu please type in one of the following actions: parse order, create delivery, edit delivery, cancel delivery, progress delivery, show delivery, show all deliveries, show delivery order, show all delivery orders, show all item orders or back");
            String userInput = scanner.nextLine();
            switch (userInput) {
                case "parse order": {
                    parseOrder(scanner, systemFacade);
                    break;
                }
                case "create delivery": {
                    createDelivery(scanner, systemFacade);
                    break;
                }
                case "edit delivery": {
                    editDelivery(scanner, systemFacade);
                    break;
                }
                case "progress delivery": {
                    progressDelivery(scanner, systemFacade);
                    break;
                }
                case "cancel delivery": {
                    cancelDelivery(scanner, systemFacade);
                    break;
                }
                case "show delivery": {
                    showDelivery(scanner, systemFacade);
                    break;
                }
                case "show all deliveries": {
                    System.out.println(systemFacade.showAllDeliveries());
                    break;
                }
                case "show delivery order": {
                    showDeliveryOrder(scanner, systemFacade);
                    break;
                }
                case "show all delivery orders": {
                    System.out.println(systemFacade.showAllDeliveryOrders());
                    break;
                }
                case "show all item orders": {
                    System.out.println(systemFacade.showAllItemOrders());
                    break;
                }
                case "back":
                    return;
                default:
                    System.out.println("Invalid command");
            }
        }
    }

    private static void parseOrder(Scanner scanner, SystemFacade systemFacade) {
        while (true) {
            System.out.println("You are in the delivery order parse menu please type in json to parse or type back");
            String userInput = scanner.nextLine();
            if (!userInput.equals("back")) {
                try {
                    System.out.println(systemFacade.parseOrder(userInput));
                    continue;
                } catch (Exception e) {
                    System.out.println("Wrong format");
                    continue;
                }
            }
            return;
        }
    }

    //region Delivery planning functions
    private static void createDelivery(Scanner scanner, SystemFacade systemFacade) {
        while (true) {
            System.out.println("You are in the delivery creation menu please type in delivery date, delivery leave time, selected truck id, selected driver id, source address in the format: [YYYY/MM/DD,HH:MM,truck id,driver id,address] where truck id and driver id are numbers or type back");
            String userInput = scanner.nextLine();
            if (!userInput.equals("back")) {
                String[] split = userInput.split(",");
                if (split.length != 5) {
                    System.out.println("Wrong format");
                    continue;
                }
                String[] date = split[0].split("/");
                if (date.length != 3) {
                    System.out.println("Wrong format");
                    continue;
                }
                String[] time = split[1].split(":");
                if (time.length != 2) {
                    System.out.println("Wrong format");
                    continue;
                }
                try {
                    System.out.println(systemFacade.addDelivery(LocalDate.of(Integer.parseInt(date[0]), Integer.parseInt(date[1]), Integer.parseInt(date[2])), LocalTime.of(Integer.parseInt(time[0]), Integer.parseInt(time[1])), Integer.parseInt(split[2]), split[3], split[4]));
                    continue;
                } catch (Exception e) {
                    System.out.println("Wrong format");
                    continue;
                }
            }
            return;
        }
    }

    private static void editDelivery(Scanner scanner, SystemFacade systemFacade) {
        while (true) {
            System.out.println("You are in the delivery management menu please type in one of the following actions: update truck, update driver, add stop, remove stop, edit stop arrive time,add item, remove item or back");
            String userInput = scanner.nextLine();
            switch (userInput) {
                case "update truck": {
                    updateTruck(scanner, systemFacade);
                    break;
                }
                case "update driver": {
                    updateDriver(scanner, systemFacade);
                    break;
                }
                case "add stop": {
                    addStop(scanner, systemFacade);
                    break;
                }
                case "remove stop": {
                    removeStop(scanner, systemFacade);
                    break;
                }
                case "edit stop arrive time": {
                    editStopArriveTime(scanner, systemFacade);
                    break;
                }
                case "add item": {
                    addItem(scanner, systemFacade);
                    break;
                }
                case "remove item": {
                    removeItem(scanner, systemFacade);
                    break;
                }
                case "back":
                    return;
                default:
                    System.out.println("Invalid command");
            }
        }
    }

    private static void updateTruck(Scanner scanner, SystemFacade systemFacade) {
        while (true) {
            System.out.println("You are in the delivery truck update menu please type delivery id and truck id in the format: [delivery id,truck id] where delivery id and truck id are numbers or type back");
            String userInput = scanner.nextLine();
            if (!userInput.equals("back")) {
                String[] split = userInput.split(",");
                if (split.length != 2) {
                    System.out.println("Wrong format");
                    continue;
                }
                try {
                    System.out.println(systemFacade.updateDeliveryTruck(Integer.parseInt(split[0]), Integer.parseInt(split[1])));
                    continue;
                } catch (Exception e) {
                    System.out.println("Wrong format");
                    continue;
                }
            }
            return;
        }
    }

    private static void updateDriver(Scanner scanner, SystemFacade systemFacade) {
        while (true) {
            System.out.println("You are in the delivery driver update menu please type delivery id and driver id in the format: [delivery id,driver id] where delivery id is a number or type back");
            String userInput = scanner.nextLine();
            if (!userInput.equals("back")) {
                String[] split = userInput.split(",");
                if (split.length != 2) {
                    System.out.println("Wrong format");
                    continue;
                }
                try {
                    System.out.println(systemFacade.updateDeliveryDriver(Integer.parseInt(split[0]), split[1]));
                    continue;
                } catch (Exception e) {
                    System.out.println("Wrong format");
                    continue;
                }
            }
            return;
        }
    }

    private static void addStop(Scanner scanner, SystemFacade systemFacade) {
        while (true) {
            System.out.println("You are in the delivery stop adding menu please type delivery id and stop address in the format: [delivery id,address] where delivery id is a number or type back");
            String userInput = scanner.nextLine();
            if (!userInput.equals("back")) {
                String[] split = userInput.split(",");
                if (split.length != 2) {
                    System.out.println("Wrong format");
                    continue;
                }
                try {
                    System.out.println(systemFacade.addStopToDelivery(Integer.parseInt(split[0]), split[1]));
                    continue;
                } catch (Exception e) {
                    System.out.println("Wrong format");
                    continue;
                }
            }
            return;
        }
    }

    private static void removeStop(Scanner scanner, SystemFacade systemFacade) {
        while (true) {
            System.out.println("You are in the delivery stop remove menu please type delivery id and stop address in the format: [delivery id,address] where delivery id is a number or type back");
            String userInput = scanner.nextLine();
            if (!userInput.equals("back")) {
                String[] split = userInput.split(",");
                if (split.length != 2) {
                    System.out.println("Wrong format");
                    continue;
                }
                try {
                    System.out.println(systemFacade.removeStopFromDelivery(Integer.parseInt(split[0]), split[1]));
                    continue;
                } catch (Exception e) {
                    System.out.println("Wrong format");
                    continue;
                }
            }
            return;
        }
    }

    private static void editStopArriveTime(Scanner scanner, SystemFacade systemFacade) {
        while (true) {
            System.out.println("You are in the delivery stop edit arrive time menu please type delivery id, stop address and arrive time in the format: [delivery id,address, HH:MM] where delivery id is a number and time is or type back");
            String userInput = scanner.nextLine();
            if (!userInput.equals("back")) {
                String[] split = userInput.split(",");
                if (split.length != 3) {
                    System.out.println("Wrong format");
                    continue;
                }
                String[] time = split[2].split(":");
                if (time.length != 2) {
                    System.out.println("Wrong format");
                    continue;
                }
                try {
                    System.out.println(systemFacade.updateArriveTimeToStop(Integer.parseInt(split[0]),split[1],LocalTime.of(Integer.parseInt(time[0]),Integer.parseInt(time[1]))));
                    continue;
                } catch (Exception e) {
                    System.out.println("Wrong format");
                    continue;
                }
            }
            return;
        }
    }

    private static void addItem(Scanner scanner, SystemFacade systemFacade) {
        while (true) {
            System.out.println("You are in the delivery item adding menu please type delivery id, item order id, direct delivery or not, what stage of split delivery it is in the format: [delivery id,item order id, direct delivery, delivery stage] where delivery id, item order id are numbers, delivery stage is 0 for direct or to logic center deliveries and 1 if delivery is from logistic center to store and direct delivery is true or false or type back");
            String userInput = scanner.nextLine();
            if (!userInput.equals("back")) {
                String[] split = userInput.split(",");
                if (split.length != 4) {
                    System.out.println("Wrong format");
                    continue;
                }
                try {
                    System.out.println(systemFacade.addItemToDelivery(Integer.parseInt(split[0]), Integer.parseInt(split[1]), Boolean.parseBoolean(split[2]), Integer.parseInt(split[3])));
                    continue;
                } catch (Exception e) {
                    System.out.println("Wrong format");
                    continue;
                }
            }
            return;
        }
    }

    private static void removeItem(Scanner scanner, SystemFacade systemFacade) {
        while (true) {
            System.out.println("You are in the delivery item remove menu please type delivery id, item order id in the format: [delivery id,item order id] where delivery id and item order id are numbers or type back");
            String userInput = scanner.nextLine();
            if (!userInput.equals("back")) {
                String[] split = userInput.split(",");
                if (split.length != 2) {
                    System.out.println("Wrong format");
                    continue;
                }
                try {
                    System.out.println(systemFacade.removeItemFromDelivery(Integer.parseInt(split[0]), Integer.parseInt(split[1])));
                    continue;
                } catch (Exception e) {
                    System.out.println("Wrong format");
                    continue;
                }
            }
            return;
        }
    }
    //endregion

    //region Delivery progression functions
    private static void progressDelivery(Scanner scanner, SystemFacade systemFacade) {
        while (true) {
            System.out.println("You are in the delivery management menu please type in one of the following actions: approve delivery, visit stop, handle overweight or back");
            String userInput = scanner.nextLine();
            switch (userInput) {
                case "approve delivery": {
                    approveDelivery(scanner, systemFacade);
                    break;
                }
                case "visit stop": {
                    visitStop(scanner, systemFacade);
                    break;
                }
                case "handle overweight": {
                    handleOverweight(scanner, systemFacade);
                    break;
                }
                case "back":
                    return;
                default:
                    System.out.println("Invalid command");
            }
        }
    }

    private static void approveDelivery(Scanner scanner, SystemFacade systemFacade) {
        while (true) {
            System.out.println("You are in the delivery approval menu please type delivery id (a number) or type back");
            String userInput = scanner.nextLine();
            if (!userInput.equals("back")) {
                try {
                    System.out.println(systemFacade.approveDelivery(Integer.parseInt(userInput)));
                    continue;
                } catch (Exception e) {
                    System.out.println("Wrong format");
                    continue;
                }
            }
            return;
        }
    }

    private static void visitStop(Scanner scanner, SystemFacade systemFacade) {
        while (true) {
            System.out.println("You are in the delivery stop visit menu please type delivery id, stop address and weight in the format: [delivery id,address,weight] where delivery id is a number and weight is in kilograms or type back");
            String userInput = scanner.nextLine();
            if (!userInput.equals("back")) {
                String[] split = userInput.split(",");
                if (split.length != 3) {
                    System.out.println("Wrong format");
                    continue;
                }
                try {
                    System.out.println(systemFacade.visitStop(Integer.parseInt(split[0]), split[1], Float.parseFloat(split[2])));
                    continue;
                } catch (Exception e) {
                    System.out.println("Wrong format");
                    continue;
                }
            }
            return;
        }
    }

    private static void handleOverweight(Scanner scanner, SystemFacade systemFacade) {
        while (true) {
            System.out.println("You are in the delivery overweight handling menu please type in one of the following actions: switch truck, switch truck and driver, skip stop load, leave item or back");
            String userInput = scanner.nextLine();
            switch (userInput) {
                case "switch truck": {
                    switchTruck(scanner, systemFacade);
                    break;
                }
                case "switch truck and driver": {
                    switchTruckDriver(scanner, systemFacade);
                    break;
                }
                case "skip stop load": {
                    skipStopLoad(scanner, systemFacade);
                    break;
                }
                case "leave item": {
                    leaveItem(scanner, systemFacade);
                    break;
                }
                case "back":
                    return;
                default:
                    System.out.println("Invalid command");
            }
        }
    }

    private static void switchTruck(Scanner scanner, SystemFacade systemFacade) {
        while (true) {
            System.out.println("You are in the delivery truck switch menu please type delivery id and new truck id and current total weight in the format: [delivery id,truck id,weight] where delivery id and truck id are numbers and weight is in kilograms or type back");
            String userInput = scanner.nextLine();
            if (!userInput.equals("back")) {
                String[] split = userInput.split(",");
                if (split.length != 3) {
                    System.out.println("Wrong format");
                    continue;
                }
                try {
                    System.out.println(systemFacade.switchTruck(Integer.parseInt(split[0]), Integer.parseInt(split[1]), Float.parseFloat(split[2])));
                    continue;
                } catch (Exception e) {
                    System.out.println("Wrong format");
                    continue;
                }
            }
            return;
        }
    }

    private static void switchTruckDriver(Scanner scanner, SystemFacade systemFacade) {
        while (true) {
            System.out.println("You are in the delivery truck and driver switch menu please type delivery id, new truck id, new driver id and current total weight in the format: [delivery id,truck id,driver id,weight] where delivery id and truck id are numbers and weight is in kilograms or type back");
            String userInput = scanner.nextLine();
            if (!userInput.equals("back")) {
                String[] split = userInput.split(",");
                if (split.length != 4) {
                    System.out.println("Wrong format");
                    continue;
                }
                try {
                    System.out.println(systemFacade.switchTruckDriver(Integer.parseInt(split[0]), Integer.parseInt(split[1]), split[2], Float.parseFloat(split[3])));
                    continue;
                } catch (Exception e) {
                    System.out.println("Wrong format");
                    continue;
                }
            }
            return;
        }
    }

    private static void skipStopLoad(Scanner scanner, SystemFacade systemFacade) {
        while (true) {
            System.out.println("You are in the delivery stop loading skip menu please type delivery id (a number) or type back");
            String userInput = scanner.nextLine();
            if (!userInput.equals("back")) {
                try {
                    System.out.println(systemFacade.skipStopLoad(Integer.parseInt(userInput)));
                    continue;
                } catch (Exception e) {
                    System.out.println("Wrong format");
                    continue;
                }
            }
            return;
        }
    }

    private static void leaveItem(Scanner scanner, SystemFacade systemFacade) {
        while (true) {
            System.out.println("You are in the delivery item change menu please type delivery id and item order id in the format: [delivery id,item order id] where delivery id and item order id are numbers or type back");
            String userInput = scanner.nextLine();
            if (!userInput.equals("back")) {
                String[] split = userInput.split(",");
                if (split.length != 2) {
                    System.out.println("Wrong format");
                    continue;
                }
                try {
                    System.out.println(systemFacade.leaveItem(Integer.parseInt(split[0]), Integer.parseInt(split[1])));
                    continue;
                } catch (Exception e) {
                    System.out.println("Wrong format");
                    continue;
                }
            }
            return;
        }
    }

    private static void showDelivery(Scanner scanner, SystemFacade systemFacade) {
        while (true) {
            System.out.println("You are in the delivery show menu please type in delivery id or type back");
            String userInput = scanner.nextLine();
            if (!userInput.equals("back")) {
                try {
                    System.out.println(systemFacade.showDelivery(Integer.parseInt(userInput)));
                    continue;
                } catch (Exception e) {
                    System.out.println("Wrong format");
                    continue;
                }
            }
            return;
        }
    }

    private static void showDeliveryOrder(Scanner scanner, SystemFacade systemFacade) {
        while (true) {
            System.out.println("You are in the delivery order show menu please type in delivery order id or type back");
            String userInput = scanner.nextLine();
            if (!userInput.equals("back")) {
                try {
                    System.out.println(systemFacade.showDeliveryOrder(Integer.parseInt(userInput)));
                    continue;
                } catch (Exception e) {
                    System.out.println("Wrong format");
                    continue;
                }
            }
            return;
        }
    }

    private static void cancelDelivery(Scanner scanner, SystemFacade systemFacade) {
        while (true) {
            System.out.println("You are in the delivery cancel menu please type in delivery id (a number) or type back");
            String userInput = scanner.nextLine();
            if (!userInput.equals("back")) {
                try {
                    System.out.println(systemFacade.cancelDelivery(Integer.parseInt(userInput)));
                    continue;
                } catch (Exception e) {
                    System.out.println("Wrong format");
                    continue;
                }
            }
            return;
        }
    }
    //endregion

    //endregion
}
