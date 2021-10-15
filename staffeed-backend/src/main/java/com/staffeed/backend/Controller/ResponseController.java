package com.staffeed.backend.Controller;

import com.staffeed.backend.Model.Question;
import com.staffeed.backend.Model.User;
import com.staffeed.backend.Payload.Response.MessageResponse;
import com.staffeed.backend.Repository.QuestionRepository;
import com.staffeed.backend.Repository.UserRepository;
import com.staffeed.backend.Model.Response;
import com.staffeed.backend.Repository.ResponseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
public class ResponseController {

    @Autowired
    private ResponseRepository repository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private QuestionRepository questionRepository;

    @RequestMapping(value="/user/{userId}/response", method=RequestMethod.POST)
    public ResponseEntity<?> addResponseToUser(@PathVariable String userId, @RequestBody Map<String, String> requestBody) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty()) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Invalid user ID!"));
        }

        // check question id
        if (requestBody.get("questionId") == null || requestBody.get("questionId").equals("")) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Need to provide question ID!"));
        }

        Optional<Question> optionalQuestion = questionRepository.findById(requestBody.get("questionId"));
        if (optionalQuestion.isEmpty()) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Invalid question ID!"));
        }

        Question questionAnswered = optionalQuestion.get();
        User userToUpdate = optionalUser.get();

        // check input response
        if (requestBody.get("response") == null || requestBody.get("response").equals("")) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Need to provide response!"));
        }

        // save response
        Response newResponse = new Response(requestBody.get("response"), userToUpdate, questionAnswered);
        Response responseCreated = repository.save(newResponse);
        responseCreated.setUser(userToUpdate);

        // update user
        List<Response> responseList = userToUpdate.getResponses();
        responseList.add(responseCreated);
        userToUpdate.setResponses(responseList);
        User userUpdated = userRepository.save(userToUpdate);

        // update question
        responseList = questionAnswered.getResponses();
        responseList.add(responseCreated);
        questionAnswered.setResponses(responseList);
        questionRepository.save(questionAnswered);

        return ResponseEntity.ok(new MessageResponse("Response submitted successfully"));
    }

    @GetMapping("/responses")
    public ResponseEntity<?> getAllResponses(@RequestParam(required = false) Integer minutes, @RequestParam(required = false) String questionId) {
        if (minutes != null && minutes < 0) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Minutes cannot be negative!"));
        } else {
            List<Response> listOfResponses = repository.getAllResponsesByLatest();
            if (minutes != null) {
                listOfResponses = listOfResponses
                                    .stream()
                                    .filter(response -> {
                                       Date dateToBeCompared = new Date(System.currentTimeMillis() - (60 * minutes) * 1000);
                                       return response.getSubmittedOn().after(dateToBeCompared);
                                    })
                                    .collect(Collectors.toList());
            }

            if (questionId != null && !questionId.equals("")) {
                listOfResponses = listOfResponses
                                    .stream()
                                    .filter(response -> response.getQuestion().getId().equals(questionId))
                                    .collect(Collectors.toList());
            }

            return new ResponseEntity<>(listOfResponses, HttpStatus.OK);
        }

    }

    @GetMapping("/response/{id}")
    public Optional<Response> getResponseById(@PathVariable String id) {
        return repository.findById(id);
    }

    @PutMapping("/response/{id}")
    public ResponseEntity<?> updateResponse(@PathVariable String id, @RequestBody Map<String, Object> requestObj) {
        Optional<Response> responseFromDb = getResponseById(id);
        if (responseFromDb.isEmpty()) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Invalid response ID!"));
        }

        Response responseToBeEdited = responseFromDb.get();
        String updatedResponse = (String) requestObj.get("response");
        responseToBeEdited.setResponse(updatedResponse);
        Response updatedFeedback = repository.save(responseToBeEdited);
        return new ResponseEntity<>(updatedFeedback, HttpStatus.OK);
    }

    @DeleteMapping("/response/{id}")
    public ResponseEntity<String> deleteResponse(@PathVariable String id) {
        repository.deleteById(id);
        return new ResponseEntity<>("Response with ID :" + id + " deleted successfully", HttpStatus.OK);
    }
}
