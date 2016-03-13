package io.github.Oleiva.controllers;

import io.github.Oleiva.jpa.ShippingAddressesDao;
import io.github.Oleiva.entity.ShippingAddressesEntity;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/addresses")
public class ShippingAddressesController {
    public final Logger LOG = Logger.getLogger(this.getClass());

    @Autowired
    private ShippingAddressesDao shippingAddressesDao;

    @RequestMapping(value="",method = RequestMethod.GET)
    @ResponseBody
    public List<ShippingAddressesEntity> getAll() {
        return shippingAddressesDao.findAll();
    }

    @RequestMapping(value="/get-by-id/{id}",method = RequestMethod.GET)
    @ResponseBody
    public ShippingAddressesEntity getById(@PathVariable long id) {

        if (shippingAddressesDao.findOne(id) == null) {
            LOG.warn("Number of customer does not exist");
        }
             return shippingAddressesDao.findOne(id);
    }

}
