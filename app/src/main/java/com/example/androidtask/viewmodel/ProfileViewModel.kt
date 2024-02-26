package com.example.androidtask.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidtask.APIService.RetrofitInstance
import com.example.androidtask.database.ProfileEntity
import com.example.androidtask.database.ProfileRepository
import com.example.androidtask.model.Profile
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ProfileViewModel(): ViewModel() {
    private val apiService = RetrofitInstance.api
    private val _creditCards = MutableLiveData<List<Profile>>()
    val creditCards: LiveData<List<Profile>> = _creditCards
    private val _data = MutableLiveData<Profile>()
    val data: LiveData<Profile> = _data

    private val _studentDetailsList = MutableStateFlow(emptyList<ProfileEntity>())
    val studentDetailsList = _studentDetailsList.asStateFlow()

    fun getPosts() {
        viewModelScope.launch {
            try {
                val response = apiService.getAllData()
                if (response.isNotEmpty()) {
                    _creditCards.value = response
                }
            } catch (e: Exception) {
                Log.e("Error",e.localizedMessage)
            }
        }
    }

    fun createProfile(data:Profile) {
        viewModelScope.launch {
            try {
                val response = apiService.postData(data)
                    _data.value = response
//                insert(ProfileEntity(id = 0, name = response.name, email = response.email, mobile = response.mobile, gender = response.gender))
                Log.e("Log",response.toString())
            } catch (e: Exception) {
                Log.e("Error",e.localizedMessage)
            }
        }
    }
    fun getAllData(repo: ProfileRepository) {
        viewModelScope.launch(IO) {
            repo.getAllStudents().collectLatest {
                _studentDetailsList.tryEmit(it)
            }
        }
    }

    fun updateName(repo: ProfileRepository, id: Int, name: String) {
        viewModelScope.launch(IO) {
            repo.updateTitle(id,name)
        }
    }

}