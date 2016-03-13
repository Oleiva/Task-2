package io.github.Oleiva.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "ORDERS")
public class OrdersEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long ID;

    @NotNull
    private long CUSTOMER_ID;

    @NotNull
    private long ADDRESSES_ID;

    @NotNull
    private String STATUS;

//    @OneToMany(mappedBy = "ordersEntity",fetch = FetchType.LAZY)
    @OneToMany(mappedBy = "ordersEntity",fetch = FetchType.EAGER)
    private Set<TransactionsEntity> getTransaction;

//    @ManyToOne
//    @JoinColumn(name ="CUSTOMER_ID", insertable = false, updatable = false)
//    public CustomersEntity getCustomers;

    public OrdersEntity(){}
    public OrdersEntity(long ID){
        this.ID = ID;
    }

    public OrdersEntity(long CUSTOMER_ID, long ADDRESSES_ID, String STATUS) {
        this.CUSTOMER_ID = CUSTOMER_ID;
        this.ADDRESSES_ID = ADDRESSES_ID;
        this.STATUS = STATUS;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public long getCUSTOMER_ID() {
        return CUSTOMER_ID;
    }

    public void setCUSTOMER_ID(long CUSTOMER_ID) {
        this.CUSTOMER_ID = CUSTOMER_ID;
    }

    public long getADDRESSES_ID() {
        return ADDRESSES_ID;
    }

    public void setADDRESSES_ID(long ADDRESSES_ID) {
        this.ADDRESSES_ID = ADDRESSES_ID;
    }

    public String getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(String STATUS) {
        this.STATUS = STATUS;
    }

}