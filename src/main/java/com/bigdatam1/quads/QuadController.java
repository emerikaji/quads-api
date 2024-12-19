package com.bigdatam1.quads;

import java.util.ArrayList;
import java.util.List;

import com.google.cloud.datastore.Cursor;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.Query;
import com.google.cloud.datastore.StructuredQuery.Builder;
import com.google.cloud.datastore.StructuredQuery.Filter;
import com.google.cloud.datastore.StructuredQuery.CompositeFilter;
import com.google.cloud.datastore.StructuredQuery.PropertyFilter;
import com.google.cloud.spring.data.datastore.core.DatastoreResultsIterable;
import com.google.cloud.spring.data.datastore.core.DatastoreTemplate;
import org.springframework.security.core.Authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/quads")
public class QuadController {

    @Autowired
    private DatastoreTemplate datastoreTemplate;

    @Autowired
    private QuadRepository quadRepository;

    
    @PostMapping
    public Quad createQuad(@RequestBody Quad quad, Authentication authentication) {
        return quadRepository.save(quad);
    }
    
    @GetMapping
    public PagedResponse<Quad> getQuadsWithCursor(
        @RequestParam(required = false) String graph,
        @RequestParam(required = false) String subject,
        @RequestParam(required = false) String predicate,
        @RequestParam(required = false) String object,
        @RequestParam(required = false) String cursor) {

        List<Filter> filters = new ArrayList<>();

        if (graph != null) {
            filters.add(PropertyFilter.eq("graph", graph));
        }
        if (subject != null) {
            filters.add(PropertyFilter.eq("subject", subject));
        }
        if (predicate != null) {
            filters.add(PropertyFilter.eq("predicate", predicate));
        }
        if (object != null) {
            filters.add(PropertyFilter.eq("object", object));
        }

        Builder<Entity> builder = Query.newEntityQueryBuilder()
            .setKind("quad")
            .setLimit(50);

        if (filters.size() > 0) {
            builder = builder.setFilter(CompositeFilter.and(filters.get(0), filters.subList(1, filters.size()).toArray(new Filter[0])));
        }

        if (cursor != null) {
            builder = builder.setStartCursor(Cursor.fromUrlSafe(cursor));
        }

        DatastoreResultsIterable<Quad> results = datastoreTemplate.query(builder.build(), Quad.class);

        return new PagedResponse<Quad>(results.toList(), results.getCursor().toUrlSafe());
    }

    public static class PagedResponse<T> {
        private List<T> results;
        private String nextCursor;

        public PagedResponse(List<T> results, String nextCursor) {
            this.results = results;
            this.nextCursor = nextCursor;
        }

        public List<T> getResults() {
            return results;
        }

        public String getNextCursor() {
            return nextCursor;
        }
    }
}
