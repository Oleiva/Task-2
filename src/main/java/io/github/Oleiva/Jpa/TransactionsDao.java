package io.github.Oleiva.jpa;

import io.github.Oleiva.entity.TransactionsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionsDao  extends JpaRepository<TransactionsEntity, Long> {
    List<TransactionsEntity> findByOrderId (long order);
}
