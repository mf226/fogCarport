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
        return MaterialAndOrderMapper.getAngledRoofMat();
    }

    public static List<Material> getFlatroofs() throws LoginSampleException {
        return MaterialAndOrderMapper.getFlatRoofMat();
    }

    public static Order createFlatRoofCarport(int length, int width, int height, int roofAngle) throws LoginSampleException {
        Order order = new Order(length, width, height, roofAngle);
        double amountOfRafters = createFlatRoofRafters(order);
        createRestOfCarport(order, amountOfRafters);
        return order;
    }

    public static Order createAngledRoofCarport(int length, int width, int height, int roofAngle) throws LoginSampleException {
        Order order = new Order(length, width, height, roofAngle);
        double amountOfRafters = createAngledRoofRafters(order, roofAngle);
        createRestOfCarport(order, amountOfRafters);
        return order;
    }

    private static void createRestOfCarport(Order order, double bottomRafterAmount) throws LoginSampleException {
        double postsAmount = createPosts(order);
        Material concrete = MaterialAndOrderMapper.getMaterial(RulesAndConstants.PREFERRED_MATERIAL_CONCRETE);
        double concreteAmount = Calculators.concreteAmountCalc(postsAmount);
        order.getMaterials().add(new MaterialDetails(concrete, 0, concreteAmount, "Cement"));
        
        Material mount = MaterialAndOrderMapper.getMaterial(RulesAndConstants.PREFERRED_MATERIAL_MOUNT);
        double postMountAmount = Calculators.mountPerPost(postsAmount);
        order.getMaterials().add(new MaterialDetails(mount, 0, postMountAmount, "Beslag stolper/rem"));
        
        double rafterMountAmount = Calculators.mountPerRafter(bottomRafterAmount);
        order.getMaterials().add(new MaterialDetails(mount, 0, rafterMountAmount, "Beslag rem/spær"));
        
        Material rem = MaterialAndOrderMapper.getMaterial(RulesAndConstants.PREFERRED_MATERIAL_REM);
        double remLength = Calculators.remLengthCalc(order.getLength());
        order.getMaterials().add(new MaterialDetails(rem, remLength, 2, "Remme"));
    }

    private static double createFlatRoofRafters(Order order) throws LoginSampleException {
        Material rafter = MaterialAndOrderMapper.getMaterial(RulesAndConstants.PREFERRED_MATERIAL_RAFTERS);
        double raftersAmount = Calculators.flatRoofRafterAmountCalc(order.getLength());
        double cmLengthEach = Calculators.rafterBottomLengthCalc(order.getWidth());
        order.getMaterials().add(new MaterialDetails(rafter, cmLengthEach, raftersAmount, "Spær"));
        return raftersAmount;
    }

    private static double createPosts(Order order) throws LoginSampleException {
        Material post = MaterialAndOrderMapper.getMaterial(RulesAndConstants.PREFERRED_MATERIAL_POSTS);
        double postsAmount = Calculators.postsAmountCalc(order.getLength(), order.getWidth());
        double cmLengthEach = Calculators.postsLengthCalc(order.getHeight());
        order.getMaterials().add(new MaterialDetails(post, cmLengthEach, postsAmount, "Stolper"));

        return postsAmount;
    }

    private static double createAngledRoofRafters(Order order, int roofAngle) throws LoginSampleException {
        Material rafter = MaterialAndOrderMapper.getMaterial(RulesAndConstants.PREFERRED_MATERIAL_RAFTERS);
        double bottomRafterAmount = Calculators.angledRoofRafterBottomAmountCalc(order.getLength());
        double cmLengthEachBottomRafter = Calculators.rafterBottomLengthCalc(order.getWidth());
        order.getMaterials().add(new MaterialDetails(rafter, cmLengthEachBottomRafter, bottomRafterAmount, "Bund spær"));

        double sideRafterAmount = Calculators.angledRoofRafterSidesAmountCalc(order.getLength());
        double cmLengthEachSideRafter = Calculators.angledRoofRafterSidesLengthCalc(order.getWidth(), roofAngle);
        order.getMaterials().add(new MaterialDetails(rafter, cmLengthEachSideRafter, sideRafterAmount, "Side spær"));
        
        return bottomRafterAmount;
    }

}
