package com.assignment.pointsApi.service;

import com.assignment.pointsApi.model.CustomerPoints;
import com.assignment.pointsApi.model.MonthPoints;
import com.assignment.pointsApi.model.Transaction;
import com.assignment.pointsApi.Dao.TransactionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;

@Service
public class CustomerPointsService {

    @Autowired
    TransactionDao transactionDAO;

    public List<Transaction> getAll() {
        return transactionDAO.getAll();
    }

    public List<CustomerPoints> getAllCustomersPointsEarnedLastThreeMonths() {
        Map<Integer, List<MonthPoints>> monthPointsByCustomerId = new HashMap<>();
        List<CustomerPoints> listOfCustomerPointsObjects = new ArrayList<>();
        List<Month> monthList = new ArrayList<>();
        List<Integer> yearList = new ArrayList<>();
        initializeMonthList(monthList);
        initializeYearList(yearList);
        LocalDateTime firstDayOfTwoMonthsAgo = LocalDateTime.of(yearList.get(0), monthList.get(0), 1, 0, 0);

        List<Transaction> transactionList = transactionDAO.getAllSinceTimestamp(firstDayOfTwoMonthsAgo.toString());

        Set<Integer> customerIdSet = new TreeSet<>();
        initializeCustomerIdSet(transactionList, customerIdSet);
        List<Integer> customerList = new ArrayList<Integer>(customerIdSet);

        mapCustomerPoints(monthList, yearList, transactionList, customerList, monthPointsByCustomerId);

        createListOfCustomerPointsObjects(customerList, monthPointsByCustomerId, listOfCustomerPointsObjects);

        return listOfCustomerPointsObjects;
    }

    private void initializeMonthList(List<Month> monthList) {
        for (int i = 2; i >= 0; i--) {
            Month month = LocalDateTime.now().minusMonths(i).getMonth();
            monthList.add(month);
        }
    }

    private void initializeYearList(List<Integer> yearList) {
        for (int i = 2; i >= 0; i--) {
            int year = LocalDateTime.now().minusMonths(i).getYear();
            yearList.add(year);
        }
    }

    private void initializeCustomerIdSet(List<Transaction> transactionList, Set<Integer> customerIdSet) {
        for (Transaction transaction : transactionList) {
            int customerId = transaction.getCustomerId();
            customerIdSet.add(customerId);
        }
    }

    private void mapCustomerPoints(List<Month> monthList, List<Integer> yearList, List<Transaction> transactionList, List<Integer> customerList, Map<Integer, List<MonthPoints>> monthPointsByCustomerId) {
        for (Integer customer : customerList) {
            List<MonthPoints> monthPointsList = new ArrayList<>();
            for (int i = 0; i < 3; i++) {
                Month month = monthList.get(i);
                float pointsValue = transactionList.stream()
                        .filter(item -> item.getTransactionTime().getMonth() == month)
                        .filter(item -> item.getCustomerId() == customer)
                        .map(item -> item.getPurchaseAmount())
                        .map(item -> getTransactionPoints(item))
                        .reduce(0.0F, Float::sum);
                monthPointsList.add(new MonthPoints(monthList.get(i), yearList.get(i), pointsValue));
            }
            monthPointsByCustomerId.put(customer, monthPointsList);
        }
    }

    private void createListOfCustomerPointsObjects(List<Integer> customerList, Map<Integer, List<MonthPoints>> monthPointsByCustomerId, List<CustomerPoints> listOfCustomerPointsObjects) {
        for (Integer customer : customerList) {
            List<MonthPoints> monthPoints = monthPointsByCustomerId.get(customer);
            CustomerPoints customerPoints = new CustomerPoints(customer, monthPoints, 0.0F);
            listOfCustomerPointsObjects.add(customerPoints);
        }
    }

    private float getTransactionPoints(float transactionAmt) {
        float total = 0.0F;
        if (transactionAmt > 50.00 && transactionAmt < 100.00) {
            total += transactionAmt - 50.00;
        }
        if (transactionAmt > 100.00) {
            total += 50.00;
            total += (transactionAmt - 100.00) * 2;
        }
        return total;
    }
}
