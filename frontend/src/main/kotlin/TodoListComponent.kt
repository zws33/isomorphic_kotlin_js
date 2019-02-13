import kotlinx.html.InputType
import kotlinx.html.js.onChangeFunction
import kotlinx.serialization.json.Json
import kotlinx.serialization.list
import org.w3c.dom.HTMLInputElement
import react.*
import react.dom.*

interface TodoListProps : RProps {
    var initialItems: List<TodoItem>
}

interface TodoListState : RState {
    var todos: List<TodoItem>
    var text: String
}

class TodoListComponent(props: TodoListProps) : RComponent<TodoListProps, TodoListState>(props) {
    override fun TodoListState.init(props: TodoListProps) {
        todos = props.initialItems
        text = ""
    }

    override fun RBuilder.render() {
        div("row justify-content-center") {
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

        div("row justify-content-center") {
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

    private fun addTodo() {
        if (state.text.isNotEmpty()) {
            setState {
                todos += TodoItem(
                    userId = 1,
                    id = generateId(),
                    title = text,
                    completed = false
                )
                text = ""
            }
        }
    }

    private fun deleteTodo(selectedTodo: TodoItem) {
        setState {
            todos = todos.filter { todoModel -> todoModel.id != selectedTodo.id }
        }
    }

    private fun fetchTodosFromNetwork() {
        xhrGet("https://jsonplaceholder.typicode.com/todos") { response ->
            setState {
                todos += Json.parse(TodoItem.serializer().list, response)
            }
        }
    }
}

fun RBuilder.todoListComponent(items: List<TodoItem> = listOf()) = child(TodoListComponent::class) {
    attrs.initialItems = items
}