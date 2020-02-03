package kz.zhanbolat.shop.dao;

import kz.zhanbolat.shop.entity.User;

public interface UserDao extends BaseDao<User, Integer> {
    boolean checkUserExist(User user);
}
