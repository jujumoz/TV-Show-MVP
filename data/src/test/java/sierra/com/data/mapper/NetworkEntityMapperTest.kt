package sierra.com.data.mapper

import com.nhaarman.mockitokotlin2.eq
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import sierra.com.data.entity.CountryEntity
import sierra.com.data.entity.NetworkEntity
import sierra.com.domain.mapper.Mapper
import sierra.com.domain.model.Country
import sierra.com.domain.model.Network

private const val ID = 123
private const val NAME = "name"

@RunWith(MockitoJUnitRunner::class)
class NetworkEntityMapperTest {

    @Mock
    private lateinit var countryMapper: Mapper<CountryEntity, Country>
    @Mock
    private lateinit var countryEntity: CountryEntity
    @Mock
    private lateinit var country: Country

    private val networkEntityMapper by lazy {
        NetworkEntityMapper(countryMapper)
    }

    @Before
    fun setUp() {
        Mockito.`when`(countryMapper.map(eq(countryEntity))).thenReturn(country)
    }

    @Test
    fun `map NetworkEntity (data) to Network (domain)`() {
        val input = getInput(country = countryEntity)
        val expected = getExpected(country = country)

        val output = networkEntityMapper.map(input)

        Assert.assertEquals(expected, output)
    }

    @Test
    fun `map NetworkEntity (data) to Network (domain) when country is null`() {
        val input = getInput(country = null)
        val expected = getExpected(country = null)

        val output = networkEntityMapper.map(input)

        Assert.assertEquals(expected, output)
    }

    private fun getInput(country: CountryEntity?): NetworkEntity {
        return NetworkEntity(
            country = country,
            id = ID,
            name = NAME
        )
    }

    private fun getExpected(country: Country?): Network {
        return Network(
            country = country,
            id = ID,
            name = NAME
        )
    }
}