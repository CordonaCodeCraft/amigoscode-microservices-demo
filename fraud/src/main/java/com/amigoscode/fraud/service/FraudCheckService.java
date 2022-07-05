package com.amigoscode.fraud.service;

import com.amigoscode.fraud.entity.FraudCheckHistory;
import com.amigoscode.fraud.persistence.FraudCheckHistoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class FraudCheckService {

  private final FraudCheckHistoryRepository fraudCheckHistoryRepository;

  public boolean isFraudulentCustomer(final Integer customerId) {
    fraudCheckHistoryRepository.save(
        FraudCheckHistory.builder()
            .customerId(customerId)
            .isFraudster(false)
            .createdAt(LocalDateTime.now())
            .build());
    return false;
  }
}
