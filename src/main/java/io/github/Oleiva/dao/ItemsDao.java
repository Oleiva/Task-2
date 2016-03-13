package io.github.Oleiva.dao;


import io.github.Oleiva.entity.ItemsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemsDao  extends JpaRepository<ItemsEntity, Long> {

}
