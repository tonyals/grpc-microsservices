package br.com.tony.customer.service.impl;

import br.com.tony.customer.dto.CustomerDTO;
import br.com.tony.customer.entity.Customer;
import br.com.tony.customer.exceptions.NotFoundException;
import br.com.tony.customer.repository.CustomerRepository;
import br.com.tony.customer.service.CustomerService;

import javax.inject.Singleton;

@Singleton
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public CustomerDTO findById(Long id) {
        Customer customer = this.customerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Customer not found"));
        return new CustomerDTO(customer.getId(), customer.getName(), customer.getEmail(), customer.getCardNumber());
    }
}
