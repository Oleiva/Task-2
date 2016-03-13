package io.github.Oleiva.dao;

import io.github.Oleiva.entity.CustomersEntity;
import io.github.Oleiva.entity.OrdersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;

public interface OrdersDao  extends JpaRepository<OrdersEntity, Long> {

    @Query(value = findLastIndex, nativeQuery = true)  long  findLastIndex();
    String findLastIndex = "SELECT max(id) FROM orders";

}
