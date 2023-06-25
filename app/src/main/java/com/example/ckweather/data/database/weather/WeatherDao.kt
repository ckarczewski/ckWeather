package com.example.ckweather.data.database.weather

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface WeatherDao {
    @Insert
    suspend fun insertAll(weather: List<WeatherItem>)

    @Delete
    suspend fun delete(weather: List<WeatherItem>)

    @Update
    suspend fun update(weather: WeatherItem)

    @Query("SELECT * FROM weather_table")
    fun getAll(): Flow<List<WeatherItem>>

    @Query("DELETE FROM weather_table")
    suspend fun dropDatabase()
}