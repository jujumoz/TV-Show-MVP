package sierra.com.data.entity

data class LinksEntity(
    val nextEpisode: NextEpisodeEntity,
    val previousEpisode: PreviousEpisodeEntity,
    val self: SelfEntity
)