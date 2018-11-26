package PresentationLayer;

import FunctionLayer.HTMLGenerator;
import FunctionLayer.LoginSampleException;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

abstract class Command {

    private static HashMap<String, Command> commands;
    static HTMLGenerator gen = new HTMLGenerator();

    private static void initCommands() {
        commands = new HashMap<>();
        commands.put("login", new Login());
        commands.put("register", new Register());
        commands.put("loginpage", new LoginPage());
        commands.put("registerpage", new Registerpage());
        commands.put("logout", new Logout());
        commands.put("measurementFlat", new MeasurementFlat());
        commands.put("measurementAngled", new MeasurementAngled());
        commands.put("addShed", new AddShed());
        commands.put("SelectFlat", new FlatBOM());
        commands.put("SelectAngled", new AngledBOM());

    }

    static Command from(HttpServletRequest request) {
        String commandName = request.getParameter("command");
        if (commands == null) {
            initCommands();
        }
        return commands.getOrDefault(commandName, new UnknownCommand());
    }

    abstract String execute(HttpServletRequest request, HttpServletResponse response)
            throws LoginSampleException;

}
