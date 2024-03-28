package com.mertg.toodo.view

import com.mertg.toodo.database.DatabaseHelper
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.mertg.toodo.R
import com.mertg.toodo.adapter.RecyclerViewAdap
import com.mertg.toodo.adapter.Tasks
import com.mertg.toodo.databinding.FragmentMainBinding

class MainFragment : Fragment() {
    private lateinit var binding : FragmentMainBinding

    private lateinit var taskListOld : ArrayList<Tasks>

    override fun onCreate(savedInstanceState: Bundle?) {
        taskListOld = ArrayList<Tasks>()

        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMainBinding.inflate(inflater,container,false)
        //combining
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        val recyclerAdapter = RecyclerViewAdap(taskListOld)
        binding.recyclerView.adapter = recyclerAdapter

        setHasOptionsMenu(true)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val databaseHelper = DatabaseHelper(requireContext())

        // Verileri veritabanından al
        var tasksList = databaseHelper.getAllTasks()

        // RecyclerView için adapter'ı ve verileri ayarla
        val recyclerAdapter = RecyclerViewAdap(tasksList)
        binding.recyclerView.adapter = recyclerAdapter
        recyclerAdapter.notifyDataSetChanged()
        // ActionBar'ı al
        val actionBar = (activity as AppCompatActivity?)?.supportActionBar
        actionBar?.setBackgroundDrawable(ColorDrawable(ContextCompat.getColor(requireContext(), R.color.darkThemeColor)))

        // Başlık ve diğer özellikleri ayarla
        actionBar?.title = " TooDo"
        actionBar?.setDisplayHomeAsUpEnabled(false) // Geri butonunu göster
    }

    @Deprecated("Deprecated in Java")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_view, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    @Deprecated("Deprecated in Java")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.add_task -> {
                val action = MainFragmentDirections.actionMainFragment2ToAddTaskFragment2("main","main")
                Navigation.findNavController(requireView()).navigate(action)
                return true
            }
            else -> {
                return super.onOptionsItemSelected(item)
            }
        }
    }
}