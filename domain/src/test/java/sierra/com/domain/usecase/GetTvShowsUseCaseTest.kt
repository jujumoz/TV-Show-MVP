package sierra.com.domain.usecase

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.anyOrNull
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.never
import org.mockito.junit.MockitoJUnitRunner
import sierra.com.domain.model.TvShow
import sierra.com.domain.repository.FavoriteRepository
import sierra.com.domain.repository.TvShowRepository

private const val SEARCH = "blabla"

@RunWith(MockitoJUnitRunner::class)
class GetTvShowsUseCaseTest {

    @Mock
    private lateinit var repository: TvShowRepository
    @Mock
    private lateinit var favoriteRepository: FavoriteRepository
    @Mock
    private lateinit var show: TvShow

    private val getTvShowsUseCase by lazy {
        GetTvShowsUseCase(repository, favoriteRepository)
    }

    @Before
    fun setUp() {
        Mockito.`when`(repository.getAll(anyOrNull())).thenReturn(Single.just(listOf(show)))

        Mockito.`when`(favoriteRepository.getAll()).thenReturn(Single.just(listOf(show)))
    }

    @Test
    fun `should get shows when param is not null`() {
        getTvShowsUseCase.build(GetTvShowsUseCase.Params(SEARCH))
            .test()
            .assertNoErrors()

        Mockito.verify(repository).search(SEARCH)
    }

    @Test
    fun `should not get shows when param is null`() {
        getTvShowsUseCase.build(null)
            .test()
            .assertNoErrors()

        Mockito.verify(repository).getAll(any())
    }

    @Test
    fun `should get tagged show when show is favorite`() {
        getTvShowsUseCase.build(GetTvShowsUseCase.Params(SEARCH))
            .test()
            .assertNoErrors()

        Mockito.verify(favoriteRepository).getAll()
        Mockito.verify(show).favorite = true
    }

    @Test
    fun `should not get tagged show when show is not favorite`() {
        Mockito.`when`(favoriteRepository.getAll()).thenReturn(Single.just(listOf()))

        getTvShowsUseCase.build(GetTvShowsUseCase.Params(SEARCH))
            .test()
            .assertNoErrors()

        Mockito.verify(favoriteRepository).getAll()
        Mockito.verify(show, never()).favorite = true
    }
}