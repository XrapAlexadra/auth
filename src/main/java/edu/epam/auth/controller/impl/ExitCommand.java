package edu.epam.auth.controller.impl;

import edu.epam.auth.controller.Command;
import edu.epam.auth.controller.CommandResult;
import edu.epam.auth.constant.PageConstant;
import edu.epam.auth.controller.RequestContent;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ExitCommand implements Command {


    @Override
    public CommandResult execute(RequestContent requestContent) {
        requestContent.invalidateSession();
        CommandResult commandResult = CommandResult.setRedirectPage(PageConstant.INDEX_PAGE);
        return commandResult;
    }
}
