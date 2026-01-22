package com.company.application.service;

import com.company.application.dto.CustomerRequest;
import com.company.application.dto.CustomerResponse;
import com.company.application.exception.EmailAlreadyRegisteredException;
import com.company.domain.model.Customer;
import com.company.domain.repository.CustomerRepository;
import io.quarkus.hibernate.reactive.panache.common.WithTransaction;
import io.smallrye.mutiny.Uni;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CustomerService {

    private final CustomerRepository repository;

    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    @WithTransaction
    public Uni<CustomerResponse> register(CustomerRequest request) {

        return repository.findByEmail(request.email())
                .onItem()
                .ifNotNull()
                .failWith(() ->
                        new EmailAlreadyRegisteredException(request.email())
                )
                .onItem()
                .ifNull()
                .switchTo(() -> {
                    Customer c = new Customer();
                    c.fullName = request.fullName();
                    c.email = request.email();
                    return repository.save(c);
                })
                .map(c -> new CustomerResponse(c.id, c.fullName, c.email));
    }
}
