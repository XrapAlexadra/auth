package edu.epam.auth.service.impl;

import edu.epam.auth.dao.EntityTransaction;
import edu.epam.auth.exception.DaoException;
import edu.epam.auth.exception.EncodeServiceException;
import edu.epam.auth.exception.ServiceException;
import edu.epam.auth.model.Role;
import edu.epam.auth.model.User;
import edu.epam.auth.model.UserStatus;
import edu.epam.auth.dao.impl.UserDaoImpl;
import edu.epam.auth.service.UserService;
import edu.epam.auth.util.PasswordEncoder;
import edu.epam.auth.constant.PathConstant;
import edu.epam.auth.validator.UserValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static edu.epam.auth.constant.MessageConstant.*;

import java.io.File;
import java.time.LocalDate;
import java.util.*;

public class UserServiceImpl implements UserService {

    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);

    public static final int USER_PAGE_COUNT = 2;

    private static final UserServiceImpl instance = new UserServiceImpl();

    private final PasswordEncoder passwordEncoder = PasswordEncoder.getInstance();

    private UserServiceImpl() {
    }

    public static UserServiceImpl getInstance() {
        return instance;
    }

    @Override
    public Map<String, String> register(User user, String password, String repeatedPassword) throws ServiceException {
        Map<String, String> result;
        UserDaoImpl userDao = new UserDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.begin(userDao);
        try {
            Optional<User> userFromBd = userDao.findByLogin(user.getLogin());
            if (userFromBd.isPresent()) {
                result = new HashMap<>();
                result.put(user.getLogin(), REPEATED_LOGIN);
                logger.info("Impossible register user with login: {}. This login already exist.", user.getLogin());
            } else {
                result = UserValidator.isValid(user, password, repeatedPassword);
                if (result.isEmpty()) {
                    password = passwordEncoder.encodePassword(password);
                    setUserDefaultFields(user);
                    long userId = userDao.save(user, password);
                    user.setId(userId);
                    logger.info("Save user {} in repository.", user);
                } else {
                    String imageName = user.getImage();
                    File userImage = new File(PathConstant.USER_IMAGE_PATH + imageName);
                    userImage.delete();
                    logger.info("Impossible register. User: {} has invalid data.", user.getLogin());
                }
            }
            transaction.commit();
        } catch (DaoException | EncodeServiceException e) {
            logger.error(e);
            transaction.rollback();
            throw new ServiceException(e);
        } finally {
            transaction.end();
        }
        return result;
    }


    @Override
    public String checkActivationKey(String login, String activationKye) throws ServiceException {
        String result = INVALID_ACTIVATION_LINK;
        UserDaoImpl userDao = new UserDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.begin(userDao);
        try {
            if (UserValidator.isValidLogin(login) && activationKye != null) {
                User user = userDao.findByLogin(login).orElse(null);
                if (user != null) {
                    if (activationKye.equals(user.getActivationKey())) {
                        user.setActivationKey(null);
                        user.setStatus(UserStatus.ACTIVE);
                        userDao.update(user);
                        result = SUCCESSFULLY_ACTIVATION;
                    }
                } else {
                    result = INVALID_ACTIVATION_LINK_LOGIN;
                }
            }
            transaction.commit();
        } catch (DaoException e) {
            transaction.rollback();
            logger.error(e);
            throw new ServiceException(e);
        } finally {
            transaction.end();
        }
        return result;
    }

    @Override
    public List<User> findAll() throws ServiceException {
        UserDaoImpl userDao = new UserDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.begin(userDao);
        List<User> userList;
        try {
            userList = userDao.findAll();
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        } finally {
            transaction.end();
        }
        return userList;
    }

    @Override
    public int deleteMoreThenDayInactive() throws ServiceException {
        LocalDate today = LocalDate.now();
        LocalDate yesterday = today.minusDays(1);
        int deletedNumber;
        UserDaoImpl userDao = new UserDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.begin(userDao);
        try {
            deletedNumber = userDao.deleteMoreThenInactive(yesterday);
            transaction.commit();
        } catch (DaoException e) {
            transaction.rollback();
            logger.error(e);
            throw new ServiceException(e);
        } finally {
            transaction.end();
        }
        return deletedNumber;
    }

    @Override
    public List<String> changePassword(String login, String password, String newPassword, String repeatedPassword) throws ServiceException {
        List<String> result = new ArrayList<>();
        UserDaoImpl userDao = new UserDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.begin(userDao);
        try {
            if (UserValidator.isValidPassword(newPassword)) {
                if (!newPassword.equals(repeatedPassword)) {
                    result.add(WRONG_REPEAT_PASSWORD);
                }
            } else {
                result.add(INVALID_NEW_PASSWORD);
            }
            Optional<String> passwordFromBD = userDao.findPasswordByLogin(login);
            if (passwordFromBD.isPresent()) {
                if (password == null || !passwordEncoder.check(password, passwordFromBD.get())) {
                    result.add(WRONG_PASSWORD);
                }
            } else {
                result.add(NOT_FIND_USER_BY_LOGIN);
            }
            if (result.isEmpty()) {
                String encodedPassword = passwordEncoder.encodePassword(newPassword);
                userDao.updatePassword(login, encodedPassword);
                logger.info("User :{} changed password.", login);
            } else {
                logger.info("Impossible change user: {} password. {}", login, result);
            }
            transaction.commit();
        } catch (EncodeServiceException | DaoException e) {
            transaction.rollback();
            logger.error(e);
            throw new ServiceException(e);
        } finally {
            transaction.end();
        }
        return result;
    }

    @Override
    public void changeRoleStatus(long userId, Role role, UserStatus userStatus) throws ServiceException {
        UserDaoImpl userDao = new UserDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.begin(userDao);
        try {
            Optional<User> userFromBd = userDao.findById(userId);
            if (userFromBd.isPresent()) {
                User user = userFromBd.get();
                user.setStatus(userStatus);
                user.setRole(role);
                userDao.update(user);
                logger.info("Admin changed user: {} status: {} and role: {}", userId, userStatus, role);
            }
            transaction.commit();
        } catch (DaoException e) {
            transaction.rollback();
            logger.error(e);
            throw new ServiceException(e);
        } finally {
            transaction.end();
        }
    }

    @Override
    public List<User> findUserPage(int page) throws ServiceException {
        UserDaoImpl userDao = new UserDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.begin(userDao);
        List<User> userPage = new ArrayList<>();
        try {
            long pageCount = getPageCountFromBd(userDao);
            if (pageCount >= page) {
                long from = (page - 1) * USER_PAGE_COUNT;
                userPage = userDao.findNumberFrom(from, USER_PAGE_COUNT);
            }
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        } finally {
            transaction.end();
        }
        return userPage;
    }

    @Override
    public long getPageCount() throws ServiceException {
        long pageCount;
        UserDaoImpl userDao = new UserDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.begin(userDao);
        try {
            pageCount = getPageCountFromBd(userDao);
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
        finally {
            transaction.end();
        }
        return pageCount;
    }

    //
//    @Override
//    public boolean delete(long id) {
//        boolean result = false;
//        Optional<User> userInRepository = userDao.findById(id);
//        if(userInRepository.isPresent()){
//            result = userDao.delete(id);
//            logger.info("Delete user {} from repository.", userInRepository);
//        }
//        else {
//            logger.info("There's not user with id {} in the repository.", id);
//        }
//        return result;
//    }
//
    @Override
    public String login(User user, String password) throws ServiceException {
        String result = "";
        String login = user.getLogin();
        if (UserValidator.isValidLogin(login)) {
            UserDaoImpl userDao = new UserDaoImpl();
            EntityTransaction transaction = new EntityTransaction();
            transaction.begin(userDao);
            try {
                Optional<String> passwordFromBD = userDao.findPasswordByLogin(login);
                if (passwordFromBD.isPresent()) {
                    if (password != null && passwordEncoder.check(password, passwordFromBD.get())) {
                        Optional<User> userFromBD = userDao.findByLogin(login);
                        userFromBD.ifPresent(o -> userMapping(user, o));
                        UserStatus userStatus = user.getStatus();
                        switch (userStatus) {
                            case ACTIVE: {
                                user.setLastLoginDate(LocalDate.now());
                                userDao.update(user);
                                logger.info("{} login.", user);
                                break;
                            }
                            case INACTIVE: {
                                result = USER_STATUS_INACTIVE;
                                logger.info("Impossible login user : {}. Status INACTIVE.", user);
                                break;
                            }
                            case BLOCK: {
                                result = USER_STATUS_BLOCK;
                                logger.info("Impossible login user : {}. Status BLOCK.", user);
                                break;
                            }
                            case DELETE: {
                                result = USER_STATUS_DELETE;
                                logger.info("Impossible login user : {}. Status DELETE.", user);
                            }
                        }
                    } else {
                        result = WRONG_PASSWORD;
                        logger.info("{} User's password does mismatch.", login);
                    }
                } else {
                    result = NOT_FIND_USER_BY_LOGIN;
                    logger.info("There's not user with login {} in the repository.", login);
                }
                transaction.commit();
            } catch (EncodeServiceException | DaoException e) {
                transaction.rollback();
                logger.error(e);
                throw new ServiceException(e);
            }finally {
                transaction.end();
            }
        } else {
            result = INVALID_LOGIN;
        }
        return result;
    }


    private long getPageCountFromBd(UserDaoImpl userDao) throws DaoException {
        long totalNumber = userDao.getTotalNumber();
        long pageCount = (totalNumber / USER_PAGE_COUNT);
        pageCount = totalNumber % USER_PAGE_COUNT == 0 ? pageCount : pageCount + 1;

        return pageCount;
    }

    private void userMapping(User user, User userFromBD) {
        user.setId(userFromBD.getId())
                .setEmail(userFromBD.getEmail())
                .setRole(userFromBD.getRole())
                .setStatus(userFromBD.getStatus())
                .setImage(userFromBD.getImage())
                .setLastLoginDate(userFromBD.getLastLoginDate())
                .setRegistrationDate(userFromBD.getRegistrationDate());
    }

    private void setUserDefaultFields(User user) {
        String activationKey = UUID.randomUUID().toString();
        user.setActivationKey(activationKey);
        user.setRegistrationDate(LocalDate.now());
        user.setRole(Role.USER);
        user.setStatus(UserStatus.INACTIVE);
    }

}
