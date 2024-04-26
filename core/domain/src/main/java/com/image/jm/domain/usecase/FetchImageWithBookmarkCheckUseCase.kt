package com.image.jm.domain.usecase

import com.image.jm.data.repository.BookmarkRepository
import com.image.jm.data.repository.KakaoRepository
import com.image.jm.model.KakaoImage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import java.lang.Exception
import javax.inject.Inject

class FetchImageWithBookmarkCheckUseCase @Inject constructor(
    private val kakaoRepository: KakaoRepository,
    private val bookmarkRepository: BookmarkRepository
) {

    suspend operator fun invoke(query: String): Flow<Result<List<KakaoImage>>> {
        return try {
            val images = kakaoRepository.getSearchImage(query)
            val bookmarks = bookmarkRepository.getBookmarkFlow().first()

            val mappedImages = images.map { image ->
                val isBookmarked = image.thumbnail in bookmarks
                image.copy(isBookmark = isBookmarked)
            }

            flowOf(Result.success(mappedImages))
        } catch (e: Exception) {
            flowOf(Result.failure(e))
        }
    }
}
