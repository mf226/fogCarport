package FunctionLayer;

import FunctionLayer.Entity.User;
import FunctionLayer.Entity.Order;
import FunctionLayer.Entity.WoodMaterial;
import FunctionLayer.Entity.Role;
import FunctionLayer.Exceptions.DBException;
import DBAccess.DBFacade;
import FunctionLayer.Entity.MetalMaterial;
import FunctionLayer.Exceptions.LogicException;
import java.util.List;

public class LogicFacade {

    public static User login(String email, String password) throws DBException {
        return DBFacade.login(email, password);
    }

    public static User createUser(String email, String password, Role role) throws DBException {
        return DBFacade.createUser(email, password, role);
    }
    
    public static MetalMaterial getMetalMaterial(int itemNumber) throws DBException {
        return DBFacade.getMetalMaterial(itemNumber);
    }
    
    public static WoodMaterial getWoodMaterial(int itemNumber) throws DBException {
        return DBFacade.getWoodMaterial(itemNumber);
    }

    public static List<WoodMaterial> getAllMaterials() throws DBException {
        return DBFacade.getAllMaterials();
    }

    public static List<WoodMaterial> getAngledroofs() throws DBException {
        return DBFacade.getAngledroofs();
    }

    public static List<WoodMaterial> getFlatroofs() throws DBException {
        return DBFacade.getFlatroofs();
    }

    public static List<WoodMaterial> getSideMaterials() throws DBException {
        return DBFacade.getSideMaterials();
    }

    public static List<Order> getAllOrders() throws DBException {
        return DBFacade.getAllOrders();
    }
    public static List<Order> getAllOrdersByUser(int id) throws DBException {
        return DBFacade.getAllOrdersByUser(id);
    }
    
    public static void addOrderToDB(Order order) throws DBException {
        DBFacade.addOrderToDB(order);
    }

    public static Order getOrderByOrderID(int id) throws DBException {
        return DBFacade.getOrderByOrderID(id);
    }

    public static void editOrderStatus(Order order, String status) throws DBException {
        DBFacade.editOrderStatus(order, status);
    }

    public static void editOrderPrice(Order order, double newPrice) throws DBException {
        DBFacade.editOrderPrice(order, newPrice);
    }

    public static void createShed(Order order) throws DBException  {
        createCarport.createShed(order);
    }

    public static Order createOrder(int l, int w, int h, int a, int roofTypeNumber) throws DBException, LogicException {
        return createCarport.createOrder(l, w, h, a, roofTypeNumber);
    }

    public static void createCarport(Order order) throws DBException, LogicException  {
        createCarport.createCarport(order);
    }
}
