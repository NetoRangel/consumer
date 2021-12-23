package com.neto.consumer.util;

public class DeliveryNotFoundException extends RuntimeException {

    public DeliveryNotFoundException(Long idDelivery) {
        super("Could not find Client " + idDelivery);
    }
}
