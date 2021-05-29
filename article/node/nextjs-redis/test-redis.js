const redis = require("redis")

console.log("hello world")

const client = redis.createClient()
//client.sadd("fooset", "baz")

client.srandmember("fooset", function (err, value) {
    console.log(value)
})