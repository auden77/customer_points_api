package com.assignment.pointsApi.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CustomerPointsUtil {

    public static BigDecimal roundToTwoPlaces(BigDecimal unformattedBD) {
        return unformattedBD.setScale(2, RoundingMode.HALF_UP);

    }

    public static BigDecimal getTransactionPoints(Float transactionAmt) {
        BigDecimal total = BigDecimal.valueOf(0);
        BigDecimal bigDecimalTransactionAmt = new BigDecimal(Double.toString(transactionAmt));
        BigDecimal singleFloor = BigDecimal.valueOf(50);
        BigDecimal doubleFloor = BigDecimal.valueOf(100);

        if (bigDecimalTransactionAmt.compareTo(doubleFloor) == 0) {
            total = BigDecimal.valueOf(50);
        } else if (bigDecimalTransactionAmt.compareTo(singleFloor) == 1
                && bigDecimalTransactionAmt.compareTo(doubleFloor) == -1) {
            total = total.add(bigDecimalTransactionAmt.subtract(singleFloor));
        } else if (bigDecimalTransactionAmt.compareTo(doubleFloor) == 1) {
            total = total.add(singleFloor);
            BigDecimal doublePoints = (bigDecimalTransactionAmt.subtract(doubleFloor))
                    .multiply(BigDecimal.valueOf(2));
            total = total.add(doublePoints);
        }
        return total.setScale(2, RoundingMode.HALF_UP);
    }

}
