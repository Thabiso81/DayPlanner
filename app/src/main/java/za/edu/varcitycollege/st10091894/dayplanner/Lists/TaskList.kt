package za.edu.varcitycollege.st10091894.dayplanner.Lists

import za.edu.varcitycollege.st10091894.dayplanner.Models.TaskModel
import java.time.LocalDate

object TaskList {
    val taskList = mutableListOf<TaskModel>(
        TaskModel("Walk the dog", "the dog needs a walk", LocalDate.now()),
        TaskModel("Feed the cat", "the cat will starve if you dont feed it", LocalDate.now()),
        TaskModel("Do homework", "Maths homework, pages 89-94", LocalDate.now())
    )
}