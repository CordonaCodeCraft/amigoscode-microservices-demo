package com.amigoscode.customer.controller;

import com.amigoscode.customer.dto.CustomerRegistrationRequest;
import com.amigoscode.customer.service.CustomerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

  private final CustomerService customerService;

  @PostMapping
  public void registerCustomer(
      @RequestBody final CustomerRegistrationRequest customerRegistrationRequest) {
    log.info("new customer registration {}", customerRegistrationRequest);
    customerService.registerCustomer(customerRegistrationRequest);
  }
}
