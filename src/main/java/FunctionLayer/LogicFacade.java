package FunctionLayer;

import DBAccess.MaterialAndOrderMapper;
import DBAccess.UserMapper;
import java.sql.SQLException;
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

    public static Order createFlatRoofCarport(int length, int width, int height, int roofAngle, int roofType) throws LoginSampleException {
        Order order = new Order(length, width, height, roofAngle);
        double amountOfRafters = createFlatRoofRafters(order);
        createRestOfCarport(order, amountOfRafters, roofType);
        return order;
    }

    public static Order createAngledRoofCarport(int length, int width, int height, int roofAngle, int roofType) throws LoginSampleException {
        Order order = new Order(length, width, height, roofAngle);
        double amountOfRafters = createAngledRoofRafters(order, roofAngle);
        createRestOfCarport(order, amountOfRafters, roofType);
        return order;
    }

    private static void createRestOfCarport(Order order, double bottomRafterAmount, int roofType) throws LoginSampleException {
        double postsAmount = createPosts(order);
        MetalMaterial concrete = MaterialAndOrderMapper.getMetalMaterial(RulesAndConstants.PREFERRED_MATERIAL_CONCRETE);
        double concreteAmount = Calculators.concreteAmountCalc(postsAmount);

        order.getCarportMetalMaterials().put(RulesAndConstants.CARPORT_CONCRETE_DESCRIPTION, new MetalDetails(concrete, concreteAmount));

        MetalMaterial mount = MaterialAndOrderMapper.getMetalMaterial(RulesAndConstants.PREFERRED_MATERIAL_MOUNT);
        double postMountAmount = Calculators.mountPerPost(postsAmount);
        order.getCarportMetalMaterials().put(RulesAndConstants.CARPORT_MOUNTS_POST_DESCRIPTION, new MetalDetails(mount, postMountAmount));

        double rafterMountAmount = Calculators.mountPerRafter(bottomRafterAmount);
        order.getCarportMetalMaterials().put(RulesAndConstants.CARPORT_MOUNTS_RAFTERS_DESCRIPTION, new MetalDetails(mount, rafterMountAmount));

        WoodMaterial rem = MaterialAndOrderMapper.getWoodMaterial(RulesAndConstants.PREFERRED_MATERIAL_REM);
        double remLength = Calculators.remLengthCalc(order.getLength());

        order.getCarportWoodMaterials().put(RulesAndConstants.CARPORT_REM_DESCRIPTION, new WoodDetails(rem, 2, remLength)); //There are always 2 REMME

        MetalMaterial roof = MaterialAndOrderMapper.getMetalMaterial(roofType);
        double roofAmount = Calculators.calcRoof(order);
        order.getCarportMetalMaterials().put(RulesAndConstants.CARPORT_ROOF_DESCRIPTION, new MetalDetails(roof, roofAmount));

        MetalMaterial screw = MaterialAndOrderMapper.getMetalMaterial(RulesAndConstants.PREFERRED_MATERIAL_SCREWS);
        double screwAmount = Calculators.screwsAmountCalc(order);
        order.getCarportMetalMaterials().put(RulesAndConstants.SCREWS_DESCRIPTION, new MetalDetails(screw, screwAmount));
    }

    private static double createFlatRoofRafters(Order order) throws LoginSampleException {
        WoodMaterial rafter = MaterialAndOrderMapper.getWoodMaterial(RulesAndConstants.PREFERRED_MATERIAL_RAFTERS);
        double raftersAmount = Calculators.flatRoofRafterAmountCalc(order.getLength());
        double cmLengthEach = Calculators.rafterBottomLengthCalc(order.getWidth());
        order.getCarportWoodMaterials().put(RulesAndConstants.CARPORT_RAFTER_FLATROOF_DESCRIPTON, new WoodDetails(rafter, raftersAmount, cmLengthEach));
        return raftersAmount;
    }

    private static double createPosts(Order order) throws LoginSampleException {
        WoodMaterial post = MaterialAndOrderMapper.getWoodMaterial(RulesAndConstants.PREFERRED_MATERIAL_POSTS);
        double postsAmount = Calculators.postsAmountCalc(order.getLength(), order.getWidth());
        double cmLengthEach = Calculators.postsLengthCalc(order.getHeight());
        order.getCarportWoodMaterials().put(RulesAndConstants.CARPORT_POSTS_DESCRIPTION, new WoodDetails(post, postsAmount, cmLengthEach));

        return postsAmount;
    }

    private static double createAngledRoofRafters(Order order, int roofAngle) throws LoginSampleException {
        WoodMaterial rafter = MaterialAndOrderMapper.getWoodMaterial(RulesAndConstants.PREFERRED_MATERIAL_RAFTERS);
        double bottomRafterAmount = Calculators.angledRoofRafterBottomAmountCalc(order.getLength());
        double cmLengthEachBottomRafter = Calculators.rafterBottomLengthCalc(order.getWidth());
        order.getCarportWoodMaterials().put(RulesAndConstants.CARPORT_RAFTER_ANGLEDROOF_BOTTOM_DESCRIPTION, new WoodDetails(rafter, bottomRafterAmount, cmLengthEachBottomRafter));

        double sideRafterAmount = Calculators.angledRoofRafterSidesAmountCalc(order.getLength());
        double cmLengthEachSideRafter = Calculators.angledRoofRafterSidesLengthCalc(order.getWidth(), roofAngle);
        order.getCarportWoodMaterials().put(RulesAndConstants.CARPORT_RAFTER_ANGLEDROOF_SIDE_DESCRIPTION, new WoodDetails(rafter, sideRafterAmount, cmLengthEachSideRafter));

        return bottomRafterAmount;
    }

    public static List<WoodMaterial> getSideMaterials() throws LoginSampleException {
        return MaterialAndOrderMapper.getSideMat();
    }

    public static List<Order> getAllOrders() throws LoginSampleException {
        return MaterialAndOrderMapper.getAllOrders();
    }

    public static void addOrderToDB(Order order) throws LoginSampleException, SQLException, ClassNotFoundException {
        MaterialAndOrderMapper.addOrderToDB(order);
    }
//
//    public static int getUserIDByEmail(String email) throws LoginSampleException, LoginSampleException, ClassNotFoundException {
//        return UserMapper.getUserIDByEmail(email);
//    }

}
