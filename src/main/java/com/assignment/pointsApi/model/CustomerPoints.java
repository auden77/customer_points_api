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
        BigDecimal tally = BigDecimal.valueOf(0);
        for (MonthPoints mp : monthPointsList) {
            tally = tally.add(new BigDecimal(mp.getPointsValue().toString()));
        }
        return tally.setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    public void setTotalPoints(Double totalPoints) {
        this.totalPoints = totalPoints;
    }
}
