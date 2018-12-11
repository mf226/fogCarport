/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentationLayer;

import FunctionLayer.LogicFacade;
import FunctionLayer.Exceptions.LoginSampleException;
import FunctionLayer.Entity.Order;
import FunctionLayer.Entity.User;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jonab
 */
public class CreateOrder extends Command {

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginSampleException {
        User user;
        user = (User) request.getSession(false).getAttribute("user");
        Order order = (Order) request.getSession(false).getAttribute("order");
        if (null != user) {
            try {
                int id = user.getId();
                order.setUserID(id);
                order.setPrice(order.getTotalOrderPrice());
                LogicFacade.addOrderToDB(order);
                return "index";
            } catch (LoginSampleException ex) {
                System.out.println(ex.getMessage());
            }
        }
        String error = "not logged in";
        request.setAttribute("error", error);
        return "errorpage";
    }

}
