package sierra.com.data.repository

import io.reactivex.Completable
import io.reactivex.Single
import sierra.com.data.db.provider.LocalDataProvider
import sierra.com.domain.model.TvShow
import sierra.com.domain.repository.FavoriteRepository
import javax.inject.Inject

class FavoriteRepositoryImpl @Inject constructor(
    private val dataProvider: LocalDataProvider<TvShow>
) : FavoriteRepository {

    override fun add(show: TvShow): Completable {
        return dataProvider.store(show)
    }

    override fun remove(show: TvShow): Completable {
        return dataProvider.delete(show)
    }

    override fun getAll(): Single<List<TvShow>> {
        return dataProvider.getAll()
    }
}