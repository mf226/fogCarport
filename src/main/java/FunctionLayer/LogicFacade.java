package FunctionLayer;

import DBAccess.MaterialAndOrderMapper;
import static DBAccess.MaterialAndOrderMapper.getAngledRoofMat;
import static DBAccess.MaterialAndOrderMapper.getFlatRoofMat;
import DBAccess.UserMapper;
import java.util.List;

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

    public static List<Material> getAngledroofs() throws LoginSampleException {
        return getAngledRoofMat();
    }

    public static List<Material> getFlatroofs() throws LoginSampleException {
        return getFlatRoofMat();
    }

    public static Order createFlatRoofCarport(int length, int width, int height) throws LoginSampleException {
        Order order = new Order(length, width, height);
        createFlatRoofRafters(order);
        createRestOfCarport(order);
        return order;
    }

    public static Order createAngledRoofCarport(int length, int width, int height, int roofAngle) throws LoginSampleException {
        Order order = new Order(length, width, height);
        createAngledRoofRafters(order, roofAngle);
        createRestOfCarport(order);
        return order;
    }
    
    private static void createRestOfCarport(Order order) throws LoginSampleException {
        double postsAmount = createPosts(order);
        Material concrete = MaterialAndOrderMapper.getMaterial(RulesAndConstants.PREFERRED_MATERIAL_CONCRETE);
        double concreteAmount = Calculators.concreteAmountCalc(postsAmount);
        order.getMaterials().add(new MaterialDetails(concrete, 0, concreteAmount, "Beton"));
    }

    private static void createFlatRoofRafters(Order order) throws LoginSampleException {
        Material rafter = MaterialAndOrderMapper.getMaterial(RulesAndConstants.PREFERRED_MATERIAL_RAFTERS);
        double raftersAmount = Calculators.flatRoofRafterAmountCalc(order.getLength());
        double cmLengthEach = Calculators.rafterBottomLengthCalc(order.getWidth());
        order.getMaterials().add(new MaterialDetails(rafter, cmLengthEach, raftersAmount, "Spær"));
    }

    private static double createPosts(Order order) throws LoginSampleException {
        Material post = MaterialAndOrderMapper.getMaterial(RulesAndConstants.PREFERRED_MATERIAL_POSTS);
        double postsAmount = Calculators.postsAmountCalc(order.getLength(), order.getWidth());
        double cmLengthEach = Calculators.postsLengthCalc(order.getHeight());
        order.getMaterials().add(new MaterialDetails(post, cmLengthEach, postsAmount, "Stolper"));
        
        return postsAmount;
    }

    private static void createAngledRoofRafters(Order order, int roofAngle) throws LoginSampleException {
        Material rafter = MaterialAndOrderMapper.getMaterial(RulesAndConstants.PREFERRED_MATERIAL_RAFTERS);
        double bottomRafterAmount = Calculators.angledRoofRafterBottomAmountCalc(order.getLength());
        double cmLengthEachBottomRafter = Calculators.rafterBottomLengthCalc(order.getWidth());
        order.getMaterials().add(new MaterialDetails(rafter, cmLengthEachBottomRafter, bottomRafterAmount, "Bund spær"));

        double sideRafterAmount = Calculators.angledRoofRafterSidesAmountCalc(order.getLength());
        double cmLengthEachSideRafter = Calculators.angledRoofRafterSidesLengthCalc(order.getWidth(), roofAngle);
        order.getMaterials().add(new MaterialDetails(rafter, cmLengthEachSideRafter, sideRafterAmount, "Side spær"));
    }

}
