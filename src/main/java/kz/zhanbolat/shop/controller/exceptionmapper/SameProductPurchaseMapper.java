package kz.zhanbolat.shop.controller.exceptionmapper;

import kz.zhanbolat.shop.exception.SameProductPurchaseException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class SameProductPurchaseMapper implements ExceptionMapper<SameProductPurchaseException> {
    @Override
    public Response toResponse(SameProductPurchaseException exception) {
        return Response.status(Response.Status.NOT_ACCEPTABLE).entity(exception.getMessage()).build();
    }
}
