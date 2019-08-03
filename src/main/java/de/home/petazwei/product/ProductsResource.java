package de.home.petazwei.product;

import de.home.petazwei.Parameter;
import de.home.petazwei.UriBuilder;
import de.home.petazwei.supplier.Supplier;
import de.home.petazwei.supplier.SuppliersResource;

import java.util.List;
import java.util.Set;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

@RequestScoped
@Path("/products")
@Produces(MediaType.APPLICATION_JSON)
public class ProductsResource {

    @Inject
    private ProductService service;

    private JsonArray getSupplierObjects(Set<Supplier> suppliers, UriInfo info) {
        JsonArrayBuilder jab = Json.createArrayBuilder();
        suppliers.forEach(supplier -> { jab.add(Json.createObjectBuilder()
                .add("id", supplier.getId())
                .add("name", supplier.getName())
                .add("uri", info.getBaseUriBuilder()
                        .path(SuppliersResource.class)
                        .path(SuppliersResource.class, "getSupplier")
                        .build(supplier.getId())
                        .toString()));
        });
        return jab.build();
    }

    @GET
    public Response getProducts(@Context UriInfo info, @Valid @BeanParam Parameter param) {
        List<Product> products = this.service.find(param);
        Long countProducts = this.service.count();
        JsonArrayBuilder jab = Json.createArrayBuilder();
        products.forEach(product -> jab.add(Json.createObjectBuilder()
            .add("id", product.getId())
            .add("name", product.getName())
            .add("product", product.getManufacturer())
            .add("suppliers", getSupplierObjects(product.getSuppliers(), info))));
        List<Link> links = UriBuilder.links(info, param, countProducts);
        return Response.ok(jab.build()).links(links.toArray(new Link[links.size()])).build();
    }

    @GET
    @Path("{id}")
    public Response getProduct(@Context UriInfo info, @PathParam("id") Integer id) {
        Product product = this.service.find(id).orElseThrow(NotFoundException::new);
        JsonArrayBuilder jab = Json.createArrayBuilder();
        jab.add(Json.createObjectBuilder()
                .add("id", product.getId())
                .add("name", product.getName())
                .add("product", product.getManufacturer())
                .add("suppliers", getSupplierObjects(product.getSuppliers(), info)));
        return Response.ok(jab.build()).build();
    }
}