package eu.marcolenzo.hexagonalarchitecture.persistence.mongo;

import java.math.BigDecimal;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "accounts")
public class AccountDocument {

  @Id
  private String number;
  private String customerId;
  private BigDecimal balance;

  public AccountDocument(String number, String customerId, BigDecimal balance) {
    this.number = number;
    this.customerId = customerId;
    this.balance = balance;
  }

  public String getNumber() {
    return number;
  }

  public void setNumber(String number) {
    this.number = number;
  }

  public String getCustomerId() {
    return customerId;
  }

  public void setCustomerId(String customerId) {
    this.customerId = customerId;
  }

  public BigDecimal getBalance() {
    return balance;
  }

  public void setBalance(BigDecimal balance) {
    this.balance = balance;
  }

}
