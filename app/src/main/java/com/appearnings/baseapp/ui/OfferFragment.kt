package com.appearnings.baseapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.appearnings.baseapp.Movie
import com.appearnings.baseapp.R
import com.appearnings.baseapp.databinding.FragmentOfferBinding
import com.appearnings.baseapp.extension.setSafeOnClickListener

class OfferFragment : Fragment() {
    private lateinit var binding: FragmentOfferBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        binding =
            FragmentOfferBinding.bind(inflater.inflate(R.layout.fragment_offer, container, false))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.send.setSafeOnClickListener {
            val action = OfferFragmentDirections.actionOfferFragmentToDetailsFragment(
                Movie(
                    id = 1, name = "asjkdhajksdh", imageUrl = "asdfajkdhkasj", isSelected = false
                )
            )
            findNavController().navigate(action)
        }
    }
}