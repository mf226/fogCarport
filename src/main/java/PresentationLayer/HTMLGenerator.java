/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentationLayer;

import FunctionLayer.Entity.MetalDetails;
import FunctionLayer.Entity.Order;
import FunctionLayer.Entity.Role;
import FunctionLayer.Entity.User;
import FunctionLayer.Entity.WoodDetails;
import FunctionLayer.Entity.WoodMaterial;
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
        StringBuilder sb = new StringBuilder();
        String roleLC = user.getRole().toString().toLowerCase() + "page";
        String role = roleLC.substring(0, 1).toUpperCase() + roleLC.substring(1, roleLC.length());
        sb.append("  <div class=\"userdropdown\">\n");
        sb.append("             <button class=\"userdropbtn\">Logged in as ").append(user.getEmail()).append("\n");
        sb.append("                <i class=\"fa fa-caret-down\"></i>\n");
        sb.append("                </button>\n");
        sb.append("                <div class=\"userdropdown-content\">\n");
        sb.append("                 <form action=\"FrontController\" method=\"POST\">\n");
        sb.append("                     <input type=\"submit\" value=\"").append(role).append("\">\n");
        sb.append("                     <input type=\"hidden\" name=\"command\" value=\"").append(role).append("\">\n");
        sb.append("                 </form>");
        sb.append("                 <form id=\"logout\" action=\"FrontController\" method=\"POST\">\n");
        sb.append("                     <input type=\"hidden\" name=\"command\" value=\"logout\">\n");
        sb.append("                     <input id=\"btn\" type=\"submit\" value=\"Logout\">\n");
        sb.append("                 </form>");
        sb.append("                </div>\n");
        sb.append("              </div>");
        return sb.toString();
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
        StringBuilder sb = new StringBuilder();
        sb.append("<form action=\"FrontController\" method=\"POST\">\n");
        sb.append(" <select name=\"status\">\n");
        sb.append("  <option value=\"approved\" Selected>Approved</option>\n");
        sb.append("  <option value=\"pending\">Pending</option>\n");
        sb.append("  <option value=\"denied\">Denied</option>\n");
        sb.append(" </select>");
        sb.append("  <input type=\"submit\" value=\"Update Order\">\n");
        sb.append("  <input type=\"hidden\" name=\"command\" value=\"updateOrder\">\n");
        sb.append("  <input type=\"number\" name=\"newPrice\" value=\"").append(order.getTotalOrderPrice()).append("\">\n");
        sb.append("</form>");
        return sb.toString();
    }

    public static String showAllOrdersByUser(List<Order> orders) {
        StringBuilder sb = new StringBuilder();
        sb.append("<table id=\"reviewTable\">\n");
        sb.append("            <thead>\n");
        sb.append("            <tr>\n");
        sb.append("                <th>Ordre ID</th>\n");
        sb.append("                <th>Status</th>\n");
        sb.append("                <th>User</th>\n");
        sb.append("                <th>Længde</th>\n");
        sb.append("                <th>Bredde</th>\n");
        sb.append("                <th>Dato</th>\n");
        sb.append("                <th>Pris</th>\n");
        sb.append("            </tr>\n");
        sb.append("            </thead>\n");

        for (int i = 0; i < orders.size(); i++) {
            sb.append("<form action=\"FrontController\" method=\"POST\">\n");
            sb.append(" <input type=\"hidden\" name=\"command\" value=\"customerReview\">\n");
            sb.append(" <input type=\"hidden\" name=\"orderID\" value=\"").append(orders.get(i).getOrderID()).append("\">\n");
            sb.append("<td class=\"reviewData\">").append(orders.get(i).getOrderID()).append("</td>\n");
            sb.append("<td class=\"reviewData\" id=\"statusField\">").append(orders.get(i).getStatus()).append("</td>\n");
            sb.append("<td class=\"reviewData\">").append(orders.get(i).getUserID()).append("</td>\n");
            sb.append("<td class=\"reviewData\">").append(orders.get(i).getLength()).append("</td>\n");
            sb.append("<td class=\"reviewData\">").append(orders.get(i).getWidth()).append("</td>\n");
            sb.append("<td class=\"reviewData\">").append(orders.get(i).getOrderDate()).append("</td>\n");
            sb.append("<td class=\"reviewData\">").append(orders.get(i).getPrice()).append("</td>\n");
            sb.append("<td><input class=\"reviewBtn\" type=\"submit\" value=\"Review\"></td>\n");
            sb.append("</tr>\n");
            sb.append("</form>");

        }
        return sb.toString();
    }

    public static String showAllOrders(List<Order> orders) {
        StringBuilder sb = new StringBuilder();
        sb.append("<table id=\"reviewTable\">\n");
        sb.append("            <thead>\n");
        sb.append("            <tr>\n");
        sb.append("                <th>Ordre ID</th>\n");
        sb.append("                <th>Status</th>\n");
        sb.append("                <th>User</th>\n");
        sb.append("                <th>Længde</th>\n");
        sb.append("                <th>Bredde</th>\n");
        sb.append("                <th>Dato</th>\n");
        sb.append("                <th>Pris</th>\n");
        sb.append("            </tr>\n");
        sb.append("            </thead>\n");

        for (int i = 0; i < orders.size(); i++) {
            sb.append("<form action=\"FrontController\" method=\"POST\">\n");
            sb.append("<input type=\"hidden\" name=\"command\" value=\"reviewOrder\">\n");
            sb.append("<input type=\"hidden\" name=\"orderID\" value=\"").append(orders.get(i).getOrderID()).append("\">\n");
            sb.append("<td class=\"reviewData\">").append(orders.get(i).getOrderID()).append("</td>\n");
            sb.append("<td class=\"reviewData\" id=\"statusField\">").append(orders.get(i).getStatus()).append("</td>\n");
            sb.append("<td class=\"reviewData\">").append(orders.get(i).getUserID()).append("</td>\n");
            sb.append("<td class=\"reviewData\">").append(orders.get(i).getLength()).append("</td>\n");
            sb.append("<td class=\"reviewData\">").append(orders.get(i).getWidth()).append("</td>\n");
            sb.append("<td class=\"reviewData\">").append(orders.get(i).getOrderDate()).append("</td>\n");
            sb.append("<td class=\"reviewData\">").append(orders.get(i).getPrice()).append("</td>\n");
            sb.append("<td><input class=\"reviewBtn\" type=\"submit\" value=\"Review\"></td>\n");
            sb.append("</tr>\n");
            sb.append("</form>");

        }
        return sb.toString();
    }

    public static String generateBOM(Order order) {
        StringBuilder sb = new StringBuilder();
        HashMap<String, WoodDetails> materialsWood = order.getCarportWoodMaterials();
        sb.append("<table id=\"BillOfMaterials\">\n");
        sb.append("            <thead>\n");
        sb.append("            <tr>\n");
        sb.append("                <th>Vare</th>\n");
        sb.append("                <th>Varenummer</th>\n");
        sb.append("                <th>Beskrivelse</th>\n");
        sb.append("                <th>Længde i cm</th>\n");
        sb.append("                <th>Enhed</th>\n");
        sb.append("                <th>Pris pr. enhed</th>\n");
        sb.append("                <th>Antal</th>\n");
        sb.append("                <th>Lagerbeholdning</th>\n");
        sb.append("                <th>Samlet pris</th>\n");
        sb.append("            </tr>\n");
        sb.append("            </thead>\n");

        sb.append(getWoodMaterialsList(materialsWood));
        HashMap<String, MetalDetails> materialsMetal = order.getCarportMetalMaterials();

        sb.append(getMetalMaterialsList(materialsMetal));
        if (order.isShedExists()) {
            HashMap<String, WoodDetails> shedMaterialsWood = order.getShedWoodMaterials();

            sb.append(getWoodMaterialsList(shedMaterialsWood));
            HashMap<String, MetalDetails> shedMaterialsMetal = order.getShedMetalMaterials();

            sb.append(getMetalMaterialsList(shedMaterialsMetal));
        }

        sb.append("<tr><td>Ialt</td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td>").append(order.getTotalOrderPrice()).append(" kr </td></tr>");
        sb.append("</table>");
        return sb.toString();
    }

    private static String getMetalMaterialsList(HashMap<String, MetalDetails> materialsMetal) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, MetalDetails> mapShedMetal : materialsMetal.entrySet()) {
            sb.append("<tr>");
            sb.append("<td>").append(mapShedMetal.getKey()).append("</td>");
            sb.append("<td>").append(mapShedMetal.getValue().getMaterial().getItemNumber()).append("</td>");
            sb.append("<td>").append(mapShedMetal.getValue().getMaterial().getName()).append("</td>");
            sb.append("<td></td>");
            sb.append("<td>").append(mapShedMetal.getValue().getMaterial().getUnit()).append("</td>");
            sb.append("<td>").append(mapShedMetal.getValue().getMaterial().getPricePerUnit()).append("  kr </td>");
            sb.append("<td>").append(mapShedMetal.getValue().getAmount()).append("</td>");
            sb.append("<td>").append(mapShedMetal.getValue().getMaterial().getAmountInStock()).append("</td>");

            sb.append("<td>").append(mapShedMetal.getValue().getTotalItemPrice()).append("  kr </td>");
            sb.append("</tr>");

        }
        return sb.toString();
    }
    private static String getWoodMaterialsList(HashMap<String, WoodDetails> materialsWood) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, WoodDetails> mapWood : materialsWood.entrySet()) {
            sb.append("<tr>");
            sb.append("<td>").append(mapWood.getKey()).append("</td>");
            sb.append("<td>").append(mapWood.getValue().getMaterial().getItemNumber()).append("</td>");
            sb.append("<td>").append(mapWood.getValue().getMaterial().getName()).append("</td>");
            sb.append("<td>").append(mapWood.getValue().getCmLengthEach()).append("</td>");
            sb.append("<td>").append(mapWood.getValue().getMaterial().getUnit()).append("</td>");
            sb.append("<td>").append(mapWood.getValue().getMaterial().getPricePerUnit()).append("  kr </td>");
            sb.append("<td>").append(mapWood.getValue().getAmount()).append("</td>");
            sb.append("<td>").append(mapWood.getValue().getMaterial().getAmountInStock()).append("</td>");
            sb.append("<td>").append(mapWood.getValue().getTotalItemPrice()).append("  kr </td>");
            sb.append("</tr>");

        }
        return sb.toString();
    }

    public static String generateShedMeasurements(int length, int width, List<WoodMaterial> sideMat) {
        StringBuilder sb = new StringBuilder();

        sb.append("<h4>Venligst vælg den ønskede størrelse af dit skur.</h4>\n");
        sb.append("            <form action=\"FrontController\" method=\"POST\">\n");
        sb.append("                <h5>Længde: </h5>\n");
        sb.append("                <select name=\"shedLength\">");
        //Shed Length        
        for (int i = 100; i < length; i += 100) {
            sb.append("<option value=\"").append(i).append("\">").append(i).append(" cm</option>");
        }
        sb.append("</select>\n");
        sb.append("\n");
        sb.append("                <h5>Bredde: </h5>\n");
        sb.append("                <select name=\"shedWidth\">");

        for (int i = 100; i < width; i += 100) {
            sb.append("<option value=\"").append(i).append("\">").append(i).append(" cm</option>");

        }
        sb.append("<option value=\"").append(width).append("\">").append(width).append(" cm</option>");

        sb.append("</select>\n");

        sb.append(sideMaterial(sideMat));
        sb.append("<h4>Vælg hvor dit skur skal placeres</h4>\n");
        sb.append("         <div class=\"shedPlacements\">\n");
        sb.append("         <div class=\"upperleft\" style=\"height:").append(width / 2).append("px; width: ").append(length / 2).append("px;\">\n");
        sb.append("                <input type=\"radio\" name=\"placement\" value=UL>");
        sb.append("                 <label for=\"UL\">øvre venstre</label>\n");
        sb.append("         </div>");
        sb.append("         <div class=\"upperright\" style=\"height:").append(width / 2).append("px; width: ").append(length / 2).append("px;\">\n");
        sb.append("                <input style=\"float:right;\" type=\"radio\" name=\"placement\" value=UR checked>");
        sb.append("                 <label style=\"float:right;\" for=\"UR\">øvre højre</label>\n");
        sb.append("         </div>");
        sb.append("         <div class=\"lowerleft\" style=\"margin-top:").append((width / 2) * 0.8).append("px; height:").append(width / 2).append("px; width: ").append(length / 2).append("px;\">\n");
        sb.append("                <input id=\"LL\" type=\"radio\" name=\"placement\" value=LL>");
        sb.append("                 <label for=\"LL\">nedre venstre</label>\n");
        sb.append("         </div>");
        sb.append("         <div class=\"lowerright\" style=\"margin-top:").append((width / 2) * 0.8).append("px; height:").append(width / 2).append("px; width: ").append(length / 2).append("px;\">\n");
        sb.append("                <input style=\"float:right;\" id=\"LR\" type=\"radio\" name=\"placement\" value=LR>");
        sb.append("                 <label style=\"float:right;\" for=\"LR\">nedre højre</label>\n");
        sb.append("         </div>");
        sb.append("       </div>\n");
        sb.append("                <input type=\"submit\" value=\"Fortsæt\">\n");
        sb.append("                <input type=\"hidden\" name=\"command\" value=\"addShed\">\n");
        sb.append("            </form>");

        return sb.toString();
    }

    public static String createRoofTypesFlat(List<WoodMaterial> roofs) {
        StringBuilder sb = new StringBuilder();
        sb.append("<h5>Tagtype:</h5>\n");
        sb.append("                <select name=\"roofType\">\n");

        for (int i = 0; i < roofs.size(); i++) {
            sb.append("<option value=\"").append(roofs.get(i).getItemNumber()).append("\">").append(roofs.get(i).getName()).append("</option>\n");
        }

        sb.append("</select>");

        return sb.toString();
    }

    public static String sideMaterial(List<WoodMaterial> sideMat) {
        StringBuilder sb = new StringBuilder();
        sb.append("<h5>Beklædningstype</h5>\n"
                + "<select name=\"sideMat\">\n");

        for (int i = 0; i < sideMat.size(); i++) {
            sb.append("<option value=\"").append(sideMat.get(i).getItemNumber()).append("\">").append(sideMat.get(i).getName()).append("</option>\n");

        }
        sb.append("</select>");
        return sb.toString();
    }

    public static String createRoofTypesAngled(List<WoodMaterial> roofs) {
        StringBuilder sb = new StringBuilder();
        sb.append("<h5>Tagtype:</h5>\n");
        sb.append("                <select name=\"roofType\">\n");

        for (int i = 0; i < roofs.size(); i++) {
            sb.append("<option value=\"" + roofs.get(i).getItemNumber() + "\">" + roofs.get(i).getName() + "</option>\n");
        }

        sb.append("</select>");

        return sb.toString();
    }
}
