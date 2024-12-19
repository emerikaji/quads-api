# Quads API

## Reference

### GET /api/quads

#### Description

This method returns a list of quads and a cursor that can be passed to continue searching if the search limit of 50 is reached.

The parameters allow filtering over any part of the quad.

#### Specification

Query params:
- graph
- subject
- predicate
- object
- cursor

Example result:
```json
{
    "results": [{
        "id": 5634161670881280,
        "graph": "http://example.org/graph1",
        "subject": "http://example.org/resource1",
        "predicate": "http://schema.org/name",
        "object": "Alice"
    }],
    "nextCursor": "CiMSHWoIZX5xdWFkczFyEQsSBHF1YWQYgICA6NeHgQoMGAAgAA=="
}
```

### POST /api/quads

#### Description

This method creates a quad from its json, and returns that quad if it was added properly. It requires a valid Google token as authentication.

#### Specification

Headers:
- Authorization: Bearer jwt

Body:
- json Quad

Body example:
```json
{
    "graph": "exampleGraph",
    "subject": "exampleSubject",
    "predicate": "examplePredicate",
    "object": "exampleObject"
}
```

Example result:
```json
{
    "id": 1234,
    "graph": "exampleGraph",
    "subject": "exampleSubject",
    "predicate": "examplePredicate",
    "object": "exampleObject"
}
```