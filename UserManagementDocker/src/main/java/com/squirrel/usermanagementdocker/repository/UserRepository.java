package com.squirrel.usermanagementdocker.repository;

import com.squirrel.usermanagementdocker.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {

    Page<User> findAll(Pageable pageable);

    Optional<User> findById(String s);

    User findByUserNameOrEmail(String userName, String email);

    User findByUserName(String userName);

    User findByEmail(String email);

    void deleteById(String id);
}
