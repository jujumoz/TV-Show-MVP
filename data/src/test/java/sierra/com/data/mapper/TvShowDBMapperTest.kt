package sierra.com.data.mapper

import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import sierra.com.data.db.entity.TvShowDB
import sierra.com.domain.model.TvShow

private const val AIR_DATE = "AIR_DATE"
private const val AIR_STAMP = "AIR_STAMP"
private const val AIRTIME = "AIRTIME"
private const val ID = 123
private const val IMAGE = "IMAGE"
private const val NAME = "NAME"
private const val NUMBER = 6
private const val RUNTIME = 11
private const val SEASON = 5
private const val URL = "URL"

@RunWith(MockitoJUnitRunner::class)
class TvShowDBMapperTest {

    private val tvShowDBMapper by lazy {
        TvShowDBMapper()
    }

    @Test
    fun `map TvShow (domain) to TvShowDB (data)`() {
        val input = getTvShow()
        val expected = getTvShowDB()

        val output = tvShowDBMapper.map(input)

        Assert.assertEquals(expected, output)
    }

    @Test
    fun `map TvShowDB (data) to TvShow (domain)`() {
        val input = getTvShowDB()
        val expected = getTvShow()

        val output = tvShowDBMapper.reverse(input)

        Assert.assertEquals(expected, output)
    }

    private fun getTvShow(): TvShow {
        return TvShow(
            airDate = AIR_DATE,
            airStamp = AIR_STAMP,
            airtime = AIRTIME,
            id = ID,
            image = IMAGE,
            name = NAME,
            number = NUMBER,
            runtime = RUNTIME,
            season = SEASON,
            show = null,
            summary = null,
            url = URL
        )
    }

    private fun getTvShowDB(): TvShowDB {
        return TvShowDB(
            airDate = AIR_DATE,
            airStamp = AIR_STAMP,
            airtime = AIRTIME,
            id = ID,
            image = IMAGE,
            name = NAME,
            number = NUMBER,
            runtime = RUNTIME,
            season = SEASON,
            url = URL
        )
    }
}