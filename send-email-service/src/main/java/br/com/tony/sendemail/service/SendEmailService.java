package br.com.tony.sendemail.service;

import br.com.tony.sendemail.CustomerRequest;
import br.com.tony.sendemail.CustomerResponse;
import br.com.tony.sendemail.PaymentResponse;
import br.com.tony.sendemail.config.ChannelFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;

@Singleton
public class SendEmailService {

    private static final Logger logger = LoggerFactory.getLogger(SendEmailService.class);

    private final ChannelFactory channelFactory;

    public SendEmailService(ChannelFactory channelFactory) {
        this.channelFactory = channelFactory;
    }

    public void send(PaymentResponse paymentResponse) {
        CustomerResponse customerResponse = this.channelFactory.customerStub()
                .findCustomerById(CustomerRequest.newBuilder()
                        .setId(paymentResponse.getClientId()).build());

        logger.info("## ENVIANDO E-MAIL PARA O CLIENTE ##");
        logger.info("Nome: " + customerResponse.getName());
        logger.info("Email: " + customerResponse.getEmail());
        logger.info("Status do pedido: " + paymentResponse.getStatus());
        logger.info("####################################");
    }
}
