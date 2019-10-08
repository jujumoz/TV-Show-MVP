package sierra.com.data.mapper

import sierra.com.data.entity.ShowEntity
import sierra.com.data.entity.TvShowEntity
import sierra.com.domain.mapper.Mapper
import sierra.com.domain.model.Show
import sierra.com.domain.model.TvShow
import javax.inject.Inject

class TvShowEntityMapper @Inject constructor(
    private val showMapper: Mapper<ShowEntity, Show>
) : Mapper<TvShowEntity, TvShow> {

    override fun map(value: TvShowEntity): TvShow {
        return with(value) {
            TvShow(
                airDate = airdate,
                airStamp = airstamp,
                airtime = airtime,
                id = id,
                image = show?.image?.medium,
                name = name,
                number = number,
                runtime = runtime,
                season = season,
                show = show?.let { showMapper.map(it) },
                summary = summary,
                url = url
            )
        }
    }
}