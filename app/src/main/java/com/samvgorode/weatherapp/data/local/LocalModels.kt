package com.samvgorode.weatherapp.data.local

import androidx.room.*

@Entity(tableName = "weather")
data class WeatherInCity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "icon_name") val iconName: String?,
    @ColumnInfo(name = "description") val description: String?,
    @ColumnInfo(name = "date") val date: Long?,
    @ColumnInfo(name = "temperature") val temperature: Double?,
    @ColumnInfo(name = "temperature_min") val temperatureMin: Double?,
    @ColumnInfo(name = "temperature_max") val temperatureMax: Double?,
)

@Dao
interface WeatherDao {
    @Query("SELECT * FROM weather ORDER BY date DESC LIMIT 5 ")
    suspend fun getAll(): List<WeatherInCity>

    @Query("SELECT * FROM weather WHERE name LIKE :name LIMIT 1")
    suspend fun getByCityName(name: String): WeatherInCity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeather(weather: WeatherInCity)

    @Delete
    suspend fun deleteWeather(weather: WeatherInCity)
}
