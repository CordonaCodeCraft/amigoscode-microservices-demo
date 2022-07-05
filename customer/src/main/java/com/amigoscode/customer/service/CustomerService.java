package com.amigoscode.customer.service;

import com.amigoscode.customer.dto.CustomerRegistrationRequest;
import com.amigoscode.customer.dto.FraudCheckResponse;
import com.amigoscode.customer.entity.Customer;
import com.amigoscode.customer.persistence.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class CustomerService {

  private final CustomerRepository customerRepository;
  private final RestTemplate restTemplate;

  public void registerCustomer(final CustomerRegistrationRequest request) {

    final var newCustomer =
        Customer.builder()
            .firstName(request.firstName())
            .lastName(request.lastName())
            .email(request.email())
            .build();

    final var savedCustomer = customerRepository.saveAndFlush(newCustomer);

    final var fraudCheckResponseResponse =
        restTemplate.getForObject(
            "http://FRAUD/api/v1/fraud-check/{customerId}",
            FraudCheckResponse.class,
            savedCustomer.getId());

    if (fraudCheckResponseResponse.isFraudster()) {
      throw new IllegalStateException("Fraudster!");
    }
  }
}
