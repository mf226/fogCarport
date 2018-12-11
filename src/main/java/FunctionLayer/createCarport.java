package FunctionLayer;

import FunctionLayer.Entity.MetalMaterial;
import FunctionLayer.Entity.Order;
import FunctionLayer.Entity.WoodMaterial;
import FunctionLayer.Entity.WoodDetails;
import FunctionLayer.Entity.MetalDetails;
import FunctionLayer.Exceptions.LoginSampleException;
import DBAccess.MaterialMapper;

public class createCarport {

    public static Order createOrder(int length, int width, int height, int roofAngle, int roofType) throws LoginSampleException {
        Order order = new Order(length, width, height, roofAngle, roofType);
        createCarport(order);
        return order;
    }

    public static void createCarport(Order order) throws LoginSampleException {
        if (order.getAngle() == 0) {
            double amountOfRafters = createFlatRoofRafters(order);
            createRestOfCarport(order, amountOfRafters);
        } else {
            double amountOfRafters = createAngledRoofRafters(order);
            createRestOfCarport(order, amountOfRafters);
        }
    }

    private static void createRestOfCarport(Order order, double bottomRafterAmount) throws LoginSampleException {
        double postsAmount = createPosts(order);
        MetalMaterial concrete = MaterialMapper.getMetalMaterial(RulesAndConstants.PREFERRED_MATERIAL_CONCRETE);
        double concreteAmount = Calculators.concreteAmountCalc(postsAmount);

        order.getCarportMetalMaterials().put(RulesAndConstants.CARPORT_CONCRETE_DESCRIPTION, new MetalDetails(concrete, concreteAmount));

        MetalMaterial mount = MaterialMapper.getMetalMaterial(RulesAndConstants.PREFERRED_MATERIAL_MOUNT);
        double postMountAmount = Calculators.mountPerPost(postsAmount);
        order.getCarportMetalMaterials().put(RulesAndConstants.CARPORT_MOUNTS_POST_DESCRIPTION, new MetalDetails(mount, postMountAmount));

        double rafterMountAmount = Calculators.mountPerRafter(bottomRafterAmount);
        order.getCarportMetalMaterials().put(RulesAndConstants.CARPORT_MOUNTS_RAFTERS_DESCRIPTION, new MetalDetails(mount, rafterMountAmount));

        WoodMaterial rem = MaterialMapper.getWoodMaterial(RulesAndConstants.PREFERRED_MATERIAL_REM);
        double remLength = Calculators.remLengthCalc(order.getLength());

        order.getCarportWoodMaterials().put(RulesAndConstants.CARPORT_REM_DESCRIPTION, new WoodDetails(rem, 2, remLength)); //There are always 2 REMME

        MetalMaterial roof = MaterialMapper.getMetalMaterial(order.getRoofType());
        double roofAmount = Calculators.calcRoof(order);
        order.getCarportMetalMaterials().put(RulesAndConstants.CARPORT_ROOF_DESCRIPTION, new MetalDetails(roof, roofAmount));

        MetalMaterial screw = MaterialMapper.getMetalMaterial(RulesAndConstants.PREFERRED_MATERIAL_SCREWS);
        double screwAmount = Calculators.screwsAmountCalc(order);
        order.getCarportMetalMaterials().put(RulesAndConstants.SCREWS_DESCRIPTION, new MetalDetails(screw, screwAmount));
    }

    private static double createFlatRoofRafters(Order order) throws LoginSampleException {
        WoodMaterial rafter = MaterialMapper.getWoodMaterial(RulesAndConstants.PREFERRED_MATERIAL_RAFTERS);
        double raftersAmount = Calculators.flatRoofRafterAmountCalc(order.getLength());
        double cmLengthEach = Calculators.rafterBottomLengthCalc(order.getWidth());
        order.getCarportWoodMaterials().put(RulesAndConstants.CARPORT_RAFTER_FLATROOF_DESCRIPTON, new WoodDetails(rafter, raftersAmount, cmLengthEach));
        return raftersAmount;
    }

    private static double createPosts(Order order) throws LoginSampleException {
        WoodMaterial post = MaterialMapper.getWoodMaterial(RulesAndConstants.PREFERRED_MATERIAL_POSTS);
        double postsAmount = Calculators.postsAmountCalc(order.getLength(), order.getWidth());
        double cmLengthEach = Calculators.postsLengthCalc(order.getHeight());
        order.getCarportWoodMaterials().put(RulesAndConstants.CARPORT_POSTS_DESCRIPTION, new WoodDetails(post, postsAmount, cmLengthEach));

        return postsAmount;
    }

    private static double createAngledRoofRafters(Order order) throws LoginSampleException {
        WoodMaterial rafter = MaterialMapper.getWoodMaterial(RulesAndConstants.PREFERRED_MATERIAL_RAFTERS);
        double bottomRafterAmount = Calculators.angledRoofRafterBottomAmountCalc(order.getLength());
        double cmLengthEachBottomRafter = Calculators.rafterBottomLengthCalc(order.getWidth());
        order.getCarportWoodMaterials().put(RulesAndConstants.CARPORT_RAFTER_ANGLEDROOF_BOTTOM_DESCRIPTION, new WoodDetails(rafter, bottomRafterAmount, cmLengthEachBottomRafter));

        double sideRafterAmount = Calculators.angledRoofRafterSidesAmountCalc(order.getLength());
        double cmLengthEachSideRafter = Calculators.angledRoofRafterSidesLengthCalc(order.getWidth(), order.getAngle());
        order.getCarportWoodMaterials().put(RulesAndConstants.CARPORT_RAFTER_ANGLEDROOF_SIDE_DESCRIPTION, new WoodDetails(rafter, sideRafterAmount, cmLengthEachSideRafter));

        return bottomRafterAmount;
    }

    public static void createShed(Order order) throws LoginSampleException {
        WoodMaterial wallMaterial = MaterialMapper.getWoodMaterial(order.getWallType());
        double wallAmount = Calculators.shedWallCalc(order.getShedLength(), order.getShedWidth(), wallMaterial.getTopsideWidth());
        double wallLengthEach = Calculators.shedWallLength(order.getHeight());
        order.getShedWoodMaterials().put(RulesAndConstants.SHED_WALL_DESCRIPTION, new WoodDetails(wallMaterial, wallAmount, wallLengthEach));

        MetalMaterial screw = MaterialMapper.getMetalMaterial(RulesAndConstants.PREFERRED_MATERIAL_SCREWS);
        double screwAmount = Calculators.screwsAmountCalcForShed(order);
        order.getCarportMetalMaterials().put(RulesAndConstants.SCREWS_DESCRIPTION, new MetalDetails(screw, screwAmount));

        MetalMaterial door = MaterialMapper.getMetalMaterial(RulesAndConstants.PREFERRED_MATERIAL_DOOR);
        order.getShedMetalMaterials().put(RulesAndConstants.SHED_DOOR_DESCRIPTUIN, new MetalDetails(door, 1));
    }

}
