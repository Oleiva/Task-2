package io.github.Oleiva.dao;

import io.github.Oleiva.entity.CustomersEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ListOfCustomersDao  extends JpaRepository<CustomersEntity, Long> {

}
