package kz.zhanbolat.shop.dao.factory;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import java.io.IOException;

@ApplicationScoped
public final class SqlMapFactory {
    private static final String SQL_MAP_CONFIG_PATH = "./SqlMapConfig.xml";

    @Produces
    public SqlMapClient createSqlMapClient() throws IOException {
        return SqlMapClientBuilder.buildSqlMapClient(Resources.getResourceAsReader(SQL_MAP_CONFIG_PATH));
    }
}
