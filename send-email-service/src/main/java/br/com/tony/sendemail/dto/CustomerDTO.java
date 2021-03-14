package br.com.tony.sendemail.dto;

public class CustomerDTO {

    private final String name;
    private final String email;

    public CustomerDTO(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
