package com.company.infrastructure.persistence;

import com.company.domain.model.Customer;
import com.company.domain.repository.CustomerRepository;
import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import io.smallrye.mutiny.Uni;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CustomerRepositoryImpl
        implements CustomerRepository, PanacheRepository<Customer> {

    @Override
    public Uni<Customer> save(Customer customer) {
        return persist(customer);
    }

    @Override
    public Uni<Customer> findByEmail(String email) {
        return find("email", email).firstResult();
    }
}
