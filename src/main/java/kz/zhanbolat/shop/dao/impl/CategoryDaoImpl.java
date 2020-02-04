package kz.zhanbolat.shop.dao.impl;

import com.ibatis.sqlmap.client.SqlMapClient;
import kz.zhanbolat.shop.dao.BaseDao;
import kz.zhanbolat.shop.entity.Category;
import kz.zhanbolat.shop.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.sql.SQLException;
import java.util.List;

@Stateless
public class CategoryDaoImpl implements BaseDao<Category, String> {
    private static final Logger logger = LogManager.getLogger(CategoryDaoImpl.class);
    private static final String SELECT_ALL_QUERY_ID = "get_all_category";
    private static final String INSERT_QUERY_ID = "insert_category";
    private static final String DELETE_QUERY_ID = "delete_category";
    @Inject
    private SqlMapClient sqlMapClient;

    @Override
    public List<Category> findAll() {
        try {
            return sqlMapClient.queryForList(SELECT_ALL_QUERY_ID);
        } catch (SQLException e) {
            logger.error("Got exception on select query: ", e);
            throw new DaoException(e);
        }
    }

    @Override
    public Category findOne(String id) {
        logger.info("Find one by the primary key cannot be execute due to existence of one column");
        throw new UnsupportedOperationException("find one cannot be execute, see the logs");
    }

    @Override
    public void create(Category entity) {
        try {
            sqlMapClient.insert(INSERT_QUERY_ID, entity);
        } catch (SQLException e) {
            logger.error("Got exception on insert query: ", e);
            throw new DaoException(e);
        }
    }

    @Override
    public void update(Category entity) {
        logger.info("Updated cannot be executed if table has only one column");
        throw new UnsupportedOperationException("update cannot be execute, see the logs");
    }

    @Override
    public void delete(String id) {
        try {
            sqlMapClient.delete(DELETE_QUERY_ID, id);
        } catch (SQLException e) {
            logger.error("Got exception on delete query: ", e);
            throw new DaoException(e);
        }
    }
}
