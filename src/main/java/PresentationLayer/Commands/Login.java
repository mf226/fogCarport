package PresentationLayer.Commands;

import PresentationLayer.Commands.Command;
import FunctionLayer.LogicFacade;
import FunctionLayer.Exceptions.DBException;
import FunctionLayer.Entity.User;
import PresentationLayer.HTMLGenerator;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * The purpose of Login is to...
 *
 * @author kasper
 */
public class Login extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws DBException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        User user;
//        try {
        user = LogicFacade.login(email, password);
        HttpSession session = request.getSession(true);
        session.setAttribute("user", user);
        session.setAttribute("role", user.getRole());
        request.setAttribute("menu", HTMLGenerator.generateMenu(request));

//        } catch (Exception ex) {
////            request.setAttribute("error", "Wrong username/password");
//            ex.printStackTrace();
//        }
        return "index";
    }

}
