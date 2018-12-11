package DBAccess;

import FunctionLayer.Exceptions.LoginSampleException;
import FunctionLayer.Entity.Order;
import FunctionLayer.Entity.Role;
import FunctionLayer.Entity.User;
import FunctionLayer.Entity.WoodMaterial;
import java.util.List;

public class DBFacade {

    public static User login(String email, String password) throws LoginSampleException {
        return UserMapper.login(email, password);
    }

    public static User createUser(String email, String password, Role role) throws LoginSampleException {
        User user = new User(email, password, role);
        UserMapper.createUser(user);
        return user;
    }

    public static List<WoodMaterial> getAllMaterials() throws LoginSampleException {
        return MaterialMapper.getAllMaterials();
    }

    public static List<WoodMaterial> getAngledroofs() throws LoginSampleException {
        return MaterialMapper.getAngledRoofMat();
    }

    public static List<WoodMaterial> getFlatroofs() throws LoginSampleException {
        return MaterialMapper.getFlatRoofMat();
    }

    public static List<WoodMaterial> getSideMaterials() throws LoginSampleException {
        return MaterialMapper.getSideMat();
    }

    public static List<Order> getAllOrders() throws LoginSampleException {
        return OrderMapper.getAllOrders();
    }

    public static void addOrderToDB(Order order) throws LoginSampleException {
        OrderMapper.addOrderToDB(order);
    }

    public static Order getOrderByOrderID(int id) throws LoginSampleException {
        return OrderMapper.getOrderByOrderID(id);
    }

    public static void editOrderStatus(Order order, String status) throws LoginSampleException {
        OrderMapper.editOrderStatus(order, status);
    }

    public static void editOrderPrice(Order order, double newPrice) throws LoginSampleException {
        OrderMapper.editOrderPrice(order, newPrice);
    }

}
