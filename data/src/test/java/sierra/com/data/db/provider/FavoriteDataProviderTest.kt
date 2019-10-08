package sierra.com.data.db.provider

import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import sierra.com.data.db.dao.TvShowDao
import sierra.com.data.db.entity.TvShowDB
import sierra.com.domain.mapper.Mapper
import sierra.com.domain.model.TvShow

@RunWith(MockitoJUnitRunner::class)
class FavoriteDataProviderTest {

    @Mock
    lateinit var dao: TvShowDao
    @Mock
    lateinit var mapper: Mapper<TvShow, TvShowDB>
    @Mock
    lateinit var favoriteDB: TvShowDB
    @Mock
    lateinit var show: TvShow

    private val dataProvider by lazy {
        TvShowDataProvider(dao, mapper)
    }

    @Test
    fun `should get favorites`() {
        Mockito.`when`(dao.getAll()).thenReturn(listOf(favoriteDB))

        dataProvider.getAll().test()

        Mockito.verify(dao).getAll()
        Mockito.verify(mapper).reverse(favoriteDB)
    }

    @Test
    fun `should delete favorites`() {
        Mockito.`when`(mapper.map(show)).thenReturn(favoriteDB)

        dataProvider.delete(show).test()

        Mockito.verify(dao).delete(favoriteDB)
        Mockito.verify(mapper).map(show)
    }

    @Test
    fun `should store favorites`() {
        Mockito.`when`(mapper.map(show)).thenReturn(favoriteDB)

        dataProvider.store(show).test()

        Mockito.verify(dao).insertAll(favoriteDB)
        Mockito.verify(mapper).map(show)
    }
}