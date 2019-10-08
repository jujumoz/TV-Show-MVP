package sierra.com.domain.usecase


import androidx.annotation.Nullable
import io.reactivex.Completable
import io.reactivex.Single

abstract class UseCase<in P : UseCase.Params, out R> {

    interface Params

    object NotUseCaseParams : Params

    abstract fun build(@Nullable params: P? = null): R
}

abstract class CompletableUseCase<P : UseCase.Params> : UseCase<P, Completable>()

abstract class SingleUseCase<T, P : UseCase.Params> : UseCase<P, Single<T>>()