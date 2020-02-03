package kz.zhanbolat.shop.dao;

import java.util.List;

public interface BaseDao<T, K> {
    List<T> findAll();
    T findOne(K id);
    void create(T entity);
    void update(T entity);
    void delete(K id);
}
