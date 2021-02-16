package edu.epam.auth.controller.impl;

import edu.epam.auth.controller.Command;
import edu.epam.auth.controller.CommandResult;
import edu.epam.auth.exception.ServiceException;
import edu.epam.auth.model.Role;
import edu.epam.auth.model.UserStatus;
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

public class ChangeUserRoleStatusCommand implements Command {

    private static final Logger logger = LogManager.getLogger(ChangeUserRoleStatusCommand.class);

    private final UserService userService = UserServiceImpl.getInstance();


    @Override
    public CommandResult execute(HttpServletRequest req) throws ServletException {
        String parameterStatus = req.getParameter(ParameterConstant.NEW_STATUS).toUpperCase();
        String parameterRole = req.getParameter(ParameterConstant.NEW_ROLE).toUpperCase();

        long userId = Long.parseLong(req.getParameter(ParameterConstant.USER_ID));
        UserStatus userStatus = UserStatus.valueOf(parameterStatus);
        Role userRole = Role.valueOf(parameterRole);

        try {
            userService.changeRoleStatus(userId, userRole, userStatus);
            req.setAttribute(AttributeConstant.MESSAGE, MessageConstant.SUCCESSFULLY_CHANGE_RECORD);
            logger.info("Admin changed user: {} status: {} and role: {}", userId, userStatus, userRole);
        } catch (ServiceException e) {
            logger.error(e);
            throw new ServletException(e);
        }
        CommandResult commandResult = CommandResult.setForwardPage(PageConstant.USER_ADMINISTRATION);
        return commandResult;
    }
}
