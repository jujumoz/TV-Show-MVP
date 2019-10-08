package sierra.com.domain.usecase

import io.reactivex.Single
import sierra.com.domain.model.TvShow
import sierra.com.domain.repository.FavoriteRepository
import sierra.com.domain.repository.TvShowRepository
import sierra.com.domain.utils.PairZipperFunc2
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

private const val DATE_FORMAT = "yyyy-MM-dd"

class GetTvShowsUseCase @Inject constructor(
    private val repository: TvShowRepository,
    private val favoriteRepository: FavoriteRepository
) : SingleUseCase<List<TvShow>, GetTvShowsUseCase.Params>() {

    private var data: List<TvShow> = listOf()

    override fun build(params: Params?): Single<List<TvShow>> {
        val single = params?.let {
            repository.search(it.search)
        } ?: repository.getAll(getToday())

        return single.flatMap { list ->
            list.forEach { show -> show.star = isNewShow(show) }

            Single.zip(Single.just(list), favoriteRepository.getAll(), PairZipperFunc2())
        }.map { pair ->
            val list = pair.first
            val favorites = pair.second
            list.apply {
                forEach { show ->
                    show.favorite = favorites.any { favorite -> favorite.id == show.id }
                }
            }
        }
    }

    private fun isNewShow(tvShow: TvShow): Boolean {
        val dateFormat = SimpleDateFormat(DATE_FORMAT, Locale.getDefault())

        val twoYearAgo = Calendar.getInstance().apply {
            set(Calendar.YEAR, get(Calendar.YEAR) - 2)
        }
        val showDate = tvShow.show?.premiered?.let { dateFormat.parse(it) } ?: twoYearAgo.time

        return showDate > twoYearAgo.time
    }

    private fun getToday(): String {
        return SimpleDateFormat(DATE_FORMAT, Locale.getDefault())
            .format(Calendar.getInstance().time)
    }

    fun refreshFavorite(): Single<List<TvShow>> {
        return Single.zip(Single.just(data), favoriteRepository.getAll(), PairZipperFunc2())
            .map { pair ->
                val list = pair.first
                val favorite = pair.second
                list.apply {
                    forEach { show ->
                        show.favorite = favorite.contains(show)
                    }
                }
            }
    }

    data class Params(
        val search: String
    ) : UseCase.Params
}