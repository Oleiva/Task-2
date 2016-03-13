package io.github.Oleiva.controllers;

import io.github.Oleiva.dao.ItemsDao;
//import io.github.Oleiva.dto.pojo.ResponseDTO;
import io.github.Oleiva.entity.CustomersEntity;
import io.github.Oleiva.entity.ItemsEntity;
import org.apache.log4j.Logger;
import org.jboss.logging.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemsController  {
    public final Logger LOG = Logger.getLogger(this.getClass());

    @Autowired
    private ItemsDao itemsDao;

    @RequestMapping(value="",method = RequestMethod.GET)
    @ResponseBody
    public List<ItemsEntity> getAll() {
        return itemsDao.findAll();
    }

    @RequestMapping(value="/get-by-id/{id}",method = RequestMethod.GET)
    @ResponseBody
    public ItemsEntity getById(@PathVariable long id) {

        if (itemsDao.findOne(id) == null) {
            LOG.warn("Number of item  does not exist");
        }
        return itemsDao.findOne(id);
    }
}
