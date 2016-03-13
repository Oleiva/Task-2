package io.github.Oleiva.services;

import io.github.Oleiva.entity.OrdersEntity;
import io.github.Oleiva.entity.TransactionsEntity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public interface OrdersService {

    void addNewOrder(long customerId, long addressesId, long itemId, long amount);
    void addExistOrder(long orderId, long itemId, long amount);
    List<TransactionsEntity> getItemsByOrderId(long order);
    long getLastOrder();
    long getCustomerAddress(long order);
    long getCustomerFromOrder(long order);

}
