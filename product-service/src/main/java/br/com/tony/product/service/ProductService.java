package br.com.tony.product.service;

import br.com.tony.product.dto.ProductDTO;

public interface ProductService {
    ProductDTO findById(Long id);
}
