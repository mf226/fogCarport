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
import java.util.HashMap;
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
        
        String check = request.getParameter("skur");

        if (check != null) {
            request.getSession(false).setAttribute("order", order);
            String shedSketch = gen.shedPlacement(order);
            String shedOptions = gen.generateShedMeasurements(l, w);
            request.setAttribute("shedOptions", shedOptions);
            request.setAttribute("shedSketch", shedSketch);
            return "shedpage";
        }
        
        String table = gen.generateBOM(order);
        String sketch = gen.createSketchBirdsEyeView(order);
        request.setAttribute("table", table);
        request.setAttribute("sketch", sketch);
        request.setAttribute("order", order);

        return "BOMpage";
    }

}
