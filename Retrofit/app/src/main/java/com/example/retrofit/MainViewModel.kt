package com.example.retrofit

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

// MainViewModel class will depend on the UserService class
// to get a list of users and store them in a Compose state
// that will be used in the UI.
/********************************************************************************/
//4. Modify the MainViewModel class to fetch users from the UserService class
//and then insert them into the UserDao class.

class MainViewModel(
    private val userService: UserService,
    private val userDao: UserDao,
    private val appDataStore: AppDataStore
    ): ViewModel() {

    var resultState by mutableStateOf(UiState())
    private set

    init {
        viewModelScope.launch {
            flow { emit(userService.getUsers()) }
                .onEach {
                    val userEntities =
                        it.map { user -> UserEntity (user.id, user.name, user.username, user.email)}
                    userDao.insertUsers(userEntities)
                    appDataStore.incrementCount()
                }.flatMapConcat { userDao.getUsers() }
                .catch { emitAll(userDao.getUsers()) }
                .flatMapConcat { users -> appDataStore.savedCount.map {
                    count -> UiState(users, count.toString()) } }
                .flowOn(Dispatchers.IO)
                .collect{
                    resultState = it
                }
        }
    }
}

// MainViewModelFactory will be responsible for injecting
// the UserService class into the MainViewModel class
class MainViewModelFactory: ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        MainViewModel(
            MyApplication.userService,
            MyApplication.userDao,
            MyApplication.appDataStore) as T
}