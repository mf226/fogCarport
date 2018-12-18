package FunctionLayer;

import FunctionLayer.Entity.User;
import FunctionLayer.Entity.Order;
import FunctionLayer.Entity.WoodMaterial;
import FunctionLayer.Entity.Role;
import FunctionLayer.Exceptions.LoginException;
import DBAccess.DBFacade;
import FunctionLayer.Entity.MetalMaterial;
import java.util.List;

public class LogicFacade {

    public static User login(String email, String password) throws LoginException {
        return DBFacade.login(email, password);
    }

    public static User createUser(String email, String password, Role role) throws LoginException {
        return DBFacade.createUser(email, password, role);
    }
    
    public static MetalMaterial getMetalMaterial(int itemNumber) throws LoginException {
        return DBFacade.getMetalMaterial(itemNumber);
    }
    
    public static WoodMaterial getWoodMaterial(int itemNumber) throws LoginException {
        return DBFacade.getWoodMaterial(itemNumber);
    }

    public static List<WoodMaterial> getAllMaterials() throws LoginException {
        return DBFacade.getAllMaterials();
    }

    public static List<WoodMaterial> getAngledroofs() throws LoginException {
        return DBFacade.getAngledroofs();
    }

    public static List<WoodMaterial> getFlatroofs() throws LoginException {
        return DBFacade.getFlatroofs();
    }

    public static List<WoodMaterial> getSideMaterials() throws LoginException {
        return DBFacade.getSideMaterials();
    }

    public static List<Order> getAllOrders() throws LoginException {
        return DBFacade.getAllOrders();
    }
    public static List<Order> getAllOrdersByUser(int id) throws LoginException {
        return DBFacade.getAllOrdersByUser(id);
    }
    
    public static void addOrderToDB(Order order) throws LoginException {
        DBFacade.addOrderToDB(order);
    }

    public static Order getOrderByOrderID(int id) throws LoginException {
        return DBFacade.getOrderByOrderID(id);
    }

    public static void editOrderStatus(Order order, String status) throws LoginException {
        DBFacade.editOrderStatus(order, status);
    }

    public static void editOrderPrice(Order order, double newPrice) throws LoginException {
        DBFacade.editOrderPrice(order, newPrice);
    }

    public static void createShed(Order order) throws LoginException {
        CreateCarport.createShed(order);
    }

    public static Order createOrder(int l, int w, int h, int a, int roofTypeNumber) throws LoginException {
        return CreateCarport.createOrder(l, w, h, a, roofTypeNumber);
    }

    public static void createCarport(Order order) throws LoginException {
        CreateCarport.createCarport(order);
    }
}
