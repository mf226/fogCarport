package FunctionLayer;

import DBAccess.MaterialAndOrderMapper;
import static DBAccess.MaterialAndOrderMapper.getAngledRoofMat;
import static DBAccess.MaterialAndOrderMapper.getFlatRoofMat;
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
    
    public static Order createFlatRoofCarport(int length, int width, int height) throws LoginSampleException {
        Order order = new Order(length, width, height);
        createPosts(order);        
        createFlatRoofRafters(order);
        
        return order;
    }
    
    public static Order createAngledRoofCarport(int length, int width, int height, int roofAngle) throws LoginSampleException {
        Order order = new Order(length, width, height);
        createPosts(order);
        createAngledRoofRafters(order, roofAngle);
        return order;
    }

    private static void createFlatRoofRafters(Order order) throws LoginSampleException {
        Material rafter = MaterialAndOrderMapper.getMaterial(1);
        int raftersAmount = Calculators.flatRoofRafterAmountCalc(order.getLength());
        int cmLengthEach = Calculators.rafterBottomLengthCalc(order.getWidth());
        order.getMaterials().add(new MaterialDetails(rafter, cmLengthEach, raftersAmount, "Spær"));
    }

    private static void createPosts(Order order) throws LoginSampleException {
        Material post = MaterialAndOrderMapper.getMaterial(2);
        int postsAmount = Calculators.postsAmountCalc(order.getLength(), order.getWidth());
        int cmLengthEach = Calculators.postsLengthCalc(order.getHeight());
        order.getMaterials().add(new MaterialDetails(post, cmLengthEach, postsAmount, "Stolper"));
    }

    private static void createAngledRoofRafters(Order order, int roofAngle) throws LoginSampleException {
        Material rafter = MaterialAndOrderMapper.getMaterial(1);
        int bottomRafterAmount = Calculators.angledRoofRafterBottomAmountCalc(order.getLength());
        int cmLengthEachBottomRafter = Calculators.rafterBottomLengthCalc(order.getWidth());
        order.getMaterials().add(new MaterialDetails(rafter, cmLengthEachBottomRafter, bottomRafterAmount, "Bund spær"));
        
        int sideRafterAmount = Calculators.angledRoofRafterSidesAmountCalc(order.getLength());
        int cmLengthEachSideRafter = Calculators.angledRoofRafterSidesLengthCalc(order.getWidth(), roofAngle);
        order.getMaterials().add(new MaterialDetails(rafter, cmLengthEachSideRafter, sideRafterAmount, "Side spær"));
    }
    
    public static List<Material> getAngledroofs() throws LoginSampleException{
        return getAngledRoofMat();
    }
    public static List<Material> getFlatroofs() throws LoginSampleException{
        return getFlatRoofMat();
    }
    
}
