package edu.epam.auth.controller.impl.authentification;

import edu.epam.auth.controller.Command;
import edu.epam.auth.controller.CommandResult;
import edu.epam.auth.controller.RequestContent;
import edu.epam.auth.exception.ServiceException;
import edu.epam.auth.model.User;
import edu.epam.auth.service.UserService;
import edu.epam.auth.util.MailSender;
import edu.epam.auth.service.impl.UserServiceImpl;
import edu.epam.auth.constant.AttributeConstant;
import edu.epam.auth.constant.PageConstant;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import static edu.epam.auth.constant.ParameterConstant.*;
import javax.servlet.ServletException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RegistrationCommand implements Command {

    private static final Logger logger = LogManager.getLogger(RegistrationCommand.class);

    private final MailSender mailSender = MailSender.getInstance();
    private final UserService userService = UserServiceImpl.getInstance();

    @Override
    public CommandResult execute(RequestContent requestContent) throws ServletException {

        String login = requestContent.getRequestParameter(LOGIN);
        String password = requestContent.getRequestParameter(PASSWORD);
        String passwordRepeat =requestContent.getRequestParameter(REPEAT_PASSWORD);
        String email = requestContent.getRequestParameter(EMAIL);
        String image = requestContent.getRequestParameter(IMAGE);

        CommandResult commandResult;
        Map<String, String> registerUserResult;
            User user = new User();
            user.setLogin(login);
            user.setEmail(email);
            user.setImage(image);
            try {
                registerUserResult = userService.register(user, password, passwordRepeat);
                if (registerUserResult.isEmpty()) {
                    mailSender.sendActivationMail(user);
                    logger.info("User {} registered and sent mail to user email", user);
                    commandResult = CommandResult.setRedirectPage(PageConstant.USER_ACTIVATION_PAGE);
                } else {
                    logger.info("Impossible register user with login: {}. This login already exist.", login);
                    if(!registerUserResult.containsKey(login)){
                        requestContent.putRequestAttribute(LOGIN, login);
                    }
                    if(!registerUserResult.containsKey(email)){
                        requestContent.putRequestAttribute(EMAIL, email);
                    }
                    if(!registerUserResult.containsKey(password)){
                        requestContent.putRequestAttribute(PASSWORD, password);
                    }
                    List<String> errorList = new ArrayList<>(registerUserResult.values());
                    requestContent.putRequestAttribute(AttributeConstant.ERRORS, errorList);
                    commandResult = CommandResult.setForwardPage(PageConstant.AUTH_PAGE);
                }
            } catch (ServiceException e) {
                logger.error(e);
                throw new ServletException(e);
            }
        return commandResult;
    }
}
