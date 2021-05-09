package com.squirrel.jpa.repository;

import com.squirrel.jpa.model.MyUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserRepository extends JpaRepository<MyUser, Long> {

    MyUser findByUserName(String userName);

    MyUser findByUserNameOrEmail(String userName, String email);

    @Transactional
    @Modifying
    @Query("delete from MyUser where id = ?1")
    void deleteById(Long id);

    @Query("select u from MyUser u where u.email = ?1")
    MyUser findByEmail(String email);

    @Transactional(timeout = 10)
    @Modifying
    @Query("update MyUser set userName=?1 where id =?2")
    int modifyById(String userName, Long id);

    @Query("select u from MyUser u where u.nickName = :nickName")
    Page<MyUser> findByNickName(@Param("nickName") String nickName, Pageable pageable);

    @Query("select u from MyUser u")
    Page<MyUser> findAll(Pageable pageable);

    List<MyUser> findByPassWord(String passWord);

    List<MyUser> findByNickName(String nickName);

    Slice<MyUser> findByNickNameAndEmail(String nickName, String email, Pageable pageable);

    MyUser findFirstByOrderByUserNameAsc();

    MyUser findTopByOrderByIdDesc();

    Page<MyUser> queryFirst10ByUserName(String userName, Pageable pageable);

    List<MyUser> findFirst10ByUserName(String userName, Pageable pageable);

    List<MyUser> findTop10ByUserName(String userName, Pageable pageable);

}
