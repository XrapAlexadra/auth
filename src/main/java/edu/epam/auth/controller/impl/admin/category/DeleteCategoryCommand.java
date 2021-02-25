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

import javax.mail.MessageAware;
import javax.servlet.ServletException;

public class DeleteCategoryCommand implements Command {

    private static final Logger logger = LogManager.getLogger(DeleteCategoryCommand.class);

    private final CategoryService categoryService = CategoryServiceImpl.getInstance();

    @Override
    public CommandResult execute(RequestContent requestContent) throws ServletException {
        String categoryIdParameter = requestContent.getRequestParameter(ParameterConstant.CATEGORY_ID);
        long categoryId = Long.parseLong(categoryIdParameter);
        String result;
        try {
            result = categoryService.deleteById(categoryId);
            if(result.equals(MessageConstant.SUCCESSFULLY_DELETE_RECORD)){
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
