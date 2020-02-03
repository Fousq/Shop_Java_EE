package kz.zhanbolat.shop.service.impl;

import kz.zhanbolat.shop.dao.UserDao;
import kz.zhanbolat.shop.entity.User;
import kz.zhanbolat.shop.service.UserAuthenticationService;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class UserAuthenticationServiceImpl implements UserAuthenticationService {
    @Inject
    private UserDao userDao;

    @Override
    public boolean authenticateUser(User user) {
        return userDao.checkUserExist(user);
    }
}
