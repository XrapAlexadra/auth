package edu.epam.auth.controller;

import edu.epam.auth.controller.impl.HomePageCommand;
import edu.epam.auth.controller.impl.admin.*;
import edu.epam.auth.controller.impl.admin.category.*;
import edu.epam.auth.controller.impl.authentification.ActivationCommand;
import edu.epam.auth.controller.impl.authentification.ExitCommand;
import edu.epam.auth.controller.impl.authentification.LoginCommand;
import edu.epam.auth.controller.impl.authentification.RegistrationCommand;
import edu.epam.auth.controller.impl.user.ChangePasswordCommand;
import edu.epam.auth.controller.impl.user.ChangeUserImageCommand;
import edu.epam.auth.controller.impl.user.CreateAdCommand;

public enum Action {
    HOME(new HomePageCommand()),
    EXIT(new ExitCommand()),
    LOGIN(new LoginCommand()),
    REGISTRATION(new RegistrationCommand()),
    ACTIVATION(new ActivationCommand()),
    USER_ADMINISTRATION(new UserAdministrationCommand()),
    USER_CLEANING(new UserCleaningCommand()),
    CHANGE_PASSWORD(new ChangePasswordCommand()),
    CHANGE_ROLE_STATUS(new ChangeUserRoleStatusCommand()),
    CREATE_AD(new CreateAdCommand()),
    CHANGE_USER_IMAGE(new ChangeUserImageCommand()),
    CATEGORY_ADMINISTRATION(new CategoryAdministrationCommand()),
    ADD_CATEGORY(new AddCategoryCommand()),
    CATEGORY(new CategoryCommand()),
    CHANGE_CATEGORY(new ChangeCategoryCommand()),
    DELETE_CATEGORY(new DeleteCategoryCommand());



    private Command command;

    Action(Command command) {
        this.command = command;
    }

    public Command getCommand(){
        return this.command;
    }
}
