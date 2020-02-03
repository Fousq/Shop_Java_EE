package kz.zhanbolat.shop.dao.factory;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

import javax.enterprise.inject.Produces;

public final class SqlMapFactory {

    @Produces
    public SqlMapClient createSqlMapClient() {
        return SqlMapClientBuilder.buildSqlMapClient(getClass().getResourceAsStream("SqlMapConfig"));
    }
}
