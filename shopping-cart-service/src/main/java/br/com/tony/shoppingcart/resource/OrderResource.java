package br.com.tony.shoppingcart.resource;

import br.com.tony.shoppingcart.OrderRequest;
import br.com.tony.shoppingcart.OrderResponse;
import br.com.tony.shoppingcart.OrderServiceGrpc;
import br.com.tony.shoppingcart.entity.Order;
import br.com.tony.shoppingcart.service.OrderService;
import br.com.tony.shoppingcart.service.PaymentService;
import io.grpc.stub.StreamObserver;
import io.micronaut.grpc.annotation.GrpcService;

@GrpcService
public class OrderResource extends OrderServiceGrpc.OrderServiceImplBase {

    private final PaymentService paymentService;
    private final OrderService orderService;

    public OrderResource(PaymentService paymentService, OrderService orderService) {
        this.paymentService = paymentService;
        this.orderService = orderService;
    }

    @Override
    public void order(OrderRequest request, StreamObserver<OrderResponse> responseObserver) {
        Order order = this.orderService.createOrder(request);

        this.paymentService.sendPaymentProcessor(order);

        responseObserver.onNext(OrderResponse.newBuilder()
                .setOrderConfirmation("Recebemos o seu pedido e o pagamento está sendo processado!")
                .build());
        responseObserver.onCompleted();
    }
}
