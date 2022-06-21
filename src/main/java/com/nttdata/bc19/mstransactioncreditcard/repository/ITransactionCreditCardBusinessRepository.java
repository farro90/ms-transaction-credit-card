package com.nttdata.bc19.mstransactioncreditcard.repository;

import com.nttdata.bc19.mstransactioncreditcard.model.TransactionCreditCardBusiness;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface ITransactionCreditCardBusinessRepository extends ReactiveMongoRepository<TransactionCreditCardBusiness, String> {

    Flux<TransactionCreditCardBusiness> findByIdCreditCardBusiness(String idCreditCardBusiness);
}
