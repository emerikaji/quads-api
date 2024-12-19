package com.bigdatam1.quads;

import com.google.cloud.spring.data.datastore.core.mapping.Entity;
import org.springframework.data.annotation.Id;

@Entity
public class Quad {
    @Id
    private Long id; // Auto-generated unique identifier
    private String graph;
    private String subject;
    private String predicate;
    private String object;

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getGraph() { return graph; }
    public void setGraph(String graph) { this.graph = graph; }

    public String getSubject() { return subject; }
    public void setSubject(String subject) { this.subject = subject; }

    public String getPredicate() { return predicate; }
    public void setPredicate(String predicate) { this.predicate = predicate; }

    public String getObject() { return object; }
    public void setObject(String object) { this.object = object; }
}
