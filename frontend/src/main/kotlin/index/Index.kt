import app.app
import react.*
import react.dom.*
import kotlin.browser.document

fun main() {
    render(document.getElementById("root")) {
        app()
    }
}