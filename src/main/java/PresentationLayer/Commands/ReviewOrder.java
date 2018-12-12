/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentationLayer.Commands;

import PresentationLayer.Commands.Command;
import FunctionLayer.LogicFacade;
import FunctionLayer.Exceptions.LoginSampleException;
import FunctionLayer.Entity.Order;
import PresentationLayer.HTMLGenerator;
import PresentationLayer.SVGGenerator;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jonab
 */
public class ReviewOrder extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws LoginSampleException {
        String orderID = request.getParameter("orderID");
        int id = Integer.parseInt(orderID);
        Order order = LogicFacade.getOrderByOrderID(id);
        
        LogicFacade.createCarport(order);
        if(order.isShedExists()) {
            LogicFacade.createShed(order);
        }
        String table = HTMLGenerator.generateBOM(order);
        String updateOrder = HTMLGenerator.updateOrderInput(order);
        String sketchSV = "";
        String sketchBE = "";
        if (null != order) {
            if (order.getAngle() != 0) {
                sketchSV = SVGGenerator.createSketchSideViewAngled(order);
                sketchBE = SVGGenerator.createSketchBirdsEyeViewFlat(order);
            } else {
                sketchSV = SVGGenerator.createSketchSideViewFlat(order);
                sketchBE = SVGGenerator.createSketchBirdsEyeViewFlat(order);
            }
            request.setAttribute("updateOrder", updateOrder);
            request.setAttribute("table", table);
            request.setAttribute("sketchSV", sketchSV);
            request.setAttribute("sketchBE", sketchBE);
            request.getSession(false).setAttribute("order", order);

            return "reviewpage";
        }
        return "errorpage";
    }

}
