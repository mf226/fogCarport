/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentationLayer.Commands;

import PresentationLayer.Commands.Command;
import FunctionLayer.Exceptions.LoginException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jonab
 */
public class Registerpage extends Command {

    public Registerpage() {
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws LoginException {
        return "registerpage";
    }

}
