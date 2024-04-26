package com.image.jm.data.mapper

import com.image.jm.data.api.model.KakaoResponse
import com.image.jm.model.KakaoImage

internal fun KakaoResponse.toData(): List<KakaoImage> = documents.map {
    KakaoImage(thumbnail = it.thumbnailUrl, dateTime = it.datetime)
}
    
