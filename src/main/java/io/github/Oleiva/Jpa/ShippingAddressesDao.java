package io.github.Oleiva.jpa;

import io.github.Oleiva.entity.ShippingAddressesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShippingAddressesDao extends JpaRepository<ShippingAddressesEntity, Long> {

}
