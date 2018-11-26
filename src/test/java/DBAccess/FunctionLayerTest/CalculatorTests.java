/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBAccess.FunctionLayerTest;

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

    @Test
    public void testPostAmountCalcFail() {
        Calculators calc = new Calculators();
        int caportLength = 300;
        int caportWidth = 300;
        int expResult = 9;
        int result = (int) calc.postsAmountCalc(caportLength, caportWidth);
        assertNotEquals(expResult, result);
    }

    @Test
    public void testpostsLengthCalc() {
        Calculators calc = new Calculators();
        int caportLength = 300;
        int expResult = 390;
        int result = (int) calc.postsLengthCalc(caportLength);
        assertEquals(expResult, result);
    }

    @Test
    public void testPostsLengthCalcFail() {
        Calculators calc = new Calculators();
        int caportLength = 300;
        int caportWidth = 300;
        int expResult = 391;
        int result = (int) calc.postsAmountCalc(caportLength, caportWidth);
        assertNotEquals(expResult, result);
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
    
    
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
