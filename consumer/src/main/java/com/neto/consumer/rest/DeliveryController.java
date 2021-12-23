package com.neto.consumer.rest;

import com.fasterxml.jackson.databind.JsonNode;
import com.neto.consumer.config.RequestQueueConfig;
import com.neto.consumer.model.Delivery;
import com.neto.consumer.model.Request;
import com.neto.consumer.repository.DeliveryRepository;
import com.neto.consumer.util.DeliveryModelAssembler;
import com.neto.consumer.util.DeliveryNotFoundException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
@RestController
public class DeliveryController {

    private final DeliveryRepository repository;
    private final DeliveryModelAssembler assembler;

    DeliveryController(DeliveryRepository repository, DeliveryModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    @GetMapping("/delivery")
    public CollectionModel<EntityModel<Delivery>> all() {

        List<EntityModel<Delivery>> orders = repository.findAll().stream() //
                .map(assembler::toModel) //
                .collect(Collectors.toList());

        return CollectionModel.of(orders, //
                linkTo(methodOn(DeliveryController.class).all()).withSelfRel());
    }

    @GetMapping("/delivery/{id}")
    public EntityModel<Delivery> one(@PathVariable Long id) {

        Delivery delivery = repository.findById(id) //
                .orElseThrow(() -> new DeliveryNotFoundException(id));

        return assembler.toModel(delivery);
    }

    @RabbitListener (queues = RequestQueueConfig.QUEUE)
    public void listener(Request request){
        Delivery newDelivery = new Delivery();
        newDelivery.setIdRequest(request.getIdRequest());
        newDelivery.setDeliveryAddress(request.getDeliveryAdress());
        Delivery savedDelivery = repository.saveAndFlush(newDelivery);
        System.out.println("The delivery " + savedDelivery.getIdDelivery() + " was created successfully");
    }
}
