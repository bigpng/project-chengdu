package template.data

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.encodedPath
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

class NetApi(private val client: HttpClient) {
    suspend fun getCollection(): List<Media> {
        val response: MediaResponse = client.get {
            url {
                encodedPath = "/v1/collections/yejd2gf"
            }
        }.body()
        return response.media
    }
}

@Serializable
sealed class Media {
    abstract val id: Long
    abstract val url: String

    // Photo
    @Serializable
    @SerialName("Photo")
    data class Photo(
        override val id: Long,
        override val url: String,
        val photographer: String,
        val src: Src,
        val alt: String
    ) : Media() {
        @Serializable
        data class Src(
            val small: String
        )
    }

    // Video
    @Serializable
    @SerialName("Video")
    data class Video(
        override val id: Long,
        override val url: String,
        val user: User,
        @SerialName("video_files")
        val videoFiles: List<VideoFile>
    ) : Media() {
        @Serializable
        data class User(
            val name: String
        )

        @Serializable
        data class VideoFile(
            val link: String
        )

        val firstVideoLink: String?
            get() = videoFiles.firstOrNull()?.link
    }
}

@Serializable
data class MediaResponse(
    val media: List<Media>
)

