package sierra.com.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import sierra.com.data.db.RoomConstants

@Entity(tableName = RoomConstants.TV_SHOW_TABLE)
data class TvShowDB(

    @ColumnInfo(name = "airStamp")
    val airStamp: String?,

    @ColumnInfo(name = "airDate")
    val airDate: String?,

    @ColumnInfo(name = "airtime")
    val airtime: String?,

    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "name")
    val name: String?,

    @ColumnInfo(name = "number")
    val number: Int?,

    @ColumnInfo(name = "runtime")
    val runtime: Int?,

    @ColumnInfo(name = "season")
    val season: Int?,

    @ColumnInfo(name = "url")
    val url: String?,

    @ColumnInfo(name = "image")
    val image: String?
)