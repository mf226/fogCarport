package DBAccess;

import FunctionLayer.Exceptions.LoginException;
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

    /**
     * Returns a List of woodMaterials from the database
     *
     * @throws LoginException
     * @return List<WoodMaterial>
     */
    static List<WoodMaterial> getAllMaterials() throws LoginException {
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
            throw new LoginException(ex.getMessage());
        }
    }

    /**
     * Returns a WoodMaterial from the database with the given itemNumber
     *
     * @param int itemNumber
     * @throws LoginException
     * @return WoodMaterial
     */
    static WoodMaterial getWoodMaterial(int itemNumber) throws LoginException {
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
                throw new LoginException("Material doesn't exist.");
            }
        } catch (SQLException | ClassNotFoundException ex) {
            throw new LoginException(ex.getMessage());
        }
    }

    /**
     * Returns a MetalMaterial from the database with the given itemNumber
     *
     * @param int itemNumber
     * @throws LoginException
     * @return MetalMaterial
     */
    static MetalMaterial getMetalMaterial(int itemNumber) throws LoginException {
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
                throw new LoginException("Material doesn't exist.");
            }
        } catch (SQLException | ClassNotFoundException ex) {
            throw new LoginException(ex.getMessage());
        }
    }

    /**
     * Returns a List of WoodMaterial from the database for creating angled roof
     *
     * @throws LoginException
     * @return List<WoodMaterial>
     */
    static List<WoodMaterial> getAngledRoofMat() throws LoginException {
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
            throw new LoginException(ex.getMessage());
        }

    }

    /**
     * Returns a List of WoodMaterial from the database for creating shed-sides
     *
     * @throws LoginException
     * @return List<WoodMaterial>
     */
    static List<WoodMaterial> getSideMat() throws LoginException {
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
            throw new LoginException(ex.getMessage());
        }
    }
    /**
     * Returns a List of WoodMaterial from the database for creating flat roof
     *
     * @throws LoginException
     * @return List<WoodMaterial>
     */
    static List<WoodMaterial> getFlatRoofMat() throws LoginException {
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
            throw new LoginException(ex.getMessage());
        }

    }
    /**
     * Updates amount in stock with given amount on given itemNumber
     * @param int newAmountInStock
     * @param int itemNumber
     * @throws LoginException
     */
    static void updateStock(int newAmountInStock, int itemNumber) throws LoginException {
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
            throw new LoginException(ex.getMessage());
        }
    }

}
