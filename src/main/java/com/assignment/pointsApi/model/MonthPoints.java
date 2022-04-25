package com.assignment.pointsApi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Month;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MonthPoints {
    private Month month;
    private Integer monthYear;
    private Float pointsValue;

}
