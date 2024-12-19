package com.bigdatam1.quads;

import com.google.cloud.spring.data.datastore.repository.DatastoreRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuadRepository extends DatastoreRepository<Quad, Long> {
    Iterable<Quad> findByPredicate(String predicate);
}
