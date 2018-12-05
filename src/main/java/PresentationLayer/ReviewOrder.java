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
public class ReviewOrder extends Command {

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginSampleException {
        String orderID = request.getParameter("orderID");
        int id = Integer.parseInt(orderID);
        Order order = LogicFacade.getOrderByOrderID(id);
        String table = gen.generateBOM(order);
        String sketchSV = "";
        String sketchBE = "";
        if (null != order) {
//            if (order.getAngle() != 0) {
//                sketchSV = gen.createSketchSideViewAngled(order);
//                sketchBE = gen.createSketchBirdsEyeView(order);
//            } else {
//                sketchSV = gen.createSketchSideViewFlat(order);
//                sketchBE = gen.createSketchBirdsEyeView(order);
//            }

            request.setAttribute("table", table);
//            request.setAttribute("sketchSV", sketchSV);
//            request.setAttribute("sketchBE", sketchBE);

            return "reviewpage";
        }
        return "errorpage";
    }

}
