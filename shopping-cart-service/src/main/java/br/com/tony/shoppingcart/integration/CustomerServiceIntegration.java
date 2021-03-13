package br.com.tony.shoppingcart.integration;

import br.com.tony.customer.CustomerResponse;
import br.com.tony.customer.FindCustomerByIdRequest;
import br.com.tony.shoppingcart.config.ChannelFactory;
import br.com.tony.shoppingcart.dto.CustomerDTO;
import br.com.tony.shoppingcart.service.PaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;

@Singleton
public class CustomerServiceIntegration {

    private static final Logger logger = LoggerFactory.getLogger(CustomerServiceIntegration.class);

    private final ChannelFactory channelFactory;

    public CustomerServiceIntegration(ChannelFactory channelFactory) {
        this.channelFactory = channelFactory;
    }

    public CustomerDTO getCustomer(Long customerId) {
        logger.info("## BUSCANDO DADOS DO CLIENTE NO CUSTOMER SERVICE ##");
        CustomerResponse customerResponse = channelFactory.customerStub()
                .findCustomerById(FindCustomerByIdRequest.newBuilder()
                        .setId(customerId)
                        .build());

        logger.info("Dados encontrados!");
        logger.info("#################################################");
        return CustomerDTO.builder()
                .id(customerResponse.getId())
                .name(customerResponse.getName())
                .email(customerResponse.getEmail())
                .cardNumber(customerResponse.getCardNumber())
                .build();
    }
}
