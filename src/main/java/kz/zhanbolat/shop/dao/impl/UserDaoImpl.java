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
    private static final String SELECT_ALL_QUERY_ID = "get_all_user";
    private static final String SELECT_ONE_BY_ID_QUERY_ID = "get_user_by_id";
    private static final String INSERT_QUERY_ID = "insert_user";
    private static final String UPDATE_QUERY_ID = "update_user";
    private static final String DELETE_BY_ID_QUERY_ID = "delete_user_by_id";
    private static final String COUNT_USERS_BY_USERNAME_AND_PASSWORD_QUERY_ID = "count_user_of_username_and_password";
    @Inject
    private SqlMapClient sqlMapClient;

    @Override
    public List<User> findAll() {
        try {
            return sqlMapClient.queryForList(SELECT_ALL_QUERY_ID);
        } catch (SQLException e) {
            logger.error("Got exception on select query: ", e);
            throw new DaoException(e);
        }
    }

    @Override
    public User findOne(Integer id) {
        try {
            User user = (User) sqlMapClient.queryForObject(SELECT_ONE_BY_ID_QUERY_ID, id);
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
            sqlMapClient.insert(INSERT_QUERY_ID, entity);
        } catch (SQLException e) {
            logger.error("Got exception on insert query: ", e);
            throw new DaoException(e);
        }
    }

    @Override
    public void update(User entity) {
        try {
            sqlMapClient.update(UPDATE_QUERY_ID, entity);
        } catch (SQLException e) {
            logger.error("Got exception on update query: ", e);
            throw new DaoException(e);
        }
    }

    @Override
    public void delete(Integer id) {
        try {
            sqlMapClient.delete(DELETE_BY_ID_QUERY_ID, id);
        } catch (SQLException e) {
            logger.error("Got exception on delete query: ", e);
            throw new DaoException(e);
        }
    }

    @Override
    public boolean checkUserExist(User user) {
        int count = 0;
        try {
            count = (int) sqlMapClient.queryForObject(COUNT_USERS_BY_USERNAME_AND_PASSWORD_QUERY_ID, user);
        } catch (SQLException e) {
            logger.error("Got exception on select query: ", e);
            throw new DaoException(e);
        }
        return count == 1;
    }
}
