package com.nttdata.bc19.mstransactioncreditcard.repository;

import com.nttdata.bc19.mstransactioncreditcard.model.TransactionCreditCardPerson;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface ITransactionCreditCardPersonRepository extends ReactiveMongoRepository<TransactionCreditCardPerson, String> {

    Flux<TransactionCreditCardPerson> findByIdCreditCardPerson(String idCreditCardPerson);
}
