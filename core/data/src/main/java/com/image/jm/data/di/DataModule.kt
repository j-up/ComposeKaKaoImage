package com.image.jm.data.di

import com.image.jm.data.repository.BookmarkRepository
import com.image.jm.data.repository.BookmarkRepositoryImpl
import com.image.jm.data.repository.KakaoRepository
import com.image.jm.data.repository.KakaoRepositoryImpl
import com.image.jm.datastore.datasource.BookmarkPreferencesDataSource
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
internal abstract class DataModule {

    @Binds
    abstract fun bindsKakaoRepository(
        repository: KakaoRepositoryImpl,
    ): KakaoRepository

    @InstallIn(SingletonComponent::class)
    @Module
    internal object ProvideModule {
        @Provides
        fun provideBookmarkRepository(
            bookmarkDataSource: BookmarkPreferencesDataSource
        ): BookmarkRepository {
            return BookmarkRepositoryImpl(bookmarkDataSource)
        }
    }

}
