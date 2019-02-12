
external fun require(module: String): dynamic
external val __dirname: dynamic

fun main() {
    println("Hello JavaScript!")

    val express = require("express")
    val app = express()

    val request = require("request")

    app.use(express.static("public"))

    app.get("/") { req, res ->
        res.send("public")
    }

    app.get("/todos") { req, res ->
        request.get(js("({url: 'https://jsonplaceholder.typicode.com/todos'})")) { err, response, body ->
            res.type("text/json")
            res.send(response.body)
        }
    }

    app.listen(3000) {
        println("Listening on port 3000")
    }
}