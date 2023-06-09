package com.bottleneck.performance.quarkus.demo.entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * This is an entry in the guestbook containing message to other visitors
 */
@Entity
public class GuestBookEntry {

    @Id
    @GeneratedValue
    private Long id;

    /**
     * name of the author
     */
    @JsonProperty("by")
    @Column(name="author")
    private String by;

    /**
     * content of this entry
     */
    @JsonProperty("message")
    @Column(name="message")
    private String message;

    /**
     * timestamp of creation
     */
    @JsonProperty("when")
    @Column(name="created_on")
    @CreationTimestamp
    private LocalDateTime when;

    protected GuestBookEntry() {}

    @JsonCreator
    public GuestBookEntry(@JsonProperty(value = "by", required = true) String by,
                          @JsonProperty(value = "message", required = true) String message,
                          @JsonProperty(value = "when", required = false) LocalDateTime when) {
        this.by = by;
        this.message = message;
        this.when = when;
    }

    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getWhen() {
        return when;
    }

    public void setWhen(LocalDateTime when) {
        this.when = when;
    }

    @JsonGetter("id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // to implement equals() and hashcode() on JPA entities is not recommended…
}
