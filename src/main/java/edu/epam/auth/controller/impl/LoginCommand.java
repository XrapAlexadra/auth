package edu.epam.auth.controller.impl;

import edu.epam.auth.controller.Command;
import edu.epam.auth.controller.CommandResult;
import edu.epam.auth.exception.ServiceException;
import edu.epam.auth.model.User;
import edu.epam.auth.service.UserService;
import edu.epam.auth.service.impl.UserServiceImpl;
import edu.epam.auth.util.AttributeConstant;
import edu.epam.auth.util.MessageConstant;
import edu.epam.auth.util.PageConstant;
import edu.epam.auth.util.ParameterConstant;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LoginCommand implements Command {

    private static final Logger logger = LogManager.getLogger(LoginCommand.class);

    private final UserService userService = UserServiceImpl.getInstance();

    @Override
    public CommandResult execute(HttpServletRequest req) throws ServletException {
        String login = req.getParameter(ParameterConstant.LOGIN);
        String pass = req.getParameter(ParameterConstant.PASSWORD);

        User user = new User();
        user.setLogin(login);
        String loginResult;
        try {
            loginResult = userService.login(user, pass);
        } catch (ServiceException e) {
            logger.error(e);
            throw new ServletException(e);
        }

        HttpSession session = req.getSession();
        CommandResult commandResult;
        if (loginResult.isEmpty()) {
            session.setAttribute(AttributeConstant.USER, user);
            commandResult = CommandResult.setRedirectPage(PageConstant.INDEX_PAGE);
            logger.info("User login :{}", user);
        } else {
            if (loginResult.equals(MessageConstant.WRONG_PASSWORD) || loginResult.equals(MessageConstant.NOT_FIND_USER_BY_LOGIN)) {
                req.setAttribute(ParameterConstant.LOGIN, login);
            }
            req.setAttribute(AttributeConstant.ERROR, loginResult);
            commandResult = CommandResult.setForwardPage(PageConstant.LOGIN_PAGE);
            logger.info("{}. Impossible login user : {}.", loginResult, user);
        }
        return commandResult;
    }
}
