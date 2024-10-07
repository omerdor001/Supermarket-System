package Tests;

import ServiceLayer.SystemFacade;
import BusinessLayer.Delivery.*;
import ServiceLayer.JsonService.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class DeliveryServiceTest {
    SystemFacade systemFacade;

    @BeforeEach
    void setUp() {
        systemFacade = SystemFacade.getInstance();
        systemFacade.DeleteInstance();
        systemFacade = SystemFacade.getInstance();
    }

    @AfterEach
    void setDown() {
        systemFacade.DeleteInstance();
    }

    void makeDeliveryOrder() {
        LocalTime startMHour = LocalTime.of(8, 0);
        LocalTime endMHour = LocalTime.of(13, 0);
        LocalTime startEHour = LocalTime.of(13, 0);
        LocalTime endEHour = LocalTime.of(22, 0);
        systemFacade.addBranch("Beer Sheva", "0525381648", "Anna Zack", 1, startMHour, startEHour, endMHour, endEHour, 31.25181, 34.7913);
        systemFacade.addBranch("Tel Aviv", "0501234567", "Noa Kirel", 1, startMHour, startEHour, endMHour, endEHour, 32.109333, 34.855499);
        systemFacade.addSite("Ashdod", "0541010101", "mr. wow", 3, 2, 31.801447, 34.643497);
        systemFacade.addTruck(6666, "reg", "mazda", 1500, 4000);
        systemFacade.registerDriver("777888777", "kfir", "nissim", "Kn123456", 45, 67, 30, "aaaaaaa", "aaaaaaa", LocalDateTime.of(2023, 1, 1, 1, 1), "0548929158",  15000);
        systemFacade.registerBranchEmployee("666666666", "bbb", "bbb", "Bb222222", 45, 12, 30, "bbb", "bbbbbb", LocalDateTime.of(2022, 1, 1, 1, 1), "0533719572", false, false);
        systemFacade.insertRole("666666666","store keeper");
        systemFacade.registerBranchEmployee("777777777", "bbb", "bbb", "Bb222222", 45, 12, 30, "bbb", "bbbbbb", LocalDateTime.of(2022, 1, 1, 1, 1), "0544279391", false, false);
        systemFacade.insertRole("777777777","store keeper");
        systemFacade.registerBranchEmployee("888888888", "bbb", "bbb", "Bb222222", 45, 12, 30, "bbb", "bbbbbb", LocalDateTime.of(2022, 1, 1, 1, 1), "0533719572", false, false);
        systemFacade.insertRole("888888888","store keeper");
        systemFacade.registerBranchEmployee("999999999", "bbb", "bbb", "Bb222222", 45, 12, 30, "bbb", "bbbbbb", LocalDateTime.of(2022, 1, 1, 1, 1), "0544279391", false, false);
        systemFacade.insertRole("999999999","store keeper");
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

        systemFacade.insertTimeOfShift("Tel Aviv", "TUESDAY", "E");
        systemFacade.insertTimeOfShift("Tel Aviv", "TUESDAY", "M");
        systemFacade.insertTimeOfShift("Tel Aviv", "FRIDAY", "E");
        systemFacade.insertTimeOfShift("Tel Aviv", "FRIDAY", "M");
        systemFacade.insertTimeOfShift("Tel Aviv", "SATURDAY", "E");
        systemFacade.insertTimeOfShift("Tel Aviv", "SATURDAY", "M");
        systemFacade.insertTimeOfShift("Tel Aviv", "SUNDAY", "E");
        systemFacade.insertTimeOfShift("Tel Aviv", "SUNDAY", "M");
        systemFacade.insertTimeOfShift("Tel Aviv", "MONDAY", "E");
        systemFacade.insertTimeOfShift("Tel Aviv", "MONDAY", "M");
        systemFacade.insertTimeOfShift("Tel Aviv", "THURSDAY", "E");
        systemFacade.insertTimeOfShift("Tel Aviv", "THURSDAY", "M");
        systemFacade.insertTimeOfShift("Tel Aviv", "WEDNESDAY", "E");
        systemFacade.insertTimeOfShift("Tel Aviv", "WEDNESDAY", "M");
        systemFacade.addBranchShift(LocalDate.of(2023, 5, 13).atTime(startMHour), 5, 5, 5, 5, 5, 5, 5, "Beer Sheva","M");
        systemFacade.addBranchShift(LocalDate.of(2023, 5, 13).atTime(startEHour), 5, 5, 5, 5, 5, 5, 5, "Beer Sheva","E");
        systemFacade.addBranchShift(LocalDate.of(2023, 5, 13).atTime(startMHour), 5, 5, 5, 5, 5, 5, 5, "Tel Aviv","M");
        systemFacade.addBranchShift(LocalDate.of(2023, 5, 13).atTime(startEHour), 5, 5, 5, 5, 5, 5, 5, "Tel Aviv","E");
        systemFacade.addEmployeeToSchedule("666666666", 0);
        systemFacade.scheduleEmployeeToRole("666666666", 0, "store keeper");
        systemFacade.addEmployeeToSchedule("777777777", 1);
        systemFacade.scheduleEmployeeToRole("777777777", 1, "store keeper");
        systemFacade.addEmployeeToSchedule("888888888", 2);
        systemFacade.scheduleEmployeeToRole("888888888", 2, "store keeper");
        systemFacade.addEmployeeToSchedule("999999999", 3);
        systemFacade.scheduleEmployeeToRole("999999999", 3, "store keeper");
        systemFacade.addDriverShift(LocalDate.of(2023, 5, 13).atTime(startEHour), 4);
        systemFacade.addBranchShift(LocalDate.of(2023, 5, 14).atTime(startMHour), 5, 5, 5, 5, 5, 5, 5, "Beer Sheva","M");
        systemFacade.addBranchShift(LocalDate.of(2023, 5, 14).atTime(startEHour), 5, 5, 5, 5, 5, 5, 5, "Beer Sheva","E");
        systemFacade.addBranchShift(LocalDate.of(2023, 5, 14).atTime(startMHour), 5, 5, 5, 5, 5, 5, 5, "Tel Aviv","M");
        systemFacade.addBranchShift(LocalDate.of(2023, 5, 14).atTime(startEHour), 5, 5, 5, 5, 5, 5, 5, "Tel Aviv","E");
        systemFacade.addEmployeeToSchedule("666666666", 5);
        systemFacade.scheduleEmployeeToRole("666666666", 5, "store keeper");
        systemFacade.addEmployeeToSchedule("777777777", 6);
        systemFacade.scheduleEmployeeToRole("777777777", 6, "store keeper");
        systemFacade.addEmployeeToSchedule("888888888", 7);
        systemFacade.scheduleEmployeeToRole("888888888", 7, "store keeper");
        systemFacade.addEmployeeToSchedule("999999999", 8);
        systemFacade.scheduleEmployeeToRole("999999999", 8, "store keeper");
        systemFacade.addDriverShift(LocalDate.of(2023, 5, 14).atTime(startEHour), 4);
        ItemOrder o1 = systemFacade.getDeliveryService().getDeliveryController().addItemOrderToSystem(111, 0, "Beer Sheva", "Tel Aviv", 20, false);
        ItemOrder o2 = systemFacade.getDeliveryService().getDeliveryController().addItemOrderToSystem(222, 0, "Beer Sheva", "Tel Aviv", 30, false);
        HashMap<Integer, ItemOrder> loadList = new HashMap<>();
        loadList.put(0, o1);
        loadList.put(1, o2);
        systemFacade.getDeliveryService().getDeliveryController().addDeliveryOrder(65, "Tel Aviv", "Beer Sheva", loadList);
    }

    @Test
    void addTruckSuccess() {
        String ans = systemFacade.addTruck(1234, "reg", "mazda", 1500, 4000);
        assertEquals("Truck added successfully", ans);
    }

    @Test
    void addTruckIsExist() {
        systemFacade.addTruck(1234, "reg", "mazda", 1500, 4000);
        String ans = systemFacade.addTruck(1234, "reg", "mazda", 1500, 4000);
        assertEquals("truck's ID already exists", ans);
    }

    @Test
    void addTruckIllegalNetWeight() {
        String ans = systemFacade.addTruck(1234, "reg", "mazda", 0, 4000);
        assertEquals("netWeight is illegal", ans);
    }

    @Test
    void addTruckNetWeightIsBigger() {
        String ans = systemFacade.addTruck(1234, "reg", "mazda", 1500, 1000);
        assertEquals("maxWeight must be bigger than netWeight", ans);
    }

    @Test
    void removeTruckSuccess() {
        systemFacade.addTruck(1234, "reg", "mazda", 1500, 4000);
        String ans = systemFacade.removeTruck(1234);
        assertEquals("Truck removed successfully", ans);
    }

    @Test
    void removeTruckDoesNotExist() {
        String ans = systemFacade.removeTruck(1234);
        assertEquals("truck doesn't exist", ans);
    }

    @Test
    void updateTruckStatusSuccess() {
        systemFacade.addTruck(1234, "reg", "mazda", 1500, 4000);
        String ans = systemFacade.updateTruckStatus(1234, 1);
        assertEquals("Truck status updated successfully", ans);
        assertEquals(1, systemFacade.getDeliveryService().getResourceController().getTruck(1234).getStatusOrdinal());
    }

    @Test
    void updateTruckStatusIllegalStatus() {
        systemFacade.addTruck(1234, "reg", "mazda", 1500, 4000);
        String ans = systemFacade.updateTruckStatus(1234, 3);
        assertEquals("status is illegal", ans);
    }

    @Test
    void addTruckDeliveryDateSuccess() {
        systemFacade.addTruck(1234, "reg", "mazda", 1500, 4000);
        String ans = systemFacade.addTruckDeliveryDate(1234, LocalDate.of(2023, 5, 13));
        assertEquals("Truck delivery date added successfully", ans);
    }

    @Test
    void addTruckDeliveryDateIsExist() {
        systemFacade.addTruck(1234, "reg", "mazda", 1500, 4000);
        systemFacade.addTruckDeliveryDate(1234, LocalDate.of(2023, 5, 13));
        String ans = systemFacade.addTruckDeliveryDate(1234, LocalDate.of(2023, 5, 13));
        assertEquals("deliveryDate is already exist", ans);
    }

    @Test
    void removeTruckDeliveryDateSuccess() {
        systemFacade.addTruck(1234, "reg", "mazda", 1500, 4000);
        systemFacade.addTruckDeliveryDate(1234, LocalDate.of(2023, 5, 13));
        String ans = systemFacade.removeTruckDeliveryDate(1234, LocalDate.of(2023, 5, 13));
        assertEquals("Truck delivery date removed successfully", ans);
    }

    @Test
    void removeTruckDeliveryDateDoesNotExist() {
        systemFacade.addTruck(1234, "reg", "mazda", 1500, 4000);
        String ans = systemFacade.removeTruckDeliveryDate(1234, LocalDate.of(2023, 5, 13));
        assertEquals("deliveryDate doesn't exist", ans);
    }

    @Test
    void showTruck() {
        systemFacade.addTruck(1234, "reg", "mazda", 1500, 4000);
        String ans = systemFacade.showTruck(1234);
        ans = ans.replaceAll("\\r\\n?", "");
        ans = ans.replaceAll("\\r", "\n");
        assertEquals("{  \"id\" : 1234,  \"type\" : \"reg\",  \"model\" : \"mazda\",  \"netWeight\" : 1500.0,  \"maxWeight\" : 4000.0,  \"deliveryDates\" : [ ],  \"status\" : \"AVAILABLE\",  \"statusOrdinal\" : 1}", ans);
    }

    @Test
    void showAllTrucks() {
        systemFacade.addTruck(1234, "reg", "mazda", 1500, 4000);
        String ans = systemFacade.showAllTrucks();
        ans = ans.replaceAll("\\r\\n?", "");
        ans = ans.replaceAll("\\r", "\n");
        assertEquals("{  \"1234\" : {    \"id\" : 1234,    \"type\" : \"reg\",    \"model\" : \"mazda\",    \"netWeight\" : 1500.0,    \"maxWeight\" : 4000.0,    \"deliveryDates\" : [ ],    \"status\" : \"AVAILABLE\",    \"statusOrdinal\" : 1  }}", ans);
    }

    @Test
    void addSiteSuccess() {
        String ans = systemFacade.addSite("Beer Sheva", "0525381648", "Anna Zack", 1, 1, 31.25181, 34.7913);
        assertEquals("Site created successfully", ans);
    }

    @Test
    void addSiteSuccessIsExist() {
        systemFacade.addSite("Beer Sheva", "0525381648", "Anna Zack", 1, 1, 31.25181, 34.7913);
        String ans = systemFacade.addSite("Beer Sheva", "0525381648", "Anna Zack", 1, 1, 31.25181, 34.7913);
        assertEquals("the address exists", ans);
    }

    @Test
    void addSiteRegionIsIllegal() {
        String ans = systemFacade.addSite("Beer Sheva", "0525381648", "Anna Zack", 5, 1, 31.25181, 34.7913);
        assertEquals("region is illegal", ans);
    }

    @Test
    void addSiteTypeIsIllegal() {
        String ans = systemFacade.addSite("Beer Sheva", "0525381648", "Anna Zack", 1, 5, 31.25181, 34.7913);
        assertEquals("type is illegal", ans);
    }

    @Test
    void removeSiteSuccess() {
        systemFacade.addSite("Beer Sheva", "0525381648", "Anna Zack", 1, 1, 31.25181, 34.7913);
        String ans = systemFacade.removeSite("Beer Sheva");
        assertEquals("Site removed successfully", ans);
    }

    @Test
    void removeSiteDoesNotExist() {
        String ans = systemFacade.removeSite("Beer Sheva");
        assertEquals("Site does not exist", ans);
    }

    @Test
    void showSite() {
        systemFacade.addSite("Beer Sheva", "0525381648", "Anna Zack", 1, 1, 31.25181, 34.7913);
        String ans = systemFacade.showSite("Beer Sheva");
        ans = ans.replaceAll("\\r\\n?", "");
        ans = ans.replaceAll("\\r", "\n");
        assertEquals("{  \"region\" : \"SOUTH\",  \"type\" : \"SUPPLIER\",  \"address\" : \"Beer Sheva\",  \"phoneNumber\" : \"0525381648\",  \"contactName\" : \"Anna Zack\",  \"coordinate\" : {    \"latitude\" : 31.25181,    \"longitude\" : 34.7913  }}", ans);
    }

    @Test
    void showAllSites() {
        systemFacade.addSite("Beer Sheva", "0525381648", "Anna Zack", 1, 1, 31.25181, 34.7913);
        String ans = systemFacade.showAllSites();
        ans = ans.replaceAll("\\r\\n?", "");
        ans = ans.replaceAll("\\r", "\n");
        assertEquals("{  \"Beer Sheva\" : {    \"region\" : \"SOUTH\",    \"type\" : \"SUPPLIER\",    \"address\" : \"Beer Sheva\",    \"phoneNumber\" : \"0525381648\",    \"contactName\" : \"Anna Zack\",    \"coordinate\" : {      \"latitude\" : 31.25181,      \"longitude\" : 34.7913    }  }}", ans);
    }

    @Test
    void showDeliveryOrder() {
        makeDeliveryOrder();
        String ans = systemFacade.showDeliveryOrder(0);
        ans = ans.replaceAll("\\r\\n?", "");
        ans = ans.replaceAll("\\r", "\n");
        assertEquals("{  \"id\" : 0,  \"destination\" : \"Tel Aviv\",  \"source\" : \"Beer Sheva\",  \"totalLoadWeight\" : 65.0,  \"items\" : {    \"0\" : {      \"id\" : 0,      \"itemId\" : 111,      \"deliveryOrderId\" : 0,      \"source\" : \"Beer Sheva\",      \"destination\" : \"Tel Aviv\",      \"quantity\" : 20,      \"refrigeration\" : false,      \"status\" : \"PENDING\",      \"itemDelivery1\" : -1,      \"itemDelivery2\" : -1,      \"statusOrdinal\" : 0    },    \"1\" : {      \"id\" : 1,      \"itemId\" : 222,      \"deliveryOrderId\" : 0,      \"source\" : \"Beer Sheva\",      \"destination\" : \"Tel Aviv\",      \"quantity\" : 30,      \"refrigeration\" : false,      \"status\" : \"PENDING\",      \"itemDelivery1\" : -1,      \"itemDelivery2\" : -1,      \"statusOrdinal\" : 0    }  },  \"status\" : \"PENDING\",  \"statusOrdinal\" : 0}", ans);
    }

    @Test
    void parseOrder() throws JsonProcessingException {
        DeliveryOrder.Triplet<String, Integer, Double> item0 = new DeliveryOrder.Triplet<>("000-000", 15, 0.25);
        DeliveryOrder.Triplet<String, Integer, Double> item1 = new DeliveryOrder.Triplet<>("111-111", 30, 0.2);
        LinkedList<DeliveryOrder.Triplet<String, Integer, Double>> list1 = new LinkedList<>();
        list1.add(item0);
        list1.add(item1);
        DeliveryOrder.Shipment ship1 = new DeliveryOrder.Shipment("13/04/2023", "Tel Aviv", "Beer Sheva", "Avi", "Moshe", "0501111111", "0502222222", list1);
        systemFacade.addSite("Tel Aviv", "0501234567", "Noa Kirel", 1, 0, 32.109333, 34.855499);
        systemFacade.addSite("Beer Sheva", "0525381648", "Anna Zack", 1, 1, 31.25181, 34.7913);
        String ans = systemFacade.parseOrder(JsonConverter.toJson(ship1));
        assertEquals("PresentationLayer.GUI.Delivery order created successfully", ans);
    }

    @Test
    void addDeliverySuccess() {
        systemFacade.addSite("Beer Sheva", "0525381648", "Anna Zack", 1, 1, 31.25181, 34.7913);
        systemFacade.addTruck(6666, "reg", "mazda", 1500, 4000);
        systemFacade.registerDriver("777888777", "kfir", "nissim", "Kn123456", 45, 67, 30, "aaaaaaa", "aaaaaaa", LocalDateTime.of(2023, 1, 1, 1, 1), "0548929158",  15000);
        String ans = systemFacade.addDelivery(LocalDate.of(2023, 5, 13), LocalTime.of(11, 0), 6666, "777888777", "Beer Sheva");
        assertEquals("PresentationLayer.GUI.Delivery created successfully", ans);
    }

    @Test
    void addDeliverySiteDoesNotExist() {
        systemFacade.addTruck(6666, "reg", "mazda", 1500, 4000);
        systemFacade.registerDriver("777888777", "kfir", "nissim", "Kn123456", 45, 67, 30, "aaaaaaa", "aaaaaaa", LocalDateTime.of(2023, 1, 1, 1, 1), "0548929158",  15000);
        String ans = systemFacade.addDelivery(LocalDate.of(2023, 5, 13), LocalTime.of(11, 0), 6666, "777888777", "Beer Sheva");
        assertEquals("the address doesn't exist", ans);
    }

    @Test
    void addDeliveryDriverDoesNotExist() {
        systemFacade.addSite("Beer Sheva", "0525381648", "Anna Zack", 1, 1, 31.25181, 34.7913);
        systemFacade.addTruck(6666, "reg", "mazda", 1500, 4000);
        String ans = systemFacade.addDelivery(LocalDate.of(2023, 5, 13), LocalTime.of(11, 0), 6666, "777888777", "Beer Sheva");
        assertEquals("Driver doesn't exist", ans);
    }

    @Test
    void addDeliveryTruckDoesNotExist() {
        systemFacade.addSite("Beer Sheva", "0525381648", "Anna Zack", 1, 1, 31.25181, 34.7913);
        systemFacade.registerDriver("777888777", "kfir", "nissim", "Kn123456", 45, 67, 30, "aaaaaaa", "aaaaaaa", LocalDateTime.of(2023, 1, 1, 1, 1), "0548929158",  15000);
        String ans = systemFacade.addDelivery(LocalDate.of(2023, 5, 13), LocalTime.of(11, 0), 6666, "777888777", "Beer Sheva");
        assertEquals("truck doesn't exist", ans);
    }

    @Test
    void updateDriverSuccess() {
        systemFacade.addSite("Beer Sheva", "0525381648", "Anna Zack", 1, 1, 31.25181, 34.7913);
        systemFacade.addTruck(6666, "reg", "mazda", 1500, 4000);
        systemFacade.registerDriver("777888777", "kfir", "nissim", "Kn123456", 45, 67, 30, "aaaaaaa", "aaaaaaa", LocalDateTime.of(2023, 1, 1, 1, 1), "0548929158",  15000);
        systemFacade.registerDriver("888777888", "Martin", "Koroktov", "Mk5349875", 67, 45, 30, "aaaaa", "ggggg", LocalDateTime.of(2023, 1, 1, 1, 1), "0548929158",  15000);
        systemFacade.addDelivery(LocalDate.of(2023, 5, 13), LocalTime.of(11, 0), 6666, "777888777", "Beer Sheva");
        String ans = systemFacade.updateDeliveryDriver(0, "888777888");
        assertEquals("PresentationLayer.GUI.Delivery driver updated successfully", ans);
        assertEquals("888777888", systemFacade.getDeliveryService().getDeliveryController().getDeliveries().get(0).getDriverId());
    }

    @Test
    void updateDriverDeliveryDoesNotExist() {
        systemFacade.addSite("Beer Sheva", "0525381648", "Anna Zack", 1, 1, 31.25181, 34.7913);
        systemFacade.addTruck(6666, "reg", "mazda", 1500, 4000);
        systemFacade.registerDriver("777888777", "kfir", "nissim", "Kn123456", 45, 67, 30, "aaaaaaa", "aaaaaaa", LocalDateTime.of(2023, 1, 1, 1, 1), "0548929158",  15000);
        systemFacade.registerDriver("888777888", "Martin", "Koroktov", "Mk5349875", 67, 45, 30, "aaaaa", "ggggg", LocalDateTime.of(2023, 1, 1, 1, 1), "0557395721",  15000);
        String ans = systemFacade.updateDeliveryDriver(0, "888777888");
        assertEquals("delivery doesn't exist", ans);
    }

    @Test
    void updateDriverDriverDoesNotExist() {
        systemFacade.addSite("Beer Sheva", "0525381648", "Anna Zack", 1, 1, 31.25181, 34.7913);
        systemFacade.addTruck(6666, "reg", "mazda", 1500, 4000);
        systemFacade.registerDriver("777888777", "kfir", "nissim", "Kn123456", 45, 67, 30, "aaaaaaa", "aaaaaaa", LocalDateTime.of(2023, 1, 1, 1, 1), "0548929158",  15000);
        systemFacade.addDelivery(LocalDate.of(2023, 5, 13), LocalTime.of(11, 0), 6666, "777888777", "Rager 1 Beer Sheva");
        String ans = systemFacade.updateDeliveryDriver(0, "77788877");
        assertEquals("Driver doesn't exist", ans);
    }


    @Test
    void updateTruckSuccess() {
        systemFacade.addSite("Beer Sheva", "0525381648", "Anna Zack", 1, 1, 31.25181, 34.7913);
        systemFacade.addTruck(6666, "reg", "mazda", 1500, 4000);
        systemFacade.addTruck(7777, "refrigeration", "honda", 1000, 3000);
        systemFacade.registerDriver("777888777", "kfir", "nissim", "Kn123456", 45, 67, 30, "aaaaaaa", "aaaaaaa", LocalDateTime.of(2023, 1, 1, 1, 1), "0548929158",  15000);
        systemFacade.addDelivery(LocalDate.of(2023, 5, 13), LocalTime.of(11, 0), 6666, "777888777", "Beer Sheva");
        String ans = systemFacade.updateDeliveryTruck(0, 7777);
        assertEquals("PresentationLayer.GUI.Delivery truck updated successfully", ans);
        assertEquals(7777, systemFacade.getDeliveryService().getDeliveryController().getDeliveries().get(0).getTruckId());
    }

    @Test
    void updateTruckTruckDoesNotExist() {
        systemFacade.addSite("Beer Sheva", "0525381648", "Anna Zack", 1, 1, 31.25181, 34.7913);
        systemFacade.addTruck(6666, "reg", "mazda", 1500, 4000);
        systemFacade.registerDriver("777888777", "kfir", "nissim", "Kn123456", 45, 67, 30, "aaaaaaa", "aaaaaaa", LocalDateTime.of(2023, 1, 1, 1, 1), "0548929158",  15000);
        systemFacade.addDelivery(LocalDate.of(2023, 5, 13), LocalTime.of(11, 0), 6666, "777888777", "Rager 1 Beer Sheva");
        String ans = systemFacade.updateDeliveryTruck(0, 7777);
        assertEquals("truck doesn't exist", ans);
    }

    @Test
    void addStopToDeliverySuccess1() {
        systemFacade.addSite("Beer Sheva", "0525381648", "Anna Zack", 1, 1, 31.25181, 34.7913);
        systemFacade.addSite("Tel Aviv", "0501234567", "Noa Kirel", 1, 0, 32.109333, 34.855499);
        systemFacade.addTruck(6666, "reg", "mazda", 1500, 4000);
        systemFacade.registerDriver("777888777", "kfir", "nissim", "Kn123456", 45, 67, 30, "aaaaaaa", "aaaaaaa", LocalDateTime.of(2023, 1, 1, 1, 1), "0548929158",  15000);
        systemFacade.addDelivery(LocalDate.of(2023, 5, 13), LocalTime.of(11, 0), 6666, "777888777", "Beer Sheva");
        String ans = systemFacade.addStopToDelivery(0, "Tel Aviv");
        assertEquals("PresentationLayer.GUI.Delivery stop added successfully", ans);
    }

    @Test
    void addStopToDeliverySuccess2() {
        systemFacade.addSite("Beer Sheva", "0525381648", "Anna Zack", 1, 1, 31.25181, 34.7913);
        systemFacade.addSite("Tel Aviv", "0501234567", "Noa Kirel", 1, 0, 32.109333, 34.855499);
        systemFacade.addTruck(6666, "reg", "mazda", 1500, 4000);
        systemFacade.registerDriver("777888777", "kfir", "nissim", "Kn123456", 45, 67, 30, "aaaaaaa", "aaaaaaa", LocalDateTime.of(2023, 1, 1, 1, 1), "0548929158",  15000);
        systemFacade.addDelivery(LocalDate.of(2023, 5, 13), LocalTime.of(11, 0), 6666, "777888777", "Beer Sheva");
        systemFacade.addStopToDelivery(0, "Tel Aviv");
        String ans = systemFacade.addStopToDelivery(0, "Beer Sheva");
        assertEquals("PresentationLayer.GUI.Delivery stop added successfully", ans);
    }

    @Test
    void addStopToDeliveryNoStopsBetween() {
        systemFacade.addSite("Beer Sheva", "0525381648", "Anna Zack", 1, 1, 31.25181, 34.7913);
        systemFacade.addSite("Tel Aviv", "0501234567", "Noa Kirel", 1, 0, 32.109333, 34.855499);
        systemFacade.addTruck(6666, "reg", "mazda", 1500, 4000);
        systemFacade.registerDriver("777888777", "kfir", "nissim", "Kn123456", 45, 67, 30, "aaaaaaa", "aaaaaaa", LocalDateTime.of(2023, 1, 1, 1, 1), "0548929158", 15000);
        systemFacade.addDelivery(LocalDate.of(2023, 5, 13), LocalTime.of(11, 0), 6666, "777888777", "Beer Sheva");
        String ans = systemFacade.addStopToDelivery(0, "Beer Sheva");
        assertEquals("Cant deliver from source to itself with no stops in between", ans);
    }

    @Test
    void addStopToDeliveryFirstAndLastIsTheSame() {
        systemFacade.addSite("Beer Sheva", "0525381648", "Anna Zack", 1, 1, 31.25181, 34.7913);
        systemFacade.addSite("Tel Aviv", "0501234567", "Noa Kirel", 1, 0, 32.109333, 34.855499);
        systemFacade.addTruck(6666, "reg", "mazda", 1500, 4000);
        systemFacade.registerDriver("777888777", "kfir", "nissim", "Kn123456", 45, 67, 30, "aaaaaaa", "aaaaaaa", LocalDateTime.of(2023, 1, 1, 1, 1), "0548929158",  15000);
        systemFacade.addDelivery(LocalDate.of(2023, 5, 13), LocalTime.of(11, 0), 6666, "777888777", "Beer Sheva");
        systemFacade.addStopToDelivery(0, "Tel Aviv");
        systemFacade.addStopToDelivery(0, "Beer Sheva");
        String ans = systemFacade.addStopToDelivery(0, "Tel Aviv");
        assertEquals("Cannot add stops after first and last stops are the same", ans);
    }

    @Test
    void addStopToDeliveryCannotBeVisitedTwice() {
        systemFacade.addSite("Beer Sheva", "0525381648", "Anna Zack", 1, 1, 31.25181, 34.7913);
        systemFacade.addSite("Tel Aviv", "0501234567", "Noa Kirel", 1, 0, 32.109333, 34.855499);
        systemFacade.addTruck(6666, "reg", "mazda", 1500, 4000);
        systemFacade.registerDriver("777888777", "kfir", "nissim", "Kn123456", 45, 67, 30, "aaaaaaa", "aaaaaaa", LocalDateTime.of(2023, 1, 1, 1, 1), "0548929158", 15000);
        systemFacade.addDelivery(LocalDate.of(2023, 5, 13), LocalTime.of(11, 0), 6666, "777888777", "Beer Sheva");
        systemFacade.addStopToDelivery(0, "Tel Aviv");
        String ans = systemFacade.addStopToDelivery(0, "Tel Aviv");
        assertEquals("A non center site may only be visited once per delivery", ans);
    }

    @Test
    void addStopToDeliveryCannotBeVisitedTwice1() {
        systemFacade.addSite("Beer Sheva", "0525381648", "Anna Zack", 1, 1, 31.25181, 34.7913);
        systemFacade.addSite("Tel Aviv", "0501234567", "Noa Kirel", 1, 0, 32.109333, 34.855499);
        systemFacade.addSite("Ashdod", "0541010101", "mr. wow", 3, 2, 31.801447, 34.643497);
        systemFacade.addTruck(6666, "reg", "mazda", 1500, 4000);
        systemFacade.registerDriver("777888777", "kfir", "nissim", "Kn123456", 45, 67, 30, "aaaaaaa", "aaaaaaa", LocalDateTime.of(2023, 1, 1, 1, 1), "0548929158",  15000);
        systemFacade.addDelivery(LocalDate.of(2023, 5, 13), LocalTime.of(11, 0), 6666, "777888777", "Beer Sheva");
        systemFacade.addStopToDelivery(0, "Ashdod");
        String ans = systemFacade.addStopToDelivery(0, "Tel Aviv");
        assertEquals("Logistic center can only be the last stop, cant add more stops", ans);
    }

    @Test
    void removeStopFromDeliverySuccess() {
        systemFacade.addSite("Beer Sheva", "0525381648", "Anna Zack", 1, 1, 31.25181, 34.7913);
        systemFacade.addSite("Tel Aviv", "0501234567", "Noa Kirel", 1, 0, 32.109333, 34.855499);
        systemFacade.addSite("Ashdod", "0541010101", "mr. wow", 3, 2, 31.801447, 34.643497);
        systemFacade.addTruck(6666, "reg", "mazda", 1500, 4000);
        systemFacade.registerDriver("777888777", "kfir", "nissim", "Kn123456", 45, 67, 30, "aaaaaaa", "aaaaaaa", LocalDateTime.of(2023, 1, 1, 1, 1), "0548929158", 15000);
        systemFacade.addDelivery(LocalDate.of(2023, 5, 13), LocalTime.of(11, 0), 6666, "777888777", "Beer Sheva");
        systemFacade.addStopToDelivery(0, "Tel Aviv");
        String ans = systemFacade.removeStopFromDelivery(0, "Tel Aviv");
        assertEquals("PresentationLayer.GUI.Delivery stop removed successfully", ans);
    }

    @Test
    void removeStopFromDeliveryStopIsNotNotFound() {
        systemFacade.addSite("Beer Sheva", "0525381648", "Anna Zack", 1, 1, 31.25181, 34.7913);
        systemFacade.addSite("Tel Aviv", "0501234567", "Noa Kirel", 1, 0, 32.109333, 34.855499);
        systemFacade.addSite("Ashdod", "0541010101", "mr. wow", 3, 2, 31.801447, 34.643497);
        systemFacade.addTruck(6666, "reg", "mazda", 1500, 4000);
        systemFacade.registerDriver("777888777", "kfir", "nissim", "Kn123456", 45, 67, 30, "aaaaaaa", "aaaaaaa", LocalDateTime.of(2023, 1, 1, 1, 1), "0548929158",  15000);
        systemFacade.addDelivery(LocalDate.of(2023, 5, 13), LocalTime.of(11, 0), 6666, "777888777", "Beer Sheva");
        String ans = systemFacade.removeStopFromDelivery(0, "Tel Aviv");
        assertEquals("stop isn't found in the delivery", ans);
    }

    @Test
    void addItemToDeliverySuccessDirect() {
        makeDeliveryOrder();
        systemFacade.addDelivery(LocalDate.of(2023, 5, 13), LocalTime.of(11, 0), 6666, "777888777", "Beer Sheva");
        systemFacade.addStopToDelivery(0, "Tel Aviv");
        String ans = systemFacade.addItemToDelivery(0, 0, true, 0);
        assertEquals("Item added to delivery successfully", ans);
    }

    @Test
    void addItemToDeliverySuccessSeparate() {
        makeDeliveryOrder();
        systemFacade.addDelivery(LocalDate.of(2023, 5, 13), LocalTime.of(11, 0), 6666, "777888777", "Beer Sheva");
        systemFacade.addStopToDelivery(0, "Ashdod");
        systemFacade.addDelivery(LocalDate.of(2023, 5, 13), LocalTime.of(11, 0), 6666, "777888777", "Ashdod");
        systemFacade.addStopToDelivery(1, "Tel Aviv");
        String ans1 = systemFacade.addItemToDelivery(0, 0, false, 0);
        assertEquals("Item added to delivery successfully", ans1);
        String ans2 = systemFacade.addItemToDelivery(1, 0, false, 1);
        assertEquals("Item added to delivery successfully", ans2);
    }

    @Test
    void addItemToDeliveryMissingUnloadingStop() {
        makeDeliveryOrder();
        systemFacade.addDelivery(LocalDate.of(2023, 5, 13), LocalTime.of(11, 0), 6666, "777888777", "Beer Sheva");
        systemFacade.addStopToDelivery(0, "Tel Aviv");
        String ans = systemFacade.addItemToDelivery(0, 0, false, 0);
        assertEquals("Missing unloading stop", ans);
    }

    @Test
    void addItemToDeliveryMissingLoadingStop() {
        makeDeliveryOrder();
        systemFacade.addDelivery(LocalDate.of(2023, 5, 13), LocalTime.of(11, 0), 6666, "777888777", "Tel Aviv");
        systemFacade.addStopToDelivery(0, "Ashdod");
        String ans = systemFacade.addItemToDelivery(0, 0, true, 0);
        assertEquals("Missing loading stop", ans);
    }

    @Test
    void addItemToDeliveryItemOrderDoesNotExist() {
        makeDeliveryOrder();
        systemFacade.addDelivery(LocalDate.of(2023, 5, 13), LocalTime.of(11, 0), 6666, "777888777", "Beer Sheva");
        systemFacade.addStopToDelivery(0, "Tel Aviv");
        String ans = systemFacade.addItemToDelivery(0, 2, true, 0);
        assertEquals("No such item order", ans);
    }

    @Test
    void addItemToDeliveryDeliveryDoesNotExist() {
        makeDeliveryOrder();
        systemFacade.addDelivery(LocalDate.of(2023, 5, 13), LocalTime.of(11, 0), 6666, "777888777", "Beer Sheva");
        systemFacade.addStopToDelivery(0, "Tel Aviv");
        String ans = systemFacade.addItemToDelivery(1, 1, true, 0);
        assertEquals("No such delivery ID", ans);
    }

    @Test
    void addItemToDeliveryItemIsExist() {
        makeDeliveryOrder();
        systemFacade.addDelivery(LocalDate.of(2023, 5, 13), LocalTime.of(11, 0), 6666, "777888777", "Beer Sheva");
        systemFacade.addStopToDelivery(0, "Tel Aviv");
        systemFacade.addItemToDelivery(0, 0, true, 0);
        String ans = systemFacade.addItemToDelivery(0, 0, true, 0);
        assertEquals("item order is already in the delivery", ans);
    }

    @Test
    void removeItemFromDeliverySuccess() {
        makeDeliveryOrder();
        systemFacade.addDelivery(LocalDate.of(2023, 5, 13), LocalTime.of(11, 0), 6666, "777888777", "Beer Sheva");
        systemFacade.addStopToDelivery(0, "Tel Aviv");
        systemFacade.addItemToDelivery(0, 0, true, 0);
        String ans = systemFacade.removeItemFromDelivery(0, 0);
        assertEquals("Item removed from delivery successfully", ans);
    }

    @Test
    void removeItemFromDeliveryDeliveryDoesNotExist() {
        makeDeliveryOrder();
        systemFacade.addDelivery(LocalDate.of(2023, 5, 13), LocalTime.of(11, 0), 6666, "777888777", "Beer Sheva");
        systemFacade.addStopToDelivery(0, "Tel Aviv");
        systemFacade.addItemToDelivery(0, 0, true, 0);
        String ans = systemFacade.removeItemFromDelivery(1, 0);
        assertEquals("No such delivery ID", ans);
    }

    @Test
    void removeItemFromDeliveryItemOrderNotInDelivery() {
        makeDeliveryOrder();
        systemFacade.addDelivery(LocalDate.of(2023, 5, 13), LocalTime.of(11, 0), 6666, "777888777", "Beer Sheva");
        systemFacade.addStopToDelivery(0, "Tel Aviv");
        systemFacade.addItemToDelivery(0, 0, true, 0);
        String ans = systemFacade.removeItemFromDelivery(0, 1);
        assertEquals("itemOrder doesn't exist in the delivery", ans);
    }

    @Test
    void approveDeliverySuccessDirect() {
        makeDeliveryOrder();
        systemFacade.addDelivery(LocalDate.of(2023, 5, 13), LocalTime.of(11, 0), 6666, "777888777", "Beer Sheva");
        systemFacade.addStopToDelivery(0, "Tel Aviv");
        systemFacade.addItemToDelivery(0, 0, true, 0);
        systemFacade.addDriverSchedule("777888777", 4);
        String ans = systemFacade.approveDelivery(0);
        ans = ans.replaceAll("\\r\\n?", "");
        ans = ans.replaceAll("\\r", "\n");
        assertEquals("PresentationLayer.GUI.Delivery approved successfully", ans);
        assertEquals(0, systemFacade.getDeliveryService().getDeliveryController().getItemOrders().get(0).getItemDelivery1());
        assertEquals(2, systemFacade.getDeliveryService().getDeliveryController().getItemOrders().get(0).getStatusOrdinal());
    }

    @Test
    void approveDeliveryDriverIsNotInTheShift() {
        makeDeliveryOrder();
        systemFacade.registerDriver("123456789", "kfir", "nissim", "Kn123456", 45, 67, 30, "aaaaaaa", "aaaaaaa", LocalDateTime.of(2023, 1, 1, 1, 1), "0548929158",  15000);
        systemFacade.addDelivery(LocalDate.of(2023, 5, 13), LocalTime.of(11, 0), 6666, "123456789", "Beer Sheva");
        systemFacade.addStopToDelivery(0, "Tel Aviv");
        systemFacade.addItemToDelivery(0, 0, true, 0);
        systemFacade.addDriverSchedule("777888777", 4);
        String ans = systemFacade.approveDelivery(0);
        ans = ans.replaceAll("\\r\\n?", "");
        ans = ans.replaceAll("\\r", "\n");
        assertEquals("Driver isn't in the shift", ans);
    }

    @Test
    void approveDeliverySuccessSeparate() {
        makeDeliveryOrder();
        systemFacade.addDelivery(LocalDate.of(2023, 5, 13), LocalTime.of(11, 0), 6666, "777888777", "Beer Sheva");
        systemFacade.addStopToDelivery(0, "Ashdod");
        systemFacade.addItemToDelivery(0, 0, false, 0);
        systemFacade.addDriverSchedule("777888777", 4);
        systemFacade.addDriverSchedule("777888777", 9);
        String ans1 = systemFacade.approveDelivery(0);
        ans1 = ans1.replaceAll("\\r\\n?", "");
        ans1 = ans1.replaceAll("\\r", "\n");
        assertEquals("PresentationLayer.GUI.Delivery approved successfully", ans1);
        assertEquals(0, systemFacade.getDeliveryService().getDeliveryController().getItemOrders().get(0).getItemDelivery1());
        assertEquals(1, systemFacade.getDeliveryService().getDeliveryController().getItemOrders().get(0).getStatusOrdinal());
        systemFacade.addDelivery(LocalDate.of(2023, 5, 14), LocalTime.of(11, 0), 6666, "777888777", "Ashdod");
        systemFacade.addStopToDelivery(1, "Tel Aviv");
        systemFacade.addItemToDelivery(1, 0, false, 1);
        String ans2 = systemFacade.approveDelivery(1);
        ans2 = ans2.replaceAll("\\r\\n?", "");
        ans2 = ans2.replaceAll("\\r", "\n");
        assertEquals("PresentationLayer.GUI.Delivery approved successfully", ans2);
        assertEquals(0, systemFacade.getDeliveryService().getDeliveryController().getItemOrders().get(0).getItemDelivery1());
        assertEquals(1, systemFacade.getDeliveryService().getDeliveryController().getItemOrders().get(0).getItemDelivery2());
        assertEquals(2, systemFacade.getDeliveryService().getDeliveryController().getItemOrders().get(0).getStatusOrdinal());
    }

    @Test
    void approveDeliveryDeliveryDoesNotExist() {
        makeDeliveryOrder();
        systemFacade.addDelivery(LocalDate.of(2023, 5, 13), LocalTime.of(11, 0), 6666, "777888777", "Beer Sheva");
        systemFacade.addStopToDelivery(0, "Tel Aviv");
        systemFacade.addItemToDelivery(0, 0, true, 0);
        String ans = systemFacade.approveDelivery(2);
        assertEquals("No such delivery ID", ans);
    }

    @Test
    void approveDeliveryDeliveryIsAlreadyApproved() {
        makeDeliveryOrder();
        systemFacade.addDelivery(LocalDate.of(2023, 5, 13), LocalTime.of(11, 0), 6666, "777888777", "Beer Sheva");
        systemFacade.addStopToDelivery(0, "Tel Aviv");
        systemFacade.addItemToDelivery(0, 0, true, 0);
        systemFacade.addDriverSchedule("777888777", 4);
        systemFacade.approveDelivery(0);
        String ans = systemFacade.approveDelivery(0);
        assertEquals("Can only approve a pending delivery", ans);
    }

    @Test
    void approveDeliveryDriverDoesNotMatch() {
        makeDeliveryOrder();
        systemFacade.registerDriver("888777888", "Martin", "Koroktov", "Mk5349875", 67, 45, 30, "aaaaa", "ggggg", LocalDateTime.of(2023, 1, 1, 1, 1), "0548929158",  1000);
        systemFacade.addDelivery(LocalDate.of(2023, 5, 13), LocalTime.of(11, 0), 6666, "888777888", "Beer Sheva");
        systemFacade.addStopToDelivery(0, "Tel Aviv");
        systemFacade.addItemToDelivery(0, 0, true, 0);
        systemFacade.addDriverSchedule("888777888", 4);
        String ans = systemFacade.approveDelivery(0);
        assertEquals("Driver does not have the required license for this truck", ans);
    }

    @Test
    void approveDeliveryDriverIsUnAvailable() {
        makeDeliveryOrder();
        systemFacade.addDelivery(LocalDate.of(2023, 5, 13), LocalTime.of(11, 0), 6666, "777888777", "Beer Sheva");
        systemFacade.addDriverDeliveryDate("777888777", LocalDate.of(2023, 5, 13));
        systemFacade.addStopToDelivery(0, "Tel Aviv");
        systemFacade.addItemToDelivery(0, 0, true, 0);
        systemFacade.addDriverSchedule("777888777", 4);
        String ans = systemFacade.approveDelivery(0);
        assertEquals("Driver is unavailable", ans);
    }

    @Test
    void approveDeliveryTruckIsUnAvailable() {
        makeDeliveryOrder();
        systemFacade.addDelivery(LocalDate.of(2023, 5, 13), LocalTime.of(11, 0), 6666, "777888777", "Beer Sheva");
        systemFacade.addTruckDeliveryDate(6666, LocalDate.of(2023, 5, 13));
        systemFacade.addStopToDelivery(0, "Tel Aviv");
        systemFacade.addItemToDelivery(0, 0, true, 0);
        systemFacade.addDriverSchedule("777888777", 4);
        String ans = systemFacade.approveDelivery(0);
        assertEquals("Truck is unavailable", ans);
    }

    @Test
    void approveDeliveryNotFirstApprove() {
        makeDeliveryOrder();
        systemFacade.addDelivery(LocalDate.of(2023, 5, 13), LocalTime.of(11, 0), 6666, "777888777", "Ashdod");
        systemFacade.addStopToDelivery(0, "Tel Aviv");
        systemFacade.addItemToDelivery(0, 0, false, 1);
        systemFacade.addDriverSchedule("777888777", 4);
        String ans = systemFacade.approveDelivery(0);
        assertEquals("Cannot load items from logistic center that has no approved delivery to logistic center for them", ans);
    }

    @Test
    void showDelivery() {
        makeDeliveryOrder();
        systemFacade.addDelivery(LocalDate.of(2023, 5, 13), LocalTime.of(11, 0), 6666, "777888777", "Beer Sheva");
        systemFacade.addStopToDelivery(0, "Tel Aviv");
        systemFacade.addItemToDelivery(0, 0, true, 0);
        systemFacade.addDriverSchedule("777888777", 4);
        systemFacade.approveDelivery(0);
        String ans = systemFacade.showDelivery(0);
        ans = ans.replaceAll("\\r\\n?", "");
        ans = ans.replaceAll("\\r", "\n");
        assertEquals("{  \"date\" : \"2023-05-13\",  \"time\" : \"11:00:00\",  \"source\" : {    \"region\" : \"SOUTH\",    \"type\" : \"STORE\",    \"address\" : \"Beer Sheva\",    \"phoneNumber\" : \"0525381648\",    \"contactName\" : \"Anna Zack\",    \"coordinate\" : {      \"latitude\" : 31.25181,      \"longitude\" : 34.7913    },    \"timeOfShifts\" : [ \"TUESDAY E\", \"TUESDAY M\", \"FRIDAY E\", \"FRIDAY M\", \"SATURDAY E\", \"SATURDAY M\", \"SUNDAY E\", \"SUNDAY M\", \"MONDAY E\", \"MONDAY M\", \"THURSDAY E\", \"THURSDAY M\", \"WEDNESDAY E\", \"WEDNESDAY M\" ],    \"morningShiftStartHour\" : \"08:00:00\",    \"eveningShiftStartHour\" : \"13:00:00\",    \"morningShiftEndHour\" : \"13:00:00\",    \"eveningShiftEndHour\" : \"22:00:00\"  },  \"destinations\" : [ {    \"id\" : 0,    \"deliveryId\" : 0,    \"destination\" : {      \"region\" : \"SOUTH\",      \"type\" : \"STORE\",      \"address\" : \"Beer Sheva\",      \"phoneNumber\" : \"0525381648\",      \"contactName\" : \"Anna Zack\",      \"coordinate\" : {        \"latitude\" : 31.25181,        \"longitude\" : 34.7913      },      \"timeOfShifts\" : [ \"TUESDAY E\", \"TUESDAY M\", \"FRIDAY E\", \"FRIDAY M\", \"SATURDAY E\", \"SATURDAY M\", \"SUNDAY E\", \"SUNDAY M\", \"MONDAY E\", \"MONDAY M\", \"THURSDAY E\", \"THURSDAY M\", \"WEDNESDAY E\", \"WEDNESDAY M\" ],      \"morningShiftStartHour\" : \"08:00:00\",      \"eveningShiftStartHour\" : \"13:00:00\",      \"morningShiftEndHour\" : \"13:00:00\",      \"eveningShiftEndHour\" : \"22:00:00\"    },    \"loadList\" : [ {      \"id\" : 0,      \"deliveryId\" : 0,      \"itemOrder\" : {        \"id\" : 0,        \"itemId\" : 111,        \"deliveryOrderId\" : 0,        \"source\" : \"Beer Sheva\",        \"destination\" : \"Tel Aviv\",        \"quantity\" : 20,        \"refrigeration\" : false,        \"status\" : \"APPROVED\",        \"itemDelivery1\" : 0,        \"itemDelivery2\" : -1,        \"statusOrdinal\" : 2      },      \"source\" : \"Beer Sheva\",      \"destination\" : \"Tel Aviv\",      \"status\" : \"APPROVED\",      \"statusOrdinal\" : 1    } ],    \"status\" : \"APPROVED\",    \"arriveTime\" : \"2023-05-13T11:00:00\",    \"unloadList\" : [ ]  }, {    \"id\" : 1,    \"deliveryId\" : 0,    \"destination\" : {      \"region\" : \"SOUTH\",      \"type\" : \"STORE\",      \"address\" : \"Tel Aviv\",      \"phoneNumber\" : \"0501234567\",      \"contactName\" : \"Noa Kirel\",      \"coordinate\" : {        \"latitude\" : 32.109333,        \"longitude\" : 34.855499      },      \"timeOfShifts\" : [ \"TUESDAY E\", \"TUESDAY M\", \"FRIDAY E\", \"FRIDAY M\", \"SATURDAY E\", \"SATURDAY M\", \"SUNDAY E\", \"SUNDAY M\", \"MONDAY E\", \"MONDAY M\", \"THURSDAY E\", \"THURSDAY M\", \"WEDNESDAY E\", \"WEDNESDAY M\" ],      \"morningShiftStartHour\" : \"08:00:00\",      \"eveningShiftStartHour\" : \"13:00:00\",      \"morningShiftEndHour\" : \"13:00:00\",      \"eveningShiftEndHour\" : \"22:00:00\"    },    \"loadList\" : [ ],    \"status\" : \"APPROVED\",    \"arriveTime\" : \"2023-05-13T12:46:00\",    \"unloadList\" : [ {      \"id\" : 0,      \"deliveryId\" : 0,      \"itemOrder\" : {        \"id\" : 0,        \"itemId\" : 111,        \"deliveryOrderId\" : 0,        \"source\" : \"Beer Sheva\",        \"destination\" : \"Tel Aviv\",        \"quantity\" : 20,        \"refrigeration\" : false,        \"status\" : \"APPROVED\",        \"itemDelivery1\" : 0,        \"itemDelivery2\" : -1,        \"statusOrdinal\" : 2      },      \"source\" : \"Beer Sheva\",      \"destination\" : \"Tel Aviv\",      \"status\" : \"APPROVED\",      \"statusOrdinal\" : 1    } ]  } ],  \"truckId\" : 6666,  \"driverId\" : \"777888777\",  \"startingWeight\" : -1.0,  \"status\" : \"APPROVED\",  \"maxWeight\" : 4000.0,  \"id\" : 0,  \"statusOrdinal\" : 1}", ans);
        assertEquals(0, systemFacade.getDeliveryService().getDeliveryController().getItemOrders().get(0).getItemDelivery1());
        assertEquals(2, systemFacade.getDeliveryService().getDeliveryController().getItemOrders().get(0).getStatusOrdinal());
    }

    @Test
    void cancelDeliverySuccessPending() {
        makeDeliveryOrder();
        systemFacade.addDelivery(LocalDate.of(2023, 5, 13), LocalTime.of(11, 0), 6666, "777888777", "Beer Sheva");
        systemFacade.addStopToDelivery(0, "Tel Aviv");
        systemFacade.addItemToDelivery(0, 0, true, 0);
        String ans = systemFacade.cancelDelivery(0);
        assertEquals("PresentationLayer.GUI.Delivery canceled successfully", ans);
    }

    @Test
    void cancelDeliverySuccessApproved() {
        makeDeliveryOrder();
        systemFacade.addDelivery(LocalDate.of(2023, 5, 13), LocalTime.of(11, 0), 6666, "777888777", "Beer Sheva");
        systemFacade.addStopToDelivery(0, "Tel Aviv");
        systemFacade.addItemToDelivery(0, 0, true, 0);
        systemFacade.approveDelivery(0);
        String ans = systemFacade.cancelDelivery(0);
        assertEquals("PresentationLayer.GUI.Delivery canceled successfully", ans);
        assertEquals(-1, systemFacade.getDeliveryService().getDeliveryController().getItemOrders().get(0).getItemDelivery1());
        assertEquals(0, systemFacade.getDeliveryService().getDeliveryController().getItemOrders().get(0).getStatusOrdinal());
    }

    @Test
    void cancelDeliverySuccessApproved2() {
        makeDeliveryOrder();
        systemFacade.addDelivery(LocalDate.of(2023, 5, 13), LocalTime.of(11, 0), 6666, "777888777", "Beer Sheva");
        systemFacade.addStopToDelivery(0, "Ashdod");
        systemFacade.addItemToDelivery(0, 0, false, 0);
        systemFacade.addDriverSchedule("777888777", 4);
        systemFacade.addDriverSchedule("777888777", 9);
        systemFacade.approveDelivery(0);
        systemFacade.addDelivery(LocalDate.of(2023, 5, 14), LocalTime.of(11, 0), 6666, "777888777", "Ashdod");
        systemFacade.addStopToDelivery(1, "Tel Aviv");
        systemFacade.addItemToDelivery(1, 0, false, 1);
        systemFacade.approveDelivery(1);
        String ans = systemFacade.cancelDelivery(0);
        assertEquals("PresentationLayer.GUI.Delivery canceled successfully", ans);
        assertEquals(-1, systemFacade.getDeliveryService().getDeliveryController().getItemOrders().get(0).getItemDelivery1());
        assertEquals(-1, systemFacade.getDeliveryService().getDeliveryController().getItemOrders().get(0).getItemDelivery2());
        assertEquals(0, systemFacade.getDeliveryService().getDeliveryController().getItemOrders().get(0).getStatusOrdinal());
        assertEquals(0, systemFacade.getDeliveryService().getDeliveryController().getDeliveries().get(1).getDestinations().getFirst().getLoadList().size());
    }

    @Test
    void cancelDeliveryCanceledDelivery() {
        makeDeliveryOrder();
        systemFacade.addDelivery(LocalDate.of(2023, 5, 13), LocalTime.of(11, 0), 6666, "777888777", "Beer Sheva");
        systemFacade.addStopToDelivery(0, "Tel Aviv");
        systemFacade.addItemToDelivery(0, 0, true, 0);
        systemFacade.approveDelivery(0);
        systemFacade.cancelDelivery(0);
        String ans = systemFacade.cancelDelivery(0);
        assertEquals("cannot cancel this delivery", ans);
    }

    @Test
    void cancelDeliveryDeliveryDoesNotExist() {
        makeDeliveryOrder();
        systemFacade.addDelivery(LocalDate.of(2023, 5, 13), LocalTime.of(11, 0), 6666, "777888777", "Beer Sheva");
        systemFacade.addStopToDelivery(0, "Tel Aviv");
        systemFacade.addItemToDelivery(0, 0, true, 0);
        systemFacade.approveDelivery(0);
        String ans = systemFacade.cancelDelivery(2);
        assertEquals("No such delivery ID", ans);
    }

    @Test
    void visitStopSuccess1() {
        makeDeliveryOrder();
        systemFacade.addDelivery(LocalDate.of(2023, 5, 13), LocalTime.of(11, 0), 6666, "777888777", "Beer Sheva");
        systemFacade.addStopToDelivery(0, "Tel Aviv");
        systemFacade.addItemToDelivery(0, 0, true, 0);
        systemFacade.addDriverSchedule("777888777", 4);
        systemFacade.approveDelivery(0);
        String ans = systemFacade.visitStop(0, "Beer Sheva", 1000);
        assertEquals("Stop visited successfully", ans);
    }

    @Test
    void visitStopSuccess2() {
        makeDeliveryOrder();
        systemFacade.addDelivery(LocalDate.of(2023, 5, 13), LocalTime.of(11, 0), 6666, "777888777", "Beer Sheva");
        systemFacade.addStopToDelivery(0, "Tel Aviv");
        systemFacade.addItemToDelivery(0, 0, true, 0);
        systemFacade.addDriverSchedule("777888777", 4);
        systemFacade.approveDelivery(0);
        systemFacade.visitStop(0, "Beer Sheva", 1000);
        String ans = systemFacade.visitStop(0, "Tel Aviv", 1000);
        assertEquals("Stop visited successfully", ans);
        assertEquals(3, systemFacade.getDeliveryService().getDeliveryController().getItemOrders().get(0).getStatusOrdinal());
    }

    @Test
    void visitStopCanceledDelivery() {
        makeDeliveryOrder();
        systemFacade.addDelivery(LocalDate.of(2023, 5, 13), LocalTime.of(11, 0), 6666, "777888777", "Beer Sheva");
        systemFacade.addStopToDelivery(0, "Tel Aviv");
        systemFacade.addItemToDelivery(0, 0, true, 0);
        systemFacade.approveDelivery(0);
        systemFacade.cancelDelivery(0);
        String ans = systemFacade.visitStop(0, "Beer Sheva", 1000);
        assertEquals("Cannot visit stops in an unapproved delivery", ans);
    }

    @Test
    void visitStopStopIsNotFound() {
        makeDeliveryOrder();
        systemFacade.addDelivery(LocalDate.of(2023, 5, 13), LocalTime.of(11, 0), 6666, "777888777", "Beer Sheva");
        systemFacade.addStopToDelivery(0, "Tel Aviv");
        systemFacade.addItemToDelivery(0, 0, true, 0);
        systemFacade.addDriverSchedule("777888777", 4);
        systemFacade.approveDelivery(0);
        String ans = systemFacade.visitStop(0, "Ashdod", 1000);
        assertEquals("Cannot visit a stop before visiting all previous stops", ans);
    }

    @Test
    void visitStopStopOverWeight() {
        makeDeliveryOrder();
        systemFacade.addDelivery(LocalDate.of(2023, 5, 13), LocalTime.of(11, 0), 6666, "777888777", "Beer Sheva");
        systemFacade.addStopToDelivery(0, "Tel Aviv");
        systemFacade.addItemToDelivery(0, 0, true, 0);
        systemFacade.addDriverSchedule("777888777", 4);
        systemFacade.approveDelivery(0);
        String ans = systemFacade.visitStop(0, "Beer Sheva", 5000);
        assertEquals("Truck is overweight with weight: 5000.0 at stop Beer Sheva possible solutions: switch truck, remove this stop, remove items from this stop", ans);
        assertEquals(4, systemFacade.getDeliveryService().getDeliveryController().getDeliveries().get(0).getStatusOrdinal());
    }

    @Test
    void switchTruckSuccess() {
        makeDeliveryOrder();
        systemFacade.addDelivery(LocalDate.of(2023, 5, 13), LocalTime.of(11, 0), 6666, "777888777", "Beer Sheva");
        systemFacade.addStopToDelivery(0, "Tel Aviv");
        systemFacade.addItemToDelivery(0, 0, true, 0);
        systemFacade.addDriverSchedule("777888777", 4);
        systemFacade.approveDelivery(0);
        systemFacade.visitStop(0, "Beer Sheva", 5000);
        systemFacade.addTruck(2222, "reg", "mitsubishi", 3000, 5000);
        String ans = systemFacade.switchTruck(0, 2222, 5000);
        assertEquals("PresentationLayer.GUI.Delivery truck switched successfully", ans);
    }

    @Test
    void switchTruckStillOverweight() {
        makeDeliveryOrder();
        systemFacade.addDelivery(LocalDate.of(2023, 5, 13), LocalTime.of(11, 0), 6666, "777888777", "Beer Sheva");
        systemFacade.addStopToDelivery(0, "Tel Aviv");
        systemFacade.addItemToDelivery(0, 0, true, 0);
        systemFacade.addDriverSchedule("777888777", 4);
        systemFacade.approveDelivery(0);
        systemFacade.visitStop(0, "Beer Sheva", 5000);
        systemFacade.addTruck(2222, "reg", "mitsubishi", 3000, 4000);
        String ans = systemFacade.switchTruck(0, 2222, 5000);
        assertEquals("Truck max weight is lower than current weight", ans);
    }

    @Test
    void switchTruckNotMatch() {
        makeDeliveryOrder();
        systemFacade.addDelivery(LocalDate.of(2023, 5, 13), LocalTime.of(11, 0), 6666, "777888777", "Beer Sheva");
        systemFacade.addStopToDelivery(0, "Tel Aviv");
        systemFacade.addItemToDelivery(0, 0, true, 0);
        systemFacade.addDriverSchedule("777888777", 4);
        systemFacade.approveDelivery(0);
        systemFacade.visitStop(0, "Beer Sheva", 5000);
        systemFacade.addTruck(2222, "Refrigeration", "mitsubishi", 3000, 6000);
        String ans = systemFacade.switchTruck(0, 2222, 5000);
        assertEquals("Truck does not match delivery type", ans);
    }

    @Test
    void switchTruckDriver() {
        makeDeliveryOrder();
        systemFacade.addDelivery(LocalDate.of(2023, 5, 13), LocalTime.of(11, 0), 6666, "777888777", "Beer Sheva");
        systemFacade.addStopToDelivery(0, "Tel Aviv");
        systemFacade.addItemToDelivery(0, 0, true, 0);
        systemFacade.addDriverSchedule("777888777", 4);
        systemFacade.approveDelivery(0);
        systemFacade.visitStop(0, "Beer Sheva", 5000);
        systemFacade.addTruck(2222, "reg", "mitsubishi", 3000, 5000);
        systemFacade.registerDriver("888777888", "Martin", "Koroktov", "Mk5349875", 67, 45, 30, "aaaaa", "ggggg", LocalDateTime.of(2023, 1, 1, 1, 1), "0557395721",  6000);
        systemFacade.addDriverSchedule("888777888", 4);
        String ans = systemFacade.switchTruckDriver(0, 2222, "777888777", 5000);
        assertEquals("PresentationLayer.GUI.Delivery truck and driver switched successfully", ans);
    }

    @Test
    void switchTruckDriverNotMatched() {
        makeDeliveryOrder();
        systemFacade.addDelivery(LocalDate.of(2023, 5, 13), LocalTime.of(11, 0), 6666, "777888777", "Beer Sheva");
        systemFacade.addStopToDelivery(0, "Tel Aviv");
        systemFacade.addItemToDelivery(0, 0, true, 0);
        systemFacade.addDriverSchedule("777888777", 4);
        systemFacade.approveDelivery(0);
        systemFacade.visitStop(0, "Beer Sheva", 5000);
        systemFacade.addTruck(2222, "reg", "mitsubishi", 3000, 5000);
        systemFacade.registerDriver("888777888", "Martin", "Koroktov", "Mk5349875", 67, 45, 30, "aaaaa", "ggggg", LocalDateTime.of(2023, 1, 1, 1, 1), "0557395721",  4000);
        systemFacade.addDriverSchedule("888777888", 4);
        String ans = systemFacade.switchTruckDriver(0, 2222, "888777888", 5000);
        assertEquals("Driver does not have the required license for this truck", ans);
    }

    @Test
    void skipStopLoadSuccess() {
        makeDeliveryOrder();
        systemFacade.addDelivery(LocalDate.of(2023, 5, 13), LocalTime.of(11, 0), 6666, "777888777", "Beer Sheva");
        systemFacade.addStopToDelivery(0, "Ashdod");
        systemFacade.addItemToDelivery(0, 0, false, 0);
        systemFacade.addDelivery(LocalDate.of(2023, 5, 14), LocalTime.of(11, 0), 6666, "777888777", "Ashdod");
        systemFacade.addStopToDelivery(1, "Beer Sheva");
        systemFacade.addStopToDelivery(1, "Tel Aviv");
        systemFacade.addItemToDelivery(1, 0, false, 1);
        systemFacade.addItemToDelivery(1, 1, true, 0);
        systemFacade.addDriverSchedule("777888777", 4);
        systemFacade.addDriverSchedule("777888777", 9);
        systemFacade.approveDelivery(0);
        systemFacade.approveDelivery(1);
        systemFacade.visitStop(0, "Beer Sheva", 200);
        systemFacade.visitStop(0, "Ashdod", 200);
        systemFacade.visitStop(1, "Ashdod", 5000);
        String ans1 = systemFacade.skipStopLoad(1);
        assertEquals("Stop skipped successfully", ans1);
        String ans2 = systemFacade.visitStop(1, "Beer Sheva", 200);
        assertEquals("Stop visited successfully", ans2);
    }

    @Test
    void skipStopLoadNotOverweight() {
        makeDeliveryOrder();
        systemFacade.addDelivery(LocalDate.of(2023, 5, 13), LocalTime.of(11, 0), 6666, "777888777", "Beer Sheva");
        systemFacade.addStopToDelivery(0, "Ashdod");
        systemFacade.addItemToDelivery(0, 0, false, 0);
        systemFacade.addDelivery(LocalDate.of(2023, 5, 14), LocalTime.of(11, 0), 6666, "777888777", "Ashdod");
        systemFacade.addStopToDelivery(1, "Beer Sheva");
        systemFacade.addStopToDelivery(1, "Tel Aviv");
        systemFacade.addItemToDelivery(1, 0, false, 1);
        systemFacade.addItemToDelivery(1, 1, true, 0);
        systemFacade.approveDelivery(0);
        systemFacade.approveDelivery(1);
        systemFacade.visitStop(0, "Beer Sheva", 200);
        systemFacade.visitStop(0, "Ashdod", 200);
        systemFacade.visitStop(1, "Ashdod", 200);
        String ans = systemFacade.skipStopLoad(1);
        assertEquals("Cannot switch items from non overweight delivery", ans);
    }

    @Test
    void leaveItemSuccess() {
        makeDeliveryOrder();
        systemFacade.addDelivery(LocalDate.of(2023, 5, 13), LocalTime.of(11, 0), 6666, "777888777", "Beer Sheva");
        systemFacade.addDriverSchedule("777888777", 4);
        systemFacade.addDriverSchedule("777888777", 9);
        systemFacade.addStopToDelivery(0, "Ashdod");
        systemFacade.addItemToDelivery(0, 0, false, 0);
        systemFacade.addDelivery(LocalDate.of(2023, 5, 14), LocalTime.of(11, 0), 6666, "777888777", "Ashdod");
        systemFacade.addStopToDelivery(1, "Beer Sheva");
        systemFacade.addStopToDelivery(1, "Tel Aviv");
        systemFacade.addItemToDelivery(1, 0, false, 1);
        systemFacade.addItemToDelivery(1, 1, true, 0);
        systemFacade.approveDelivery(0);
        systemFacade.approveDelivery(1);
        systemFacade.visitStop(0, "Beer Sheva", 200);
        systemFacade.visitStop(0, "Ashdod", 200);
        systemFacade.visitStop(1, "Ashdod", 5000);
        String ans = systemFacade.leaveItem(1, 0);
        assertEquals("Item removed from loading successfully", ans);
    }

    @Test
    void leaveItemNotRelatedToStop() {
        makeDeliveryOrder();
        systemFacade.addDelivery(LocalDate.of(2023, 5, 13), LocalTime.of(11, 0), 6666, "777888777", "Beer Sheva");
        systemFacade.addStopToDelivery(0, "Ashdod");
        systemFacade.addItemToDelivery(0, 0, false, 0);
        systemFacade.addDelivery(LocalDate.of(2023, 5, 14), LocalTime.of(11, 0), 6666, "777888777", "Ashdod");
        systemFacade.addStopToDelivery(1, "Beer Sheva");
        systemFacade.addStopToDelivery(1, "Tel Aviv");
        systemFacade.addItemToDelivery(1, 0, false, 1);
        systemFacade.addItemToDelivery(1, 1, true, 0);
        systemFacade.addDriverSchedule("777888777", 4);
        systemFacade.addDriverSchedule("777888777", 9);
        systemFacade.approveDelivery(0);
        systemFacade.approveDelivery(1);
        systemFacade.visitStop(0, "Beer Sheva", 200);
        systemFacade.visitStop(0, "Ashdod", 200);
        systemFacade.visitStop(1, "Ashdod", 5000);
        String ans = systemFacade.leaveItem(1, 1);
        assertEquals("Item is not related to this stop", ans);
    }
}