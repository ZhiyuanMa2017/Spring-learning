package com.squirrel.druidmultijpa.repository.test2;

import com.squirrel.druidmultijpa.model.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserTest2Repository extends JpaRepository<MyUser, Long> {
    MyUser findById(long id);

    MyUser findByUserName(String userName);

    MyUser findByUserNameOrEmail(String userName, String email);
}
