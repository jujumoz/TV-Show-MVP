package sierra.com.data.mapper

import sierra.com.data.entity.*
import sierra.com.domain.mapper.Mapper
import sierra.com.domain.model.*
import javax.inject.Inject

class ShowEntityMapper @Inject constructor(
    private val externalsMapper: Mapper<ExternalsEntity, Externals>,
    private val imageMapper: Mapper<ImageEntity, Image>,
    private val networkMapper: Mapper<NetworkEntity, Network>,
    private val ratingMapper: Mapper<RatingEntity, Rating>,
    private val scheduleMapper: Mapper<ScheduleEntity, Schedule>
) : Mapper<ShowEntity, Show> {

    override fun map(value: ShowEntity): Show {
        return with(value) {
            Show(
                externals = externals?.let { externalsMapper.map(it) },
                genres = genres,
                id = id,
                image = image?.let { imageMapper.map(it) },
                language = language,
                name = name,
                network = network?.let { networkMapper.map(it) },
                officialSite = officialSite,
                premiered = premiered,
                rating = rating?.let { ratingMapper.map(it) },
                runtime = runtime,
                schedule = schedule?.let { scheduleMapper.map(it) },
                status = status,
                summary = summary,
                type = type,
                updated = updated,
                url = url,
                webChannel = webChannel,
                weight = weight
            )
        }
    }
}
