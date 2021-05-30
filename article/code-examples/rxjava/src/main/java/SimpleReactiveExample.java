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

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return Flowable.just(randomJoke);
        }
    }
}
