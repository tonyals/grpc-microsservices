package br.com.tony.customer.service;

import br.com.tony.customer.dto.CustomerDTO;

public interface CustomerService {
    CustomerDTO findById(Long id);
}
