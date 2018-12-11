package DBAccess;

import FunctionLayer.Exceptions.LoginSampleException;
import FunctionLayer.Entity.Role;
import FunctionLayer.Entity.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The purpose of UserMapper is to...
 *
 */
public class UserMapper {

    static void createUser(User user) throws LoginSampleException {
        try {
            Connection con = Connector.connection();
            String SQL = "INSERT INTO User (email, password, role) VALUES (?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getRole().toString());
            ps.executeUpdate();
            ResultSet ids = ps.getGeneratedKeys();
            ids.next();
            int id = ids.getInt(1);
            user.setId(id);
        } catch (SQLException | ClassNotFoundException ex) {
            throw new LoginSampleException(ex.getMessage());
        }
    }

    static User login(String email, String password) throws LoginSampleException {
        try {
            Connection con = Connector.connection();
            String SQL = "SELECT UserID, role FROM User "
                    + "WHERE email=? AND password=?";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String role = rs.getString("role").toUpperCase();
                int id = rs.getInt("UserID");
                User user = new User(email, password, Role.valueOf(role));
                user.setId(id);
                return user;
            } else {
                throw new LoginSampleException("Could not validate user");
            }
        } catch (ClassNotFoundException | SQLException ex) {
            throw new LoginSampleException(ex.getMessage());
        }
    }

    static int getUserIDByEmail(String email) throws LoginSampleException {
        try {
            Connection con = Connector.connection();
            String SQL = "SELECT UserID FROM FogDB.User WHERE email = ?";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int userID = rs.getInt("UserID");
                return userID;
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(UserMapper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    static void removeCustomerByUserID(int UserID) throws LoginSampleException {
        try {
            Connection con = Connector.connection();
            String SQL = "DELETE FROM `FogDB`.`User` "
                    + "WHERE `UserID`= ?;";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setInt(1, UserID);
            ps.executeUpdate();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UserMapper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//    public static User getUser(String email, String password) throws LoginSampleException {
//        try {
//            Connection con = Connector.connection();
//            String SQL = "SELECT email, password FROM User "
//                    + "WHERE email=?";
//            PreparedStatement ps = con.prepareStatement(SQL);
//            ps.setString(1, email);
//            ResultSet rs = ps.executeQuery();
//            if (rs.next()) {
//                String role = rs.getString("role");
//                String userEmail = rs.getString("email");
//                String userPW = rs.getString("password");
//                int id = rs.getInt("id");
//                User user = new User(email, password, Role.valueOf(role));
//                user.setId(id);
//                return user;
//
//            } else {
//                throw new LoginSampleException("Could not validate user");
//            }
//        } catch (ClassNotFoundException | SQLException ex) {
//            throw new LoginSampleException(ex.getMessage());
//        }
//
//    }
}
