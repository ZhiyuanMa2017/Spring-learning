package com.squirrel.jpa.repository;


import com.squirrel.jpa.model.UserDetail;
import com.squirrel.jpa.param.UserDetailParam;
import com.squirrel.jpa.service.UserDetailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JpaSpecificationTests {

    @Resource
    private UserDetailService userDetailService;



    @Test
    public void testFindByCondition() {
        int page = 0, size = 10;
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "id"));
        UserDetailParam userDetailParam = new UserDetailParam();
        userDetailParam.setIntroduction("programmer");
        userDetailParam.setMinAge(10);
        userDetailParam.setMaxAge(30);
        Page<UserDetail> page1 = userDetailService.findByCondition(userDetailParam, pageable);
        for (UserDetail userDetail : page1) {
            System.out.println("userDetail: " + userDetail.toString());
        }
    }
}
