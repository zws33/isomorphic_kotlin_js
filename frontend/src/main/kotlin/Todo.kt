import kotlinx.html.id
import react.*
import react.dom.button
import react.dom.div

data class Todo(
    val userId: Int,
    val id: Int,
    val title: String,
    val completed: Boolean
)

interface TodosState : RState {
    var todos: MutableList<Todo>
}

class TodosComponent : RComponent<RProps, TodosState>() {

    fun onClick() {
        xhrGet("https://jsonplaceholder.typicode.com/todos/1") { response ->
            setState { }
        }

    }

    override fun RBuilder.render() {
        div {
            button {
                attrs {
                    id = "get-todos"
                }
            }
            div {
                attrs {
                    id = "todos-list"
                }
            }
        }
    }
}

fun RBuilder.todo(startFrom: Int = 0) = child(TodosComponent::class) {
}