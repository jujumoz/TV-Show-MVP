package sierra.com.data.mapper

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import sierra.com.data.entity.*
import sierra.com.domain.mapper.Mapper
import sierra.com.domain.model.*

private const val ID = 1
private const val LANGUAGE = "LANGUAGE"
private const val NAME = "NAME"
private const val OFFICIAL_SITE = "OFFICIAL_SITE"
private const val PREMIERED = "PREMIERED"
private const val RUNTIME = 3
private const val STATUS = "STATUS"
private const val SUMMARY = "SUMMARY"
private const val TYPE = "TYPE"
private const val UPDATED = 4
private const val URL = "URL"
private const val WEB_CHANNEL = "WEB_CHANNEL"
private const val WEIGHT = 5
private val GENRES = listOf("genre1", "genre2", "genre3")

@RunWith(MockitoJUnitRunner::class)
class ShowEntityMapperTest {

    @Mock
    private lateinit var externalsMapper: Mapper<ExternalsEntity, Externals>
    @Mock
    private lateinit var imageMapper: Mapper<ImageEntity, Image>
    @Mock
    private lateinit var networkMapper: Mapper<NetworkEntity, Network>
    @Mock
    private lateinit var ratingMapper: Mapper<RatingEntity, Rating>
    @Mock
    private lateinit var scheduleMapper: Mapper<ScheduleEntity, Schedule>

    @Mock
    private lateinit var externals: Externals
    @Mock
    private lateinit var image: Image
    @Mock
    private lateinit var network: Network
    @Mock
    private lateinit var rating: Rating
    @Mock
    private lateinit var schedule: Schedule

    private val showEntityMapper by lazy {
        ShowEntityMapper(
            externalsMapper,
            imageMapper,
            networkMapper,
            ratingMapper,
            scheduleMapper
        )
    }

    @Before
    fun setUp() {
        Mockito.`when`(externalsMapper.map(any<ExternalsEntity>())).thenReturn(externals)
        Mockito.`when`(imageMapper.map(any<ImageEntity>())).thenReturn(image)
        Mockito.`when`(networkMapper.map(any<NetworkEntity>())).thenReturn(network)
        Mockito.`when`(ratingMapper.map(any<RatingEntity>())).thenReturn(rating)
        Mockito.`when`(scheduleMapper.map(any<ScheduleEntity>())).thenReturn(schedule)
    }

    @Test
    fun `map ShowEntity (data) to Show (domain)`() {
        val input = getInput()
        val expected = getExpected()

        val output = showEntityMapper.map(input)

        Assert.assertEquals(expected, output)
    }

    @Test
    fun `map ShowEntity (data) to Show (domain) when some fields are null`() {
        val input = getInputWithNulls()
        val expected = getExpectedWithNulls()

        val output = showEntityMapper.map(input)

        Assert.assertEquals(expected, output)
    }

    private fun getInput(): ShowEntity {
        return ShowEntity(
            _links = null,
            externals = mock(),
            genres = GENRES,
            id = ID,
            image = mock(),
            language = LANGUAGE,
            name = NAME,
            network = mock(),
            officialSite = OFFICIAL_SITE,
            premiered = PREMIERED,
            rating = mock(),
            runtime = RUNTIME,
            schedule = mock(),
            status = STATUS,
            summary = SUMMARY,
            type = TYPE,
            updated = UPDATED,
            url = URL,
            webChannel = WEB_CHANNEL,
            weight = WEIGHT
        )
    }

    private fun getExpected(): Show {
        return Show(
            externals = externals,
            genres = GENRES,
            id = ID,
            image = image,
            language = LANGUAGE,
            name = NAME,
            network = network,
            officialSite = OFFICIAL_SITE,
            premiered = PREMIERED,
            rating = rating,
            runtime = RUNTIME,
            schedule = schedule,
            status = STATUS,
            summary = SUMMARY,
            type = TYPE,
            updated = UPDATED,
            url = URL,
            webChannel = WEB_CHANNEL,
            weight = WEIGHT
        )
    }

    private fun getInputWithNulls(): ShowEntity {
        return ShowEntity(
            _links = null,
            externals = null,
            genres = GENRES,
            id = ID,
            image = null,
            language = LANGUAGE,
            name = NAME,
            network = null,
            officialSite = OFFICIAL_SITE,
            premiered = PREMIERED,
            rating = null,
            runtime = RUNTIME,
            schedule = null,
            status = STATUS,
            summary = SUMMARY,
            type = TYPE,
            updated = UPDATED,
            url = URL,
            webChannel = WEB_CHANNEL,
            weight = WEIGHT
        )
    }

    private fun getExpectedWithNulls(): Show {
        return Show(
            externals = null,
            genres = GENRES,
            id = ID,
            image = null,
            language = LANGUAGE,
            name = NAME,
            network = null,
            officialSite = OFFICIAL_SITE,
            premiered = PREMIERED,
            rating = null,
            runtime = RUNTIME,
            schedule = null,
            status = STATUS,
            summary = SUMMARY,
            type = TYPE,
            updated = UPDATED,
            url = URL,
            webChannel = WEB_CHANNEL,
            weight = WEIGHT
        )
    }
}