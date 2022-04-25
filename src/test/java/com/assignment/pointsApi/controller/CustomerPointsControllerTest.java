package com.assignment.pointsApi.controller;

import com.assignment.pointsApi.model.CustomerPoints;
import com.assignment.pointsApi.model.MonthPoints;
import com.assignment.pointsApi.service.CustomerPointsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CustomerPointsController.class)
//@WithMockUser
public class CustomerPointsControllerTest {

    @MockBean
    CustomerPointsService customerPointsService;

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void callToRetrievePointsGives200()
            throws Exception {

        mockMvc.perform(
                        get("/customer-points/points-balance-last-three-months"))
                .andExpect(status().isOk());
    }


    @Test
    public void retrieveDetailsForCourse() throws Exception {
        List<MonthPoints> mockMonthPointsList = new ArrayList<>();
        MonthPoints mockMonthPoints1 = new MonthPoints(Month.APRIL, 2022, 123.45D);
        MonthPoints mockMonthPoints2 = new MonthPoints(Month.MAY, 2022, 678.90D);
        mockMonthPointsList.add(mockMonthPoints1);
        mockMonthPointsList.add(mockMonthPoints2);

        List<CustomerPoints> mockCustomerPointsList = new ArrayList<>();
        CustomerPoints mockCourse = new CustomerPoints(1, mockMonthPointsList, 0D);
        mockCustomerPointsList.add(mockCourse);

        Mockito.when(
                customerPointsService.getAllCustomersPointsEarnedLastThreeMonths()).thenReturn(mockCustomerPointsList);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/customer-points/points-balance-last-three-months").accept(
                MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        System.out.println(result.getResponse());
        String expected = "[" +
                "  {" +
                "    \"customerId\": 1," +
                "    \"monthPointsList\": [" +
                "      {" +
                "        \"month\": \"APRIL\"," +
                "        \"monthYear\": 2022," +
                "        \"pointsValue\": 123.45" +
                "      }," +
                "      {" +
                "        \"month\": \"MAY\"," +
                "        \"monthYear\": 2022," +
                "        \"pointsValue\": 678.90" +
                "      }" +
                "    ]," +
                "    \"totalPoints\": 802.35" +
                "  }" +
                "]";

        JSONAssert.assertEquals(expected, result.getResponse()
                .getContentAsString(), false);
    }

}

