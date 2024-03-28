package com.mertg.toodo.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.mertg.toodo.R
import com.mertg.toodo.database.DatabaseHelper
import com.mertg.toodo.databinding.FragmentTaskDetailBinding


class TaskDetailFragment : Fragment() {
    private lateinit var binding : FragmentTaskDetailBinding

    private lateinit var databaseHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        databaseHelper = DatabaseHelper(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTaskDetailBinding.inflate(inflater,container,false)

        binding.deleteButton.setOnClickListener{
            buttonDelete()
        }

        binding.updateButton.setOnClickListener{
            buttonUpdate()
        }
        binding.updateButton.setBackgroundColor(resources.getColor(R.color.darkThemeColor))
        binding.deleteButton.setBackgroundColor(resources.getColor(R.color.darkThemeColor))

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val receivedArgs = arguments
        if (receivedArgs != null) {
            // Eğer argümanlar varsa, istediğiniz verilere erişebilirsiniz.
            val taskName = receivedArgs.getString("tasknamefrommain").toString()
            val taskDetail = receivedArgs.getString("taskdetailfrommain").toString()
            // Verileri kullanabilirsiniz.
            binding.taskNameText.text = taskName
            binding.taskDetailText.text = taskDetail
        }
    }

    private fun buttonDelete(){
        val taskNameForDelete = binding.taskNameText.text.toString()

        databaseHelper.deleteTask(taskNameForDelete)

        val action = TaskDetailFragmentDirections.actionTaskDetailFragmentToMainFragment22()
        Navigation.findNavController(requireView()).navigate(action)
    }

    private fun buttonUpdate(){
        val taskNameForUpdate = binding.taskNameText.text.toString()
        val taskDetailForUpdate = binding.taskDetailText.text.toString()

        val action = TaskDetailFragmentDirections.actionTaskDetailFragmentToAddTaskFragment2(taskNameForUpdate,taskDetailForUpdate)
        Navigation.findNavController(requireView()).navigate(action)
    }


}