package com.digitalacademy.customer.controller;

import com.digitalacademy.api.LoanApi;
import com.digitalacademy.controller.LoanController;
import com.digitalacademy.model.response.GetLoanInfoResponse;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class LoanControllerTest {
    @Mock
    LoanApi loanApi;

    @InjectMocks
    LoanController loanController;

    private MockMvc mvc;

    public static final String urlCustomer = "/loan/";

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        loanController = new LoanController(loanApi);
        mvc = MockMvcBuilders.standaloneSetup(loanController).build();
    }

    public static GetLoanInfoResponse getResponse(){
        GetLoanInfoResponse getLoanInfoResponse = new GetLoanInfoResponse();
        getLoanInfoResponse.setId(1L);
        getLoanInfoResponse.setAccountPayable("102-222-2200");
        getLoanInfoResponse.setAccountReceivable("102-333-2020");
        getLoanInfoResponse.setPrincipalAmount(1000000.00);
        getLoanInfoResponse.setStatus("0");
        return getLoanInfoResponse;
    }

    @DisplayName("Test get loan info should return loan information")
    @Test
    void testGetLoanInfo() throws Exception{
        Long reqId = 1L;

        when(loanApi.getLoanInfo(reqId)).thenReturn(getResponse());

        MvcResult mvcResult = mvc.perform(get(urlCustomer + "/" +reqId)).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andReturn();

        JSONObject json = new JSONObject(mvcResult.getResponse().getContentAsString());
        assertEquals("1", json.get("id").toString());
        assertEquals("0", json.get("status").toString());
        assertEquals("102-222-2200", json.get("account_payable").toString());
        assertEquals("102-333-2020", json.get("account_receivable").toString());
        assertEquals(1000000.00, json.get("principal_amount"));
    }

//    @DisplayName("Test get loan info ")
}
