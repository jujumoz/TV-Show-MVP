package sierra.com.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import sierra.com.data.db.dao.TvShowDao
import sierra.com.data.db.entity.TvShowDB

@Database(
    entities = [
        TvShowDB::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun tvShowDao(): TvShowDao
}