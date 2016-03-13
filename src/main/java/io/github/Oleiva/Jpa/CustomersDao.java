package io.github.Oleiva.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import io.github.Oleiva.entity.CustomersEntity;

public interface CustomersDao  extends JpaRepository<CustomersEntity, Long> {

}
