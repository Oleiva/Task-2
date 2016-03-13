package io.github.Oleiva.dto.pojo;

import io.github.Oleiva.entity.OrdersEntity;
import io.github.Oleiva.entity.TransactionsEntity;

import java.util.List;

public class OrderPojo {

    private long coinsTotal;

    private List<TransactionsEntity> listOfItems ;

    public List<TransactionsEntity> getListOfItems() {
        return listOfItems;
    }

    public void setListOfItems(List<TransactionsEntity> listOfItems) {
        this.listOfItems = listOfItems;
    }

    public long getCoinsTotal() {
        return coinsTotal;
    }

    public void setCoinsTotal(long coinsTotal) {
        this.coinsTotal = coinsTotal;
    }
}
