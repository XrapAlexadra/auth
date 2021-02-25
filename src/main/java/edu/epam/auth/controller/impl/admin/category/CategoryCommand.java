package edu.epam.auth.controller.impl.admin.category;

import edu.epam.auth.constant.AttributeConstant;
import edu.epam.auth.constant.MessageConstant;
import edu.epam.auth.constant.PageConstant;
import edu.epam.auth.constant.ParameterConstant;
import edu.epam.auth.controller.Command;
import edu.epam.auth.controller.CommandResult;
import edu.epam.auth.controller.RequestContent;
import edu.epam.auth.exception.ServiceException;
import edu.epam.auth.model.ad.Category;
import edu.epam.auth.service.CategoryService;
import edu.epam.auth.service.impl.CategoryServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import java.util.Optional;

public class CategoryCommand implements Command {

    private static final Logger logger = LogManager.getLogger(CategoryCommand.class);

    private final CategoryService categoryService = CategoryServiceImpl.getInstance();

    @Override
    public CommandResult execute(RequestContent requestContent) throws ServletException {
        String categoryIdParameter = requestContent.getRequestParameter(ParameterConstant.CATEGORY_ID);
        long categoryId = Long.parseLong(categoryIdParameter);

        try {
            Optional<Category> category = categoryService.findById(categoryId);
            if(category.isPresent()){
                requestContent.putRequestAttribute(AttributeConstant.CATEGORY, category.get());
            }
            else {
                requestContent.putRequestAttribute(AttributeConstant.ERROR, MessageConstant.NOT_FIND_RECORD);
            }
        } catch (ServiceException e) {
            logger.error(e);
            throw new ServletException(e);
        }
        CommandResult commandResult = CommandResult.setForwardPage(PageConstant.CATEGORY_PAGE);
        return commandResult;
    }
}
