/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.PathSegment;

import com.mycompany.entities.OrderedProduct;
import com.mycompany.entities.OrderedProductPK;


/**
 *
 * @author User
 */

@Path("entities.orderedproduct")
public class OrderedProductFacadeREST extends AbstractFacade<OrderedProduct> {
	EntityManagerFactory emfactory = Persistence
			.createEntityManagerFactory("elink_JPA");

	EntityManager em = emfactory.createEntityManager();

    private OrderedProductPK getPrimaryKey(PathSegment pathSegment) {
        /*
         * pathSemgent represents a URI path segment and any associated matrix parameters.
         * URI path part is supposed to be in form of 'somePath;customerOrderId=customerOrderIdValue;productId=productIdValue'.
         * Here 'somePath' is a result of getPath() method invocation and
         * it is ignored in the following code.
         * Matrix parameters are used as field names to build a primary key instance.
         */
        com.mycompany.entities.OrderedProductPK key = new com.mycompany.entities.OrderedProductPK();
        javax.ws.rs.core.MultivaluedMap<String, String> map = pathSegment.getMatrixParameters();
        java.util.List<String> customerOrderId = map.get("customerOrderId");
        if (customerOrderId != null && !customerOrderId.isEmpty()) {
            key.setCustomerOrderId(new java.lang.Integer(customerOrderId.get(0)));
        }
        java.util.List<String> productId = map.get("productId");
        if (productId != null && !productId.isEmpty()) {
            key.setProductId(new java.lang.Integer(productId.get(0)));
        }
        return key;
    }

    public OrderedProductFacadeREST() {
        super(OrderedProduct.class);
    }

    @POST
    @Override
    @Consumes({"application/xml", "application/json"})
    public void create(OrderedProduct entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") PathSegment id, OrderedProduct entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") PathSegment id) {
        com.mycompany.entities.OrderedProductPK key = getPrimaryKey(id);
        super.remove(super.find(key));
    }

    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public OrderedProduct find(@PathParam("id") PathSegment id) {
        com.mycompany.entities.OrderedProductPK key = getPrimaryKey(id);
        return super.find(key);
    }

    @GET
    @Override
    @Produces({"application/xml", "application/json"})
    public List<OrderedProduct> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public List<OrderedProduct> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
