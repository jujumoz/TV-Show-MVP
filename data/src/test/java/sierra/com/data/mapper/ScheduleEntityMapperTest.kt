package sierra.com.data.mapper

import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import sierra.com.data.entity.ScheduleEntity
import sierra.com.domain.model.Schedule

private const val TIME = "time"
private val DATE_LIST = listOf("date1", "date2", "date3")

@RunWith(MockitoJUnitRunner::class)
class ScheduleEntityMapperTest {

    private val scheduleEntityMapper by lazy {
        ScheduleEntityMapper()
    }

    @Test
    fun `map ScheduleEntity (data) to Schedule (domain)`() {
        val input = getInput()
        val expected = getExpected()

        val output = scheduleEntityMapper.map(input)

        Assert.assertEquals(expected, output)
    }

    private fun getInput(): ScheduleEntity {
        return ScheduleEntity(
            days = DATE_LIST,
            time = TIME
        )
    }

    private fun getExpected(): Schedule {
        return Schedule(
            days = DATE_LIST,
            time = TIME
        )
    }
}