package sierra.com.data.mapper

import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import sierra.com.data.entity.ExternalsEntity
import sierra.com.domain.model.Externals

private const val IMDB = "imdb"
private const val THETVDB = 15
private const val TVRAGE = "tvrage"

@RunWith(MockitoJUnitRunner::class)
class ExternalsEntityMapperTest {

    private val externalsEntityMapper by lazy {
        ExternalsEntityMapper()
    }

    @Test
    fun `map ExternalsEntity (data) to Externals (domain)`() {
        val input = getInput()
        val expected = getExpected()

        val output = externalsEntityMapper.map(input)

        Assert.assertEquals(expected, output)
    }

    private fun getInput(): ExternalsEntity {
        return ExternalsEntity(
            imdb = IMDB,
            thetvdb = THETVDB,
            tvrage = TVRAGE
        )
    }

    private fun getExpected(): Externals {
        return Externals(
            imdb = IMDB,
            thetvdb = THETVDB,
            tvrage = TVRAGE
        )
    }
}