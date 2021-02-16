package edu.epam.auth.dao.impl;

import edu.epam.auth.dao.AbstractDao;
import edu.epam.auth.dao.UserDao;
import edu.epam.auth.exception.DaoException;
import edu.epam.auth.model.Role;
import edu.epam.auth.model.User;
import edu.epam.auth.model.UserStatus;
import edu.epam.auth.service.impl.UserServiceImpl;
import edu.epam.auth.util.DaoUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl extends AbstractDao<Long, User> implements UserDao{

    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);

    private static final String INSERT =
            "INSERT INTO users (login, password, email, role, status, image, activation_key, registration_date) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
    private static final String FIND_BY_LOGIN =
            "SELECT u.user_id, u.login, u.email, u.role, u.status, u.image, u.activation_key, " +
                    "u.registration_date, u.last_login_date FROM users AS u WHERE u.login = ?;";

    private static final String FIND_PASSWORD_BY_LOGIN =
            "SELECT u.password FROM users AS u WHERE u.login=?;";
    private static final String FIND_BY_ID =
            "SELECT u.user_id, u.login, u.email, u.role, u.status, u.image, u.activation_key, " +
                    "u.registration_date, u.last_login_date FROM users AS u WHERE u.user_id=?;";
    public static final String DELETE = "DELETE FROM users WHERE users.User_id=?;";

    private static final String UPDATE_USER =
            "UPDATE users AS u SET u.activation_key=?, u.last_login_date = ?, " +
                    "u.status = ?, u.role = ?, u.image = ?, u.email= ? " +
                    "WHERE u.user_id=?;";
    private static final String FIND_ALL =
            "SELECT u.user_id, u.login, u.email, u.role, u.status, u.image, u.activation_key, " +
                    "u.registration_date, u.last_login_date FROM users AS u;";
    private static final String DELETE_MORE_THAN_DAY_INACTIVE =
            "DELETE FROM users WHERE users.registration_date<? and users.activation_key IS NOT NULL;";
    private static final String UPDATE_PASSWORD =
            "UPDATE users AS u SET u.password=? WHERE u.login=?;";

    private static final String FIND_FROM_TO =
            "SELECT u.user_id, u.login, u.email, u.role, u.status, u.image, u.activation_key, " +
                    "u.registration_date, u.last_login_date FROM users AS u " +
                    "LIMIT ? OFFSET ?;";

    private static final String FIND_ALL_COUNT = "SELECT COUNT(user_id) AS count FROM users;";

    public UserDaoImpl() {
    }

    @Override
    public long save(User user, String password) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement
                     (INSERT, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, user.getLogin());
            statement.setString(2, password);
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getRole().name());
            statement.setString(5, user.getStatus().name());
            statement.setString(6, user.getImage());
            statement.setString(7, user.getActivationKey());
            statement.setDate(8, DaoUtil.localDateToDate(user.getRegistrationDate()));
            statement.executeUpdate();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                generatedKeys.next();
                return generatedKeys.getLong(1);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public Optional<User> findByLogin(String login) throws DaoException {
        Optional<User> result = Optional.empty();
        try (PreparedStatement statement = connection.prepareStatement(FIND_BY_LOGIN)) {
            statement.setString(1, login);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    result = Optional.of(createUser(rs));
                }
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return result;
    }


    @Override
    public Optional<String> findPasswordByLogin(String login) throws DaoException {
        String password = null;
        try (PreparedStatement statement = connection.prepareStatement(FIND_PASSWORD_BY_LOGIN)) {
            statement.setString(1, login);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    password = rs.getString("password");
                }
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        Optional<String> result = Optional.ofNullable(password);
        return result;
    }

    @Override
    public Optional<User> findById(Long userId) throws DaoException {
        Optional<User> result = Optional.empty();
        try (PreparedStatement statement = connection.prepareStatement(FIND_BY_ID)) {
            statement.setLong(1, userId);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    result = Optional.of(createUser(rs));
                }
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return result;
    }

    @Override
    public void deleteById(Long userId) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(DELETE)) {
            statement.setLong(1, userId);
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new DaoException("User id: " + userId + " don't delete!");
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void update(User user) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_USER)) {
            statement.setString(1, user.getActivationKey());
            statement.setDate(2, DaoUtil.localDateToDate(user.getLastLoginDate()));
            statement.setString(3, user.getStatus().name());
            statement.setString(4, user.getRole().name());
            statement.setString(5, user.getImage());
            statement.setString(6, user.getEmail());
            statement.setLong(7, user.getId());
            int affectedRows = statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<User> findAll() throws DaoException {
        List<User> userList = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(FIND_ALL)) {
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    User user = createUser(rs);
                    userList.add(user);
                }
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return userList;
    }

    @Override
    public int deleteMoreThenInactive(LocalDate date) throws DaoException {
        int affectedRows;
        try (PreparedStatement statement = connection.prepareStatement(DELETE_MORE_THAN_DAY_INACTIVE)) {
            statement.setDate(1, DaoUtil.localDateToDate(date));
            affectedRows = statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return affectedRows;
    }

    @Override
    public void updatePassword(String login, String newPassword) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_PASSWORD)) {
            statement.setString(1, newPassword);
            statement.setString(2, login);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<User> findNumberFrom(Long from, int userNumber) throws DaoException {
        List<User> userList = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(FIND_FROM_TO)) {
            statement.setInt(1, userNumber);
            statement.setLong(2, from);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    User user = createUser(rs);
                    userList.add(user);
                }
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return userList;
    }

    @Override
    public Long getTotalNumber() throws DaoException {
        long userCount = 0;
        try (PreparedStatement statement = connection.prepareStatement(FIND_ALL_COUNT)) {
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    userCount = rs.getLong("count");
                }
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return userCount;
    }


    private User createUser(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getInt("user_id"));
        user.setLogin(rs.getString("login"));
        user.setEmail(rs.getString("email"));
        user.setRole(Role.valueOf(rs.getString("role")));
        user.setStatus(UserStatus.valueOf(rs.getString("status")));
        user.setImage(rs.getString("image"));
        user.setActivationKey(rs.getString("activation_key"));
        Date registrationDate = rs.getDate("registration_date");
        Date lastLoginDate = rs.getDate("last_login_date");

        if (registrationDate != null) {
            user.setRegistrationDate(registrationDate.toLocalDate());
        }
        if (lastLoginDate != null) {
            user.setLastLoginDate(lastLoginDate.toLocalDate());
        }
        return user;
    }
}
