/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionLayer;

import FunctionLayer.User;
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

    private String active = "class=\"active\"";

    private String headerBackground = "<img class=\"header-background\" src=\"images/woody.jpg\">";
    private String home = "<form id=\"Home\" action=\"FrontController\" method=\"POST\">\n"
            + "            <input type=\"hidden\" name=\"command\" value=\"home\">\n"
            + "            <input id=\"btn\" type=\"submit\" value=\"Home\">\n"
            + "        </form>";
    private String createHouse = "<form id=\"createHouse\" action=\"FrontController\" method=\"POST\">\n"
            + "            <input type=\"hidden\" name=\"command\" value=\"createHouse\">\n"
            + "            <input id=\"btn\" type=\"submit\" value=\"Create House\">\n"
            + "        </form>";

    private String designDropdown = "  <div class=\"dropdown\">\n"
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
    private String register = "<form id=\"register\" action=\"FrontController\" method=\"POST\">\n"
            + "            <input type=\"hidden\" name=\"command\" value=\"registerpage\">\n"
            + "            <input id=\"btn\" type=\"submit\" value=\"Register\">\n"
            + "        </form>";
    private String login = "<form id=\"login\" action=\"FrontController\" method=\"POST\">\n"
            + "            <input type=\"hidden\" name=\"command\" value=\"loginpage\">\n"
            + "            <input id=\"btn\" type=\"submit\" value=\"Login\">\n"
            + "        </form>";
    private String logout = "<form id=\"logout\" action=\"FrontController\" method=\"POST\">\n"
            + "            <input type=\"hidden\" name=\"command\" value=\"logout\">\n"
            + "            <input id=\"btn\" type=\"submit\" value=\"Logout\">\n"
            + "        </form>";
    private String employee = "<form id=\"employee\" action=\"FrontController\" method=\"POST\">\n"
            + "            <input type=\"hidden\" name=\"command\" value=\"employee\">\n"
            + "            <input id=\"btn\" type=\"submit\" value=\"View all orders\">\n"
            + "        </form>";

    private String fogIcon = "<form action=\"FrontController\" method=\"POST\">\n"
            + "             <input type=\"image\" src=\"images/fogIcon.png\" alt=\"Fog Icon\">"
            + "             <input type=\"hidden\" name=\"command\" value=\"home\">\n"
            + "             </img>"
            + "             </form>";

    private final int outerCanvasW = 700;
    private final int outerCanvasL = 700;
    private final int innerCanvasW = 600;
    private final int innerCanvasl = 600;

    private String userDropdown(User user) {
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

    public String generateMenu(HttpServletRequest request) {
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

    public String showAllOrders(List<Order> orders) {
        String table = "<table id=\"table\">\n"
                + "            <thead>\n"
                + "            <tr>\n"
                + "                <th>Status</th>\n"
                + "                <th>User</th>\n"
                + "                <th>Længde</th>\n"
                + "                <th>Bredde</th>\n"
                + "                <th>Dato</th>\n"
                + "                <th>Pris</th>\n"
                + "            </tr>\n"
                + "            </thead>\n";

        for (int i = 0; i < orders.size(); i++) {
            table += "<tr>\n"
                    + "<td>" + orders.get(i).getStatus() + "</td>\n"
                    + "<td>" + orders.get(i).getUserID() + "</td>\n"
                    + "<td>" + orders.get(i).getLength() + "</td>\n"
                    + "<td>" + orders.get(i).getWidth() + "</td>\n"
                    + "<td>" + orders.get(i).getOrderDate() + "</td>\n"
                    + "<td>" + orders.get(i).getPrice() + "</td>\n"
                    + "</tr>\n";
            table += "<form action=\"FrontController\" method=\"POST\">\n"
                    + " <input class=\"reviewBtn\" type=\"submit\" value=\"Review\" name=\"orderID\">\n"
                    + "                <input type=\"hidden\" name=\"command\" value=\"reviewOrder\">\n"
                    + "                <input type=\"hidden\" name=\"orderID\" value=\"" + orders.get(i).getOrderID() + "\">\n"
                    + "            </form>";

        }
        return table;
    }

    public String generateBOM(Order order) {
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
                table += "<td>" + mapMetal.getValue().getTotalItemPrice() + "  kr </td>";
                table += "</tr>";

            }
        }
        table += "<tr><td>Ialt</td><td></td><td></td><td></td><td></td><td></td><td></td><td>" + order.getTotalOrderPrice() + " kr </td></tr>";
        table += "</table>";
        return table;
    }

    public String generateShedMeasurements(int length, int width, List<WoodMaterial> sideMat) {

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
                + "         <div class=\"lowerleft\" style=\"margin-top:" + (width / 2) + "px; height:" + (width / 2) + "px; width: " + (length / 2) + "px;\">\n"
                + "                <input id=\"LL\" type=\"radio\" name=\"placement\" value=LL>"
                + "                 <label for=\"LL\">nedre venstre</label>\n"
                + "         </div>"
                + "         <div class=\"lowerright\" style=\"margin-top:" + (width / 2) + "px; height:" + (width / 2) + "px; width: " + (length / 2) + "px;\">\n"
                + "                <input style=\"float:right;\" id=\"LR\" type=\"radio\" name=\"placement\" value=LR>"
                + "                 <label style=\"float:right;\" for=\"LR\">nedre højre</label>\n"
                + "         </div>"
                + "       </div>\n"
                + "                <input type=\"submit\" value=\"Fortsæt\">\n"
                + "                <input type=\"hidden\" name=\"command\" value=\"addShed\">\n"
                + "            </form>";

        return shed;
    }

    public String createRoofTypesFlat(List<WoodMaterial> roofs) {
        String roof = "<h5>Tagtype:</h5>\n"
                + "                <select name=\"roofType\">\n";

        for (int i = 0; i < roofs.size(); i++) {
            roof += "<option value=\"" + roofs.get(i).getItemNumber() + "\">" + roofs.get(i).getName() + "</option>\n";
        }

        roof += "</select>";

        return roof;
    }

    public String sideMaterial(List<WoodMaterial> sideMat) {
        String matType = "<h5>Beklædningstype</h5>\n"
                + "<select name=\"sideMat\">\n";

        for (int i = 0; i < sideMat.size(); i++) {
            matType += "<option value=\"" + sideMat.get(i).getItemNumber() + "\">" + sideMat.get(i).getName() + "</option>\n";

        }
        matType += "</select>";
        return matType;
    }

    public String createRoofTypesAngled(List<WoodMaterial> roofs) {
        String roof = "<h5>Tagtype:</h5>\n"
                + "                <select name=\"roofType\">\n";

        for (int i = 0; i < roofs.size(); i++) {
            roof += "<option value=\"" + roofs.get(i).getItemNumber() + "\">" + roofs.get(i).getName() + "</option>\n";
        }

        roof += "</select>";

        return roof;
    }

    public String createSketchSideViewFlat(Order order) {
        HashMap<String, WoodDetails> materials = order.getCarportWoodMaterials();
        final int innerX = 75;
        final int innerY = 100;
        String style = "style=\"\n"
                + "                  fill:white;\n"
                + "                  stroke-width:1;\n"
                + "                  stroke:rgb(0,0,0)\" />\n";
        //Outer Measurements
        WoodMaterial pole = (WoodMaterial) materials.get(RulesAndConstants.CARPORT_POSTS_DESCRIPTION).getMaterial();
        WoodMaterial rem = (WoodMaterial) materials.get(RulesAndConstants.CARPORT_REM_DESCRIPTION).getMaterial();
        WoodMaterial rafter = (WoodMaterial) materials.get(RulesAndConstants.CARPORT_RAFTER_FLATROOF_DESCRIPTON).getMaterial();

        int outerLength = order.getLength() + RulesAndConstants.ROOF_LENGTH_EXTRA;
        int innerLength = order.getLength();
        double innerHeight = order.getHeight();
        double outerHeight = order.getHeight() + (rem.getTopsideWidth() * 2);
        String sketch = "<svg width=\"" + outerCanvasW + "\" height=\"" + outerCanvasL + "\"scale>\n";
        //OuterLength
        sketch += "<line x1=\"" + (innerX - RulesAndConstants.ROOF_LENGTH_EXTRA / 2) + "\" y1=\"" + 0 + "\" x2=\"" + (innerX + (outerLength - RulesAndConstants.ROOF_WIDTH_EXTRA / 2)) + "\" y2=\"" + 0 + "\"" + style;
        sketch += "<text x=\"" + order.getLength() / 2 + "\" y=\"15\" font-family=\"sans-serif\" font-size=\"12px\" fill=\"black\">Længde: " + outerLength + " cm</text>";
        //InnerLength
        sketch += "<line x1=\"" + innerX + "\" y1=\"" + 22 + "\" x2=\"" + (innerLength + innerX) + "\" y2=\"" + 22 + "\"" + style;
        sketch += "<text x=\"" + order.getLength() / 2 + "\" y=\"32\" font-family=\"sans-serif\" font-size=\"12px\" fill=\"black\">Indre længde: " + innerLength + " cm</text>";
        //OuterHeight
        sketch += "<line x1=\"" + 0 + "\" y1=\"" + (innerY - (rafter.getTopsideWidth() * 2)) + "\" x2=\"" + 0 + "\" y2=\"" + (innerY + innerHeight) + "\"" + style;
        sketch += "<text x=\"" + 10 + "\" y=\"" + order.getWidth() / 2 + "\" writing-mode=\"tb-rl\" glyph-orientation-vertical=\"0\" font-family=\"sans-serif\" font-size=\"12px\" fill=\"black\">Højde: " + outerHeight + " cm</text>";
        //InnerHeight
        sketch += "<line x1=\"" + 22 + "\" y1=\"" + innerY + "\" x2=\"" + 22 + "\" y2=\"" + (order.getHeight() + innerY) + "\"" + style;
        sketch += "<text x=\"" + 32 + "\" y=\"" + order.getWidth() / 2 + "\" writing-mode=\"tb-rl\" glyph-orientation-vertical=\"0\" font-family=\"sans-serif\" font-size=\"12px\" fill=\"black\">Indre højde: " + innerHeight + " cm</text>";
        //Stolper
        double amount = materials.get(RulesAndConstants.CARPORT_POSTS_DESCRIPTION).getAmount() / 2;
        WoodDetails poles = materials.get(RulesAndConstants.CARPORT_POSTS_DESCRIPTION);
        double xSpacing = order.getLength() / (amount - 1);
        int x = innerX;
        int y = innerY;
        //adding shed before poles
        if (order.isShedExists()) {
            if (order.getShedWidth() < order.getWidth() || order.getShedPlacement().equals("UR") || order.getShedPlacement().equals("UL")) {
                sketch += addShedSideView(innerX, innerY, order);
            }
        }
        for (int i = 0; i < amount; i++) {

            sketch += "<<rect x=\"" + x + "\" y=\"" + y + "\" width=\"" + pole.getTopsideLength() + "\" height=\"" + (poles.getCmLengthEach() - RulesAndConstants.LENGTH_UNDER_GROUND) + "\"" + style;
            x += xSpacing;
        }
        //adding Shed after poles
        if (order.isShedExists()) {
            if (order.getShedWidth() == order.getWidth() || order.getShedPlacement().equals("LR") || order.getShedPlacement().equals("LL")) {
                sketch += addShedSideView(innerX, innerY, order);
            }
        }
        //Remme
        amount = materials.get(RulesAndConstants.CARPORT_REM_DESCRIPTION).getAmount();

        y = innerY - rem.getTopsideWidth();
        x = innerX - RulesAndConstants.ROOF_LENGTH_EXTRA / 2;
        WoodMaterial mat = (WoodMaterial) materials.get(RulesAndConstants.CARPORT_REM_DESCRIPTION).getMaterial();
        sketch += "<rect x=\"" + x + "\" y=\"" + y + "\" width=\"" + materials.get(RulesAndConstants.CARPORT_REM_DESCRIPTION).getCmLengthEach() + "\" height=\"" + mat.getTopsideWidth() + "\"" + style;

        //Spær - Horizontal
        amount = materials.get(RulesAndConstants.CARPORT_RAFTER_FLATROOF_DESCRIPTON).getAmount();
        xSpacing = order.getLength() / (amount - 1);
        x = innerX;
        y -= rafter.getTopsideWidth();
        for (int i = 0; i < amount; i++) {
            sketch += "<rect x=\"" + x + "\" y=\"" + y + "\" width=\"4.5\" height=\"" + rafter.getTopsideWidth() + "\"" + style;
            x += xSpacing;
        }
        sketch += "</svg>";
        return sketch;
    }

    public String createSketchSideViewAngled(Order order) {
        HashMap<String, WoodDetails> materials = order.getCarportWoodMaterials();
        final int innerX = 75;
        final int innerY = 100;
        String style = "style=\"\n"
                + "                  fill:white;\n"
                + "                  stroke-width:1;\n"
                + "                  stroke:rgb(0,0,0)\" />\n";
        //Outer Measurements
        WoodMaterial pole = (WoodMaterial) materials.get(RulesAndConstants.CARPORT_POSTS_DESCRIPTION).getMaterial();
        WoodMaterial rem = (WoodMaterial) materials.get(RulesAndConstants.CARPORT_REM_DESCRIPTION).getMaterial();
        WoodMaterial rafter = (WoodMaterial) materials.get(RulesAndConstants.CARPORT_RAFTER_ANGLEDROOF_BOTTOM_DESCRIPTION).getMaterial();

        int outerLength = order.getLength() + RulesAndConstants.ROOF_LENGTH_EXTRA;
        int innerLength = order.getLength();
        double innerHeight = order.getHeight();
        double outerHeight = order.getHeight() + (rem.getTopsideWidth() * 2);
        String sketch = "<svg width=\"" + outerCanvasW + "\" height=\"" + outerCanvasL + "\"scale>\n";
        //OuterLength
        sketch += "<line x1=\"" + (innerX - RulesAndConstants.ROOF_LENGTH_EXTRA / 2) + "\" y1=\"" + 0 + "\" x2=\"" + (innerX + (outerLength - RulesAndConstants.ROOF_WIDTH_EXTRA / 2)) + "\" y2=\"" + 0 + "\"" + style;
        sketch += "<text x=\"" + order.getLength() / 2 + "\" y=\"15\" font-family=\"sans-serif\" font-size=\"12px\" fill=\"black\">Længde: " + outerLength + " cm</text>";
        //InnerLength
        sketch += "<line x1=\"" + innerX + "\" y1=\"" + 22 + "\" x2=\"" + (innerLength + innerX) + "\" y2=\"" + 22 + "\"" + style;
        sketch += "<text x=\"" + order.getLength() / 2 + "\" y=\"32\" font-family=\"sans-serif\" font-size=\"12px\" fill=\"black\">Indre længde: " + innerLength + " cm</text>";
        //OuterHeight
        sketch += "<line x1=\"" + 0 + "\" y1=\"" + (innerY - (rafter.getTopsideWidth() * 2)) + "\" x2=\"" + 0 + "\" y2=\"" + (innerY + innerHeight) + "\"" + style;
        sketch += "<text x=\"" + 10 + "\" y=\"" + order.getWidth() / 2 + "\" writing-mode=\"tb-rl\" glyph-orientation-vertical=\"0\" font-family=\"sans-serif\" font-size=\"12px\" fill=\"black\">Højde: " + outerHeight + " cm</text>";
        //InnerHeight
        sketch += "<line x1=\"" + 22 + "\" y1=\"" + innerY + "\" x2=\"" + 22 + "\" y2=\"" + (order.getHeight() + innerY) + "\"" + style;
        sketch += "<text x=\"" + 32 + "\" y=\"" + order.getWidth() / 2 + "\" writing-mode=\"tb-rl\" glyph-orientation-vertical=\"0\" font-family=\"sans-serif\" font-size=\"12px\" fill=\"black\">Indre højde: " + innerHeight + " cm</text>";
        //Stolper
        double amount = materials.get(RulesAndConstants.CARPORT_POSTS_DESCRIPTION).getAmount() / 2;
        WoodDetails poles = materials.get(RulesAndConstants.CARPORT_POSTS_DESCRIPTION);
        double xSpacing = order.getLength() / (amount - 1);
        int x = innerX;
        int y = innerY;
        //adding shed before poles
        if (order.isShedExists()) {

            if (order.getShedWidth() < order.getWidth() || order.getShedPlacement().equals("UR") || order.getShedPlacement().equals("UL")) {
                sketch += addShedSideView(innerX, innerY, order);
            }
        }
        for (int i = 0; i < amount; i++) {

            sketch += "<<rect x=\"" + x + "\" y=\"" + y + "\" width=\"" + pole.getTopsideLength() + "\" height=\"" + (poles.getCmLengthEach() - RulesAndConstants.LENGTH_UNDER_GROUND) + "\"" + style;
            x += xSpacing;
        }
        //adding Shed after poles
        if (order.isShedExists()) {
            if (order.getShedWidth() == order.getWidth() || order.getShedPlacement().equals("LR") || order.getShedPlacement().equals("LL")) {
                sketch += addShedSideView(innerX, innerY, order);
            }
        }
        //Remme
        amount = materials.get(RulesAndConstants.CARPORT_REM_DESCRIPTION).getAmount();

        y = innerY - rem.getTopsideWidth();
        x = innerX - RulesAndConstants.ROOF_LENGTH_EXTRA / 2;
        WoodMaterial mat = (WoodMaterial) materials.get(RulesAndConstants.CARPORT_REM_DESCRIPTION).getMaterial();
        sketch += "<rect x=\"" + x + "\" y=\"" + y + "\" width=\"" + materials.get(RulesAndConstants.CARPORT_REM_DESCRIPTION).getCmLengthEach() + "\" height=\"" + mat.getTopsideWidth() + "\"" + style;

        //Spær - Horizontal
        amount = materials.get(RulesAndConstants.CARPORT_RAFTER_ANGLEDROOF_BOTTOM_DESCRIPTION).getAmount();
        xSpacing = order.getLength() / (amount - 1);
        x = innerX;
        y -= rafter.getTopsideWidth();
        for (int i = 0; i < amount; i++) {
            sketch += "<rect x=\"" + x + "\" y=\"" + y + "\" width=\"4.5\" height=\"" + rafter.getTopsideWidth() + "\"" + style;
            x += xSpacing;
        }
        sketch += "</svg>";
        return sketch;
    }

    public String createSketchBirdsEyeView(Order order) {
        HashMap<String, WoodDetails> materials = order.getCarportWoodMaterials();
        final int innerX = 50;
        final int innerY = 50;
        String style = "style=\"\n"
                + "                  fill:white;\n"
                + "                  stroke-width:1;\n"
                + "                  stroke:rgb(0,0,0)\" />\n";
        //Outer Measurements
        WoodMaterial pole = (WoodMaterial) materials.get(RulesAndConstants.CARPORT_POSTS_DESCRIPTION).getMaterial();
        int outerLength = order.getLength() + RulesAndConstants.ROOF_LENGTH_EXTRA;
        int innerLength = order.getLength();
        int outerWidth = order.getWidth();
        int innerWidth = order.getWidth() - pole.getTopsideWidth() * 2;
        String sketch = "<svg width=\"" + outerCanvasW + "\" height=\"" + outerCanvasL + "\" scale>\n";
        //OuterLength
        sketch += "<line x1=\"" + RulesAndConstants.ROOF_LENGTH_EXTRA / 2 + "\" y1=\"" + 0 + "\" x2=\"" + (outerLength + RulesAndConstants.ROOF_LENGTH_EXTRA / 2) + "\" y2=\"" + 0 + "\"" + style;
        sketch += "<text x=\"" + order.getLength() / 2 + "\" y=\"15\" font-family=\"sans-serif\" font-size=\"12px\" fill=\"black\">Længde: " + outerLength + " cm</text>";
        //InnerLength
        sketch += "<line x1=\"" + innerX + "\" y1=\"" + 22 + "\" x2=\"" + outerLength + "\" y2=\"" + 22 + "\"" + style;
        sketch += "<text x=\"" + order.getLength() / 2 + "\" y=\"32\" font-family=\"sans-serif\" font-size=\"12px\" fill=\"black\">Indre længde: " + innerLength + " cm</text>";
        //OuterWidth
        sketch += "<line x1=\"" + 0 + "\" y1=\"" + (innerY) + "\" x2=\"" + 0 + "\" y2=\"" + (order.getWidth() + innerY + RulesAndConstants.ROOF_WIDTH_EXTRA) + "\"" + style;
        sketch += "<text x=\"" + 10 + "\" y=\"" + order.getWidth() / 2 + "\" writing-mode=\"tb-rl\" glyph-orientation-vertical=\"0\" font-family=\"sans-serif\" font-size=\"12px\" fill=\"black\">Bredde: " + outerWidth + " cm</text>";
        //InnerWidth
        sketch += "<line x1=\"" + 22 + "\" y1=\"" + ((innerY + RulesAndConstants.ROOF_WIDTH_EXTRA / 2) + pole.getTopsideWidth()) + "\" x2=\"" + 22 + "\" y2=\"" + ((order.getWidth() + innerY + RulesAndConstants.ROOF_WIDTH_EXTRA / 2) - pole.getTopsideWidth()) + "\"" + style;
        sketch += "<text x=\"" + 32 + "\" y=\"" + order.getWidth() / 2 + "\" writing-mode=\"tb-rl\" glyph-orientation-vertical=\"0\" font-family=\"sans-serif\" font-size=\"12px\" fill=\"black\">Indre bredde: " + innerWidth + " cm</text>";

        //Stolper
        double amount = materials.get(RulesAndConstants.CARPORT_POSTS_DESCRIPTION).getAmount();

        double xSpacing = order.getLength() / (amount / 2 - 1);
        int x = innerX;
        int x1 = innerX;
        int y = innerY + RulesAndConstants.ROOF_WIDTH_EXTRA / 2;
        for (int i = 0; i < amount; i++) {

            //Horizontal-North
            if (i < amount / 2) {
                sketch += "<--! horizontal N-->\n<rect x=\"" + x + "\" y=\"" + y + "\" width=\"9.7\" height=\"9.7\"" + style;
                x += xSpacing;
            }
            WoodMaterial mat = (WoodMaterial) materials.get(RulesAndConstants.CARPORT_POSTS_DESCRIPTION).getMaterial();
            //Horizontal-South
            int yPolePlacement = innerY + order.getWidth() + 25 - mat.getTopsideLength();
            if (i >= amount / 2) {
                sketch += "<--! horizontal S-->\n<rect x=\"" + x1 + "\"y=\"" + yPolePlacement + "\" width=\"9.7\" height=\"9.7\"" + style;
                x1 += xSpacing;
            }
        }

        //Spær
        if (order.getAngle() != 0) {
            amount = materials.get(RulesAndConstants.CARPORT_RAFTER_ANGLEDROOF_BOTTOM_DESCRIPTION).getAmount();
        } else {
            amount = materials.get(RulesAndConstants.CARPORT_RAFTER_FLATROOF_DESCRIPTON).getAmount();
        }

        xSpacing = order.getLength() / (amount - 1);
        x = innerX;
        y = innerY;
        for (int i = 0; i < amount; i++) {
            if (order.getAngle() != 0) {
                sketch += "<rect x=\"" + x + "\" y=\"" + innerY + "\" width=\"4.5\" height=\"" + materials.get(RulesAndConstants.CARPORT_RAFTER_ANGLEDROOF_BOTTOM_DESCRIPTION).getCmLengthEach() + "\"" + style;
                x += xSpacing;
            } else {
                sketch += "<rect x=\"" + x + "\" y=\"" + innerY + "\" width=\"4.5\" height=\"" + materials.get(RulesAndConstants.CARPORT_RAFTER_FLATROOF_DESCRIPTON).getCmLengthEach() + "\"" + style;
                x += xSpacing;
            }
        }
        //Remme
        amount = materials.get(RulesAndConstants.CARPORT_REM_DESCRIPTION).getAmount();
        y = innerY + RulesAndConstants.ROOF_WIDTH_EXTRA / 2;
        int ySpacing = order.getWidth() + y;
        x = innerX - RulesAndConstants.ROOF_LENGTH_EXTRA / 2;

        WoodMaterial mat = (WoodMaterial) materials.get("Remme").getMaterial();

        for (int i = 0; i < amount; i++) {

            sketch += "<rect x=\"" + x + "\" y=\"" + y + "\" width=\"" + materials.get(RulesAndConstants.CARPORT_REM_DESCRIPTION).getCmLengthEach() + "\" height=\"" + mat.getTopsideLength() + "\"" + style;
            y = ySpacing - mat.getTopsideLength();
        }
        //adding shed
        sketch += addShedBirdsEye(innerX, innerY, style, order.getShedLength(), order.getShedWidth(), order);
        sketch += "</svg>";
        return sketch;
    }

    private String addShedBirdsEye(final int innerX, final int innerY, String style, int length, int width, Order order) {
        String shed = "";
        if (length == 0 || width == 0) {
            return shed;
        }
        double wallWidth = 0.25;
        int x = innerX;
        int y = innerY + RulesAndConstants.ROOF_LENGTH_EXTRA / 2;
        //Upperleft
        if (order.getShedPlacement().equals("UL")) {
            //Length - N
            shed += "<rect x=\"" + x + "\" y=\"" + y + "\" width=\"" + length + "\" height=\"" + wallWidth + "\"" + style;
            //Length - S
            y += width;
            shed += "<rect x=\"" + x + "\" y=\"" + y + "\" width=\"" + length + "\" height=\"" + wallWidth + "\"" + style;
            //Width - W
            y -= width;
            shed += "<rect x=\"" + x + "\" y=\"" + y + "\" width=\"" + wallWidth + "\" height=\"" + width + "\"" + style;
            //Width - E
            x += length;
            shed += "<rect x=\"" + x + "\" y=\"" + y + "\" width=\"" + wallWidth + "\" height=\"" + width + "\"" + style;
            return shed;
        }
        //Upperright
        if (order.getShedPlacement().equals("UR")) {
            //Length - N
            x += order.getLength() - length;
            shed += "<rect x=\"" + x + "\" y=\"" + y + "\" width=\"" + length + "\" height=\"" + wallWidth + "\"" + style;
            //Length - S
            y += width;
            shed += "<rect x=\"" + x + "\" y=\"" + y + "\" width=\"" + length + "\" height=\"" + wallWidth + "\"" + style;
            //Width - W
            y -= width;
            shed += "<rect x=\"" + x + "\" y=\"" + y + "\" width=\"" + wallWidth + "\" height=\"" + width + "\"" + style;
            //Width - E
            x += length;
            shed += "<rect x=\"" + x + "\" y=\"" + y + "\" width=\"" + wallWidth + "\" height=\"" + width + "\"" + style;
            return shed;
        }
        //Lowerright
        if (order.getShedPlacement().equals("LR")) {
            //Length - N
            x += order.getLength() - length;
            y += order.getWidth() - width;
            shed += "<rect x=\"" + x + "\" y=\"" + y + "\" width=\"" + length + "\" height=\"" + wallWidth + "\"" + style;
            //Length - S
            y += width;
            shed += "<rect x=\"" + x + "\" y=\"" + y + "\" width=\"" + length + "\" height=\"" + wallWidth + "\"" + style;
            //Width - W
            y -= width;
            shed += "<rect x=\"" + x + "\" y=\"" + y + "\" width=\"" + wallWidth + "\" height=\"" + width + "\"" + style;
            //Width - E
            x += length;
            shed += "<rect x=\"" + x + "\" y=\"" + y + "\" width=\"" + wallWidth + "\" height=\"" + width + "\"" + style;
            return shed;
        }
        //Lowerleft
        if (order.getShedPlacement().equals("LL")) {
            //Length - N
            y += order.getWidth() - width;
            shed += "<rect x=\"" + x + "\" y=\"" + y + "\" width=\"" + length + "\" height=\"" + wallWidth + "\"" + style;
            //Length - S
            y += width;
            shed += "<rect x=\"" + x + "\" y=\"" + y + "\" width=\"" + length + "\" height=\"" + wallWidth + "\"" + style;
            //Width - W
            y -= width;
            shed += "<rect x=\"" + x + "\" y=\"" + y + "\" width=\"" + wallWidth + "\" height=\"" + width + "\"" + style;
            //Width - E
            x += length;
            shed += "<rect x=\"" + x + "\" y=\"" + y + "\" width=\"" + wallWidth + "\" height=\"" + width + "\"" + style;
            return shed;
        }
        return shed;
    }

    private String addShedSideView(final int innerX, final int innerY, Order order) {
        String shed = "";
        if (order.getShedLength() == 0) {
            return shed;
        }
        String style = "style=\"\n"
                + "                  fill:lightgray;\n"
                + "                  stroke-width:1;\n"
                + "                  stroke:rgb(0,0,0)\" />\n";

        double plankWidth = 15.0;
        int x = innerX;
        int y = innerY;
        //Upperleft
        if (order.getShedPlacement().equals("UL") || order.getShedPlacement().equals("LL")) {
            //Length - N
            for (int i = 0; i < order.getShedLength(); i += plankWidth) {
                shed += "<!--Shed--><rect x=\"" + x + "\" y=\"" + y + "\" width=\"" + plankWidth + "\" height=\"" + order.getHeight() + "\"" + style;
                x += plankWidth;
            }
            return shed;
        }
        //Upperright
        if (order.getShedPlacement().equals("UR") || order.getShedPlacement().equals("LR")) {
            //Length - N
            x += order.getLength();
            for (int i = 0; i < order.getShedLength(); i += plankWidth) {
                shed += "<!--Shed--><rect x=\"" + x + "\" y=\"" + y + "\" width=\"" + plankWidth + "\" height=\"" + order.getHeight() + "\"" + style;
                x -= plankWidth;
            }
            return shed;
        }
        return shed;
    }

    public String shedPlacement(Order order) {
        int innerX = 50;
        int innerY = 50;
        String sketch = "<svg class=\"outerCanvas\" width=\"" + 1000 + "\" height=\"" + 1000 + "\" scale>\n";

        //Inner Canvas
        sketch += "<svg class=\"innerCanvas\" width=\"" + 800 + "\" height=\"" + 800 + "\" scale>\n";
        String style = "style=\"\n"
                + "                  fill:white;\n"
                + "                  stroke-width:1;\n"
                + "                  stroke:rgb(0,0,0)\" />\n";

        sketch += "<rect x=\"" + innerX + "\" y=\"" + innerY + "\" width=\"" + order.getLength() + "\" height=\"" + order.getWidth() + "\"" + style;
        //Upper Left - NW
        sketch += "<rect x=\"" + innerX + "\" y=\"" + innerY + "\" width=\"" + order.getLength() / 2 + "\" height=\"" + order.getWidth() / 2 + "\"" + style;
        //Upper Right - NE
        sketch += "<rect x=\"" + (order.getLength() / 2 + innerX) + "\" y=\"" + innerY + "\" width=\"" + order.getLength() / 2 + "\" height=\"" + order.getWidth() / 2 + "\"" + style;
        //Lower Left - SW
        sketch += "<rect x=\"" + innerX + "\" y=\"" + (order.getWidth() / 2 + innerY) + "\" width=\"" + order.getLength() / 2 + "\" height=\"" + order.getWidth() / 2 + "\"" + style;
        //Lower Right - SE
        sketch += "<rect x=\"" + (order.getLength() / 2 + innerX) + "\" y=\"" + (order.getWidth() / 2 + innerY) + "\" width=\"" + order.getLength() / 2 + "\" height=\"" + order.getWidth() / 2 + "\"" + style;
        sketch += "</svg>";

        //Outer Canvas
        sketch += "<line x1=\"" + innerX + "\" y1=\"10\" x2=\"" + (order.getLength() + innerX) + "\" y2=\"10\"" + style;
        sketch += "<text x=\"" + order.getLength() / 2 + "\" y=\"20\" font-family=\"sans-serif\" font-size=\"12px\" fill=\"black\">Længde: " + order.getLength() + " cm</text>";
        sketch += "<line x1=\"" + 10 + "\" y1=\"" + innerY + "\" x2=\"" + 10 + "\" y2=\"" + (order.getWidth() + innerY) + "\"" + style;
        sketch += "<text x=\"" + 20 + "\" y=\"" + order.getWidth() / 2 + "\" writing-mode=\"tb-rl\" glyph-orientation-vertical=\"0\" font-family=\"sans-serif\" font-size=\"12px\" fill=\"black\">bredde: " + order.getWidth() + " cm</text>";

        sketch += "</svg>";

        return sketch;
    }
}
