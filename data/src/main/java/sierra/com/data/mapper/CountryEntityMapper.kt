package sierra.com.data.mapper

import sierra.com.data.entity.CountryEntity
import sierra.com.domain.mapper.Mapper
import sierra.com.domain.model.Country
import javax.inject.Inject

class CountryEntityMapper @Inject constructor() : Mapper<CountryEntity, Country> {

    override fun map(value: CountryEntity): Country {
        return with(value) {
            Country(
                code = code,
                name = name,
                timezone = timezone
            )
        }
    }
}
