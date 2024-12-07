package com.interview.hcl.banking.mortgagesystem.controller;

import com.interview.hcl.banking.mortgagesystem.controller.PaymentController;
import com.interview.hcl.banking.mortgagesystem.dto.ErrorResponseDto;
import com.interview.hcl.banking.mortgagesystem.dto.ResponseDto;
import com.interview.hcl.banking.mortgagesystem.dto.TransactionDto;
import com.interview.hcl.banking.mortgagesystem.service.PaymentService;
import com.interview.hcl.banking.mortgagesystem.validation.FundTransferValidation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.validation.BindingResult;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class PaymentControllerTest {

    @InjectMocks
    private PaymentController paymentController;

    @Mock
    private PaymentService paymentService;

    @Mock
    private FundTransferValidation fundTransferValidation;

    @Mock
    private BindingResult bindingResult;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreatePaymentWithBindingErrors() {
        when(bindingResult.hasErrors()).thenReturn(true);
        List<String> errors = new ArrayList<>();
        errors.add("Invalid Input");
        when(bindingResult.getAllErrors()).thenReturn(List.of());

        ResponseDto response = paymentController.createPayment(new TransactionDto(), bindingResult);

        assertEquals("ERROR", response.getStatus());
        ErrorResponseDto errorResponse = (ErrorResponseDto) response.getErrors();
        assertEquals("FAILURE - Input", errorResponse.getMessage());
        verifyNoInteractions(paymentService, fundTransferValidation);
    }

    @Test
    void testCreatePaymentWithBusinessValidationErrors() {
        when(bindingResult.hasErrors()).thenReturn(false);

        List<String> businessErrors = new ArrayList<>();
        businessErrors.add("Insufficient funds");
        when(fundTransferValidation.checkValidation(any(TransactionDto.class))).thenReturn(businessErrors);

        ResponseDto response = paymentController.createPayment(new TransactionDto(), bindingResult);

        assertEquals("ERROR", response.getStatus());
        ErrorResponseDto errorResponse = (ErrorResponseDto) response.getErrors();
        assertEquals("FAILURE- BusinessValidation", errorResponse.getMessage());
        verifyNoInteractions(paymentService);
    }

    @Test
    void testCreatePayment_SuccessfulTransfer() {
        when(bindingResult.hasErrors()).thenReturn(false);

        when(fundTransferValidation.checkValidation(any(TransactionDto.class))).thenReturn(new ArrayList<>());

        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setFromAccountId(Long.valueOf("123"));
        transactionDto.setToAccountId(Long.valueOf("456"));
        transactionDto.setAmount(BigDecimal.valueOf(100.0));
        transactionDto.setDescription("Test transfer");
        when(paymentService.transferFunds(anyLong(), anyLong(), anyString(), any())).thenReturn(transactionDto);

        ResponseDto response = paymentController.createPayment(transactionDto, bindingResult);

        assertEquals("OK", response.getStatus());
        assertEquals(transactionDto, response.getData());
        assertEquals(null, response.getErrors());
    }


}

