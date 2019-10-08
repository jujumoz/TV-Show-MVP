package sierra.com.data.db.dao

import androidx.room.*
import sierra.com.data.db.entity.TvShowDB

@Dao
interface TvShowDao {

    @Query("SELECT * FROM tv_show WHERE id = :identifier")
    fun get(identifier: String): TvShowDB

    @Query("SELECT * FROM tv_show")
    fun getAll(): List<TvShowDB>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg favorite: TvShowDB)

    @Delete
    fun delete(vararg favorite: TvShowDB)
}