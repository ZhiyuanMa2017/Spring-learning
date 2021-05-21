package com.squirrel.mongodb.TemplateRepository;

import com.squirrel.mongodb.model.User;

public interface TemplateUserRepository {

    void saveUser(User user);

    User findUserByUserName(String userName);

    long updateUser(User user);

    void deleteUserById(Long id);
}
