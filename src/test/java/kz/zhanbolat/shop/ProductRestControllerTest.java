package kz.zhanbolat.shop;

import kz.zhanbolat.shop.controller.ProductRestController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Ignore;
import org.junit.Test;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

/**
 * Don't work properly
 */
@Ignore
public class ProductRestControllerTest extends JerseyTest {
    private static final Logger logger = LogManager.getLogger(ProductRestControllerTest.class);

    @Override
    protected Application configure() {
        return new ResourceConfig(ProductRestController.class);
    }

    @Test
    public void getProducts() {
        Response response = target("product").request().get();
        logger.debug(response);
        Object entity = response.getEntity();
        logger.debug(entity);
    }
}
