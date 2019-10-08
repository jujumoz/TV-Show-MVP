package sierra.com.domain.utils

import io.reactivex.functions.BiFunction
import io.reactivex.functions.Function3


class PairZipperFunc2<T, R> : BiFunction<T, R, Pair<T, R>> {
    override fun apply(t1: T, t2: R): Pair<T, R> = Pair(t1, t2)
}

class PairZipperFunc3<T, R, M> : Function3<T, R, M, Triple<T, R, M>> {
    override fun apply(t1: T, t2: R, t3: M): Triple<T, R, M> = Triple(t1, t2, t3)
}
