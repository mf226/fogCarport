/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentationLayer.Commands;

import PresentationLayer.Commands.Command;
import FunctionLayer.LogicFacade;
import FunctionLayer.Exceptions.DBException;
import FunctionLayer.Entity.WoodMaterial;
import PresentationLayer.HTMLGenerator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jonab
 */
public class MeasurementFlat extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws DBException {
        List<WoodMaterial> roofs = LogicFacade.getFlatroofs();
        String roof = HTMLGenerator.createRoofTypesFlat(roofs);
        request.setAttribute("roof", roof);
        return "measurementsFlat";
    }

}
