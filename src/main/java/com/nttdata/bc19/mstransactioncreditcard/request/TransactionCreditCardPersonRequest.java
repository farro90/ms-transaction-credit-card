package com.nttdata.bc19.mstransactioncreditcard.request;

import lombok.Data;

@Data
public class TransactionCreditCardPersonRequest {
    private String idCreditCardPerson;
    private double amount;
    private String transactionTypeCreditCard;
}
