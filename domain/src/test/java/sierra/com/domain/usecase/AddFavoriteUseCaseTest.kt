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
class AddFavoriteUseCaseTest {

    @Mock
    private lateinit var repository: FavoriteRepository
    @Mock
    private lateinit var show: TvShow

    private val addFavoriteUseCase by lazy {
        AddFavoriteUseCase(repository)
    }

    @Before
    fun setUp() {
        Mockito.`when`(repository.add(show)).thenReturn(Completable.complete())
    }

    @Test
    fun `add favorite if parameter is correct`() {
        addFavoriteUseCase.build(AddFavoriteUseCase.Params(show))
            .test()
            .assertNoErrors()

        Mockito.verify(repository).add(show)
    }

    @Test
    fun `return error when try to add favorite with parameter incorrect`() {
        addFavoriteUseCase.build(null)
            .test()
            .assertError(RuntimeException::class.java)

        Mockito.verify(repository, never()).add(show)
    }
}