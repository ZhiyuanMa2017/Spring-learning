package com.squirrel.mybatisxml.mapper.two;

import com.squirrel.mybatisxml.model.MyUser;
import com.squirrel.mybatisxml.param.UserParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface User2Mapper {

    List<MyUser> getAll();

    List<MyUser> getList(UserParam userParam);

    int getCount(UserParam userParam);

    MyUser getOne(Long id);

    void insert(MyUser myUser);

    int update(MyUser myUser);

    int delete(Long id);

}
