/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mamadoudiallo.bestdealsapi.service;

import com.mamadoudiallo.bestdealsapi.entities.Users;
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
@Path("users")
public class UsersFacadeREST extends AbstractFacade<Users> {
    @PersistenceContext(unitName = "com.mamadoudiallo_BestDealsApi_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    public UsersFacadeREST()
    {
        super(Users.class);
    }

    @POST
    @Override
    @Consumes(
    {
        "application/json"
    })
    public void create(Users entity)
    {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes(
    {
        "application/json"
    })
    public void edit(@PathParam("id") Integer id, Users entity)
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
    public Users find(@PathParam("id") Integer id)
    {
        return super.find(id);
    }
    
        
    @GET
    @Path("login/{email}")
    @Produces(
    {
        "application/json"
    })
    public Users find(@PathParam("email") String email)
    {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        // Query for a single object.
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery();
        Root user = criteriaQuery.from(Users.class);
        criteriaQuery.where(criteriaBuilder.equal(user.get("email"), criteriaBuilder.parameter(String.class, "email")));
        Query query = em.createQuery(criteriaQuery);
        query.setParameter("email", email);
        return (Users)query.getSingleResult(); 
    }

    @GET
    @Override
    @Produces(
    {
        "application/json"
    })
    public List<Users> findAll()
    {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces(
    {
        "application/json"
    })
    public List<Users> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to)
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
