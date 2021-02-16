package edu.epam.auth.controller;

public class CommandResult {

    private String page;
    private boolean isRedirect;

    public CommandResult(String page, boolean isRedirect) {
        this.page = page;
        this.isRedirect = isRedirect;
    }

    public static CommandResult setRedirectPage(String page){
        CommandResult commandResult = new CommandResult(page, true);
        return commandResult;
    }

    public static CommandResult setForwardPage(String page){
        CommandResult commandResult = new CommandResult(page, false);
        return commandResult;
    }

    public String getPage() {
        return page;
    }

    public boolean isRedirect() {
        return isRedirect;
    }
}
