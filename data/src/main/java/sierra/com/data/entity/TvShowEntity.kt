package sierra.com.data.entity

data class TvShowEntity(
    val _links: LinksXEntity?,
    val airdate: String?,
    val airstamp: String?,
    val airtime: String?,
    val id: Int,
    val image: ImageEntity?,
    val name: String?,
    val number: Int?,
    val runtime: Int?,
    val season: Int?,
    val show: ShowEntity?,
    val summary: Any?,
    val url: String?
)