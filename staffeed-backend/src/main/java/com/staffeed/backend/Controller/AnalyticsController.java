package com.staffeed.backend.Controller;

import com.staffeed.backend.Model.Employee;
import com.staffeed.backend.Model.Response;
import com.staffeed.backend.Payload.Response.AnalyticsResponse;
import com.staffeed.backend.Repository.ResponseRepository;
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
    private ResponseRepository responseRepository;

    @GetMapping("/responses")
    public ResponseEntity<?> getResponseAnalytics(@RequestParam String department) {
        List<Integer> listOfResponses = responseRepository.findAllDistinctResponses();
        Collections.sort(listOfResponses);

        int responseSize;
        double percentage;
        List<AnalyticsResponse> analyticsList = new ArrayList<>();
        if (department != null && !department.equals("")) {
            // get all responses from a department
//            System.out.println("==DEPARTMENT IS " + department + "==");
            List<Response> responsesByDepartment = responseRepository.findAllResponsesByDepartment(department);
            responseSize = responsesByDepartment.size();
            for (Integer response : listOfResponses) {
                long numOfOccurrences = responsesByDepartment
                                            .stream()
                                            .filter(res -> res.getResponse() == response)
                                            .filter(res -> {
                                                if (res.getUser() instanceof Employee employee) {
                                                    return employee.getDepartment().equals(department);
                                                }
                                                return false;
                                            })
                                            .count();

                if (responseSize > 0) {
                    percentage = (double) numOfOccurrences / (double) responseSize;
                } else {
                    percentage = 0.0;
                }

                AnalyticsResponse analyticsResponse = new AnalyticsResponse(response, percentage);
                analyticsList.add(analyticsResponse);
            }
        } else {
            // get all responses
//            System.out.println("==NO DEPARTMENT==");
            List<Response> responses = responseRepository.findAll();
            responseSize = responses.size();
            for (Integer response : listOfResponses) {
                long numOfOccurrences = responses.stream().filter(res -> res.getResponse() == response).count();

                if (responseSize > 0) {
                    percentage = (double) numOfOccurrences / (double) responseSize;
                } else {
                    percentage = 0.0;
                }

                AnalyticsResponse analyticsResponse = new AnalyticsResponse(response, percentage);
                analyticsList.add(analyticsResponse);
            }
        }

        return new ResponseEntity<>(analyticsList, HttpStatus.OK);
    }
}
