package com.staffeed.backend.Controller;

import com.staffeed.backend.Model.Category;
import com.staffeed.backend.Model.Employee;
import com.staffeed.backend.Model.Question;
import com.staffeed.backend.Model.Response;
import com.staffeed.backend.Payload.Response.*;
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
        List<Question> listOfQuestions = questionRepository.getAllQuestionsByLatest();
        System.out.println(listOfQuestions);
//        List<Integer> listOfResponses = responseRepository.findAllDistinctResponses();
//        Collections.sort(listOfResponses);

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
        List<Question> listOfQuestions = questionRepository.getAllQuestionsByLatest();
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
        List<String> listOfCategories = Stream.of(Category.values())
                .map(Category::name)
                .collect(Collectors.toList());
        Collections.sort(listOfCategories);
        System.out.println(listOfCategories);

        List<QuestionAnalyticsCategoryResponse> list = new ArrayList<>();
        for (String category : listOfCategories) {
            double categoryAverage = 0.0;
            double categoryTotal = 0.0;
            double percentage;
            List<QuestionAnalyticsResponse> questionAnalyticsResponseList = new ArrayList<>();

            List<Question> listOfQuestions = questionRepository.findByCategory(Category.valueOf(category));
            for (Question q : listOfQuestions) {
                double questionAverage = 0.0;
                List<ResponseAnalyticsResponse> responseAnalyticsList = new ArrayList<>();
                List<String> listOfChoices = Arrays.asList(q.getOptions());

                int totalNumResponses = q.getResponses().size();
                for (String choice : listOfChoices) {
                    int choiceNum = listOfChoices.indexOf(choice) + 1;
                    long numResponses = q.getResponses()
                            .stream()
                            .filter(response -> response.getResponse().equals(choice)).count();

                    if (totalNumResponses > 0) {
                        percentage = (double) numResponses / (double) totalNumResponses;
                    } else {
                        percentage = 0.0;
                    }

                    questionAverage += (percentage * choiceNum);
                    ResponseAnalyticsResponse responseAnalyticsResponse = new ResponseAnalyticsResponse(choiceNum, choice, percentage);
                    responseAnalyticsList.add(responseAnalyticsResponse);
                }
                categoryTotal += questionAverage;
                QuestionAnalyticsResponse questionAnalyticsResponse = new QuestionAnalyticsResponse(q, questionAverage, responseAnalyticsList);
                questionAnalyticsResponseList.add(questionAnalyticsResponse);
            }

            if (listOfQuestions.size() > 0) {
                categoryAverage = categoryTotal / listOfQuestions.size();
                QuestionAnalyticsCategoryResponse obj = new QuestionAnalyticsCategoryResponse(Category.valueOf(category), categoryAverage, questionAnalyticsResponseList);
                list.add(obj);
            } else {
                QuestionAnalyticsCategoryResponse obj = new QuestionAnalyticsCategoryResponse(Category.valueOf(category), questionAnalyticsResponseList);
                list.add(obj);
            }

        }

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/response-rate")
    public ResponseEntity<?> getResponseRate() {
        List<Question> listOfQuestions = questionRepository.findAll();
        long totalNumEmployees = userRepository.findAll().stream().filter(user -> user instanceof Employee).count();

        List<QuestionResponseRateResponse> questionResponseRateResponseList = new ArrayList<>();
        for (Question q : listOfQuestions) {
            int responseListSize = responseRepository.findResponsesWithDistinctRespondents(q.getId()).size();
            double responseRate = (double) responseListSize / (double) totalNumEmployees;
            QuestionResponseRateResponse obj = new QuestionResponseRateResponse(responseRate, q);
            questionResponseRateResponseList.add(obj);
        }

        OverallResponseRateResponse resObj = new OverallResponseRateResponse((int) totalNumEmployees, questionResponseRateResponseList);
        return new ResponseEntity<>(resObj, HttpStatus.OK);
    }
}
