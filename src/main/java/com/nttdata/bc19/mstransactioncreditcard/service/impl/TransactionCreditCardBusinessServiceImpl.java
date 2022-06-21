package com.nttdata.bc19.mstransactioncreditcard.service.impl;

import com.nttdata.bc19.mstransactioncreditcard.exception.ModelNotFoundException;
import com.nttdata.bc19.mstransactioncreditcard.model.TransactionCreditCardBusiness;
import com.nttdata.bc19.mstransactioncreditcard.model.responseWC.CreditCardBusiness;
import com.nttdata.bc19.mstransactioncreditcard.repository.ITransactionCreditCardBusinessRepository;
import com.nttdata.bc19.mstransactioncreditcard.request.TransactionCreditCardBusinessRequest;
import com.nttdata.bc19.mstransactioncreditcard.service.ITransactionCreditCardBusinessService;
import com.nttdata.bc19.mstransactioncreditcard.util.TransactionTypeActPro;
import com.nttdata.bc19.mstransactioncreditcard.webclient.impl.ServiceWCImpl;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
public class TransactionCreditCardBusinessServiceImpl implements ITransactionCreditCardBusinessService {

    @Autowired
    ITransactionCreditCardBusinessRepository transactionCreditCardBusinessRepository;

    @Autowired
    private ServiceWCImpl clientServiceWC;

    @Override
    public Mono<TransactionCreditCardBusiness> create(TransactionCreditCardBusinessRequest transactionCreditCardBusinessRequest) {
        return clientServiceWC.findCreditCardBusinessById(transactionCreditCardBusinessRequest.getIdCreditCardBusiness())
                .switchIfEmpty(Mono.error(new Exception()))
                .flatMap(CreditCardCardResponse -> this.businessLogicTransaction(transactionCreditCardBusinessRequest, CreditCardCardResponse));
    }

    @Override
    public Mono<TransactionCreditCardBusiness> update(TransactionCreditCardBusiness transactionCreditCardBusiness) {
        transactionCreditCardBusiness.setUpdatedAt(LocalDateTime.now());
        return clientServiceWC.findCreditCardBusinessById(transactionCreditCardBusiness.getId())
                .switchIfEmpty(Mono.error(new Exception()))
                .flatMap(CreditCardBusiness -> this.updateTransaction(transactionCreditCardBusiness));
    }

    @Override
    public Mono<Void> deleteById(String id) {
        return transactionCreditCardBusinessRepository.deleteById(id);
    }

    @Override
    public Mono<TransactionCreditCardBusiness> findById(String id) {
        return transactionCreditCardBusinessRepository.findById(id);
    }

    @Override
    public Flux<TransactionCreditCardBusiness> findAll() {
        return transactionCreditCardBusinessRepository.findAll();
    }

    @Override
    public Flux<TransactionCreditCardBusiness> findByIdCreditCardBusiness(String idCreditCardBusiness) {
        return transactionCreditCardBusinessRepository.findByIdCreditCardBusiness(idCreditCardBusiness);
    }

    private Mono<TransactionCreditCardBusiness> businessLogicTransaction(TransactionCreditCardBusinessRequest transactionCreditCardBusinessRequest, CreditCardBusiness creditCardBusiness){

        TransactionCreditCardBusiness transactionCreditCardBusiness = new TransactionCreditCardBusiness();
        transactionCreditCardBusiness.setId(new ObjectId().toString());
        transactionCreditCardBusiness.setIdCreditCardBusiness(transactionCreditCardBusinessRequest.getIdCreditCardBusiness());
        transactionCreditCardBusiness.setTransactionTypeCreditCard(transactionCreditCardBusinessRequest.getTransactionTypeCreditCard());
        transactionCreditCardBusiness.setTransactionDate(LocalDateTime.now());
        transactionCreditCardBusiness.setCreatedAt(LocalDateTime.now());
        transactionCreditCardBusiness.setAmount(transactionCreditCardBusinessRequest.getAmount());

        if(transactionCreditCardBusinessRequest.getTransactionTypeCreditCard().equals(TransactionTypeActPro.PAGO.name())){
            creditCardBusiness.setAmountConsumed(creditCardBusiness.getAmountConsumed() + transactionCreditCardBusinessRequest.getAmount());
            return clientServiceWC.updateCreditCardBusiness(creditCardBusiness)
                    .switchIfEmpty(Mono.error(new Exception()))
                    .flatMap(creditCardBusinessUpdate -> this.registerTransaction(creditCardBusinessUpdate, transactionCreditCardBusiness));
        }

        return Mono.error(new ModelNotFoundException("Invalid option"));
    }

    private Mono<TransactionCreditCardBusiness> registerTransaction(CreditCardBusiness creditCardBusiness, TransactionCreditCardBusiness transactionCreditCardBusiness){
        transactionCreditCardBusiness.setCreditCardBusiness(creditCardBusiness);
        return transactionCreditCardBusinessRepository.save(transactionCreditCardBusiness);
    }

    private Mono<TransactionCreditCardBusiness> updateTransaction(TransactionCreditCardBusiness transactionCreditCardBusiness){
        transactionCreditCardBusiness.setUpdatedAt(LocalDateTime.now());
        return transactionCreditCardBusinessRepository.save(transactionCreditCardBusiness);
    }
}
