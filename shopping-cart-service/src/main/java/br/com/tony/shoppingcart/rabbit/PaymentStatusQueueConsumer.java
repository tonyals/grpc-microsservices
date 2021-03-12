package br.com.tony.shoppingcart.rabbit;

import br.com.tony.shoppingcart.PaymentResponse;
import io.micronaut.rabbitmq.annotation.Queue;
import io.micronaut.rabbitmq.annotation.RabbitListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@RabbitListener
public class PaymentStatusQueueConsumer {

    private static final Logger logger = LoggerFactory.getLogger(PaymentStatusQueueConsumer.class);

    @Queue("payment.status.cart.service")
    public void paymentData(PaymentResponse paymentResponse) {
        logger.info("## RECEBENDO DADOS DO SERVIÇO DE PAGAMENTOS ##");
        logger.info("Id do pedido: " + paymentResponse.getOrderId());
        logger.info("Status do pedido: " + paymentResponse.getStatus());
        logger.info("Depachando pedido para o cliente.");
        logger.info("##############################################");
    }
}
