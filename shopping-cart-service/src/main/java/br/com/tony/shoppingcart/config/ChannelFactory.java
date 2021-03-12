package br.com.tony.shoppingcart.config;

import br.com.tony.customer.CustomerServiceGrpc;
import io.grpc.ManagedChannel;
import io.micronaut.context.annotation.Factory;
import io.micronaut.grpc.annotation.GrpcChannel;

import javax.inject.Inject;
import javax.inject.Singleton;

@Factory
public class ChannelFactory {

    @Inject
    @GrpcChannel("customer")
    private ManagedChannel customerChannel;

    @Singleton
    public CustomerServiceGrpc.CustomerServiceBlockingStub customerStub() {
        return CustomerServiceGrpc.newBlockingStub(customerChannel);
    }
}
