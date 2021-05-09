package com.squirrel.multijpa.repository.test1;

import com.squirrel.multijpa.model.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserTest1Repository extends JpaRepository<MyUser, Long> {

    MyUser findById(long id);

    MyUser findByUserName(String userName);

    MyUser findByUserNameOrEmail(String userName, String email);
}
