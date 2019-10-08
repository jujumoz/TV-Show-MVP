package sierra.com.data.mapper

import sierra.com.data.entity.ScheduleEntity
import sierra.com.domain.mapper.Mapper
import sierra.com.domain.model.Schedule
import javax.inject.Inject

class ScheduleEntityMapper @Inject constructor() : Mapper<ScheduleEntity, Schedule> {

    override fun map(value: ScheduleEntity): Schedule {
        return with(value) {
            Schedule(
                days = days,
                time = time
            )
        }
    }
}
