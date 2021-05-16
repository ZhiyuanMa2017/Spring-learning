package com.squirrel.jpathymeleaf.repository;

import com.squirrel.jpathymeleaf.model.MyUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<MyUser, Long> {

    @Query("select u from MyUser u")
    Page<MyUser> findList(Pageable pageable);

    MyUser findById(long id);

    MyUser findByUserName(String userName);

    void deleteById(long id);
}
