package com.image.jm.data.repository

import com.image.jm.data.api.KakaoService
import com.image.jm.data.mapper.toData
import com.image.jm.model.KakaoImage
import javax.inject.Inject

internal class KakaoRepositoryImpl @Inject constructor(
    private val kakaoApi: KakaoService
) : KakaoRepository {

    override suspend fun getSearchImage(query: String): List<KakaoImage> {
        return kakaoApi.getSearchImage(query = query).toData()
    }
}
