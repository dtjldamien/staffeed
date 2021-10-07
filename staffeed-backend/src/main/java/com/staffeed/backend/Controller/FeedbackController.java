//package com.staffeed.backend.Controller;
//import java.util.List;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import com.staffeed.backend.Model.Feedback;
//import com.staffeed.backend.Service.FeedbackService;
//@RestController
//public class FeedbackController {
//    @Autowired
//    private FeedbackService feedbackService;
//    @GetMapping("/get-feedbacks")
//    public ResponseEntity<List<Feedback>> getAllFeedbacks() {
//        List<Feedback> feedbacks = feedbackService.getAllFeedbacks();
//        return new ResponseEntity<>(employees, HttpStatus.OK);
//    }
//    @PostMapping("/feedback")
//    public ResponseEntity<Feedback> saveFeedback(@RequestBody Feedback feedback) {
//        Feedback feedback1 = feedbackService.addFeedback(feedback);
//        return new ResponseEntity<>(feedback1, HttpStatus.OK);
//    }
//    @PutMapping("/feedback")
//    public ResponseEntity<Feedback> updateFeedback(@RequestBody Feedback feedback) {
//        Feedback feedback1 = feedbackService.editFeedback(feedback);
//        return new ResponseEntity<>(feedback1, HttpStatus.OK);
//    }
//    @DeleteMapping("/feedback")
//    public ResponseEntity<String> deleteFeedback(@RequestParam(name = "feedbackId") String feedbackId) {
//        feedbackService.deleteFeedback(feedbackId);
//        return new ResponseEntity<>("Feedback with ID :" + feedbackId + " deleted successfully", HttpStatus.OK);
//    }
//}

package com.staffeed.backend.Controller;

import com.staffeed.backend.Model.User;
import com.staffeed.backend.Repository.UserRepository;
import com.staffeed.backend.Model.Feedback;
import com.staffeed.backend.Repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class FeedbackController {

    @Autowired
    private FeedbackRepository repository;
    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value="/user/{userId}/feedback", method=RequestMethod.POST)
    public ResponseEntity<User> addFeedbackToUser(@PathVariable String userId, @RequestBody Feedback feedback) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.OK);
        }

        User userToUpdate = optionalUser.get();
        Feedback feedbackCreated = repository.save(feedback);
        List<Feedback> feedbackList = userToUpdate.getFeedbacks();
        feedbackList.add(feedbackCreated);
        userToUpdate.setFeedbacks(feedbackList);
        User userUpdated = userRepository.save(userToUpdate);
        return new ResponseEntity<>(userUpdated, HttpStatus.OK);
    }

    @GetMapping("/feedbacks")
    public List<Feedback> getAllFeedbacks() {
        return repository.findAll();
    }

    @GetMapping("/feedback/{id}")
    public Optional<Feedback> getFeedbackById(@PathVariable String id) {
        return repository.findById(id);
    }

    @PutMapping("/feedback")
    public ResponseEntity<Feedback> updateFeedback(@RequestBody Feedback feedback) {
        Feedback editedFeedback = repository.save(feedback);
        return new ResponseEntity<>(editedFeedback, HttpStatus.OK);
    }

    @DeleteMapping("/feedback/{id}")
    public ResponseEntity<String> deleteFeedback(@PathVariable String id) {
        repository.deleteById(id);
        return new ResponseEntity<>("Feedback with ID :" + id + " deleted successfully", HttpStatus.OK);
    }
}
