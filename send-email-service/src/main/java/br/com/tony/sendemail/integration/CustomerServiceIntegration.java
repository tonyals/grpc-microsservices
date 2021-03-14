package br.com.tony.sendemail.integration;

import br.com.tony.sendemail.CustomerRequest;
import br.com.tony.sendemail.CustomerResponse;
import br.com.tony.sendemail.config.ChannelFactory;
import br.com.tony.sendemail.dto.CustomerDTO;

import javax.inject.Singleton;

@Singleton
public class CustomerServiceIntegration {

    private final ChannelFactory channelFactory;

    public CustomerServiceIntegration(ChannelFactory channelFactory) {
        this.channelFactory = channelFactory;
    }

    public CustomerDTO getCustomer(Long id) {
        CustomerResponse customerResponse = this.channelFactory.customerStub()
                .findCustomerById(CustomerRequest.newBuilder()
                        .setId(id).build());

        return new CustomerDTO(customerResponse.getName(), customerResponse.getEmail());
    }
}
