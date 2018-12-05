package DBAccess;

import FunctionLayer.LoginSampleException;
import FunctionLayer.Material;
import FunctionLayer.MetalMaterial;
import FunctionLayer.WoodMaterial;
import FunctionLayer.Order;
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
            String SQL = "SELECT itemNumber, materialName, unit, price, stockLength FROM FogDB.Materials;";
            PreparedStatement ps = con.prepareStatement(SQL);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int itemNumber = rs.getInt("itemNumber");
                String materialName = rs.getString("materialName");
                String unit = rs.getString("unit");
                double price = rs.getDouble("price");
                int lengthInStock = rs.getInt("stockLength");
                materials.add(new WoodMaterial(itemNumber, materialName, unit, price, lengthInStock));
            }
            return materials;
        } catch (SQLException | ClassNotFoundException ex) {
            throw new LoginSampleException(ex.getMessage());
        }
    }

    public static WoodMaterial getWoodMaterial(int itemNumber) throws LoginSampleException {
        try {

            Connection con = Connector.connection();
            String SQL = "SELECT materialName, unit, price FROM FogDB.Materials "
                    + "WHERE itemNumber = ?;";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setInt(1, itemNumber);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String materialName = rs.getString("materialName");
                String unit = rs.getString("unit");
                double price = rs.getDouble("price");
                int lengthInStock = rs.getInt("stockLength");
                return new WoodMaterial(itemNumber, materialName, unit, price, lengthInStock);
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
            String SQL = "SELECT materialName, unit, price, amountInPackage FROM FogDB.Materials "
                    + "WHERE itemNumber = ?;";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setInt(1, itemNumber);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String materialName = rs.getString("materialName");
                String unit = rs.getString("unit");
                double price = rs.getDouble("price");
                int soldInPacksOf = rs.getInt("amountInPackage");
                return new MetalMaterial(itemNumber, materialName, unit, price, soldInPacksOf);
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
                int width = rs.getInt("width");
                int height = rs.getInt("height");
                double finalizedPrice = rs.getDouble("finalizedPrice");
//                Date orderDate = rs.getDate("orderDate");
                Order order = new Order(length, width, height, length);
                order.setUserID(userID);
                order.setFinalizedPrice(finalizedPrice);
                orders.add(order);
            }
            return orders;
        } catch (SQLException | ClassNotFoundException ex) {
            throw new LoginSampleException(ex.getMessage());
        }
    }

    public static ArrayList<Order> getOrdersByEmail(String email) {
        ArrayList<Order> orders = new ArrayList();
        try {

            Connection con = Connector.connection();
            String SQL = "SELECT orderID, length, width, height, angle, finalizedPrice FROM FogDB.Order "
                    + "WHERE userID = ?;";
            PreparedStatement ps = con.prepareStatement(SQL);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int orderID = rs.getInt("orderID");
                int length = rs.getInt("length");
                int width = rs.getInt("width");
                int height = rs.getInt("height");
                int angle = rs.getInt("angle");
                double finalizedPrice = rs.getDouble("finalizedPrice");
                orders.add(new Order(orderID, length, width, height, angle, finalizedPrice));

            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(MaterialAndOrderMapper.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return orders;
    }

    public static List<Order> getOrdersbyUserID(User user) throws LoginSampleException {

        try {
            ArrayList<Order> orders = new ArrayList();
            Connection con = Connector.connection();
            String SQL = "SELECT orderID, length, width, height, finalizedPrice, orderDate FROM FogDB.`Order` WHERE userID = ?;";
            PreparedStatement ps = con.prepareStatement(SQL);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int orderID = rs.getInt("orderID");
                int length = rs.getInt("length");
                int width = rs.getInt("width");
                int height = rs.getInt("height");
                double finalizedPrice = rs.getDouble("finalizedPrice");
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
            String SQL = "SELECT itemNumber, materialName, unit, price FROM FogDB.Materials WHERE category = 'Tagbelægning';";
            PreparedStatement ps = con.prepareStatement(SQL);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int itemNumber = rs.getInt("itemNumber");
                String materialName = rs.getString("materialName");
                String unit = rs.getString("unit");
                int price = rs.getInt("price");
                material.add(new WoodMaterial(itemNumber, materialName, unit, price, 0));
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
            String SQL = "SELECT itemNumber, materialName, unit, price stockLength FROM FogDB.Materials WHERE category = 'Beklædning'";
            PreparedStatement ps = con.prepareStatement(SQL);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int itemNumber = rs.getInt("itemNumber");
                String materialName = rs.getString("materialName");
                String unit = rs.getString("unit");
                int price = rs.getInt("price");
                int lengthInStock = rs.getInt("stockLength");
                material.add(new WoodMaterial(itemNumber, materialName, unit, price, lengthInStock));
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
            String SQL = "SELECT itemNumber, materialName, unit, price, amountInPackage FROM FogDB.Materials WHERE category = 'Tag';";
            PreparedStatement ps = con.prepareStatement(SQL);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int itemNumber = rs.getInt("itemNumber");
                String materialName = rs.getString("materialName");
                String unit = rs.getString("unit");
                int price = rs.getInt("price");
                int soldInPacksOf = rs.getInt("amountInPackage");
                material.add(new WoodMaterial(itemNumber, materialName, unit, price, soldInPacksOf));
            }
            return material;
        } catch (SQLException | ClassNotFoundException ex) {
            throw new LoginSampleException(ex.getMessage());
        }

    }
}
