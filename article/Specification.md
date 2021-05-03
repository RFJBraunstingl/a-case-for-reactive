# Specification

## the example application
The first step of comparing stuff, is doing stuff. Which means we need something
to do.  
The simplest application which involves the whole stack is an application which
stores texts in some kind of persistent data store and makes those accessible via
some API.  
In order not to just send random strings around, let's make this a little more 
fun and assume the text snippets are jokes.  
We want to allow all the people who wish to do so, to send us their best jokes.  
Then we'll let all people be part of the joy by presenting them random jokes 
submitted by our users.

## the specification
We will create a simple spec to describe the REST based API we want to deliver.  
Firstly, we will need an endpoint to retrieve a random joke:
```
openapi: 3.0.3
info:
  title: Jokr API
  description: a simple REST API for jokes
  version: 1.0.0
servers:
  - url: 'http://localhost:8080'
paths:
  /:
    get: 
      summary: display a random joke to the user
      security: [] # none means we'll allow anonymous access to this resource
      responses: 
        200:
          description: display a random joke in plain text
          content: 
            text/plain:
              schema: 
                type: string
```

Of course, we also need to accept jokes from our users, hence we'll add a POST method:
```
openapi: 3.0.3
info:
  title: Jokr API
  description: a simple REST API for jokes
  version: 1.0.0
servers:
  - url: 'http://localhost:8080'
paths:
  /:
    get: 
      summary: display a random joke to the user
      security: [] # none means we'll allow anonymous access to this resource
      responses: 
        200:
          description: display a random joke in plain text
          content: 
            text/plain:
              schema: 
                type: string
    post:
      summary: create and save a new joke
      security: []
      requestBody: 
        content:
          text/plain:
            schema:
              type: string
      responses: 
        201:
          description: the new joke submission was saved
          content: 
            text/plain:
              schema: 
                type: string
```

## the implementation (Spring Boot)
With that out of the way, let's implement this simple web app using spring boot web.
