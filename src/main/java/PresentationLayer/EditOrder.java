/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentationLayer;

import FunctionLayer.LogicFacade;
import FunctionLayer.LoginSampleException;
import FunctionLayer.Order;
import static PresentationLayer.Command.gen;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jonab
 */
public class EditOrder extends Command {

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginSampleException {
        Order order = (Order) request.getSession(false).getAttribute("order");
        String status = request.getParameter("status");
        String newPrice_str = request.getParameter("newPrice");
        if (null != newPrice_str) {
        double newPrice = Double.parseDouble(newPrice_str);
            if (newPrice >= order.getTotalOrderPrice()) {
                LogicFacade.editOrderPrice(order, newPrice);
                LogicFacade.editOrderStatus(order, status);

            }
        }
        LogicFacade.editOrderStatus(order, status);
        List<Order> orders = LogicFacade.getAllOrders();
        String ordersTable = gen.showAllOrders(orders);
        request.setAttribute("ordersTable", ordersTable);
        return "adminpage";
    }

}
