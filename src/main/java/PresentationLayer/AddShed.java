/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentationLayer;

import FunctionLayer.LogicFacade;
import FunctionLayer.LoginSampleException;
import FunctionLayer.Order;
import java.util.Enumeration;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jonab
 */
public class AddShed extends Command {

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginSampleException {
        Order order = (Order) request.getSession(false).getAttribute("order");
        String placement = request.getParameter("placement");
        String shedLength = request.getParameter("shedLength");
        int length = Integer.parseInt(shedLength);
        String shedWidth = request.getParameter("shedWidth");
        String sideMat = request.getParameter("sideMat");
        int wallType = Integer.parseInt(sideMat);
        int width = Integer.parseInt(shedWidth);
        order.createShed(placement, length, width, true);
        LogicFacade.createShed(order, wallType);
        
        String table = gen.generateBOM(order);
        String sketchSV = "";
        String sketchBE = "";
        if (order.getAngle() != 0) {
            sketchSV = gen.createSketchSideViewAngled(order);
            sketchBE = gen.createSketchBirdsEyeView(order);
        } else {
            sketchSV = gen.createSketchSideViewFlat(order);
            sketchBE = gen.createSketchBirdsEyeView(order);
        }

        request.setAttribute("table", table);
        request.setAttribute("sketchSV", sketchSV);
        request.setAttribute("sketchBE", sketchBE);
        return "BOMpage";
    }
    
}
