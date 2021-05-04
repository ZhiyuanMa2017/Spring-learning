package com.squirrel.jdbc.repository;

import com.squirrel.jdbc.model.MyUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private JdbcTemplate primaryJdbcTemplate;

    @Override
    public int save(MyUser myUser, JdbcTemplate jdbcTemplate) {
        if (jdbcTemplate == null) {
            jdbcTemplate = primaryJdbcTemplate;
        }
        return jdbcTemplate.update("INSERT INTO users(name, password, age) values (?,?,?)",
                myUser.getName(), myUser.getPassword(), myUser.getAge());
    }

    @Override
    public int update(MyUser myUser, JdbcTemplate jdbcTemplate) {
        if (jdbcTemplate == null) {
            jdbcTemplate = primaryJdbcTemplate;
        }
        return jdbcTemplate.update("UPDATE users SET name = ?, password = ?, age =?  where id = ?",
                myUser.getName(), myUser.getPassword(), myUser.getAge(), myUser.getId());
    }

    @Override
    public int delete(long id, JdbcTemplate jdbcTemplate) {
        if (jdbcTemplate == null) {
            jdbcTemplate = primaryJdbcTemplate;
        }
        return jdbcTemplate.update("DELETE FROM users where id = ?", id);
    }

    @Override
    public List<MyUser> findAll(JdbcTemplate jdbcTemplate) {
        if (jdbcTemplate == null) {
            jdbcTemplate = primaryJdbcTemplate;
        }
        return jdbcTemplate.query("SELECT * FROM users", new UserRowMapper());
    }

    @Override
    public MyUser findById(long id, JdbcTemplate jdbcTemplate) {
        if (jdbcTemplate == null) {
            jdbcTemplate = primaryJdbcTemplate;
        }
        return jdbcTemplate.queryForObject("SELECT * FROM users where id=?",
                new BeanPropertyRowMapper<MyUser>(MyUser.class), new Object[]{id});
    }

    class UserRowMapper implements RowMapper<MyUser> {
        @Override
        public MyUser mapRow(ResultSet rs, int rowNum) throws SQLException {
            MyUser myUser = new MyUser();
            myUser.setId(rs.getLong("id"));
            myUser.setName(rs.getString("name"));
            myUser.setPassword(rs.getString("password"));
            myUser.setAge(rs.getInt("age"));
            return myUser;
        }
    }
}
