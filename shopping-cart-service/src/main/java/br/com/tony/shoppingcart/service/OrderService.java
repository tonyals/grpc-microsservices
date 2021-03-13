package br.com.tony.shoppingcart.service;

import br.com.tony.shoppingcart.OrderRequest;
import br.com.tony.shoppingcart.dto.ProductDTO;
import br.com.tony.shoppingcart.entity.Order;
import br.com.tony.shoppingcart.integration.ProductServiceIntegration;
import br.com.tony.shoppingcart.repository.OrderRepository;

import javax.inject.Singleton;

@Singleton
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductServiceIntegration productServiceIntegration;

    public OrderService(OrderRepository orderRepository, ProductServiceIntegration productServiceIntegration) {
        this.orderRepository = orderRepository;
        this.productServiceIntegration = productServiceIntegration;
    }

    public Order createOrder(OrderRequest request) {
        ProductDTO productDTO = this.productServiceIntegration
                .getProduct(request.getProduct().getProductId());

        return this.orderRepository.save(Order.builder()
                .clientId(request.getCustomer().getId())
                .productId(request.getProduct().getProductId())
                .quantity(request.getProduct().getQuantity())
                .amount(productDTO.getPrice() * request.getProduct().getQuantity())
                .build());
    }
}
