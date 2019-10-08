package sierra.com.data.mapper

import sierra.com.data.entity.ImageEntity
import sierra.com.domain.mapper.Mapper
import sierra.com.domain.model.Image
import javax.inject.Inject

class ImageEntityMapper @Inject constructor() : Mapper<ImageEntity, Image> {

    override fun map(value: ImageEntity): Image {
        return with(value) {
            Image(
                medium = medium,
                original = original
            )
        }
    }
}
