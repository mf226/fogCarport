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
        Calculators calc = new Calculators();
        int caportLength = 300;
        int caportWidth = 300;
        int expResult = 8;
        int result = (int) calc.postsAmountCalc(caportLength, caportWidth);
        assertEquals(expResult, result);

    }

    //Tester grænseværdiere
    @Test(expected = IllegalArgumentException.class)
    public void testPostAmountCalcException() {
        Calculators calc = new Calculators();
        int caportLength = RulesAndConstants.MAXLENGTH + 1;
        int caportWidth = RulesAndConstants.MAXWIDTH;
        calc.postsAmountCalc(caportLength, caportWidth);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPostAmountCalcNegative() {
        Calculators calc = new Calculators();
        int caportLength = RulesAndConstants.MINLENGTH - 1;
        int caportWidth = RulesAndConstants.MINWIDTH - 1;
        calc.postsAmountCalc(caportLength, caportWidth);

    }

    @Test
    public void testpostsLengthCalc() {
        Calculators calc = new Calculators();
        int carportLength = 300;
        int expResult = 390;
        int result = (int) calc.postsLengthCalc(carportLength);
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
        Calculators calc = new Calculators();
        int caportLength = 300;
        int expResult = 7;
        int result = (int) calc.flatRoofRafterAmountCalc(caportLength);
        assertEquals(expResult, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testflatRoofRafterAmountCalcFail() {
        Calculators calc = new Calculators();
        int caportLength = RulesAndConstants.MINLENGTH - 1;
        calc.flatRoofRafterAmountCalc(caportLength);
    }

    @Test
    public void testrafterBottomLengthCalc() {
        Calculators calc = new Calculators();
        int carportWidth = 300;
        int expResult = 350;
        int result = (int) calc.rafterBottomLengthCalc(carportWidth);
        assertEquals(expResult, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testrafterBottomLengthCalcFail() {
        Calculators calc = new Calculators();
        int carportWidth = (int) Math.sqrt(300);
        calc.rafterBottomLengthCalc(carportWidth);
    }

    @Test
    public void testangledRoofRafterBottomAmountCalc() {
        Calculators calc = new Calculators();
        int caportLength = 300;
        int expResult = 4;
        int result = (int) calc.angledRoofRafterBottomAmountCalc(caportLength);
        assertEquals(expResult, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testangledRoofRafterBottomAmountCalcFail() {
        Calculators calc = new Calculators();
        int caportLength = RulesAndConstants.MAXLENGTH + 1;
        calc.angledRoofRafterBottomAmountCalc(caportLength);
    }

    @Test
    public void testangledRoofRafterSidesAmountCalc() {
        Calculators calc = new Calculators();
        int caportLength = 500;
        int expResult = 12;
        int result = (int) calc.angledRoofRafterSidesAmountCalc(caportLength);
        assertEquals(expResult, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testangledRoofRafterSidesAmountCalcFail() {
        Calculators calc = new Calculators();
        int caportLength = RulesAndConstants.MINLENGTH - 1;
        calc.angledRoofRafterSidesAmountCalc(caportLength);
    }

    @Test
    public void testangledRoofRafterSidesLengthCalc() {
        Calculators calc = new Calculators();
        int caportLength = 500;
        int roofAngle = 15;
        int expResult = 284;
        int result = (int) calc.angledRoofRafterSidesLengthCalc(caportLength, roofAngle);
        assertEquals(expResult, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testangledRoofRafterSidesLengthCalcFail() {
        Calculators calc = new Calculators();
        int caportLength = RulesAndConstants.MINLENGTH;
        int roofAngle = RulesAndConstants.MINROOFANGLE - 10;
        calc.angledRoofRafterSidesLengthCalc(caportLength, roofAngle);
    }

    @Test
    public void testconcreteAmountCalc() {
        Calculators calc = new Calculators();
        int amountOfPosts = 12;
        int expResult = 12;
        int result = (int) calc.concreteAmountCalc(amountOfPosts);
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
        Calculators calc = new Calculators();
        int amountOfPosts = 12;
        int expResult = 12;
        int result = (int) calc.mountPerPost(amountOfPosts);
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
        Calculators calc = new Calculators();
        int amountOfRafters = 12;
        int expResult = 24;
        int result = (int) calc.mountPerRafter(amountOfRafters);
        assertEquals(expResult, result);
    }

//    @Test
//    public void testmountPerRafterFail() {
//        Calculators calc = new Calculators();
//        int amountOfRafters = 12;
//        int expResult = 12;
//        int result = (int) calc.mountPerRafter(amountOfRafters);
//        assertNotEquals(expResult, result);
//    }
    @Test
    public void testremLengthCalc() {
        Calculators calc = new Calculators();
        int carportLength = 400;
        int expResult = 450;
        int result = (int) calc.remLengthCalc(carportLength);
        assertEquals(expResult, result);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testremLengthCalcFail() {
        Calculators calc = new Calculators();
        int carportLength = RulesAndConstants.MINLENGTH - 1;
        calc.remLengthCalc(carportLength);
    }
}
