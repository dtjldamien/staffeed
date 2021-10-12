package com.staffeed.backend.Controller;

import com.staffeed.backend.Model.Employee;
import com.staffeed.backend.Model.Feedback;
import com.staffeed.backend.Payload.Response.FeedbackAnalyticsResponse;
import com.staffeed.backend.Repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/analytics")
public class AnalyticsController {

    @Autowired
    private FeedbackRepository feedbackRepository;

    @GetMapping("/feedbacks")
    public ResponseEntity<?> getFeedbackAnalytics(@RequestParam String department) {
        List<Integer> listOfResponses = feedbackRepository.findAllDistinctResponses();
        Collections.sort(listOfResponses);

        int feedbackSize;
        double percentage;
        List<FeedbackAnalyticsResponse> analyticsList = new ArrayList<>();
        if (department != null && !department.equals("")) {
            // get all feedbacks from a department
//            System.out.println("==DEPARTMENT IS " + department + "==");
            List<Feedback> feedbacksByDepartment = feedbackRepository.findAllFeedbacksByDepartment(department);
            feedbackSize = feedbacksByDepartment.size();
            for (Integer response : listOfResponses) {
                long numOfOccurrences = feedbacksByDepartment
                                            .stream()
                                            .filter(feedback -> feedback.getResponse() == response)
                                            .filter(feedback -> {
                                                if (feedback.getUser() instanceof Employee employee) {
                                                    return employee.getDepartment().equals(department);
                                                }
                                                return false;
                                            })
                                            .count();

                if (feedbackSize > 0) {
                    percentage = (double) numOfOccurrences / (double) feedbackSize;
                } else {
                    percentage = 0.0;
                }

                FeedbackAnalyticsResponse feedbackAnalyticsResponse = new FeedbackAnalyticsResponse(response, percentage);
                analyticsList.add(feedbackAnalyticsResponse);
            }
        } else {
            // get all feedbacks
//            System.out.println("==NO DEPARTMENT==");
            List<Feedback> feedbacks = feedbackRepository.findAll();
            feedbackSize = feedbacks.size();
            for (Integer response : listOfResponses) {
                long numOfOccurrences = feedbacks.stream().filter(feedback -> feedback.getResponse() == response).count();

                if (feedbackSize > 0) {
                    percentage = (double) numOfOccurrences / (double) feedbackSize;
                } else {
                    percentage = 0.0;
                }

                FeedbackAnalyticsResponse feedbackAnalyticsResponse = new FeedbackAnalyticsResponse(response, percentage);
                analyticsList.add(feedbackAnalyticsResponse);
            }
        }

        return new ResponseEntity<>(analyticsList, HttpStatus.OK);
    }
}
