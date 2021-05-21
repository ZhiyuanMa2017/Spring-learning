package com.squirrel.multimongodb.repository.secondary;

import com.squirrel.multimongodb.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SecondaryRepository extends MongoRepository<User, String> {

}
