package com.image.jm.data.api.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class KakaoResponse(val meta: Meta = Meta(), val documents: List<ImageDocument> = listOf()) {
    @Serializable
    data class Meta(
        @SerialName("total_count") val totalCount: Int = 0,
        @SerialName("pageable_count") val pageableCount: Int = 0,
        @SerialName("is_end") val isEnd: Boolean = true
    )

    @Serializable
    data class ImageDocument(
        val collection: String = "",
        @SerialName("thumbnail_url") val thumbnailUrl: String = "",
        @SerialName("image_url") val imageUrl: String = "",
        val width: Int = 0,
        val height: Int = 0,
        @SerialName("display_sitename") val displaySiteName: String = "",
        @SerialName("doc_url") val docUrl: String = "",
        val datetime: String = ""
    )
}