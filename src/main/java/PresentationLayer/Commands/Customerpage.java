/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentationLayer.Commands;

import FunctionLayer.Entity.Order;
import FunctionLayer.Entity.User;
import FunctionLayer.Exceptions.LoginException;
import FunctionLayer.LogicFacade;
import PresentationLayer.HTMLGenerator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jonab
 */
public class Customerpage extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws LoginException {
        User user = (User) request.getSession(false).getAttribute("user");
        List<Order> orders = LogicFacade.getAllOrdersByUser(user.getId());
        String ordersTable = HTMLGenerator.showAllOrdersByUser(orders);
        request.setAttribute("ordersTable", ordersTable);
        
        return "customerpage";
    }

}
