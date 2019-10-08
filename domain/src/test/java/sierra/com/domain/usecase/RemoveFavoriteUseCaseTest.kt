package sierra.com.domain.usecase

import io.reactivex.Completable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.never
import org.mockito.junit.MockitoJUnitRunner
import sierra.com.domain.model.TvShow
import sierra.com.domain.repository.FavoriteRepository

@RunWith(MockitoJUnitRunner::class)
class RemoveFavoriteUseCaseTest {

    @Mock
    private lateinit var repository: FavoriteRepository
    @Mock
    private lateinit var show: TvShow

    private val deleteFavoriteUseCase by lazy {
        RemoveFavoriteUseCase(repository)
    }

    @Before
    fun setUp() {
        Mockito.`when`(repository.remove(show)).thenReturn(Completable.complete())
    }

    @Test
    fun `should remove favorite when param is not null`() {
        deleteFavoriteUseCase.build(RemoveFavoriteUseCase.Params(show))
            .test()
            .assertNoErrors()

        Mockito.verify(repository).remove(show)
    }

    @Test
    fun `should not remove favorite when param is null`() {
        deleteFavoriteUseCase.build(null)
            .test()
            .assertError(RuntimeException::class.java)

        Mockito.verify(repository, never()).remove(show)
    }
}