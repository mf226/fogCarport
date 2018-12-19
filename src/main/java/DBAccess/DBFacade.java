package DBAccess;

import FunctionLayer.Entity.MetalMaterial;
import FunctionLayer.Exceptions.DBException;
import FunctionLayer.Entity.Order;
import FunctionLayer.Entity.Role;
import FunctionLayer.Entity.User;
import FunctionLayer.Entity.WoodMaterial;
import java.util.List;

public class DBFacade {

    public static User login(String email, String password) throws DBException {
        return UserMapper.login(email, password);
    }

    public static User createUser(String email, String password, Role role) throws DBException {
        User user = new User(email, password, role);
        UserMapper.createUser(user);
        return user;
    }
    
    public static MetalMaterial getMetalMaterial(int itemNumber) throws DBException {
        return MaterialMapper.getMetalMaterial(itemNumber);
    }
    
    public static WoodMaterial getWoodMaterial(int itemNumber) throws DBException {
        return MaterialMapper.getWoodMaterial(itemNumber);
    }

    public static List<WoodMaterial> getAllMaterials() throws DBException {
        return MaterialMapper.getAllMaterials();
    }

    public static List<WoodMaterial> getAngledroofs() throws DBException {
        return MaterialMapper.getAngledRoofMat();
    }

    public static List<WoodMaterial> getFlatroofs() throws DBException {
        return MaterialMapper.getFlatRoofMat();
    }

    public static List<WoodMaterial> getSideMaterials() throws DBException {
        return MaterialMapper.getSideMat();
    }

    public static List<Order> getAllOrders() throws DBException {
        return OrderMapper.getAllOrders();
    }
    public static List<Order> getAllOrdersByUser(int id) throws DBException {
        return OrderMapper.getAllOrdersByUser(id);
    }

    public static void addOrderToDB(Order order) throws DBException {
        OrderMapper.addOrderToDB(order);
    }

    public static Order getOrderByOrderID(int id) throws DBException {
        return OrderMapper.getOrderByOrderID(id);
    }

    public static void editOrderStatus(Order order, String status) throws DBException {
        OrderMapper.editOrderStatus(order, status);
    }

    public static void editOrderPrice(Order order, double newPrice) throws DBException {
        OrderMapper.editOrderPrice(order, newPrice);
    }

}
