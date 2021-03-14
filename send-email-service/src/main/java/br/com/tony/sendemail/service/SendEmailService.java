package br.com.tony.sendemail.service;

import br.com.tony.sendemail.CustomerRequest;
import br.com.tony.sendemail.CustomerResponse;
import br.com.tony.sendemail.PaymentResponse;
import br.com.tony.sendemail.config.ChannelFactory;
import br.com.tony.sendemail.dto.CustomerDTO;
import br.com.tony.sendemail.integration.CustomerServiceIntegration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;

@Singleton
public class SendEmailService {

    private static final Logger logger = LoggerFactory.getLogger(SendEmailService.class);

    private final CustomerServiceIntegration customerServiceIntegration;

    public SendEmailService(CustomerServiceIntegration customerServiceIntegration) {
        this.customerServiceIntegration = customerServiceIntegration;
    }

    public void send(PaymentResponse paymentResponse) {

        CustomerDTO customerDTO = this.customerServiceIntegration
                .getCustomer(paymentResponse.getClientId());

        logger.info("## ENVIANDO E-MAIL PARA O CLIENTE ##");
        logger.info("Nome: " + customerDTO.getName());
        logger.info("Email: " + customerDTO.getEmail());
        logger.info("Status do pedido: " + paymentResponse.getStatus());
        logger.info("####################################");
    }
}
