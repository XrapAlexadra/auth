package edu.epam.auth.controller.impl;

import edu.epam.auth.controller.Command;
import edu.epam.auth.controller.CommandResult;
import edu.epam.auth.service.UserService;
import edu.epam.auth.service.impl.UserServiceImpl;
import edu.epam.auth.util.PageConstant;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ExitCommand implements Command {


    @Override
    public CommandResult execute(HttpServletRequest req) {
        HttpSession session = req.getSession();
        session.removeAttribute("user");
        session.invalidate();

        CommandResult commandResult = CommandResult.setRedirectPage(PageConstant.INDEX_PAGE);
        return commandResult;
    }
}
