import kotlinx.html.InputType
import kotlinx.html.LI
import kotlinx.html.js.onChangeFunction
import kotlinx.html.js.onClickFunction
import org.w3c.dom.HTMLInputElement
import react.*
import react.dom.*

interface TodoListProps : RProps {
    var initialItems: List<TodoModel>
}

interface TodoListState : RState {
    var items: List<TodoModel>
    var text: String
}

class TodoList(props: TodoListProps) : RComponent<TodoListProps, TodoListState>(props) {
    override fun TodoListState.init(props: TodoListProps) {
        items = props.initialItems
        text = ""
    }

    override fun RBuilder.render() {
        div {
            input(type = InputType.text, name = "itemText") {
                key = "itemText"

                attrs {
                    value = state.text
                    placeholder = "Add a to-do item"
                    onChangeFunction = {
                        val target = it.target as HTMLInputElement
                        setState {
                            text = target.value
                        }
                    }
                }
            }

            button {
                +"Add"
                attrs {
                    onClickFunction = { addTodo() }
                }
            }
            button {
                +"Get Todos From Network"
                attrs {
                    onClickFunction = { fetchTodosFromNetwork() }
                }
            }

            h3 {
                ul {
                    for (item in state.items) {
                        li {
                            +item.title
                            deleteButton(item, ::deleteTodo)
                        }
                    }
                }
            }
        }
    }

    private fun RDOMBuilder<LI>.deleteButton(item: TodoModel, deleteTodo: (TodoModel) -> Unit ) {
        button {
            +"DELETE"
            attrs {
                onClickFunction = { deleteTodo(item) }
            }
        }
    }

    private fun deleteTodo(selectedTodo: TodoModel) {
        setState {
            items = items.filter { todoModel -> todoModel != selectedTodo }
        }
    }

    private fun fetchTodosFromNetwork() {
        xhrGet("https://jsonplaceholder.typicode.com/todos") { response ->
            val todos: List<TodoModel> = JSON.parse(response)
            setState {
                items += todos
            }
        }
    }

    private fun addTodo() {
        if (state.text.isNotEmpty()) {
            setState {
                items += TodoModel(
                    userId = 1,
                    id = generateId(),
                    title = text,
                    completed = false
                )
                text = ""
            }
        }
    }

}

fun RBuilder.todoList(items: List<TodoModel> = listOf()) = child(TodoList::class) {
    attrs.initialItems = items
}