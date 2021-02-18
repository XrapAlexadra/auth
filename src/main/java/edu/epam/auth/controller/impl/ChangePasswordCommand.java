package edu.epam.auth.controller.impl;

import edu.epam.auth.controller.Command;
import edu.epam.auth.controller.CommandResult;
import edu.epam.auth.controller.RequestContent;
import edu.epam.auth.exception.ServiceException;
import edu.epam.auth.service.UserService;
import edu.epam.auth.service.impl.UserServiceImpl;
import edu.epam.auth.constant.AttributeConstant;
import edu.epam.auth.constant.MessageConstant;
import edu.epam.auth.constant.PageConstant;
import edu.epam.auth.constant.ParameterConstant;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ChangePasswordCommand implements Command {

    private static final Logger logger = LogManager.getLogger(ChangePasswordCommand.class);

    private final UserService userService = UserServiceImpl.getInstance();

    @Override
    public CommandResult execute(RequestContent requestContent) throws ServletException {
        String password = requestContent.getRequestParameter(ParameterConstant.PASSWORD)[0];
        String newPassword = requestContent.getRequestParameter(ParameterConstant.NEW_PASSWORD)[0];
        String repeatedPassword = requestContent.getRequestParameter(ParameterConstant.REPEAT_PASSWORD)[0];
        String login = requestContent.getRequestParameter(ParameterConstant.LOGIN)[0];

        List<String> changePasswordResult;
        try {
            changePasswordResult = userService.changePassword(login, password, newPassword, repeatedPassword);
            if (changePasswordResult.isEmpty()) {
                requestContent.putRequestAttribute(AttributeConstant.MESSAGE, MessageConstant.SUCCESSFULLY_CHANGE_PASSWORD);
                logger.info("User :{} changed password.", login);
            } else {
                requestContent.putRequestAttribute(AttributeConstant.ERRORS, changePasswordResult);
                logger.info("Impossible change user: {} password. {}", login, changePasswordResult);
            }
        } catch (ServiceException e) {
            throw new ServletException(e);
        }
        CommandResult commandResult = CommandResult.setForwardPage(PageConstant.USER_PROFILE_PAGE);
        return commandResult;
    }
}
