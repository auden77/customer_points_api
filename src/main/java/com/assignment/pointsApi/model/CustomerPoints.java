package com.assignment.pointsApi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerPoints {
    private Integer customerId;
    private List<MonthPoints> monthPointsList = new ArrayList<>();
    private Double totalPoints;

    public Double getTotalPoints() {
        Double tally = 0.0D;
        for (MonthPoints mp : monthPointsList) {
            tally += mp.getPointsValue();
        }
        return tally;
    }

    public void setTotalPoints(Double totalPoints) {
        this.totalPoints = totalPoints;
    }
}
