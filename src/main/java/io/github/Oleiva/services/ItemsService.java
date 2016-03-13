package io.github.Oleiva.services;


import io.github.Oleiva.entity.ItemsEntity;

public interface ItemsService {

    ItemsEntity addItems(String SKU, String NAME, long PRICE, long AMOUN);
    long amountItemInStock(long id);
    void removeFromStock(long item, long amount);
    long getAnte(long item,long amount);
}