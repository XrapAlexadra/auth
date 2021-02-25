package edu.epam.auth.controller;

import edu.epam.auth.exception.ServiceException;

import javax.servlet.ServletException;

public interface Command {

    CommandResult execute(RequestContent requestContent) throws ServletException;
}
