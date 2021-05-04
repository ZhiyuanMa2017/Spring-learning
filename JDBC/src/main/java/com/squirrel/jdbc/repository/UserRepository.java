package com.squirrel.jdbc.repository;

import com.squirrel.jdbc.model.MyUser;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public interface UserRepository {

    int save(MyUser myUser, JdbcTemplate jdbcTemplate);

    int update(MyUser myUser, JdbcTemplate jdbcTemplate);

    int delete(long id, JdbcTemplate jdbcTemplate);

    List<MyUser> findAll(JdbcTemplate jdbcTemplate);

    MyUser findById(long id, JdbcTemplate jdbcTemplate);

}
