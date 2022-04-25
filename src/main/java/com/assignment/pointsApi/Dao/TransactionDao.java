package com.assignment.pointsApi.Dao;

import com.assignment.pointsApi.mapper.TransactionRowMapper;
import com.assignment.pointsApi.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TransactionDao {

    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final String GET_ALL = "SELECT * FROM TRANSACTION";
    private final String GET_ALL_FOR_LAST_NUM_MONTHS = "SELECT * FROM TRANSACTION WHERE TRANSACTION_TIME > :firstMonth ORDER BY CUSTOMER_ID ASC";

    public List<Transaction> getAll() {
        try {
            return namedParameterJdbcTemplate.query(GET_ALL, new TransactionRowMapper());
        } catch (Exception e) {
            throw new IllegalStateException("Error getting TRANSACTION row", e);
        }
    }

    public List<Transaction> getAllSinceTimestamp(String firstMonth) {
        try {
            MapSqlParameterSource params = new MapSqlParameterSource();
            params.addValue("firstMonth", firstMonth);
            return namedParameterJdbcTemplate.query(GET_ALL_FOR_LAST_NUM_MONTHS, params, new TransactionRowMapper());
        } catch (Exception e) {
            throw new IllegalStateException("Error getting TRANSACTION row", e);
        }
    }

}
