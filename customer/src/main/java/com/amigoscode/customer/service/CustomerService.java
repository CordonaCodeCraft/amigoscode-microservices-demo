package com.amigoscode.customer.service;

import com.amigoscode.amqp.RabbitMQMessageProducer;
import com.amigoscode.clients.fraud.FraudClient;
import com.amigoscode.clients.notification.NotificationRequest;
import com.amigoscode.customer.dto.CustomerRegistrationRequest;
import com.amigoscode.customer.entity.Customer;
import com.amigoscode.customer.persistence.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerService {

  private final CustomerRepository customerRepository;
  private final FraudClient fraudClient;
  private final RabbitMQMessageProducer messageProducer;

  public void registerCustomer(final CustomerRegistrationRequest request) {

    final var newCustomer =
        Customer.builder()
            .firstName(request.firstName())
            .lastName(request.lastName())
            .email(request.email())
            .build();

    final var savedCustomer = customerRepository.saveAndFlush(newCustomer);
    final var fraudCheckResponseResponse = fraudClient.isFraudster(savedCustomer.getId());

    if (fraudCheckResponseResponse.isFraudster()) {
      throw new IllegalStateException("Fraudster!");
    }

    final var notificationRequest =
        new NotificationRequest(
            savedCustomer.getId(),
            savedCustomer.getEmail(),
            String.format("Hi, %s, welcome to Amigoscode...", savedCustomer.getFirstName()));

    messageProducer.publish(
        notificationRequest, "internal.exchange", "internal.notification.routing-key");
  }
}
