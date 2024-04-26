package com.image.jm.domain.usecase

import com.image.jm.data.repository.BookmarkRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetBookmarkUseCase @Inject constructor(
    private val bookmarkRepository: BookmarkRepository
) {
    operator fun invoke(): Flow<Set<String>> {
        return bookmarkRepository.getBookmarkFlow()
    }
}