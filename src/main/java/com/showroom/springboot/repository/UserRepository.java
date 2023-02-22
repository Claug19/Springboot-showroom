package com.showroom.springboot.repository;

import com.showroom.springboot.model.User;
import com.showroom.springboot.utils.DataUtils;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserRepository {

    private static final List<User> users = DataUtils.getUsersMethod();

    public User findUser(int id){
        return users.get(id);
    }
}
