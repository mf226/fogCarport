package FunctionLayer;

import FunctionLayer.Entity.MetalMaterial;
import FunctionLayer.Entity.Order;
import FunctionLayer.Entity.WoodMaterial;
import FunctionLayer.Entity.WoodDetails;
import FunctionLayer.Entity.MetalDetails;
import FunctionLayer.Exceptions.LoginException;
import FunctionLayer.Entity.MaterialDetails;
import java.util.ArrayList;
import java.util.HashMap;

public class createCarport {

    /**
     * Creates order from given data
     *
     * @param int length
     * @param int width
     * @param int height
     * @param int roofAngle
     * @param int roofType
     *
     * @throws LoginException
     * @return Order
     */
    public static Order createOrder(int length, int width, int height, int roofAngle, int roofType) throws LoginException {
        Order order = new Order(length, width, height, roofAngle, roofType);
        createCarport(order);
        return order;
    }

    /**
     * Creates carport from given Order
     *
     * @param Order
     * @throws LoginException
     */
    public static void createCarport(Order order) throws LoginException {
        if (order.getAngle() == 0) {
            createFlatRoofRafters(order);
        } else {
            createAngledRoofRafters(order);
        }

        addAllWoodMaterialsToList(order);
        addAllMetalMaterialsToList(order);
    }

    /**
     * Puts a List of MetalMaterials inside given Order
     *
     * @param Order
     * @throws LoginException
     */
    private static void addAllMetalMaterialsToList(Order order) throws LoginException {
        double concreteAmount = Calculators.concreteAmountCalc(order.getPostsAmount());
        double postMountAmount = Calculators.mountPerPost(order.getPostsAmount());
        double roofAmount = Calculators.calcRoof(order);
        double rafterMountAmount = Calculators.mountPerRafter(order.getAmountOfRoofRafters());

        MetalMaterial concrete = LogicFacade.getMetalMaterial(RulesAndConstants.PREFERRED_MATERIAL_CONCRETE);
        MetalMaterial mount = LogicFacade.getMetalMaterial(RulesAndConstants.PREFERRED_MATERIAL_MOUNT);
        MetalMaterial roof = LogicFacade.getMetalMaterial(order.getRoofType());
        MetalMaterial screw = LogicFacade.getMetalMaterial(RulesAndConstants.PREFERRED_MATERIAL_SCREWS);

        ArrayList<MaterialDetails> list = new ArrayList();

        list.add(new MetalDetails(concrete, concreteAmount, RulesAndConstants.CARPORT_CONCRETE_DESCRIPTION));
        list.add(new MetalDetails(mount, postMountAmount, RulesAndConstants.CARPORT_MOUNTS_POST_DESCRIPTION));
        list.add(new MetalDetails(roof, roofAmount, RulesAndConstants.CARPORT_ROOF_DESCRIPTION));
        list.add(new MetalDetails(mount, rafterMountAmount, RulesAndConstants.CARPORT_MOUNTS_RAFTERS_DESCRIPTION));

        list.forEach(m -> putIntoList(order.getCarportMetalMaterials(), m));

        MetalDetails m = new MetalDetails(screw, Calculators.screwsAmountCalc(order), RulesAndConstants.SCREWS_DESCRIPTION);
        order.getCarportMetalMaterials().put(m.getDescription(), m);
    }

    /**
     * Puts a List of WoodMaterials inside given Order
     *
     * @param Order
     * @throws LoginException
     */
    private static void addAllWoodMaterialsToList(Order order) throws LoginException {
        //Calculate lengths
        double cmLengthEach = Calculators.postsLengthCalc(order.getHeight());
        double remLength = Calculators.remLengthCalc(order.getLength());
        //Get materials from DB
        WoodMaterial rem = LogicFacade.getWoodMaterial(RulesAndConstants.PREFERRED_MATERIAL_REM);
        WoodMaterial post = LogicFacade.getWoodMaterial(RulesAndConstants.PREFERRED_MATERIAL_POSTS);
        //Add to materialsList
        putIntoList(order.getCarportWoodMaterials(), new WoodDetails(post, order.getPostsAmount(), cmLengthEach, RulesAndConstants.CARPORT_POSTS_DESCRIPTION));
        putIntoList(order.getCarportWoodMaterials(), new WoodDetails(rem, 2, remLength, RulesAndConstants.CARPORT_REM_DESCRIPTION));

    }

    /**
     * Adds a List of rafters to given Order with flat roof
     *
     * @param Order
     * @throws LoginException
     */
    private static void createFlatRoofRafters(Order order) throws LoginException {
        WoodMaterial rafter = LogicFacade.getWoodMaterial(RulesAndConstants.PREFERRED_MATERIAL_RAFTERS);
        double cmLengthEach = Calculators.rafterBottomLengthCalc(order.getWidth());

        putIntoList(order.getCarportWoodMaterials(), new WoodDetails(rafter, order.getAmountOfRoofRafters(), cmLengthEach, RulesAndConstants.CARPORT_RAFTER_FLATROOF_DESCRIPTON));
    }

    /**
     * Adds a List of rafters to given Order with angled roof
     *
     * @param Order
     * @throws LoginException
     */
    private static void createAngledRoofRafters(Order order) throws LoginException {
        double cmLengthEachBottomRafter = Calculators.rafterBottomLengthCalc(order.getWidth());
        double sideRafterAmount = Calculators.angledRoofRafterSidesAmountCalc(order.getLength());
        double cmLengthEachSideRafter = Calculators.angledRoofRafterSidesLengthCalc(order.getWidth(), order.getAngle());
        WoodMaterial rafter = LogicFacade.getWoodMaterial(RulesAndConstants.PREFERRED_MATERIAL_RAFTERS);

        putIntoList(order.getCarportWoodMaterials(), new WoodDetails(rafter, order.getAmountOfRoofRafters(), cmLengthEachBottomRafter, RulesAndConstants.CARPORT_RAFTER_ANGLEDROOF_BOTTOM_DESCRIPTION));
        putIntoList(order.getCarportWoodMaterials(), new WoodDetails(rafter, sideRafterAmount, cmLengthEachSideRafter, RulesAndConstants.CARPORT_RAFTER_ANGLEDROOF_SIDE_DESCRIPTION));
    }

    /**
     * Adds a shed to given Order
     *
     * @param Order
     * @throws LoginException
     */
    public static void createShed(Order order) throws LoginException {
        MetalMaterial screw = LogicFacade.getMetalMaterial(RulesAndConstants.PREFERRED_MATERIAL_SCREWS);
        MetalMaterial door = LogicFacade.getMetalMaterial(RulesAndConstants.PREFERRED_MATERIAL_DOOR);
        WoodMaterial wallMaterial = LogicFacade.getWoodMaterial(order.getWallType());
        double wallAmount = Calculators.shedWallCalc(order.getShedLength(), order.getShedWidth(), wallMaterial.getTopsideWidth());
        double wallLengthEach = Calculators.shedWallLength(order.getHeight());

        putIntoList(order.getShedWoodMaterials(), new WoodDetails(wallMaterial, wallAmount, wallLengthEach, RulesAndConstants.SHED_WALL_DESCRIPTION));
        putIntoList(order.getShedMetalMaterials(), new MetalDetails(door, 1, RulesAndConstants.SHED_DOOR_DESCRIPTUIN));

        double screwAmount = Calculators.screwsAmountCalcForShed(order);
        putIntoList(order.getShedMetalMaterials(), new MetalDetails(screw, screwAmount, RulesAndConstants.SCREWS_DESCRIPTION));
    }

    /**
     * Puts MaterialDetails into given HashMap
     *
     * @param HashMap map
     * @param MaterialDetails mat
     */
    private static void putIntoList(HashMap map, MaterialDetails mat) {
        map.put(mat.getDescription(), mat);
    }

}
