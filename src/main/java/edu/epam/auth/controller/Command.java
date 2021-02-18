package edu.epam.auth.controller;

import javax.servlet.ServletException;

public interface Command {

    CommandResult execute(RequestContent requestContent) throws ServletException;
}
