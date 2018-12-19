/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentationLayer.Commands;

import PresentationLayer.Commands.Command;
import FunctionLayer.Exceptions.DBException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jonab
 */
public class LoginPage extends Command {

    public LoginPage() {
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws DBException {
        return "loginpage";
    }
    
}
