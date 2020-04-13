package com.oshamahue.login.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.oshamahue.repository.LoginRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginViewModel(private val repository: LoginRepository) : ViewModel() {
    private val _loginLiveData = MutableLiveData<Boolean>()

    val loginLiveData: LiveData<Boolean> = _loginLiveData

    fun login(username: String, password: String) {
        viewModelScope.launch {
            try {
                repository.login(username, password)
                _loginLiveData.postValue(true)
            } catch (e: Exception) {
                Log.e("LoginViewModel", "request error", e)
                _loginLiveData.postValue(false)
            }
        }
    }
}

class LoginViewModelFactory @Inject constructor(private val repository: LoginRepository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            LoginViewModel(this.repository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }

}