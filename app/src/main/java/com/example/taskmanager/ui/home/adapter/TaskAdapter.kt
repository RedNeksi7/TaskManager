package com.example.taskmanager.ui.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import androidx.recyclerview.widget.RecyclerView
import com.example.taskmanager.databinding.ItemTaskBinding
import com.example.taskmanager.model.Task

class TaskAdapter(val onLongClick: (Task) -> Unit, val onClick: (Task) -> Unit) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {
    private val taskList= arrayListOf<Task>()

    fun removeTask(task: Task) {
        val position = taskList.indexOf(task)
        if (position != -1) {
            taskList.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    fun setTasks(tasks: List<Task>) {
        taskList.clear()
        taskList.addAll(tasks)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder(
            ItemTaskBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = taskList.size

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(taskList[position])
    }

    inner class TaskViewHolder(private val binding: ItemTaskBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(task: Task)= with(binding) {
            binding.tvTitle.text = task.title
            binding.tvDesk.text = task.desc
            itemView.setOnLongClickListener{
                onLongClick(task)
                false
            }
            itemView.setOnClickListener{
                onClick(task)
            }
        }
    }
}
