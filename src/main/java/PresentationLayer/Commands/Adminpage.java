/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentationLayer.Commands;

import FunctionLayer.LogicFacade;
import FunctionLayer.Exceptions.DBException;
import FunctionLayer.Entity.Order;
import PresentationLayer.HTMLGenerator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jonab
 */
public class Adminpage extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws DBException {
        List<Order> orders = LogicFacade.getAllOrders();
        String ordersTable = HTMLGenerator.showAllOrders(orders);
        request.setAttribute("ordersTable", ordersTable);
        return "adminpage";
    }

}
