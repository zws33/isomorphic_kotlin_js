import kotlinx.serialization.Serializable

@Serializable
data class TodoModel(
    val userId: Int,
    val id: Int,
    val title: String,
    val completed: Boolean
)