package com.image.jm.data.repository

import com.image.jm.datastore.datasource.BookmarkPreferencesDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import javax.inject.Inject

internal class BookmarkRepositoryImpl @Inject constructor(
    private val bookmarkDataSource: BookmarkPreferencesDataSource
) : BookmarkRepository {

    private val bookmarkFlow: Flow<Set<String>> = bookmarkDataSource.bookmarkFlow

    override fun getBookmarkFlow(): Flow<Set<String>> {
        return bookmarkFlow.filterNotNull()
    }

    override suspend fun updateBookmark(thumbnail: String, bookmark: Boolean) {
        val bookmarkSet = bookmarkFlow.first()
        bookmarkDataSource.updateBookmark(
            if (bookmark) {
                bookmarkSet + thumbnail
            } else {
                bookmarkSet - thumbnail
            }
        )
    }
}