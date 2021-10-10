package com.staffeed.backend;

import com.staffeed.backend.Model.Employee;
import com.staffeed.backend.Model.Feedback;
import com.staffeed.backend.Model.User;
import com.staffeed.backend.Repository.EmployeeRepository;
import com.staffeed.backend.Repository.FeedbackRepository;
import com.staffeed.backend.Repository.UserRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;


@SpringBootApplication
public class BackendApplication {

	@Autowired
	PasswordEncoder encoder;
	@Autowired
	UserRepository userRepository;
	@Autowired
	EmployeeRepository employeeRepository;
	@Autowired
	FeedbackRepository feedbackRepository;


	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	@Bean
	public InitializingBean sendDatabase() {
		return () -> {
			String password = encoder.encode("password");
			if (employeeRepository.findAll().size() < 1) {
				// creating employee's account
				Employee employee1 = new Employee("employee1", password, "employee1@user.com", "investment");
				Employee employee2 = new Employee("employee2", password, "employee2@user.com", "technology");
				employeeRepository.save(employee1);
				employeeRepository.save(employee2);

				// creating HR's account
				User hr = new User("hr", password, "hr@user.com");
				userRepository.save(hr);

				// Creating feedbacks
				Feedback feedback1 = new Feedback(1, employee1);
				Feedback feedback2 = new Feedback(1, employee1);
				Feedback feedback3 = new Feedback(2, employee1);
				Feedback feedback4 = new Feedback(2, employee1);
				Feedback feedback5 = new Feedback(3, employee1);
				Feedback feedback6 = new Feedback(2, employee1);
				Feedback feedback7 = new Feedback(2, employee1);
				Feedback feedback8 = new Feedback(3, employee1);
				Feedback feedback9 = new Feedback(3, employee2);
				Feedback feedback10 = new Feedback(4, employee2);
				Feedback feedback11 = new Feedback(1, employee2);
				Feedback feedback12 = new Feedback(3, employee2);
				Feedback feedback13 = new Feedback(4, employee2);
				feedbackRepository.save(feedback1);
				feedbackRepository.save(feedback2);
				feedbackRepository.save(feedback3);
				feedbackRepository.save(feedback4);
				feedbackRepository.save(feedback5);
				feedbackRepository.save(feedback6);
				feedbackRepository.save(feedback7);
				feedbackRepository.save(feedback8);
				feedbackRepository.save(feedback9);
				feedbackRepository.save(feedback10);
				feedbackRepository.save(feedback11);
				feedbackRepository.save(feedback12);
				feedbackRepository.save(feedback13);

			}
		};
	}

}
