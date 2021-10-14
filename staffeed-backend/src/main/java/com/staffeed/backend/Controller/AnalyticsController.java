package com.staffeed.backend.Controller;

import com.staffeed.backend.Model.Question;
import com.staffeed.backend.Payload.Response.QuestionAnalyticsResponse;
import com.staffeed.backend.Payload.Response.ResponseAnalyticsResponse;
import com.staffeed.backend.Repository.QuestionRepository;
import com.staffeed.backend.Repository.ResponseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/analytics")
public class AnalyticsController {

    @Autowired
    private ResponseRepository responseRepository;
    @Autowired
    private QuestionRepository questionRepository;

    @GetMapping("/responses")
    public ResponseEntity<?> getResponseAnalytics(@RequestParam(required = false) String department) {
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
//        if (department != null && !department.equals("")) {
//            // get all responses from a department
////            System.out.println("==DEPARTMENT IS " + department + "==");
//            List<Response> responsesByDepartment = responseRepository.findAllResponsesByDepartment(department);
//            responseSize = responsesByDepartment.size();
//            for (Integer response : listOfResponses) {
//                long numOfOccurrences = responsesByDepartment
//                                            .stream()
//                                            .filter(res -> res.getResponse() == response)
//                                            .filter(res -> {
//                                                if (res.getUser() instanceof Employee employee) {
//                                                    return employee.getDepartment().equals(department);
//                                                }
//                                                return false;
//                                            })
//                                            .count();
//
//                if (responseSize > 0) {
//                    percentage = (double) numOfOccurrences / (double) responseSize;
//                } else {
//                    percentage = 0.0;
//                }
//
//                AnalyticsResponse analyticsResponse = new AnalyticsResponse(response, percentage);
//                analyticsList.add(analyticsResponse);
//            }
//        } else {
//            // get all responses
////            System.out.println("==NO DEPARTMENT==");
//            List<Response> responses = responseRepository.findAll();
//            responseSize = responses.size();
//            for (Integer response : listOfResponses) {
//                long numOfOccurrences = responses.stream().filter(res -> res.getResponse() == response).count();
//
//                if (responseSize > 0) {
//                    percentage = (double) numOfOccurrences / (double) responseSize;
//                } else {
//                    percentage = 0.0;
//                }
//
//                AnalyticsResponse analyticsResponse = new AnalyticsResponse(response, percentage);
//                analyticsList.add(analyticsResponse);
//            }
//        }

        return new ResponseEntity<>(questionAnalyticsResponseList, HttpStatus.OK);
    }
}
