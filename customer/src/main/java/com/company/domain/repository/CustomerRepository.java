package com.company.domain.repository;

import com.company.domain.model.Customer;
import io.smallrye.mutiny.Uni;

public interface CustomerRepository {
    Uni<Customer> save(Customer customer);
    Uni<Customer> findByEmail(String email);
}
