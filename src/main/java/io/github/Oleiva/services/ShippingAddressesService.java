package io.github.Oleiva.services;

import io.github.Oleiva.entity.ShippingAddressesEntity;

public interface ShippingAddressesService {
    public ShippingAddressesEntity addItems(long CUSTOMERID, int POSTALCODE, String STREET, int HOUSE, int FLAT);
}
