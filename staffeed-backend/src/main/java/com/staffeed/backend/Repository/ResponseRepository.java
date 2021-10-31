package com.staffeed.backend.Repository;

import com.staffeed.backend.Model.Response;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ResponseRepository extends MongoRepository<Response, String> {

    @Aggregation(pipeline = { "{ '$group': { '_id' : '$response' } }" })
    List<Response> findAllDistinctResponses();

    @Query(value = "{ 'user.department' : ?0 }")
    List<Response> findAllResponsesByDepartment(String department);

    @Aggregation(pipeline = { "{ '$sort' : { 'submittedOn' : -1 } }" })
    List<Response> getAllResponsesByLatest();

    @Aggregation(pipeline = { "{ '$match': { 'question._id': ?0 } }", "{ '$group': { '_id' : '$user._id' } }" })
    List<Response> findResponsesWithDistinctRespondents(String questionId);

}
