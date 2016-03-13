package io.github.Oleiva.services.Impl;

import io.github.Oleiva.jpa.CustomersDao;
import io.github.Oleiva.entity.CustomersEntity;
import io.github.Oleiva.services.CustomersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CustomersServiceImpl implements CustomersService {

    @Autowired
    private CustomersDao customersDao;

    public CustomersEntity addCustomers(String USERNAME, String EMAIL, long AVAILABLECREDIT){
        CustomersEntity customersEntity = new CustomersEntity(USERNAME, EMAIL, AVAILABLECREDIT);

        return customersDao.saveAndFlush(customersEntity);
    }

    @Override
    public void removeFromCredit(long cust, long ante){
       long availCredit =  customersDao.findOne(cust).getAVAILABLE_CREDIT();
        customersDao.findOne(cust).setAVAILABLECREDIT(availCredit - ante);
    }

}
