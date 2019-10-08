package sierra.com.domain.usecase

import io.reactivex.Completable
import sierra.com.domain.model.TvShow
import sierra.com.domain.repository.FavoriteRepository
import javax.inject.Inject

class RemoveFavoriteUseCase @Inject constructor(
    private val repository: FavoriteRepository
) : CompletableUseCase<RemoveFavoriteUseCase.Params>() {

    override fun build(params: Params?): Completable {
        return params?.let {
            repository.remove(it.tvShow)
        } ?: run {
            Completable.error(RuntimeException())
        }
    }

    data class Params(
        val tvShow: TvShow
    ) : UseCase.Params
}