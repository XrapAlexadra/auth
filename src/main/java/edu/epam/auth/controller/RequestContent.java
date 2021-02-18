package edu.epam.auth.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class RequestContent {

    private final Map<String, Object> requestAttributes = new HashMap<>();
    private final Map<String, Object> sessionAttributes = new HashMap<>();
    private Map<String, String[]> requestParameters = new HashMap<>();
    private boolean invalidateSession = false;


    public RequestContent(HttpServletRequest req) {
        Enumeration<String> parameterNames = req.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String parameterName = parameterNames.nextElement();
            String[] parameter = req.getParameterValues(parameterName);
            requestParameters.put(parameterName, parameter);
        }
    }

    public RequestContent(Map<String, String[]> requestParameters) {
        this.requestParameters = requestParameters;
    }

    public String[] getRequestParameter(String parameterName) {
        String[] parameterValues = requestParameters.get(parameterName);
        return parameterValues;
    }

    public void putRequestAttribute(String attributeName, Object attribute) {
        requestAttributes.put(attributeName, attribute);
    }

    public void putSessionAttribute(String attributeName, Object attribute) {
        sessionAttributes.put(attributeName, attribute);
    }

    public void fillRequest(HttpServletRequest req) {
        for (String attributeName : requestAttributes.keySet()) {
            Object attribute = requestAttributes.get(attributeName);
            req.setAttribute(attributeName, attribute);
        }
        HttpSession session = req.getSession();
        if(invalidateSession){
            session.invalidate();
        }else {
            for (String attributeName : sessionAttributes.keySet()) {
                Object attribute = sessionAttributes.get(attributeName);
                session.setAttribute(attributeName, attribute);
            }
        }
    }

    public void invalidateSession(){
        invalidateSession = true;
    }
}
