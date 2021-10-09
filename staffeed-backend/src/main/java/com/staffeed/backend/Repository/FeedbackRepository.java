package com.staffeed.backend.Repository;

import com.staffeed.backend.Model.Feedback;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface FeedbackRepository extends MongoRepository<Feedback, String> {

    @Aggregation(pipeline = { "{ '$group': { '_id' : '$response' } }" })
    List<Integer> findAllDistinctResponses();

    @Query(value = "{ 'user.department' : ?0 }")
    List<Feedback> findAllFeedbacksByDepartment(String department);
}
