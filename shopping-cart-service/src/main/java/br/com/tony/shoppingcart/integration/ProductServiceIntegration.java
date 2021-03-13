package br.com.tony.shoppingcart.integration;

import br.com.tony.shoppingcart.FindProductByIdRequest;
import br.com.tony.shoppingcart.ProductResponse;
import br.com.tony.shoppingcart.config.ChannelFactory;
import br.com.tony.shoppingcart.dto.ProductDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;

@Singleton
public class ProductServiceIntegration {

    private static final Logger logger = LoggerFactory.getLogger(ProductServiceIntegration.class);

    private final ChannelFactory channelFactory;

    public ProductServiceIntegration(ChannelFactory channelFactory) {
        this.channelFactory = channelFactory;
    }

    public ProductDTO getProduct(Long productId) {
        logger.info("## BUSCANDO DADOS DO PRODUTO NO PRODUCT SERVICE ##");
        ProductResponse productResponse = channelFactory.productStub()
                .findProductById(FindProductByIdRequest.newBuilder()
                        .setId(productId)
                        .build());

        logger.info("Dados encontrados!");
        logger.info("#################################################");
        return ProductDTO.builder()
                .id(productResponse.getId())
                .name(productResponse.getName())
                .price(productResponse.getPrice())
                .build();
    }
}
