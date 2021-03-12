package br.com.tony.shoppingcart.rabbit;

import br.com.tony.shoppingcart.PaymentRequest;
import io.micronaut.rabbitmq.annotation.Binding;
import io.micronaut.rabbitmq.annotation.RabbitClient;

@RabbitClient(value = "payment.processor")
public interface PaymentProcessorQueuePublisher {
    @Binding("payment.processor.solicitation")
    void send(PaymentRequest paymentRequest);
}
