package app

import kotlinx.coroutines.await
import org.w3c.fetch.RequestInit
import org.w3c.fetch.Response
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.dom.code
import react.dom.div
import react.dom.h2
import react.dom.p
import ticker
import kotlin.browser.window

class App : RComponent<RProps, RState>() {
    override fun RBuilder.render() {
        div {
            h2 {
                +"Welcome to React with Kotlin"
            }
        }
        p {
            +"To get started, edit "
            code { +"app/App.kt" }
            +" and save to reload."
        }
        p {
            ticker()
        }
    }
}

fun RBuilder.app() = child(App::class) {}

