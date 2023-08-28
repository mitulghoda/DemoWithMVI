package com.appearnings.baseapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.appearnings.baseapp.Movie
import com.appearnings.baseapp.R
import com.appearnings.baseapp.databinding.FragmentDetailsBinding
import com.appearnings.baseapp.extension.setNavigationResult
import com.appearnings.baseapp.extension.setSafeOnClickListener

class DetailsFragment : Fragment() {
    private lateinit var binding: FragmentDetailsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentDetailsBinding.bind(
            inflater.inflate(
                R.layout.fragment_details, container, false
            )
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val data = Movie(
            id = 1, name = "asjkdhajksdh", imageUrl = "asdfajkdhkasj", isSelected = false
        )
        binding.btnSend.setSafeOnClickListener {
            setNavigationResult("MY_DATA", data)
        }
    }
}