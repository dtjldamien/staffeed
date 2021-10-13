package com.staffeed.backend.Repository;

import com.staffeed.backend.Model.Question;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface QuestionRepository extends MongoRepository<Question, String> {
}
