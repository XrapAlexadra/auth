package edu.epam.auth.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

public interface Command {

    CommandResult execute(HttpServletRequest req) throws ServletException;
}
