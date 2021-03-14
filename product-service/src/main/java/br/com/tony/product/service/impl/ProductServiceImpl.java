package br.com.tony.product.service.impl;

import br.com.tony.product.dto.ProductDTO;
import br.com.tony.product.entity.Product;
import br.com.tony.product.exception.NotFoundException;
import br.com.tony.product.repository.ProductRepository;
import br.com.tony.product.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;

@Singleton
public class ProductServiceImpl implements ProductService {

    private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductDTO findById(Long id) {
        logger.info("#### BUSCANDO PRODUTO POR ID ####");
        Product product = this.productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Produto não encontrado"));
        logger.info("Produto encontrado!");
        logger.info("Nome: " + product.getName());
        logger.info("Preço: R$ " + product.getPrice());
        logger.info("#################################");
        return new ProductDTO(product.getId(), product.getName(), product.getPrice());
    }
}
