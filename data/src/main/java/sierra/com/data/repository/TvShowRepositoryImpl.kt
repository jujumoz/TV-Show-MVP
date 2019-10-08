package sierra.com.data.repository

import io.reactivex.Single
import sierra.com.data.entity.TvShowEntity
import sierra.com.data.networking.ShowService
import sierra.com.domain.mapper.Mapper
import sierra.com.domain.model.TvShow
import sierra.com.domain.repository.TvShowRepository
import javax.inject.Inject

private const val FRANCE_ISO = "FR"

class TvShowRepositoryImpl @Inject constructor(
    private val service: ShowService,
    private val mapper: Mapper<TvShowEntity, TvShow>
) : TvShowRepository {

    override fun getAll(date: String): Single<List<TvShow>> {
        return service.getAll(FRANCE_ISO, date).map { mapper.map(it) }
    }

    override fun search(search: String): Single<List<TvShow>> {
        return service.search(search).map { mapper.map(it) }
    }
}