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

    private String html = "<div class=\"topnav\">\n"
            + "        <form id=\"Home\" action=\"FrontController\" method=\"POST\">\n"
            + "            <input type=\"hidden\" name=\"command\" value=\"home\">\n"
            + "            <input id=\"btn\" type=\"submit\" value=\"Home\">\n"
            + "        </form>\n"
            + "     <div class=\"dropdown\">\n"
            + "     <button class=\"dropbtn\">Dropdown \n"
            + "         <i class=\"fa fa-caret-down\"></i>\n"
            + "     </button>\n"
            + "         <div class=\"dropdown-content\">\n"
            + "             <form id=\"createHouse\" action=\"FrontController\" method=\"POST\">\n"
            + "                 <input type=\"hidden\" name=\"command\" value=\"createHouse\">\n"
            + "                 <input id=\"btn\" type=\"submit\" value=\"Create House\">\n"
            + "             </form>"
            + "             </div>\n"
            + "         </div> \n"
            + "     </div>"
            + "        <form id=\"register\" action=\"FrontController\" method=\"POST\">\n"
            + "            <input type=\"hidden\" name=\"command\" value=\"registerpage\">\n"
            + "            <input id=\"btn\" type=\"submit\" value=\"Register\">\n"
            + "        </form>\n"
            + "        <form id=\"login\" action=\"FrontController\" method=\"POST\">\n"
            + "            <input type=\"hidden\" name=\"command\" value=\"loginpage\">\n"
            + "            <input id=\"btn\" type=\"submit\" value=\"Login\">\n"
            + "        </form>\n"
            + "        </div>";

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

    public String generateBOM(Order order) {
        HashMap<String, WoodDetails> materialsWood = order.getWoodMaterials();
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
        HashMap<String, MetalDetails> materialsMetal = order.getMetalMaterials();

        for (Map.Entry<String, MetalDetails> mapMetal : materialsMetal.entrySet()) {
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
        table += "<tr><td>Ialt</td><td></td><td></td><td></td><td></td><td></td><td></td><td>" + order.getTotalOrderPrice() + " kr </td></tr>";
        table += "</table>";
        return table;
    }
//
//    public String createSketchSideView(Order order) {
//        String sketch = "<svg width=\"" + order.getWidth() + "\" height=\"" + order.getHeight() * 2 + "\" scale>\n";
//        String style = "style=\"\n"
//                + "                  fill:white;\n"
//                + "                  stroke-width:1;\n"
//                + "                  stroke:rgb(0,0,0)\" />\n";
//
//        List<WoodDetails> list = order.getMaterials();
//        double amount = 0;
//        for (int i = 0; i < list.size(); i++) {
//            //Stolper
//            if (list.get(i).getUseDescription().equals("Stolper")) {
//                amount = (list.get(i).getAmount() - 2) / 2; //Stolperne fra forsiden og bagsiden fratrækkes
//                int xSpacing = 0;
//                double underground = list.get(i).getCmLengthEach() - 90;
//
//                for (int j = 0; j < amount; j++) {
//                    sketch += "<rect x=\"" + xSpacing + "\" width=\"9.7\" height=\"" + list.get(i).getCmLengthEach() + "\"" + style;
//                    sketch += "<rect x=\"" + xSpacing + "\" width=\"9.7\" height=\"" + underground + "\"" + style;
//                    xSpacing += 50;
//                }
//            }
//        }
//        sketch += "</svg>";
//        return sketch;
//    }

    public String generateShedMeasurements(int length, int width) {
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

        shed += "</select>\n"
                + "<h4>Vælg hvor dit skur skal placeres</h4>\n"
                + "         <div class=\"shedPlacements\">\n"
                + "         <div class=\"upperleft\" style=\"height:" + (width / 2) + "px; width: " + (length / 2) + "px;\">\n"
                + "                <input type=\"radio\" name=\"placement\" value=UL>"
                + "                 <label for=\"UL\">øvre venstre</label>\n"
                + "         </div>"
                + "         <div class=\"upperright\" style=\"height:" + (width / 2) + "px; width: " + (length / 2) + "px;\">\n"
                + "                <input style=\"float:right;\" type=\"radio\" name=\"placement\" value=UR checked>"
                + "                 <label style=\"float:right;\" for=\"UR\">øvre højre</label>\n"
                + "         </div>"
                + "         <div class=\"lowerleft\" style=\"margin-top:" + (width / 2 - 20) + "px; height:" + (width / 2) + "px; width: " + (length / 2) + "px;\">\n"
                + "                <input id=\"LL\" type=\"radio\" name=\"placement\" value=LL>"
                + "                 <label for=\"LL\">nedre venstre</label>\n"
                + "         </div>"
                + "         <div class=\"lowerright\" style=\"margin-top:" + (width / 2 - 20) + "px; height:" + (width / 2) + "px; width: " + (length / 2) + "px;\">\n"
                + "                <input style=\"float:right;\" id=\"LR\" type=\"radio\" name=\"placement\" value=LR>"
                + "                 <label style=\"float:right;\" for=\"LR\">nedre højre</label>\n"
                + "         </div>"
                + "       </div>\n"
                + "                <input type=\"submit\" value=\"Submit\">\n"
                + "                <input type=\"hidden\" name=\"command\" value=\"addShed\">\n"
                + "            </form>";

        return shed;
    }

    public String createRoofTypesFlat(List<WoodMaterial> roofs) {
        String roof = "<h5>Tagtype:</h5>\n"
                + "                <select name=\"roofType\">\n";

        for (int i = 0; i < roofs.size(); i++) {
            roof += "<option value=\"" + roofs.get(i).getName() + "\">" + roofs.get(i).getName() + "</option>\n";
        }

        roof += "</select>";

        return roof;
    }

    public String createRoofTypesAngled(List<WoodMaterial> roofs) {
        String roof = "<h5>Tagtype:</h5>\n"
                + "                <select name=\"roofType\">\n";

        for (int i = 0; i < roofs.size(); i++) {
            roof += "<option value=\"" + roofs.get(i).getName() + "\">" + roofs.get(i).getName() + "</option>\n";
        }

        roof += "</select>";

        return roof;
    }

    public String createSketchBirdsEyeView(Order order) {
        final int innerX = 50;
        final int innerY = 50;
        String style = "style=\"\n"
                + "                  fill:white;\n"
                + "                  stroke-width:1;\n"
                + "                  stroke:rgb(0,0,0)\" />\n";
        //Outer Measurements
        String sketch = "<svg width=\"" + 1000 + "\" height=\"" + 1000 + "\" scale>\n";
        sketch += "<line x1=\"" + innerX + "\" y1=\"" + 0 + "\" x2=\"" + (order.getLength() + innerX) + "\" y2=\"" + 0 + "\"" + style;
        sketch += "<text x=\"" + order.getLength() / 2 + "\" y=\"20\" font-family=\"sans-serif\" font-size=\"12px\" fill=\"black\">Længde: " + order.getLength() + " cm</text>";
        sketch += "<line x1=\"" + 0 + "\" y1=\"" + (innerY + RulesAndConstants.ROOF_WIDTH_EXTRA / 2) + "\" x2=\"" + 0 + "\" y2=\"" + (order.getWidth() + innerY + RulesAndConstants.ROOF_WIDTH_EXTRA / 2) + "\"" + style;
        sketch += "<text x=\"" + 20 + "\" y=\"" + order.getWidth() / 2 + "\" writing-mode=\"tb-rl\" glyph-orientation-vertical=\"0\" font-family=\"sans-serif\" font-size=\"12px\" fill=\"black\">bredde: " + order.getWidth() + " cm</text>";

        HashMap<String, WoodDetails> materials = order.getWoodMaterials();

        //Stolper
        int xSpacing = 100;
        int x = innerX;
        int x1 = innerX;
        int y = innerY + RulesAndConstants.ROOF_WIDTH_EXTRA / 2;
        double amount = materials.get(RulesAndConstants.CARPORT_POSTS_DESCRIPTION).getAmount();
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
        amount = materials.get("Spær").getAmount();
        xSpacing = 50;
        x = innerX;
        y = innerY;
        for (int i = 0; i < amount; i++) {
            sketch += "<rect x=\"" + x + "\" y=\"" + innerY + "\" width=\"4.5\" height=\"" + materials.get(RulesAndConstants.CARPORT_RAFTER_FLATROOF_DESCRIPTON).getCmLengthEach() + "\"" + style;
            x += xSpacing;
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

        // sketch = addShedToSketch(innerX, innerY, sketch, style);
        sketch += "</svg>";
        return sketch;
    }

//    private String addShedToSketch(final int innerX, final int innerY, String sketch, String style) {
//        //shed
//        int x = innerX;
//        int y = innerY + 25;
//        HashMap<String, List> shed = testShed();
//        double unitWidth = 1.5;
//        List north = shed.get("north");
//        List south = shed.get("south");
//        List east = shed.get("east");
//        List west = shed.get("west");
//        double xSpacing = unitWidth;
//        int length = 200; //length CM
//        int width = 100; //width CM
//        //northside
//        for (int j = 0; j < north.size(); j++) {
//            sketch += "<rect x=\"" + x + "\" y=\"" + y + "\" width=\"" + unitWidth + "\" height=\"" + 0.25 + "\"" + style;
//            x += xSpacing;
//        }
//        //southside
//        x = innerX;
//        y += width;
//        for (int j = 0; j < south.size(); j++) {
//            sketch += "<rect x=\"" + x + "\" y=\"" + y + "\" width=\"" + unitWidth + "\" height=\"" + 0.25 + "\"" + style;
//            x += xSpacing;
//        }
//        //eastside
//        x = innerX;
//        y = innerY + 25;
//        for (int j = 0; j < east.size(); j++) {
//            sketch += "<rect x=\"" + x + "\" y=\"" + y + "\" width=\"" + unitWidth + "\" height=\"" + 0.25 + "\"" + style;
//            y += xSpacing;
//        }
//        //westside
//        y = innerY + 25;
//        x = innerX + length;
//        for (int j = 0; j < west.size(); j++) {
//            sketch += "<rect x=\"" + x + "\" y=\"" + y + "\" width=\"" + unitWidth + "\" height=\"" + 0.25 + "\"" + style;
//            y += xSpacing;
//        }
//        return sketch;
//    }
//
////    //hardcoding shed to test sketch
//    public HashMap<String, List> testShed() {
//        HashMap<String, List> shed = new HashMap();
//        int length = 200; //length CM
//        int width = 100; //Width CM
//        double unitWidth = 1.5; //width CM
//
//        //north
//        List<WoodMaterial> listNorth = new ArrayList();
//        int tempLength = 0;
//
//        for (int i = 0; tempLength < length; i++) {
//            WoodMaterial mat = new WoodMaterial(7, "25x150", "m", 60);
//            tempLength += unitWidth;
//            listNorth.add(i, mat);
//        }
//        shed.put("north", listNorth);
//        //south
//        List<WoodMaterial> listSouth = new ArrayList();
//        tempLength = 0;
//
//        for (int i = 0; tempLength < length; i++) {
//            WoodMaterial mat = new WoodMaterial(7, "25x150", "m", 60);
//            tempLength += unitWidth;
//            listSouth.add(i, mat);
//        }
//        shed.put("south", listSouth);
//        //east
//        List<WoodMaterial> listEast = new ArrayList();
//        int tempWidth = 0;
//
//        for (int i = 0; tempWidth < width; i++) {
//            WoodMaterial mat = new WoodMaterial(7, "25x150", "m", 60);
//            tempWidth += unitWidth;
//            listEast.add(i, mat);
//        }
//        shed.put("east", listEast);
//        //west
//        //east
//        List<WoodMaterial> listWest = new ArrayList();
//        tempWidth = 0;
//
//        for (int i = 0; tempWidth < width; i++) {
//            WoodMaterial mat = new WoodMaterial(7, "25x150", "m", 60);
//            tempWidth += unitWidth;
//            listWest.add(i, mat);
//        }
//        shed.put("west", listWest);
//
//        return shed;
//    }
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
