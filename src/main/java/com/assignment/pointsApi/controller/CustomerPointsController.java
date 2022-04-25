package com.assignment.pointsApi.controller;

import com.assignment.pointsApi.model.CustomerPoints;
import com.assignment.pointsApi.model.Transaction;
import com.assignment.pointsApi.service.CustomerPointsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/customer-points")
public class CustomerPointsController {

    @Autowired
    CustomerPointsService customerPointsService;

    @GetMapping("/")
    public List<Transaction> getAll() {
        return customerPointsService.getAll();
    }

    @GetMapping("/points-balance-last-three-months")
    public ResponseEntity<List<CustomerPoints>> getPointsBalance() {
        return ResponseEntity.ok()
                .body(customerPointsService.getAllCustomersPointsEarnedLastThreeMonths());
    }

}
