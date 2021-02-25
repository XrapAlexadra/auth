package edu.epam.auth.controller.impl.authentification;

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

public class ActivationCommand implements Command {

    private static final Logger logger = LogManager.getLogger(ActivationCommand.class);

    private final UserService userService = UserServiceImpl.getInstance();

    @Override
    public CommandResult execute(RequestContent requestContent) throws ServletException {
        String activationKey = requestContent.getRequestParameter(ParameterConstant.ACTIVATION_KEY);
        String login = requestContent.getRequestParameter(ParameterConstant.LOGIN);
        CommandResult commandResult;
            try {
                String activationResult = userService.checkActivationKey(login, activationKey);
                if (activationResult.equals(MessageConstant.SUCCESSFULLY_ACTIVATION)) {
                    commandResult = CommandResult.setForwardPage(PageConstant.LOGIN_PAGE);
                    requestContent.putRequestAttribute(AttributeConstant.MESSAGE, activationResult);
                    logger.info("Activate user: {}.", login);
                } else {
                    commandResult = CommandResult.setForwardPage(PageConstant.USER_ACTIVATION_PAGE);
                    requestContent.putRequestAttribute(AttributeConstant.MESSAGE, activationResult);
                    logger.info("Impossible activate user: {}. {}", login, activationResult);
                }
            } catch (ServiceException e) {
                logger.error(e);
                throw new ServletException(e);
            }
        return commandResult;
    }
}
