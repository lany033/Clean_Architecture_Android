package com.example.retrofit

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

// MainViewModel class will depend on the UserService class
// to get a list of users and store them in a Compose state
// that will be used in the UI.
class MainViewModel(private val userService: UserService): ViewModel() {

    var resultState by mutableStateOf<List<User>>(emptyList())
    private set

    init {
        viewModelScope.launch {
            val users = userService.getUsers()
            resultState = users
        }
    }
}

// MainViewModelFactory will be responsible for injecting
// the UserService class into the MainViewModel class
class MainViewModelFactory: ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T
    = MainViewModel(MyApplication.userService) as T
}