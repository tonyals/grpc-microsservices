package br.com.tony.product.service.impl;

import br.com.tony.product.dto.ProductDTO;
import br.com.tony.product.entity.Product;
import br.com.tony.product.exception.NotFoundException;
import br.com.tony.product.repository.ProductRepository;
import br.com.tony.product.service.ProductService;

import javax.inject.Singleton;

@Singleton
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductDTO findById(Long id) {
        Product product = this.productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Produto não encontrado"));
        return new ProductDTO(product.getId(), product.getName(), product.getPrice());
    }
}
