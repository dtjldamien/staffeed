package com.staffeed.backend.Controller;

import com.staffeed.backend.Model.Employee;
import com.staffeed.backend.Model.User;
import com.staffeed.backend.Repository.EmployeeRepository;
import com.staffeed.backend.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin
public class UserController {

    @Autowired
    private UserRepository repository;
    @Autowired
    private EmployeeRepository employeeRepository;

    @PostMapping("/user")
    public ResponseEntity<User> saveUser(@RequestBody Map<String, String> jsonObj) {
        if (jsonObj.get("type").equalsIgnoreCase("employee")) {
            Employee newEmployee = new Employee(jsonObj.get("name"), jsonObj.get("password"), jsonObj.get("email"), jsonObj.get("department"));
            return new ResponseEntity<>(employeeRepository.save(newEmployee), HttpStatus.OK);
        }
        User newUser = new User(jsonObj.get("name"), jsonObj.get("password"), jsonObj.get("email"));
        return new ResponseEntity<>(repository.save(newUser), HttpStatus.OK);
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        return repository.findAll();
    }

    @GetMapping("/user/{id}")
    public Optional<User> getUserById(@PathVariable String id) {
        return repository.findById(id);
    }

    @PutMapping("/user")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        User userUpdated = repository.save(user);
        return new ResponseEntity<>(userUpdated, HttpStatus.OK);
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable String id) {
        repository.deleteById(id);
        return new ResponseEntity<>("User with ID:" + id + " was deleted successfully", HttpStatus.OK);
    }
}
