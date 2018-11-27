package FunctionLayer;

import DBAccess.MaterialAndOrderMapper;
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

    public static List<WoodMaterial> getAllMaterials() throws LoginSampleException {
        return MaterialAndOrderMapper.getAllMaterials();
    }

    public static List<WoodMaterial> getAngledroofs() throws LoginSampleException {
        return MaterialAndOrderMapper.getAngledRoofMat();
    }

    public static List<WoodMaterial> getFlatroofs() throws LoginSampleException {
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
        WoodMaterial concrete = MaterialAndOrderMapper.getMaterial(RulesAndConstants.PREFERRED_MATERIAL_CONCRETE);
        double concreteAmount = Calculators.concreteAmountCalc(postsAmount);
        order.getCarportWoodMaterials().put(RulesAndConstants.CARPORT_CONCRETE_DESCRIPTION, new WoodDetails(concrete, concreteAmount, 0));
        WoodMaterial mount = MaterialAndOrderMapper.getMaterial(RulesAndConstants.PREFERRED_MATERIAL_MOUNT);
        double postMountAmount = Calculators.mountPerPost(postsAmount);
        order.getCarportWoodMaterials().put(RulesAndConstants.CARPORT_MOUNTS_POST_DESCRIPTION, new WoodDetails(mount, postMountAmount, 0));
        
        double rafterMountAmount = Calculators.mountPerRafter(bottomRafterAmount);
        order.getCarportWoodMaterials().put(RulesAndConstants.CARPORT_MOUNTS_RAFTERS_DESCRIPTION, new WoodDetails(mount, rafterMountAmount, 0));
        
        WoodMaterial rem = MaterialAndOrderMapper.getMaterial(RulesAndConstants.PREFERRED_MATERIAL_REM);
        double remLength = Calculators.remLengthCalc(order.getLength());
        order.getCarportWoodMaterials().put(RulesAndConstants.CARPORT_REM_DESCRIPTION, new WoodDetails(rem, 2, remLength));
    }

    private static double createFlatRoofRafters(Order order) throws LoginSampleException {
        WoodMaterial rafter = MaterialAndOrderMapper.getMaterial(RulesAndConstants.PREFERRED_MATERIAL_RAFTERS);
        double raftersAmount = Calculators.flatRoofRafterAmountCalc(order.getLength());
        double cmLengthEach = Calculators.rafterBottomLengthCalc(order.getWidth());
        order.getCarportWoodMaterials().put(RulesAndConstants.CARPORT_RAFTER_FLATROOF_DESCRIPTON, new WoodDetails(rafter, raftersAmount, cmLengthEach));
        return raftersAmount;
    }

    private static double createPosts(Order order) throws LoginSampleException {
        WoodMaterial post = MaterialAndOrderMapper.getMaterial(RulesAndConstants.PREFERRED_MATERIAL_POSTS);
        double postsAmount = Calculators.postsAmountCalc(order.getLength(), order.getWidth());
        double cmLengthEach = Calculators.postsLengthCalc(order.getHeight());
        order.getCarportWoodMaterials().put(RulesAndConstants.CARPORT_POSTS_DESCRIPTION, new WoodDetails(post, postsAmount, cmLengthEach ));

        return postsAmount;
    }

    private static double createAngledRoofRafters(Order order, int roofAngle) throws LoginSampleException {
        WoodMaterial rafter = MaterialAndOrderMapper.getMaterial(RulesAndConstants.PREFERRED_MATERIAL_RAFTERS);
        double bottomRafterAmount = Calculators.angledRoofRafterBottomAmountCalc(order.getLength());
        double cmLengthEachBottomRafter = Calculators.rafterBottomLengthCalc(order.getWidth());
        order.getCarportWoodMaterials().put(RulesAndConstants.CARPORT_RAFTER_ANGLEDROOF_BOTTOM_DESCRIPTION, new WoodDetails(rafter, bottomRafterAmount, cmLengthEachBottomRafter));

        double sideRafterAmount = Calculators.angledRoofRafterSidesAmountCalc(order.getLength());
        double cmLengthEachSideRafter = Calculators.angledRoofRafterSidesLengthCalc(order.getWidth(), roofAngle);
        order.getCarportWoodMaterials().put(RulesAndConstants.CARPORT_RAFTER_ANGLEDROOF_SIDE_DESCRIPTION, new WoodDetails(rafter, sideRafterAmount, cmLengthEachSideRafter));
        
        return bottomRafterAmount;
    }

}
