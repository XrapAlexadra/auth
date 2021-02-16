package edu.epam.auth.controller.impl;

import edu.epam.auth.controller.Command;
import edu.epam.auth.controller.CommandResult;
import edu.epam.auth.exception.ServiceException;
import edu.epam.auth.service.UserService;
import edu.epam.auth.service.impl.UserServiceImpl;
import edu.epam.auth.util.AttributeConstant;
import edu.epam.auth.util.MessageConstant;
import edu.epam.auth.util.PageConstant;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class UserCleaningCommand implements Command {

    private static final Logger logger = LogManager.getLogger(UserCleaningCommand.class);

    private final UserService userService = UserServiceImpl.getInstance();


    @Override
    public CommandResult execute(HttpServletRequest req) throws ServletException {
        try {
            int changedNumber = userService.deleteMoreThenDayInactive();
            String message = MessageConstant.DELETED_NUMBER + changedNumber;
            req.setAttribute(AttributeConstant.MESSAGE, message);
            logger.info("Admin delete more than day inactive users. Deleted number :{}", changedNumber);
        } catch (ServiceException e) {
            logger.error(e);
            throw new ServletException(e);
        }
        CommandResult commandResult = CommandResult.setForwardPage( PageConstant.USER_ADMINISTRATION);
        return commandResult;
    }
}
