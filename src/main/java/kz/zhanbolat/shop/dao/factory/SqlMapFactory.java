package kz.zhanbolat.shop.dao.factory;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

import javax.enterprise.inject.Produces;
import java.io.IOException;

public final class SqlMapFactory {

    @Produces
    public SqlMapClient createSqlMapClient() throws IOException {
        return SqlMapClientBuilder.buildSqlMapClient(Resources.getResourceAsReader("./SqlMapConfig.xml"));
    }
}
