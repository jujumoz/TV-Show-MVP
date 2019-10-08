package sierra.com.data.mapper

import com.nhaarman.mockitokotlin2.eq
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import sierra.com.data.entity.ImageEntity
import sierra.com.data.entity.ShowEntity
import sierra.com.data.entity.TvShowEntity
import sierra.com.domain.mapper.Mapper
import sierra.com.domain.model.Show
import sierra.com.domain.model.TvShow

private const val AIR_DATE = "AIR_DATE"
private const val AIR_STAMP = "AIR_STAMP"
private const val AIRTIME = "AIRTIME"
private const val ID = 123
private const val MEDIUM = "MEDIUM"
private const val ORIGINAL = "ORIGINAL"
private const val NAME = "NAME"
private const val NUMBER = 6
private const val RUNTIME = 11
private const val SEASON = 5
private const val SUMMARY = "SUMMARY"
private const val URL = "URL"


@RunWith(MockitoJUnitRunner::class)
class TvShowEntityMapperTest {

    @Mock
    private lateinit var showMapper: Mapper<ShowEntity, Show>
    @Mock
    private lateinit var showEntity: ShowEntity
    @Mock
    private lateinit var show: Show

    private val tvShowResultEntityMapper by lazy {
        TvShowEntityMapper(showMapper)
    }

    @Before
    fun setUp() {
        Mockito.`when`(showMapper.map(eq(showEntity))).thenReturn(show)
    }

    @Test
    fun `map TvShowEntity (data) to TvShow (domain)`() {
        givenImageIsNotNull()
        val input = getInput(show = showEntity)
        val expected = getExpected(show = show, image = MEDIUM)

        val output = tvShowResultEntityMapper.map(input)

        Assert.assertEquals(expected, output)
    }

    @Test
    fun `map TvShowEntity (data) to TvShow (domain) when show is null`() {
        val input = getInput(show = null)
        val expected = getExpected(show = null, image = null)

        val output = tvShowResultEntityMapper.map(input)

        Assert.assertEquals(expected, output)
    }

    @Test
    fun `map TvShowEntity (data) to TvShow (domain) when image is null`() {
        val input = getInput(show = showEntity)
        val expected = getExpected(show = show, image = null)

        val output = tvShowResultEntityMapper.map(input)

        Assert.assertEquals(expected, output)
    }

    private fun getInput(show: ShowEntity?): TvShowEntity {
        return TvShowEntity(
            _links = null,
            airdate = AIR_DATE,
            airstamp = AIR_STAMP,
            airtime = AIRTIME,
            id = ID,
            image = null,
            name = NAME,
            number = NUMBER,
            runtime = RUNTIME,
            season = SEASON,
            show = show,
            summary = SUMMARY,
            url = URL
        )
    }

    private fun getExpected(show: Show?, image: String?): TvShow {
        return TvShow(
            airDate = AIR_DATE,
            airStamp = AIR_STAMP,
            airtime = AIRTIME,
            id = ID,
            image = image,
            name = NAME,
            number = NUMBER,
            runtime = RUNTIME,
            season = SEASON,
            show = show,
            summary = SUMMARY,
            url = URL

        )
    }

    private fun getImageEntity(): ImageEntity {
        return ImageEntity(
            medium = MEDIUM,
            original = ORIGINAL
        )
    }

    private fun givenImageIsNotNull() {
        Mockito.`when`(showEntity.image).thenReturn(getImageEntity())
    }
}