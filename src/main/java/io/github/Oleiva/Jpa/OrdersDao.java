package io.github.Oleiva.jpa;

import io.github.Oleiva.entity.OrdersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrdersDao  extends JpaRepository<OrdersEntity, Long> {

    @Query(value = findLastIndex, nativeQuery = true)  long  findLastIndex();
    String findLastIndex = "SELECT max(id) FROM orders";

}
