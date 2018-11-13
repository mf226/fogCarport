/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionLayer;

import FunctionLayer.User;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author jonab
 */
public class HTMLGenerator {

    String html = "<div class=\"topnav\">\n"
            + "        <form id=\"Home\" action=\"FrontController\" method=\"POST\">\n"
            + "            <input type=\"hidden\" name=\"command\" value=\"home\">\n"
            + "            <input id=\"btn\" type=\"submit\" value=\"Home\">\n"
            + "        </form>\n"
            + "        <form id=\"createHouse\" action=\"FrontController\" method=\"POST\">\n"
            + "            <input type=\"hidden\" name=\"command\" value=\"createHouse\">\n"
            + "            <input id=\"btn\" type=\"submit\" value=\"Create House\">\n"
            + "        </form>\n"
            + "        <form id=\"register\" action=\"FrontController\" method=\"POST\">\n"
            + "            <input type=\"hidden\" name=\"command\" value=\"registerpage\">\n"
            + "            <input id=\"btn\" type=\"submit\" value=\"Register\">\n"
            + "        </form>\n"
            + "        <form id=\"login\" action=\"FrontController\" method=\"POST\">\n"
            + "            <input type=\"hidden\" name=\"command\" value=\"loginpage\">\n"
            + "            <input id=\"btn\" type=\"submit\" value=\"Login\">\n"
            + "        </form>\n"
            + "        </div>";

    String active = "class=\"active\"";

    String home = "<form id=\"Home\" action=\"FrontController\" method=\"POST\">\n"
            + "            <input type=\"hidden\" name=\"command\" value=\"home\">\n"
            + "            <input id=\"btn\" type=\"submit\" value=\"Home\">\n"
            + "        </form>";
    String createHouse = "<form id=\"createHouse\" action=\"FrontController\" method=\"POST\">\n"
            + "            <input type=\"hidden\" name=\"command\" value=\"createHouse\">\n"
            + "            <input id=\"btn\" type=\"submit\" value=\"Create House\">\n"
            + "        </form>";
    String register = "<form id=\"register\" action=\"FrontController\" method=\"POST\">\n"
            + "            <input type=\"hidden\" name=\"command\" value=\"registerpage\">\n"
            + "            <input id=\"btn\" type=\"submit\" value=\"Register\">\n"
            + "        </form>";
    String login = "<form id=\"login\" action=\"FrontController\" method=\"POST\">\n"
            + "            <input type=\"hidden\" name=\"command\" value=\"loginpage\">\n"
            + "            <input id=\"btn\" type=\"submit\" value=\"Login\">\n"
            + "        </form>";
    String logout = "<form id=\"logout\" action=\"FrontController\" method=\"POST\">\n"
            + "            <input type=\"hidden\" name=\"command\" value=\"logout\">\n"
            + "            <input id=\"btn\" type=\"submit\" value=\"Logout\">\n"
            + "        </form>";
    String employee = "<form id=\"employee\" action=\"FrontController\" method=\"POST\">\n"
            + "            <input type=\"hidden\" name=\"command\" value=\"employee\">\n"
            + "            <input id=\"btn\" type=\"submit\" value=\"View all orders\">\n"
            + "        </form>";

    public String generateMenu(HttpServletRequest request) {
        User user;
        if (request.getSession(false) != null) {
            try {
                user = (User) request.getSession(false).getAttribute("user");
                if ("EMPLOYEE".equals(user.getRole())) {
                    return "<!-- Logged In as employee--><div class=\"topnav\">\n"
                            + home + "\n"
                            + createHouse + "\n"
                            + employee + "\n"
                            + logout + "\n"
                            + "<h5 id=\"user\">Logged in as: " + user.getEmail() + "</h5>\n"
                            + "</div>";
                }
                if (user.getEmail() != null) {
                    return "<!-- Logged In as customer --><div class=\"topnav\">\n"
                            + home + "\n"
                            + logout + "\n"
                            + createHouse + "\n"
                            + "<h5 id=\"user\">Logged in as: " + user.getEmail() + "</h5>\n"
                            + "</div>";
                }
            } catch (NullPointerException ne) {
                ne.printStackTrace();
            }

        }
        return "<!--Not Logged In --><div class=\"topnav\">\n"
                + home + "\n"
                + createHouse + "\n"
                + login + "\n"
                + register + "\n"
                + "</div>";
    }

}
