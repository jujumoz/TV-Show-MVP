package sierra.com.data.mapper

import sierra.com.data.entity.RatingEntity
import sierra.com.domain.mapper.Mapper
import sierra.com.domain.model.Rating
import javax.inject.Inject

class RatingEntityMapper @Inject constructor() : Mapper<RatingEntity, Rating> {

    override fun map(value: RatingEntity): Rating {
        return with(value) {
            Rating(
                average = average
            )
        }
    }
}
