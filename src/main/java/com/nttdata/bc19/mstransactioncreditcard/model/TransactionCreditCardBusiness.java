package com.nttdata.bc19.mstransactioncreditcard.model;

import com.nttdata.bc19.mstransactioncreditcard.model.responseWC.CreditCardBusiness;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document
public class TransactionCreditCardBusiness extends BaseModel{
    //private  String code;
    private String idCreditCardBusiness;
    private CreditCardBusiness creditCardBusiness;
    private LocalDateTime transactionDate;
    private String transactionTypeCreditCard;
    private double amount;
}
