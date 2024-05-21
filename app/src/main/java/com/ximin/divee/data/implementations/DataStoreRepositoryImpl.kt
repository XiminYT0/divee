package com.ximin.divee.data.implementations

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.ximin.divee.domain.repository.DataStoreRepository
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Singleton


class DataStoreRepositoryImpl(private val context: Context): DataStoreRepository {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("preferences")


    override suspend fun setValue(key: String, value: Boolean) {
        val preferencesKey = booleanPreferencesKey("key")

        context.dataStore.edit { preferences ->
            preferences[preferencesKey] = value
        }
    }

//    suspend fun <T> setValue (key: String, value: T){
//
//    }



    override suspend fun getValue(key: String): Boolean? {
        val preferencesKey = booleanPreferencesKey("Key")
        return  context.dataStore.data.map { preferences ->
            preferences[preferencesKey] ?: false
        }.first()
    }
}