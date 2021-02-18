package edu.epam.auth.controller;

import edu.epam.auth.connection.ConnectionPool;
import edu.epam.auth.constant.ParameterConstant;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Controller", urlPatterns = {"/main"})
public class Controller extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String parameterAction = req.getParameter(ParameterConstant.ACTION).toUpperCase();
        Action action = Action.valueOf(parameterAction);

        RequestContent requestContent = new RequestContent(req);
        Command command = action.getCommand();
        CommandResult commandResult = command.execute(requestContent);
        requestContent.fillRequest(req);
        sendByCommandResult(commandResult, req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String parameterAction = req.getParameter(ParameterConstant.ACTION).toUpperCase();
        Action action = Action.valueOf(parameterAction);

        RequestContent requestContent = new RequestContent(req);
        Command command = action.getCommand();
        CommandResult commandResult = command.execute(requestContent);
        requestContent.fillRequest(req);
        sendByCommandResult(commandResult, req, resp);
    }

    private void sendByCommandResult
            (CommandResult commandResult, HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String page = commandResult.getPage();
        if (commandResult.isRedirect()) {
            resp.sendRedirect(req.getContextPath() + page);
        } else {
            req.getRequestDispatcher(page).forward(req, resp);
        }
    }

    @Override
    public void destroy() {
        ConnectionPool.getInstance().destroyPool();
        super.destroy();
    }

}
