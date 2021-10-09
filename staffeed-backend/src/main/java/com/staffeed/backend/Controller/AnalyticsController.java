package com.staffeed.backend.Controller;

import com.staffeed.backend.Payload.Response.FeedbackAnalyticsResponse;
import com.staffeed.backend.Repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<?> getFeedbackAnalytics() {
        List<Integer> listOfResponses = feedbackRepository.findAllDistinctResponses();
        Collections.sort(listOfResponses);
        int feedbackSize = feedbackRepository.findAll().size();

        List<FeedbackAnalyticsResponse> analyticsList = new ArrayList<>();
        for (Integer response : listOfResponses) {
            Long numOfOccurrences = feedbackRepository.countByResponse(response);
            double percentage = (double) numOfOccurrences / (double) feedbackSize;
            FeedbackAnalyticsResponse feedbackAnalyticsResponse = new FeedbackAnalyticsResponse(response, percentage);
            analyticsList.add(feedbackAnalyticsResponse);
        }

        return new ResponseEntity<>(analyticsList, HttpStatus.OK);
    }
}
