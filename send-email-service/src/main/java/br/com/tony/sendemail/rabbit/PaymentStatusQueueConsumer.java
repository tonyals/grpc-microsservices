package br.com.tony.sendemail.rabbit;

import br.com.tony.sendemail.PaymentResponse;
import br.com.tony.sendemail.service.SendEmailService;
import io.micronaut.rabbitmq.annotation.Queue;
import io.micronaut.rabbitmq.annotation.RabbitListener;

@RabbitListener
public class PaymentStatusQueueConsumer {

    private final SendEmailService sendEmailService;

    public PaymentStatusQueueConsumer(SendEmailService sendEmailService) {
        this.sendEmailService = sendEmailService;
    }

    @Queue("payment.status.email.service")
    public void consumerData(PaymentResponse paymentResponse) {
        this.sendEmailService.send(paymentResponse);
    }
}
