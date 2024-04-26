package com.image.jm.data.api

import com.image.jm.data.BuildConfig
import com.image.jm.data.api.model.KakaoResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

internal interface KakaoService {
    @GET("v2/search/image")
    suspend fun getSearchImage(
        @Header("Authorization") authorization: String = BuildConfig.ACCESS_KEY,
        @Query("query") query: String,
        @Query("sort") sort: String = SORT_KEY_RECENCY,
        @Query("size") size: Int = DEFAULT_SIZE,
    ): KakaoResponse
}

private const val SORT_KEY_RECENCY = "recency"
private const val DEFAULT_SIZE = 80