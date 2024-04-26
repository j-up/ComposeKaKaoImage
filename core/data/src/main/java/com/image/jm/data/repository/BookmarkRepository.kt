package com.image.jm.data.repository

import kotlinx.coroutines.flow.Flow

interface BookmarkRepository {

    fun getBookmarkFlow(): Flow<Set<String>>

    suspend fun updateBookmark(thumbnail: String, bookmark: Boolean)
}