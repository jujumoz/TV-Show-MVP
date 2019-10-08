package sierra.com.data.db.provider

import io.reactivex.Completable
import io.reactivex.Single

interface LocalDataProvider<T> {

    fun get(identifier: String): Single<T> = Single.error(Throwable())

    fun store(element: T): Completable = Completable.error(Throwable())

    fun delete(element: T): Completable = Completable.error(Throwable())

    fun delete(identifier: String): Completable = Completable.error(Throwable())

    fun getAll(): Single<List<T>> = Single.error(Throwable())
}