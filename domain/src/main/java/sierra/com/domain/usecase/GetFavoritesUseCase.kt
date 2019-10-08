package sierra.com.domain.usecase

import io.reactivex.Single
import sierra.com.domain.model.TvShow
import sierra.com.domain.repository.FavoriteRepository
import javax.inject.Inject

class GetFavoritesUseCase @Inject constructor(
    private val repository: FavoriteRepository
) : SingleUseCase<List<TvShow>, UseCase.NotUseCaseParams>() {

    override fun build(params: NotUseCaseParams?): Single<List<TvShow>> {
        return repository.getAll().map { shows ->
            shows.apply {
                forEach { tvShow ->
                    tvShow.favorite = true
                }
            }
        }
    }
}