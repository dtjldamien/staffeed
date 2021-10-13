package com.staffeed.backend.Controller;

import com.staffeed.backend.Model.Question;
import com.staffeed.backend.Repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
public class QuestionController {

    @Autowired
    private QuestionRepository repository;

    @PostMapping("/question")
    public ResponseEntity<Question> saveQuestion(@RequestBody Question question) {
        return new ResponseEntity<>(repository.save(question), HttpStatus.OK);
    }

    @GetMapping("/questions")
    public List<Question> getQuestions() {
        return repository.findAll();
    }

    @GetMapping("/question/{id}")
    public Optional<Question> getUserById(@PathVariable String id) {
        return repository.findById(id);
    }

    @PutMapping("/question")
    public ResponseEntity<Question> updateQuestion(@RequestBody Question question) {
        Optional<Question> qn = repository.findById(question.getId());
        question.setCreatedDate(qn.get().getCreatedDate());
        question.setCreatedBy(qn.get().getCreatedBy());
        return new ResponseEntity<>(question, HttpStatus.OK);
    }

    @DeleteMapping("/question/{id}")
    public ResponseEntity<String> deleteQuestion(@PathVariable String id) {
        repository.deleteById(id);
        return new ResponseEntity<>("Question with ID:" + id + " was deleted successfully", HttpStatus.OK);
    }
}
