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
public class ApproveOrder extends Command {

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginSampleException {
        Order order = (Order) request.getSession(false).getAttribute("order");
        LogicFacade.approveOrder(order);
        String newPrice_str = request.getParameter("newPrice");
        double newPrice = Double.parseDouble(newPrice_str);
        if(newPrice > order.getTotalOrderPrice()){
            LogicFacade.editOrderPrice(order, newPrice);
        }
        List<Order> orders = LogicFacade.getAllOrders();
        String ordersTable = gen.showAllOrders(orders);
        request.setAttribute("ordersTable", ordersTable);
        return "adminpage";
    }

}
