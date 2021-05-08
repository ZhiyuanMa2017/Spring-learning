package com.squirrel.jpa.repository;

import com.squirrel.jpa.model.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<MyUser, Long> {

    MyUser findByUserName(String userName);

    MyUser findByUserNameAndAndEmail(String userName, String email);

    void deleteById(Long id);

    Long countByUserName(String userName);

}
