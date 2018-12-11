package DBAccess;

import FunctionLayer.Exceptions.LoginSampleException;
import FunctionLayer.Entity.MetalMaterial;
import FunctionLayer.Entity.WoodMaterial;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Fen
 */
public class MaterialMapper {

    static List<WoodMaterial> getAllMaterials() throws LoginSampleException {
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

    static WoodMaterial getWoodMaterial(int itemNumber) throws LoginSampleException {
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

    static MetalMaterial getMetalMaterial(int itemNumber) throws LoginSampleException {
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

    static List<WoodMaterial> getAngledRoofMat() throws LoginSampleException {
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

    static List<WoodMaterial> getSideMat() throws LoginSampleException {
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

    static List<WoodMaterial> getFlatRoofMat() throws LoginSampleException {
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

    static void updateStock(int newAmountInStock, int itemNumber) throws LoginSampleException {
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
        } catch (SQLException | ClassNotFoundException ex) {
            throw new LoginSampleException(ex.getMessage());
        }
    }

}
