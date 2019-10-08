package sierra.com.domain.model

data class TvShow(
    val airDate: String?,
    val airStamp: String?,
    val airtime: String?,
    val id: Int,
    val image: String?,
    val name: String?,
    val number: Int?,
    val runtime: Int?,
    val season: Int?,
    val show: Show?,
    val summary: Any?,
    val url: String?
) {
    var star = false
    var favorite = false
}