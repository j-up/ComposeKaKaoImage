package com.image.jm.data.repository

import com.image.jm.model.KakaoImage

interface KakaoRepository {
    suspend fun getSearchImage(query: String): List<KakaoImage>
}
