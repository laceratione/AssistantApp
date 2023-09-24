package ru.android.assistantapp.tasks

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import ru.android.assistantapp.R
import ru.android.assistantapp.domain.Task

class TasksAdapter(
    private val taskClickListener: OnItemTaskClickListener
) : RecyclerView.Adapter<TasksAdapter.ViewHolder>() {
    private var tasks: MutableList<Task> = mutableListOf()

    interface OnItemTaskClickListener {
        fun onItemClick(task: Task, position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.rv_tasks_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = tasks.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val task = tasks[position]
        holder.name.text = task.name
        holder.btnDetails.setOnClickListener {
            taskClickListener.onItemClick(task, position)
        }
        if (task.isFinished){
            holder.llDetails.visibility = View.GONE
            holder.llWorkFinished.visibility = View.VISIBLE
        }
    }

    fun update(data: MutableList<Task>){
        tasks = data
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.nameTask)
        val btnDetails: MaterialButton = view.findViewById(R.id.btnShowDetails)
        val llDetails: LinearLayout = view.findViewById(R.id.llDetails)
        val llWorkFinished: LinearLayout = view.findViewById(R.id.llWorkFinished)
    }
}