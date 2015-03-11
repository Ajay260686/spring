package com.sample.spring.jersey.swagger.rest;

import com.sample.spring.jersey.swagger.beans.Employee;
import com.sample.spring.jersey.swagger.beans.EmployeeListResponse;
import com.sample.spring.jersey.swagger.beans.EmployeeResponse;
import com.sample.spring.jersey.swagger.service.EmployeeService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Controller
@Api(value="/employees", description = "Endpoint for Employee listing")
@Path("/employees")
public class EmployeeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class.getName());

    @Autowired
    private EmployeeService employeeService;

    @GET
    @ApiOperation(
            value = "Lists all employees",
            notes = "Lists all employees"
    )
    @ApiResponses(value= {
            @ApiResponse(code = 200, message = "Successful retrieval of employees"),
            @ApiResponse(code = 404, message = "Employee records not found"),
            @ApiResponse(code = 500, message = "Internal servererror")
    })
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getEmployees() {

        List<Employee> employees = employeeService.getAllEmployees();
        if(employees == null || employees.size() == 0) {
            LOGGER.debug("getEmployees No employees found");
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        else {
            LOGGER.info("getEmployees [ employeeList= [{}]]", employees.toString());
            EmployeeListResponse employeeListResponse = new EmployeeListResponse();
            employeeListResponse.setResult(employees);
            return Response.ok(employeeListResponse).build();
        }
    }

    @GET
    @ApiOperation(
            value = "Retrieves an Employee",
            notes = "Retrieves an Employee"
    )
    @ApiResponses(value= {
            @ApiResponse(code = 200, message = "Successful retrieval of employee"),
            @ApiResponse(code = 500, message = "Internal server error"),
            @ApiResponse(code = 404, message = "Employee Not found")
    })
    @Path("{employeeId}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getEmployee(@PathParam("employeeId") final Integer employeeId){
        EmployeeResponse employeeResponse = new EmployeeResponse();
        Employee employee = employeeService.getEmployee(employeeId);
        if(employee == null) {
            LOGGER.debug("getEmployee not found [employeeId ={}]", employeeId);
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        else {
            LOGGER.info("getEmployee [ employeeDetails= [{}]]", employee.toString());
            employeeResponse.setResult(employee);
            return Response.ok(employeeResponse).build();
        }
    }

    @DELETE
    @ApiOperation(
            value = "Deletes an Employee",
            notes = "Deletes an Employee"
    )
    @ApiResponses(value= {
            @ApiResponse(code = 200, message = "Successful deletion of employee"),
            @ApiResponse(code = 500, message = "Internal server error"),
            @ApiResponse(code = 404, message = "Employee Not found")
    })
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Path("{employeeId}")
    public Response deleteEmployee(@PathParam("employeeId") final Integer employeeId){
        Employee  employee  = employeeService.getEmployee(employeeId);
        if(employee == null){
            LOGGER.debug("deleteEmployee not found [employeeId ={}]", employeeId);
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        else {
            employeeService.removeEmployee(employeeId);
            LOGGER.info("Deleted Employee [employeeDetails= [{}]]", employee.toString());
            return Response.ok().build();
        }
    }

    @POST
    @ApiOperation(
            value = "Inserts a new employee record",
            notes = "Inserts a new employee record"
    )
    @ApiResponses(value= {
            @ApiResponse(code = 201, message = "Successful creation of employee record"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response createEmployee(final Employee employee){

        employeeService.addEmployee(employee);
        LOGGER.info("createEmployee Created Employee [employeeDetails=[{}]]", employee.toString());
        return Response.status(Response.Status.CREATED).build();
    }
}
