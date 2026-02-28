package template.data

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import kotlinx.serialization.Serializable

class NetworkApi(private val client: HttpClient) {
    suspend fun getCharacters(name:String? = null): List<RickMortyCharacters> {
        return try {
            val response: CharacterResponse = client.get("https://rickandmortyapi.com/api/character/") {
                name?.let { parameter("name", it) }
            }.body()
            response.results
        } catch (e: Exception) {
            emptyList()
        }
    }
}

@Serializable
data class CharacterResponse(
    val results: List<RickMortyCharacters>
)

@Serializable
data class RickMortyCharacters(
    val id: Int,
    val name: String,
    val species: String,
    val image: String
)


