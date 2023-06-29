package com.example.ckweather.data.database.weather

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface SettingDao {
    @Insert
    suspend fun insertAll(weather: SettingItem)

    @Update
    suspend fun update(weather: SettingItem)

    @Query("SELECT * FROM setting_table")
    fun getAll(): Flow<List<SettingItem>>

}