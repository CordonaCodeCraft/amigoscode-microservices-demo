package com.amigoscode.fraud.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class FraudCheckHistory {

  @Id
  @SequenceGenerator(name = "fraud_id_sequence", sequenceName = "fraud_id_sequence")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "fraud_id_sequence")
  private Integer id;

  private Integer customerId;
  private Boolean isFraudster;
  private LocalDateTime createdAt;

  @Override
  public boolean equals(final Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    final FraudCheckHistory that = (FraudCheckHistory) o;
    return Objects.equals(id, that.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
