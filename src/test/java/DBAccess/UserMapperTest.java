package DBAccess;

import FunctionLayer.LoginSampleException;
import FunctionLayer.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class UserMapperTest {
//    Test date in the UsersTest table
//    INSERT INTO `UsersTest` VALUES 
//    (1,'jens@somewhere.com','jensen','customer'),
//    (2,'ken@somewhere.com','kensen','customer'),
//    (3,'robin@somewhere.com','batman','employee'),
//    (4,'someone@nowhere.com','sesam','customer');
 
    private static final String URL = "jdbc:mysql://104.248.17.255:3306/FogDB";
    private static final String USERNAME = "transformer";
    private static final String PASSWORD = "transformerpass";

    private static Connection singleton;
    
    @Before
    public void setUp() {
        try {
             //awoid making a new connection for each test
            if ( singleton == null ) {
                String url = String.format( URL, USERNAME, PASSWORD );
                Class.forName( "com.mysql.cj.jdbc.Driver" );

                singleton = DriverManager.getConnection( URL, USERNAME, PASSWORD  );
                 //Make mappers use test 
                Connector.setConnection( singleton );
            }
//             //reset test database
//            try ( Statement stmt = testConnection.createStatement() ) {
////                stmt.execute( "drop table if exists Users" );
////                stmt.execute( "create table Users like UsersTest" );
//                stmt.execute( "insert into User SELECT * FROM FogDB.User" );
//            }

        } catch ( ClassNotFoundException | SQLException ex ) {
            singleton = null;
            System.out.println( "Could not open connection to database: " + ex.getMessage() );
        }
    }

    @Test
    public void testSetUpOK() {
         //Just check that we have a connection.
        assertNotNull( singleton );
    }

    @Test
    public void testLogin01() throws LoginSampleException {
         //Can we log in
        User user = UserMapper.login( "testuser@test.com", "test" );
        assertTrue( user != null );
    }

    @Test( expected = LoginSampleException.class )
    public void testLogin02() throws LoginSampleException {
         //We should get an exception if we use the wrong password
        User user = UserMapper.login( "testuser@test.com", "testing" );
    }

    @Test
    public void testLogin03() throws LoginSampleException {
         //Jens is supposed to be a customer
        User user = UserMapper.login( "testuser@test.com", "test" );
        assertEquals( "CUSTOMER", user.getRole() );
    }

    @Test
    public void testCreateUser01() throws LoginSampleException {
        // Can we create a new user - Notice, if login fails, this will fail
        // but so would login01, so this is OK
        User user = new User( "king@kong.com", "uhahvorhemmeligt" );
        UserMapper.createUser( user );
        User retrieved = UserMapper.login( "king@kong.com", "uhahvorhemmeligt" );
        assertEquals( "CUSTOMER", retrieved.getRole() );
    }
}
