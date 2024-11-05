package com.udacity.shoestore

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.databinding.ShoeItemBinding
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.viewModel.ShoesViewModel


class ShoeListFragment : Fragment() {
    private val shoesViewModel: ShoesViewModel by activityViewModels()
    private lateinit var binding:FragmentShoeListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_shoe_list, container,  false)
        binding.buttonShoeDetail.setOnClickListener{ view: View ->
            findNavController().navigate(R.id.action_shoeListFragment_to_shoeDetailFragment)
        }
        shoesViewModel.shoesLiveData.observe(this.viewLifecycleOwner) { shoes ->
            loadShoes(shoes)
        }

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.logout_menu -> {
                logout()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun logout() {
        findNavController().navigate(R.id.action_shoeListFragment_to_loginFragment)
    }

    private fun loadShoes(shoes:List<Shoe>){

        shoes.forEach { shoe ->
            val shoeItemBinding: ShoeItemBinding = DataBindingUtil.inflate(layoutInflater, R.layout.shoe_item, null, false)

            shoeItemBinding.shoeNameItem.text = shoe.name
            shoeItemBinding.companyItem.text = shoe.company
            shoeItemBinding.shoeSizeItem.text = shoe.size.toString()
            shoeItemBinding.descriptionItem.text = shoe.description
            if (shoe.images.isNotEmpty()) {
                shoeItemBinding.shoeImageItem.setImageResource(shoe.images[0].toInt())
            }
            binding.linearLayout.addView(shoeItemBinding.root)
        }

    }
}