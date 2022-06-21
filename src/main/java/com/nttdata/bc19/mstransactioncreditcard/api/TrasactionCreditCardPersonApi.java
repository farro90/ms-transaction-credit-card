package com.nttdata.bc19.mstransactioncreditcard.api;

import com.nttdata.bc19.mstransactioncreditcard.model.TransactionCreditCardPerson;
import com.nttdata.bc19.mstransactioncreditcard.request.TransactionCreditCardPersonRequest;
import com.nttdata.bc19.mstransactioncreditcard.service.ITransactionCreditCardPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/transaction/credit-card/person")
public class TrasactionCreditCardPersonApi {

    @Autowired
    private ITransactionCreditCardPersonService transactionCreditCardPersonService;

    @PostMapping
    public Mono<TransactionCreditCardPerson> create(@RequestBody TransactionCreditCardPersonRequest transactionCreditCardPersonRequest){
        return transactionCreditCardPersonService.create(transactionCreditCardPersonRequest);
    }

    @PutMapping
    public Mono<TransactionCreditCardPerson> update(@RequestBody TransactionCreditCardPerson transactionCreditCardPerson){
        return transactionCreditCardPersonService.update(transactionCreditCardPerson);
    }

    @GetMapping
    public Flux<TransactionCreditCardPerson> findAll(){
        return transactionCreditCardPersonService.findAll();
    }

    @GetMapping("/{id}")
    public Mono<TransactionCreditCardPerson> findById(@PathVariable String id){ return transactionCreditCardPersonService.findById(id); }

    @GetMapping("/find/{idCreditCardPerson}")
    public Flux<TransactionCreditCardPerson> findByIdPasProPerCli(@PathVariable String idCreditCardPerson){
        return transactionCreditCardPersonService.findByIdCreditCardPerson(idCreditCardPerson);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable String id){
        return transactionCreditCardPersonService.deleteById(id);
    }
}
