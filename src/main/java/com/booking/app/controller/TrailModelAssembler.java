package com.booking.app.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.booking.app.entity.Trail;

/**
 * @author vijaysingh
 */
@Component
public class TrailModelAssembler implements RepresentationModelAssembler<Trail, EntityModel<Trail>> {

	@Override
	public EntityModel<Trail> toModel(Trail trail) {
		return EntityModel.of(trail, linkTo(methodOn(BookHikingController.class).oneTrail(trail.getId())).withSelfRel(),
				linkTo(methodOn(BookHikingController.class).allTrails()).withRel("trails"));
	}

	public CollectionModel<EntityModel<Trail>> toModel(List<EntityModel<Trail>> allTrails) {
		return CollectionModel.of(allTrails, linkTo(methodOn(BookHikingController.class).allTrails()).withSelfRel());
	}
}
