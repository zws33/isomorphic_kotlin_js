import kotlinx.html.InputType
import kotlinx.html.js.onChangeFunction
import kotlinx.html.js.onClickFunction
import kotlinx.serialization.json.Json
import kotlinx.serialization.list
import org.w3c.dom.HTMLInputElement
import react.*
import react.dom.*

interface TodoListProps : RProps {
    var initialItems: List<TodoModel>
}

interface TodoListState : RState {
    var todos: List<TodoModel>
    var text: String
}

class TodoList(props: TodoListProps) : RComponent<TodoListProps, TodoListState>(props) {
    override fun TodoListState.init(props: TodoListProps) {
        todos = props.initialItems
        text = ""
    }

    override fun RBuilder.render() {
        div("row justify-content-center") {
            div("col-md-12") {
                div("input-group mb-3") {
                    input(type = InputType.text, name = "itemText", classes = "form-control") {
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

                    div("input-group-append") {
                        primaryButton("Add", ::addTodo)
                        primaryButton("Get Todos From Network", ::fetchTodosFromNetwork)
                    }
                }
            }
        }

        div("row justify-content-center") {
            div("col-md-6") {
                ul("list-group") {
                    state.todos.forEachIndexed { index, todo ->
                        li("list-group-item d-flex justify-content-between align-items-center") {
                            key = index.toString()
                            +todo.title
                            deleteButton("DELETE") { deleteTodo(todo) }
                        }
                    }
                }
            }
        }
    }

    private fun RDOMBuilder<*>.primaryButton(btnText: String, onClick: () -> Unit) {
        button(classes = "btn btn-outline-secondary") {
            +btnText
            attrs {
                onClickFunction = { onClick() }
            }
        }
    }

    private fun RDOMBuilder<*>.deleteButton(btnText: String, onClick: () -> Unit) {
        button(classes = "badge badge-danger badge-pill") {
            +btnText
            attrs {
                onClickFunction = { onClick() }
            }
        }
    }

    private fun addTodo() {
        if (state.text.isNotEmpty()) {
            setState {
                todos += TodoModel(
                    userId = 1,
                    id = generateId(),
                    title = text,
                    completed = false
                )
                text = ""
            }
        }
    }

    private fun deleteTodo(selectedTodo: TodoModel) {
        setState {
            todos = todos.filter { todoModel -> todoModel.id != selectedTodo.id }
        }
    }

    private fun fetchTodosFromNetwork() {
        xhrGet("https://jsonplaceholder.typicode.com/todos") { response ->
            setState {
                todos += Json.parse(TodoModel.serializer().list, response)
            }
        }
    }
}

fun RBuilder.todoList(items: List<TodoModel> = listOf()) = child(TodoList::class) {
    attrs.initialItems = items
}