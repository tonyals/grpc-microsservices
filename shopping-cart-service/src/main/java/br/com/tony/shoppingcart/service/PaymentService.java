package br.com.tony.shoppingcart.service;

import br.com.tony.shoppingcart.PaymentRequest;
import br.com.tony.shoppingcart.entity.Order;
import br.com.tony.shoppingcart.integration.CustomerServiceIntegration;
import br.com.tony.shoppingcart.rabbit.PaymentProcessorQueuePublisher;

import javax.inject.Singleton;

@Singleton
public class PaymentService {

    private final CustomerServiceIntegration customerServiceIntegration;
    private final PaymentProcessorQueuePublisher paymentProcessorQueuePublisher;

    public PaymentService(CustomerServiceIntegration customerServiceIntegration, PaymentProcessorQueuePublisher paymentProcessorQueuePublisher) {
        this.customerServiceIntegration = customerServiceIntegration;
        this.paymentProcessorQueuePublisher = paymentProcessorQueuePublisher;
    }

    public void sendPaymentProcessor(Order order) {

        var customer = this.customerServiceIntegration
                .getCustomer(order.getClientId());

        this.paymentProcessorQueuePublisher.send(PaymentRequest.newBuilder()
                .setOrderId(order.getId())
                .setClientId(order.getClientId())
                .setClientName(customer.getName())
                .setCardNumber(customer.getCardNumber())
                .setAmount(order.getAmount())
                .build());
    }
}
