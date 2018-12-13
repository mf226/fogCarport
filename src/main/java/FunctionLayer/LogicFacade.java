package FunctionLayer;

import FunctionLayer.Entity.User;
import FunctionLayer.Entity.Order;
import FunctionLayer.Entity.WoodMaterial;
import FunctionLayer.Entity.Role;
import FunctionLayer.Exceptions.LoginSampleException;
import DBAccess.DBFacade;
import FunctionLayer.Entity.MetalMaterial;
import java.util.List;

public class LogicFacade {

    public static User login(String email, String password) throws LoginSampleException {
        return DBFacade.login(email, password);
    }

    public static User createUser(String email, String password, Role role) throws LoginSampleException {
        return DBFacade.createUser(email, password, role);
    }
    
    public static MetalMaterial getMetalMaterial(int itemNumber) throws LoginSampleException {
        return DBFacade.getMetalMaterial(itemNumber);
    }
    
    public static WoodMaterial getWoodMaterial(int itemNumber) throws LoginSampleException {
        return DBFacade.getWoodMaterial(itemNumber);
    }

    public static List<WoodMaterial> getAllMaterials() throws LoginSampleException {
        return DBFacade.getAllMaterials();
    }

    public static List<WoodMaterial> getAngledroofs() throws LoginSampleException {
        return DBFacade.getAngledroofs();
    }

    public static List<WoodMaterial> getFlatroofs() throws LoginSampleException {
        return DBFacade.getFlatroofs();
    }

    public static List<WoodMaterial> getSideMaterials() throws LoginSampleException {
        return DBFacade.getSideMaterials();
    }

    public static List<Order> getAllOrders() throws LoginSampleException {
        return DBFacade.getAllOrders();
    }
    public static List<Order> getAllOrdersByUser(int id) throws LoginSampleException {
        return DBFacade.getAllOrdersByUser(id);
    }
    
    public static void addOrderToDB(Order order) throws LoginSampleException {
        DBFacade.addOrderToDB(order);
    }

    public static Order getOrderByOrderID(int id) throws LoginSampleException {
        return DBFacade.getOrderByOrderID(id);
    }

    public static void editOrderStatus(Order order, String status) throws LoginSampleException {
        DBFacade.editOrderStatus(order, status);
    }

    public static void editOrderPrice(Order order, double newPrice) throws LoginSampleException {
        DBFacade.editOrderPrice(order, newPrice);
    }

    public static void createShed(Order order) throws LoginSampleException {
        createCarport.createShed(order);
    }

    public static Order createOrder(int l, int w, int h, int a, int roofTypeNumber) throws LoginSampleException {
        return createCarport.createOrder(l, w, h, a, roofTypeNumber);
    }

    public static void createCarport(Order order) throws LoginSampleException {
        createCarport.createCarport(order);
    }
}
