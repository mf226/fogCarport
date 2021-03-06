/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBAccess;

import FunctionLayer.Exceptions.LoginException;
import FunctionLayer.Entity.Order;
import FunctionLayer.Entity.User;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Fen
 */
public class OrderMapper {

    /**
     * Returns a List of all Orders from the database
     *
     * @throws LoginException
     * @return List<Order>
     */
    static List<Order> getAllOrders() throws LoginException {
        try {
            ArrayList<Order> orders = new ArrayList();
            Connection con = Connector.connection();
            String SQL = "SELECT * FROM FogDB.`Order`;";
            PreparedStatement ps = con.prepareStatement(SQL);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int orderID = rs.getInt("orderID");
                int userID = rs.getInt("userID");
                int length = rs.getInt("length");
                int angle = rs.getInt("angle");
                int roofType = rs.getInt("roofType");
                int width = rs.getInt("width");
                int height = rs.getInt("height");
                double price = rs.getDouble("price");
                Date date = rs.getDate("orderDate");
                String status = rs.getString("status");

//                Date orderDate = rs.getDate("orderDate");
                Order order = new Order(length, width, height, angle, roofType);
                order.setOrderID(orderID);
                order.setUserID(userID);
                order.setPrice(price);
                order.setOrderDate(date);
                order.setStatus(status);
                orders.add(order);
            }
            return orders;
        } catch (SQLException | ClassNotFoundException ex) {
            throw new LoginException(ex.getMessage());
        }
    }

    /**
     * Returns a List of Orders from the database with given UserID
     *
     * @param int userID
     * @throws LoginException
     * @return List<Order>
     */
    static List<Order> getAllOrdersByUser(int id) throws LoginException {
        try {
            ArrayList<Order> orders = new ArrayList();
            Connection con = Connector.connection();
            String SQL = "SELECT * FROM FogDB.`Order` WHERE userID = ?;";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int orderID = rs.getInt("orderID");
                int userID = rs.getInt("userID");
                int length = rs.getInt("length");
                int angle = rs.getInt("angle");
                int roofType = rs.getInt("roofType");
                int width = rs.getInt("width");
                int height = rs.getInt("height");
                double price = rs.getDouble("price");
                Date date = rs.getDate("orderDate");
                String status = rs.getString("status");

//                Date orderDate = rs.getDate("orderDate");
                Order order = new Order(length, width, height, angle, roofType);
                order.setOrderID(orderID);
                order.setUserID(userID);
                order.setPrice(price);
                order.setOrderDate(date);
                order.setStatus(status);
                orders.add(order);
            }
            return orders;
        } catch (SQLException | ClassNotFoundException ex) {
            throw new LoginException(ex.getMessage());
        }
    }

    /**
     * Returns an Order from the database with given OrderID
     *
     * @param int orderID
     * @throws LoginException
     * @return Order
     */
    static Order getOrderByOrderID(int orderID) throws LoginException {
        try {
            Order order;
            Connection con = Connector.connection();
            String SQL = "SELECT * FROM FogDB.`Order` WHERE orderID = ?;";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setInt(1, orderID);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int userID = rs.getInt("userID");
                int length = rs.getInt("length");
                int width = rs.getInt("width");
                int height = rs.getInt("height");
                int angle = rs.getInt("angle");
                int roofType = rs.getInt("roofType");
                double price = rs.getDouble("price");
                Date orderDate = rs.getDate("orderDate");
                Boolean hasShed = rs.getBoolean("hasShed");
                int shedLength = rs.getInt("shedLength");
                int shedWidth = rs.getInt("shedWidth");
                String shedPlacement = rs.getString("shedPlacement");
                int wallType = rs.getInt("wallType");

                order = new Order(length, width, height, angle, roofType);
                order.setUserID(userID);
                order.setOrderID(orderID);
                order.setOrderDate(orderDate);
                order.setPrice(price);
                if (hasShed) {
                    order.createShed(shedPlacement, shedLength, shedWidth, hasShed, wallType);
                }
                return order;
            }
        } catch (ClassNotFoundException | SQLException ex) {
            throw new LoginException(ex.getMessage());
        }
        return null;
    }

    /**
     * Updates order-status on given Order to 'Approved'
     *
     * @param Order order
     * @throws LoginException
     */
    static void approveOrder(Order order) throws LoginException {
        try {
            Connection con = Connector.connection();
            con.setAutoCommit(false);
            String SQL = "UPDATE `FogDB`.`Order` SET `status` = 'approved' WHERE (`orderID` = ?) and (`userID` = ?);;";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setInt(1, order.getOrderID());
            ps.setInt(2, order.getUserID());
            ps.executeUpdate();
            con.commit();
            con.setAutoCommit(true);
        } catch (SQLException | ClassNotFoundException ex) {
            throw new LoginException(ex.getMessage());

        }
    }

    /**
     * Updates order-status on given Order to status
     *
     * @param Order order
     * @param String status
     * @throws LoginException
     */
    static void editOrderStatus(Order order, String status) throws LoginException {
        try {
            Connection con = Connector.connection();
            con.setAutoCommit(false);
            String SQL = "UPDATE `FogDB`.`Order` SET `status` = ? WHERE (`orderID` = ?) and (`userID` = ?);";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setString(1, status);
            ps.setInt(2, order.getOrderID());
            ps.setInt(3, order.getUserID());
            ps.executeUpdate();
            con.commit();
            con.setAutoCommit(true);
        } catch (SQLException | ClassNotFoundException ex) {
            throw new LoginException(ex.getMessage());
        }
    }

    /**
     * returns a list of all orders made by given User
     *
     * @param User user
     * @throws LoginException
     * @return List<Order>
     */
    static List<Order> getOrdersbyUserID(User user) throws LoginException {

        try {
            ArrayList<Order> orders = new ArrayList();
            Connection con = Connector.connection();
            String SQL = "SELECT * FROM FogDB.`Order` WHERE userID = ?;";
            PreparedStatement ps = con.prepareStatement(SQL);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int orderID = rs.getInt("orderID");
                int length = rs.getInt("length");
                int width = rs.getInt("width");
                int height = rs.getInt("height");
                double finalizedPrice = rs.getDouble("price");
                Date orderDate = rs.getDate("orderDate");
                //String status = rs.getString("status"); 
                // orders.add(new Order(orderID, user.getId(), length, width, height, finalizedPrice, orderDate, status));
                //orders.add(new Order(orderID, orderDescription, price, user.getId(), orderDate));

            }
            return orders;
        } catch (SQLException | ClassNotFoundException ex) {
            throw new LoginException(ex.getMessage());
        }

    }

    /**
     * Adds given Order to DB
     *
     * @param Order order
     * @throws LoginException
     */
    static void addOrderToDB(Order order) throws LoginException {
        try {
            Connection con = Connector.connection();
            con.setAutoCommit(false);
            String SQL = "INSERT INTO `FogDB`.`Order` (`userID`, `length`, `width`, `height`, `angle`, `roofType`, `hasShed`, `shedLength`, `shedWidth`, `shedPlacement`, `wallType`, `price`, `status`)"
                    + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setInt(1, order.getUserID());
            ps.setInt(2, order.getLength());
            ps.setInt(3, order.getWidth());
            ps.setInt(4, order.getHeight());
            ps.setInt(5, order.getAngle());
            ps.setInt(6, order.getRoofType());
            ps.setBoolean(7, order.isShedExists());
            ps.setInt(8, order.getShedLength());
            ps.setInt(9, order.getShedWidth());
            ps.setString(10, order.getShedPlacement());
            ps.setInt(11, order.getWallType());
            ps.setDouble(12, order.getPrice());
            ps.setString(13, order.getStatus());
            ps.executeUpdate();
            con.commit();
            con.setAutoCommit(true);
        } catch (SQLException | ClassNotFoundException ex) {
            throw new LoginException(ex.getMessage());

        }

    }

    /**
     * Updates order-price on given Order to newPrice
     *
     * @param Order order
     * @param double newPrice
     * @throws LoginException
     */
    static void editOrderPrice(Order order, double newPrice) throws LoginException {
        try {
            Connection con = Connector.connection();
            con.setAutoCommit(false);
            String SQL = "UPDATE `FogDB`.`Order` SET `price` = ? WHERE (`orderID` = ?) and (`userID` = ?);";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setDouble(1, newPrice);
            ps.setInt(2, order.getOrderID());
            ps.setInt(3, order.getUserID());
            ps.executeUpdate();
            con.commit();
            con.setAutoCommit(true);
        } catch (ClassNotFoundException | SQLException EX) {
            throw new LoginException(EX.getMessage());

        }

    }
}
