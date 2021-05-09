package com.squirrel.jpa.service;

import com.squirrel.jpa.model.UserDetail;
import com.squirrel.jpa.param.UserDetailParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserDetailService {

    Page<UserDetail> findByCondition(UserDetailParam userDetailParam, Pageable pageable);
}
