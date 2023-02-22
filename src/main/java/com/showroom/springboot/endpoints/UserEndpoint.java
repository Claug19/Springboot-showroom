package com.showroom.springboot.endpoints;

import com.showroomSoap.soap.user.GetUserRequest;
import com.showroomSoap.soap.user.GetUserResponse;
import com.showroom.springboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class UserEndpoint {
    private static final String NAMESPACE_URI = "http://showroomSoap.com/soap/user";

    private UserRepository userRepository;

    @Autowired
    public UserEndpoint(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getUserRequest")
    @ResponsePayload
    public GetUserResponse getUser(@RequestPayload GetUserRequest request) {
        int userId = request.getId();
        GetUserResponse response = new GetUserResponse();
        response.setUser(userRepository.findUser(request.getId()));
        return response;
    }
}