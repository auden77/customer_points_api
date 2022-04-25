package com.assignment.pointsApi.controller;

import com.assignment.pointsApi.service.CustomerPointsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CustomerPointsController.class)
public class CustomerPointsControllerTest {

    @MockBean
    CustomerPointsService customerPointsService;

    @Autowired
    private MockMvc mvc;


    @Test
    public void callToRetrievePointsGives200()
            throws Exception {

        mvc.perform(
                        get("/customer-points/points-balance-last-three-months"))
                .andExpect(status().isOk());
    }

}
