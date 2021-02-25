package edu.epam.auth.controller.impl.admin.category;

import edu.epam.auth.constant.AttributeConstant;
import edu.epam.auth.constant.MessageConstant;
import edu.epam.auth.constant.PageConstant;
import edu.epam.auth.constant.ParameterConstant;
import edu.epam.auth.controller.Command;
import edu.epam.auth.controller.CommandResult;
import edu.epam.auth.controller.RequestContent;
import edu.epam.auth.exception.ServiceException;
import edu.epam.auth.service.CategoryService;
import edu.epam.auth.service.impl.CategoryServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;

public class AddCategoryCommand implements Command {

    private static final Logger logger = LogManager.getLogger(AddCategoryCommand.class);

    private final CategoryService categoryService = CategoryServiceImpl.getInstance();

    @Override
    public CommandResult execute(RequestContent requestContent) throws ServletException {
        String categoryName = requestContent.getRequestParameter(ParameterConstant.CATEGORY_NAME);
        String upCategoryIdParameter = requestContent.getRequestParameter(ParameterConstant.CATEGORY_UP_LEVEL_ID);
        long upCategoryId = Long.parseLong(upCategoryIdParameter);
        try {
            String result = categoryService.addCategory(categoryName, upCategoryId);
            if(result.equals(MessageConstant.SUCCESSFULLY_ADD_RECORD)) {
                requestContent.putRequestAttribute(AttributeConstant.MESSAGE, result);
            }else {
                requestContent.putRequestAttribute(AttributeConstant.ERROR, result);
            }
        } catch (ServiceException e) {
           logger.error(e);
           throw new ServletException(e);
        }
        CommandResult commandResult = CommandResult.setForwardPage(PageConstant.CATEGORY_ADMINISTRATION);
        return commandResult;
    }
}
