package com.udacity.shoestore.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.R
import com.udacity.shoestore.models.Shoe

class ShoesViewModel(): ViewModel() {
    private val shoesList =  mutableListOf<Shoe>()
    private val _shoesLiveData = MutableLiveData<List<Shoe>>()
    val shoesLiveData: LiveData<List<Shoe>>
        get() = _shoesLiveData
    private val _addShoe = MutableLiveData<Boolean>()
    val addShoeLiveData: LiveData<Boolean>
        get() = _addShoe



    fun createShoesList() {
        shoesList.add(Shoe("Name1", 8.0, "Nike", "description1", listOf(R.drawable.shoe1.toString()) ))
        shoesList.add(Shoe("Name2", 9.0, "Nike", "description2", listOf(R.drawable.shoe2.toString())))
        shoesList.add(Shoe("Name3", 7.5, "Nike", "description3", listOf(R.drawable.shoe3.toString())))
        shoesList.add(Shoe("Name4", 7.0, "Nike", "description4", listOf(R.drawable.shoe4.toString())))
        shoesList.add(Shoe("Name5", 6.5, "Nike", "description5", listOf(R.drawable.shoe1.toString())))
        _shoesLiveData.value = shoesList
    }
    init {
        createShoesList()
    }
    fun addNewShoe(shoe: Shoe) {
        shoesList.add(shoe.copy(images = emptyList()))
        _addShoe.value = true
    }
}