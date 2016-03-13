package io.github.Oleiva.controllers;

import io.github.Oleiva.jpa.CustomersDao;
import io.github.Oleiva.entity.CustomersEntity;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/customers")
public class CustomersController {
    public final Logger LOG = Logger.getLogger(this.getClass());

    @Autowired
    private CustomersDao customersDao;

    @RequestMapping(value="",method = RequestMethod.GET)
    @ResponseBody
    public List<CustomersEntity> getAll() {
        return customersDao.findAll();
    }

    @RequestMapping(value="/get-by-id/{id}",method = RequestMethod.GET)
    @ResponseBody
    public CustomersEntity getById(@PathVariable long id) {

        if (customersDao.findOne(id) == null) {
            LOG.warn("Number of customer does not exist");
        }
        return customersDao.findOne(id);
    }

}
