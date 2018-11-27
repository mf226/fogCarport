/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionLayer;

import java.util.List;

/**
 *
 * @author porse
 */
public class Calculators {

    public static double postsAmountCalc(int carportLength, int carportWidth) {
        int posts = (int) (carportLength / RulesAndConstants.DISTANCE_BETWEEN_POSTS + 1); //+1 for the first post
//        if (width >= 500) { //if width is bigger than 5
//            posts++;
//        }
        return posts * 2; //both sides
    }

    public static double postsLengthCalc(int carportHeight) {
        double postLength = carportHeight + RulesAndConstants.LENGTH_UNDER_GROUND;

        return postLength;
    }

    public static double flatRoofRafterAmountCalc(int carportLength) {
        int rafter = (int) (carportLength / RulesAndConstants.DISTANCE_BETWEEN_FLATROOF_RAFTERS + 1); //+1 for first rafter

        return rafter;
    }

    public static double rafterBottomLengthCalc(int carportWidth) {
        double rafterLength = carportWidth + RulesAndConstants.ROOF_WIDTH_EXTRA;

        return rafterLength;
    }

    public static double angledRoofRafterBottomAmountCalc(int carportLength) {
        int rafterBottomAmount = (int) (carportLength / RulesAndConstants.DISTANCE_BETWEEN_ANGLEDROOF_RAFTERS + 1);

        return rafterBottomAmount;
    }

    public static double angledRoofRafterSidesAmountCalc(int carportLength) {
        int rafterBottomAmount = (int) (carportLength / RulesAndConstants.DISTANCE_BETWEEN_ANGLEDROOF_RAFTERS + 1);

        return rafterBottomAmount * 2; //both sides
    }

    public static double angledRoofRafterSidesLengthCalc(int carportWidth, int roofAngle) {
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
        return carportLength + RulesAndConstants.ROOF_LENGTH_EXTRA;
    }

//    public static double screwsAmountCalc(Order order) {
//        double screws = 0;
//        List<WoodDetails> materials = order.getMaterials();
//        for (int i = 0; i < materials.size(); i++) {
//            String description = materials.get(i).getUseDescription();
//            switch (description) {
//                case RulesAndConstants.CARPORT_MOUNTS_POST_DESCRIPTION:
//                    screws += materials.get(i).getAmount() * RulesAndConstants.SCREWS_PER_MOUNT;
//                    break;
//                case RulesAndConstants.CARPORT_MOUNTS_RAFTERS_DESCRIPTION:
//                    screws += materials.get(i).getAmount() * RulesAndConstants.SCREWS_PER_MOUNT;
//                    break;
//
//                default:
//                    break;
//            }
//        }
//        return screws;
//    }

}
