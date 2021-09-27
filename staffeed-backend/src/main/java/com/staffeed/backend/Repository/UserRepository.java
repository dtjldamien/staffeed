package com.staffeed.backend.Repository;

import com.staffeed.backend.Model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
}

