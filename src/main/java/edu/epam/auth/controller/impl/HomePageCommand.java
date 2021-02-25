package edu.epam.auth.controller.impl;

import edu.epam.auth.constant.AttributeConstant;
import edu.epam.auth.constant.PageConstant;
import edu.epam.auth.controller.Command;
import edu.epam.auth.controller.CommandResult;
import edu.epam.auth.controller.RequestContent;
import edu.epam.auth.exception.ServiceException;
import edu.epam.auth.model.ad.CategoryComponent;
import edu.epam.auth.service.CategoryService;
import edu.epam.auth.service.UserService;
import edu.epam.auth.service.impl.CategoryServiceImpl;
import edu.epam.auth.service.impl.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;

public class HomePageCommand  implements Command {

    private static final Logger logger = LogManager.getLogger(HomePageCommand.class);

    private final CategoryService categoryService = CategoryServiceImpl.getInstance();

    @Override
    public CommandResult execute(RequestContent requestContent) throws ServletException {

        try {
            CategoryComponent catalog = categoryService.buildCategoryTree();
            requestContent.putSessionAttribute(AttributeConstant.CATALOG, catalog);
        } catch (ServiceException e) {
            logger.error(e);
            throw new ServletException(e);
        }
        CommandResult commandResult = CommandResult.setForwardPage(PageConstant.HOME_PAGE);
        return commandResult;
    }
}
