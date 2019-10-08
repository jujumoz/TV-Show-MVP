package sierra.com.data.mapper

import sierra.com.data.entity.ExternalsEntity
import sierra.com.domain.mapper.Mapper
import sierra.com.domain.model.Externals
import javax.inject.Inject

class ExternalsEntityMapper @Inject constructor() : Mapper<ExternalsEntity, Externals> {

    override fun map(value: ExternalsEntity): Externals {
        return with(value) {
            Externals(
                imdb = imdb,
                thetvdb = thetvdb,
                tvrage = tvrage
            )
        }
    }
}
