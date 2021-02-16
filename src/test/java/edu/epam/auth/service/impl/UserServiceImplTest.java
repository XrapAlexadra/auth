package edu.epam.auth.service.impl;

import org.testng.Assert;
import org.testng.annotations.Test;

import static org.mockito.ArgumentMatchers.any;

public class UserServiceImplTest extends Assert {

//    UserDao userDao = Mockito.mock(UserDao.class);
//
//    @Mock
//    UserService userService = UserServiceImpl.getInstance();
//
//    @BeforeMethod
//    public void init() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    public void testRegister() throws DaoException, ServiceException {
//        User user = new User();
//        user.setLogin("first");
//        user.setEmail("first@email");
//        String password = "12345";
//
//        Mockito.when(userDao.findByLogin("first")).thenReturn(Optional.of(user));
//        Mockito.when(userDao.save(user, password)).thenReturn(1l);
//
//        userService.register(user, password);
//
//        System.out.println(user);
//    }

    @Test
    public void testDelete() {
    }

    @Test
    public void testFindById() {
    }

    @Test
    public void testCheckActivationKey() {
    }

    @Test
    public void testLogin() {
    }
}