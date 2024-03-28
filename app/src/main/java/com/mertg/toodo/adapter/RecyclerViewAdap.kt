package com.mertg.toodo.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.mertg.toodo.R
import com.mertg.toodo.databinding.RecyclerRowBinding
import com.mertg.toodo.view.MainFragment
import com.mertg.toodo.view.MainFragmentDirections
import com.mertg.toodo.view.TaskDetailFragment

class RecyclerViewAdap(val taskList : ArrayList<Tasks>) : RecyclerView.Adapter<RecyclerViewAdap.TaskHolder>() {

    inner class TaskHolder(val binding: RecyclerRowBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskHolder {
        val binding = RecyclerRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return TaskHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskHolder, position: Int) {
        val task = taskList[position]
        holder.binding.recyclerRowText.text = task.taskName

        holder.itemView.setOnClickListener {
            var clickedTaskName = taskList[position].taskName
            var clickedTaskDetail = taskList[position].taskDetail

            val action = MainFragmentDirections.actionMainFragment2ToTaskDetailFragment(clickedTaskName,clickedTaskDetail)
            Navigation.findNavController(holder.itemView).navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return taskList.size
    }
}