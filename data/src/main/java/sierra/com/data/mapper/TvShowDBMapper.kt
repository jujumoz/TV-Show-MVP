package sierra.com.data.mapper

import sierra.com.data.db.entity.TvShowDB
import sierra.com.domain.mapper.Mapper
import sierra.com.domain.model.TvShow
import javax.inject.Inject

class TvShowDBMapper @Inject constructor() : Mapper<TvShow, TvShowDB> {

    override fun map(value: TvShow): TvShowDB {
        return with(value) {
            TvShowDB(
                airStamp = airStamp,
                airDate = airDate,
                airtime = airtime,
                id = id,
                name = name,
                number = number,
                runtime = runtime,
                season = season,
                url = url,
                image = image
            )
        }
    }

    override fun reverse(value: TvShowDB): TvShow {
        return with(value) {
            TvShow(
                airStamp = airStamp,
                airDate = airDate,
                airtime = airtime,
                id = id,
                name = name,
                number = number,
                runtime = runtime,
                season = season,
                url = url,
                image = image,
                show = null,
                summary = null
            )
        }
    }
}
