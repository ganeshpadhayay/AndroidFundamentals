package com.ganesh.androidfundamentals.samplemvvmproject.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.ganesh.androidfundamentals.samplemvvmproject.models.User
import com.ganesh.androidfundamentals.samplemvvmproject.repository.Repository

class SampleMVVMCoroutinesViewModel : ViewModel() {

    private val _userId: MutableLiveData<String> = MutableLiveData()

    val user: LiveData<User> = Transformations.switchMap(_userId) { userId ->
        Repository.getUser(userId)
    }

    fun setUserId(userId: String) {
        val update = userId
        if (_userId.value == update) {
            return
        }
        _userId.value = update
    }

    fun cancelJobs() {
        Repository.cancelJobs()
    }
}