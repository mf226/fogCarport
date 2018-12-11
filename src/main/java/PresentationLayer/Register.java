package PresentationLayer;

import FunctionLayer.LogicFacade;
import FunctionLayer.Exceptions.LoginSampleException;
import FunctionLayer.Entity.Role;
import FunctionLayer.Entity.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Register extends Command {

    @Override
    String execute( HttpServletRequest request, HttpServletResponse response ) throws LoginSampleException {
        String email = request.getParameter( "email" );
        String password = request.getParameter( "password" );
        String password1 = request.getParameter( "password1" );
        
        if ( password.equals( password1 ) ) {
            User user = LogicFacade.createUser( email, password, Role.CUSTOMER );
            HttpSession session = request.getSession();
            session.setAttribute( "user", user );
            session.setAttribute( "role", user.getRole() );
            return "index";
        } else {
            throw new LoginSampleException( "the two passwords did not match" );
        }
    }

}
