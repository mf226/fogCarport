/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentationLayer;

import FunctionLayer.Entity.MetalDetails;
import FunctionLayer.Entity.Order;
import FunctionLayer.Entity.Role;
import FunctionLayer.RulesAndConstants;
import FunctionLayer.Entity.User;
import FunctionLayer.Entity.User;
import FunctionLayer.Entity.WoodDetails;
import FunctionLayer.Entity.WoodMaterial;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author jonab
 */
public class HTMLGenerator {

    private static String active = "class=\"active\"";

    private static String headerBackground = "<img class=\"header-background\" src=\"images/woody.jpg\">";
    private static String home = "<form id=\"Home\" action=\"FrontController\" method=\"POST\">\n"
            + "            <input type=\"hidden\" name=\"command\" value=\"home\">\n"
            + "            <input id=\"btn\" type=\"submit\" value=\"Home\">\n"
            + "        </form>";
    private static String createHouse = "<form id=\"createHouse\" action=\"FrontController\" method=\"POST\">\n"
            + "            <input type=\"hidden\" name=\"command\" value=\"createHouse\">\n"
            + "            <input id=\"btn\" type=\"submit\" value=\"Create House\">\n"
            + "        </form>";

    private static String designDropdown = "  <div class=\"dropdown\">\n"
            + "             <button class=\"dropbtn\">Design Carport\n"
            + "                <i class=\"fa fa-caret-down\"></i>\n"
            + "                </button>\n"
            + "                <div class=\"dropdown-content\">\n"
            + "                 <form action=\"FrontController\" method=\"POST\">\n"
            + "                     <input type=\"submit\" value=\"Med fladt tag\">\n"
            + "                     <input type=\"hidden\" name=\"command\" value=\"measurementFlat\">\n"
            + "                 </form>"
            + "                 <form action=\"FrontController\" method=\"POST\">\n"
            + "                     <input type=\"submit\" value=\"Med skråt tag\">\n"
            + "                     <input type=\"hidden\" name=\"command\" value=\"measurementAngled\">\n"
            + "                 </form>"
            + "                </div>\n"
            + "              </div>";
    private static String register = "<form id=\"register\" action=\"FrontController\" method=\"POST\">\n"
            + "            <input type=\"hidden\" name=\"command\" value=\"registerpage\">\n"
            + "            <input id=\"btn\" type=\"submit\" value=\"Register\">\n"
            + "        </form>";
    private static String login = "<form id=\"login\" action=\"FrontController\" method=\"POST\">\n"
            + "            <input type=\"hidden\" name=\"command\" value=\"loginpage\">\n"
            + "            <input id=\"btn\" type=\"submit\" value=\"Login\">\n"
            + "        </form>";
    private static String logout = "<form id=\"logout\" action=\"FrontController\" method=\"POST\">\n"
            + "            <input type=\"hidden\" name=\"command\" value=\"logout\">\n"
            + "            <input id=\"btn\" type=\"submit\" value=\"Logout\">\n"
            + "        </form>";
    private static String employee = "<form id=\"employee\" action=\"FrontController\" method=\"POST\">\n"
            + "            <input type=\"hidden\" name=\"command\" value=\"employee\">\n"
            + "            <input id=\"btn\" type=\"submit\" value=\"View all orders\">\n"
            + "        </form>";

    private static String fogIcon = "<form action=\"FrontController\" method=\"POST\">\n"
            + "             <input type=\"image\" src=\"images/fogIcon.png\" alt=\"Fog Icon\">"
            + "             <input type=\"hidden\" name=\"command\" value=\"home\">\n"
            + "             </img>"
            + "             </form>";

  

    public static String userDropdown(User user) {
        String roleLC = user.getRole().toString().toLowerCase() + "page";
        String role = roleLC.substring(0, 1).toUpperCase() + roleLC.substring(1, roleLC.length());
        String userDropdown = "  <div class=\"userdropdown\">\n"
                + "             <button class=\"userdropbtn\">Logged in as " + user.getEmail() + "\n"
                + "                <i class=\"fa fa-caret-down\"></i>\n"
                + "                </button>\n"
                + "                <div class=\"userdropdown-content\">\n"
                + "                 <form action=\"FrontController\" method=\"POST\">\n"
                + "                     <input type=\"submit\" value=\"" + role + "\">\n"
                + "                     <input type=\"hidden\" name=\"command\" value=\"" + role + "\">\n"
                + "                 </form>"
                + "                 <form id=\"logout\" action=\"FrontController\" method=\"POST\">\n"
                + "                     <input type=\"hidden\" name=\"command\" value=\"logout\">\n"
                + "                     <input id=\"btn\" type=\"submit\" value=\"Logout\">\n"
                + "                 </form>"
                + "                </div>\n"
                + "              </div>";
        return userDropdown;
    }

    public static String generateMenu(HttpServletRequest request) {
        User user;
        if (request.getSession(false) != null) {
            try {
                user = (User) request.getSession(false).getAttribute("user");
                if (Role.EMPLOYEE.equals(user.getRole())) {
                    return "<!-- Logged In as employee--><div class=\"topnav\">\n"
                            + fogIcon + "\n"
                            + designDropdown + "\n"
                            + userDropdown(user)
                            + "</div>";
                }
                if (Role.ADMIN.equals(user.getRole())) {
                    return "<!-- Logged In as admin--><div class=\"topnav\">\n"
                            + fogIcon + "\n"
                            + designDropdown + "\n"
                            + userDropdown(user)
                            + "</div>";
                }
                if (user.getEmail() != null) {
                    return "<!-- Logged In as customer --><div class=\"topnav\">\n"
                            + fogIcon + "\n"
                            + designDropdown + "\n"
                            + userDropdown(user)
                            + "</div>";
                }
            } catch (NullPointerException ne) {
                ne.printStackTrace();
            }

        }
        return "<!--Not Logged In --><div class=\"topnav\">\n"
                + fogIcon + "\n"
                + designDropdown + "\n"
                + login + "\n"
                + register + "\n"
                + "</div>";
    }

    public static String updateOrderInput(Order order) {

        String btns = "<form action=\"FrontController\" method=\"POST\">\n";
        btns += "<select name=\"status\">\n"
                + "  <option value=\"approved\" Selected>Approved</option>\n"
                + "  <option value=\"pending\">Pending</option>\n"
                + "  <option value=\"denied\">Denied</option>\n"
                + "</select>"
                + "  <input type=\"submit\" value=\"Update Order\">\n"
                + "  <input type=\"hidden\" name=\"command\" value=\"updateOrder\">\n"
                + "  <input type=\"number\" name=\"newPrice\" value=\"" + order.getTotalOrderPrice() + "\">\n"
                + "                </form>";
        return btns;
    }

    public static String showAllOrders(List<Order> orders) {
        String table = "<table id=\"reviewTable\">\n"
                + "            <thead>\n"
                + "            <tr>\n"
                + "                <th>Ordre ID</th>\n"
                + "                <th>Status</th>\n"
                + "                <th>User</th>\n"
                + "                <th>Længde</th>\n"
                + "                <th>Bredde</th>\n"
                + "                <th>Dato</th>\n"
                + "                <th>Pris</th>\n"
                + "            </tr>\n"
                + "            </thead>\n";

        for (int i = 0; i < orders.size(); i++) {
            table += "<form action=\"FrontController\" method=\"POST\">\n"
                    + " <input type=\"hidden\" name=\"command\" value=\"reviewOrder\">\n"
                    + " <input type=\"hidden\" name=\"orderID\" value=\"" + orders.get(i).getOrderID() + "\">\n"
                    + "<td class=\"reviewData\">" + orders.get(i).getOrderID() + "</td>\n"
                    + "<td class=\"reviewData\" id=\"statusField\">" + orders.get(i).getStatus() + "</td>\n"
                    + "<td class=\"reviewData\">" + orders.get(i).getUserID() + "</td>\n"
                    + "<td class=\"reviewData\">" + orders.get(i).getLength() + "</td>\n"
                    + "<td class=\"reviewData\">" + orders.get(i).getWidth() + "</td>\n"
                    + "<td class=\"reviewData\">" + orders.get(i).getOrderDate() + "</td>\n"
                    + "<td class=\"reviewData\">" + orders.get(i).getPrice() + "</td>\n"
                    + "<td><input class=\"reviewBtn\" type=\"submit\" value=\"Review\"></td>\n"
                    + "</tr>\n"
                    + "</form>";

        }
        return table;
    }

    public static String generateBOM(Order order) {
        HashMap<String, WoodDetails> materialsWood = order.getCarportWoodMaterials();
        String table = "<table id=\"BillOfMaterials\">\n"
                + "            <thead>\n"
                + "            <tr>\n"
                + "                <th>Vare</th>\n"
                + "                <th>Varenummer</th>\n"
                + "                <th>Beskrivelse</th>\n"
                + "                <th>Længde i cm</th>\n"
                + "                <th>Enhed</th>\n"
                + "                <th>Pris pr. enhed</th>\n"
                + "                <th>Antal</th>\n"
                + "                <th>Lagerbeholdning</th>\n"
                + "                <th>Samlet pris</th>\n"
                + "            </tr>\n"
                + "            </thead>\n";

        for (Map.Entry<String, WoodDetails> mapWood : materialsWood.entrySet()) {
            table += "<tr>";
            table += "<td>" + mapWood.getKey() + "</td>";
            table += "<td>" + mapWood.getValue().getMaterial().getItemNumber() + "</td>";
            table += "<td>" + mapWood.getValue().getMaterial().getName() + "</td>";
            table += "<td>" + mapWood.getValue().getCmLengthEach() + "</td>";
            table += "<td>" + mapWood.getValue().getMaterial().getUnit() + "</td>";
            table += "<td>" + mapWood.getValue().getMaterial().getPricePerUnit() + "  kr </td>";
            table += "<td>" + mapWood.getValue().getAmount() + "</td>";
            table += "<td>" + mapWood.getValue().getMaterial().getAmountInStock() + "</td>";
            table += "<td>" + mapWood.getValue().getTotalItemPrice() + "  kr </td>";
            table += "</tr>";

        }
        HashMap<String, MetalDetails> materialsMetal = order.getCarportMetalMaterials();

        for (Map.Entry<String, MetalDetails> mapShedMetal : materialsMetal.entrySet()) {
            table += "<tr>";
            table += "<td>" + mapShedMetal.getKey() + "</td>";
            table += "<td>" + mapShedMetal.getValue().getMaterial().getItemNumber() + "</td>";
            table += "<td>" + mapShedMetal.getValue().getMaterial().getName() + "</td>";
            table += "<td></td>";
            table += "<td>" + mapShedMetal.getValue().getMaterial().getUnit() + "</td>";
            table += "<td>" + mapShedMetal.getValue().getMaterial().getPricePerUnit() + "  kr </td>";
            table += "<td>" + mapShedMetal.getValue().getAmount() + "</td>";
            table += "<td>" + mapShedMetal.getValue().getMaterial().getAmountInStock() + "</td>";

            table += "<td>" + mapShedMetal.getValue().getTotalItemPrice() + "  kr </td>";
            table += "</tr>";

        }
        if (order.isShedExists()) {
            HashMap<String, WoodDetails> shedMaterialsWood = order.getShedWoodMaterials();

            for (Map.Entry<String, WoodDetails> mapShedWood : shedMaterialsWood.entrySet()) {
                table += "<tr>";
                table += "<td>" + mapShedWood.getKey() + "</td>";
                table += "<td>" + mapShedWood.getValue().getMaterial().getItemNumber() + "</td>";
                table += "<td>" + mapShedWood.getValue().getMaterial().getName() + "</td>";
                table += "<td>" + mapShedWood.getValue().getCmLengthEach() + "</td>";
                table += "<td>" + mapShedWood.getValue().getMaterial().getUnit() + "</td>";
                table += "<td>" + mapShedWood.getValue().getMaterial().getPricePerUnit() + "  kr </td>";
                table += "<td>" + mapShedWood.getValue().getAmount() + "</td>";
                table += "<td>" + mapShedWood.getValue().getMaterial().getAmountInStock() + "</td>";

                table += "<td>" + mapShedWood.getValue().getTotalItemPrice() + "  kr </td>";
                table += "</tr>";

            }
            HashMap<String, MetalDetails> shedMaterialsMetal = order.getShedMetalMaterials();

            for (Map.Entry<String, MetalDetails> mapMetal : shedMaterialsMetal.entrySet()) {
                table += "<tr>";
                table += "<td>" + mapMetal.getKey() + "</td>";
                table += "<td>" + mapMetal.getValue().getMaterial().getItemNumber() + "</td>";
                table += "<td>" + mapMetal.getValue().getMaterial().getName() + "</td>";
                table += "<td></td>";
                table += "<td>" + mapMetal.getValue().getMaterial().getUnit() + "</td>";
                table += "<td>" + mapMetal.getValue().getMaterial().getPricePerUnit() + "  kr </td>";
                table += "<td>" + mapMetal.getValue().getAmount() + "</td>";
                table += "<td>" + mapMetal.getValue().getMaterial().getAmountInStock() + "</td>";

                table += "<td>" + mapMetal.getValue().getTotalItemPrice() + "  kr </td>";
                table += "</tr>";

            }
        }

        table += "<tr><td>Ialt</td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td>" + order.getTotalOrderPrice() + " kr </td></tr>";
        table += "</table>";
        return table;
    }

    public static String generateShedMeasurements(int length, int width, List<WoodMaterial> sideMat) {

        String shed = "<h4>Venligst vælg den ønskede størrelse af dit skur.</h4>\n"
                + "            <form action=\"FrontController\" method=\"POST\">\n"
                + "                <h5>Længde: </h5>\n"
                + "                <select name=\"shedLength\">";
        //Shed Length        
        for (int i = 100; i < length; i += 100) {
            shed += "<option value=\"" + i + "\">" + i + " cm</option>";
        }
        shed += "</select>\n"
                + "\n"
                + "                <h5>Bredde: </h5>\n"
                + "                <select name=\"shedWidth\">";

        for (int i = 100; i < width; i += 100) {
            shed += "<option value=\"" + i + "\">" + i + " cm</option>";

        }
        shed += "<option value=\"" + width + "\">" + width + " cm</option>";

        shed += "</select>\n";

        shed += sideMaterial(sideMat);
        shed += "<h4>Vælg hvor dit skur skal placeres</h4>\n"
                + "         <div class=\"shedPlacements\">\n"
                + "         <div class=\"upperleft\" style=\"height:" + (width / 2) + "px; width: " + (length / 2) + "px;\">\n"
                + "                <input type=\"radio\" name=\"placement\" value=UL>"
                + "                 <label for=\"UL\">øvre venstre</label>\n"
                + "         </div>"
                + "         <div class=\"upperright\" style=\"height:" + (width / 2) + "px; width: " + (length / 2) + "px;\">\n"
                + "                <input style=\"float:right;\" type=\"radio\" name=\"placement\" value=UR checked>"
                + "                 <label style=\"float:right;\" for=\"UR\">øvre højre</label>\n"
                + "         </div>"
                + "         <div class=\"lowerleft\" style=\"margin-top:" + ((width / 2) * 0.8) + "px; height:" + (width / 2) + "px; width: " + (length / 2) + "px;\">\n"
                + "                <input id=\"LL\" type=\"radio\" name=\"placement\" value=LL>"
                + "                 <label for=\"LL\">nedre venstre</label>\n"
                + "         </div>"
                + "         <div class=\"lowerright\" style=\"margin-top:" + ((width / 2) * 0.8) + "px; height:" + (width / 2) + "px; width: " + (length / 2) + "px;\">\n"
                + "                <input style=\"float:right;\" id=\"LR\" type=\"radio\" name=\"placement\" value=LR>"
                + "                 <label style=\"float:right;\" for=\"LR\">nedre højre</label>\n"
                + "         </div>"
                + "       </div>\n"
                + "                <input type=\"submit\" value=\"Fortsæt\">\n"
                + "                <input type=\"hidden\" name=\"command\" value=\"addShed\">\n"
                + "            </form>";

        return shed;
    }
    
        
    public static String createRoofTypesFlat(List<WoodMaterial> roofs) {
        String roof = "<h5>Tagtype:</h5>\n"
                + "                <select name=\"roofType\">\n";

        for (int i = 0; i < roofs.size(); i++) {
            roof += "<option value=\"" + roofs.get(i).getItemNumber() + "\">" + roofs.get(i).getName() + "</option>\n";
        }

        roof += "</select>";

        return roof;
    }
        
    public static String sideMaterial(List<WoodMaterial> sideMat) {
        String matType = "<h5>Beklædningstype</h5>\n"
                + "<select name=\"sideMat\">\n";

        for (int i = 0; i < sideMat.size(); i++) {
            matType += "<option value=\"" + sideMat.get(i).getItemNumber() + "\">" + sideMat.get(i).getName() + "</option>\n";

        }
        matType += "</select>";
        return matType;
    }

    public static String createRoofTypesAngled(List<WoodMaterial> roofs) {
        String roof = "<h5>Tagtype:</h5>\n"
                + "                <select name=\"roofType\">\n";

        for (int i = 0; i < roofs.size(); i++) {
            roof += "<option value=\"" + roofs.get(i).getItemNumber() + "\">" + roofs.get(i).getName() + "</option>\n";
        }

        roof += "</select>";

        return roof;
    }
}
