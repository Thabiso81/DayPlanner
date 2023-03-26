package za.edu.varcitycollege.st10091894.dayplanner

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast


class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.create_task)

        val createTaskButton:Button = findViewById(R.id.bvCreateTask)


        createTaskButton.setOnClickListener() {
            val taskDescription: TextView = findViewById(R.id.etvTaskDescription)
            val taskName: TextView = findViewById(R.id.etvTaskName)

            if (taskDescription.getText().toString().isEmpty() || taskName.getText().toString().isEmpty()){
                Toast.makeText(applicationContext, "Enter all fields.", Toast.LENGTH_LONG).show()
            } else {
                val newTask = TaskModel(taskDescription = taskDescription.text.toString(),
                    taskName = taskName.text.toString())

                TaskList.taskList.add(newTask)

                val nextPage = Intent(this, MainActivity::class.java)
                startActivity(nextPage)
                finish()
            }
        }
    }
}