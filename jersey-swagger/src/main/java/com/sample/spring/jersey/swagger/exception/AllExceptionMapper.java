package com.sample.spring.jersey.swagger.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class AllExceptionMapper implements ExceptionMapper<Exception> {

    private static final Logger LOGGER = LoggerFactory.getLogger(AllExceptionMapper.class.getName());
    @Override
    public Response toResponse(Exception e) {
        LOGGER.error("allexceptionmapper - " + e.getMessage(), e);
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }
}
