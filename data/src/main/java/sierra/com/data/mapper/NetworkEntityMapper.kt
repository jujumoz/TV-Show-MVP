package sierra.com.data.mapper

import sierra.com.data.entity.CountryEntity
import sierra.com.data.entity.NetworkEntity
import sierra.com.domain.mapper.Mapper
import sierra.com.domain.model.Country
import sierra.com.domain.model.Network
import javax.inject.Inject

class NetworkEntityMapper @Inject constructor(
    private val countryMapper: Mapper<CountryEntity, Country>
) : Mapper<NetworkEntity, Network> {

    override fun map(value: NetworkEntity): Network {
        return with(value) {
            Network(
                country = country?.let { countryMapper.map(it) },
                id = id,
                name = name
            )
        }
    }
}
