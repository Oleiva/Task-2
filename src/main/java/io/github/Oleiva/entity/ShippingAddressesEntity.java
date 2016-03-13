package io.github.Oleiva.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "SHIPPING_ADDRESSES")
public class ShippingAddressesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long ID;

    @NotNull
    private long CUSTOMER_ID;

    @NotNull
    private int POSTAL_CODE;

    @NotNull
    private String STREET;

    @NotNull
    private int HOUSE;

    private int FLAT;

    public ShippingAddressesEntity(){}

    public ShippingAddressesEntity(long ID){
        this.ID = ID;
    }

    public ShippingAddressesEntity(long CUSTOMER_ID, int POSTAL_CODE, String STREET, int HOUSE, int FLAT) {
        this.CUSTOMER_ID = CUSTOMER_ID;
        this.POSTAL_CODE = POSTAL_CODE;
        this.STREET = STREET;
        this.HOUSE = HOUSE;
        this.FLAT = FLAT;
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

    public int getPOSTAL_CODE() {
        return POSTAL_CODE;
    }

    public void setPOSTAL_CODE(int POSTAL_CODE) {
        this.POSTAL_CODE = POSTAL_CODE;
    }

    public String getSTREET() {
        return STREET;
    }

    public void setSTREET(String STREET) {
        this.STREET = STREET;
    }

    public int getHOUSE() {
        return HOUSE;
    }

    public void setHOUSE(int HOUSE) {
        this.HOUSE = HOUSE;
    }

    public int getFLAT() {
        return FLAT;
    }

    public void setFLAT(int FLAT) {
        this.FLAT = FLAT;
    }
}
