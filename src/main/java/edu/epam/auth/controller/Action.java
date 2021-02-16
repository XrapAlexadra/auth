package edu.epam.auth.controller;

import edu.epam.auth.controller.Command;
import edu.epam.auth.controller.impl.*;

public enum Action {
    EXIT(new ExitCommand()),
    LOGIN(new LoginCommand()),
    REGISTRATION(new RegistrationCommand()),
    ACTIVATION(new ActivationCommand()),
    USER_ADMINISTRATION(new UserAdministrationCommand()),
    USER_CLEANING(new UserCleaningCommand()),
    CHANGE_PASSWORD(new ChangePasswordCommand()),
    CHANGE_ROLE_STATUS(new ChangeUserRoleStatusCommand()),
    CREATE_AD(new CreateAdCommand());

    private Command command;

    Action(Command command) {
        this.command = command;
    }

    public Command getCommand(){
        return this.command;
    }
}
