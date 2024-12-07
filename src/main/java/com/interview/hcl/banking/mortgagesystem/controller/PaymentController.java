package com.interview.hcl.banking.mortgagesystem.controller;

import com.interview.hcl.banking.mortgagesystem.dto.ErrorResponseDto;
import com.interview.hcl.banking.mortgagesystem.dto.ResponseDto;
import com.interview.hcl.banking.mortgagesystem.dto.TransactionDto;
import com.interview.hcl.banking.mortgagesystem.service.PaymentService;
import com.interview.hcl.banking.mortgagesystem.validation.FundTransferValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Used to handle the payment System
 */
@RestController
@RequestMapping("/api/banking/mortgage/")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private FundTransferValidation fundTransferValidation;

    @PostMapping("/createPayment")
    public ResponseDto createPayment(@RequestBody @Validated TransactionDto transactionDto, BindingResult bindingResult) {
        try {
            List<String> errorMessage = new ArrayList<>();
            bindingResult.getAllErrors().forEach(fieldError -> {
                errorMessage.add(fieldError.getDefaultMessage() + ": " + fieldError.getDefaultMessage());
            });
            ResponseDto response = new ResponseDto();

            if (bindingResult.hasErrors()) {
                Map<String, List<String>> errors = new HashMap<>();
                errors.put("FieldError", errorMessage);
                response = new ResponseDto("ERROR", null, new ErrorResponseDto("FAILURE - Input", errors));
                return response;
            }

            //Validate BusinessValidation
            List<String> errorsBusinessValidation = fundTransferValidation.checkValidation(transactionDto);
            if (!errorsBusinessValidation.isEmpty()) {
                Map<String, List<String>> errors = new HashMap<>();
                errors.put("BusinessError", errorsBusinessValidation);
                response = new ResponseDto("ERROR", new TransactionDto(), new ErrorResponseDto("FAILURE- BusinessValidation", errors));
                return response;
            }

            transactionDto = paymentService.transferFunds(transactionDto.getFromAccountId(), transactionDto.getToAccountId(), transactionDto.getDescription(), transactionDto.getAmount());
            if (transactionDto != null) {
                response = new ResponseDto("OK", transactionDto, null);
            } else {
                response = new ResponseDto("ERROR", new TransactionDto(), new ErrorResponseDto("FAILURE", null));
            }
            return response;
        }catch (Exception e) {
            ResponseDto response = new ResponseDto();
            Map<String, List<String>> errors = new HashMap<>();
            List<String> exceptionMessage = new ArrayList<>();
            exceptionMessage.add(e.getMessage());
            errors.put("404",exceptionMessage );
            response = new ResponseDto("ERROR", new TransactionDto(), new ErrorResponseDto("ExceptionOccured", errors));
            return response;

        }
    }

}
