package com.staffeed.backend.Repository;

import com.staffeed.backend.Model.Category;
import com.staffeed.backend.Model.Question;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface QuestionRepository extends MongoRepository<Question, String> {

    @Aggregation(pipeline = { "{ '$sort' : { 'startDate' : -1 } }" })
    List<Question> getAllQuestionsByLatest();

    @Query(value = "{ 'category' : ?0 }", sort = "{ 'startDate': -1 }")
    List<Question> findByCategory(Category category);

}
