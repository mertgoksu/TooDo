package com.mertg.toodo.view

import android.content.Context.MODE_PRIVATE
import android.database.sqlite.SQLiteDatabase
import com.mertg.toodo.database.DatabaseHelper
import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import com.mertg.toodo.databinding.FragmentAddTaskBinding

class AddTaskFragment : Fragment() {
    private lateinit var binding : FragmentAddTaskBinding

    private lateinit var databaseHelper: DatabaseHelper

    private lateinit var taskNameForUpdate : String

    var taskName = ""
    var taskDetail = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        databaseHelper = DatabaseHelper(requireContext())

        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAddTaskBinding.inflate(inflater,container,false)

        setHasOptionsMenu(false)

        binding.saveButton.setOnClickListener{
            buttonSaving()
        }

        val taskDetailFragment = TaskDetailFragment()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        taskNameForUpdate = binding.enterTask.text.toString()

        val receivedArgs = arguments

        taskName = receivedArgs?.getString("tasknamefromdetail").toString()
        val taskDetail = receivedArgs?.getString("taskdetailfromdetail").toString()



        if (receivedArgs != null) {
            if(taskName == "main" && taskDetail == "main"){
                //If args came from main
                binding.enterTask.text = Editable.Factory.getInstance().newEditable("")
                binding.enterDetail.text = Editable.Factory.getInstance().newEditable("")
            }
            else if (!receivedArgs.isEmpty){
                //If args came from TaskDetailFragment - edit button
                binding.enterTask.text = Editable.Factory.getInstance().newEditable(taskName)
                binding.enterDetail.text = Editable.Factory.getInstance().newEditable(taskDetail)
            }

            /*if (receivedArgs.isEmpty) {
                // Eğer argümanlar varsa, istediğiniz verilere erişebilirsiniz.
                var taskName = receivedArgs.getString("tasknamefromdetail").toString()
                var taskDetail = receivedArgs.getString("taskdetailfromdetail").toString()

                // Verileri kullanabilirsiniz.
                binding.enterTask.text = Editable.Factory.getInstance().newEditable(taskName)
                binding.enterDetail.text = Editable.Factory.getInstance().newEditable(taskDetail)
            }
            else if (!receivedArgs.isEmpty){
                if(receivedArgs.getString("tasknamefromdetail").toString() == "main" && receivedArgs.getString("taskdetailfromdetail").toString() == "main"){
                    binding.enterTask.text = Editable.Factory.getInstance().newEditable("")
                    binding.enterDetail.text = Editable.Factory.getInstance().newEditable("")
                }
                else
                    binding.enterTask.text = Editable.Factory.getInstance().newEditable(taskName)
                    binding.enterDetail.text = Editable.Factory.getInstance().newEditable(taskDetail)
            }*/

        }
    }


    private fun buttonSaving() {
        val receivedArgs = arguments
        if (receivedArgs != null) {
            val taskNameFromDetail = receivedArgs.getString("tasknamefromdetail")
            val taskDetailFromDetail = receivedArgs.getString("taskdetailfromdetail")

            val taskNameForUpdate = binding.enterTask.text.toString()
            val taskDetailForUpdate = binding.enterDetail.text.toString()

            if (taskNameFromDetail == "main" && taskDetailFromDetail == "main") {
                databaseHelper.insertTask(taskNameForUpdate, taskDetailForUpdate)
            } else {
                // Eski taskName'i kullanmalısınız
                databaseHelper.updateTask(taskNameFromDetail.toString(), taskNameForUpdate, taskDetailForUpdate)
            }

            val action = AddTaskFragmentDirections.actionAddTaskFragment2ToMainFragment2()
            Navigation.findNavController(requireView()).navigate(action)
        }
    }




}
