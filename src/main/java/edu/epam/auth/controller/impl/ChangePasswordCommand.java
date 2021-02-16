package edu.epam.auth.controller.impl;

import edu.epam.auth.controller.Command;
import edu.epam.auth.controller.CommandResult;
import edu.epam.auth.exception.ServiceException;
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
import java.util.List;

public class ChangePasswordCommand implements Command {

    private static final Logger logger = LogManager.getLogger(ChangePasswordCommand.class);

    private final UserService userService = UserServiceImpl.getInstance();

    @Override
    public CommandResult execute(HttpServletRequest req) throws ServletException {
        String password = req.getParameter(ParameterConstant.PASSWORD);
        String newPassword = req.getParameter(ParameterConstant.NEW_PASSWORD);
        String repeatedPassword = req.getParameter(ParameterConstant.REPEAT_PASSWORD);
        String login = req.getParameter(ParameterConstant.LOGIN);

        List<String> changePasswordResult;
        try {
            changePasswordResult = userService.changePassword(login, password, newPassword, repeatedPassword);
            if (changePasswordResult.isEmpty()) {
                req.setAttribute(AttributeConstant.MESSAGE, MessageConstant.SUCCESSFULLY_CHANGE_PASSWORD);
                logger.info("User :{} changed password.", login);
            } else {
                req.setAttribute(AttributeConstant.ERRORS, changePasswordResult);
                logger.info("Impossible change user: {} password. {}", login, changePasswordResult);
            }
        } catch (ServiceException e) {
            throw new ServletException(e);
        }
        CommandResult commandResult = CommandResult.setForwardPage(PageConstant.USER_PROFILE_PAGE);
        return commandResult;
    }
}
