package com.showroom.springboot.resource;

import com.showroom.springboot.model.*;
import com.showroom.springboot.services.ClientService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/client")
@Api(value = "Client Resource", description = "shows client info")
public class ClientController {
    @Autowired
    ClientService clientService;

    @ApiOperation(value = "Returns car list")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 100, message = "100 is the message"),
                    @ApiResponse(code = 200, message = "Successful request")
            }
    )
    @GetMapping("/clients")
    public List<Client> getClients() {
        return clientService.getClientList();
    }
}

