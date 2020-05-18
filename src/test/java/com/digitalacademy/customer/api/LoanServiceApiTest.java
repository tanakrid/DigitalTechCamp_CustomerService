package com.digitalacademy.customer.api;

import com.digitalacademy.api.LoanApi;
import com.digitalacademy.model.response.GetLoanInfoResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class LoanServiceApiTest {

    @Mock
    RestTemplate restTemplate;

    @InjectMocks
    LoanApi loanApi;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        loanApi = new LoanApi(restTemplate);
    }

    public static ResponseEntity<String> prepareResponseSuccess() {
        return ResponseEntity.ok("{\n" +
                "    \"status\": {\n" +
                "        \"code\": \"0\",\n" +
                "        \"message\": \"success\"\n" +
                "    },\n" +
                "    \"data\": {\n" +
                "        \"id\": 1,\n" +
                "        \"status\": \"OK\",\n" +
                "        \"account_payable\": \"102-222-2200\",\n" +
                "        \"account_receivable\": \"102-333-2020\",\n" +
                "        \"principal_amount\": 1000000.0\n" +
                "    }\n" +
                "}");
    }
    public static ResponseEntity<String> prepareResponseLOAN4002() {
        return ResponseEntity.ok("{\n" +
                "    \"status\": {\n" +
                "        \"code\": \"LOAN4002\",\n" +
                "        \"message\": \"Loan information not found\"\n" +
                "    }\n" +
                "}");
    }
    public static ResponseEntity<String> prepareResponseLOAN4001() {
        return ResponseEntity.ok("{\n" +
                "    \"status\": {\n" +
                "        \"code\": \"LOAN4001\",\n" +
                "        \"message\": \"Cannot get loan information\"\n" +
                "    },\n" +
                "    \"data\": \"Cannot get loan information\"\n" +
                "}");
    }

    @DisplayName("Test get loan info should return loan information")
    @Test
    void testGetLoanInfo() throws Exception {
        Long reqId = 1L;

        when(restTemplate.exchange(
                ArgumentMatchers.<RequestEntity<String>>any(),
                ArgumentMatchers.<Class<String>>any()
        )).thenReturn(this.prepareResponseSuccess());

        GetLoanInfoResponse resp = loanApi.getLoanInfo(reqId);

        assertEquals("1", resp.getId().toString());
        assertEquals("OK", resp.getStatus());
        assertEquals("102-222-2200", resp.getAccountPayable().toString());
        assertEquals("102-333-2020", resp.getAccountReceivable().toString());
        assertEquals(1000000.0, resp.getPrincipalAmount());

        verify(restTemplate, times(1)).exchange(ArgumentMatchers.<RequestEntity<String>>any(),ArgumentMatchers.<Class<String>>any());
    }

    @DisplayName("Test get loan info should return LOAN4002")
    @Test
    void testGetLoanInfoLOAN4002() throws Exception{
        Long reqId = 2L;

        when(restTemplate.exchange(
                ArgumentMatchers.<RequestEntity<String>>any(),
                ArgumentMatchers.<Class<String>>any()
        )).thenReturn(this.prepareResponseLOAN4002());

        GetLoanInfoResponse resp = loanApi.getLoanInfo(reqId);

        assertEquals(null, resp.getId().toString());
        assertEquals(null, resp.getStatus());
        assertEquals(null, resp.getAccountPayable().toString());
        assertEquals(null, resp.getAccountReceivable().toString());
        assertEquals(0, resp.getPrincipalAmount());
    }

    @DisplayName("Test get loan info should return LOAN4001")
    @Test
    void testGetLoanInfoLOAN4001() throws Exception{
        Long reqId = 3L;

        when(restTemplate.exchange(
                ArgumentMatchers.<RequestEntity<String>>any(),
                ArgumentMatchers.<Class<String>>any()
        )).thenReturn(this.prepareResponseLOAN4001());

        GetLoanInfoResponse resp = loanApi.getLoanInfo(reqId);

        assertEquals(null, resp.getId().toString());
        assertEquals(null, resp.getStatus());
        assertEquals(null, resp.getAccountPayable().toString());
        assertEquals(null, resp.getAccountReceivable().toString());
        assertEquals(0, resp.getPrincipalAmount());
    }
}