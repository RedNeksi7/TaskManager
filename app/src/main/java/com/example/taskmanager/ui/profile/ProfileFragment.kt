package com.example.taskmanager.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.taskmanager.databinding.FragmentProfileBinding
import com.example.taskmanager.data.local.Pref

class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    private val pref: Pref by lazy {
        Pref(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        saveName()
        saveImage()
    }

    private val fileChooserContract =
        registerForActivityResult(ActivityResultContracts.GetContent()) { image ->
            if (image != null) {
                pref.saveImage(image.toString())
                Glide.with(requireContext()).load(image).apply(RequestOptions.circleCropTransform())
                    .into(binding.logo)
            }
        }

    private fun saveImage() {
        Glide.with(binding.logo).load(pref.getImage()).apply(RequestOptions.circleCropTransform())
            .into(binding.logo)
        binding.logo.setOnClickListener {
            fileChooserContract.launch("image/*")
        }
    }

    private fun saveName() {
        binding.name.setText(pref.getName())
        binding.saveBtn.setOnClickListener {
            pref.saveName(binding.name.text.toString())
        }
    }
}