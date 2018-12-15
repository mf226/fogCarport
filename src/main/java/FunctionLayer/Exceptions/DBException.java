/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionLayer.Exceptions;

/**
 *
 * @author Fen
 */
public class DBException extends Exception {

    /**
     * Creates a new instance of <code>DBException</code> without detail
     * message.
     */
    public DBException() {
    }

    /**
     * Constructs an instance of <code>DBException</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public DBException(String msg) {
        super(msg);
    }
}
