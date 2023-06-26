package com.example.taskmanager.ui.task

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import com.example.taskmanager.R
import com.example.taskmanager.databinding.FragmentTaskBinding
import com.example.taskmanager.ui.App
import com.example.taskmanager.data.local.Pref
import com.example.taskmanager.model.Task

class TaskFragment : Fragment() {
    private lateinit var binding: FragmentTaskBinding
    private var task: Task? = null
    private lateinit var data: Task
    private val pref: Pref by lazy {
        Pref(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btSave.setOnClickListener {
            onSave()
        }
        if (arguments !== null) {
            task = requireArguments().getSerializable("task") as Task
        }else {
            binding.btSave.text = getString(R.string.save)
        }
        fillTask()
    }

    private fun fillTask() {
        if (task != null) {
            binding.etTitle.setText(task!!.title)
            binding.etDescription.setText(task!!.desc)
            binding.btSave.text = getString(R.string.update)
        }
    }

    private fun onSave() {
        data = Task(
            title = binding.etTitle.text.toString(),
            desc = binding.etDescription.text.toString()
        )
        if (task !== null) {
            updateTask()
        }
        App.db.taskDao().insert(data)

        findNavController().navigateUp()
    }

    companion object {
        const val TASK_KEY = "task.result"
        const val TASK_REQUEST = "task.result"
    }

    private fun updateTask() {
        task!!.title = data.title
        task!!.desc = data.desc
        App.db.taskDao().update(task!!)
    }
}