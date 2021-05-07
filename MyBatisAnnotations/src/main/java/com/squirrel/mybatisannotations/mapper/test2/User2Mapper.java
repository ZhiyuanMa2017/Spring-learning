package com.squirrel.mybatisannotations.mapper.test2;

import com.squirrel.mybatisannotations.enums.UserSexEnum;
import com.squirrel.mybatisannotations.mapper.UserSql;
import com.squirrel.mybatisannotations.model.MyUser;
import com.squirrel.mybatisannotations.param.UserParam;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

@Mapper
public interface User2Mapper {

    @Select("SELECT * from users")
    @Results({@Result(property = "userSex", column = "user_sex", javaType = UserSexEnum.class),
            @Result(property = "nickName", column = "nick_name")})
    List<MyUser> getAll();

    @SelectProvider(type = UserSql.class, method = "getList")
    List<MyUser> getList(UserParam userParam);

    @SelectProvider(type = UserSql.class, method = "getCount")
    int getCount(UserParam userParam);

    @Select("select * from users where user_sex = #{user_sex}")
    List<MyUser> getListByUserSex(@Param("user_sex") String userSex);

    @Select("select * from users where user_sex = #{user_sex} and username=#{username}")
    List<MyUser> getListBySexAndName(Map<String, Object> map);

    @Select("SELECT * from users where id = #{id}")
    @Results({@Result(property = "userSex", column = "user_sex", javaType = UserSexEnum.class),
            @Result(property = "nickName", column = "nick_name")})
    MyUser getOne(Long id);

    @Insert("insert into users(userName,passWord,user_sex) "
            + "values(#{userName},#{passWord},#{userSex})")
    void insert(MyUser myUser);

    @Update("update users set userName = #{userName}, nick_name=#{nickName} where id = #{id}")
    int update(MyUser myUser);

    @Delete("delete from users where id =#{id}")
    int delete(Long id);

    @Update({"<script> ",
            "update users ",
            "<set>",
            "<if test='userName!=null'>userName=#{userName},</if>",
            "<if test='nickName!=null'>nick_name=#{nickName},</if>",
            "</set>",
            "where id = #{id} ",
            "</script>"})
    void updateUser(MyUser myUser);

}
