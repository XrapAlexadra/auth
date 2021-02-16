package edu.epam.auth.service;

import edu.epam.auth.exception.ServiceException;
import edu.epam.auth.model.Role;
import edu.epam.auth.model.User;
import edu.epam.auth.model.UserStatus;

import java.util.List;
import java.util.Map;


public interface UserService {

    Map<String, String> register(User user, String password, String repeatedPassword) throws ServiceException;

    String login(User user, String pass)  throws ServiceException;

    String checkActivationKey(String login, String activationKye)throws ServiceException;

    List<User> findAll()throws ServiceException;

    int deleteMoreThenDayInactive()throws ServiceException;

    List<String> changePassword(String login, String password, String newPassword, String repeatedPassword) throws ServiceException;

    void changeRoleStatus(long userId, Role role, UserStatus userStatus) throws ServiceException;

    List<User> findUserPage(int page) throws ServiceException;

    long getPageCount() throws ServiceException;
}
