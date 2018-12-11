/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentationLayer;

import FunctionLayer.LogicFacade;
import FunctionLayer.Exceptions.LoginSampleException;
import FunctionLayer.Entity.WoodMaterial;
import static PresentationLayer.Command.gen;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jonab
 */
public class MeasurementAngled extends Command {

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginSampleException {
        List<WoodMaterial> roofs = LogicFacade.getAngledroofs();
        String roof = gen.createRoofTypesAngled(roofs);
        request.setAttribute("roof", roof);
        return "measurementsAngled";
    }

}
