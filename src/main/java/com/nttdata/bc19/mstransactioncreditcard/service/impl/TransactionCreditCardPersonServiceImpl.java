package com.nttdata.bc19.mstransactioncreditcard.service.impl;

import com.nttdata.bc19.mstransactioncreditcard.exception.ModelNotFoundException;
import com.nttdata.bc19.mstransactioncreditcard.model.TransactionCreditCardPerson;
import com.nttdata.bc19.mstransactioncreditcard.model.responseWC.CreditCardPerson;
import com.nttdata.bc19.mstransactioncreditcard.repository.ITransactionCreditCardPersonRepository;
import com.nttdata.bc19.mstransactioncreditcard.request.TransactionCreditCardPersonRequest;
import com.nttdata.bc19.mstransactioncreditcard.service.ITransactionCreditCardPersonService;
import com.nttdata.bc19.mstransactioncreditcard.util.TransactionTypeActPro;
import com.nttdata.bc19.mstransactioncreditcard.webclient.impl.ServiceWCImpl;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
public class TransactionCreditCardPersonServiceImpl implements ITransactionCreditCardPersonService {

    @Autowired
    ITransactionCreditCardPersonRepository transactionCreditCardPersonRepository;

    @Autowired
    private ServiceWCImpl clientServiceWC;

    @Override
    public Mono<TransactionCreditCardPerson> create(TransactionCreditCardPersonRequest transactionCreditCardPersonRequest) {
        return clientServiceWC.findCreditCardPersonById(transactionCreditCardPersonRequest.getIdCreditCardPerson())
                .switchIfEmpty(Mono.error(new Exception()))
                .flatMap(CreditCardCardResponse -> this.businessLogicTransaction(transactionCreditCardPersonRequest, CreditCardCardResponse));
    }

    @Override
    public Mono<TransactionCreditCardPerson> update(TransactionCreditCardPerson transactionCreditCardPerson) {
        transactionCreditCardPerson.setUpdatedAt(LocalDateTime.now());
        return clientServiceWC.findCreditCardPersonById(transactionCreditCardPerson.getId())
                .switchIfEmpty(Mono.error(new Exception()))
                .flatMap(CreditCardPerson -> this.updateTransaction(transactionCreditCardPerson));
    }

    @Override
    public Mono<Void> deleteById(String id) {
        return transactionCreditCardPersonRepository.deleteById(id);
    }

    @Override
    public Mono<TransactionCreditCardPerson> findById(String id) {
        return transactionCreditCardPersonRepository.findById(id);
    }

    @Override
    public Flux<TransactionCreditCardPerson> findAll() {
        return transactionCreditCardPersonRepository.findAll();
    }

    @Override
    public Flux<TransactionCreditCardPerson> findByIdCreditCardPerson(String idCreditCardPerson) {
        return transactionCreditCardPersonRepository.findByIdCreditCardPerson(idCreditCardPerson);
    }

    private Mono<TransactionCreditCardPerson> businessLogicTransaction(TransactionCreditCardPersonRequest transactionCreditCardPersonRequest, CreditCardPerson creditCardPerson){

        TransactionCreditCardPerson transactionCreditCardPerson = new TransactionCreditCardPerson();
        transactionCreditCardPerson.setId(new ObjectId().toString());
        transactionCreditCardPerson.setIdCreditCardPerson(transactionCreditCardPersonRequest.getIdCreditCardPerson());
        transactionCreditCardPerson.setTransactionTypeCreditCard(transactionCreditCardPersonRequest.getTransactionTypeCreditCard());
        transactionCreditCardPerson.setTransactionDate(LocalDateTime.now());
        transactionCreditCardPerson.setCreatedAt(LocalDateTime.now());
        transactionCreditCardPerson.setAmount(transactionCreditCardPersonRequest.getAmount());

        if(transactionCreditCardPersonRequest.getTransactionTypeCreditCard().equals(TransactionTypeActPro.PAGO.name())){
            creditCardPerson.setAmountConsumed(creditCardPerson.getAmountConsumed() + transactionCreditCardPersonRequest.getAmount());
            return clientServiceWC.updateCreditCardPerson(creditCardPerson)
                    .switchIfEmpty(Mono.error(new Exception()))
                    .flatMap(creditCardPersonUpdate -> this.registerTransaction(creditCardPersonUpdate, transactionCreditCardPerson));
        }

        return Mono.error(new ModelNotFoundException("Invalid option"));
    }

    private Mono<TransactionCreditCardPerson> registerTransaction(CreditCardPerson creditCardPerson, TransactionCreditCardPerson transactionCreditCardPerson){
        transactionCreditCardPerson.setCreditCardPerson(creditCardPerson);
        return transactionCreditCardPersonRepository.save(transactionCreditCardPerson);
    }

    private Mono<TransactionCreditCardPerson> updateTransaction(TransactionCreditCardPerson transactionCreditCardPerson){
        transactionCreditCardPerson.setUpdatedAt(LocalDateTime.now());
        return transactionCreditCardPersonRepository.save(transactionCreditCardPerson);
    }
}
