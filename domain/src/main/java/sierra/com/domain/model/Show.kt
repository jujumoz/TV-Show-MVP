package sierra.com.domain.model

data class Show(
    val externals: Externals?,
    val genres: List<Any>?,
    val id: Int?,
    val image: Image?,
    val language: String?,
    val name: String?,
    val network: Network?,
    val officialSite: String?,
    val premiered: String?,
    val rating: Rating?,
    val runtime: Int?,
    val schedule: Schedule?,
    val status: String?,
    val summary: String?,
    val type: String?,
    val updated: Int?,
    val url: String?,
    val webChannel: Any?,
    val weight: Int?
)