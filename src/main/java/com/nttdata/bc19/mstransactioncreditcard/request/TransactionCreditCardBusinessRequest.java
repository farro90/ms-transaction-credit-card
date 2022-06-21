package com.nttdata.bc19.mstransactioncreditcard.request;

import lombok.Data;

@Data
public class TransactionCreditCardBusinessRequest {
    private String idCreditCardBusiness;
    private double amount;
    private String transactionTypeCreditCard;
}
