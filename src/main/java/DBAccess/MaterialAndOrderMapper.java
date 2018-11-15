package DBAccess;

import FunctionLayer.LoginSampleException;
import FunctionLayer.Material;
import FunctionLayer.Order;
import FunctionLayer.User;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fen
 */
public class MaterialAndOrderMapper {

    public static List<Material> getAllMaterials() throws LoginSampleException {
        try {
            ArrayList<Material> materials = new ArrayList();
            Connection con = Connector.connection();
            String SQL = "SELECT itemNumber, materialName, unit, price FROM FogDB.Materials;";
            PreparedStatement ps = con.prepareStatement(SQL);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int itemNumber = rs.getInt("itemNumber");
                String materialName = rs.getString("materialName");
                String unit = rs.getString("unit");
                double price = rs.getDouble("price");
                materials.add(new Material(itemNumber, materialName, unit, price));
            }
            return materials;
        } catch (SQLException | ClassNotFoundException ex) {
            throw new LoginSampleException(ex.getMessage());
        }
    }

    public static Material getMaterial(int itemNumber) throws LoginSampleException {
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
                return new Material(itemNumber, materialName, unit, price);
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
            String SQL = "SELECT orderID, orderDescription, price, User_UserID, orderDate FROM FogDB.`Order`;";
            PreparedStatement ps = con.prepareStatement(SQL);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int orderID = rs.getInt("orderID");
                String orderDescription = rs.getString("orderDescription");
                int price = rs.getInt("price");
                int userID = rs.getInt("User_UserID");
                Date orderDate = rs.getDate("orderDate");
                orders.add(new Order(orderID, orderDescription, price, userID, orderDate));
            }
            return orders;
        } catch (SQLException | ClassNotFoundException ex) {
            throw new LoginSampleException(ex.getMessage());
        }
    }

    public static List<Order> getOrdersbyUserID(User user) throws LoginSampleException {
       
        try {
            ArrayList<Order> orders = new ArrayList();
            Connection con = Connector.connection();
            String SQL = "SELECT orderID, orderDescription, price, orderDate FROM FogDB.`Order` WHERE User_UserID = ?;";
            PreparedStatement ps = con.prepareStatement(SQL);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int orderID = rs.getInt("orderID");
                String orderDescription = rs.getString("orderDescription");
                int price = rs.getInt("price");
                Date orderDate = rs.getDate("orderDate");
                orders.add(new Order(orderID, orderDescription, price, user.getId(), orderDate));
                
            }
            return orders;
        } catch (SQLException | ClassNotFoundException ex ) {
            throw new LoginSampleException(ex.getMessage());
        } 

    }

   

}
