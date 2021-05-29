# Framework
Which framework to use for cloud native Java?  
While there are more great pieces of Software out there (and probably more that I can ever know of) - I will focus on 
the two particular frameworks I already got to know a bit while experimenting with Java in the Cloud:
- Spring Boot and
- Quarkus

Let's meet our contestants.

### Spring Boot
Spring out to be _the_ dependency injection framework on the JVM (in my mind at least).
It's so well known that you surely do not need me to introduce it to you.  
With Spring Boot, the Spring team went out of their way to make Spring interesting 
for quick, cloud native, agile development. Built on the battle tested Spring framework which is around
in one form or another since the early 2000s, and with powerful corporate backing

### Quarkus
Quarkus is still a kind of 'newcomer' framework (in my opinion at least).  
I do not doubt Redhat a second when they say it is production
ready although it's just at version 1.13.3 (march 2021), but kind of a lot of
features are in experimental / beta mode (like the cassandra driver).  
Quarkus is actually an implementation of the Eclipse Microprofile specification,
which is the attempt to define a subset of the JEE spec needed for cloud native 
project. Quarkus prides itself on being _the_ Java Framework developed for the 
cloud, because of it's many integrations (there are JIB and Kubernetes extensions for example)
it also partners with Oracles new GraalVM to produce native executables from Java code
which are conceived with the goal of the smallest possible memory footprint
as well as the least possible RAM usage.

## the application
Because we will keep it simple, let's define our simple application:


## links
- https://blogs.oracle.com/javamagazine/java-frameworks-for-the-cloud-establishing-the-bounds-for-rapid-startups