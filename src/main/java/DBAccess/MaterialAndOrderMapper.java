package DBAccess;

import FunctionLayer.LoginSampleException;
import FunctionLayer.Material;
import FunctionLayer.MetalMaterial;
import FunctionLayer.WoodMaterial;
import FunctionLayer.Order;
import FunctionLayer.Role;
import FunctionLayer.User;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fen
 */
public class MaterialAndOrderMapper {

    public static List<WoodMaterial> getAllMaterials() throws LoginSampleException {
        try {
            ArrayList<WoodMaterial> materials = new ArrayList();
            Connection con = Connector.connection();
            String SQL = "SELECT * FROM FogDB.Materials;";
            PreparedStatement ps = con.prepareStatement(SQL);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int itemNumber = rs.getInt("itemNumber");
                String materialName = rs.getString("materialName");
                String unit = rs.getString("unit");
                double price = rs.getDouble("price");
                int lengthInStock = rs.getInt("stockLength");
                int amountInStock = rs.getInt("inStock");
                materials.add(new WoodMaterial(itemNumber, materialName, unit, price, lengthInStock, amountInStock));
            }
            return materials;
        } catch (SQLException | ClassNotFoundException ex) {
            throw new LoginSampleException(ex.getMessage());
        }
    }

    public static WoodMaterial getWoodMaterial(int itemNumber) throws LoginSampleException {
        try {

            Connection con = Connector.connection();
            String SQL = "SELECT * FROM FogDB.Materials "
                    + "WHERE itemNumber = ?;";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setInt(1, itemNumber);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String materialName = rs.getString("materialName");
                String unit = rs.getString("unit");
                double price = rs.getDouble("price");
                int lengthInStock = rs.getInt("stockLength");
                int amountInStock = rs.getInt("inStock");
                return new WoodMaterial(itemNumber, materialName, unit, price, lengthInStock, amountInStock);
            } else {
                throw new LoginSampleException("Material doesn't exist.");
            }
        } catch (SQLException | ClassNotFoundException ex) {
            throw new LoginSampleException(ex.getMessage());
        }
    }

    public static MetalMaterial getMetalMaterial(int itemNumber) throws LoginSampleException {
        try {
            Connection con = Connector.connection();
            String SQL = "SELECT * FROM FogDB.Materials "
                    + "WHERE itemNumber = ?;";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setInt(1, itemNumber);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String materialName = rs.getString("materialName");
                String unit = rs.getString("unit");
                double price = rs.getDouble("price");
                int soldInPacksOf = rs.getInt("amountInPackage");
                int amountInStock = rs.getInt("inStock");
                return new MetalMaterial(itemNumber, materialName, unit, price, soldInPacksOf, amountInStock);
            } else {
                throw new LoginSampleException("Material doesn't exist.");
            }
        } catch (SQLException | ClassNotFoundException ex) {
            throw new LoginSampleException(ex.getMessage());
        }
    }

    public static List<Order> getAllOrders() throws LoginSampleException {
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

//    public static ArrayList<Order> getOrdersByEmail(String email) {
//        ArrayList<Order> orders = new ArrayList();
//        try {
//
//            Connection con = Connector.connection();
//            String SQL = "SELECT * FROM FogDB.Order "
//                    + "WHERE userID = ?;";
//            PreparedStatement ps = con.prepareStatement(SQL);
//
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()) {
//                int orderID = rs.getInt("orderID");
//                int length = rs.getInt("length");
//                int width = rs.getInt("width");
//                int height = rs.getInt("height");
//                int angle = rs.getInt("angle");
//                double finalizedPrice = rs.getDouble("price");
//                orders.add(new Order(orderID, length, width, height, angle, finalizedPrice));
//
//            }
//        } catch (SQLException | ClassNotFoundException ex) {
//            Logger.getLogger(MaterialAndOrderMapper.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return orders;
//    }
//    public static ArrayList<Order> getOrdersByEmail(String email) {
//        ArrayList<Order> orders = new ArrayList();
//        try {
//
//            Connection con = Connector.connection();
//            String SQL = "SELECT * FROM FogDB.Order "
//                    + "WHERE userID = ?;";
//            PreparedStatement ps = con.prepareStatement(SQL);
//
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()) {
//                int orderID = rs.getInt("orderID");
//                int length = rs.getInt("length");
//                int width = rs.getInt("width");
//                int height = rs.getInt("height");
//                int angle = rs.getInt("angle");
//                double finalizedPrice = rs.getDouble("finalizedPrice");
//                orders.add(new Order(orderID, length, width, height, angle, finalizedPrice));
//
//            }
//        } catch (SQLException | ClassNotFoundException ex) {
//            Logger.getLogger(MaterialAndOrderMapper.class.getName()).log(Level.SEVERE, null, ex);
//        } 
//        return orders;
//    }
    public static Order getOrderByOrderID(int orderID) {
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
            Logger.getLogger(MaterialAndOrderMapper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static void approveOrder(Order order) throws LoginSampleException {
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

    public static void editOrderStatus(Order order, String status) throws LoginSampleException {
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

    public static List<Order> getOrdersbyUserID(User user) throws LoginSampleException {

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

    public static List<WoodMaterial> getAngledRoofMat() throws LoginSampleException {
        try {
            ArrayList<WoodMaterial> material = new ArrayList();
            Connection con = Connector.connection();
            String SQL = "SELECT * FROM FogDB.Materials WHERE category = 'Tagbelægning';";
            PreparedStatement ps = con.prepareStatement(SQL);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int itemNumber = rs.getInt("itemNumber");
                String materialName = rs.getString("materialName");
                String unit = rs.getString("unit");
                int price = rs.getInt("price");
                int amountInStock = rs.getInt("inStock");
                material.add(new WoodMaterial(itemNumber, materialName, unit, price, 0, amountInStock));
            }
            return material;
        } catch (SQLException | ClassNotFoundException ex) {
            throw new LoginSampleException(ex.getMessage());
        }

    }

    public static List<WoodMaterial> getSideMat() throws LoginSampleException {
        try {
            ArrayList<WoodMaterial> material = new ArrayList();
            Connection con = Connector.connection();
            String SQL = "SELECT * FROM FogDB.Materials WHERE category = 'Beklædning'";
            PreparedStatement ps = con.prepareStatement(SQL);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int itemNumber = rs.getInt("itemNumber");
                String materialName = rs.getString("materialName");
                String unit = rs.getString("unit");
                int price = rs.getInt("price");
                int lengthInStock = rs.getInt("stockLength");
                int amountInStock = rs.getInt("inStock");
                material.add(new WoodMaterial(itemNumber, materialName, unit, price, lengthInStock, amountInStock));
            }
            return material;
        } catch (SQLException | ClassNotFoundException ex) {
            throw new LoginSampleException(ex.getMessage());
        }
    }

    public static List<WoodMaterial> getFlatRoofMat() throws LoginSampleException {
        try {
            ArrayList<WoodMaterial> material = new ArrayList();
            Connection con = Connector.connection();
            String SQL = "SELECT * FROM FogDB.Materials WHERE category = 'Tag';";
            PreparedStatement ps = con.prepareStatement(SQL);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int itemNumber = rs.getInt("itemNumber");
                String materialName = rs.getString("materialName");
                String unit = rs.getString("unit");
                int price = rs.getInt("price");
                int soldInPacksOf = rs.getInt("amountInPackage");
                int amountInStock = rs.getInt("inStock");
                material.add(new WoodMaterial(itemNumber, materialName, unit, price, soldInPacksOf, amountInStock));
            }
            return material;
        } catch (SQLException | ClassNotFoundException ex) {
            throw new LoginSampleException(ex.getMessage());
        }

    }

    public static void addOrderToDB(Order order) throws LoginSampleException, SQLException, ClassNotFoundException {
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
            ps.setString(13, order.getStatus().toString());
            ps.executeUpdate();
            con.commit();
            con.setAutoCommit(true);
        } catch (SQLException | ClassNotFoundException ex) {
            throw new LoginSampleException(ex.getMessage());

        }

    }

    public static void updateStock(int newAmountInStock, int itemNumber) {
        try {
            Connection con = Connector.connection();
            con.setAutoCommit(false);
            String SQL = "UPDATE FogDB.Materials SET inStock = ? WHERE itemNumber = ?;";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setInt(1, newAmountInStock);
            ps.setInt(2, itemNumber);
            ps.executeUpdate();
            con.commit();
            con.setAutoCommit(true);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MaterialAndOrderMapper.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MaterialAndOrderMapper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void editOrderPrice(Order order, double newPrice) {
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
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MaterialAndOrderMapper.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MaterialAndOrderMapper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
