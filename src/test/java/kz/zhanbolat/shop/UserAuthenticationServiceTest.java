package kz.zhanbolat.shop;

import kz.zhanbolat.shop.dao.UserDao;
import kz.zhanbolat.shop.entity.User;
import kz.zhanbolat.shop.service.impl.UserAuthenticationServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserAuthenticationServiceTest {
    private static final Logger logger = LogManager.getLogger(UserAuthenticationServiceTest.class);
    @InjectMocks
    private UserAuthenticationServiceImpl userAuthenticationService;
    @Mock
    private UserDao userDao;

    @Test
    public void testAuthenticateUserShouldReturnTrue() {
        when(userDao.checkUserExist(any(User.class))).thenReturn(Boolean.TRUE);

        boolean authenticated = userAuthenticationService.authenticateUser(new User());

        assertEquals(Boolean.TRUE, authenticated);
    }

    @Test
    public void testAuthenticateUserShouldReturnFalse() {
        when(userDao.checkUserExist(any(User.class))).thenReturn(Boolean.FALSE);

        boolean authenticated = userAuthenticationService.authenticateUser(new User());

        assertEquals(Boolean.FALSE, authenticated);
    }
}
