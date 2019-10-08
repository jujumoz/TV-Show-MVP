package sierra.com.data.repository

import io.reactivex.Completable
import io.reactivex.Single
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import sierra.com.data.db.provider.LocalDataProvider
import sierra.com.domain.model.TvShow

@RunWith(MockitoJUnitRunner::class)
class FavoriteRepositoryImplTest {

    @Mock
    lateinit var provider: LocalDataProvider<TvShow>
    @Mock
    lateinit var show: TvShow

    private val repository by lazy {
        FavoriteRepositoryImpl(provider)
    }

    @Test
    fun `should add favorite`() {
        Mockito.`when`(provider.store(show)).thenReturn(Completable.complete())

        repository.add(show).test()

        Mockito.verify(provider).store(show)
    }

    @Test
    fun `should remove favorite`() {
        Mockito.`when`(provider.delete(show)).thenReturn(Completable.complete())

        repository.remove(show).test()

        Mockito.verify(provider).delete(show)
    }

    @Test
    fun `should get all favorites`() {
        Mockito.`when`(provider.getAll()).thenReturn(Single.just(listOf(show)))

        repository.getAll().test()

        Mockito.verify(provider).getAll()
    }
}