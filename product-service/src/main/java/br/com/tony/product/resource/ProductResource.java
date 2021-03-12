package br.com.tony.product.resource;

import br.com.tony.product.FindProductByIdRequest;
import br.com.tony.product.ProductResponse;
import br.com.tony.product.ProductServiceGrpc;
import br.com.tony.product.dto.ProductDTO;
import br.com.tony.product.service.ProductService;
import io.grpc.stub.StreamObserver;
import io.micronaut.grpc.annotation.GrpcService;

@GrpcService
public class ProductResource extends ProductServiceGrpc.ProductServiceImplBase {

    private final ProductService productService;

    public ProductResource(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public void findProductById(FindProductByIdRequest request, StreamObserver<ProductResponse> responseObserver) {
        ProductDTO productDTO = this.productService.findById(request.getId());
        responseObserver.onNext(ProductResponse.newBuilder()
                .setId(productDTO.getId())
                .setName(productDTO.getName())
                .setPrice(productDTO.getPrice())
                .build());
        responseObserver.onCompleted();
    }
}
