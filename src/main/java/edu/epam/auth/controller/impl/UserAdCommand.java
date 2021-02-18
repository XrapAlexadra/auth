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

public class UserAdCommand implements Command {

    private static final Logger logger = LogManager.getLogger(UserAdCommand.class);

    private final AdService adService = AdServiceImpl.getInstance();

    @Override
    public CommandResult execute(RequestContent requestContent) throws ServletException {
        return null;
    }
//    @Override
//    public String execute(HttpServletRequest req) throws CommandException {
//
//        return null;
//    }
}
