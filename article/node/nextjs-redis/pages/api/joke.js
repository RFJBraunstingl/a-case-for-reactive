import redis from "redis"

const SET_KEY_JOKES = "JOKES"

const client = redis.createClient()

const createJoke = async (joke) => {
    return new Promise((resolve, reject) => {
        client.sadd(SET_KEY_JOKES, joke, function (err, value) {
            if (err) {
                reject(err)
            } else {
                resolve(value)
            }
        })
    })
}

const randomJoke = async () => {
    return new Promise((resolve, reject) => {
        client.srandmember(SET_KEY_JOKES, function (err, value) {

            if (err) {
                reject(err)
            } else {
                resolve(value)
            }
        })
    })
}

export default async (req, res) => {
    if (req.method === 'POST') {
        const jokeInput = req.body;

        if (!jokeInput) {
            res.status(400).send("error, request body was empty!")
        } else {
            res.status(201).send(await createJoke(jokeInput))
        }
    } else {
        res.status(200).send(await randomJoke())
    }
}
