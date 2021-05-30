# Introduction


## what is all this about

One who has a project or an idea which they want to share with the world, 
they inevitably will come across the issue of deploying them.  
Because part of my day job is installing, maintaining and supporting custom
installations of Java tooling on various servers for customers, I got to know
quite a bit about managing servers and resources and stuff - because of this,
I did not want to take the step of letting some third party platform completely
manage everything for me, this would mean sacrificing a great deal of flexibility
after all.  
So the initial instinct was to rent a couple of bare metal linux servers and 
setting everything up there.  
This actually worked pretty great, it was of course a great deal more challenging
that I initially thought, but of course that's life. 


## The problem

My main concern was a different one, after exploring this strategy 
for a while, a simple truth needed to be faced::

*I am alone.*

Meaning that, for pretty much every software project I do (in my free time), 
I am the sole developer, tester and operating personell.

This is an issue, because the more I got to know about server administration
(and patching and security and maintenance), the more I realised why server 
administrators are a full-time role - it's quite a bit of work to make sure 
everything is running smoothly and because I still have my day job, _and_ I 
tend to lean more on the development side (server administration is fun, but not 
my favourite thing in the world), my conclusion was that running my own servers
was not the right thing to do. Simply because if I ever want to put a meaningful
software project out there, I can't manage it and go on with my life to do the
next thing.

But because I _have_ a background in server stuff, I did not want to spend 
unnecessary money to use some service which completely takes over deployment 
and management - I wanted to find my personal best balance between cost and benefit.

My compromise was renting a Kubernetes cluster from a cloud provider which 
patches the underlying machines and software for me, so I can just develop
software on the container level.

But of course I still need to pay for cluster resources, so how can I - with 
so little money that it wouldn't hurt my monthly fast food budget - deploy as 
many ideas as possible? How to best manage the restricted resources any
cloud provider hands to you if you give them money?

So I did a little research and stumbled across Spring Boot and Spring Cloud and 
Quarkus and Reactive and all this stuff, but what would be the most fitting 
way to develop applications for me? And should I start doing things in 'reactive'?


## Reactive programming

When checking out reactive programming - chances are the first thing you might 
encounter the [ReactiveX](http://reactivex.io/) framworks - such as 
[RxJava](https://github.com/ReactiveX/RxJava) and [RxJS](https://github.com/ReactiveX/rxjs)
as well as the [Reactive Streams Specification](https://www.reactive-streams.org/).

The idea being, that you implement everything as asynchronous streams of data, which
enables the runtime to manage execution in a non-blocking way.

An oversimplified example might be a web application which - given a request 
by a user 
- retrieves a value from our database
- modifies it (let's say it changes the case to all upper)
- presents the result to the user

```java
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

class SimpleBlockingExample {

    public static void main(String[] args) {
        Database database = new Database();

        String joke = database.getRandomJoke();
        String result = joke.toUpperCase();
        System.out.println(result);
    }

    private static class Database {

        private static final List<String> JOKES = Arrays.asList(
                "this is a joke",
                "this is another joke",
                "also joke-ful",
                "blah",
                "something"
        );

        public String getRandomJoke() {
            return JOKES.get(ThreadLocalRandom.current().nextInt(JOKES.size()));
        }
    }
}
```

So, naturally, the transformation of the data (making it all upper case) has to wait 
on the database to execute the query and provide the data in the first place.  
A normal database would introduce a small delay here and the programm has to wait (block)
until the data is available.

It's this wait time which just eats up valuable resources (CPU time and RAM for a 
Thread which does nothing) that we would like to get rid of.

Let's try to mitigate this by using a reactive library such as RxJava:

```java
import io.reactivex.rxjava3.core.Flowable;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static java.lang.System.out;

public class SimpleReactiveExample {

    public static void main(String[] args) throws InterruptedException {
        Database database = new Database();

        database.getRandomJoke()
                .map(String::toUpperCase)
                .subscribe(out::println);

        Thread.sleep(100);
    }

    private static class Database {
        private static final List<String> JOKES = Arrays.asList(
                "this is a joke",
                "this is another joke",
                "also joke-ful",
                "blah",
                "something"
        );

        public Flowable<String> getRandomJoke() {
            int numOfJokes = JOKES.size();
            int randomIndex = ThreadLocalRandom.current().nextInt(numOfJokes);
            String randomJoke = JOKES.get(randomIndex);

            return Flowable.just(randomJoke);
        }
    }
}
```

Note that our "database" now returns a `Flowable` rather than a simple `String`, this
represents, that we are using a *reactive database driver* which provides the data we
ask for in a non-blocking fashion (although the example here of course is exactly as blocking 
as before).

The point here is that we could do something useful, or simply go on with our life,
after we specified the data flow rather than just calling `Thread.sleep` here. The database
could work in an asynchronous and concurrent fashion.

This would also mean, that we do not need 

The purpose of this article is, to document my findings about which stack would work best for me, 
because I figured to really come to promising conclusions, I need to try out all the stuff.

This article will be a comparison:
- which framework to use (Spring Boot vs. Quarkus vs. NextJS)
- which programming style to use (is reactive worth the fuss?)