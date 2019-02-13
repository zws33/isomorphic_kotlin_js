import kotlinx.html.js.onClickFunction
import react.ReactElement
import react.dom.RDOMBuilder
import react.dom.button

fun RDOMBuilder<*>.primaryButton(btnText: String, onClick: () -> Unit): ReactElement {
    return button(classes = "btn btn-outline-secondary") {
        +btnText
        attrs {
            onClickFunction = { onClick() }
        }
    }
}

fun RDOMBuilder<*>.deleteButton(btnText: String, onClick: () -> Unit): ReactElement {
    return button(classes = "badge badge-danger badge-pill") {
        +btnText
        attrs {
            onClickFunction = { onClick() }
        }
    }
}