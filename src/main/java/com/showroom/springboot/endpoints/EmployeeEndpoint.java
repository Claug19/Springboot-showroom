package com.showroom.springboot.endpoints;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.showroomSoap.soap.employee.GetEmployeeRequest;
import com.showroomSoap.soap.employee.GetEmployeeResponse;
import com.showroom.springboot.repository.EmployeeRepository;

@Endpoint
public class EmployeeEndpoint {
    private static final String NAMESPACE_URI = "http://showroomSoap.com/soap/employee";

    private EmployeeRepository empRepository;

    @Autowired
    public EmployeeEndpoint(EmployeeRepository empRepository) {
        this.empRepository = empRepository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getEmployeeRequest")
    @ResponsePayload
    public GetEmployeeResponse getEmployee(@RequestPayload GetEmployeeRequest request) {
        GetEmployeeResponse response = new GetEmployeeResponse();
        response.setEmployee(empRepository.findEmployee(request.getId()));

        return response;
    }
}
