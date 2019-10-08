package sierra.com.data.mapper

import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import sierra.com.data.entity.ImageEntity
import sierra.com.domain.model.Image

private const val MEDIUM = "medium"
private const val ORIGINAL = "original"

@RunWith(MockitoJUnitRunner::class)
class ImageEntityMapperTest {

    private val imageEntityMapper by lazy {
        ImageEntityMapper()
    }

    @Test
    fun `map ImageEntity (data) to Image (domain)`() {
        val input = getInput()
        val expected = getExpected()

        val output = imageEntityMapper.map(input)

        Assert.assertEquals(expected, output)
    }

    private fun getInput(): ImageEntity {
        return ImageEntity(
            medium = MEDIUM,
            original = ORIGINAL
        )
    }

    private fun getExpected(): Image {
        return Image(
            medium = MEDIUM,
            original = ORIGINAL
        )
    }
}