package com.squirrel.multimongodb.repository.primary;

import com.squirrel.multimongodb.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PrimaryRepository extends MongoRepository<User, String> {

}
