package edu.epam.auth.controller.impl;

import edu.epam.auth.controller.Command;
import edu.epam.auth.controller.CommandResult;
import edu.epam.auth.controller.RequestContent;
import edu.epam.auth.service.AdService;
import edu.epam.auth.service.impl.AdServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

public class CreateAdCommand implements Command {

    private static final Logger logger = LogManager.getLogger(CreateAdCommand.class);

    private final AdService adService = AdServiceImpl.getInstance();

    @Override
    public CommandResult execute(RequestContent requestContent) throws ServletException {
        return null;
    }

//    @Override
//    public String execute(HttpServletRequest req) throws CommandException {
//        String parameterAuthorId = req.getParameter(ParameterConstant.AUTHOR_ID);
//        String parameterType = req.getParameter(ParameterConstant.AD_TYPE).toUpperCase();
//        String parameterPrice = req.getParameter(ParameterConstant.AD_PRICE);
//        String parameterCategory = req.getParameter(ParameterConstant.AD_CATEGORY).toUpperCase();
//
//        String title = req.getParameter(ParameterConstant.AD_TITLE);
//        String text = req.getParameter(ParameterConstant.AD_TEXT);
//        long authorId = Long.parseLong(parameterAuthorId);
//        AdType adType = AdType.valueOf(parameterType);
//        BigDecimal price = new BigDecimal(parameterPrice);
//        AdCategory adCategory = AdCategory.valueOf(parameterCategory);
//
//        HttpSession session = req.getSession();
//        String page = PageConstant.CREATE_AD;
//
//        Ad newAd = adCategory.createAd();
//
//        newAd
//                .setAuthorId(authorId)
//                .setTitle(title)
//                .setText(text)
//                .setType(adType)
//                .setPrice(price);
//
//        try {
//            Optional<Ad> ad = adService.addAd(newAd);
//            if(ad.isPresent()){
//                session.setAttribute(AttributeConstant.NEW_AD, ad);
//                page = PageConstant.USER_PROFILE_PAGE;
//            }
//            else {
//                session.setAttribute(AttributeConstant.ERROR, MessageConstant.WRONG_DATE);
//                session.setAttribute(ParameterConstant.AD_TITLE, title);
//                session.setAttribute(ParameterConstant.AD_TEXT, text);
//                session.setAttribute(ParameterConstant.AD_PRICE, price);
//            }
//        } catch (ServiceException e) {
//            throw  new CommandException(e);
//        }
//        return page;
//    }
}
