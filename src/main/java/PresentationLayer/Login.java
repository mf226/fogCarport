package PresentationLayer;

import FunctionLayer.LogicFacade;
import FunctionLayer.LoginSampleException;
import FunctionLayer.User;
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
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginSampleException {
                String email = request.getParameter("email");
        String password = request.getParameter("password");
        User user;
        try {
            user = LogicFacade.getUser(email, password);
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                User user1 = LogicFacade.login(user.getEmail(), user.getPassword());
                HttpSession session = request.getSession(true);
                session.setAttribute("user", user1);
                session.setAttribute("role", user1.getRole());
                request.setAttribute("menu", gen.generateMenu(request));

            }
        } catch (Exception ex) {
            request.setAttribute("error", "Wrong username/password");
            ex.printStackTrace();
        }
        return "index";
    }

}
