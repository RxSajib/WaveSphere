package com.zenbyte.studio.data.local.repoimpl

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.zenbyte.studio.domain.repository.local.DataStoreRepo
import dagger.hilt.android.qualifiers.ApplicationContext
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataStoreRepoImpl @Inject constructor(
    @ApplicationContext val context: Context
) : DataStoreRepo {

    val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
        name = "MyDataStore"
    )

    override suspend fun saveStringData(key: String, value: String) {
        context.dataStore.edit { preferences ->
            preferences[stringPreferencesKey(key)] = value
        }
    }

    override fun getStringData(key: String): Flow<String?> {
        return context.dataStore.data.map {preferences ->
            preferences[stringPreferencesKey(key)]
        }
    }

    override suspend fun saveBooleanData(key: String, value: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[booleanPreferencesKey(key)] = value
        }
    }

    override fun getBooleanData(key: String): Flow<Boolean> {
        return context.dataStore.data.map { preferences ->
            preferences[booleanPreferencesKey(key)]?: false
        }
    }

    override suspend fun saveIntData(key: String, value: Int) {
        context.dataStore.edit {preferences ->
            preferences[intPreferencesKey(key)]
        }
    }

    override fun getIntData(key: String): Flow<Int?> {
        return context.dataStore.data.map { preferences ->
            preferences[intPreferencesKey(key)]
        }
    }
}