package com.example.ckweather.data.database.weather

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [WeatherItem::class, SettingItem::class], version = 2)
abstract class WeatherDatabase: RoomDatabase(){
    abstract fun WeatherDao(): WeatherDao
    abstract fun SettingDao(): SettingDao
}

object WeatherDB{
    private var database: WeatherDatabase? = null
    fun getInstance(context: Context): WeatherDatabase{
        if(database == null){
            database = Room.databaseBuilder(
                context,
                WeatherDatabase::class.java,
                "weather_database"
            ).build()
        }
        return database!!
    }
}