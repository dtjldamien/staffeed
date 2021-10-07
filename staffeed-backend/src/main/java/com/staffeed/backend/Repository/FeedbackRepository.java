package com.staffeed.backend.Repository;
import com.staffeed.backend.Model.Feedback;
import org.springframework.data.mongodb.repository.MongoRepository;
public interface FeedbackRepository extends MongoRepository<Feedback, String> {
}
