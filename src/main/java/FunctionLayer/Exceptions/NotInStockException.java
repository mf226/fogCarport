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
public class NotInStockException extends BaseException {

    /**
     * Creates a new instance of <code>NotInStockException</code> without detail
     * message.
     */
    public NotInStockException() {
    }

    /**
     * Constructs an instance of <code>NotInStockException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public NotInStockException(String msg) {
        super(msg);
    }
}
