package com.staffeed.backend.Repository;

import com.staffeed.backend.Model.User;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    @Aggregation(pipeline = { "{ '$group': { '_id' : '$department' } }" })
    List<String> getAllDepartments();
    
}

