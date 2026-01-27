package com.quarkus.application.service;

import com.quarkus.application.service.dto.CustomerRequest;
import com.quarkus.application.service.dto.CustomerResponse;
import io.smallrye.mutiny.Uni;

import java.util.List;

public interface CustomerService {

    Uni<CustomerResponse> create(CustomerRequest request);

    Uni<CustomerResponse> findById(Long id);

    Uni<List<CustomerResponse>> findAll();

    Uni<CustomerResponse> update(Long id, CustomerRequest request);

    Uni<Void> delete(Long id);
}
