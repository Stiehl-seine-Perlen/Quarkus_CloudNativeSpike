package com.bottleneck.performance.quarkus.demo.beans;

import com.bottleneck.performance.quarkus.demo.entities.GuestBookEntry;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class GuestBook {

    @Inject
    EntityManager entityManager;

    public GuestBook() {
    }

    public List<GuestBookEntry> getEntries() {
        return entityManager.createQuery("SELECT a FROM GuestBookEntry a", GuestBookEntry.class).getResultList();
    }

    @Transactional
    public void addEntry(GuestBookEntry entry) {
        entityManager.persist(entry);
    }
}
