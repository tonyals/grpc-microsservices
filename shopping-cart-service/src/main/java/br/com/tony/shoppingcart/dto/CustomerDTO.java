package br.com.tony.shoppingcart.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CustomerDTO {
    private final Long id;
    private final String name;
    private final String email;
    private final String cardNumber;
}
