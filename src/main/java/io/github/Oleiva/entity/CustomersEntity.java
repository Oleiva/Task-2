package io.github.Oleiva.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "CUSTOMERS")
public class CustomersEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long ID;

    @NotNull
    private String USERNAME;

    @NotNull
    @Column(unique=true, name = "EMAIL")
    private String EMAIL;

    private long AVAILABLE_CREDIT;

//    @OneToMany(mappedBy = "ID" )
//    private List<ShippingAddressesEntity> shippingAddressesEntities;
//
//    @OneToMany(mappedBy = "ID")
//    private List<OrdersEntity> ordersEntities;

    public CustomersEntity(){}

    public CustomersEntity(long ID) {
        this.ID = ID;
    }

    public CustomersEntity(String USERNAME, String EMAIL, long AVAILABLE_CREDIT) {
        this.USERNAME = USERNAME;
        this.EMAIL = EMAIL;
        this.AVAILABLE_CREDIT = AVAILABLE_CREDIT;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getUSERNAME() {
        return USERNAME;
    }

    public void setUSERNAME(String USERNAME) {
        this.USERNAME = USERNAME;
    }

    public String getEMAIL() {
        return EMAIL;
    }

    public void setEMAIL(String EMAIL) {
        this.EMAIL = EMAIL;
    }

    public long getAVAILABLE_CREDIT() {
        return AVAILABLE_CREDIT;
    }

    public void setAVAILABLECREDIT(long AVAILABLECREDIT) {
        this.AVAILABLE_CREDIT = AVAILABLECREDIT;
    }
}
