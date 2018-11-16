/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionLayer;

import FunctionLayer.User;
import java.util.ArrayList;
import java.util.List;
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

    private String headerBackground = "        <img class=\"header-background\" src=\"images/woody.jpg\">";
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
        String role = user.getRole().toString().toLowerCase();
        String userDropdown = "  <div class=\"userdropdown\">\n"
                + "             <button class=\"userdropbtn\">Logged in as " + user.getEmail() + "\n"
                + "                <i class=\"fa fa-caret-down\"></i>\n"
                + "                </button>\n"
                + "                <div class=\"userdropdown-content\">\n"
                + "                 <form action=\"FrontController\" method=\"POST\">\n"
                + "                     <input type=\"submit\" value=\""+role+"page\">\n"
                + "                     <input type=\"hidden\" name=\"command\" value=\""+role+"page\">\n"
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
        List<MaterialDetails> materials = order.getMaterials();
        String table = "<table id=\"BillOfMaterials\">\n"
                + "            <tr>\n"
                + "                <th>Vare</th>\n"
                + "                <th>Beskrivelse</th>\n"
                + "                <th>Længde</th>\n"
                + "                <th>Varenummer</th>\n"
                + "                <th>Enhed</th>\n"
                + "                <th>Pris pr. enhed</th>\n"
                + "                <th>Antal</th>\n"
                + "                <th>Samlet pris</th>\n"
                + "            </tr>\n";

        for (int i = 0; i < materials.size(); i++) {
            table += "<tr>";
            table += "<td>" + materials.get(i).getUseDescription() + "</td>";
            table += "<td>" + materials.get(i).getMaterial().getName() + "</td>";
            table += "<td>" + materials.get(i).getCmLengthEach() + "</td>";
            table += "<td>" + materials.get(i).getMaterial().getItemNumber() + "</td>";
            table += "<td>" + materials.get(i).getMaterial().getUnit() + "</td>";
            table += "<td>" + materials.get(i).getMaterial().getPrice() + "  kr </td>";
            table += "<td>" + materials.get(i).getAmount() + "</td>";
            table += "<td>" + materials.get(i).getTotalItemPrice() + "  kr </td>";
            table += "</tr>";
        }
        table += "<tr><td>Ialt</td><td></td><td></td><td></td><td></td><td></td><td></td><td>" + order.getTotalOrderPrice() + " kr </td></tr>";
        table += "</table>";
        return table;
    }

}
