package edu.epam.auth.controller;

import edu.epam.auth.constant.ParameterConstant;
import edu.epam.auth.constant.PathConstant;
import edu.epam.auth.util.MultipartUtil;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.io.FileUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

@WebServlet(name = "MultipartController", urlPatterns = {"/multipart"})
public class MultipartController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String image = req.getParameter(ParameterConstant.IMAGE);
        File file = new File(PathConstant.USER_IMAGE_PATH + image);
        if (file.exists()) {
            byte[] content = FileUtils.readFileToByteArray(file);
            resp.setContentType("image/gif");
            OutputStream outputStream = resp.getOutputStream();
            outputStream.write(content);
            outputStream.flush();
            outputStream.close();
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String[]> requestParameters;
        try {
            requestParameters = MultipartUtil.extractMultipartRequestParameters(req);
        } catch (FileUploadException e) {
            throw new ServletException(e);
        }
        RequestContent requestContent = new RequestContent(requestParameters);
        String parameterAction = requestContent.getRequestParameter(ParameterConstant.ACTION);
        Action action = Action.valueOf(parameterAction.toUpperCase());
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

}
