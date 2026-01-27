package com.quarkus.application.service.impl;

import com.quarkus.application.service.CustomerService;
import com.quarkus.application.service.dto.CustomerRequest;
import com.quarkus.application.service.dto.CustomerResponse;
import com.quarkus.domain.model.Customer;
import com.quarkus.infrastructure.repository.CustomerRepository;
import com.quarkus.mapper.CustomerMapper;
import io.quarkus.hibernate.reactive.panache.common.WithSession;
import io.quarkus.hibernate.reactive.panache.common.WithTransaction;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import io.smallrye.mutiny.Uni;

import java.util.List;

@ApplicationScoped
public class CustomerServiceImpl implements CustomerService {

    @Inject
    CustomerRepository repository;

    @Override
    @WithTransaction
    public Uni<CustomerResponse> create(CustomerRequest request) {
        Customer entity = CustomerMapper.toEntity(request);
        return repository.persist(entity)
                .map(CustomerMapper::toResponse);
    }

    @Override
    @WithSession
    public Uni<CustomerResponse> findById(Long id) {
        return repository.findById(id)
                .onItem().ifNull().failWith(
                        () -> new RuntimeException("Customer not found"))
                .map(CustomerMapper::toResponse);
    }

    @Override
    @WithSession
    public Uni<List<CustomerResponse>> findAll() {
        return repository.listAll()
                .map(list -> list.stream()
                        .map(CustomerMapper::toResponse)
                        .toList());
    }

    @Override
    @WithTransaction
    public Uni<CustomerResponse> update(Long id, CustomerRequest request) {
        return repository.findById(id)
                .onItem().ifNull().failWith(
                        () -> new RuntimeException("Customer not found"))
                .invoke(c -> {
                    c.name = request.name();
                    c.email = request.email();
                })
                .map(CustomerMapper::toResponse);
    }

    @Override
    @WithTransaction
    public Uni<Void> delete(Long id) {
        return repository.deleteById(id)
                .flatMap(deleted -> deleted
                        ? Uni.createFrom().voidItem()
                        : Uni.createFrom().failure(new RuntimeException("Customer not found"))
                );
    }
}
