package ServiceLayer;

import BusinessLayer.Delivery.DeliveryController;
import BusinessLayer.Delivery.ResourceController;
import ServiceLayer.JsonService.JsonConverter;

import java.time.LocalDate;
import java.time.LocalTime;

public class DeliveryService {
    private final DeliveryController deliveryController;
    private final ResourceController resourceController;

    public DeliveryService(DeliveryController deliveryController, ResourceController resourceController) {
        this.resourceController = resourceController;
        this.deliveryController = deliveryController;
    }

    //region Resource creation/removal/update functions
    //region Truck functions
    public String addTruck(int id, String type, String model, float netWeight, float maxWeight) {
        try {
            resourceController.addTruck(id, type, model, netWeight, maxWeight);
            return ("Truck added successfully");
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String removeTruck(int truckId) {
        try {
            resourceController.removeTruck(truckId);
            return ("Truck removed successfully");
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String updateTruckStatus(int truckId, int status) {
        try {
            resourceController.updateTruckStatus(truckId, status);
            return ("Truck status updated successfully");
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String addTruckDeliveryDate(int truckId, LocalDate deliveryDate) {
        try {
            resourceController.addTruckDeliveryDate(truckId, deliveryDate);
            return ("Truck delivery date added successfully");
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String removeTruckDeliveryDate(int truckId, LocalDate deliveryDate) {
        try {
            resourceController.removeTruckDeliveryDate(truckId, deliveryDate);
            return ("Truck delivery date removed successfully");
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String showTruck(int truckId) {
        try {
            return resourceController.showTruck(truckId);
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String showAllTrucks() {
        try {
            return resourceController.showAllTrucks();
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    //endregion

    //region Site functions
    public String addSite(String address, String phoneNumber, String contactName, int region, int type, double latitude, double longitude) {
        try {
            resourceController.addSite(address, phoneNumber, contactName, region, type, latitude, longitude);
            return ("Site created successfully");
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String removeSite(String address) {
        try {
            resourceController.removeSite(address);
            return ("Site removed successfully");
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String updatePhoneNumber(String address, String phoneNumber) {
        try {
            resourceController.updatePhoneNumber(address, phoneNumber);
            return ("Phone number changed successfully");
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String updateContactName(String address, String contactName) {
        try {
            resourceController.updateContactName(address, contactName);
            return ("Contact name changed successfully");
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String updateSiteCoordinate(String address, double latitude, double longitude) {
        try {
            resourceController.updateSiteCoordinate(address, latitude, longitude);
            return ("Contact name changed successfully");
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String showSite(String address) {
        try {
            return resourceController.showSite(address);
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String showAllSites() {
        try {
            return resourceController.showAllSites();
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    //endregion
    //endregion

    //region PresentationLayer.GUI.Delivery creation/cancel/update functions
    public String parseOrder(String deliveryOrder) {
        try {
            deliveryController.parseOrder(deliveryOrder);
            return ("PresentationLayer.GUI.Delivery order created successfully");
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String addDelivery(LocalDate date, LocalTime time, int truckId, String driverId, String address) {
        try {
            deliveryController.addDelivery(date, time, truckId, driverId, address);
            return ("PresentationLayer.GUI.Delivery created successfully");
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String cancelDelivery(int deliveryId) {
        try {
            deliveryController.cancelDelivery(deliveryId);
            return ("PresentationLayer.GUI.Delivery canceled successfully");
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String updateDeliveryTruck(int deliveryId, int truckId) {
        try {
            deliveryController.updateTruck(deliveryId, truckId);
            return ("PresentationLayer.GUI.Delivery truck updated successfully");
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String updateDeliveryDriver(int deliveryId, String driverId) {
        try {
            deliveryController.updateDriver(deliveryId, driverId);
            return ("PresentationLayer.GUI.Delivery driver updated successfully");
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String addStopToDelivery(int deliveryId, String address) {
        try {
            deliveryController.addStopToDelivery(deliveryId, address);
            return ("PresentationLayer.GUI.Delivery stop added successfully");
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String removeStopFromDelivery(int deliveryId, String address) {
        try {
            deliveryController.removeStopFromDelivery(deliveryId, address);
            return ("PresentationLayer.GUI.Delivery stop removed successfully");
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String updateArriveTimeToStop(int deliveryId, String address, LocalTime time) {
        try {
            deliveryController.updateArriveTimeToStop(deliveryId, address, time);
            return ("PresentationLayer.GUI.Delivery stop arrive time update successfully");
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String addItemToDelivery(int deliveryId, int itemOrderId, boolean direct, int stage) {
        try {
            deliveryController.addItemToDelivery(deliveryId, itemOrderId, direct, stage);
            return ("Item added to delivery successfully");
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String removeItemFromDelivery(int deliveryId, int itemOrderId) {
        try {
            deliveryController.removeItemFromDelivery(deliveryId, itemOrderId);
            return ("Item removed from delivery successfully");
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String approveDelivery(int deliveryId) {
        try {
            deliveryController.approveDelivery(deliveryId);
            return ("PresentationLayer.GUI.Delivery approved successfully");
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String showDelivery(int deliveryId) {
        try {
            return deliveryController.showDelivery(deliveryId);
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String showAllDeliveries(){
        try {
            return deliveryController.showAllDeliveries();
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String showDeliveryOrder(int deliveryOrderId) {
        try {
            return deliveryController.showDeliveryOrder(deliveryOrderId);
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String showAllDeliveryOrders() {
        try {
            return deliveryController.showAllDeliveryOrders();
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String showDeliveryByShift(int shiftId) {
        try {
            return deliveryController.getDeliveriesByShift(shiftId);
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String showAllItemOrders(){
        try {
            return deliveryController.showAllItemOrders();
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String visitStop(int deliveryId, String address, float weight) {
        try {
            deliveryController.visitStop(deliveryId, address, weight);
            return ("Stop visited successfully");
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String switchTruck(int deliveryId, int truckId, float currentWeight) {
        try {
            deliveryController.switchTruck(deliveryId, truckId, currentWeight);
            return ("PresentationLayer.GUI.Delivery truck switched successfully");
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String switchTruckDriver(int deliveryId, int truckId, String driverId, float currentWeight) {
        try {
            deliveryController.switchTruckDriver(deliveryId, truckId, driverId, currentWeight);
            return ("PresentationLayer.GUI.Delivery truck and driver switched successfully");
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String skipStopLoad(int deliveryId) {
        try {
            deliveryController.skipStopLoad(deliveryId);
            return ("Stop skipped successfully");
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String leaveItem(int deliveryId, int itemOrderId) {
        try {
            deliveryController.leaveItem(deliveryId, itemOrderId);
            return ("Item removed from loading successfully");
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public DeliveryController getDeliveryController() {
        return deliveryController;
    }

    public ResourceController getResourceController() {
        return resourceController;
    }
    //endregion
}