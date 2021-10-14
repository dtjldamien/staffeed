package com.staffeed.backend;

import com.staffeed.backend.Model.Categories;
import com.staffeed.backend.Model.Question;
import com.staffeed.backend.Repository.EmployeeRepository;
import com.staffeed.backend.Repository.ResponseRepository;
import com.staffeed.backend.Repository.QuestionRepository;
import com.staffeed.backend.Repository.UserRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;


@SpringBootApplication
@EnableMongoAuditing
public class BackendApplication {

    @Autowired
    PasswordEncoder encoder;
    @Autowired
    UserRepository userRepository;
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    ResponseRepository responseRepository;
    @Autowired
    QuestionRepository questionRepository;

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }

    @Bean public InitializingBean sendDatabase() {
        return () -> {
            String password = encoder.encode("password");
            if (questionRepository.findAll().size() < 1) {
                Question question1 =
                        new Question("Question 1", "How are you feeling today?", new String[]{"1", "2", "3"},
                                new Date(), Categories.FINANCIAL);
                Question question2 =
                        new Question("Question 2", "Is the meeting productive just now?", new String[]{"1", "2", "3"}
                                , new Date(), Categories.HEALTH);
                Question question3 =
                        new Question("Question 3", "Are you stressed at work?", new String[]{"1", "2", "3"},
                                new Date(), Categories.MOOD);
                Question question4 =
                        new Question("Question 4", "I have other things in mind", new String[]{"1", "2", "3"},
                                new Date(), Categories.MOTIVATION);
                Question question5 = new Question("Question 5", "I am overworked",
                        new String[]{"Strongly Agree", "Agree", "Neutral", "Disagree", "String Disagree"},
                        new Date(), Categories.STRESS);
                questionRepository.save(question1);
                questionRepository.save(question2);
                questionRepository.save(question3);
                questionRepository.save(question4);
                questionRepository.save(question5);
            }

//			if (employeeRepository.findAll().size() < 1) {
//				// creating employee's account
//				Employee employee1 = new Employee("employee1", password, "employee1@user.com", "investment");
//				Employee employee2 = new Employee("employee2", password, "employee2@user.com", "technology");
//				employeeRepository.save(employee1);
//				employeeRepository.save(employee2);
//
//				// creating HR's account
//				User hr = new User("hr", password, "hr@user.com");
//				userRepository.save(hr);
//
//				// Creating feedbacks
//				Feedback feedback1 = new Feedback(1, employee1);
//				Feedback feedback2 = new Feedback(1, employee1);
//				Feedback feedback3 = new Feedback(2, employee1);
//				Feedback feedback4 = new Feedback(2, employee1);
//				Feedback feedback5 = new Feedback(3, employee1);
//				Feedback feedback6 = new Feedback(2, employee1);
//				Feedback feedback7 = new Feedback(2, employee1);
//				Feedback feedback8 = new Feedback(3, employee1);
//				Feedback feedback9 = new Feedback(3, employee2);
//				Feedback feedback10 = new Feedback(4, employee2);
//				Feedback feedback11 = new Feedback(1, employee2);
//				Feedback feedback12 = new Feedback(3, employee2);
//				Feedback feedback13 = new Feedback(4, employee2);
//				feedbackRepository.save(feedback1);
//				feedbackRepository.save(feedback2);
//				feedbackRepository.save(feedback3);
//				feedbackRepository.save(feedback4);
//				feedbackRepository.save(feedback5);
//				feedbackRepository.save(feedback6);
//				feedbackRepository.save(feedback7);
//				feedbackRepository.save(feedback8);
//				feedbackRepository.save(feedback9);
//				feedbackRepository.save(feedback10);
//				feedbackRepository.save(feedback11);
//				feedbackRepository.save(feedback12);
//				feedbackRepository.save(feedback13);
//
//			}
        };
    }
}
