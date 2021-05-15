package com.squirrel.druidjpa.service;

import com.mysql.cj.util.StringUtils;
import com.squirrel.druidjpa.model.UserDetail;
import com.squirrel.druidjpa.param.UserDetailParam;
import com.squirrel.druidjpa.repository.UserDetailRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailServiceImpl implements UserDetailService {

    @Resource
    private UserDetailRepository userDetailRepository;

    @Override
    public Page<UserDetail> findByCondition(UserDetailParam userDetailParam, Pageable pageable) {
        return userDetailRepository.findAll((root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (!StringUtils.isNullOrEmpty(userDetailParam.getIntroduction())) {
                predicates.add(
                        cb.equal(root.get("introduction"), userDetailParam.getIntroduction()));
            }

            if (!StringUtils.isNullOrEmpty(userDetailParam.getRealName())) {
                predicates.add(
                        cb.like(root.get("realName"), "%" + userDetailParam.getRealName() + "%"));
            }

            if (userDetailParam.getMinAge() != null && userDetailParam.getMaxAge() != null) {
                Predicate agePredicate =
                        cb.between(
                                root.get("age"),
                                userDetailParam.getMinAge(),
                                userDetailParam.getMaxAge()
                        );
                predicates.add(agePredicate);
            }

            if (userDetailParam.getMinAge() != null) {
                predicates.add(
                        cb.greaterThan(root.get("age"), userDetailParam.getMinAge()));
            }

            return query
                    .where(predicates.toArray(new Predicate[predicates.size()]))
                    .getRestriction();
        }, pageable);
    }
}
