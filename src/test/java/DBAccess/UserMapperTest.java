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

    private static Connection testConnection;
    private static String USER = "transformer";
    private static String USERPW = "transformerpass";
    private static String DBNAME = "FogDB";
    private static String HOST = "104.248.17.255";

    @Before
    public void setUp() {
        try {
             //awoid making a new connection for each test
            if ( testConnection == null ) {
                String url = String.format( "jdbc:mysql://%s:3306/%s", HOST, DBNAME );
                Class.forName( "com.mysql.cj.jdbc.Driver" );

                testConnection = DriverManager.getConnection( url, USER, USERPW );
                 //Make mappers use test 
                Connector.setConnection( testConnection );
            }
//             //reset test database
//            try ( Statement stmt = testConnection.createStatement() ) {
////                stmt.execute( "drop table if exists Users" );
////                stmt.execute( "create table Users like UsersTest" );
//                stmt.execute( "insert into User SELECT * FROM FogDB.User" );
//            }

        } catch ( ClassNotFoundException | SQLException ex ) {
            testConnection = null;
            System.out.println( "Could not open connection to database: " + ex.getMessage() );
        }
    }

    @Test
    public void testSetUpOK() {
         //Just check that we have a connection.
        assertNotNull( testConnection );
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
        assertEquals( "customer", retrieved.getRole() );
    }
}
