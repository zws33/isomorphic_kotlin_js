package app

import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.dom.div
import react.dom.h2
import todoList

class App : RComponent<RProps, RState>() {
    override fun RBuilder.render() {
        div("container") {
            div("row") {
                h2("display-1") {
                    +"Welcome to React with Kotlin"
                }
            }
            todoList()
        }
    }
}

fun RBuilder.app() = child(App::class) {}

