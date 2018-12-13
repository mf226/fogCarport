package PresentationLayer.Commands;

import FunctionLayer.Exceptions.LoginSampleException;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class Command {

    private static HashMap<String, Command> commands;

    private static void initCommands() {
        commands = new HashMap<>();
        commands.put("addShed", new AddShed());
        commands.put("Adminpage", new Adminpage());
        commands.put("updateOrder", new EditOrder());
        commands.put("createOrder", new CreateOrder());
        commands.put("Customerpage", new Customerpage());
        commands.put("Employeepage", new Employeepage());
        commands.put("login", new Login());
        commands.put("loginpage", new LoginPage());
        commands.put("logout", new Logout());
        commands.put("measurementAngled", new MeasurementAngled());
        commands.put("measurementFlat", new MeasurementFlat());
        commands.put("register", new Register());
        commands.put("registerpage", new Registerpage());
        commands.put("reviewOrder", new ReviewOrder());
        commands.put("customerReview", new UserReviewOrder());
        commands.put("SelectFlat", new FlatBOM());
        commands.put("SelectAngled", new AngledBOM());

    }

    public static Command from(HttpServletRequest request) {
        String commandName = request.getParameter("command");
        if (commands == null) {
            initCommands();
        }
        return commands.getOrDefault(commandName, new UnknownCommand());
    }

    public abstract String execute(HttpServletRequest request, HttpServletResponse response)
            throws LoginSampleException;

}
