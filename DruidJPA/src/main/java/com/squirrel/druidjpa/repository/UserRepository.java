package com.squirrel.druidjpa.repository;

import com.squirrel.druidjpa.model.MyUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;



public interface UserRepository extends JpaRepository<MyUser, Long> {

    @Transactional
    @Modifying
    @Query("delete from MyUser where id = ?1")
    void deleteById(Long id);

    @Query("select u from MyUser u")
    Page<MyUser> findAll(Pageable pageable);

}
