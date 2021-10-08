package com.staffeed.backend.Controller;

import com.staffeed.backend.Model.User;
import com.staffeed.backend.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class UserController {

    @Autowired
    private UserRepository repository;

    @PostMapping("/user")
    public String saveUser(@RequestBody User user) {
        repository.save(user);
        return user.getId();
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        return repository.findAll();
    }

    @GetMapping("/user/{id}")
    public Optional<User> getUserById(@PathVariable String id) {
        return repository.findById(id);
    }

    @DeleteMapping("/user")
    public String deleteUser(@PathVariable String id) {
        repository.deleteById(id);
        return id;
    }
}
