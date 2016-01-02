/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mamadoudiallo.bestdealsapi.service;

import com.mamadoudiallo.bestdealsapi.entities.Products;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author Mamadou
 */
@Stateless
@Path("products")
public class ProductsFacadeREST extends AbstractFacade<Products> {
    @PersistenceContext(unitName = "com.mamadoudiallo_BestDealsApi_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    public ProductsFacadeREST()
    {
        super(Products.class);
    }

    @POST
    @Override
    @Consumes(
    {
        "application/json"
    })
    public void create(Products entity)
    {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes(
    {
        "application/json"
    })
    public void edit(@PathParam("id") Integer id, Products entity)
    {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id)
    {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces(
    {
        "application/json"
    })
    public Products find(@PathParam("id") Integer id)
    {
        return super.find(id);
    }
    
    @GET
    @Path("barcode/{barcode}")
    @Produces(
    {
        "application/json"
    })
    public Products find(@PathParam("barcode") String barcode)
    {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        // Query for a single object.
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery();
        Root product = criteriaQuery.from(Products.class);
        criteriaQuery.where(criteriaBuilder.equal(product.get("barcode"), criteriaBuilder.parameter(String.class, "barcode")));
        Query query = em.createQuery(criteriaQuery);
        query.setParameter("barcode", barcode);
        return (Products)query.getSingleResult(); 
    }

    @GET
    @Override
    @Produces(
    {
        "application/json"
    })
    public List<Products> findAll()
    {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces(
    {
        "application/json"
    })
    public List<Products> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to)
    {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST()
    {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager()
    {
        return em;
    }
    
}
