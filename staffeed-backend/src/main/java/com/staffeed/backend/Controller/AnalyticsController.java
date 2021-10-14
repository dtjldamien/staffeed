package com.staffeed.backend.Controller;

import com.staffeed.backend.Model.Category;
import com.staffeed.backend.Model.Employee;
import com.staffeed.backend.Model.Question;
import com.staffeed.backend.Payload.Response.QuestionAnalyticsDepartmentCategory;
import com.staffeed.backend.Payload.Response.QuestionAnalyticsDepartmentResponse;
import com.staffeed.backend.Payload.Response.QuestionAnalyticsResponse;
import com.staffeed.backend.Payload.Response.ResponseAnalyticsResponse;
import com.staffeed.backend.Repository.QuestionRepository;
import com.staffeed.backend.Repository.ResponseRepository;
import com.staffeed.backend.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@CrossOrigin
@RequestMapping("/analytics")
public class AnalyticsController {

    @Autowired
    private ResponseRepository responseRepository;
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/responses")
    public ResponseEntity<?> getResponseAnalytics() {
        List<Question> listOfQuestions = questionRepository.findAll();
        System.out.println(listOfQuestions);
        List<Integer> listOfResponses = responseRepository.findAllDistinctResponses();
        Collections.sort(listOfResponses);

        double average = 0.0;
        double percentage;
        List<QuestionAnalyticsResponse> questionAnalyticsResponseList = new ArrayList<>();

        // loop through list of questions
        for (Question q : listOfQuestions) {
            average = 0.0;
            List<ResponseAnalyticsResponse> responseAnalyticsList = new ArrayList<>();
            List<String> listOfChoices = Arrays.asList(q.getOptions());
            int totalNumResponses = q.getResponses().size();
            for (String choice : listOfChoices) {
                int choiceNum = listOfChoices.indexOf(choice) + 1;
                long numResponses = q.getResponses().stream().filter(response -> response.getResponse().equals(choice)).count();

                if (totalNumResponses > 0) {
                    percentage = (double) numResponses / (double) totalNumResponses;
                } else {
                    percentage = 0.0;
                }

                average += (percentage * choiceNum);
                ResponseAnalyticsResponse responseAnalyticsResponse = new ResponseAnalyticsResponse(choiceNum, choice, percentage);
                responseAnalyticsList.add(responseAnalyticsResponse);
            }

            QuestionAnalyticsResponse questionAnalyticsResponse = new QuestionAnalyticsResponse(q, average, responseAnalyticsList);
            questionAnalyticsResponseList.add(questionAnalyticsResponse);
        }

        return new ResponseEntity<>(questionAnalyticsResponseList, HttpStatus.OK);
    }

    @GetMapping("/responses/department")
    public ResponseEntity<?> getResponseAnalyticsByDepartment() {
        List<Question> listOfQuestions = questionRepository.findAll();
        List<String> listOfDepartments = userRepository.getAllDepartments();
        Collections.sort(listOfDepartments);
        System.out.println(listOfDepartments);

        List<QuestionAnalyticsDepartmentResponse> list = new ArrayList<>();
        for (String department : listOfDepartments) {
            double average = 0.0;
            double percentage;
            List<QuestionAnalyticsResponse> questionAnalyticsResponseList = new ArrayList<>();

            for (Question q : listOfQuestions) {
                average = 0.0;
                List<ResponseAnalyticsResponse> responseAnalyticsList = new ArrayList<>();
                List<String> listOfChoices = Arrays.asList(q.getOptions());

                int totalNumResponses = q.getResponses().size();
                for (String choice : listOfChoices) {
                    int choiceNum = listOfChoices.indexOf(choice) + 1;
                    long numResponses = q.getResponses()
                                            .stream()
                                            .filter(res -> {
                                                if (res.getUser() instanceof Employee employee) {
                                                  return employee.getDepartment().equals(department);
                                                }
                                                return false;
                                            })
                                            .filter(response -> response.getResponse().equals(choice)).count();

                    if (totalNumResponses > 0) {
                        percentage = (double) numResponses / (double) totalNumResponses;
                    } else {
                        percentage = 0.0;
                    }

                    average += (percentage * choiceNum);
                    ResponseAnalyticsResponse responseAnalyticsResponse = new ResponseAnalyticsResponse(choiceNum, choice, percentage);
                    responseAnalyticsList.add(responseAnalyticsResponse);
                }

                QuestionAnalyticsResponse questionAnalyticsResponse = new QuestionAnalyticsResponse(q, average, responseAnalyticsList);
                questionAnalyticsResponseList.add(questionAnalyticsResponse);
            }

            QuestionAnalyticsDepartmentResponse obj = new QuestionAnalyticsDepartmentResponse(department, questionAnalyticsResponseList);
            list.add(obj);
        }

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/responses/categories")
    public ResponseEntity<?> getResponseAnalyticsByCategories() {
        List<Question> listOfQuestions = questionRepository.findAll();
        List<String> listOfCategories = Stream.of(Category.values())
                .map(Category::name)
                .collect(Collectors.toList());
//        Collections.sort(listOfDepartments);
        System.out.println(listOfCategories);

        List<QuestionAnalyticsDepartmentCategory> list = new ArrayList<>();
//        for (String category : listOfCategories) {
//            double average = 0.0;
//            double percentage;
//            List<QuestionAnalyticsResponse> questionAnalyticsResponseList = new ArrayList<>();
//
//            for (Question q : listOfQuestions) {
//                average = 0.0;
//                List<ResponseAnalyticsResponse> responseAnalyticsList = new ArrayList<>();
//                List<String> listOfChoices = Arrays.asList(q.getOptions());
//
//                int totalNumResponses = q.getResponses().size();
//                for (String choice : listOfChoices) {
//                    int choiceNum = listOfChoices.indexOf(choice) + 1;
//                    long numResponses = q.getResponses()
//                            .stream()
//                            .filter(res -> {
//                                if (res.getUser() instanceof Employee employee) {
//                                    return employee.getDepartment().equals(department);
//                                }
//                                return false;
//                            })
//                            .filter(response -> response.getResponse().equals(choice)).count();
//
//                    if (totalNumResponses > 0) {
//                        percentage = (double) numResponses / (double) totalNumResponses;
//                    } else {
//                        percentage = 0.0;
//                    }
//
//                    average += (percentage * choiceNum);
//                    ResponseAnalyticsResponse responseAnalyticsResponse = new ResponseAnalyticsResponse(choiceNum, choice, percentage);
//                    responseAnalyticsList.add(responseAnalyticsResponse);
//                }
//
//                QuestionAnalyticsResponse questionAnalyticsResponse = new QuestionAnalyticsResponse(q, average, responseAnalyticsList);
//                questionAnalyticsResponseList.add(questionAnalyticsResponse);
//            }
//
//            QuestionAnalyticsDepartmentResponse obj = new QuestionAnalyticsDepartmentResponse(department, questionAnalyticsResponseList);
//            list.add(obj);
//        }

        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
