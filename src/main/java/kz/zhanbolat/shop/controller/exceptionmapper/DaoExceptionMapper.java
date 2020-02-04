package kz.zhanbolat.shop.controller.exceptionmapper;

import kz.zhanbolat.shop.exception.DaoException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class DaoExceptionMapper implements ExceptionMapper<DaoException> {
    @Override
    public Response toResponse(DaoException exception) {
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(exception.getMessage()).build();
    }
}
