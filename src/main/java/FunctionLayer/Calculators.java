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
    private static int LENGTH_UNDER_GROUND = 90;
    private static int ROOF_WIDTH_EXTRA = 50;

    public static int postsAmountCalc(int length, int width) {
        int posts = length / 100 + 1;
        if (width/2 >= 5) {
            posts++;
        }
        return posts * 2;
    }
    
    public static int postsLengthCalc(int carportHeight) {
        int postLength = carportHeight + LENGTH_UNDER_GROUND;
        
        return postLength;        
    }
    
    public static int rafterAmountCalc(int length){
        int rafter = length / 50 + 1;
        
        return rafter;
    }
    
    public static int rafterLengthCalc(int carportWidth) {
        int rafterLength = carportWidth + ROOF_WIDTH_EXTRA;
        
        return rafterLength;
    } 
}
