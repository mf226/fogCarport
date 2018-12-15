/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionLayer.Exceptions;

public class BaseException extends Exception {

    /**
     * Creates a new instance of <code>BaseException</code> without detail
     * message.
     */
    public BaseException() {
    }

    /**
     * Constructs an instance of <code>BaseException</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public BaseException(String msg){
        super(msg);
        // do some logging
    }
}
