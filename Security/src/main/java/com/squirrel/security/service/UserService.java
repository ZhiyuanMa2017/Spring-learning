package com.squirrel.security.service;


import org.springframework.security.access.annotation.Secured;

import java.util.List;

public interface UserService {

    List<User> findAllUsers();

    @Secured("ADMIN")
    void updateUser(User user);


    @Secured({"ADMIN", "USER"})
    void deleteUser();
}
