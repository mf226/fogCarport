/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentationLayer.Commands;

import FunctionLayer.LogicFacade;
import FunctionLayer.Exceptions.LoginException;
import FunctionLayer.Entity.Order;
import PresentationLayer.HTMLGenerator;
import PresentationLayer.SVGGenerator;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jonab
 */
public class AddShed extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws LoginException {
        Order order = (Order) request.getSession(false).getAttribute("order");
        String placement = request.getParameter("placement");
        String shedLength = request.getParameter("shedLength");
        int length = Integer.parseInt(shedLength);
        String shedWidth = request.getParameter("shedWidth");
        String sideMat = request.getParameter("sideMat");
        int wallType = Integer.parseInt(sideMat);
        int width = Integer.parseInt(shedWidth);
        order.createShed(placement, length, width, true, wallType);
        LogicFacade.createShed(order);
        
        String table = HTMLGenerator.generateBOM(order);
        String sketchSV = "";
        String sketchBE = "";
        if (order.getAngle() != 0) {
            sketchSV = SVGGenerator.createSketchSideViewAngled(order);
            sketchBE = SVGGenerator.createSketchBirdsEyeView(order);
        } else {
            sketchSV = SVGGenerator.createSketchSideViewFlat(order);
            sketchBE = SVGGenerator.createSketchBirdsEyeView(order);
        }

        request.setAttribute("table", table);
        request.setAttribute("sketchSV", sketchSV);
        request.setAttribute("sketchBE", sketchBE);
        return "BOMpage";
    }
    
}
