# Quads API (https://quads1.ew.r.appspot.com)

## Reference

### GET /api/quads

#### Description

This method returns a list of quads and a cursor that can be passed to continue searching if the search limit of 50 is reached.

The parameters allow filtering over any part of the quad.

#### Specification

Query params:
- graph (string)
- subject (string)
- predicate (string)
- object (string)
- cursor (string)

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

Body (json):
- Quad (json Object)

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

### POST /api/quads/file

#### Description

This method creates all the triples contained in a given .ttl file, within the specified graph name. It returns the list of the added triples.

#### Specification

Headers:
- Authorization: Bearer jwt

Body (FORM):
- graphName (string)
- file (string/file)

Example result:
```json
[
    {"id":5629499534213120,"graph":"http://example.com/graph","subject":"http://example.com/Subject1","predicate":"http://schema.org/predicate1","object":"Object1"},{"id":5066549580791808,"graph":"http://example.com/graph","subject":"http://example.com/Subject2","predicate":"http://schema.org/predicate2","object":"Object2"},{"id":6192449487634432,"graph":"http://example.com/graph","subject":"http://example.com/Subject3","predicate":"http://schema.org/predicate3","object":"Object3"},
]
```