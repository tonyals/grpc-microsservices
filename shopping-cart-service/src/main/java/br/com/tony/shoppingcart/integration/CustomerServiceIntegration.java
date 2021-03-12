package br.com.tony.shoppingcart.integration;

import br.com.tony.customer.CustomerResponse;
import br.com.tony.customer.FindCustomerByIdRequest;
import br.com.tony.shoppingcart.config.ChannelFactory;
import br.com.tony.shoppingcart.dto.CustomerDTO;

import javax.inject.Singleton;

@Singleton
public class CustomerServiceIntegration {

    private final ChannelFactory channelFactory;

    public CustomerServiceIntegration(ChannelFactory channelFactory) {
        this.channelFactory = channelFactory;
    }

    public CustomerDTO getCustomer(Long customerId) {
        CustomerResponse customerResponse = channelFactory.customerStub()
                .findCustomerById(FindCustomerByIdRequest.newBuilder()
                        .setId(customerId)
                        .build());

        return CustomerDTO.builder()
                .id(customerResponse.getId())
                .name(customerResponse.getName())
                .email(customerResponse.getEmail())
                .cardNumber(customerResponse.getCardNumber())
                .build();
    }
}
