package br.com.tony.customer.dto;

public class CustomerDTO {

    private Long id;
    private String name;
    private String email;
    private String cardNumber;

    public CustomerDTO() {
    }

    public CustomerDTO(Long id, String name, String email, String cardNumber) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.cardNumber = cardNumber;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getCardNumber() {
        return cardNumber;
    }
}
