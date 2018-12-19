/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionLayerTest;

import FunctionLayer.Calculators;
import FunctionLayer.RulesAndConstants;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author porse
 */
public class CalculatorTests {

    public CalculatorTests() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    //Tester en succesfuld beregning
    @Test
    public void testPostAmountCalc() {
        //To test if it calculates the correct value.
        int carportLength = 200;
        int carportWidth = 300;
        int expResult = 4;
        int result = (int) Calculators.postsAmountCalc(carportLength, carportWidth);
        assertEquals(expResult, result);
        
        carportLength = 250;
        result = (int) Calculators.postsAmountCalc(carportLength, carportWidth);
        assertEquals(expResult, result);
        
        carportLength = 300;
        expResult = 6;
        result = (int) Calculators.postsAmountCalc(carportLength, carportWidth);
        assertEquals(expResult, result);
        
        carportLength = 400;
        expResult = 8;
        result = (int) Calculators.postsAmountCalc(carportLength, carportWidth);
        assertEquals(expResult, result);
        
        carportLength = 500;
        expResult = 10;
        result = (int) Calculators.postsAmountCalc(carportLength, carportWidth);
        assertEquals(expResult, result);
        
    }

    //Tester grænseværdiere
    @Test(expected = IllegalArgumentException.class)
    public void testPostAmountCalcException() {
        int caportLength = RulesAndConstants.MAXLENGTH + 1;
        int caportWidth = RulesAndConstants.MAXWIDTH;
        Calculators.postsAmountCalc(caportLength, caportWidth);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPostAmountCalcNegative() {
        int caportLength = RulesAndConstants.MINLENGTH - 1;
        int caportWidth = RulesAndConstants.MINWIDTH - 1;
        Calculators.postsAmountCalc(caportLength, caportWidth);

    }

    @Test
    public void testpostsLengthCalc() {
        int carportLength = 300;
        int expResult = 390;
        int result = (int) Calculators.postsLengthCalc(carportLength);
        assertEquals(expResult, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPostsLengthCalcFail() {
        Calculators calc = new Calculators();
        int carportHeight = RulesAndConstants.MAXHEIGHT + 1;
        calc.postsLengthCalc(carportHeight);
        //        int expResult = 391;
        //        int result = (int) calc.postsAmountCalc(caportLength, caportWidth);
        //        assertNotEquals(expResult, result);
    }

    @Test
    public void testflatRoofRafterAmountCalc() {
        int caportLength = 300;
        int expResult = 7;
        int result = (int) Calculators.flatRoofRafterAmountCalc(caportLength);
        assertEquals(expResult, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testflatRoofRafterAmountCalcFail() {
        int caportLength = RulesAndConstants.MINLENGTH - 1;
        Calculators.flatRoofRafterAmountCalc(caportLength);
    }

    @Test
    public void testrafterBottomLengthCalc() {
        int carportWidth = 300;
        int expResult = 350;
        int result = (int) Calculators.rafterBottomLengthCalc(carportWidth);
        assertEquals(expResult, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testrafterBottomLengthCalcFail() {
        int carportWidth = (int) Math.sqrt(300);
        Calculators.rafterBottomLengthCalc(carportWidth);
    }

    @Test
    public void testangledRoofRafterBottomAmountCalc() {
        int caportLength = 300;
        int expResult = 4;
        int result = (int) Calculators.angledRoofRafterBottomAmountCalc(caportLength);
        assertEquals(expResult, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testangledRoofRafterBottomAmountCalcFail() {
        int caportLength = RulesAndConstants.MAXLENGTH + 1;
        Calculators.angledRoofRafterBottomAmountCalc(caportLength);
    }

    @Test
    public void testangledRoofRafterSidesAmountCalc() {
        int caportLength = 500;
        int expResult = 12;
        int result = (int) Calculators.angledRoofRafterSidesAmountCalc(caportLength);
        assertEquals(expResult, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testangledRoofRafterSidesAmountCalcFail() {
        int caportLength = RulesAndConstants.MINLENGTH - 1;
        Calculators.angledRoofRafterSidesAmountCalc(caportLength);
    }

    @Test
    public void testangledRoofRafterSidesLengthCalc() {
        int caportLength = 500;
        int roofAngle = 15;
        int expResult = 284;
        int result = (int) Calculators.angledRoofRafterSidesLengthCalc(caportLength, roofAngle);
        assertEquals(expResult, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testangledRoofRafterSidesLengthCalcFail() {
        int caportLength = RulesAndConstants.MINLENGTH;
        int roofAngle = RulesAndConstants.MINROOFANGLE - 10;
        Calculators.angledRoofRafterSidesLengthCalc(caportLength, roofAngle);
    }

    @Test
    public void testconcreteAmountCalc() {
        int amountOfPosts = 12;
        int expResult = 12;
        int result = (int) Calculators.concreteAmountCalc(amountOfPosts);
        assertEquals(expResult, result);
    }

//    @Test
//    public void testconcreteAmountCalcFail() {
//        Calculators calc = new Calculators();
//        int amountOfPosts = 12;
//        int expResult = 10;
//        int result = (int) calc.concreteAmountCalc(amountOfPosts);
//        assertNotEquals(expResult, result);
//    }
    @Test
    public void testmountPerPost() {
        int amountOfPosts = 12;
        int expResult = 12;
        int result = (int) Calculators.mountPerPost(amountOfPosts);
        assertEquals(expResult, result);
    }
//
//    @Test
//    public void testmountPerPostFail() {
//        Calculators calc = new Calculators();
//        int amountOfPosts = 12;
//        int expResult = 10;
//        int result = (int) calc.mountPerPost(amountOfPosts);
//        assertNotEquals(expResult, result);
//    }

    @Test
    public void testmountPerRafter() {
        int amountOfRafters = 12;
        int expResult = 24;
        int result = (int) Calculators.mountPerRafter(amountOfRafters);
        assertEquals(expResult, result);
    }

    @Test
    public void testremLengthCalc() {
        int carportLength = 400;
        int expResult = 450;
        int result = (int) Calculators.remLengthCalc(carportLength);
        assertEquals(expResult, result);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testremLengthCalcFail() {
        int carportLength = RulesAndConstants.MINLENGTH - 1;
        Calculators.remLengthCalc(carportLength);
    }
}
