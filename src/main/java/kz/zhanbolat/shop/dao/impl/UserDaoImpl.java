package kz.zhanbolat.shop.dao.impl;

import com.ibatis.sqlmap.client.SqlMapClient;
import kz.zhanbolat.shop.dao.UserDao;
import kz.zhanbolat.shop.entity.User;
import kz.zhanbolat.shop.exception.DaoException;
import kz.zhanbolat.shop.exception.NoUserFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.sql.SQLException;
import java.util.List;

@Stateless
public class UserDaoImpl implements UserDao {
    private static final Logger logger = LogManager.getLogger(UserDaoImpl.class);
    @Inject
    private SqlMapClient sqlMapClient;

    @Override
    public List<User> findAll() {
        try {
            return sqlMapClient.queryForList("get_all_user");
        } catch (SQLException e) {
            logger.error("Got exception on select query: ", e);
            throw new DaoException(e);
        }
    }

    @Override
    public User findOne(Integer id) {
        try {
            User user = (User) sqlMapClient.queryForObject("get_user_by_id", id);
            if (user == null) {
                throw new NoUserFoundException("User with such id doesn't exist");
            }
            return user;
        } catch (SQLException e) {
            logger.error("Got exception on select query: ", e);
            throw new DaoException(e);
        }
    }

    @Override
    public void create(User entity) {
        try {
            sqlMapClient.insert("insert_user", entity);
        } catch (SQLException e) {
            logger.error("Got exception on insert query: ", e);
            throw new DaoException(e);
        }
    }

    @Override
    public void update(User entity) {
        try {
            sqlMapClient.update("update_user", entity);
        } catch (SQLException e) {
            logger.error("Got exception on update query: ", e);
            throw new DaoException(e);
        }
    }

    @Override
    public void delete(Integer id) {
        try {
            sqlMapClient.delete("delete_user_by_id", id);
        } catch (SQLException e) {
            logger.error("Got exception on delete query: ", e);
            throw new DaoException(e);
        }
    }

    @Override
    public boolean checkUserExist(User user) {
        int count = 0;
        try {
            count = (int) sqlMapClient.queryForObject("count_user_of_username_and_password", user);
        } catch (SQLException e) {
            logger.error("Got exception on select query: ", e);
            throw new DaoException(e);
        }
        return count == 1;
    }
}
