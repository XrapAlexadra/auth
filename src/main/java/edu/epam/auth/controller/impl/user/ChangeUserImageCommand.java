package edu.epam.auth.controller.impl.user;

import edu.epam.auth.constant.AttributeConstant;
import edu.epam.auth.constant.PageConstant;
import edu.epam.auth.controller.Command;
import edu.epam.auth.controller.CommandResult;
import edu.epam.auth.controller.RequestContent;
import edu.epam.auth.controller.impl.user.ChangePasswordCommand;
import edu.epam.auth.exception.ServiceException;
import edu.epam.auth.model.User;
import edu.epam.auth.service.UserService;
import edu.epam.auth.service.impl.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;

import java.util.Optional;

import static edu.epam.auth.constant.ParameterConstant.IMAGE;
import static edu.epam.auth.constant.ParameterConstant.USER_ID;

public class ChangeUserImageCommand implements Command {

    private static final Logger logger = LogManager.getLogger(ChangePasswordCommand.class);

    private final UserService userService = UserServiceImpl.getInstance();

    @Override
    public CommandResult execute(RequestContent requestContent) throws ServletException{
        String image = requestContent.getRequestParameter(IMAGE);
        String parameterUserId = requestContent.getRequestParameter(USER_ID);
        long userId = Long.parseLong(parameterUserId);
        try {
            Optional<User> user = userService.changeImage(userId, image);
            user.ifPresent(value -> requestContent.putSessionAttribute(AttributeConstant.USER, value));
        } catch (ServiceException e) {
            throw new ServletException(e);
        }
        CommandResult commandResult = CommandResult.setRedirectPage(PageConstant.USER_PROFILE_PAGE);
        return commandResult;
    }
}
