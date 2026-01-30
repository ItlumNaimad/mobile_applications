package pbs.edu.project.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "photos")
data class PhotoEntry(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val uri: String,
    val title: String,
    val latitude: Double,
    val longitude: Double,
    val timestamp: Long
)
