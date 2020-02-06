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
    @Inject
    private SqlMapClient sqlMapClient;

    @Override
    public List<Product> findAll() {
        try {
            return sqlMapClient.queryForList("get_all_product");
        } catch (SQLException e) {
            logger.error("Got exception on select query: ", e);
            throw new DaoException(e);
        }
    }

    @Override
    public Product findOne(Integer id) {
        try {
            Product product = (Product) sqlMapClient.queryForObject("get_product_by_id", id);
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
            sqlMapClient.insert("insert_product", entity);
        } catch (SQLException e) {
            logger.error("Got exception on insert query: ", e);
            throw new DaoException(e);
        }
    }

    @Override
    public void update(Product entity) {
        try {
            sqlMapClient.update("update_product", entity);
        } catch (SQLException e) {
            logger.error("Got exception on update query: ", e);
            throw new DaoException(e);
        }
    }

    @Override
    public void delete(Integer id) {
        try {
            sqlMapClient.delete("delete_product_by_id", id);
        } catch (SQLException e) {
            logger.error("Got exception on delete query: ", e);
            throw new DaoException(e);
        }
    }

    @Override
    public List<Product> findAllByCategory(String categoryName) {
        try {
            return sqlMapClient.queryForList("get_all_product_by_category", categoryName);
        } catch (SQLException e) {
            logger.error("Got exception on select query: ", e);
            throw new DaoException(e);
        }
    }

    @Override
    public Product findOneByName(String name) {
        try {
            return (Product) sqlMapClient.queryForObject("get_product_by_name", name);
        } catch (SQLException e) {
            logger.error("Got exception on select query: ", e);
            throw new NoProductFoundException(e);
        }
    }
}
