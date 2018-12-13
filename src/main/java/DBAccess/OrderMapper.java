/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBAccess;

import FunctionLayer.Exceptions.LoginSampleException;
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

    static List<Order> getAllOrders() throws LoginSampleException {
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
            throw new LoginSampleException(ex.getMessage());
        }
    }
    
    static List<Order> getAllOrdersByUser(int id) throws LoginSampleException {
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
            throw new LoginSampleException(ex.getMessage());
        }
    }


    static Order getOrderByOrderID(int orderID) throws LoginSampleException {
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
            throw new LoginSampleException(ex.getMessage());
        }
        return null;
    }

    static void approveOrder(Order order) throws LoginSampleException {
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
            throw new LoginSampleException(ex.getMessage());

        }
    }

    static void editOrderStatus(Order order, String status) throws LoginSampleException {
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
            throw new LoginSampleException(ex.getMessage());
        }
    }

    static List<Order> getOrdersbyUserID(User user) throws LoginSampleException {

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
            throw new LoginSampleException(ex.getMessage());
        }

    }

    static void addOrderToDB(Order order) throws LoginSampleException {
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
            throw new LoginSampleException(ex.getMessage());

        }

    }

    static void editOrderPrice(Order order, double newPrice) throws LoginSampleException {
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
            throw new LoginSampleException(EX.getMessage());

        }

    }
}
