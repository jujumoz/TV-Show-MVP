package sierra.com.domain.repository

import io.reactivex.Single
import sierra.com.domain.model.TvShow

interface TvShowRepository {

    fun getAll(date: String): Single<List<TvShow>>

    fun search(search: String): Single<List<TvShow>>
}
