package io.github.Oleiva.controllers;

import io.github.Oleiva.jpa.CustomersDao;
import io.github.Oleiva.jpa.ItemsDao;
import io.github.Oleiva.jpa.OrdersDao;
import io.github.Oleiva.jpa.ShippingAddressesDao;
import io.github.Oleiva.dto.pojo.OrderPojo;
import io.github.Oleiva.dto.pojo.ResponsePojo;
import io.github.Oleiva.entity.OrdersEntity;
import io.github.Oleiva.entity.TransactionsEntity;
import io.github.Oleiva.services.CustomersService;
import io.github.Oleiva.services.ItemsService;
import io.github.Oleiva.services.OrdersService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrdersController {
    public final Logger LOG = Logger.getLogger(this.getClass());


    @Autowired
    private OrdersDao ordersDao;

    @Autowired
    private CustomersDao customersDao;

    @Autowired
    private ItemsDao itemsDao;

    @Autowired
    private ShippingAddressesDao shippingAddressesDao;

    @Autowired
    private ItemsService itemsService;

    @Autowired
    private CustomersService customersService;

    @Autowired
    private OrdersService ordersService;

    @RequestMapping(value="",method = RequestMethod.GET)
    @ResponseBody
    public List<OrdersEntity> getAll() {
        return ordersDao.findAll();
    }

    @RequestMapping(value="/get-by-id/{id}",method = RequestMethod.GET)
    @ResponseBody
    public OrdersEntity getById(@PathVariable long id) {

        if (ordersDao.findOne(id) == null) {
            LOG.warn("Number of orders does not exist");
        }
        return ordersDao.findOne(id);
    }


    @RequestMapping(value="/usersOrder/{id}",method = RequestMethod.GET)
    @ResponseBody
    public OrderPojo  usersOrder (@PathVariable long id,
                                  OrderPojo orderPojo) {

    try{ //TODO: Refactor this
        if (ordersDao.findOne(id) == null) {
            LOG.warn("Number of orders does not exist");
        }else{
            ArrayList itemList = new ArrayList();
            ArrayList<Long> itemsIdList = new ArrayList();
            ArrayList<Long> amountList = new ArrayList<>();
            ArrayList<Long> priceList = new ArrayList<>();

            for(TransactionsEntity el : ordersService.getItemsByOrderId(id)){
                itemsIdList.add(el.getItemId());
                amountList.add(el.getAmount());

                LOG.info("itemsIdList" + itemsIdList);
                LOG.info("amountList" + amountList);
            }

            for(int i=0;i<itemsIdList.size();i++){
                long x = (int) Integer.parseInt(itemsIdList.get(i).toString());
                itemList.add(itemsDao.findOne(x).getNAME());
                priceList.add(itemsDao.findOne(x).getPRICE());
            }

            long total = 0;
            for (int i = 0; i < itemList.size(); i++) {
                total = total + amountList.get(i)*priceList.get(i);
                LOG.warn("Total = "+total);
            }

            orderPojo.setListOfItems(itemList);
            orderPojo.setCoinsTotal(total);
        }

    }catch (Exception e){
        LOG.warn("Number of orders does not exist");
    }finally {
        return orderPojo;
    }

    }

    @RequestMapping(value="/add-item/{itemId}/{amount}",method = RequestMethod.POST)
    @ResponseBody
    public ResponsePojo  addItemToLastOrder( @PathVariable(value = "itemId")   long itemId,
                                             @PathVariable(value = "amount") long amount,
                                             ResponsePojo responsePojo){

            int swither = 0;
            long lastOrderIndex = ordersService.getLastOrder();
            long cutomerId = ordersService.getCustomerFromOrder(lastOrderIndex);
        try{
            if (itemsDao.findOne(itemId)!=null){ // Are there items in the database ?
                swither++;
                LOG.info("item was found");
            }else{
                LOG.info("item not found");
                responsePojo.setMessage(responsePojo.getMessage()+" Item.. not found. ");
            }
        }catch (Exception ex){
            responsePojo.setMessage(responsePojo.getMessage()+" Exeption item ");
        }

        try{
            if (swither == 1) {
                if ( itemsService.amountItemInStock(itemId)>= amount ) { // Is there enough product in stock?

                    long ante = itemsService.getAnte(itemId,amount);
                    if(customersDao.findOne(cutomerId).getAVAILABLE_CREDIT() > ante ) {

//                     В один этап, без резервирования  +
//                     Отнять столько позиций           +
//                     Отнять столко денег              +
//                     Создать плетеж
//
                        itemsService.removeFromStock(itemId,amount);
                        customersService.removeFromCredit(cutomerId,ante);
                        ordersService.addExistOrder(lastOrderIndex,  itemId,  amount);
                        responsePojo.setMessage("All present and correct. Thank you for your purchase");
                    }else {
                        LOG.info("There is not enough money");
                        responsePojo.setMessage(responsePojo.getMessage() + " There is not enough money ");
                    }
                }else {
                    LOG.info("This position is not enough in stock");
                    responsePojo.setMessage(responsePojo.getMessage() + " This position is not enough in stock ");
                }
            }else{
                responsePojo.setMessage("Some Errors occupied "+responsePojo.getMessage());
            }
        }catch (Exception ex){
            responsePojo.setMessage(responsePojo.getMessage()+"Exeption swither ");
        }
        return responsePojo;
    }


    @RequestMapping(value="/add-new/{cust}/{adress}/{item}/{amount}",method = RequestMethod.POST)
    @ResponseBody
    public ResponsePojo  addItemToNewOrder(@PathVariable(value = "cust")   long cust,
                                          @PathVariable(value = "adress") long adress,
                                          @PathVariable(value = "item")   long item,
                                          @PathVariable(value = "amount") long amount,
                                           ResponsePojo responsePojo){
        int swither = 0;

        try{
            if(customersDao.findOne(cust) !=null){ // Is there a buyer?
                swither ++;
                LOG.info("cust was found");
            }else {
                LOG.info("cust not found");
                responsePojo.setMessage(responsePojo.getMessage()+" Cust.. not found. ");
            }
        }catch (Exception ex){
            responsePojo.setMessage(responsePojo.getMessage()+" Exeption cust");
        }

       try{
           if (shippingAddressesDao.findOne(adress)!= null){ // Is there a delivery address?
               swither ++;
               LOG.info("adress was found");

           }else {
               LOG.info("cust not found");
               responsePojo.setMessage(responsePojo.getMessage()+" Cust.. not found. ");
           }
       }catch (Exception ex){
           responsePojo.setMessage(responsePojo.getMessage()+"Exeption ADress ");
       }

       try{
           if (itemsDao.findOne(item)!=null){ // Are there items in the database ?
               swither++;
               LOG.info("item was found");
           }else{
               LOG.info("item not found");
               responsePojo.setMessage(responsePojo.getMessage()+" Item.. not found. ");
           }
       }catch (Exception ex){
           responsePojo.setMessage(responsePojo.getMessage()+" Exeption item ");
       }

     try{
         if (swither == 3) {
             if ( itemsService.amountItemInStock(item)>= amount ) { // Is there enough product in stock?

                 long ante = itemsService.getAnte(item,amount);
                 if(customersDao.findOne(cust).getAVAILABLE_CREDIT() > ante ) {

                     itemsService.removeFromStock(item,amount);
                     customersService.removeFromCredit(cust,ante);
                     ordersService.addNewOrder(cust,adress,item,amount);

                     responsePojo.setMessage("AlL OK. Try to bay");

                 }else {
                     LOG.info("There is not enough money");
                     responsePojo.setMessage(responsePojo.getMessage() + " There is not enough money ");
                 }
             }else {
                 LOG.info("This position is not enough in stock");
                 responsePojo.setMessage(responsePojo.getMessage() + " This position is not enough in stock ");
             }
         }else{
             responsePojo.setMessage("Some Errors occupied "+responsePojo.getMessage());
         }

     }catch (Exception ex){
         responsePojo.setMessage(responsePojo.getMessage()+"Exeption swither ");
     }

        return responsePojo;
    }

}
