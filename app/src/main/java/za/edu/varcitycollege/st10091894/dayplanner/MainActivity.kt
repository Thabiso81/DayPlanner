package za.edu.varcitycollege.st10091894.dayplanner

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import za.edu.varcitycollege.st10091894.dayplanner.Adapters.RecysclerViewAdapter
import za.edu.varcitycollege.st10091894.dayplanner.Lists.TaskList
import za.edu.varcitycollege.st10091894.dayplanner.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    var binding: ActivityMainBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //bind recyclerView
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        val adapter = RecysclerViewAdapter(TaskList.taskList)
        binding?.rvTasks?.adapter = adapter


        //explicit intent that takes the user to a layout that lets them add a task
        val addTask = findViewById<Button>(R.id.bvAddTask)
        addTask.setOnClickListener() {
            val nextPage = Intent(this, MainActivity2::class.java)
            startActivity(nextPage)
            finish()
        }



        //implicit intent that lets the user share their tasks (collected from the recyclerView)
 /**       val shareTasks = findViewById<Button>(R.id.bvShareTasks)
        shareTasks.setOnClickListener() {

            val message: String = "These are my tasks!"
            val intent = Intent()
            intent.action = Intent.ACTION_SEND
            intent.putExtra(Intent.EXTRA_TEXT, message)
            intent.type = "text/plain"

            startActivity(Intent.createChooser(intent,"Share to:"))
        } **/

    }
    override fun onDestroy(){
        super.onDestroy()
        binding = null
    }

}