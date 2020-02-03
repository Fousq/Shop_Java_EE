package kz.zhanbolat.shop.service;

import kz.zhanbolat.shop.entity.User;

public interface UserAuthenticationService {
    boolean authenticateUser(User user);
}
