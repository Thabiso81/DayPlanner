package za.edu.varcitycollege.st10091894.dayplanner.Lists

import za.edu.varcitycollege.st10091894.dayplanner.Models.TaskModel

object TaskList {
    val taskList = mutableListOf<TaskModel>(
        TaskModel("Walk the dog", "the dog needs a walk"),
        TaskModel("Feed the cat", "the cat will starve if you dont feed it"),
        TaskModel("Do homework", "Maths homework, pages 89-94")
    )
}