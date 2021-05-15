package com.squirrel.druid.mapper;

import com.squirrel.druid.param.UserParam;
import org.apache.ibatis.jdbc.SQL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.mysql.cj.util.StringUtils;


public class UserSql {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserSql.class);

    public String getList(UserParam userParam) {
        StringBuffer sql = new StringBuffer(
                "select id, userName, passWord, user_sex as userSex, nick_name as nickName");
        sql.append(" from users where 1=1");
        if (userParam != null) {
            if (!StringUtils.isNullOrEmpty(userParam.getUserName())) {
                sql.append(" and userName = #{userName}");
            }
            if (!StringUtils.isNullOrEmpty(userParam.getUserSex())) {
                sql.append(" and user_sex = #{userSex}");
            }
        }
        sql.append(" order by id desc");
        sql.append(" limit " + userParam.getBeginLine() + "," + userParam.getPageSize());
        LOGGER.info("getList sql is " + sql.toString());
        return sql.toString();
    }

    public String getCount(UserParam userParam) {
        String sql = new SQL() {{
            SELECT("count(1)");
            FROM("users");
            if (!StringUtils.isNullOrEmpty(userParam.getUserName())) {
                WHERE("userName = #{userName}");
            }
            if (!StringUtils.isNullOrEmpty(userParam.getUserSex())) {
                WHERE("user_sex = #{userSex}");
            }

        }}.toString();
        LOGGER.info("getCount sql is " + sql);
        return sql;
    }
}
