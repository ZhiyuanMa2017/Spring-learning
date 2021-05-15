package com.squirrel.druidjpa.service;

import com.squirrel.druidjpa.model.UserDetail;
import com.squirrel.druidjpa.param.UserDetailParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserDetailService {

    Page<UserDetail> findByCondition(UserDetailParam userDetailParam, Pageable pageable);
}
