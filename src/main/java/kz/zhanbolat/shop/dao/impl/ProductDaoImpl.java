package kz.zhanbolat.shop.dao.impl;

import com.ibatis.sqlmap.client.SqlMapClient;
import kz.zhanbolat.shop.dao.ProductDao;
import kz.zhanbolat.shop.entity.Product;
import kz.zhanbolat.shop.exception.DaoException;
import kz.zhanbolat.shop.exception.NoProductFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.sql.SQLException;
import java.util.List;

@Stateless
public class ProductDaoImpl implements ProductDao {
    private static final Logger logger = LogManager.getLogger(ProductDaoImpl.class);
    private static final String SELECT_ALL_QUERY_ID = "get_all_product";
    private static final String SELECT_ONE_BY_ID_QUERY_ID = "get_product_by_id";
    private static final String INSERT_QUERY_ID = "insert_product";
    private static final String UPDATE_QUERY_ID = "update_product";
    private static final String DELETE_BY_ID_QUERY_ID = "delete_product_by_id";
    private static final String SELECT_ALL_BY_CATEGORY_NAME_QUERY_ID = "get_all_product_by_category";
    private static final String SELECT_ONE_BY_NAME_QUERY_ID = "get_product_by_name";
    @Inject
    private SqlMapClient sqlMapClient;

    @Override
    public List<Product> findAll() {
        try {
            return sqlMapClient.queryForList(SELECT_ALL_QUERY_ID);
        } catch (SQLException e) {
            logger.error("Got exception on select query: ", e);
            throw new DaoException(e);
        }
    }

    @Override
    public Product findOne(Integer id) {
        try {
            Product product = (Product) sqlMapClient.queryForObject(SELECT_ONE_BY_ID_QUERY_ID, id);
            if (product == null) {
                throw new NoProductFoundException("No product was found");
            }
            return product;
        } catch (SQLException e) {
            logger.error("Got exception on select query: ", e);
            throw new DaoException(e);
        }
    }

    @Override
    public void create(Product entity) {
        try {
            sqlMapClient.insert(INSERT_QUERY_ID, entity);
        } catch (SQLException e) {
            logger.error("Got exception on insert query: ", e);
            throw new DaoException(e);
        }
    }

    @Override
    public void update(Product entity) {
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
    public List<Product> findAllByCategory(String categoryName) {
        try {
            List<Product> products = sqlMapClient.queryForList(SELECT_ALL_BY_CATEGORY_NAME_QUERY_ID, categoryName);
            if (products.isEmpty()) {
                throw new NoProductFoundException("Products with such category don't exist");
            }
            return products;
        } catch (SQLException e) {
            logger.error("Got exception on select query: ", e);
            throw new DaoException(e);
        }
    }

    @Override
    public Product findOneByName(String name) {
        try {
            Product product =  (Product) sqlMapClient.queryForObject(SELECT_ONE_BY_NAME_QUERY_ID, name);
            if (product == null) {
                throw new NoProductFoundException("Such product doesn't exist");
            }
            return product;
        } catch (SQLException e) {
            logger.error("Got exception on select query: ", e);
            throw new NoProductFoundException(e);
        }
    }
}
