// Next.js API route support: https://nextjs.org/docs/api-routes/introduction

const jokes = [];

export default (req, res) => {
    if (req.method === 'POST') {
        const jokeInput = req.body;

        if (!jokeInput) {
            res.status(400).send("error, request body was empty!")
        } else {
            jokes.push(jokeInput)
            res.status(201).send(jokeInput)
        }
    } else {
        if (jokes.length < 1) {
            res.status(404).send("no jokes saved yet!")
        }
        const randomIndex = Math.floor(Math.random() * jokes.length);
        res.status(200).send(jokes[randomIndex])
    }
}
