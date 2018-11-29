/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionLayerTest;

import FunctionLayer.Calculators;
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
//test grænseværdier og negative værdier

    @Test(expected = IllegalArgumentException.class)
    public void testPostAmountCalcException() {
        Calculators calc = new Calculators();
        int caportLength = 700;
        int caportWidth = 300;
        calc.postsAmountCalc(caportLength, caportWidth);
//        int expResult = 9;
//        int result = (int) calc.postsAmountCalc(caportLength, caportWidth);
//        assertNotEquals(expResult, result);
    }

    @Test
    public void testpostsLengthCalc() {
        Calculators calc = new Calculators();
        int caportLength = 300;
        int expResult = 390;
        int result = (int) calc.postsLengthCalc(caportLength);
        assertEquals(expResult, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPostsLengthCalcFail() {
        Calculators calc = new Calculators();
        int caportLength = 800;
        calc.postsLengthCalc(caportLength);
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

    @Test
    public void testflatRoofRafterAmountCalcFail() {
        Calculators calc = new Calculators();
        int caportLength = 300;
        int expResult = 5;
        int result = (int) calc.flatRoofRafterAmountCalc(caportLength);
        assertNotEquals(expResult, result);
    }

    @Test
    public void testrafterBottomLengthCalc() {
        Calculators calc = new Calculators();
        int carportWidth = 300;
        int expResult = 350;
        int result = (int) calc.rafterBottomLengthCalc(carportWidth);
        assertEquals(expResult, result);
    }

    @Test
    public void testrafterBottomLengthCalcFail() {
        Calculators calc = new Calculators();
        int carportWidth = 300;
        int expResult = 400;
        int result = (int) calc.rafterBottomLengthCalc(carportWidth);
        assertNotEquals(expResult, result);
    }

    @Test
    public void testangledRoofRafterBottomAmountCalc() {
        Calculators calc = new Calculators();
        int caportLength = 300;
        int expResult = 4;
        int result = (int) calc.angledRoofRafterBottomAmountCalc(caportLength);
        assertEquals(expResult, result);
    }

    @Test
    public void testangledRoofRafterBottomAmountCalcFail() {
        Calculators calc = new Calculators();
        int caportLength = 500;
        int expResult = 5;
        int result = (int) calc.angledRoofRafterBottomAmountCalc(caportLength);
        assertNotEquals(expResult, result);
    }

    @Test
    public void testangledRoofRafterSidesAmountCalc() {
        Calculators calc = new Calculators();
        int caportLength = 500;
        int expResult = 12;
        int result = (int) calc.angledRoofRafterSidesAmountCalc(caportLength);
        assertEquals(expResult, result);
    }

    @Test
    public void testangledRoofRafterSidesAmountCalcFail() {
        Calculators calc = new Calculators();
        int caportLength = 500;
        int expResult = 99;
        int result = (int) calc.angledRoofRafterSidesAmountCalc(caportLength);
        assertNotEquals(expResult, result);
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

    @Test
    public void testangledRoofRafterSidesLengthCalcFail() {
        Calculators calc = new Calculators();
        int caportLength = 500;
        int roofAngle = 15;
        int expResult = 150;
        int result = (int) calc.angledRoofRafterSidesLengthCalc(caportLength, roofAngle);
        assertNotEquals(expResult, result);
    }

    @Test
    public void testconcreteAmountCalc() {
        Calculators calc = new Calculators();
        int amountOfPosts = 12;
        int expResult = 12;
        int result = (int) calc.concreteAmountCalc(amountOfPosts);
        assertEquals(expResult, result);
    }

    @Test
    public void testconcreteAmountCalcFail() {
        Calculators calc = new Calculators();
        int amountOfPosts = 12;
        int expResult = 10;
        int result = (int) calc.concreteAmountCalc(amountOfPosts);
        assertNotEquals(expResult, result);
    }

    @Test
    public void testmountPerPost() {
        Calculators calc = new Calculators();
        int amountOfPosts = 12;
        int expResult = 12;
        int result = (int) calc.mountPerPost(amountOfPosts);
        assertEquals(expResult, result);
    }

    @Test
    public void testmountPerPostFail() {
        Calculators calc = new Calculators();
        int amountOfPosts = 12;
        int expResult = 10;
        int result = (int) calc.mountPerPost(amountOfPosts);
        assertNotEquals(expResult, result);
    }

    @Test
    public void testmountPerRafter() {
        Calculators calc = new Calculators();
        int amountOfRafters = 12;
        int expResult = 24;
        int result = (int) calc.mountPerRafter(amountOfRafters);
        assertEquals(expResult, result);
    }

    @Test
    public void testmountPerRafterFail() {
        Calculators calc = new Calculators();
        int amountOfRafters = 12;
        int expResult = 12;
        int result = (int) calc.mountPerRafter(amountOfRafters);
        assertNotEquals(expResult, result);
    }

    @Test
    public void testremLengthCalc() {
        Calculators calc = new Calculators();
        int carportLength = 400;
        int expResult = 450;
        int result = (int) calc.remLengthCalc(carportLength);
        assertEquals(expResult, result);
    }

    @Test
    public void testremLengthCalcFail() {
        Calculators calc = new Calculators();
        int carportLength = 400;
        int expResult = 400;
        int result = (int) calc.remLengthCalc(carportLength);
        assertNotEquals(expResult, result);
    }
}
