/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionLayer;

/**
 *
 * @author porse
 */
public class Calculators {
    
    //All lengths are in CM.
    private static int DISTANCE_BETWEEN_POSTS = 100;
    private static int DISTANCE_BETWEEN_FLATROOF_RAFTERS = 50;
    private static int DISTANCE_BETWEEN_ANGLEDROOF_RAFTERS = 100;
    private static int LENGTH_UNDER_GROUND = 90;
    private static int ROOF_WIDTH_EXTRA = 50;

    public static int postsAmountCalc(int carportLength, int carportWidth) {
        int posts = carportLength / DISTANCE_BETWEEN_POSTS + 1; //+1 for the first post
//        if (width >= 500) { //if width is bigger than 5
//            posts++;
//        }
        return posts * 2; //both sides
    }
    
    public static int postsLengthCalc(int carportHeight) {
        int postLength = carportHeight + LENGTH_UNDER_GROUND;
        
        return postLength;        
    }
    
    public static int flatRoofRafterAmountCalc(int carportLength){
        int rafter = carportLength / DISTANCE_BETWEEN_FLATROOF_RAFTERS + 1; //+1 for first rafter
        
        return rafter;
    }
    
    public static int rafterBottomLengthCalc(int carportWidth) {
        int rafterLength = carportWidth + ROOF_WIDTH_EXTRA;
        
        return rafterLength;
    }
    
    public static int angledRoofRafterBottomAmountCalc(int carportLength) {
        int rafterBottomAmount = carportLength / DISTANCE_BETWEEN_ANGLEDROOF_RAFTERS +1;
        
        return rafterBottomAmount;
    }
    
    public static int angledRoofRafterSidesAmountCalc(int carportLength) {
        int rafterBottomAmount = carportLength / DISTANCE_BETWEEN_ANGLEDROOF_RAFTERS +1;
        
        return rafterBottomAmount * 2; //both sides
    }
    
    public static int angledRoofRafterSidesLengthCalc(int carportWidth, int roofAngle) {
        int rafterSideLength = (int) ((rafterBottomLengthCalc(carportWidth)/2) / Math.cos(degreesToRad(roofAngle))); // Cosinus relation for retviklet trekant
        
        return rafterSideLength; 
    }
    
    public static double degreesToRad(int deg){
        double rad = (deg*Math.PI) / 180;
        return rad;
    }
    
    
}
