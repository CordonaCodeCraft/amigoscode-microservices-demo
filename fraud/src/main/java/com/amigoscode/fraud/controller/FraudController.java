package com.amigoscode.fraud.controller;

import com.amigoscode.clients.fraud.FraudCheckResponse;
import com.amigoscode.fraud.service.FraudCheckService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("api/v1/fraud-check")
@AllArgsConstructor
public class FraudController {

  private final FraudCheckService fraudCheckService;

  @GetMapping(path = "{customerId}")
  public FraudCheckResponse isFraudster(@PathVariable("customerId") final Integer customerId) {
    final var isFraudulentCustomer = fraudCheckService.isFraudulentCustomer(customerId);
    log.info("fraud check request for customer {}", customerId);
    return new FraudCheckResponse(isFraudulentCustomer);
  }
}
