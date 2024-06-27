package tiagoarauo00.dev.Models

object TaskRepository {
    private val tasks = mutableListOf(
        Task("cleaning", "Clean the house", Priority.Low),
        Task("gardening", "Mow the lawn", Priority.Medium),
        Task("shopping", "Buy the groceries", Priority.High),
        Task("painting", "Paint the fence", Priority.Medium)
    )

    fun getTasks(): List<Task> = tasks
    fun taskByPriority(priority: Priority) = tasks.filter { it.priority == priority }
    fun taskByName(name: String) = tasks.find { it.name.equals(name, ignoreCase = true) }
    fun addTask(task: Task) = tasks.add(task)
    fun removeTask(name: String): Boolean {
        return tasks.removeIf { it.name == name }
    }
}