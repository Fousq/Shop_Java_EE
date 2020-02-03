package kz.zhanbolat.shop;

import kz.zhanbolat.shop.dao.UserDao;
import kz.zhanbolat.shop.service.impl.UserAuthenticationServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
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
        when(userDao.checkUserExist(anyString(), anyString())).thenReturn(Boolean.TRUE);

        boolean authenticated = userAuthenticationService.authenticateUser("test1", "test1");

        assertEquals(Boolean.TRUE, authenticated);
    }

    @Test
    public void testAuthenticateUserShouldReturnFalse() {
        when(userDao.checkUserExist(anyString(), anyString())).thenReturn(Boolean.FALSE);

        boolean authenticated = userAuthenticationService.authenticateUser("test1", "test2");

        assertEquals(Boolean.FALSE, authenticated);
    }
}
