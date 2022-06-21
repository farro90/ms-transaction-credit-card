package com.nttdata.bc19.mstransactioncreditcard.api;

import com.nttdata.bc19.mstransactioncreditcard.model.TransactionCreditCardBusiness;
import com.nttdata.bc19.mstransactioncreditcard.request.TransactionCreditCardBusinessRequest;
import com.nttdata.bc19.mstransactioncreditcard.service.ITransactionCreditCardBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/transaction/credit-card/business")
public class TrasactionCreditCardBusinessApi {

    @Autowired
    private ITransactionCreditCardBusinessService transactionCreditCardBusinessService;

    @PostMapping
    public Mono<TransactionCreditCardBusiness> create(@RequestBody TransactionCreditCardBusinessRequest transactionCreditCardBusinessRequest){
        return transactionCreditCardBusinessService.create(transactionCreditCardBusinessRequest);
    }

    @PutMapping
    public Mono<TransactionCreditCardBusiness> update(@RequestBody TransactionCreditCardBusiness transactionCreditCardBusiness){
        return transactionCreditCardBusinessService.update(transactionCreditCardBusiness);
    }

    @GetMapping
    public Flux<TransactionCreditCardBusiness> findAll(){
        return transactionCreditCardBusinessService.findAll();
    }

    @GetMapping("/{id}")
    public Mono<TransactionCreditCardBusiness> findById(@PathVariable String id){ return transactionCreditCardBusinessService.findById(id); }

    @GetMapping("/find/{idCreditCardBusiness}")
    public Flux<TransactionCreditCardBusiness> findByIdPasProPerCli(@PathVariable String idCreditCardBusiness){
        return transactionCreditCardBusinessService.findByIdCreditCardBusiness(idCreditCardBusiness);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable String id){
        return transactionCreditCardBusinessService.deleteById(id);
    }
}
