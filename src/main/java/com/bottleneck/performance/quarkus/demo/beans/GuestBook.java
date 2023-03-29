package com.bottleneck.performance.quarkus.demo.beans;

import com.bottleneck.performance.quarkus.demo.entities.GuestBookEntry;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class GuestBook implements PanacheRepository<GuestBookEntry> {
    // auto implemented
}
