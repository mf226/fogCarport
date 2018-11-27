/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentationLayer;

import FunctionLayer.LogicFacade;
import FunctionLayer.LoginSampleException;
import FunctionLayer.WoodMaterial;
import FunctionLayer.WoodDetails;
import FunctionLayer.Order;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jonab
 */
public class FlatBOM extends Command {

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginSampleException {
        String height = request.getParameter("height");
        int h = Integer.parseInt(height);
        String width = request.getParameter("width");
        int w = Integer.parseInt(width);
        String length = request.getParameter("length");
        int l = Integer.parseInt(length);
        Order order = LogicFacade.createFlatRoofCarport(l, w, h, 0);
        
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
