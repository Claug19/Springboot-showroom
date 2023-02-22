package com.showroom.springboot.resource;

import com.showroom.springboot.model.User;
import com.showroom.springboot.utils.DataUtils;
import io.swagger.annotations.Api;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rest/user")
@Api(value = "User Resource REST Endpoint", description = "Shows the user info")
public class UserController {

    @ApiOperation(value = "Returns user list")
    @GetMapping
    public List<User> getUsers() {
        List<com.showroom.springboot.model.User> users = new ArrayList<>();
        users = DataUtils.getUsersMethod();
        return users;
    }

    @ApiOperation(value = "Search by username")
    @GetMapping("/{userName}")
    public List<User> getUser(@PathVariable("userName") final String userName)
    {
        List<com.showroom.springboot.model.User> users = new ArrayList<>();
        users = DataUtils.getUsersMethod();
        return users.stream()
                .filter(x -> x.getUsername().equalsIgnoreCase(userName))
                .collect(Collectors.toList());
    }

    @ApiOperation(value = "Create user")
    @PostMapping("/user")
    public User create(@RequestBody User newUser) {
        DataUtils.addUser(newUser);
        return newUser;
    }
}