package io.github.Oleiva.services.Impl;

import io.github.Oleiva.jpa.ShippingAddressesDao;
import io.github.Oleiva.entity.ShippingAddressesEntity;
import io.github.Oleiva.services.ShippingAddressesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ShippingAddressesServiceImpl implements ShippingAddressesService {

    @Autowired
    private ShippingAddressesDao shippingAddressesDao;

    @Override
    public ShippingAddressesEntity addItems(long CUSTOMERID, int POSTALCODE, String STREET, int HOUSE, int FLAT){
        ShippingAddressesEntity shippingAddressesEntity = new ShippingAddressesEntity( CUSTOMERID,POSTALCODE,  STREET, HOUSE, FLAT);
        return shippingAddressesDao.saveAndFlush(shippingAddressesEntity);
    }

}
