package DBAccess;

import FunctionLayer.Entity.MetalMaterial;
import FunctionLayer.Exceptions.LoginException;
import FunctionLayer.Entity.Order;
import FunctionLayer.Entity.Role;
import FunctionLayer.Entity.User;
import FunctionLayer.Entity.WoodMaterial;
import java.util.List;

public class DBFacade {

    public static User login(String email, String password) throws LoginException {
        return UserMapper.login(email, password);
    }

    public static User createUser(String email, String password, Role role) throws LoginException {
        User user = new User(email, password, role);
        UserMapper.createUser(user);
        return user;
    }
    
    public static MetalMaterial getMetalMaterial(int itemNumber) throws LoginException {
        return MaterialMapper.getMetalMaterial(itemNumber);
    }
    
    public static WoodMaterial getWoodMaterial(int itemNumber) throws LoginException {
        return MaterialMapper.getWoodMaterial(itemNumber);
    }

    public static List<WoodMaterial> getAllMaterials() throws LoginException {
        return MaterialMapper.getAllMaterials();
    }

    public static List<WoodMaterial> getAngledroofs() throws LoginException {
        return MaterialMapper.getAngledRoofMat();
    }

    public static List<WoodMaterial> getFlatroofs() throws LoginException {
        return MaterialMapper.getFlatRoofMat();
    }

    public static List<WoodMaterial> getSideMaterials() throws LoginException {
        return MaterialMapper.getSideMat();
    }

    public static List<Order> getAllOrders() throws LoginException {
        return OrderMapper.getAllOrders();
    }
    public static List<Order> getAllOrdersByUser(int id) throws LoginException {
        return OrderMapper.getAllOrdersByUser(id);
    }

    public static void addOrderToDB(Order order) throws LoginException {
        OrderMapper.addOrderToDB(order);
    }

    public static Order getOrderByOrderID(int id) throws LoginException {
        return OrderMapper.getOrderByOrderID(id);
    }

    public static void editOrderStatus(Order order, String status) throws LoginException {
        OrderMapper.editOrderStatus(order, status);
    }

    public static void editOrderPrice(Order order, double newPrice) throws LoginException {
        OrderMapper.editOrderPrice(order, newPrice);
    }

}
