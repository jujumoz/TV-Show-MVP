package sierra.com.domain.mapper

interface Mapper<T, M> {
    fun map(value: T): M

    fun map(values: List<T>): List<M> = values.map { map(it) }

    fun reverse(value: M): T {
        throw RuntimeException("NOT IMPLEMENTED")
    }
}