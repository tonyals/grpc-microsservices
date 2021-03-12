package br.com.tony.payment.service;

import br.com.tony.payment.PaymentRequest;
import br.com.tony.payment.PaymentResponse;
import br.com.tony.payment.rabbit.PaymentStatusPublisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;

@Singleton
public class PaymentProcessorService {

    private static final Logger logger = LoggerFactory.getLogger(PaymentProcessorService.class);

    private final PaymentStatusPublisher paymentStatusPublisher;

    public PaymentProcessorService(PaymentStatusPublisher paymentStatusPublisher) {
        this.paymentStatusPublisher = paymentStatusPublisher;
    }

    public void processor(PaymentRequest paymentRequest) {

        logger.info("###### PROCESSANDO PAGAMENTO ######");
        logger.info("Id do pedido: " + paymentRequest.getOrderId());
        logger.info("Cliente: " + paymentRequest.getClientName());
        logger.info("Valor: R$ " + paymentRequest.getAmount());
        logger.info("Pagamento processado com sucesso!");
        logger.info("Enviando status para o broker!");
        logger.info("###################################");

        this.paymentStatusPublisher.sendStatus(PaymentResponse.newBuilder()
                .setOrderId(paymentRequest.getOrderId())
                .setClientId(paymentRequest.getClientId())
                .setStatus("PAGAMENTO APROVADO")
                .build());
    }
}
