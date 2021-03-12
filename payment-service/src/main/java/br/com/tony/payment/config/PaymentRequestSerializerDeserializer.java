package br.com.tony.payment.config;

import br.com.tony.payment.PaymentRequest;
import com.google.protobuf.InvalidProtocolBufferException;
import io.micronaut.core.type.Argument;
import io.micronaut.rabbitmq.bind.RabbitConsumerState;
import io.micronaut.rabbitmq.intercept.MutableBasicProperties;
import io.micronaut.rabbitmq.serdes.RabbitMessageSerDes;

import javax.inject.Singleton;

@Singleton
public class PaymentRequestSerializerDeserializer implements RabbitMessageSerDes<PaymentRequest> {

    @Override
    public PaymentRequest deserialize(RabbitConsumerState consumerState, Argument<PaymentRequest> argument) {
        try {
            return PaymentRequest.parseFrom(consumerState.getBody());
        } catch (InvalidProtocolBufferException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public byte[] serialize(PaymentRequest data, MutableBasicProperties properties) {
        if (data == null) {
            return null;
        }
        return data.toByteArray();
    }

    @Override
    public boolean supports(Argument<PaymentRequest> type) {
        return type.getType().isAssignableFrom(PaymentRequest.class);
    }
}
