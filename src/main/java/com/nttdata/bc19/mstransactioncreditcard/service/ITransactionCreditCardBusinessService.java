package com.nttdata.bc19.mstransactioncreditcard.service;

import com.nttdata.bc19.mstransactioncreditcard.model.TransactionCreditCardBusiness;
import com.nttdata.bc19.mstransactioncreditcard.request.TransactionCreditCardBusinessRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ITransactionCreditCardBusinessService {

    Mono<TransactionCreditCardBusiness> create(TransactionCreditCardBusinessRequest transactionCreditCardBusinessRequest);
    Mono<TransactionCreditCardBusiness> update(TransactionCreditCardBusiness transactionCreditCardBusiness);
    Mono<Void>deleteById(String id);
    Mono<TransactionCreditCardBusiness> findById(String id);
    Flux<TransactionCreditCardBusiness> findAll();

    Flux<TransactionCreditCardBusiness> findByIdCreditCardBusiness(String idCreditBusiness);
}
