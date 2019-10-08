package sierra.com.domain.repository

import io.reactivex.Completable
import io.reactivex.Single
import sierra.com.domain.model.TvShow

interface FavoriteRepository {

    fun add(show: TvShow): Completable

    fun remove(show: TvShow): Completable

    fun getAll(): Single<List<TvShow>>
}