package com.assignment.pointsApi.util;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.*;

class CustomerPointsUtilTest {

    @Test
    void getTransactionPoints_for_Zero_results_Zero() {
        Float testValue = 0F;
        BigDecimal expected = BigDecimal.valueOf(0).setScale(2, RoundingMode.HALF_UP);;
        BigDecimal result = CustomerPointsUtil.getTransactionPoints(Float.valueOf(testValue));

        assertEquals(expected, result);
    }

    @Test
    void getTransactionPoints_for_One_results_Zero() {
        Float testValue = 1F;
        BigDecimal expected = BigDecimal.valueOf(0).setScale(2, RoundingMode.HALF_UP);;
        BigDecimal result = CustomerPointsUtil.getTransactionPoints(Float.valueOf(testValue));

        assertEquals(expected, result);
    }

    @Test
    void getTransactionPoints_for_Two_results_Zero() {
        Float testValue = 2F;
        BigDecimal expected = BigDecimal.valueOf(0).setScale(2, RoundingMode.HALF_UP);;
        BigDecimal result = CustomerPointsUtil.getTransactionPoints(Float.valueOf(testValue));

        assertEquals(expected, result);
    }

    @Test
    void getTransactionPoints_for_ThirtyPointThreeZero_results_Zero() {
        Float testValue = 30.30F;
        BigDecimal expected = BigDecimal.valueOf(0).setScale(2, RoundingMode.HALF_UP);;
        BigDecimal result = CustomerPointsUtil.getTransactionPoints(Float.valueOf(testValue));

        assertEquals(expected, result);
    }

    @Test
    void getTransactionPoints_for_FiftyPointZero_results_Zero() {
        Float testValue = 50.0F;
        BigDecimal expected = BigDecimal.valueOf(0).setScale(2, RoundingMode.HALF_UP);;
        BigDecimal result = CustomerPointsUtil.getTransactionPoints(Float.valueOf(testValue));

        assertEquals(expected, result);
    }

    @Test
    void getTransactionPoints_for_FiftyPointZeroThree_results_ZeroPointZeroThree() {
        Float testValue = 50.03F;
        Float expectedInputValue = 0.03F;
        BigDecimal expected = BigDecimal.valueOf(expectedInputValue).setScale(2, RoundingMode.HALF_UP);;
        BigDecimal result = CustomerPointsUtil.getTransactionPoints(Float.valueOf(testValue));

        assertEquals(expected, result);
    }

    @Test
    void getTransactionPoints_for_EightyOnePointTwentySeven_results_ThirtyOnePointTwentySeven() {
        Float testValue = 81.27F;
        Float expectedInputValue = 31.27F;
        BigDecimal expected = BigDecimal.valueOf(expectedInputValue).setScale(2, RoundingMode.HALF_UP);;
        BigDecimal result = CustomerPointsUtil.getTransactionPoints(Float.valueOf(testValue));

        assertEquals(expected, result);
    }

    @Test
    void getTransactionPoints_for_OneHundredPointZero_results_Fifty() {
        Float testValue = 100.0F;
        Float expectedInputValue = 50F;
        BigDecimal expected = BigDecimal.valueOf(expectedInputValue).setScale(2, RoundingMode.HALF_UP);;
        BigDecimal result = CustomerPointsUtil.getTransactionPoints(Float.valueOf(testValue));

        assertEquals(expected, result);
    }

    @Test
    void getTransactionPoints_for_OneHundredPointZeroOne_results_FiftyPointZeroTwo() {
        Float testValue = 100.01F;
        Float expectedInputValue = 50.02F;
        BigDecimal expected = BigDecimal.valueOf(expectedInputValue).setScale(2, RoundingMode.HALF_UP);;
        BigDecimal result = CustomerPointsUtil.getTransactionPoints(Float.valueOf(testValue));

        assertEquals(expected, result);
    }

    @Test
    void getTransactionPoints_for_OneHundredEightyOnePointTwentySeven_results_TwoHundredTwelvePointFiftyFour() {
        Float testValue = 181.27F;
        Float expectedInputValue = 212.54F;
        BigDecimal expected = BigDecimal.valueOf(expectedInputValue).setScale(2, RoundingMode.HALF_UP);;
        BigDecimal result = CustomerPointsUtil.getTransactionPoints(Float.valueOf(testValue));

        assertEquals(expected, result);
    }
}