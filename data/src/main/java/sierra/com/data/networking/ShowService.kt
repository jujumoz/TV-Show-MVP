package sierra.com.data.networking


import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import sierra.com.data.entity.TvShowEntity

interface ShowService {

    //http://api.tvmaze.com/schedule?country=FR&date=2019-09-09
    @GET("schedule")
    fun getAll(
        @Query("country") country: String,
        @Query("date") date: String
    ): Single<List<TvShowEntity>>

    @GET("/search/shows")
    fun search(
        @Query("q") query: String
    ): Single<List<TvShowEntity>>
}