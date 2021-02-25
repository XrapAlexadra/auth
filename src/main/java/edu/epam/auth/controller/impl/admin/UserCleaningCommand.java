package edu.epam.auth.controller.impl.admin;

import edu.epam.auth.controller.Command;
import edu.epam.auth.controller.CommandResult;
import edu.epam.auth.controller.RequestContent;
import edu.epam.auth.exception.ServiceException;
import edu.epam.auth.service.UserService;
import edu.epam.auth.service.impl.UserServiceImpl;
import edu.epam.auth.constant.AttributeConstant;
import edu.epam.auth.constant.MessageConstant;
import edu.epam.auth.constant.PageConstant;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;

public class UserCleaningCommand implements Command {

    private static final Logger logger = LogManager.getLogger(UserCleaningCommand.class);

    private final UserService userService = UserServiceImpl.getInstance();


    @Override
    public CommandResult execute(RequestContent requestContent) throws ServletException {
        try {
            int changedNumber = userService.deleteMoreThenDayInactive();
            String message = MessageConstant.DELETED_NUMBER + changedNumber;
            requestContent.putRequestAttribute(AttributeConstant.MESSAGE, message);
            logger.info("Admin delete more than day inactive users. Deleted number :{}", changedNumber);
        } catch (ServiceException e) {
            logger.error(e);
            throw new ServletException(e);
        }
        CommandResult commandResult = CommandResult.setForwardPage(PageConstant.USER_ADMINISTRATION);
        return commandResult;
    }
}
