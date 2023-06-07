package za.edu.varcitycollege.st10091894.dayplanner.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import za.edu.varcitycollege.st10091894.dayplanner.Models.TaskModel
import za.edu.varcitycollege.st10091894.dayplanner.databinding.RecyclerViewRowBinding

class RecysclerViewAdapter(val taskList: List<TaskModel>):
RecyclerView.Adapter<RecysclerViewAdapter.ViewHolder>() {

    inner class ViewHolder(val itemBinding: RecyclerViewRowBinding): RecyclerView.ViewHolder(itemBinding.root){
        fun bindItem(taskModel: TaskModel){
            itemBinding.tvTaskName.text = taskModel.taskName
            itemBinding.tvTaskDescription.text = taskModel.taskDescription

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(RecyclerViewRowBinding.inflate(LayoutInflater.from(parent.context),parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val task = taskList[position]
        holder.bindItem(task)
    }

    override fun getItemCount(): Int {
        return taskList.size
    }

}