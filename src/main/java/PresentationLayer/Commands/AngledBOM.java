/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentationLayer.Commands;

import FunctionLayer.LogicFacade;
import FunctionLayer.Exceptions.LoginSampleException;
import FunctionLayer.Entity.Order;
import FunctionLayer.Entity.WoodMaterial;
import PresentationLayer.HTMLGenerator;
import PresentationLayer.SVGGenerator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jonab
 */
public class AngledBOM extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws LoginSampleException {
        String height = request.getParameter("height");
        int h = Integer.parseInt(height);
        String width = request.getParameter("width");
        int w = Integer.parseInt(width);
        String length = request.getParameter("length");
        int l = Integer.parseInt(length);
        String angle = request.getParameter("angle");
        int a = Integer.parseInt(angle);
        String roofType = request.getParameter("roofType");

        int roofTypeNumber = Integer.parseInt(roofType);
        Order order = LogicFacade.createOrder(l, w, h, a, roofTypeNumber);
        
        String check = request.getParameter("skur");

        if (check != null) {
            List<WoodMaterial> sideMaterials = LogicFacade.getSideMaterials();
            request.getSession(false).setAttribute("order", order);
            String shedSketch = SVGGenerator.shedPlacement(order);
            String shedOptions = HTMLGenerator.generateShedMeasurements(l, w, sideMaterials);

            request.setAttribute("shedOptions", shedOptions);
            request.setAttribute("shedSketch", shedSketch);
            return "shedpage";
        }
        
        String table = HTMLGenerator.generateBOM(order);
        String sketchSV = SVGGenerator.createSketchSideViewAngled(order);
        String sketchBE = SVGGenerator.createSketchBirdsEyeViewAngled(order);

        request.setAttribute("table", table);
        request.setAttribute("sketchSV", sketchSV);
        request.setAttribute("sketchBE", sketchBE);

        request.getSession(false).setAttribute("order", order);

        return "BOMpage";
    }

}
