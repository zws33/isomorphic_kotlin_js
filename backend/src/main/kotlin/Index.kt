external fun require(module: String): dynamic

fun main() {
    val express = require("express")
    val request = require("request")

    val app = express()

    app.use(express.static("public"))

    app.get("/") { req, res ->
        res.send("public")
    }

    app.get("/todos") { req, res ->
        request.get("https://jsonplaceholder.typicode.com/todos") { err, response, body ->
            res.type("text/json")
            res.send(response.body)
        }
    }

    app.listen(3000) {
        println("Listening on port 3000")
    }
}