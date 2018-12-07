/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionLayer;

import java.util.HashMap;

/**
 *
 * @author porse
 */
public class Calculators {

    public static double postsAmountCalc(int carportLength, int carportWidth) {
        if (carportLength < RulesAndConstants.MINLENGTH || carportLength > RulesAndConstants.MAXLENGTH || carportWidth < RulesAndConstants.MINWIDTH || carportWidth > RulesAndConstants.MAXWIDTH) {
            throw new IllegalArgumentException("Number exceeds parameters!");
        }
        int posts = 4; // A carport has atleast 4 posts.
        if (carportLength > RulesAndConstants.MINLENGTH) {
            posts = (int) ((carportLength * 2) / RulesAndConstants.DISTANCE_BETWEEN_POSTS) - 2; 
//        if (width >= 500) { //if width is bigger than 5
//            posts++;
        }
//        if (posts < amountInStock) {
//            throw new NotInStockException("Not enough posts in stock");
//        }
        return posts; //both sides
    }

    public static double postsLengthCalc(int carportHeight) {
        if (carportHeight < RulesAndConstants.MINHEIGHT || carportHeight > RulesAndConstants.MAXHEIGHT) {
            throw new IllegalArgumentException("Number exceeds parameters!");
        }
        double postLength = carportHeight + RulesAndConstants.LENGTH_UNDER_GROUND;

        return postLength;
    }

    public static double flatRoofRafterAmountCalc(int carportLength) {
        if (carportLength < RulesAndConstants.MINLENGTH || carportLength > RulesAndConstants.MAXLENGTH) {
            throw new IllegalArgumentException("Number exceeds parameters!");
        }
        int rafter = (int) (carportLength / RulesAndConstants.DISTANCE_BETWEEN_FLATROOF_RAFTERS + 1); //+1 for first rafter
        return rafter;
    }

    public static double rafterBottomLengthCalc(int carportWidth) {
        if (carportWidth < RulesAndConstants.MINWIDTH || carportWidth > RulesAndConstants.MAXWIDTH) {
            throw new IllegalArgumentException("Number exceeds parameters!");
        }
        double rafterLength = carportWidth + RulesAndConstants.ROOF_WIDTH_EXTRA;
        return rafterLength;
    }

    public static double angledRoofRafterBottomAmountCalc(int carportLength) {
        if (carportLength < RulesAndConstants.MINLENGTH || carportLength > RulesAndConstants.MAXLENGTH) {
            throw new IllegalArgumentException("Number exceeds parameters!");
        }
        int rafterBottomAmount = (int) (carportLength / RulesAndConstants.DISTANCE_BETWEEN_ANGLEDROOF_RAFTERS + 1);

        return rafterBottomAmount;
    }

    public static double angledRoofRafterSidesAmountCalc(int carportLength) {
        if (carportLength < RulesAndConstants.MINLENGTH || carportLength > RulesAndConstants.MAXLENGTH) {
            throw new IllegalArgumentException("Number exceeds parameters!");
        }
        int rafterBottomAmount = (int) (carportLength / RulesAndConstants.DISTANCE_BETWEEN_ANGLEDROOF_RAFTERS + 1);

        return rafterBottomAmount * 2; //both sides
    }

    public static double angledRoofRafterSidesLengthCalc(int carportWidth, int roofAngle) {
        if (carportWidth < RulesAndConstants.MINLENGTH || carportWidth > RulesAndConstants.MAXLENGTH || roofAngle < RulesAndConstants.MINROOFANGLE || roofAngle > RulesAndConstants.MAXROOFANGLE) {
            throw new IllegalArgumentException("Number exceeds parameters!");
        }
        double rafterSideLength = (int) ((rafterBottomLengthCalc(carportWidth) / 2) / Math.cos(degreesToRad(roofAngle))); // Cosinus relation for retviklet trekant

        return rafterSideLength;
    }

    private static double degreesToRad(int deg) {
        double rad = (deg * Math.PI) / 180;
        return rad;
    }

    public static double concreteAmountCalc(double amountOfPosts) {
        double concreteAmountKg = amountOfPosts * RulesAndConstants.CONCRETE_PER_POST;
        return concreteAmountKg;
    }

    public static double mountPerPost(double amountOfPosts) {
        return amountOfPosts * RulesAndConstants.MOUNT_PER_POST;
    }

    public static double mountPerRafter(double amountOfRafters) {
        return amountOfRafters * RulesAndConstants.MOUNT_PER_RAFTER;
    }

    public static double remLengthCalc(int carportLength) {
        if (carportLength < RulesAndConstants.MINLENGTH || carportLength > RulesAndConstants.MAXLENGTH) {
            throw new IllegalArgumentException("Number exceeds parameters!");
        }
        return carportLength + RulesAndConstants.ROOF_LENGTH_EXTRA;
    }

    public static double screwsAmountCalc(Order order) {
        double screws = 0;
        screws += order.getCarportMetalMaterials().get(RulesAndConstants.CARPORT_MOUNTS_POST_DESCRIPTION).getAmount() * RulesAndConstants.SCREWS_PER_MOUNT;
        screws += order.getCarportMetalMaterials().get(RulesAndConstants.CARPORT_MOUNTS_RAFTERS_DESCRIPTION).getAmount() * RulesAndConstants.SCREWS_PER_MOUNT;

        return screws;
    }

    public static double screwsAmountCalcForShed(Order order) {
        double screws = screwsAmountCalc(order);
        screws += order.getShedWoodMaterials().get(RulesAndConstants.SHED_WALL_DESCRIPTION).getAmount() * RulesAndConstants.SCREWS_PER_WALL;

        return screws;

    }

    public static double calcRoof(Order order) {
        if (order.getAngle() == 0) {
            double squaremeters = (order.getCarportWoodMaterials().get(RulesAndConstants.CARPORT_REM_DESCRIPTION).getCmLengthEach() / 100)
                    * (order.getCarportWoodMaterials().get(RulesAndConstants.CARPORT_RAFTER_FLATROOF_DESCRIPTON).getCmLengthEach() / 100);
            return (int) squaremeters + 1;
        } else {
            double squaremeters = (order.getCarportWoodMaterials().get(RulesAndConstants.CARPORT_REM_DESCRIPTION).getCmLengthEach() / 100)
                    * (order.getCarportWoodMaterials().get(RulesAndConstants.CARPORT_RAFTER_ANGLEDROOF_SIDE_DESCRIPTION).getCmLengthEach() / 100);
            return (int) (squaremeters * RulesAndConstants.TILES_PER_SQUAREMETER) + 1;

        }
    }

    public static double shedWallCalc(int shedLength, int shedWidth, double materialWidth) {
        return (int) ((shedWidth / materialWidth) + (shedLength / materialWidth)) + 1;
    }

    static double shedWallLength(int height) {
        return height;
    }

}
