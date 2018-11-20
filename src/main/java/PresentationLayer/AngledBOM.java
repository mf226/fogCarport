/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentationLayer;

import FunctionLayer.LogicFacade;
import FunctionLayer.LoginSampleException;
import FunctionLayer.Order;
import static PresentationLayer.Command.gen;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jonab
 */
public class AngledBOM extends Command {

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginSampleException {
        String height = request.getParameter("height");
        int h = Integer.parseInt(height);
        String width = request.getParameter("width");
        int w = Integer.parseInt(width);
        String length = request.getParameter("length");
        int l = Integer.parseInt(length);
        String angle = request.getParameter("angle");
        int a = Integer.parseInt(angle);
        Order order = LogicFacade.createAngledRoofCarport(l, w, h, a);
        String table = gen.generateBOM(order);
        String sketch = gen.createSketchHindSight(order);
        request.setAttribute("table", table);
        request.setAttribute("sketch", sketch);
        return "BOMpage";    }

}
