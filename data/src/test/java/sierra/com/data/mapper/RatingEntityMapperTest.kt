package sierra.com.data.mapper

import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import sierra.com.data.entity.RatingEntity
import sierra.com.domain.model.Rating

private const val AVERAGE = "average"

@RunWith(MockitoJUnitRunner::class)
class RatingEntityMapperTest {

    private val ratingEntityMapper by lazy {
        RatingEntityMapper()
    }

    @Test
    fun `map RatingEntity (data) to Rating (domain)`() {
        val input = getInput()
        val expected = getExpected()

        val output = ratingEntityMapper.map(input)

        Assert.assertEquals(expected, output)
    }

    private fun getInput(): RatingEntity {
        return RatingEntity(
            average = AVERAGE
        )
    }

    private fun getExpected(): Rating {
        return Rating(
            average = AVERAGE
        )
    }
}