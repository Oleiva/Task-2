package io.github.Oleiva.services.Impl;

import io.github.Oleiva.jpa.ItemsDao;
import io.github.Oleiva.entity.ItemsEntity;
import io.github.Oleiva.services.ItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ItemsServiceImpl implements ItemsService {

    @Autowired
    private ItemsDao itemsDao;

    @Override
    public ItemsEntity addItems(String SKU, String NAME, long PRICE, long AMOUN){
        ItemsEntity itemsEntity = new ItemsEntity(SKU,  NAME, PRICE, AMOUN);

        return itemsDao.saveAndFlush(itemsEntity);
    }

    @Override
    public long amountItemInStock(long id){
      return   itemsDao.findOne(id).getAMOUNT();

    }

    @Override
    public void removeFromStock(long item, long amount){
       long newAmount  = itemsDao.findOne(item).getAMOUNT()- amount;
       itemsDao.findOne(item).setAMOUNT(newAmount);
    }

    public long getAnte(long item,long amount){
        return (itemsDao.findOne(item).getPRICE()) * amount;
    }

}
