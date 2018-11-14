package DBAccess;

import FunctionLayer.LoginSampleException;
import FunctionLayer.Material;
import FunctionLayer.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
    
}
