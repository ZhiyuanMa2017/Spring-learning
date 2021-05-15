package com.squirrel.druid.mapper.test1;

import com.squirrel.druid.enums.UserSexEnum;
import com.squirrel.druid.mapper.UserSql;
import com.squirrel.druid.model.MyUser;
import com.squirrel.druid.param.UserParam;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface User1Mapper {

    @Select("SELECT * from users")
    @Results({@Result(property = "userSex", column = "user_sex", javaType = UserSexEnum.class),
            @Result(property = "nickName", column = "nick_name")})
    List<MyUser> getAll();

    @SelectProvider(type = UserSql.class, method = "getList")
    List<MyUser> getList(UserParam userParam);

    @SelectProvider(type = UserSql.class, method = "getCount")
    int getCount(UserParam userParam);

    @Select("SELECT * from users where id = #{id}")
    @Results({@Result(property = "userSex", column = "user_sex", javaType = UserSexEnum.class),
            @Result(property = "nickName", column = "nick_name")})
    MyUser getOne(Long id);

    @Insert("insert into users(userName,passWord,user_sex) "
            + "values(#{userName},#{passWord},#{userSex})")
    void insert(MyUser myUser);

    @Update("update users set userName = #{userName}, nick_name=#{nickName} where id = #{id}")
    void update(MyUser myUser);

    @Delete("delete from users where id =#{id}")
    void delete(Long id);
}
