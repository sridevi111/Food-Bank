package com.foodbank.app.Receiver.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.foodbank.app.utils.PreferenceManager

class ProfileViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is proflie Fragment"
    }



    val text: LiveData<String> = _text
}