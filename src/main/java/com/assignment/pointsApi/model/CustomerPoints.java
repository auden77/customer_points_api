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
    private Float totalPoints;

    public Float getTotalPoints() {
        Float tally = 0.0F;
        for (MonthPoints mp : monthPointsList) {
            tally += mp.getPointsValue();
        }
        return tally;
    }

    public void setTotalPoints(Float totalPoints) {
        this.totalPoints = totalPoints;
    }
}
