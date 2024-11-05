package com.udacity.shoestore

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.databinding.FragmentShoeDetailBinding
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.viewModel.ShoesViewModel

class ShoeDetailFragment : Fragment() {

    private val viewModel: ShoesViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentShoeDetailBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_shoe_detail, container,  false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.shoe = Shoe("", 0.0, "", "")
        binding.buttonCancel.setOnClickListener { view: View ->
            findNavController().navigate(R.id.action_shoeDetailFragment_to_shoeListFragment)
        }
        viewModel.addShoeLiveData.observe(viewLifecycleOwner){ isNavigate ->
            if (isNavigate) {
                findNavController().navigate(R.id.action_shoeDetailFragment_to_shoeListFragment)
            }
        }
        return binding.root
    }
}