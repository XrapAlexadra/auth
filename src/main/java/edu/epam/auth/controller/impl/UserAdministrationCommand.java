package edu.epam.auth.controller.impl;

import edu.epam.auth.controller.Command;
import edu.epam.auth.controller.CommandResult;
import edu.epam.auth.controller.RequestContent;
import edu.epam.auth.exception.ServiceException;
import edu.epam.auth.model.User;
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

public class UserAdministrationCommand implements Command {

    private static final Logger logger = LogManager.getLogger(RegistrationCommand.class);

    private final UserService userService = UserServiceImpl.getInstance();


    @Override
    public CommandResult execute(RequestContent requestContent) throws ServletException{
        String parameterPage = requestContent.getRequestParameter(ParameterConstant.PAGE)[0];
        int page = 1;
        if(parameterPage != null){
            page = Integer.parseInt(parameterPage);
        }

        try {
            List<User> userPage = userService.findUserPage(page);
            if(userPage.isEmpty()){
                requestContent.putRequestAttribute(AttributeConstant.MESSAGE, MessageConstant.USERS_NOT_FOUND);
            }
            else {
                long pageCount = userService.getPageCount();
                requestContent.putRequestAttribute(AttributeConstant.USER_PAGE, userPage);
                requestContent.putRequestAttribute(AttributeConstant.PAGE, page);
                requestContent.putRequestAttribute(AttributeConstant.PAGE_COUNT, pageCount);
            }
        } catch (ServiceException e) {
            logger.error(e);
            throw new ServletException(e);
        }
        CommandResult commandResult = CommandResult.setForwardPage(PageConstant.USER_ADMINISTRATION_PAGE);
        return commandResult;
    }
}
