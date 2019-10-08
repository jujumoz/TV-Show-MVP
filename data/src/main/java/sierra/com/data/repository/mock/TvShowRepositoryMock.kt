package sierra.com.data.repository.mock

import android.app.Application
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.reactivex.Single
import sierra.com.data.entity.TvShowEntity
import sierra.com.data.extensions.getTextFromFileAssets
import sierra.com.domain.mapper.Mapper
import sierra.com.domain.model.TvShow
import sierra.com.domain.repository.TvShowRepository
import javax.inject.Inject

class TvShowRepositoryMock @Inject constructor(
    private val context: Application,
    private val mapper: Mapper<TvShowEntity, TvShow>
) : TvShowRepository {

    override fun getAll(date: String): Single<List<TvShow>> {
        return getMockTvShow("default_tv_show_mock.txt")
    }

    override fun search(search: String): Single<List<TvShow>> {
        return getMockTvShow("search_tv_show_mock.txt")
    }

    private fun getMockTvShow(fileName: String): Single<List<TvShow>> {
        val fileAsString = context.getTextFromFileAssets(fileName)

        val turnsType = object : TypeToken<List<TvShowEntity>>() {}.type

        return Gson().fromJson<List<TvShowEntity>>(fileAsString, turnsType)
            ?.map { mapper.map(it) }
            .let { Single.just(it) }
    }
}