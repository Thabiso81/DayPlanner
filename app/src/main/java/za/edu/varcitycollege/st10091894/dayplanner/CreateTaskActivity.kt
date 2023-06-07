package za.edu.varcitycollege.st10091894.dayplanner

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.datepicker.MaterialDatePicker
import za.edu.varcitycollege.st10091894.dayplanner.Lists.TaskList
import za.edu.varcitycollege.st10091894.dayplanner.Models.TaskModel
import java.time.LocalDate


class MainActivity2 : AppCompatActivity() {

    private lateinit var edtTaskName: EditText
    private lateinit var edtTaskDescription: EditText
    private lateinit var edtTaskCompletionDate: EditText

    private var taskCompletionDate: Long = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.create_task)

        val createTaskButton:Button = findViewById(R.id.bvCreateTask)


        createTaskButton.setOnClickListener() {
            edtTaskDescription = findViewById(R.id.edtTaskDescription)
            edtTaskName = findViewById(R.id.etvTaskName)

            if (edtTaskDescription.text.toString().isEmpty() || edtTaskName.text.toString().isEmpty()){
                Toast.makeText(applicationContext, "Enter all fields.", Toast.LENGTH_LONG).show()
            } else {
                val newTask = TaskModel(taskDescription = edtTaskDescription.text.toString(),
                    taskName = edtTaskName.text.toString())

                TaskList.taskList.add(newTask)

                val nextPage = Intent(this, MainActivity::class.java)
                startActivity(nextPage)
                finish()
            }

            edtTaskCompletionDate = findViewById(R.id.edtCompletionDate)
            edtTaskCompletionDate.setOnClickListener {
                val datePicker =
                    MaterialDatePicker.Builder.datePicker()
                        .setTitleText("Select date")
                        .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                        .build()
                datePicker.show(supportFragmentManager, "datePicker")
                datePicker.addOnPositiveButtonClickListener {
                    // Respond to positive button click.

                    //convert unix epoch value from milliseconds to days
                    taskCompletionDate = it / (24 * 60 * 60 * 1000)
                    edtTaskCompletionDate.setText(
                        "${LocalDate.ofEpochDay(taskCompletionDate).dayOfMonth} " +
                                "${LocalDate.ofEpochDay(taskCompletionDate).month} " +
                                "${LocalDate.ofEpochDay(taskCompletionDate).year}"
                    )

                }
            }
        }
    }
}