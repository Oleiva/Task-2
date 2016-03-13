package io.github.Oleiva.services.Impl;

import io.github.Oleiva.jpa.OrdersDao;
import io.github.Oleiva.jpa.TransactionsDao;
import io.github.Oleiva.entity.OrdersEntity;
import io.github.Oleiva.entity.TransactionsEntity;
import io.github.Oleiva.services.OrdersService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OrdersServiceImpl implements OrdersService {
    public final Logger LOG = Logger.getLogger(this.getClass());

   @Autowired
    private OrdersDao ordersDao;

    @Autowired
    private TransactionsDao transactionsDao;

    @Override
    public void addNewOrder(long customerId, long addressesId, long itemId, long amount){

        OrdersEntity ordersEntity = new OrdersEntity(customerId,  addressesId, "PAID");

        ordersDao.saveAndFlush(ordersEntity);
        long orderId = ordersEntity.getID();

        LOG.info("## orderId = "+ orderId);

        TransactionsEntity transactionsEntity = new TransactionsEntity(orderId, itemId, amount);
        transactionsDao.saveAndFlush(transactionsEntity);
    }

    @Override
    public void addExistOrder(long orderId, long itemId, long amount){
        TransactionsEntity transactionsEntity = new TransactionsEntity( orderId,  itemId,  amount);
        transactionsDao.saveAndFlush(transactionsEntity);
    }

    @Override
    public List<TransactionsEntity> getItemsByOrderId(long order){
        return transactionsDao.findByOrderId(order);
    }

    @Override
    public long getLastOrder() {
        long LastOrder =1;
        if(ordersDao.findLastIndex() != 0){
            LastOrder =  ordersDao.findLastIndex();
        }else {
            LastOrder = 1;
        }
        return LastOrder;
    }

    @Override
    public long getCustomerFromOrder(long order) {
        return ordersDao.findOne(order).getCUSTOMER_ID();
    }

    @Override
    public long getCustomerAddress(long order){
        long getCustomer = ordersDao.findOne(order).getADDRESSES_ID();
        LOG.info("getCustomerAddress = "+getCustomer);
        return getCustomer;
    }
}
