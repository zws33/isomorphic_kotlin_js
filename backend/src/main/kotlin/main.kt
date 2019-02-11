external fun require(module: String): dynamic

fun main() {
    println("Hello JavaScript!")

    val express = require("express")
    val app = express()

    app.get("/") { req, res ->
        res.type("text/plain")
        res.send("i am a beautiful butterfly")
    }

    app.use(express.static("public"))

    app.listen(3000) {
        println("Listening on port 3000")
    }
}