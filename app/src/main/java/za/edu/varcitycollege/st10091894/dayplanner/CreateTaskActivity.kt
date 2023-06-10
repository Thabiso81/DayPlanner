package za.edu.varcitycollege.st10091894.dayplanner

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.datepicker.MaterialDatePicker
import za.edu.varcitycollege.st10091894.dayplanner.Lists.TaskList
import za.edu.varcitycollege.st10091894.dayplanner.Models.TaskModel
import java.time.LocalDate


class MainActivity2 : AppCompatActivity() {

    private lateinit var edtTaskName: EditText
    private lateinit var edtTaskDescription: EditText
    private lateinit var edtTaskCompletionDate: EditText
    private lateinit var btnCreateTask: Button

    private var taskCompletionDate: Long = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.create_task)


        btnCreateTask = findViewById(R.id.bvCreateTask)
        edtTaskDescription = findViewById(R.id.edtTaskDescription)
        edtTaskName = findViewById(R.id.etvTaskName)
        edtTaskCompletionDate = findViewById(R.id.edtCompletionDate)

        btnCreateTask.setOnClickListener() {


            if (inputValid(edtTaskName)) {
                val newTask = TaskModel(taskDescription = edtTaskDescription.text.toString(),
                    taskName = edtTaskName.text.toString(), taskDueDate = LocalDate.now())

                TaskList.taskList.add(newTask)

                val nextPage = Intent(this, MainActivity::class.java)
                startActivity(nextPage)
                finish()
            }


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
    fun inputValid(taskNmae: EditText): Boolean{
        var isValid = true
        if (taskNmae.text.toString().isEmpty()){
            isValid = false
            Toast.makeText(applicationContext, "Enter a task name", Toast.LENGTH_LONG).show()
        }

        return isValid
    }

}