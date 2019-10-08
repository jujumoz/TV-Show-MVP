package sierra.com.data.repository

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.never
import io.reactivex.Single
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import sierra.com.data.entity.TvShowEntity
import sierra.com.data.networking.ShowService
import sierra.com.domain.mapper.Mapper
import sierra.com.domain.model.TvShow

private const val DATE = "date"

@RunWith(MockitoJUnitRunner::class)
class TvShowRepositoryImplTest {

    @Mock
    lateinit var service: ShowService
    @Mock
    lateinit var mapper: Mapper<TvShowEntity, TvShow>
    @Mock
    lateinit var tvShowEntity: TvShowEntity

    private val repository by lazy {
        TvShowRepositoryImpl(service, mapper)
    }

    @Test
    fun `should get tv show when network is ok`() {
        `when`(service.getAll(any(), any())).thenReturn(Single.just(listOf(tvShowEntity)))

        repository.getAll(DATE).test()

        Mockito.verify(service).getAll(any(), eq(DATE))
        Mockito.verify(mapper).map(listOf(tvShowEntity))
    }

    @Test
    fun `should not get tv show when network is ko`() {
        val networkError = Exception()

        `when`(service.getAll(any(), any())).thenReturn(Single.error(networkError))

        repository.getAll(DATE).test().assertError(networkError)

        Mockito.verify(service).getAll(any(), eq(DATE))
        Mockito.verify(mapper, never()).map(listOf(tvShowEntity))
    }
}