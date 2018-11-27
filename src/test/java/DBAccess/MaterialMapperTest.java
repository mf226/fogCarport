/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
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
public class MaterialMapperTest {

    private static final String URL = "jdbc:mysql://104.248.17.255:3306/FogDB";
    private static final String USERNAME = "transformer";
    private static final String PASSWORD = "transformerpass";

    private static Connection singleton;

    @Before
    public void setUp() {
        try {
            //awoid making a new connection for each test
            if (singleton == null) {
                Class.forName("com.mysql.cj.jdbc.Driver");

                singleton = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                //Make mappers use test 
                Connector.setConnection(singleton);
            }

        } catch (ClassNotFoundException | SQLException ex) {
            singleton = null;
            System.out.println("Could not open connection to database: " + ex.getMessage());
        }
    }

    @Test
    public void testsetUp() {
        assertNotNull(singleton);
    }
    
    @Test
    public void testgetMaterial() {
        
    }
}
