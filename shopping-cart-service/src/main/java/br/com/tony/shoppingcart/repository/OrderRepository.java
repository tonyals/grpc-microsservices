package br.com.tony.shoppingcart.repository;

import br.com.tony.shoppingcart.entity.Order;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {}
