package FunctionLayer;

import DBAccess.UserMapper;

/**
 * The purpose of LogicFacade is to...
 * @author kasper
 */
public class LogicFacade {

    public static User login( String email, String password ) throws LoginSampleException {
        return UserMapper.login( email, password );
    } 

    public static User createUser( String email, String password, Role role ) throws LoginSampleException {
        User user = new User(email, password, role );
        UserMapper.createUserCustomer( user );
        return user;
    }
    
    /**
     *
     * @param email
     * @param password
     * @return
     * @throws Exception
     */
    public static User getUser(String email, String password) throws Exception {
        return UserMapper.getUser(email, password);
    }

}
