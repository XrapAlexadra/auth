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

public class ActivationCommand implements Command {

    private static final Logger logger = LogManager.getLogger(ActivationCommand.class);

    private final UserService userService = UserServiceImpl.getInstance();

    @Override
    public CommandResult execute(HttpServletRequest req) throws ServletException {
        String activationKey = req.getParameter(ParameterConstant.ACTIVATION_KEY);
        String login = req.getParameter(ParameterConstant.LOGIN);
        CommandResult commandResult;
            try {
                String activationResult = userService.checkActivationKey(login, activationKey);
                if (activationResult.equals(MessageConstant.SUCCESSFULLY_ACTIVATION)) {
                    commandResult = CommandResult.setForwardPage(PageConstant.LOGIN_PAGE);
                    req.setAttribute(AttributeConstant.MESSAGE, activationResult);
                    logger.info("Activate user: {}.", login);
                } else {
                    commandResult = CommandResult.setForwardPage(PageConstant.USER_ACTIVATION_PAGE);
                    req.setAttribute(AttributeConstant.MESSAGE, activationResult);
                    logger.info("Impossible activate user: {}. {}", login, activationResult);
                }
            } catch (ServiceException e) {
                logger.error(e);
                throw new ServletException(e);
            }
        return commandResult;
    }
}
