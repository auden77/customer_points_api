package com.assignment.pointsApi.mapper;

import com.assignment.pointsApi.model.Transaction;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@NoArgsConstructor
public class TransactionRowMapper implements RowMapper<Transaction> {

    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public Transaction mapRow(ResultSet rs, int rowNum) throws SQLException {
        Transaction transaction = new Transaction();

        transaction.setTransactionId(rs.getInt("TRANSACTION_ID"));
        transaction.setCustomerId(rs.getInt("CUSTOMER_ID"));
        transaction.setPurchaseAmount(rs.getFloat("PURCHASE_AMOUNT"));
        String timeStampString = rs.getString("TRANSACTION_TIME");
        LocalDateTime timeStamp  = LocalDateTime.parse(timeStampString, dateTimeFormatter);
        transaction.setTransactionTime(timeStamp);

        return transaction;

    }

}
