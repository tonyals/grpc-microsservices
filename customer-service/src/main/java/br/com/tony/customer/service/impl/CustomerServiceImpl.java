package br.com.tony.customer.service.impl;

import br.com.tony.customer.dto.CustomerDTO;
import br.com.tony.customer.entity.Customer;
import br.com.tony.customer.exceptions.NotFoundException;
import br.com.tony.customer.repository.CustomerRepository;
import br.com.tony.customer.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;

@Singleton
public class CustomerServiceImpl implements CustomerService {

    private static final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public CustomerDTO findById(Long id) {
        logger.info("#### BUSCANDO CLIENTE POR ID ####");
        Customer customer = this.customerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Customer not found"));
        logger.info("Cliente encontrado!");
        logger.info("Nome: " + customer.getName());
        logger.info("Email: " + customer.getEmail());
        logger.info("#################################");
        return new CustomerDTO(customer.getId(), customer.getName(), customer.getEmail(), customer.getCardNumber());
    }
}
