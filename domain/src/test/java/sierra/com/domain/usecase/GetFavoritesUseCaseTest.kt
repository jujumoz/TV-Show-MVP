package sierra.com.domain.usecase

import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import sierra.com.domain.model.TvShow
import sierra.com.domain.repository.FavoriteRepository

@RunWith(MockitoJUnitRunner::class)
class GetFavoritesUseCaseTest {

    @Mock
    private lateinit var repository: FavoriteRepository
    @Mock
    private lateinit var show: TvShow

    private val getFavoritesUseCase by lazy {
        GetFavoritesUseCase(repository)
    }

    @Before
    fun setUp() {
        Mockito.`when`(repository.getAll()).thenReturn(Single.just(listOf(show)))
    }

    @Test
    fun `should get favorite`() {
        getFavoritesUseCase.build()
            .test()
            .assertNoErrors()

        Mockito.verify(repository).getAll()
        Mockito.verify(show).favorite = true
    }
}