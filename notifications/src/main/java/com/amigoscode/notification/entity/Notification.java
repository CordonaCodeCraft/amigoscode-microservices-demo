package com.amigoscode.notification.entity;

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
public class Notification {

  @Id
  @SequenceGenerator(name = "notification_id_sequence", sequenceName = "notification_id_sequence")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "notification_id_sequence")
  private Integer notificationId;

  private Integer toCustomerId;
  private String toCustomerEmail;
  private String sender;
  private String message;
  private LocalDateTime sentAt;

  @Override
  public boolean equals(final Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    final Notification that = (Notification) o;
    return Objects.equals(notificationId, that.notificationId)
        && Objects.equals(toCustomerId, that.toCustomerId)
        && Objects.equals(toCustomerEmail, that.toCustomerEmail)
        && Objects.equals(sender, that.sender)
        && Objects.equals(message, that.message)
        && Objects.equals(sentAt, that.sentAt);
  }

  @Override
  public int hashCode() {
    return Objects.hash(notificationId, toCustomerId, toCustomerEmail, sender, message, sentAt);
  }
}
