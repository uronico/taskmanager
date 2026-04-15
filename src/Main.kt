import java.io.File
import java.util.Scanner

fun main() {
    val scanner = Scanner(System.`in`)
    val taskFile = File("tasks.txt") // text file to store tasks
    val tasks = mutableListOf<String>()

    // Load existing tasks from file (if any)
    if (taskFile.exists()) {
        tasks.addAll(taskFile.readLines())
    }

    while (true) {
        println("\n--- Task Manager ---")
        println("1. Add Task")
        println("2. Remove Task")
        println("3. List Tasks")
        println("4. Exit")
        print("Choose an option: ")

        when (scanner.nextLine()) {
            "1" -> {
                print("Enter task: ")
                val task = scanner.nextLine()
                tasks.add(task)
                taskFile.appendText(task + "\n") // save to file immediately
                println("Task added.")
            }
            "2" -> {
                println("Enter task number to remove:")
                val index = scanner.nextLine().toIntOrNull()
                if (index != null && index in 1..tasks.size) {
                    val removed = tasks.removeAt(index - 1)
                    taskFile.writeText(tasks.joinToString("\n")) // rewrite file
                    println("Removed: $removed")
                } else {
                    println("Invalid task number.")
                }
            }
            "3" -> {
                if (tasks.isEmpty()) {
                    println("No tasks found.")
                } else {
                    println("\nYour Tasks:")
                    tasks.forEachIndexed { i, task -> println("${i + 1}. $task") }
                }
            }
            "4" -> {
                println("All task added to the file. Thank you, Goodbye!")
                break
            }
            else -> println("Invalid option. Try again.")
        }
    }
}