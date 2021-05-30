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
in one form or another since the early 2000s, and with powerful corporate backing (by VMware) Spring is 
a solid choice for creating JVM based web applications.

### Quarkus
Quarkus is still a kind of 'newcomer' framework (the initial release was 2019) having a similarly strong
corporate backing, as it was developed and is maintained by RedHat.

I do not doubt Redhat a second when they say it is production ready although it's just at version
1.13.3 (march 2021), but kind of a lot of features are in experimental / beta mode 
(like the cassandra driver).

Quarkus is an implementation of the Eclipse Microprofile specification, built upon the Eclipse 
Foundation's project [vert.x](https://vertx.io/) which in itself has the goal of bringing reactive 
programming into the JVM.

Quarkus prides itself on being _the_ Java Framework developed for the 
cloud, because of it's many integrations (i.e. are JIB, Kubernetes and OpenShift extensions).

Further, they provide strong support for Oracle's new GraalVM to produce native executables from Java code.
The goal of the smallest possible memory footprint as well as the least possible RAM usage makes Java applications
developed using Quarkus even suitable to be run in Serverless environments, such as AWS Lambda functions, due to
their minimal startup time.

### NextJS
When talking about reactive programming, I felt obligated to include NodeJS in the mix
because the reactive concepts such as the event loop have been supported by Node before 
they were cool.

In particular with the whole trend going towards Serverless, NodeJS is becoming a big player for programming
the cloud (followed by python and then Java - [according to newrelic](https://newrelic.com/resources/ebooks/serverless-benchmark-report-aws-lambda-2020)).

Because I default to React when creating front end stuff, I chose a fullstack React Framework to enter
as third contestant. Meet [NextJS](https://nextjs.org/) by [Vercel](https://vercel.com/).