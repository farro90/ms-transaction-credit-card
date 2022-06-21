package com.nttdata.bc19.mstransactioncreditcard.model;

import com.nttdata.bc19.mstransactioncreditcard.model.responseWC.CreditCardPerson;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document
public class TransactionCreditCardPerson extends BaseModel{
    //private  String code;
    private String idCreditCardPerson;
    private CreditCardPerson creditCardPerson;
    private LocalDateTime transactionDate;
    private String transactionTypeCreditCard;
    private double amount;
}
