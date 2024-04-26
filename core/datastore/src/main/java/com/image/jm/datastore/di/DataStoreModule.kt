package com.image.jm.datastore.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

    private const val BOOKMARK_DATASTORE_NAME = "BOOKMARK_PREFERENCES"

    private val Context.bookmarkDataStore by preferencesDataStore(BOOKMARK_DATASTORE_NAME)

    @Provides
    @Singleton
    fun provideBookmarkDataStore(
        @ApplicationContext context: Context,
    ): DataStore<Preferences> =
        context.bookmarkDataStore
}
