package com.example.retrofit

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val KEY_COUNT = intPreferencesKey("key_count") //represents the key used by the DataStore to store the number of requests

class AppDataStore(private val dataStore: DataStore<Preferences>) {
    val savedCount: Flow<Int> =dataStore.data // emit a new count value every time it. changes
        .map { preferences -> preferences[KEY_COUNT] ?: 0
    }

    suspend fun incrementCount() { //will be increment the current saved number by 1
        dataStore.edit { preferences ->
            val currentValue = preferences[KEY_COUNT] ?: 0
            preferences[KEY_COUNT] = currentValue.inc()
        }
    }
}