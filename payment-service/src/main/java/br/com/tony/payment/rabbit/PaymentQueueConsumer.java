package br.com.tony.payment.rabbit;

import br.com.tony.payment.PaymentRequest;
import br.com.tony.payment.service.PaymentProcessorService;
import io.micronaut.rabbitmq.annotation.Queue;
import io.micronaut.rabbitmq.annotation.RabbitListener;

@RabbitListener
public class PaymentQueueConsumer {

    private final PaymentProcessorService paymentProcessorService;

    public PaymentQueueConsumer(PaymentProcessorService paymentProcessorService) {
        this.paymentProcessorService = paymentProcessorService;
    }

    @Queue("payment.processor.solicitation")
    public void paymentData(PaymentRequest message) {
        paymentProcessorService.processor(message);
    }
}
