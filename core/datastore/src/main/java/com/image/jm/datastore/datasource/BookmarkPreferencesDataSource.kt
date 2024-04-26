package com.image.jm.datastore.datasource

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringSetPreferencesKey
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import kotlinx.coroutines.flow.map

class BookmarkPreferencesDataSource @Inject constructor(
    private val dataStore: DataStore<Preferences>,
) {
    object PreferencesKey {
        val BOOKMARK = stringSetPreferencesKey("BOOKMARK")
    }

    val bookmarkFlow: Flow<Set<String>> = dataStore.data.map { preferences ->
        preferences[PreferencesKey.BOOKMARK] ?: emptySet()
    }

    suspend fun updateBookmark(bookmarkSet: Set<String>) {
        dataStore.edit { preferences ->
            preferences[PreferencesKey.BOOKMARK] = bookmarkSet
        }
    }
}
