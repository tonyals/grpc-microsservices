package br.com.tony.customer.resource;

import br.com.tony.customer.CustomerResponse;
import br.com.tony.customer.CustomerServiceGrpc;
import br.com.tony.customer.FindCustomerByIdRequest;
import br.com.tony.customer.dto.CustomerDTO;
import br.com.tony.customer.exceptions.NotFoundException;
import br.com.tony.customer.service.CustomerService;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import io.micronaut.grpc.annotation.GrpcService;

@GrpcService
public class CustomerResource extends CustomerServiceGrpc.CustomerServiceImplBase {

    private final CustomerService customerService;

    public CustomerResource(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Override
    public void findCustomerById(FindCustomerByIdRequest request, StreamObserver<CustomerResponse> responseObserver) {
        try {
            CustomerDTO customerDTO = this.customerService.findById(request.getId());
            CustomerResponse response = CustomerResponse.newBuilder()
                    .setId(customerDTO.getId())
                    .setName(customerDTO.getName())
                    .setEmail(customerDTO.getEmail())
                    .setCardNumber(customerDTO.getCardNumber())
                    .build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();

        } catch (NotFoundException e) {
            responseObserver.onError(
                    Status.NOT_FOUND.withDescription(e.getMessage()).asRuntimeException()
            );
        }
    }
}
