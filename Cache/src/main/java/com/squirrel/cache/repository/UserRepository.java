package com.squirrel.cache.repository;

import com.squirrel.cache.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUserName(String userName);

    User findByUserNameOrEmail(String userName, String email);

    List<User> findByNickName(String nickName);
}
