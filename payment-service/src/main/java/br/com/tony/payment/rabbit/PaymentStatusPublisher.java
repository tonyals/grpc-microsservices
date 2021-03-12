package br.com.tony.payment.rabbit;

import br.com.tony.payment.PaymentResponse;
import io.micronaut.rabbitmq.annotation.RabbitClient;

@RabbitClient(value = "payment.status")
public interface PaymentStatusPublisher {
    void sendStatus(PaymentResponse response);
}
