package edu.epam.auth.dao.impl;

import edu.epam.auth.exception.DaoException;
import edu.epam.auth.model.Role;
import edu.epam.auth.model.User;
import edu.epam.auth.model.UserStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.LocalDate;

public class UserDaoImplTest extends Assert {

    UserDaoImpl userDao = new UserDaoImpl();

    @Test
    public void testSave() throws DaoException {
        User user = new User();
        user.setLogin("first");
        user.setEmail("first@email");
        user.setRole(Role.USER);
        user.setStatus(UserStatus.ACTIVE);
        user.setRegistrationDate(LocalDate.now());
        String password = "12345";
        user.setId(userDao.save(user, password));
        userDao.deleteById(user.getId());
    }

    @Test
    public void testFindByLogin() throws DaoException {
        User user = new User();
        user.setLogin("first");
        user.setEmail("first@email");
        user.setRole(Role.USER);
        user.setStatus(UserStatus.ACTIVE);
        user.setRegistrationDate(LocalDate.now());
        String password = "12345";
        user.setId(userDao.save(user, password));

        User actual = userDao.findByLogin("first").orElse(null);
        User expected = user;

        assertEquals(actual, expected);

        userDao.deleteById(user.getId());
    }

    @Test
    public void testFindPasswordByLogin() throws DaoException {
        User user = new User();
        user.setLogin("first");
        user.setEmail("first@email");
        user.setRole(Role.USER);
        user.setStatus(UserStatus.ACTIVE);
        user.setRegistrationDate(LocalDate.now());
        String password = "12345";
        user.setId(userDao.save(user, password));

        String actual = userDao.findPasswordByLogin("first").orElse(null);
        String expected = password;

        assertEquals(actual, expected);

        userDao.deleteById(user.getId());
    }

//    @Test
//    public void testIsExistActivationKey() throws DaoException {
//        User user = new User();
//        user.setLogin("first");
//        user.setEmail("first@email");
//        user.setRole(Role.USER);
//        user.setStatus(UserStatus.ACTIVE);
//        user.setRegistrationDate(LocalDate.now());
//        user.setActivationKey("asdfghjk");
//        String password = "12345";
//
//        user.setId(userDao.save(user, password));
//
//        boolean actual = userDao.u(user.getActivationKey());
//
//        assertTrue(actual);
//
//        userDao.delete(user.getId());
//    }

    @Test
    public void testUpdate() throws DaoException {
        User user = new User();
        user.setLogin("first");
        user.setEmail("first@email");
        user.setRole(Role.USER);
        user.setStatus(UserStatus.ACTIVE);
        user.setRegistrationDate(LocalDate.now());
        user.setActivationKey("asdfghjk");
        String password = "12345";

        user.setId(userDao.save(user, password));

        user.setActivationKey(null);
        user.setStatus(UserStatus.INACTIVE);

        userDao.update(user);

        User actual = userDao.findByLogin(user.getLogin()).orElse(null);
        User expected = user;

        assertEquals(actual, expected);
    }
}