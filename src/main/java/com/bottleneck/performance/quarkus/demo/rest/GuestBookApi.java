package com.bottleneck.performance.quarkus.demo.rest;

import com.bottleneck.performance.quarkus.demo.beans.GuestBook;
import com.bottleneck.performance.quarkus.demo.entities.GuestBookEntry;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/guestbook")
public class GuestBookApi {

    @Inject
    GuestBook guestBook;

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public void addEntry(GuestBookEntry entry) {
        guestBook.persist(entry);
    }

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<GuestBookEntry> getAllEntries() {
        return guestBook.listAll();
    }
}
