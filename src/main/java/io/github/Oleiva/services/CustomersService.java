package io.github.Oleiva.services;

import io.github.Oleiva.entity.CustomersEntity;
import io.github.Oleiva.entity.ItemsEntity;


public interface CustomersService {

    public CustomersEntity addCustomers(String USERNAME, String EMAIL, long AVAILABLECREDIT);

    public void removeFromCredit(long cust, long ante);

}