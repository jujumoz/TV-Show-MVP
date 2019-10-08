package sierra.com.data.db.provider

import io.reactivex.Completable
import io.reactivex.Single
import sierra.com.data.db.dao.TvShowDao
import sierra.com.data.db.entity.TvShowDB
import sierra.com.domain.mapper.Mapper
import sierra.com.domain.model.TvShow
import javax.inject.Inject

class TvShowDataProvider @Inject constructor(
    private val favoriteDao: TvShowDao,
    private val mapper: Mapper<TvShow, TvShowDB>
) : LocalDataProvider<TvShow> {

    override fun store(element: TvShow): Completable {
        return Completable.create {
            favoriteDao.insertAll(mapper.map(element))
            it.onComplete()
        }
    }

    override fun getAll(): Single<List<TvShow>> {
        return Single.create { emitter ->
            favoriteDao.getAll().let { list ->
                emitter.onSuccess(list.map { mapper.reverse(it) })
            }
        }
    }

    override fun delete(element: TvShow): Completable {
        return Completable.create {
            favoriteDao.delete(mapper.map(element))
            it.onComplete()
        }
    }
}