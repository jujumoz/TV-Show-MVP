package sierra.com.data.entity

data class ShowEntity(
    val _links: LinksEntity?,
    val externals: ExternalsEntity?,
    val genres: List<Any>?,
    val id: Int?,
    val image: ImageEntity?,
    val language: String?,
    val name: String?,
    val network: NetworkEntity?,
    val officialSite: String?,
    val premiered: String,
    val rating: RatingEntity?,
    val runtime: Int?,
    val schedule: ScheduleEntity?,
    val status: String?,
    val summary: String?,
    val type: String?,
    val updated: Int?,
    val url: String?,
    val webChannel: Any?,
    val weight: Int?
)