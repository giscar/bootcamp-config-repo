package com.quarkus.mapper;

import com.quarkus.application.service.dto.CustomerRequest;
import com.quarkus.application.service.dto.CustomerResponse;
import com.quarkus.domain.model.Customer;

public class CustomerMapper {

    public static Customer toEntity(CustomerRequest request) {
        Customer c = new Customer();
        c.name = request.name();
        c.email = request.email();
        return c;
    }

    public static CustomerResponse toResponse(Customer customer) {
        return new CustomerResponse(
                customer.id,
                customer.name,
                customer.email
        );
    }
}
