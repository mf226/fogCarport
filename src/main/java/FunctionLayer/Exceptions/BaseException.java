/*
 * Idea for this is to manage logging in constructor or by method, thereby having all logging
 * from selfmade exceptions done at throw time.
 * 
 */
package FunctionLayer.Exceptions;

public class BaseException extends Exception {

    public BaseException(String msg){
        super(msg);
        
        // do some logging, making sure all exceptions inheriting from this will log.
        //maybe take the said exception as a parameter to constructor for acces to all info to be logged.
    }
}
