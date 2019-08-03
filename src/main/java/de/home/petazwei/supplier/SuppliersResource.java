package de.home.petazwei.supplier;

import de.home.petazwei.Parameter;
import de.home.petazwei.product.Product;

import java.util.List;
import java.util.Set;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@RequestScoped
@Path("/suppliers")
@Produces(MediaType.APPLICATION_JSON)
public class SuppliersResource {

    @Inject
    private SupplierService service;

    @GET
    public Response getSuppliers(@Context UriInfo info, @Valid @BeanParam Parameter param) {
        List<Supplier> suppliers = this.service.find(param);
        JsonArrayBuilder jab = Json.createArrayBuilder();
        suppliers.forEach(supplier -> jab.add(Json.createObjectBuilder()
            .add("id", supplier.getId())
            .add("name", supplier.getName())
            .add("products", info.getAbsolutePathBuilder()
                .path(supplier.getId().toString())
                .path("products")
                .build()
                .toString())));
        return Response.ok(suppliers).build();
    }

    @GET
    @Path("{id}")
    public Response getSupplier(@PathParam("id") Integer id) {
        Supplier supplier = this.service.find(id).orElseThrow(NotFoundException::new);
        return Response.ok(supplier).build();
    }
}