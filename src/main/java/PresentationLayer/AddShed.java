/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentationLayer;

import FunctionLayer.LogicFacade;
import FunctionLayer.LoginSampleException;
import FunctionLayer.Order;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jonab
 */
public class AddShed extends Command {

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginSampleException {
        Order order = (Order) request.getAttribute("order");
        String table = gen.generateBOM(order);
        String sketch = gen.createSketchHindSight(order);
        request.setAttribute("table", table);
        request.setAttribute("sketch", sketch);
        return "BOMpage";
    }


    
}
