package com.image.jm.domain.usecase

import com.image.jm.data.repository.BookmarkRepository
import javax.inject.Inject

class UpdateBookmarkUseCase @Inject constructor(
    private val bookmarkRepository: BookmarkRepository
) {
    suspend operator fun invoke(thumbnail: String, bookmark: Boolean) {
        return bookmarkRepository.updateBookmark(thumbnail, bookmark)
    }
}