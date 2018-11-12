/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBAccess;

import FunctionLayer.LoginSampleException;
import FunctionLayer.User;

/**
 *
 * @author Fen
 */
public class DBaccessTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws LoginSampleException {
        User user = new User("madstester@test.com","testthisshit","Customer");
        UserMapper.createUserCustomer(user);
        
    }
    
}
