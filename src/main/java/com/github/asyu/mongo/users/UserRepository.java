package com.github.asyu.mongo.users;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserRepository extends MongoRepository<User, String> {

    public User findByName(String name);

    public List<User> findByAgeGreaterThan(Integer age);
}
