import kotlinx.html.InputType
import kotlinx.html.LI
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
                    for (todo in state.todos) {
                        li {
                            +todo.title
                            deleteButton(todo, ::deleteTodo)
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

}

fun RBuilder.todoList(items: List<TodoModel> = listOf()) = child(TodoList::class) {
    attrs.initialItems = items
}