package de.home.petazwei.subcategory;

import de.home.petazwei.Parameter;
import de.home.petazwei.category.CategoriesResource;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@RequestScoped
@Path("/subcategories")
@Produces(MediaType.APPLICATION_JSON)
public class SubcategoriesResource {

    @Inject
    private SubcategoryService service;

    @GET
    public Response getSubcategories(@Context UriInfo info, @Valid @BeanParam Parameter param) {
        List<Subcategory> subcategories = this.service.find(param);
        JsonArrayBuilder ab = Json.createArrayBuilder();
        subcategories.forEach(subcategory -> ab.add(Json.createObjectBuilder()
                .add("id", subcategory.getId())
                .add("name", subcategory.getName())
                .add("category", info.getBaseUriBuilder()
                        .path(CategoriesResource.class)
                        .path(CategoriesResource.class, "get")
                        .build(subcategory.getCategory().getId())
                        .toString())));
        return Response.ok(ab.build()).build();
    }

    @GET
    @Path("{id}")
    public Response getSubcategory(@PathParam("id") Integer id) {
        Subcategory subcategory = this.service.find(id).orElseThrow(NotFoundException::new);
        return Response.ok(subcategory).build();
    }
}