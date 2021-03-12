package br.com.tony.shoppingcart.resource;

import br.com.tony.shoppingcart.OrderRequest;
import br.com.tony.shoppingcart.OrderResponse;
import br.com.tony.shoppingcart.OrderServiceGrpc;
import br.com.tony.shoppingcart.entity.Order;
import br.com.tony.shoppingcart.repository.OrderRepository;
import br.com.tony.shoppingcart.service.PaymentService;
import io.grpc.stub.StreamObserver;
import io.micronaut.grpc.annotation.GrpcService;

import javax.inject.Inject;

@GrpcService
public class OrderResource extends OrderServiceGrpc.OrderServiceImplBase {

    @Inject
    private final PaymentService paymentService;

    @Inject
    private final OrderRepository orderRepository;

    public OrderResource(PaymentService paymentService, OrderRepository orderRepository) {
        this.paymentService = paymentService;
        this.orderRepository = orderRepository;
    }

    @Override
    public void order(OrderRequest request, StreamObserver<OrderResponse> responseObserver) {
        Order order = this.orderRepository.save(Order.builder()
                .clientId(request.getCustomer().getId())
                .productId(request.getProduct().getProductId())
                .quantity(request.getProduct().getQuantity())
                .amount(request.getProduct().getPrice() * request.getProduct().getQuantity())
                .build());

        this.paymentService.sendPaymentProcessor(order);

        responseObserver.onNext(OrderResponse.newBuilder()
                .setOrderConfirmation("Recebemos o seu pedido e o pagamento está sendo processado!")
                .build());
        responseObserver.onCompleted();
    }
}
