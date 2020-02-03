package kz.zhanbolat.shop.service;

public interface UserAuthenticationService {
    boolean authenticateUser(String username, String password);
}
