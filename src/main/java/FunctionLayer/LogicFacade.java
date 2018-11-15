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
    
    public static List<Material> createCarport(int length, int width, int height) throws LoginSampleException {
        Order order = new Order(length, width, height);
        Material posts = MaterialAndOrderMapper.getMaterial(2);
        posts.setAmount(Calculators.postsCalc(length, width));
        order.getMaterials().add(posts);
        
        Material rafters = MaterialAndOrderMapper.getMaterial(1);
        rafters.setAmount(Calculators.rafterCalc(length));
        order.getMaterials().add(rafters);
        return order.getMaterials();
    }
}
