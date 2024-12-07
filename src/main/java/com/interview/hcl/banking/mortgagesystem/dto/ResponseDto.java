package com.interview.hcl.banking.mortgagesystem.dto;

/**
 * Manikumari Muthu
 */
public class ResponseDto {

    private String response_status;
    private TransactionDto transactionDto;
    private ErrorResponseDto    errorResponseDto;

    public ResponseDto(){

    }
    public ResponseDto(String response_status, TransactionDto transactionDto, ErrorResponseDto errorResponseDto) {
        this.response_status = response_status;
        this.transactionDto = transactionDto;
        this.errorResponseDto = errorResponseDto;
    }

    public String getResponse_status() {
        return response_status;
    }

    public void setResponse_status(String response_status) {
        this.response_status = response_status;
    }

    public TransactionDto getTransactionDto() {
        return transactionDto;
    }

    public void setTransactionDto(TransactionDto transactionDto) {
        this.transactionDto = transactionDto;
    }

    public ErrorResponseDto getErrorResponseDto() {
        return errorResponseDto;
    }

    public void setErrorResponseDto(ErrorResponseDto errorResponseDto) {
        this.errorResponseDto = errorResponseDto;
    }

    public String getStatus() {
        return response_status;
    }

    public Object getErrors() {
        return errorResponseDto;
    }

    public TransactionDto getData() {
        return transactionDto;
    }
}
