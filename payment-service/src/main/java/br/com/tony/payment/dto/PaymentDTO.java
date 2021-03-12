package br.com.tony.payment.dto;

public class PaymentDTO {

    private String clientName;
    private String cardNumber;
    private Double amount;

    public String getClientName() {
        return clientName;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public Double getAmount() {
        return amount;
    }
}
