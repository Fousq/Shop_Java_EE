package kz.zhanbolat.shop.controller.exceptionmapper;

import kz.zhanbolat.shop.exception.NoProductFoundException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ProductNotFoundMapper implements ExceptionMapper<NoProductFoundException> {
    @Override
    public Response toResponse(NoProductFoundException exception) {
        return Response.status(Response.Status.NOT_FOUND).entity(exception.getMessage()).build();
    }
}