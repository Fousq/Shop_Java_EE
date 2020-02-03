package kz.zhanbolat.shop;

import kz.zhanbolat.shop.dao.impl.UserDaoImpl;
import kz.zhanbolat.shop.entity.User;
import kz.zhanbolat.shop.exception.NoUserFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.se.SeContainerInitializer;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class UserDaoTest {
    private static final Logger logger = LogManager.getLogger(UserDaoTest.class);
    private static SeContainer container;
    private UserDaoImpl userDao;

    @BeforeClass
    public static void setUp() {
        container = SeContainerInitializer.newInstance().initialize();
    }

    @Before
    public void init() {
        userDao = container.select(UserDaoImpl.class).get();
    }

    @Test
    public void findAllShouldNotReturnEmptyList() {
        List<User> users = userDao.findAll();
        logger.debug(users);
        assertNotEquals(Collections.EMPTY_LIST, users);
    }

    @Test
    public void findOneShouldNotThrowNoUserFoundException() {
        User user = userDao.findOne(1);

        assertNotNull(user);
    }

    @Test(expected = NoUserFoundException.class)
    public void findOneShouldThrowNoUserFoundException() {
        userDao.findOne(Integer.MAX_VALUE);
    }

    @Test
    public void createUserShouldNotThrowAnyException() {
        userDao.create(new User("testUser", "testPass"));
        List<User> users = userDao.findAll();
        User user = users.get(users.size() - 1);

        assertEquals("testUser", user.getUsername());
        assertEquals("testPass", user.getPassword());
    }

    @Test
    public void updateUsernameShouldNotThrowAnyException() {
        userDao.create(new User("userToBeUpdated", "testPass"));
        List<User> users = userDao.findAll();
        User user = users.get(users.size() - 1);
        user.setUsername("updatedUser");
        userDao.update(user);
        User updatedUser = userDao.findOne(user.getId());

        assertEquals("updatedUser", updatedUser.getUsername());
    }

    @Test
    public void updatePasswordShouldNotThrowAnyException() {
        userDao.create(new User("testUser", "passToBeUpdated"));
        List<User> users = userDao.findAll();
        User user = users.get(users.size() - 1);
        user.setPassword("updatedPass");
        userDao.update(user);
        User updatedUser = userDao.findOne(user.getId());

        assertEquals("updatedPass", updatedUser.getPassword());
    }

    @Test(expected = NoUserFoundException.class)
    public void deleteUserShouldThrowNoUserFoundExceptionOnFindOne() {
        userDao.create(new User("userToBeDeleted", "pass"));
        List<User> users = userDao.findAll();
        User user = users.get(users.size() - 1);
        userDao.delete(user.getId());
        userDao.findOne(user.getId());
    }

    @Test
    public void checkUserExistShouldReturnTrue() {
        boolean exist = userDao.checkUserExist(new User("user", "pass"));
        assertTrue(exist);
    }

    @Test
    public void checkUserExistShouldReturnFalse() {
        boolean exist = userDao.checkUserExist(new User("non-exist_user", "non-exist_pass"));
        assertFalse(exist);
    }

    @AfterClass
    public static void tearDown() {
        container.close();
    }
}
