package FunctionLayer;

import DBAccess.MaterialAndOrderMapper;
import DBAccess.UserMapper;
import java.util.List;

/**
 * The purpose of LogicFacade is to...
 *
 * @author kasper
 */
public class LogicFacade {

    public static User login(String email, String password) throws LoginSampleException {
        return UserMapper.login(email, password);
    }

    public static User createUser(String email, String password, Role role) throws LoginSampleException {
        User user = new User(email, password, role);
        UserMapper.createUser(user);
        return user;
    }

    public static List<Material> getAllMaterials() throws LoginSampleException {
        return MaterialAndOrderMapper.getAllMaterials();
    }
    
    public static Order createCarport(int length, int width, int height) throws LoginSampleException {
        Order order = new Order(length, width, height);
        createPosts(order);        
        createRafters(order);
        
        return order;
    }

    private static void createRafters(Order order) throws LoginSampleException {
        Material rafter = MaterialAndOrderMapper.getMaterial(1);
        int raftersAmount = Calculators.rafterAmountCalc(order.getLength());
        int cmLengthEach = Calculators.rafterLengthCalc(order.getWidth());
        order.getMaterials().add(new MaterialDetails(rafter, cmLengthEach, raftersAmount, "Spær"));
    }

    private static void createPosts(Order order) throws LoginSampleException {
        Material post = MaterialAndOrderMapper.getMaterial(2);
        int postsAmount = Calculators.postsAmountCalc(order.getLength(), order.getWidth());
        int cmLengthEach = Calculators.postsLengthCalc(order.getHeight());
        order.getMaterials().add(new MaterialDetails(post, cmLengthEach, postsAmount, "Stolper"));
    }
    
    
}
