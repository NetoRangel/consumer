package com.neto.consumer.util;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.neto.consumer.model.Delivery;
import com.neto.consumer.rest.DeliveryController;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class DeliveryModelAssembler implements RepresentationModelAssembler<Delivery, EntityModel<Delivery>> {

    @Override
    public EntityModel<Delivery> toModel(Delivery delivery) {

        return EntityModel.of(delivery, //
                linkTo(methodOn(DeliveryController.class).one(delivery.getIdDelivery())).withSelfRel(),
                linkTo(methodOn(DeliveryController.class).all()).withRel("delivery"));
    }
}