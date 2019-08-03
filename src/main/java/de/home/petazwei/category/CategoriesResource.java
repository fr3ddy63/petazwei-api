package de.home.petazwei.category;

import de.home.petazwei.Parameter;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@RequestScoped
@Path("/categories")
@Produces(MediaType.APPLICATION_JSON)
public class CategoriesResource {

    @Inject
    private CategoryService service;

    @GET
    public Response getCategories(@Context UriInfo info, @Valid @BeanParam Parameter param) {
        List<Category> categories = this.service.find(param);
        return Response.ok(categories).build();
    }

    @GET
    @Path("{category-id:[1-9][0-9]*}")
    public Response getCategory(@PathParam("category-id") Integer id) {
        Category category = this.service.find(id).orElseThrow(NotFoundException::new);
        return Response.ok(category).build();
    }
}