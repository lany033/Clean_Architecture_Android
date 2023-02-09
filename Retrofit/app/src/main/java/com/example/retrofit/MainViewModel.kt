package com.example.retrofit

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

// MainViewModel class will depend on the UserService class
// to get a list of users and store them in a Compose state
// that will be used in the UI.

//4. Modify the MainViewModel class to fetch users from the UserService class
//and then insert them into the UserDao class.

@HiltViewModel
class MainViewModel @Inject constructor(
    private val userService: UserService,
    private val userDao: UserDao,
    private val appDataStore: AppDataStore,
    //Inject MainTextFormatter in MainViewModel and use the formatted text as a
    //value for the UiState.count
    private val mainTextFormatter: MainTextFormatter
    ): ViewModel() {

    private val _uiStateLiveData = MutableLiveData(UiState()) //update the value
    val uiStateLiveData: LiveData<UiState> = _uiStateLiveData //be the observer

    //var resultState by mutableStateOf(UiState())
    //private set

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
                    count -> UiState(users,
                                    mainTextFormatter.getCounterText(count)) } }
                .flowOn(Dispatchers.IO)
                .collect{
                    _uiStateLiveData.value = it //update the value of liveData
                }
        }
    }
}

// MainViewModelFactory will be responsible for injecting
// the UserService class into the MainViewModel class
/*
class MainViewModelFactory: ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        MainViewModel(
            MyApplication.userService,
            MyApplication.userDao,
            MyApplication.appDataStore,
            MyApplication.mainTextFormatter) as T
}*/