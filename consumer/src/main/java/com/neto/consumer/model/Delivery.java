package com.neto.consumer.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Delivery {

    private @Id @GeneratedValue Long idDelivery;
    private Long idRequest;
    private String deliveryAddress;

    public Delivery(){};

    public Delivery(Long idRequest, String deliveryAddress) {
        this.idRequest = idRequest;
        this.deliveryAddress = deliveryAddress;
    }

    public Long getIdDelivery() {
        return idDelivery;
    }

    public void setIdDelivery(Long idDelivery) {
        this.idDelivery = idDelivery;
    }

    public Long getIdRequest() {
        return idRequest;
    }

    public void setIdRequest(Long idRequest) {
        this.idRequest = idRequest;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Delivery delivery = (Delivery) o;
        return idDelivery.equals(delivery.idDelivery)
                && idRequest.equals(delivery.idRequest)
                && deliveryAddress.equals(delivery.deliveryAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idDelivery, idRequest, deliveryAddress);
    }

    @Override
    public String toString() {
        return "Delivery{" +
                "idDelivery=" + idDelivery +
                ", idRequest=" + idRequest +
                ", deliveryAddress='" + deliveryAddress + '\'' +
                '}';
    }
}
