package com.nttdata.bc19.mstransactioncreditcard.service;

import com.nttdata.bc19.mstransactioncreditcard.model.TransactionCreditCardPerson;
import com.nttdata.bc19.mstransactioncreditcard.request.TransactionCreditCardPersonRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ITransactionCreditCardPersonService {

    Mono<TransactionCreditCardPerson> create(TransactionCreditCardPersonRequest transactionCreditCardPersonRequest);
    Mono<TransactionCreditCardPerson> update(TransactionCreditCardPerson transactionCreditCardPerson);
    Mono<Void>deleteById(String id);
    Mono<TransactionCreditCardPerson> findById(String id);
    Flux<TransactionCreditCardPerson> findAll();

    Flux<TransactionCreditCardPerson> findByIdCreditCardPerson(String idCreditPerson);
}
