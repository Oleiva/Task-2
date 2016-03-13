package io.github.Oleiva.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "TRANSACTION")
public class TransactionsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long ID;

    @NotNull

    private long orderId;

    @NotNull
    private long itemId;

    @NotNull
    private long amount;

    private TransactionsEntity(){}

    public TransactionsEntity(long orderId, long itemId, long amount) {
        this.orderId = orderId;
        this.itemId = itemId;
        this.amount = amount;
    }

    @ManyToOne(fetch = FetchType.LAZY,optional=true)
    @JoinColumn(name="orderId", nullable=false,insertable = false,updatable = false)
    private OrdersEntity ordersEntity;

//    public OrdersEntity getOrdersEntity() {
//        return ordersEntity;
//    }

//    @OneToMany(mappedBy = "itemsEntity")
//    private Set<ItemsEntity> itemsEntitySet;

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public long getItemId() {
        return itemId;
    }

    public void setItemId(long itemId) {
        this.itemId = itemId;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }
}
