package appComponent

import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.dom.div
import react.dom.h2
import todoListComponent

class AppComponent : RComponent<RProps, RState>() {
    override fun RBuilder.render() {
        div("container") {
            div("row") {
                h2("display-1") {
                    +"Welcome to React with Kotlin"
                }
            }
            todoListComponent()
        }
    }
}

fun RBuilder.appComponent() = child(AppComponent::class) {}

