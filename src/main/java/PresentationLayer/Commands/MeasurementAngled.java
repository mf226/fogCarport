/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentationLayer.Commands;

import PresentationLayer.Commands.Command;
import FunctionLayer.LogicFacade;
import FunctionLayer.Exceptions.LoginException;
import FunctionLayer.Entity.WoodMaterial;
import PresentationLayer.HTMLGenerator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jonab
 */
public class MeasurementAngled extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws LoginException {
        List<WoodMaterial> roofs = LogicFacade.getAngledroofs();
        String roof = HTMLGenerator.createRoofTypesAngled(roofs);
        request.setAttribute("roof", roof);
        return "measurementsAngled";
    }

}
