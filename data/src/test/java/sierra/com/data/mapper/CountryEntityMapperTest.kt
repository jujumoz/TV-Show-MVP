package sierra.com.data.mapper

import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import sierra.com.data.entity.CountryEntity
import sierra.com.domain.model.Country

private const val CODE = "code"
private const val NAME = "name"
private const val TIMEZONE = "timezone"

@RunWith(MockitoJUnitRunner::class)
class CountryEntityMapperTest {

    private val countryEntityMapper by lazy {
        CountryEntityMapper()
    }

    @Test
    fun `map CountryEntity (data) to Country (domain)`() {
        val input = getInput()
        val expected = getExpected()

        val output = countryEntityMapper.map(input)

        Assert.assertEquals(expected, output)
    }

    private fun getInput(): CountryEntity {
        return CountryEntity(
            code = CODE,
            name = NAME,
            timezone = TIMEZONE
        )
    }

    private fun getExpected(): Country {
        return Country(
            code = CODE,
            name = NAME,
            timezone = TIMEZONE
        )
    }
}