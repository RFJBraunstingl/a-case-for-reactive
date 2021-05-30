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
