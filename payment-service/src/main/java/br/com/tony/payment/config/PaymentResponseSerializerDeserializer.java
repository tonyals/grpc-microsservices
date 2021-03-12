package br.com.tony.payment.config;

import br.com.tony.payment.PaymentResponse;
import com.google.protobuf.InvalidProtocolBufferException;
import io.micronaut.core.type.Argument;
import io.micronaut.rabbitmq.bind.RabbitConsumerState;
import io.micronaut.rabbitmq.intercept.MutableBasicProperties;
import io.micronaut.rabbitmq.serdes.RabbitMessageSerDes;

import javax.inject.Singleton;

@Singleton
public class PaymentResponseSerializerDeserializer implements RabbitMessageSerDes<PaymentResponse> {

    @Override
    public PaymentResponse deserialize(RabbitConsumerState consumerState,
                                       Argument<PaymentResponse> argument) {
        try {
            return PaymentResponse.parseFrom(consumerState.getBody());
        } catch (InvalidProtocolBufferException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public byte[] serialize(PaymentResponse data, MutableBasicProperties properties) {
        if (data == null) {
            return null;
        }
        return data.toByteArray();
    }

    @Override
    public boolean supports(Argument<PaymentResponse> type) {
        return type.getType().isAssignableFrom(PaymentResponse.class);
    }
}
