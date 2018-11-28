package DBAccess;

import FunctionLayer.LoginSampleException;
import FunctionLayer.Role;
import FunctionLayer.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class UserMapperTest {

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
    public void testSetUpOK() {
        //Just check that we have a connection.
        assertNotNull(singleton);
    }

    @Test
    public void testLogin01() throws LoginSampleException {
        //Can we log in
        User user = UserMapper.login("testuser@test.com", "test");
        assertTrue(user != null);
    }

    @Test(expected = LoginSampleException.class)
    public void testLogin02() throws LoginSampleException {
        //We should get an exception if we use the wrong password
        User user = UserMapper.login("testuser@test.com", "testing");
    }

    @Test
    public void testLoginCustomer() throws LoginSampleException {
        //testuser is supposed to be a customer
        User user = UserMapper.login("testuser@test.com", "test");

        assertEquals(Role.CUSTOMER, user.getRole());
    }

    @Test
    public void testLoginNotCustomer() throws LoginSampleException {
        //testuser is not supposed to be admin or employee
        User user = UserMapper.login("testuser@test.com", "test");

        assertNotEquals(Role.ADMIN, user.getRole());
        assertNotEquals(Role.EMPLOYEE, user.getRole());
    }

    @Test
    public void testCreateCustomer() throws LoginSampleException {
        // Can we create a new user - Notice, if login fails, this will fail
        // but so would login01, so this is OK
        User user = new User("testuser2@test.com", "test2");
        UserMapper.createUser(user);
        User retrieved = UserMapper.login("testuser2@test.com", "test2");
        assertEquals(Role.CUSTOMER, retrieved.getRole());
    }
    
        @Test
    public void testCreateCustomerRoleCheck() throws LoginSampleException {
        // Can we create a new user - Notice, if login fails, this will fail
        // but so would login01, so this is OK
        User user = new User("testuser2@test.com", "test2");
        UserMapper.createUser(user);
        User retrieved = UserMapper.login("testuser2@test.com", "test2");
        assertNotEquals(Role.ADMIN, retrieved.getRole());
        assertNotEquals(Role.EMPLOYEE, retrieved.getRole());
    }
    
    @After
    public void tearDown(){
        
    }
}
