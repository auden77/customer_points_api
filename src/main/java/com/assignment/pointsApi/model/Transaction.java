package com.assignment.pointsApi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {

    private Integer transactionId;
    private Integer customerId;
    private float purchaseAmount;
    private LocalDateTime transactionTime;
    private Integer poIntegers;

}
