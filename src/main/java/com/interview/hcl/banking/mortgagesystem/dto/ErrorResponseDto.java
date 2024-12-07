package com.interview.hcl.banking.mortgagesystem.dto;

import java.util.List;
import java.util.Map;

public class ErrorResponseDto {

    private String message;
    private Map<String , List<String>> errorMessages;

    public ErrorResponseDto(String message, Map<String , List<String>> errorMessages) {
        this.message=message;
        this.errorMessages=errorMessages;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, List<String>> getErrorMessages() {
        return errorMessages;
    }

    public void setErrorMessages(Map<String, List<String>> errorMessages) {
        this.errorMessages = errorMessages;
    }
}
